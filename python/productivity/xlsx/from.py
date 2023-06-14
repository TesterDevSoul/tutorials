import pandas as pd

new_excel = '/Users/gaigai/Desktop/LiteMall.xlsx'

# 使用 pandas 库、使用 `openpyxl` 引擎读取名为 new_excel 的 Excel 文件
df = pd.read_excel(new_excel, engine='openpyxl')

# 更改为您要检查的列名，例如"A", "B"等
column_name = "A"
# 使用value_counts()方法获取True和False计数
true_false_counts = df[column_name].value_counts()
# 统计个数
true_count = true_false_counts.get(True, 0)
false_count = true_false_counts.get(False, 0)

print("True count:", true_count)
print("False count:", false_count)

labels = ['True', 'False']
sizes = [true_count, false_count]
