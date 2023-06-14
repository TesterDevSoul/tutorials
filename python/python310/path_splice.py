# 路径拼接
import os
from pathlib import Path

# Windows: example_folder\example_file.txt
# Linux 或 macOS: example_folder/example_file.txt

# 文件目录
path = '/Users/gaigai/tutorials/python/python310'
# 文件名
file = 'path_splice.py'


# 方式一：os.path
new_path = os.path.join(path, file)
print(new_path)

# 方式二：pathlib
folder = Path(path)
# 使用 pathlib 进行路径拼接
new_path = folder / file
print(new_path)
