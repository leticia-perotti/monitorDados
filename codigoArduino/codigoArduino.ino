#include <dht.h>

#include <SPI.h> //INCLUSÃO DE BIBLIOTECA
#include <MFRC522.h> //INCLUSÃO DE BIBLIOTECA

#define SS_PIN 10 //PINO SDA
#define RST_PIN 9 //PINO DE RESET

MFRC522 rfid(SS_PIN, RST_PIN); //PASSAGEM DE PARÂMETROS REFERENTE AOS PINOS


int ldr = A5;
int pinoDht = A4;
int sensorOptico = 7;


dht controleDht;

void setup() {
  Serial.begin(9600);

  pinMode(ldr, INPUT);
  pinMode(sensorOptico, INPUT);
 // pinMode(pinoDht, INPUT)

 SPI.begin(); //INICIALIZA O BARRAMENTO SPI
  rfid.PCD_Init(); //INICIALIZA MFRC522
}

void loop() {

  delay(5000);

  Serial.println("|LUMINOSIDADE="+String(analogRead(ldr)));

  controleDht.read11(pinoDht);

  Serial.println("|UMIDADE="+String(controleDht.humidity)); 
  
  Serial.println("|TEMPERATURA="+String(controleDht.temperature)); 

  if (!rfid.PICC_IsNewCardPresent() || !rfid.PICC_ReadCardSerial())
    return;
 
  String strID = ""; 
  for (byte i = 0; i < 4; i++) {
    strID +=
    (rfid.uid.uidByte[i] < 0x10 ? "0" : "") +
    String(rfid.uid.uidByte[i], HEX) +
    (i!=3 ? ":" : "");
  }
  strID.toUpperCase();
 
  Serial.println("|CARTAOACESSO="+strID);
 
  rfid.PICC_HaltA();
  rfid.PCD_StopCrypto1();
/*
  if (!rfid.PICC_IsNewCardPresent() || !rfid.PICC_ReadCardSerial()) //VERIFICA SE O CARTÃO PRESENTE NO LEITOR É DIFERENTE DO ÚLTIMO CARTÃO LIDO. CASO NÃO SEJA, FAZ
    return; //RETORNA PARA LER NOVAMENTE
 
  /***INICIO BLOCO DE CÓDIGO RESPONSÁVEL POR GERAR A TAG RFID LIDA***/
  /*String strID = ""; 
  for (byte i = 0; i < 4; i++) {
    strID +=
    (rfid.uid.uidByte[i] < 0x10 ? "0" : "") +
    String(rfid.uid.uidByte[i], HEX) +
    (i!=3 ? ":" : "");
  }
  strID.toUpperCase();
/***FIM DO BLOCO DE CÓDIGO RESPONSÁVEL POR GERAR A TAG RFID LIDA***/
 
  //O ENDEREÇO "27:41:AA:AB" DEVERÁ SER ALTERADO PARA O ENDEREÇO DA SUA TAG RFID QUE CAPTUROU ANTERIORMENTE
 /* if (strID.indexOf("7A:C7:8E:1A") >= 0) { //SE O ENDEREÇO DA TAG LIDA FOR IGUAL AO ENDEREÇO INFORMADO, FAZ
   Serial.println("Autorizado!!"); //LIGA O LED VERDE
  }else{ //SENÃO, FAZ (CASO A TAG LIDA NÃO SEJÁ VÁLIDA)
    Serial.println("Não autorizado!!"); //LIGA O LED VERMELHO
  }
 
  rfid.PICC_HaltA(); //PARADA DA LEITURA DO CARTÃO
  rfid.PCD_StopCrypto1(); //PARADA DA CRIPTOGRAFIA NO PCD
*/


    /*Serial.println(digitalRead(sensorOptico));
    delay(3000);*/
    //Serial.println(analogRead(ldr));
    /*
    controleDht.read11(pinoDht); //LÊ AS INFORMAÇÕES DO SENSOR
    Serial.print("Umidade: "); //IMPRIME O TEXTO NA SERIAL
    Serial.print(controleDht.humidity); //IMPRIME NA SERIAL O VALOR DE UMIDADE MEDIDO
    Serial.print("%"); //ESCREVE O TEXTO EM SEGUIDA
    Serial.print(" / Temperatura: "); //IMPRIME O TEXTO NA SERIAL
    Serial.print(controleDht.temperature, 0); //IMPRIME NA SERIAL O VALOR DE UMIDADE MEDIDO E REMOVE A PARTE DECIMAL
    Serial.println("*C"); //IMPRIME O TEXTO NA SERIAL
    delay(2000);*/
}
