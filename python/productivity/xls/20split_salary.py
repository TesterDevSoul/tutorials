# 20split_salary.py 工资单的拆分

import xlrd
import xlwt
from pathlib import Path, PurePath

# 工资单文件
salary_file = '工资单.xls'
# 拆分文件保存路径
dst_path = './工资单'


# 读Excel文件
data = xlrd.open_workbook(salary_file)
# 读sheet
table = data.sheets()[0]

# 取得表头
# rowx=line：表示要获取的行索引，line 是一个整数，表示行号，从0开始计数。
# start_colx=0：表示要开始提取数据的列索引，0 表示从第一列开始。
# end_colx=None：表示要结束提取数据的列索引，None 表示提取到最后一列。
salary_header = table.row_values(rowx=0, start_colx=0, end_colx=None)

# 定义写入文件的函数 有几行写几个文件
def write_to_file(filename, cnt):
    '''
    filename : 写入的文件名
    cnt      : 写入的内容
    '''
    workbook = xlwt.Workbook(encoding='utf-8')
    xlsheet = workbook.add_sheet("本月工资")

    row = 0
    for line in cnt:
        print(f"line:{line}")
        col = 0
        for cell in line:
            xlsheet.write(row, col, cell)
            col += 1
        row += 1

    folder = Path(dst_path)

    # 文件路径拼接 PurePath 类用于处理纯路径字符串，而不进行实际的文件操作。
    # 将目标路径 dst_path 中的文件名替换为 filename，并将文件扩展名更改为 .xls。
    # 这可以用于生成具有指定文件名和扩展名的新路径。
    excel_file = folder / PurePath(dst_path).with_name(filename).with_suffix('.xls')
    workbook.save(excel_file)



# 取得员工数量 有几行
employee_number = table.nrows
# 取得每一行,并用第二个单元格作为新的文件名
for line in range(1,employee_number):
    # 读出来每一行
    # rowx=line：表示要获取的行索引，line 是一个整数，表示行号，从0开始计数。
    # start_colx=0：表示要开始提取数据的列索引，0 表示从第一列开始。
    # end_colx=None：表示要结束提取数据的列索引，None 表示提取到最后一列。
    content = table.row_values(rowx=line, start_colx=0, end_colx=None)
    # print(content) end_colx=None：表示要结束提取数据的列索引，None 表示提取到最后一列。
    # 将表头和员工数量重新组成一个新的文件
    new_content = []
    # 增加表头到要写入的内容中
    new_content.append(salary_header)
    # 增加员工工资到要写入的内容中
    new_content.append(content)
    # 调用自定义函数write_to_file()写入新的文件 content[1]第二列
    write_to_file(filename = content[1], cnt = new_content)
