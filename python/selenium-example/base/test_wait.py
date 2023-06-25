from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait


class TestWait:

    def setup(self):
        self.driver = webdriver.Chrome()
        # 设置全局隐式等待
        self.driver.implicitly_wait(3)

    def teardown(self):
        self.driver.quit()

    def test_wait_until(self):
        '''
        显式等待
        '''
        self.driver.get("https://vip.ceshiren.com/#/ui_study/locate")
        # # 对消息提示按钮进行可点击的显式等待
        # WebDriverWait(self.driver, 10).until(
        #     expected_conditions.element_to_be_clickable((By.CSS_SELECTOR, '#success_btn'))
        # )
        # # 定位 消息提示按钮
        # self.driver.find_element(By.ID, "success_btn").click()
        # 对消息提示按钮进行可点击的显式等待
        ele = WebDriverWait(self.driver, 10).until(
            expected_conditions.element_to_be_clickable((By.CSS_SELECTOR, '#success_btn'))
        )
        # 定位 消息提示按钮
        ele.click()
