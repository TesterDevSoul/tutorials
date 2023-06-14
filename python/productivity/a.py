import pandas as pd

file = '/Users/gaigai/Desktop/LiteMall.xlsx'
# Pandas 默认情况下，为了节省空间，只会显示一部分数据。
# 您可以使用以下代码来设置显示所有列和行的方式。
pd.set_option('display.max_columns', None)
pd.set_option('display.max_rows', None)
# 使用 Pandas 的 read_excel() 函数读取 Excel 文件。 engine = 'openpyxl' 参数指定使用的引擎是 openpyxl。
df = pd.read_excel(
    file,
    engine='openpyxl'
)

print(df)