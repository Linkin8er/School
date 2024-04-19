import tensorflow as tf

print("Num GPUs Available: ", len(tf.config.list_physical_devices('GPU')))
if tf.test.is_gpu_available(cuda_only=False, min_cuda_compute_capability=None):
    print("Default GPU Device: ", tf.test.gpu_device_name())
else:
    print("Please install GPU version of TF")