import torch

# Check if a GPU is available and set PyTorch to use the GPU
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(f'Using device: {device}')
print(torch.cuda.get_device_name())