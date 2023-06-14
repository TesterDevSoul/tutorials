import os

import xlrd
from pathlib import Path, PurePath

import xlwt

# 指定要合并excel的路径
src_path = '/Users/gaigai/Desktop/测试用例xls'



# 取得该目录下所有的xls格式文件
# /Users/gaigai/Desktop/测试用例
p = Path(src_path)
# 列表推导式
files = [x for x in p.iterdir() if PurePath(x).match('*.xls')]
# files = []
# p.iterdir() #遍历目录 p 中的所有文件和子目录
# for x in p.iterdir():
# # 判断文件路径 x 是否匹配 '*.xls' 模式，即文件扩展名是否为 `.xls`
#     if PurePath(x).match('*.xls'):
#         files.append(x)


# 存储所有行数据的字典列表
dict_rows = []



# todo: 双层for循环读取文件夹内的每一行
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
        column_names = sheet.row_values(0)
        # 遍历 Excel 文件中的所有行 读取 Excel 文件中的每一行数据
        # 只处理了第一个 sheet
        # 遍历 Excel 文件中的所有行（跳过第一行的标题行）
        for row_index in range(1, sheet.nrows):
            row = sheet.row_values(row_index)
            # 将当前行的字典添加到字典列表中
            dict_rows.append(row)
print(dict_rows)



print("----------------  写入文件 Excel 文件中的所有行  -------------")

workbook = xlwt.Workbook(encoding='utf-8')
worksheet = workbook.add_sheet("统计结果")
print(f"column_names:\n{column_names}")
# ['用例编号', '模块', '测试标题', '优先级', '前置条件', 'URL', '请求方法', '请求参数', '预期结果', '实际结果']
# 0,用例编号
for col_num, column_title in enumerate(column_names):
    # 第一行写入列标题 0 第一行 col_num
    worksheet.write(0, col_num, column_title)

# dict_rows 是一个嵌套列表，而不是字典列表
# 通过 enumerate() 函数同时遍历 dict_rows（一个字典的列表）的索引（行号）和元素（行数据）。
# start=1 参数表示下标从 1 开始计数，而不是默认从 0 开始。
for row_num, row_data in enumerate(dict_rows, start=1):
    for col_num, cell_value in enumerate(row_data):
        # 将列表中的数据写入对应的单元格
        worksheet.write(row_num, col_num, cell_value)

# 保存 Excel 文件
# 指定合并完成的路径
dst_file = '/Users/gaigai/Desktop/结果.xls'
# new_file_path = 'path/to/new_file.xls'
# workbook.save(new_file_path)
workbook.save(dst_file)