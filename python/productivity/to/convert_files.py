# excel文件夹xls 所有的sheet转为XLSX
import os
from pathlib import PurePath, Path

import openpyxl
import xlrd

src_path = '/Users/gaigai/tutorials/python/productivity/测试用例xls'
xlsx_file = '/Users/gaigai/tutorials/python/productivity/测试用例xlsx'
# 文件夹路径
folder_path = Path(xlsx_file)

# 检查文件夹是否存在，不存在则创建
if not folder_path.exists():
    folder_path.mkdir()


# 检查文件夹内是否有数据
if any(folder_path.iterdir()):
    # 删除文件夹中的所有文件
    for file_path in folder_path.iterdir():
        if file_path.is_file():
            file_path.unlink()

p = Path(src_path)
files = [x for x in p.iterdir() if PurePath(x).match('*.xls')]


for file in files:
    file_size = os.path.getsize(file)
    # 如果文件大小大于0，则继续读取文件
    if file_size > 0:

        # 打开原始的 .xls 文件
        workbook = xlrd.open_workbook(file)

        # 创建新的 .xlsx 文件
        new_workbook = openpyxl.Workbook()

        # 遍历原始文件中的每个工作表
        for sheet_name in workbook.sheet_names():
            # 获取原始工作表对象
            worksheet = workbook[sheet_name]
            # 创建新的工作表对象
            new_worksheet = new_workbook.create_sheet(title=sheet_name)
            # 遍历原始工作表的每一行
            for row_index in range(worksheet.nrows):
                # 获取指定行数据的方法
                row_data = worksheet.row_values(row_index)
                #
                # 将每一行数据写入新的工作表
                new_worksheet.append(row_data)

        # 删除默认创建的 Sheet
        del new_workbook['Sheet']

        # 生成新的文件名（将原文件名的扩展名改为 .xlsx）
        # 用于分离文件名和扩展名的函数。它接受一个文件路径作为输入，并返回一个包含文件名和扩展名的元组。
        # new_file_name = os.path.splitext(file.name)[0] + '.xlsx'
        # 分离文件名和扩展名
        file_path = Path(file.name)
        # 文件名
        file_name = file_path.stem
        # 扩展名
        file_extension = file_path.suffix
        new_file = file_name + '.xlsx'
        new_file_name = Path(xlsx_file) / new_file
        print(new_file_name)

        # 保存为新的 .xlsx 文件
        new_workbook.save(new_file_name)
