#include <dht.h>

#include <SPI.h> //INCLUSÃO DE BIBLIOTECA
#include <MFRC522.h> //INCLUSÃO DE BIBLIOTECA

#define SS_PIN 10 //PINO SDA
#define RST_PIN 9 //PINO DE RESET

MFRC522 rfid(SS_PIN, RST_PIN); //PASSAGEM DE PARÂMETROS REFERENTE AOS PINOS


int ldr = A5;
int pinoDht = A4;
int sensorOptico = 7;

long time = 0;


dht controleDht;

void setup() {
  Serial.begin(9600);

  pinMode(ldr, INPUT);
  pinMode(sensorOptico, INPUT);
  SPI.begin(); 
  rfid.PCD_Init(); 

  time = 0;
}

void loop() {
  long aux = millis();
  if (aux - time >= 15000) {
    time = millis();
    Serial.println("|LUMINOSIDADE="+String(analogRead(ldr)));

    controleDht.read11(pinoDht);

    Serial.println("|UMIDADE="+String(controleDht.humidity)); 
    
    Serial.println("|TEMPERATURA="+String(controleDht.temperature)); 
  }

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

}
