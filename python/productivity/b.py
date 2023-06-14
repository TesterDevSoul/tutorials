
from pathlib import Path, PurePath

import xlrd

# 指定要合并excel的路径
src_path = '/Users/gaigai/Desktop/测试用例'

# 取得该目录下所有的xlsx格式文件
p = Path(src_path)
files = [x for x in p.iterdir() if PurePath(x).match('*.xls')]
print(files)
# 对每一个文件进行重复处理
for file in files:
    data = xlrd.open_workbook(file)
    table = data.sheets()[0]# Sheet  0:<Sheet1>
    print(table)