import time
from pathlib import Path
from typing import List

import yaml
from selenium import webdriver
from selenium.common import NoSuchElementException
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

from utils.log_util import logger


class BaseDriver:
    def __init__(self, driver: WebDriver = None):
        # 处理driver被反复初始化
        if driver is None:
            logger.info("第一次初始化driver")
            self.driver = webdriver.Chrome()
            self.driver.implicitly_wait(5)
            self.driver.get("https://ceshiren.com/")
            self.driver.maximize_window()
        else:
            # 如果不是 none 就用传递进来的driver
            logger.info("用传递进来的driver")
            self.driver = driver

    def find(self, by, value=None):
        ele = self.driver.find_element(by, value)
        # 截图
        self.png()
        return ele

    def find(self, by, value=None, index=0):
        ele = self.finds(by,value)[index]
        # 截图
        self.png()
        return ele

    def finds(self, by, value=None) -> List:
        # 判断是否是一个元组,如果是解元组传递
        if isinstance(by, tuple):
            # 等同于 self.driver.find_elements('id',"username")
            return self.driver.find_elements(*by)
        else:
            return self.driver.find_elements(by, value)

    def click(self, by, value):
        self.find(by, value).click()


    def send(self, by, value, text: str):
        ele = self.find(by, value)
        ele.clear()
        ele.send_keys(text)

    def wait_element(self, locator: tuple, time=10):
        # 封装显式等待
        element = WebDriverWait(self.driver, time).until(expected_conditions.visibility_of_element_located(locator))
        return element
    def wait_element_click(self, locator: tuple, time=10):
        # 封装显式等待
        element = WebDriverWait(self.driver, time). \
            until(expected_conditions.element_to_be_clickable(locator))
        return element
    def close_browser(self):
        self.driver.quit()

    def png(self):
        # 获取当前时间戳
        now = time.time()
        # png Path路径拼接 os
        png_name = f"{now}.png"
        png_path = Path("png") / png_name
        self.driver.save_screenshot(png_path)

