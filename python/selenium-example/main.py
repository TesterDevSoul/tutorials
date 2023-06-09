import time
# 导入selenium 包
from selenium import webdriver

# 创建一个 Chromdriver 的实例。Chrome()会从环境变量中寻找浏览器驱动
driver = webdriver.Chrome()
# 打开网址
driver.get("https://www.baidu.com/")
# 强等10s 秒
time.sleep(10)