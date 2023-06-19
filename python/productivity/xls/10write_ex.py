# 写入Excel文件中具体单元格
#!/usr/bin/env python
#coding=utf-8
import xlwt

print("----------------  不带样式的写入Excel文件具体行  -------------")

# 1. 创建 Excel 文件，utf-8在excel中输出中文，默认是ascii
workbook = xlwt.Workbook(encoding='utf-8')
# 2. 创建一个sheet 名为示例
# def add_sheet(self, sheetname, cell_overwrite_ok=False):
worksheet = workbook.add_sheet("示例")
# 不带样式的写入Excel文件中第一行第一列
# def write(self, r, c, label="", style=Style.default_style):
worksheet.write(0, 0, '不带样式的写入')
# 保存 Excel 文件
# 指定合并完成的路径
dst_file = 'Write.xls'
workbook.save(dst_file)


print("----------------  带样式的写入Excel文件具体行  -------------")

# 初始化样式
# 创建可应用于Excel工作表单元格的新样式定义。
# 它返回XFStyle类的一个实例，该实例可用于自定义单元格的外观，如字体样式、边框、对齐方式等
style = xlwt.XFStyle()
# 为样式创建字体
font = xlwt.Font()
font.name = 'Times New Roman'
font.bold = True  # 粗体
font.underline = True # 下划线
font.italic = True  # 斜体字
style.font = font  # 设定样式
# 不带样式的写入Excel文件中第2行第一列
worksheet.write(1, 0, '带样式的写入', style)

workbook.save(dst_file)