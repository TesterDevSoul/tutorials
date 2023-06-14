# 写入文件所有sheet的内容
from pathlib import Path

import pandas as pd

file_path = '/Users/gaigai/Desktop'

folder = Path(file_path)
# xls文件名
xls_name = 'LiteMall.xls'
# 文件路径拼接
excel_file = folder / xls_name
# 使用 pandas 库、使用 `xlrd` 引擎读取名为 excel_file 的 Excel 文件
# 当使用pd.read_excel()函数时，我们将sheet_name参数设置为None。
# 这表示pandas会从输入文件中读取所有工作表，并将其存储在一个字典中，
# 其中键值为工作表名，值为包含工作表数据的数据框架（DataFrame）。
xls = pd.read_excel(excel_file, engine='xlrd', sheet_name=None)
new_name = xls_name.split(".")[0]
xlsx_name = new_name + '.xlsx'
# 使用 pathlib 进行路径拼接
xlsx_file = folder / xlsx_name
print(f"新生成的文件路径为：{xlsx_file}")

# 使用pd.ExcelWriter()上下文管理器来创建一个.xlsx文件，
# 并使用data.to_excel()方法在每次迭代时将数据写入对应的工作表。
with pd.ExcelWriter(xlsx_file, engine='openpyxl') as writer:
    for sheet_name, data in xls.items():
        data.to_excel(writer, index=False, sheet_name=sheet_name)

# df.to_excel(xlsx_file, index=False)
print(f"已将 {excel_file} 的全部工作表写入到 {xlsx_file}")