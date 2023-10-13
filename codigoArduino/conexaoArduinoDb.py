import serial

comport = serial.Serial('COM7', 9600)
print('Serial Iniciada...\n')
print("(28.65, 68.99)")
while True:
  serialValue = str(comport.readline())
  characters = "b'"
  for x in range(len(characters)):
    serialValue = serialValue.replace(characters[x], "")
  data_sinais = serialValue.split(":")
  print(data_sinais)