# 11write_result.py 调查结果的写入
#!/usr/bin/env python
#coding=utf-8
import xlwt

# content 是一个嵌套列表，而不是字典列表
content = [['员工姓名', '第一题', '第二题'], ['韩梅梅', 'D', 'C'], ['李雷', 'B', 'B'], ['李丽', 'A', 'A']]

print("----------------  带样式写入调查结果  -------------")
# 创建 Excel 文件，utf-8在excel中输出中文，默认是ascii
workbook = xlwt.Workbook(encoding='utf-8')
# 创建一个sheet 名为调查结果
worksheet = workbook.add_sheet("调查结果")
# 初始化样式
style = xlwt.XFStyle()
# 为样式创建字体
font = xlwt.Font()
font.name = 'Times New Roman'
font.bold = True  # 粗体
font.underline = True # 下划线
font.italic = True  # 斜体字
style.font = font  # 设定样式

# 通过 enumerate() 函数同时遍历 content（一个字典的列表）的索引（行号）和元素（行数据）。
# for row_num, row_data in enumerate(content, start=0):
for row_num, row_data in enumerate(content):
    # print(row_data) ['员工姓名', '第一题', '第二题']
    for col_num, cell_value in enumerate(row_data):
        # 将列表中的数据写入对应的单元格
        worksheet.write(row_num, col_num, cell_value, style)

# 保存 Excel 文件
dst_file = '调查表汇总.xls'
workbook.save(dst_file)
