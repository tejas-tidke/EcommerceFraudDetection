#!C:\Users\Megha\AppData\Local\Programs\Python\Python310\python
import mysql.connector as mycon

def connect() : 
    con=mycon.connect(host='localhost',user='root',password='crosspolo',database='ecom_frauddb')
    return con