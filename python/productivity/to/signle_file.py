# 单个excel文件xls 第一个sheet转为XLSX
from pathlib import Path
import pandas as pd


excel_file = '/Users/gaigai/Desktop/LiteMall.xls'
xlsx_file = '/Users/gaigai/Desktop/LiteMall.xlsx'

# 方式一： 使用 pandas 库、使用 `xlrd` 引擎
# 使用 pandas 库、使用 `xlrd` 引擎读取名为 excel_file 的 Excel 文件
# 读取文件中的所有工作表
dfs = pd.read_excel(excel_file, engine="xlrd", sheet_name=None)
# 将 DataFrame 保存到新的 Excel 文件中，不包括索引列
# df.to_excel(xlsx_file, index=False)
with pd.ExcelWriter(xlsx_file) as writer:
    for sheet_name, df in dfs.items():
        # 将存在空标题行的列命名为 "Unnamed: col_num"
        df.columns = ['标题: {}'.format(col_num) if "Unnamed" in str(col_name).strip() else col_name for col_num, col_name
                      in enumerate(df.columns)]
        # new_columns = []
        # for col_num, col_name in enumerate(df.columns):
        #     print(str(col_name).strip())
        #     if "Unnamed" in str(col_name).strip():
        #         # print(col_num)
        #         col_name = '标题: {}'.format(col_num)
        #     new_columns.append(col_name)
        # df.columns = new_columns
        df.to_excel(writer, sheet_name=sheet_name, index=False)


#
# file_path = '/Users/gaigai/Desktop'
#
# folder = Path(file_path)
# # xls文件名
# xls_name = 'LiteMall.xls'
# # 文件路径拼接
# excel_file = folder / xls_name
#
# new_name = xls_name.split(".")[0]
# xlsx_name = new_name + '.xlsx'
# # 使用 pathlib 进行路径拼接
# xlsx_file = folder / xlsx_name
# print(f"新生成的文件路径为：{xlsx_file}")
#
# # 方式一： 使用 pandas 库、使用 `xlrd` 引擎
# # 使用 pandas 库、使用 `xlrd` 引擎读取名为 excel_file 的 Excel 文件
# # 读取文件中的所有工作表
# dfs = pd.read_excel(excel_file, engine="xlrd", sheet_name=None)
# # 将 DataFrame 保存到新的 Excel 文件中，不包括索引列
# # df.to_excel(xlsx_file, index=False)
# with pd.ExcelWriter(xlsx_file) as writer:
#     for sheet_name, df in dfs.items():
#         # 将存在空标题行的列命名为 "Unnamed: col_num"
#         df.columns = ['标题: {}'.format(col_num) if "Unnamed" in str(col_name).strip() else col_name for col_num, col_name
#                       in enumerate(df.columns)]
#         # new_columns = []
#         # for col_num, col_name in enumerate(df.columns):
#         #     print(str(col_name).strip())
#         #     if "Unnamed" in str(col_name).strip():
#         #         # print(col_num)
#         #         col_name = '标题: {}'.format(col_num)
#         #     new_columns.append(col_name)
#         # df.columns = new_columns
#         df.to_excel(writer, sheet_name=sheet_name, index=False)
# with pd.ExcelWriter(xlsx_file) as writer:
#
#     # 打印数据帧
#     for sheet_name, df in dfs.items():
#         print(f'DataFrame for sheet {sheet_name}:')
#         print(df)
#
#         df.to_excel(xlsx_file, index=False)


# 方式二：

# dict_rows = []
# # 打开 Excel 文件，file 是文件路径或类似文件对象的参数
# data = xlrd.open_workbook(excel_file)
# # 获取工作表（将 0 替换为需要的工作表索引）
# sheet = data.sheet_by_index(0)
# # 遍历 Excel 文件中的所有行 读取 Excel 文件中的每一行数据
# # 只处理了第一个 sheet
# # 遍历 Excel 文件中的所有行（跳过第一行的标题行）
# for row_index in range(sheet.nrows):
#     row = sheet.row_values(row_index)
#     # 将当前行的字典添加到字典列表中
#     dict_rows.append(row)
# print(dict_rows)
#
# worksheets = data.sheet_names()
# # 激活第一个表单
# workbook_new = openpyxl.Workbook()
# worksheet_new = workbook_new.active
# for worksheet_name in worksheets:
#     worksheet = data.sheet_by_name(worksheet_name)
#     worksheet_new = workbook_new.create_sheet(title=worksheet_name)
#
#     # 复制所有单元格
#     for row in range(worksheet.nrows):
#         for col in range(worksheet.ncols):
#             cell_value = worksheet.cell_value(row, col)
#             worksheet_new.cell(row=row + 1, column=col + 1, value=cell_value)
#
# # 保存文件
# workbook_new.save('example.xlsx')