from io import BytesIO
from openpyxl.drawing.image import Image

import openpyxl
import pandas as pd
from matplotlib import pyplot as plt

new_excel = '/Users/gaigai/Desktop/结果/Little.xlsx'

# 使用 pandas 库、使用 `xlrd` 引擎读取名为 excel_file 的 Excel 文件
# 当使用pd.read_excel()函数时，我们将sheet_name参数设置为None。
# 这表示pandas会从输入文件中读取所有工作表，并将其存储在一个字典中，
# 其中键值为工作表名，值为包含工作表数据的数据框架（DataFrame）。
df = pd.read_excel(new_excel, engine='openpyxl')

# 将目标列数据转换为布尔类型，然后筛选出其中的True和False
column_name = "实际结果"  # 更改为您要检查的列名，例如"A", "B"等
# df_bool = .astype(bool)
# 使用value_counts()方法获取True和False计数
true_false_counts = df[column_name].value_counts()
# 显示结果
# 使用value_counts()方法获取True和False计数
true_false_counts = df[column_name].value_counts()
true_count = true_false_counts.get(True, 0)
false_count = true_false_counts.get(False, 0)
print("True count:", true_count)
print("False count:", false_count)

labels = ['True', 'False']
sizes = [true_count, false_count]

print(sizes)
plt.pie(sizes, labels=labels, autopct='%1.1f%%')
plt.title("test",)
plt.axis('equal')  # 设置为正圆形
# plt.show()
# 将饼图保存到内存中的图片文件中
buf = BytesIO()
plt.savefig(buf, format='png')
buf.seek(0)  # 重置文件指针


# 使用 openpyxl 处理新文件并插入图片
workbook1 = openpyxl.load_workbook(new_excel)
ws = workbook1.active


image = Image(buf)
ws.add_image(image, 'M1')  # 将图片插入到 Excel 的 'D1' 单元格

 # 保存含有图像的 Excel 文件
workbook1.save(new_excel)