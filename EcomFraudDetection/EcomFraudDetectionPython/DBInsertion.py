from DBConnect import *
import base64
  
def getLoginAttempts(userid="NA"):
    conn = connect()
    #integrated security 
    cursor = conn.cursor() 
    cursor.execute("select login_attempts from inputAttributes where userid='"+userid+"';")
    logatt=0
    for row in cursor: 
        logatt=row[0]
        print(str(logatt))
    return logatt 
def getLoginTime(userid="NA"):
    conn = connect()
    #integrated security 
    cursor = conn.cursor() 
    cursor.execute("select login_time from inputAttributes where userid='"+userid+"';")
    logatt=0
    for row in cursor: 
        logatt=row[0]
        print(str(logatt))
    return logatt 
def getIdkeyauth(userid="NA"):
    conn = connect()
    #integrated security 
    cursor = conn.cursor() 
    cursor.execute("select IdentityAuth from inputAttributes where userid='"+userid+"';")
    logatt=0
    for row in cursor: 
        logatt=row[0]
        print(str(logatt))
    return logatt 
def getAmt(userid="NA"):
    conn = connect()
    #integrated security 
    cursor = conn.cursor() 
    cursor.execute("select amt from inputAttributes where userid='"+userid+"';")
    logatt=0
    for row in cursor: 
        logatt=row[0]
        print(int(logatt))
    return logatt 

def convertToBase64(message='NA') :
    message_bytes = message.encode('ascii')
    base64_bytes = base64.b64encode(message_bytes)
    base64_message = base64_bytes.decode('ascii')
    print(base64_message)
    return base64_message

def convertFromBase64(base64_message='NA') :
    base64_bytes = base64_message.encode('ascii')
    message_bytes = base64.b64decode(base64_bytes)
    message = message_bytes.decode('ascii')
    print(message)
    return message