
from selenium.webdriver.common.by import By

from package.base_driver import BaseDriver

class ResultPackage(BaseDriver):
    def get_title(self):
        # --------------- 6. 获取页面标题 断言 ---------------
        return self.driver.title


