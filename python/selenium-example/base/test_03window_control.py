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



    def test_open_window(self):
        '''
        打开网页
        '''
        self.driver.get("https://news.baidu.com/")
        time.sleep(2)

    def test_refresh_window(self):
        '''
        刷新页面
        '''
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 刷新页面
        self.driver.refresh()
        # 强制等待
        time.sleep(2)

    def test_back(self):
        '''
        返回上一页面
        '''
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
    def test_forward(self):
        '''
        返回上一页面
        '''
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

    def test_window(self):
        '''
        操作页面最大化和最小化
        '''
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

    def test_title(self):
        '''
        获取页面的标题
        '''
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 获取页面的标题
        print(self.driver.title)

    def test_page(self):
        '''
        获取页面的所有内容
        '''
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 获取页面的所有内容
        print(self.driver.page_source)


    def test_url(self):
        '''
        获取页面的所有内容
        '''
        # 打开页面
        self.driver.get("https://news.baidu.com/")
        # 强制等待
        time.sleep(2)
        # 获取页面的URL
        print(self.driver.current_url)