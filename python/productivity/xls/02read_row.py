# 02read_row.py 读Sheet1 测试用例每一行
import xlrd

file = 'LiteMall.xls'

# --------------- 获取Sheet对象 ---------------
# 打开 Excel 文件，file 是文件路径或类似文件对象的参数
data = xlrd.open_workbook(file)
# 获取 Excel 文件中第一个 sheet  Sheet  0:<Sheet1>
sheet = data.sheets()[0]
print("----------------  开始遍历 Excel 文件中具体的Sheet的每一行  -------------")
# 遍历 Excel 文件中的所有行  读取 Excel 文件中的每一行数据。
# range(sheet.nrows)是一个Python中的内置函数和内置类型structrange。
# 它返回一个range对象(范围对象)，该对象表示从0开始，到sheet对象的行数(sheet.nrows)结束的数字序列。
# 这个返回值通常被用在for循环的语句中，来遍历sheet对象中的所有行。例如：
for row_index in range(sheet.nrows):
    row = sheet.row_values(row_index)
    print(row)


