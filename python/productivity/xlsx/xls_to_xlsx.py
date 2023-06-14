import os
from pathlib import Path,PurePath
import pandas as pd

# xls 转换为 xlsx
src_path = '/Users/gaigai/Desktop/测试用例xls'
xlsx_path = '/Users/gaigai/Desktop/测试用例xlsx'

p=Path(src_path)
files=[x for x in p.iterdir() if PurePath(x).match('*.xls')]

# todo: 双层for循环读取文件夹内的每一行
# 对每一个文件进行重复处理
for file in files:
    # 使用了os库的os.path.getsize()函数来检查Excel文件的大小，
    # 如果文件大小大于0，将继续读取并处理该文件。
    # 将输出一个错误消息。这将避免抛出XLRDError: File size is 0 bytes错误。
    file_size = os.path.getsize(file)
    # 如果文件大小大于0，则继续读取文件
    if file_size > 0:
        df = pd.read_excel(file, engine="xlrd")
        name = file.name.split(".")[0]
        # 另存为新的 Excel 文件（.xlsx）  os.path.join(folder, file)
        # 在Python中，为了确保跨平台兼容性，建议使用os.path.join() 进行路径拼接。
        # 它会根据您的操作系统选择正确的路径分隔符（在Windows上是反斜杠 \，在其他大多数操作系统上是正斜杠 /）。
        # Windows: example_folder\example_file.txt
        # Linux 或 macOS: example_folder/example_file.txt
#        new_excel = os.path.join(xlsx_path, name + '.xlsx')
        #  Python 3.4 或更高版本，您还可以使用 pathlib 库进行路径操作，
        #  这是一个对路径操作进行了更高级封装的库，旨在替代 os.path。
        folder = Path(xlsx_path)
        file = name + '.xlsx'
        # 使用 pathlib 进行路径拼接
        new_excel = folder / file
        print(new_excel)
        # 将 DataFrame 保存到新的 Excel 文件中，不包括索引列
        df.to_excel(new_excel, index=False)