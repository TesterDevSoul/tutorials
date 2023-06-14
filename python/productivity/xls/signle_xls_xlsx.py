# 只写入sheet1的内容
from pathlib import Path

import pandas as pd

file_path = '/Users/gaigai/Desktop'

folder = Path(file_path)
# xls文件名
xls_name = 'LiteMall.xls'
# 文件路径拼接
excel_file = folder / xls_name
# 使用 pandas 库、使用 `xlrd` 引擎读取名为 excel_file 的 Excel 文件
df = pd.read_excel(excel_file, engine="xlrd")
new_name = xls_name.split(".")[0]
xlsx_name = new_name + '.xlsx'
# 使用 pathlib 进行路径拼接
xlsx_file = folder / xlsx_name
print(f"新生成的文件路径为：{xlsx_file}")
# 将 DataFrame 保存到新的 Excel 文件中，不包括索引列
df.to_excel(xlsx_file, index=False)