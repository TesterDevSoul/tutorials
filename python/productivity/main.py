import xlrd
from xlrd import sheet

file = '/Users/gaigai/Desktop/LiteMall.xls'
# 这段代码使用了第三方库 xlrd，用于操作 Excel 文件，其意义如下：

# 打开 Excel 文件，file 是文件路径或类似文件对象的参数
data = xlrd.open_workbook(file)
# 获取 Excel 文件中第一个 sheet
sheet = data.sheets()[0] # Sheet  0:<Sheet1>
# 获取表格中第 5 行（从 0 开始计数）第 5 列（从 0 开始计数，即 E 列）的单元格内容。
# rowx 行 colx 列
# value = table.cell_value(rowx=2, colx=2)
# 注意，这里将第 5 行和第 5 列作为参数传递给 cell_value 函数，
# 因为在 Python 中计数从 0 开始。如果想读取 Excel 文件中的其他单元格，
# 只需要将相应的行数和列数传递给 cell_value 函数即可。
# print(value)

# 存储所有行数据的字典列表
dict_rows = []

# 遍历 Excel 文件中的所有行
# 通过上述代码，你可以读取 Excel 文件中的每一行数据。
# 请注意，这段代码只处理了第一个 sheet，
# 请根据实际情况调整代码以满足您的需求。
# 如果您的 Excel 文件包含多个 sheet，
# 可以使用 workbook.sheet_by_index(sheet_index)，
# 把 sheet_index 替换成对应的 sheet 序号 (从 0 开始)，
# 或者使用 workbook.sheet_by_name(sheet_name)，
# 把 sheet_name 替换成对应的 sheet 名字。
for row_index in range(sheet.nrows):
    row = sheet.row_values(row_index)
    # 使用 zip() 函数将列名称与行数据组合
    # row_dict = dict(zip(column_names, row))

    # 将当前行的字典添加到字典列表中
    dict_rows.append(row)
    # print(row)
# 字典接收
print(dict_rows)
