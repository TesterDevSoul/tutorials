# 01read_ex.py xls文件读的示例操作
import xlrd

file = 'LiteMall.xls'

# --------------- 获取Sheet对象 ---------------
# 打开 Excel 文件，file 是文件路径或类似文件对象的参数
data = xlrd.open_workbook(file)
# 获取 Excel 文件中第一个 sheet  Sheet  0:<Sheet1>
sheet = data.sheets()[0]
print(f"{file}的第 1 个Sheet:\n{sheet}")
sheet2 = data.sheet_by_index(1)# 获取工作表（将 0 替换为需要的工作表索引）
print(f"{file}的第 2 个Sheet:\n{sheet2}")


# --------------- 获取当前Sheet行数 ---------------
number_of_rows = sheet.nrows
print(f"{file}第 1 个Sheet的行数: {number_of_rows}")


# --------------- 获取特定Sheet的特定行的数据 ---------------
# 下标从0开始
rowIndex = 1
row_values = sheet.row_values(rowIndex)
print(f"{file}第 1 个Sheet的第 2 行数据:\n{row_values}")

# --------------- 读取具体单元格的数据 ---------------
# 获取表格中第 2 行（从 0 开始计数）第 1 列（从 0 开始计数，即 A 列）的单元格内容。
# rowx 行  rowx=1
# colx 列  colx=0
value = sheet.cell_value(rowx=1, colx=0)
# 注意，这里将第 2 行和第 1 列作为参数传递给 cell_value 函数，因为在 Python 中计数从 0 开始。
# 如果想读取 Excel 文件中的其他单元格，只需要将相应的行数和列数传递给 cell_value 函数即可。
print(f"{file}第 1 个Sheet的第 2 行第 1 列:\n{value}")

