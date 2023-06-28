import time

from selenium import webdriver


class TestWindowControl:

    def setup(self):
        # 打开浏览器
        self.driver = webdriver.Chrome()
        # 设置全局隐式等待
        self.driver.implicitly_wait(3)

    def teardown(self):
        self.driver.quit()

    '''
    打开网页
    '''
    def test_open_window(self):
        self.driver.get("https://news.baidu.com/")
        time.sleep(2)

    '''
    刷新页面
    '''
    def test_refresh_window(self):
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 刷新页面
        self.driver.refresh()
        # 强制等待
        time.sleep(2)

    '''
    页面回退
    '''
    def test_back(self):
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        self.driver.get("https://www.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 返回上一页面 news
        self.driver.back()
        # 强制等待
        time.sleep(2)

    '''
    页面前进
    '''
    def test_forward(self):

        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        self.driver.get("https://www.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 前进到下一个页面   百度
        self.driver.forward()
        # 强制等待
        time.sleep(2)

    '''
    最大化窗口、最小化窗口
    '''
    def test_window(self):
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 最大化窗口
        self.driver.maximize_window()
        # 强制等待
        time.sleep(2)
        # 最小化窗口
        self.driver.minimize_window()
        # 强制等待
        time.sleep(2)

    '''
    设置浏览器窗口具体大小
    '''

    def test_set_window(self):
        self.driver.get("https://www.baidu.com/")
        # width 宽度, height 高度
        self.driver.set_window_size(10, 20)
        time.sleep(10)
    '''
    获取页面的标题
    '''
    def test_title(self):
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 获取页面的标题
        print(self.driver.title)

    '''
    获取页面的所有内容
    '''
    def test_page(self):
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 获取页面的所有内容
        print(self.driver.page_source)

    '''
    获取页面的URL
    '''
    def test_url(self):
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 获取页面的URL
        print(self.driver.current_url)