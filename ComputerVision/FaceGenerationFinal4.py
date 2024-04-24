import torch
import torch.nn as nn
import torch.optim as optim
from torch.optim.lr_scheduler import ReduceLROnPlateau
from torch.utils.data import DataLoader, Dataset
import numpy as np
import matplotlib.pyplot as plt
from tqdm import tqdm
import os
import cv2

# Check GPU availability and set the device accordingly
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(f'Using device: {device}')

# Define the directory path for the dataset
dir_path = r"C:/Computer Vision/img_align_celeba/img_align_celeba"

class ImageDataset(Dataset):
    """ Dataset class for loading and preprocessing images """
    def __init__(self, folder_path, img_size=(128, 128), limit=150000):
        self.img_size = img_size
        self.data = []
        for img_name in tqdm(os.listdir(folder_path)):
            if len(self.data) >= limit:
                break
            img_path = os.path.join(folder_path, img_name)
            img = cv2.imread(img_path)
            img = cv2.resize(img, self.img_size)
            img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
            self.data.append(img / 255.0)  # Normalize images to range [0, 1]

    def __len__(self):
        return len(self.data)

    def __getitem__(self, idx):
        img = self.data[idx]
        return torch.tensor(img, dtype=torch.float32).permute(2, 0, 1).to(device)

# Set image size and create dataset and dataloader
img_size = 64
dataset = ImageDataset(dir_path, (img_size, img_size))
dataloader = DataLoader(dataset, batch_size=150, shuffle=True)

# Fixed noise vector for generating consistent images from the generator
fixed_noise = torch.randn(60, 128, device=device)

class Discriminator(nn.Module):
    """ Discriminator model with convolutional layers """
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
            nn.Flatten(),  # Flatten the output to match label dimensions
            nn.Sigmoid()
        ).to(device)

    def forward(self, x):
        return self.model(x)

class Generator(nn.Module):
    """ Generator model with transposed convolutional layers """
    def __init__(self):
        super(Generator, self).__init__()
        self.model = nn.Sequential(
            nn.Linear(128, 4*4*1024),
            nn.InstanceNorm1d(4*4*1024),
            nn.ReLU(True),
            nn.Unflatten(1, (1024, 4, 4)),
            nn.ConvTranspose2d(1024, 512, 4, stride=2, padding=1),
            nn.InstanceNorm2d(512),
            nn.ReLU(True),
            nn.ConvTranspose2d(512, 256, 4, stride=2, padding=1),
            nn.InstanceNorm2d(256),
            nn.ReLU(True),
            nn.ConvTranspose2d(256, 128, 4, stride=2, padding=1),
            nn.InstanceNorm2d(128),
            nn.ReLU(True),
            nn.ConvTranspose2d(128, 3, 4, stride=2, padding=1),
            nn.Tanh()  # Output normalization to [-1, 1]
        ).to(device)

    def forward(self, x):
        return self.model(x)

def show_images(images):
    """ Function to display a grid of images """
    images = images.permute(0, 2, 3, 1).cpu()  # Move images to CPU and convert for plotting
    plt.figure(figsize=(10, 10))
    for i in range(20):
        plt.subplot(4, 5, i + 1)
        plt.imshow(images[i])
        plt.axis('off')
    plt.show()

# Initialize models and define optimizers and loss function
D = Discriminator()
G = Generator()
optimizer_G = optim.Adam(G.parameters(), lr=0.0002, betas=(0.5, 0.999))
optimizer_D = optim.Adam(D.parameters(), lr=0.0002, betas=(0.5, 0.999))
criterion = nn.BCELoss()

# Learning rate schedulers to adjust learning rates based on training progress
scheduler_G = ReduceLROnPlateau(optimizer_G, mode='min', factor=0.2, patience=5, verbose=True)
scheduler_D = ReduceLROnPlateau(optimizer_D, mode='min', factor=0.2, patience=5, verbose=True)

# Training loop
num_epochs = 300
for epoch in range(num_epochs):
    total_d_loss = 0.0
    total_g_loss = 0.0
    for real_images in tqdm(dataloader):
        batch_size = real_images.size(0)

        # Real and fake labels
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
        total_d_loss += d_loss.item()

        # Train Generator
        optimizer_G.zero_grad()
        output = D(fake_images)
        g_loss = criterion(output, real_labels)
        g_loss.backward()
        optimizer_G.step()
        total_g_loss += g_loss.item()

    # Calculate average losses
    avg_d_loss = total_d_loss / len(dataloader)
    avg_g_loss = total_g_loss / len(dataloader)
    print(f'Epoch [{epoch+1}/{num_epochs}], Loss D: {avg_d_loss}, Loss G: {avg_g_loss}')

    # Adjust learning rate based on the average losses
    scheduler_G.step(avg_g_loss)
    scheduler_D.step(avg_d_loss)

# Generate and display images using the fixed noise
with torch.no_grad():
    test_images = G(fixed_noise)
    show_images(test_images)
