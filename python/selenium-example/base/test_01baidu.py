import time

from selenium import webdriver


# 创建一个 Chromdriver 的实例。Chrome()会从环境变量中寻找浏览器驱动
# 打开一个空白的 data; 页面
driver = webdriver.Chrome()
# 打开网址  get()方法中需要传入要打开页面的URL
driver.get("https://www.baidu.com/")
# 强等10s 秒 看一下效果
time.sleep(10)
# 关闭driver 断开操作，回收资源 浏览器关掉、进程断开
driver.quit()