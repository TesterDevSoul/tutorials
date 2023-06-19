import yagmail

# smtp.qq.com，使用SSL，端口号465或587
conn = yagmail.SMTP(
        user="1063023078@qq.com",
        password="tnlqdvyymaplbfda",
        host="smtp.qq.com",
        port=587
        )
content = "内容填充"
body = f"模版 {content}"

conn.send("1598544315@qq.com", "主题", body, "one.png")

