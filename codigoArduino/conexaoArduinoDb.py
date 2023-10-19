import serial
import mysql.connector
from datetime import date

con = mysql.connector.connect(host='localhost',database='monitordados',user='root',password='root')

comport = serial.Serial('COM7', 9600)
print('Serial Iniciada...\n')
print("(28.65, 68.99)")

if con.is_connected():
  db_info = con.get_server_info()
  print("Conectado ao servidor MySQL versão ",db_info)

  while True:
    cursor = con.cursor()
    '''print(str(comport.readline()))'''
    serialValue = str(comport.readline())
    characters = "'b"
    for x in range(len(characters)):
      serialValue = serialValue.replace(characters[x], "")
    data_sinais = serialValue.split("|")
    aux = data_sinais[1]
    aux = aux.replace("\\", "")
    aux = aux.replace("n", "")
    aux = aux.replace("r", "")

    aux = aux.split(":")

    sql = ("insert into dados(momento, tipo, valor) values ( now() , '" + aux[0] + "' ,'"  + aux[1] + "')")

    cursor.execute(sql)
    cursor.close()
    con.commit()

  
