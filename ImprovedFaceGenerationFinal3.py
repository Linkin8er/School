import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, Dataset
import numpy as np
import matplotlib.pyplot as plt
from tqdm import tqdm
import os
import cv2

# Check if GPU is available and set the device to GPU or CPU accordingly
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(f'Using device: {device}')

# Define the directory path for dataset
dir_path = r"C:/Computer Vision/img_align_celeba/img_align_celeba"

# Custom dataset class for loading images
class ImageDataset(Dataset):
    def __init__(self, folder_path, img_size=(128, 128), limit=100000):
        """
        Initialize the dataset with a path, image size, and limit on the number of images.
        folder_path: Path to the directory containing images.
        img_size: Tuple indicating the size to which images are resized.
        limit: Maximum number of images to load.
        """
        self.img_size = img_size
        self.data = []
        for img_name in tqdm(os.listdir(folder_path)):
            if len(self.data) >= limit:
                break
            img_path = os.path.join(folder_path, img_name)
            img = cv2.imread(img_path)
            img = cv2.resize(img, self.img_size)
            img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
            self.data.append(img / 255.0)  # Normalize the images

    def __len__(self):
        """
        Return the total number of items in the dataset.
        """
        return len(self.data)

    def __getitem__(self, idx):
        """
        Retrieve an image by index, convert it to a tensor, and move it to the specified device.
        idx: Index of the image to retrieve.
        """
        img = self.data[idx]
        return torch.tensor(img, dtype=torch.float32).permute(2, 0, 1).to(device)

# Set image size and create the dataset and dataloader
img_size = 64
dataset = ImageDataset(dir_path, (img_size, img_size))
dataloader = DataLoader(dataset, batch_size=150, shuffle=True)

# Create a fixed noise vector for generating images from the generator
fixed_noise = torch.randn(60, 128, device=device)

# Discriminator model
class Discriminator(nn.Module):
    def __init__(self):
        super(Discriminator, self).__init__()
        self.model = nn.Sequential(
            nn.Conv2d(3, 64, 4, stride=2, padding=1),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Conv2d(64, 128, 4, stride=2, padding=1),
            nn.BatchNorm2d(128),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Conv2d(128, 256, 4, stride=2, padding=1),
            nn.BatchNorm2d(256),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Conv2d(256, 512, 4, stride=2, padding=1),
            nn.BatchNorm2d(512),
            nn.LeakyReLU(0.2, inplace=True),
            nn.Conv2d(512, 1, 4, stride=1, padding=0),
            nn.Flatten(),  # Add Flatten to handle the 1x1 dimension
            nn.Sigmoid()
        ).to(device)

    def forward(self, x):
        """
        Forward pass of discriminator model to classify real from fake.
        x: Input tensor representing a batch of images.
        """
        return self.model(x)


# Generator model
class Generator(nn.Module):
    def __init__(self):
        super(Generator, self).__init__()
        # Sequential model for generator
        self.model = nn.Sequential(
            nn.Linear(128, 4*4*1024),
            nn.BatchNorm1d(4*4*1024),
            nn.ReLU(True),
            nn.Unflatten(1, (1024, 4, 4)),
            nn.ConvTranspose2d(1024, 512, 4, stride=2, padding=1),
            nn.BatchNorm2d(512),
            nn.ReLU(True),
            nn.ConvTranspose2d(512, 256, 4, stride=2, padding=1),
            nn.BatchNorm2d(256),
            nn.ReLU(True),
            nn.ConvTranspose2d(256, 128, 4, stride=2, padding=1),
            nn.BatchNorm2d(128),
            nn.ReLU(True),
            nn.ConvTranspose2d(128, 3, 4, stride=2, padding=1),
            nn.Tanh()  # Output layer normalization to match input data distribution
        ).to(device)

    def forward(self, x):
        """
        Forward pass of generator model to generate images from noise.
        x: Input tensor representing a batch of random noise vectors.
        """
        return self.model(x)

# Function to display images
def show_images(images):
    """
    Display a grid of generated images.
    images: Tensor containing generated images.
    """
    images = images.permute(0, 2, 3, 1).cpu()  # Convert to CPU and format for plotting
    for i in range(20):
        plt.subplot(4, 5, i + 1)
        plt.imshow(images[i])
        plt.xticks([])
        plt.yticks([])
    plt.show()

# Initialize the discriminator and generator
D = Discriminator()
G = Generator()

# Define optimizers for generator and discriminator with specific hyperparameters
optimizer_G = optim.Adam(G.parameters(), lr=0.0002, betas=(0.5, 0.999))
optimizer_D = optim.Adam(D.parameters(), lr=0.0002, betas=(0.5, 0.999))

# Loss function
criterion = nn.BCELoss()

# Training loop for the GAN
num_epochs = 300
for epoch in range(num_epochs):
    for real_images in tqdm(dataloader):
        batch_size = real_images.size(0)

        real_labels = torch.ones(batch_size, 1, device=device)
        fake_labels = torch.zeros(batch_size, 1, device=device)

        # Train Discriminator
        optimizer_D.zero_grad()
        real_output = D(real_images)
        d_loss_real = criterion(real_output, real_labels)
        noise = torch.randn(batch_size, 128, device=device)
        fake_images = G(noise)
        fake_output = D(fake_images.detach())
        d_loss_fake = criterion(fake_output, fake_labels)
        d_loss = d_loss_real + d_loss_fake
        d_loss.backward()
        optimizer_D.step()

        # Train Generator
        optimizer_G.zero_grad()
        output = D(fake_images)
        g_loss = criterion(output, real_labels)
        g_loss.backward()
        optimizer_G.step()

    print(f'Epoch [{epoch+1}/{num_epochs}], Loss D: {d_loss.item()}, Loss G: {g_loss.item()}')

# Generate images using the fixed noise and display them
with torch.no_grad():
    test_images = G(fixed_noise)
    show_images(test_images)
