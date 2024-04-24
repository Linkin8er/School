import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, Dataset
import numpy as np
import matplotlib.pyplot as plt
from tqdm import tqdm
import os
import cv2

# Check for GPU availability and set the device accordingly
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(f'Using device: {device}')

dir_path = r"C:/Computer Vision/img_align_celeba/img_align_celeba"

class ImageDataset(Dataset):
    def __init__(self, folder_path, img_size=(128, 128), limit=75000):
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
        return len(self.data)

    def __getitem__(self, idx):
        img = self.data[idx]
        return torch.tensor(img, dtype=torch.float32).permute(2, 0, 1).to(device)

img_size = 64
dataset = ImageDataset(dir_path, (img_size, img_size))
dataloader = DataLoader(dataset, batch_size=150, shuffle=True)

fixed_noise = torch.randn(60, 128, device=device)

class Discriminator(nn.Module):
    def __init__(self):
        super(Discriminator, self).__init__()
        self.model = nn.Sequential(
            nn.Conv2d(3, 256, 3, stride=2, padding=1),
            nn.LeakyReLU(0.2),
            nn.Conv2d(256, 128, 3, stride=2, padding=1),
            nn.LeakyReLU(0.2),
            nn.BatchNorm2d(128),
            nn.Conv2d(128, 64, 3, stride=2, padding=1),
            nn.LeakyReLU(0.2),
            nn.BatchNorm2d(64),
            nn.Flatten(),
            nn.Linear(64 * 8 * 8, 1)
        ).to(device)

    def forward(self, x):
        return self.model(x)

class Generator(nn.Module):
    def __init__(self):
        super(Generator, self).__init__()
        self.model = nn.Sequential(
            nn.Linear(128, 8 * 8 * 128),
            nn.BatchNorm1d(8 * 8 * 128),
            nn.ReLU(True),
            nn.Unflatten(1, (128, 8, 8)),
            nn.ConvTranspose2d(128, 256, 3, stride=2, padding=1, output_padding=1),
            nn.LeakyReLU(0.2),
            nn.BatchNorm2d(256),
            nn.ConvTranspose2d(256, 128, 3, stride=2, padding=1, output_padding=1),
            nn.LeakyReLU(0.2),
            nn.BatchNorm2d(128),
            nn.ConvTranspose2d(128, 3, 3, stride=2, padding=1, output_padding=1),
            nn.Sigmoid()
        ).to(device)

    def forward(self, x):
        return self.model(x)

def show_images(images):
    images = images.permute(0, 2, 3, 1).cpu()  # Move images to CPU and convert to (batch, height, width, channels) for plotting
    for i in range(20):
        plt.subplot(4, 5, i + 1)
        plt.imshow(images[i])
        plt.xticks([])
        plt.yticks([])
    plt.show()

D = Discriminator()
G = Generator()

optimizer_G = optim.Adam(G.parameters(), lr=0.0001)
optimizer_D = optim.Adam(D.parameters(), lr=0.0001)

criterion = nn.BCEWithLogitsLoss()

num_epochs = 125
for epoch in range(num_epochs):
    for real_images in tqdm(dataloader):
        batch_size = real_images.size(0)

        optimizer_D.zero_grad()
        real_labels = torch.ones(batch_size, 1, device=device)
        fake_labels = torch.zeros(batch_size, 1, device=device)
        
        real_output = D(real_images)
        real_loss = criterion(real_output, real_labels)

        noise = torch.randn(batch_size, 128, device=device)
        fake_images = G(noise)
        fake_output = D(fake_images.detach())
        fake_loss = criterion(fake_output, fake_labels)

        disc_loss = real_loss + fake_loss
        disc_loss.backward()
        optimizer_D.step()

        optimizer_G.zero_grad()
        output = D(fake_images)
        gen_loss = criterion(output, real_labels)
        gen_loss.backward()
        optimizer_G.step()

    print(f'Epoch [{epoch+1}/{num_epochs}], Loss D: {disc_loss.item()}, Loss G: {gen_loss.item()}')

with torch.no_grad():
    test_images = G(fixed_noise)
    show_images(test_images)
