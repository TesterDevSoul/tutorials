# 04read_test.py 读取文件夹下的所有Excel

import os
import xlrd
from pathlib import Path, PurePath

print("----------------  读取文件夹下的所有Excel  -------------")

# 指定要合并excel的路径
src_path = '../测试用例xls'

# pathlib 模块提供了一组面向对象的类，这些类可代表各种操作系统上的路径，程序可通过这些类操作路径。
# 取得该目录下所有的xls格式文件
p = Path(src_path)#输出格式。PosixPath('.')
# 列表推导式
# files = [x for x in p.iterdir() if PurePath(x).match('*.xls')]
files = []
# https://docs.python.org/3/library/pathlib.html#pathlib.Path.iterdir
# 遍历目录 p 中的所有文件和子目录 # 遍历当前目录下所有文件和子目录
for x in p.iterdir():
    # print(x)
    #https://docs.python.org/3/library/pathlib.html#pathlib.PurePath.match
    # print(PurePath(x).match('*.xls'))
    # 判断文件路径 x 是否匹配 '*.xls' 模式，即文件扩展名是否为 `.xls`
    if PurePath(x).match('*.xls'):
        files.append(x)

print(files)
print()
print()

# 存储所有行数据的字典列表
dict_rows = []

print("----------------  读取文件夹下的所有Excel的第一个Sheet数据  -------------")


# todo: 双层for循环读取文件夹内的每一行；如果是第一个文件, 包括表头
# 对每一个文件进行重复处理
for file in files:
    # 使用了os库的os.path.getsize()函数来检查Excel文件的大小，
    # 如果文件大小大于0，将继续读取并处理该文件。
    # 将输出一个错误消息。这将避免抛出XLRDError: File size is 0 bytes错误。
    file_size = os.path.getsize(file)
    # 如果文件大小大于0，则继续读取文件
    if file_size > 0:
        # 如果文件大小大于0，则继续读取文件
        # 打开 Excel 文件，file 是文件路径或类似文件对象的参数
        data = xlrd.open_workbook(file)
        # 获取工作表（将 0 替换为需要的工作表索引）
        sheet = data.sheet_by_index(0)
        # 标题头获取
        # 逐行和逐列复制输入工作表中的数据
        if file == files[0]:  # 如果是第一个文件, 包括表头
            column_names = sheet.row_values(0)
            dict_rows.append(column_names)
        # 遍历 Excel 文件中的所有行 读取 Excel 文件中的每一行数据
        # 只处理了第一个 sheet
        # 遍历 Excel 文件中的所有行（跳过第一行的标题行）
        for row_index in range(1, sheet.nrows):
            row = sheet.row_values(row_index)
            # 将当前行的字典添加到字典列表中
            dict_rows.append(row)
print(dict_rows)