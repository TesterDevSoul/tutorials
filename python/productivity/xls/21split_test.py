# 不带样式的测试用例拆分

import xlrd
import xlwt

# 打开原始 Excel 文件
workbook = xlrd.open_workbook('用例汇总.xls')

# 获取第一个工作表
worksheet = workbook.sheet_by_index(0)

# 拆分出P0级别的用例
workbook_p0 = xlwt.Workbook()

# 在工作簿中创建新的工作表
worksheet_p0 = workbook_p0.add_sheet('P0')

# 列索引
# 根据第 3 列优先级进行拆分，索引从0开始计数
column_index = 3

# 获取当前测试用例有几行
test_number = worksheet.nrows

header = worksheet.row_values(rowx=0, start_colx=0, end_colx=None)

# 将表头和数量重新组成一个新的文件
new_content = []
# 增加表头到要写入的内容中
new_content.append(header)

# 遍历每一行
for line in range(1, test_number):  # 从第二行开始，忽略标题行
    print(line)
    # cell_value = worksheet.cell_value(row, column_index)
    content = worksheet.row_values(rowx=line, start_colx=0, end_colx=None)
    print(f"content:{content}")
    # 根据列值将数据写入不同的工作表
    if content[3] == 'P0':
        # 增加P0用例到要写入的内容中
        new_content.append(content)


# 初始化样式
style = xlwt.XFStyle()
# 为样式创建字体
font = xlwt.Font()
font.name = '仿宋'
font.bold = True  # 粗体
font.underline = True # 下划线
font.italic = True  # 斜体字
style.font = font  # 设定样式

for row_num, row_data in enumerate(new_content):
    # print(row_data) ['用例编号', '模块', '测试标题', '优先级', '前置条件', 'URL', '请求方法', '请求参数', '预期结果', '实际结果']
    for col_num, cell_value in enumerate(row_data):

        if col_num == 9:
            if cell_value == 1:
                cell_value = True
            elif cell_value == 0:
                cell_value = False
        # 不带样式的输入 将列表中的数据写入对应的单元格
        print(f"1::{row_num}, {col_num}, {cell_value}")
        worksheet_p0.write(row_num, col_num, cell_value)


# 保存拆分后的工作簿
workbook_p0.save('UI_p0.xls')
