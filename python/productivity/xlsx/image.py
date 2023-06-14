from io import BytesIO

import openpyxl
from matplotlib import pyplot as plt
from openpyxl.drawing.image import Image


new_excel = '/Users/gaigai/Desktop/结果/Little.xlsx'

labels = ['A', 'B', 'C', 'D']
sizes = [25, 30, 20, 25]

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
ws.add_image(image, 'M1')  # 将图片插入到 Excel 的 'M1' 单元格

 # 保存含有图像的 Excel 文件
workbook1.save(new_excel)