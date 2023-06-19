import email
import imaplib


# 获取qq邮箱的内容
# 使用 IMAP4_SSL 连接到邮件服务器：
# 在这里，将 'imap.example.com' 替换为你的邮件服务器的实际主机名或 IP 地址。如果需要，可以提供端口号作为第二个参数。
conn = imaplib.IMAP4_SSL(host="imap.qq.com", port = 993)
# 使用登录凭据登录到邮箱：
conn.login("1063023078@qq.com","tnlqdvyymaplbfda")
print(conn.list())


# INBOX 和 unseen 都是 IMAP 协议定义的关键字，
# Python 会将它们转译为 IMAP 的语法，并发送给服务器，
# 而服务器则会把 30 天以内未读邮件的 ID 以列表形式返回，
# 并把 ID 以字节方式存放在 data 列表的第一个元素中，

# 默认为INBOX
# 使用了 select() 函数，指定要读取的文件夹为收件箱“INBOX”

# 选择要读取的邮箱文件夹：
conn.select("INBOX")
# 使用搜索条件搜索邮件：
# 'ALL'（所有邮件）、'UNSEEN'（未读邮件）
# search() 函数的“unseen”参数，来取得 30 天内未读的邮件
# 搜索邮件，ALL为全部，可以按照发件人使用FROM过滤，也可以使用日期过滤
status, data = conn.search(None, 'ALL')
# [b'30 31 36 37 43 47 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92']
print(data)
# 在这里处理邮件内容
# 使用邮件的唯一标识符（UID）来获取邮件内容：
for uid in data[0].decode().split(" "):
    # 取回每一封未读邮件的内容
    # data = [b'1 2 3 4 5']
    status, maildata = conn.fetch(str(uid), '(RFC822)')
    # 对每一封邮件的内容进行解析
    msg = email.message_from_string(maildata[0][1].decode('utf-8'))
    # 取得标题
    subject_tmp = msg.get('subject')
    # 为标题解码
    sj_decode = email.header.decode_header(subject_tmp)[0][0]
    # 打印每一封标题
    subject = sj_decode.decode('utf-8', 'ignore')
    # 获取发件人
    sender = email.utils.parseaddr(msg['From'])[1]
    # 在这里处理邮件内容
    print('主题:', subject)
    print('发件人:', sender)
    # 获取正文
    if msg.is_multipart():
        for part in msg.get_payload():
            if part.get_content_type() == 'text/plain':
                body = part.get_payload(decode=True).decode('utf-8', 'ignore')
                utf8_str = body.encode('utf-8').decode('unicode_escape')
                print('正文:', utf8_str)

                break
    else:
        body = msg.get_payload(decode=True).decode('utf-8', 'ignore')
        utf8_str = body.encode('utf-8').decode('unicode_escape')

        print('正文:', utf8_str)


    print('--------------------------------------')






    # 将邮件标记为已读
    # conn.store(mailid, '+FLAGS','\\seen')
# 关闭与邮件服务器的连接
conn.logout()


