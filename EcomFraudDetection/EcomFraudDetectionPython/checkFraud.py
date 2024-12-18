#!C:\Users\Spider Projects\AppData\Local\Programs\Python\Python310\python
import base64 
import numpy as np
from DecisionTree import classification
import cgi, cgitb
import os
import DBConnect
import DBInsertion
import FunFactory
import urllib.request
# enable debugging
cgitb.enable()
# print content type
print("Content-type:text/html\r\n\r\n")
print("path="+os.getcwd()) 
 
form = cgi.FieldStorage()  
    
headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0'}
#print(jinja2.Environment().from_string(HTML).render(filedata=inFileData)) 
#w2d("E:\\python\\1.jpg",'haar','111')
#print(form.getvalue("fid"))

param=form.getvalue("param")
base64_bytes = param.encode('ascii')
message_bytes = base64.b64decode(base64_bytes)
userid = message_bytes.decode('ascii')
#uid,userShare,serverShare,username = message.split('|')
print(userid)

#x = np.random.randint(0, 10, size=(300, 10, 2))
#y = np.random.randint(0, 10, size=(300))

# Predict probability per label
#pred = model.predict_proba(np.random.randint(0, 10, size=(10, 2)))
login_attempts=DBInsertion.getLoginAttempts(userid)
login_time=DBInsertion.getLoginTime(userid)
editProfile=0
IdentityAuth=DBInsertion.getIdkeyauth(userid)
IdentityAuthTime=0	
IdentityRecovery=0	
product_range=0	
amt=DBInsertion.getAmt(userid)
pred =classification(userid)
# Get label with the most high probability
#pred = model.predict(np.random.randint(0, 10, size=(100, 2)))

#pred=model.predict([[0,login_attempts],[1,login_time],[2,editProfile],[3,IdentityAuth],[4,IdentityAuthTime],[5,IdentityRecovery],[6,product_range],[7,amt]])
print("prediction")
 

print("<html>")
print("<head>")
print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/FromPython?userid="+userid+"&r="+str(pred)+"&i="+str(IdentityAuth)+"'/>")
print("</head>")
print("</html>")
