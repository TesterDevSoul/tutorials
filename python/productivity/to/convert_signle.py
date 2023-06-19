# 单个excel文件xls 所有的sheet转为XLSX
# .xls 是较旧的二进制格式，而 .xlsx 是基于 Office Open XML 格式的较新格式。
# 转换过程中，数据可能会保留，但某些特定的格式和功能可能会受到限制或丢失。
import openpyxl
import xlrd



excel_file = '/Users/gaigai/Desktop/LiteMall.xls'
xlsx_file = '/Users/gaigai/Desktop/LiteMall.xlsx'

# 打开原始的 .xls 文件
workbook = xlrd.open_workbook(excel_file)

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
        # 将每一行数据写入新的工作表
        new_worksheet.append(row_data)

# 删除默认创建的 Sheet
del new_workbook['Sheet']

# 保存为新的 .xlsx 文件
new_workbook.save(xlsx_file)
