# 测试用例合并
import os

import xlrd
import xlwt
from pathlib import Path, PurePath
# 导入excel和文件操作库

# 检查是否存在目录，如果不存在则创建它
directory = '/Users/gaigai/Desktop/测试用例/result'
if not os.path.exists(directory):
    os.makedirs(directory)

# 将文件路径更改为新的目录路径
new_file_path = os.path.join(directory, '结果.xls')


# 指定要合并excel的路径
src_path = '/Users/gaigai/Desktop/测试用例'
# 指定合并完成的路径
dst_file = '/Users/gaigai/Desktop/测试用例/结果.xls'

# 取得该目录下所有的xls格式文件
p = Path(src_path)
files = [x for x in p.iterdir() if PurePath(x).match('*.xls')]

# 定义列名称列表
column_names = ["用例编号", "模块", "测试标题", "优先级", "前置条件", "URL", "请求方法", "请求参数", "预期结果", "实际结果"]


# 准备一个列表存放读取结果
content = []
# 存储所有行数据的字典列表
dict_rows = []
# dict_rows.append(column_names)

# 对每一个文件进行重复处理
for file in files:
    # 用文件名作为每个用户的标识
    username = file.stem
    data = xlrd.open_workbook(file)
    sheet = data.sheets()[0]
    # 遍历 Excel 文件中的所有行
    # 通过上述代码，你可以读取 Excel 文件中的每一行数据。
    # 请注意，这段代码只处理了第一个 sheet，
    # 请根据实际情况调整代码以满足您的需求。
    # 如果您的 Excel 文件包含多个 sheet，
    # 可以使用 workbook.sheet_by_index(sheet_index)，
    # 把 sheet_index 替换成对应的 sheet 序号 (从 0 开始)，
    # 或者使用 workbook.sheet_by_name(sheet_name)，
    # 把 sheet_name 替换成对应的 sheet 名字。
    # 遍历 Excel 文件中的所有行（跳过第一行的标题行）
    for row_index in range(1, sheet.nrows):
        row = sheet.row_values(row_index)
        # 使用 zip() 函数将列名称与行数据组合
        # row_dict = dict(zip(column_names, row))

        # 将当前行的字典添加到字典列表中
        dict_rows.append(row)
# print(dict_rows)
print(dict_rows)
# 使用 zip() 函数将列名称与行数据组合
# row_dict = dict(zip(column_names, dict_rows))

# print(row_dict)
# 准备写入文件的表头
# table_header = ['员工姓名', '第一题', '第二题']
#
workbook = xlwt.Workbook(encoding='utf-8')
worksheet = workbook.add_sheet("统计结果")

for col_num, column_title in enumerate(column_names):
    # 第一行写入列标题
    worksheet.write(0, col_num, column_title)

# 假设 row_dict 是一个嵌套列表，而不是字典列表
for row_num, row_data in enumerate(dict_rows, start=1):
    for col_num, cell_value in enumerate(row_data):
        # 将列表中的数据写入对应的单元格
        worksheet.write(row_num, col_num, cell_value)

# 保存 Excel 文件
# new_file_path = 'path/to/new_file.xls'
# workbook.save(new_file_path)
workbook.save(dst_file)


# # 写入表头
# row = 0
# col = 0
# for cell_header in table_header:
#     xlsheet.write(row, col, cell_header)
#     col += 1
#
# # 向下移动一行
# row += 1
# # 取出每一行内容
# for line in content:
#     col = 0
#     # 取出每个单元格内容
#     for cell in line:
#         # 写入内容
#         xlsheet.write(row, col, cell)
#         # 向右移动一个单元格
#         col += 1
#     # 向下移动一行
#     row += 1
# # 保存最终结果
# workbook.save(dst_file)