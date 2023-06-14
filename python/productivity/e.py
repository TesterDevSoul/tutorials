# -*- coding: utf-8 -*-
from io import BytesIO

import matplotlib.pyplot as plt

from d import wb

labels = ['A', 'B', 'C', 'D']
sizes = [25, 30, 20, 25]

plt.pie(sizes, labels=labels, autopct='%1.1f%%')
plt.title("title",)
plt.axis('equal')  # 设置为正圆形
plt.show()
# 将饼图保存到内存中的图片文件中
buf = BytesIO()
plt.savefig(buf, format='png')
buf.seek(0)  # 重置文件指针

#
# s = wb.active
#
# image = Image(buf)
# ws.add_image(image, 'A1')  # 将图片插入到 Excel 的 'A1' 单元格
#
# # 保存 Excel 文件
# wb.save("example_chart.xlsx")