import time

from selenium import webdriver
from selenium.webdriver.common.by import By



class TestSearchHogwarts:
    # 每一个方法之前执行
    def setup_method(self, method):
        # 创建一个 Chromdriver 的实例。Chrome()会从环境变量中寻找浏览器驱动
        # 打开一个空白的 data; 页面
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)
        self.vars = {}

    # 每一个方法之后执行
    def teardown_method(self, method):
        # 浏览器退出 无论断言成功还是失败
        self.driver.quit()

    # 该方法的作用是等待新的窗口打开，并返回新窗口的句柄
    # 接受一个可选参数timeout，默认值为2。
    def wait_for_window(self, timeout=2):
        # 代码会暂停执行一段由timeout值指定的时间
        # timeout值除以1000并四舍五入，得到以秒为单位的睡眠持续时间
        time.sleep(round(timeout / 1000))
        # 获取当前所有的窗口句柄
        wh_now = self.driver.window_handles
        print(wh_now)
        # 获取之前保存的窗口句柄
        wh_then = self.vars["window_handles"]
        print(wh_then)

        # 如果当前窗口句柄的数量 大于 之前保存的窗口句柄数量，执行以下操作：
        if len(wh_now) > len(wh_then):
            # set(wh_now).difference(set(wh_then))：计算当前窗口句柄集合与之前窗口句柄集合的差集
            # 从差集中随机选择一个窗口句柄并返回
            return set(wh_now).difference(set(wh_then)).pop()

    # 测试用例主程序 测试步骤
    # 测试函数  test_开头
    def test_search(self):
        # 1. 打开百度页面
        # 打开网址  get()方法中需要传入要打开页面的URL
        self.driver.get("https://www.baidu.com/")
        # 设置窗口大小
        self.driver.set_window_size(1344, 815)
        # 2. 搜索框内输入：霍格沃兹ceshiren
        # <input id="kw" name="wd" class="s_ipt" value="" maxlength="255" autocomplete="off">
        self.driver.find_element(By.ID, "kw").send_keys("霍格沃兹ceshiren")
        # 3. 点击百度一下。
        # <input type="submit" id="su" value="百度一下" class="bg s_btn">
        self.driver.find_element(By.ID, "su").click()
        # 获取当前浏览器的所有窗口
        self.vars["window_handles"] = self.driver.window_handles
        # 4. 点击第一个搜索结果。c-container
        self.driver.find_element(By.LINK_TEXT, "霍格沃兹测试开发学社").click()
        print("点击完成")
        time.sleep(10)

        # 标题的断言
        self.vars["win434"] = self.wait_for_window(2000)
        # 浏览器窗口的切换
        self.driver.switch_to.window(self.vars["win434"])

        # 5. 获取新页面的标题，断言。
        assert self.driver.title == "霍格沃兹测试开发学社", f"打开页面标题错误，是：{self.driver.title}"
