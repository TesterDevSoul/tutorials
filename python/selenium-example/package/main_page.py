from selenium.webdriver.common.by import By

from package.base_driver import BaseDriver
from package.serach_page import SearchPackage


class MainPage(BaseDriver):
    def goto_search(self, keyword: str):
        self.wait_element((By.ID, "search-button"))
        # --------------- 2. 点击搜索按钮 ---------------
        self.click(By.ID, "search-button")

        # --------------- 3. 输入关键字：Pytest ---------------
        self.send(By.ID, "search-term", keyword)

        # --------------- 4. 点击进入高级搜索页面 ---------------
        self.wait_element((By.XPATH, '//*[@title="打开高级搜索"]'))
        self.click(By.XPATH, '//*[@title="打开高级搜索"]')
        # 搜索结果页面
        self.wait_element((By.CLASS_NAME, "search-link"))
        return SearchPackage(self.driver)
