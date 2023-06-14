import xlrd

file = '/Users/gaigai/Desktop/LiteMall.xls'

# 打开 Excel 文件，file 是文件路径或类似文件对象的参数
data = xlrd.open_workbook(file)
# 获取 Excel 文件中第一个 sheet  Sheet  0:<Sheet1>
sheet = data.sheets()[0]
# 获取工作表（将 0 替换为需要的工作表索引）
# sheet = data.sheet_by_index(0)


# 获取行数
number_of_rows = sheet.nrows
print(f"当前Sheet的行数: {number_of_rows}")

# 获取特定行的数据（这里将 rowIndex 替换为需要的行号，从下标0开始 如 1）
rowIndex = 1
row_values = sheet.row_values(rowIndex)
print(f"第 2 行数据:\n{row_values}")

print("----------------  开始遍历 Excel 文件中的所有行  -------------")

# 遍历 Excel 文件中的所有行
for row_index in range(sheet.nrows):
    row = sheet.row_values(row_index)
    print(row)


# 打印具体某一行某一列

# 获取表格中第 3 行（从 0 开始计数）第 5 列（从 0 开始计数，即 E 列）的单元格内容。
# rowx 行 colx 列
value = sheet.cell_value(rowx=2, colx=4)
# 注意，这里将第 3 行和第 1 列作为参数传递给 cell_value 函数，
# 因为在 Python 中计数从 0 开始。如果想读取 Excel 文件中的其他单元格，
# 只需要将相应的行数和列数传递给 cell_value 函数即可。
print(f"第 3 行第 5 列:\n{value}")


print("----------------  获取 Sheet2 调查表结果  -------------")


table_header = ['员工姓名', '第一题', '第二题']
content = []
content.append(table_header)

# sheet2的选项结果
# username = "韩梅梅"
# 1. 根据下标获取sheet
sheet2 = data.sheet_by_index(1)
# 2. 获取sheet的名称 作为员工姓名 打印
username = sheet2.name
print(username)
# sheet2 = data.sheet_by_name(username)
# 取得每一项的结果
# 3. 获取第一道题的选项
answer1 = sheet2.cell_value(rowx=4, colx=4)
# 4. 获取第二道题的选项
answer2 = sheet2.cell_value(rowx=10, colx=4)
# 5. 合并为一行存储
temp = f'{username},{answer1},{answer2}'
# 合并为一行先存储起来
content.append(temp.split(','))
print(content)

