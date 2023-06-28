import time

from selenium import webdriver
from selenium.webdriver.common.by import By

# 元素定位
class TestElement:
    # 每一个方法之前执行
    def setup_method(self):
        # 创建一个 Chromdriver 的实例。Chrome()会从环境变量中寻找浏览器驱动
        # 打开一个空白的 data; 页面
        self.driver = webdriver.Chrome()
        # 设置全局隐式等待
        self.driver.implicitly_wait(10)

        # 设置窗口大小
        self.driver.set_window_size(1344, 815)

    # 每一个方法之后执行
    def teardown_method(self):
        time.sleep(6)
        # 浏览器退出 无论断言成功还是失败
        self.driver.quit()
    def test_by_id(self):
        # 1. 打开百度页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.baidu.com/")
        # <input id="kw" name="wd" class="s_ipt" value="" maxlength="255" autocomplete="off">
        self.driver.find_element(By.ID, "kw").send_keys("霍格沃兹ceshiren")
        time.sleep(3)
        # 1. 打开必应搜索页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.bing.com/?mkt=zh-CN")
        # <input id="sb_form_q" class="sb_form_q" name="q" type="search" maxlength="1000" autocomplete="off" aria-labelledby="sb_form_c" autofocus="" aria-controls="sw_as" aria-autocomplete="both" aria-owns="sw_as" aria-activedescendant="sa_5005">
        self.driver.find_element(By.ID, "sb_form_q").send_keys("霍格沃兹ceshiren")


    def test_by_name(self):
        # 1. 打开百度页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.baidu.com/")
        # <input id="kw" name="wd" class="s_ipt" value="" maxlength="255" autocomplete="off">
        self.driver.find_element(By.NAME, "wd").send_keys("霍格沃兹ceshiren")
        time.sleep(3)
        # 1. 打开必应搜索页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.bing.com/?mkt=zh-CN")
        # <input id="sb_form_q" class="sb_form_q" name="q" type="search"
        self.driver.find_element(By.NAME, "q").send_keys("霍格沃兹ceshiren")
    def test_by_class(self):
        # 1. 打开百度页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.baidu.com/")
        # <input id="kw" name="wd" class="s_ipt" value="" maxlength="255" autocomplete="off">
        self.driver.find_element(By.CLASS_NAME, "s_ipt").send_keys("霍格沃兹ceshiren")
        time.sleep(3)
        # 1. 打开搜狗搜索页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.bing.com/?mkt=zh-CN")
        # <input type="search" placeholder="" name="keyword" id="keyword"
        self.driver.find_element(By.CLASS_NAME, "sb_form_q").send_keys("霍格沃兹ceshiren")

    def test_by_link_text(self):
        # 1. 打开百度页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        # self.driver.get("https://www.baidu.com/")
        # <a href="https://www.hao123.com?src=from_pc" target="_blank" class="mnav c-font-normal c-color-t">hao123</a>
        # self.driver.find_element(By.LINK_TEXT, "hao123").click()
        # time.sleep(3)
        # 1. 打开必应搜索页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.bing.com/?mkt=zh-CN")
        # <a href="/images?FORM=Z9LH" data-h="ID=HpApp,9107.1" class="" target="" rel="noopener">地图</a>
        self.driver.find_element(By.LINK_TEXT, "地图").click()

    def test_by_part_link_text(self):
        # 1. 打开百度页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        # self.driver.get("https://www.baidu.com/")
        # <a href="https://www.hao123.com?src=from_pc" target="_blank" class="mnav c-font-normal c-color-t">hao123</a>
        # self.driver.find_element(By.PARTIAL_LINK_TEXT, "123").click()
        # time.sleep(3)
        # 1. 打开必应搜索页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.bing.com/?mkt=zh-CN")
        # <a href="/images?FORM=Z9LH" data-h="ID=HpApp,9107.1" class="" target="" rel="noopener">地图</a>
        self.driver.find_element(By.PARTIAL_LINK_TEXT, "地").click()
