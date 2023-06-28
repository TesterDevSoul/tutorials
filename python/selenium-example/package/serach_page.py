import this

from selenium.webdriver.common.by import By

from package.base_driver import BaseDriver
from package.result_page import ResultPackage


class SearchPackage(BaseDriver):


    def goto_result(self):
        # //*[@aria-label="搜索"]
        self.find(By.XPATH, '//*[@aria-label="搜索"]',1).click()


        search_ele = self.find(By.CLASS_NAME, "search-link")
        # 高级搜索页面下第一个标题的文本获取
        search_text = search_ele.text
        # 高级搜索页面下第一个标题的标签文本获取
        cate_eles = self.finds(By.XPATH,'//*[@class="search-results"]//div[@role="listitem"][1]//*[@class="category-name"]')
        # 列表推导式
        cate_texts = [cate_ele.text for cate_ele in cate_eles]
        # 列表拼接
        cate_text = " / ".join(cate_texts)
        text = f"{search_text} - {cate_text} - 测试人社区"
        search_ele.click()
        self.wait_element((By.XPATH, '//*[@class="fancy-title"]'))
        return ResultPackage(self.driver),text
