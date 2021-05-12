#include <ArduinoJson.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <DHT.h>
DHT dht(D5,DHT11);
float i;
float nhietdo;
float doam;
String host = "http://192.168.1.3:8080";//sửa host!
void setup () {
  pinMode(D5,INPUT);
  pinMode(D1, OUTPUT);
  pinMode(D2, OUTPUT); 
  pinMode(D6, OUTPUT);
  dht.begin();
  Serial.begin(115200);
  WiFi.begin("giangdeptrai", "giang220798"); //sửa wifi!
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting...");
  } 
} 
void loop() {
  if (WiFi.status() == WL_CONNECTED) { 
    HTTPClient http; 
//---------------------------------------------------- led1      
    http.begin(host + "/glucose/api/devices/1");  
    int httpCode = http.GET();                                  
 String payload;
    if (httpCode > 0) {  
     payload = http.getString();   
      //Serial.println(payload);      
    }
    DynamicJsonDocument doc(1024);
deserializeJson(doc, payload);
bool bitValue = doc["bitValue"];
//Serial.println(bitValue);
  digitalWrite(D1, bitValue);
//-------------------------------------------------------- pan2
http.begin(host + "/glucose/api/devices/2");  
    httpCode = http.GET();                                  
    
    if (httpCode > 0) {  
     payload = http.getString();   
     // Serial.println(payload);      
    }
   // DynamicJsonDocument doc(1024);
deserializeJson(doc, payload);
 bitValue = doc["bitValue"];
 Serial.println(bitValue);

 http.begin(host + "/glucose/api/devices/5");  
    int httpCode5 = http.GET();                                  
 String payload5;
    if (httpCode5 > 0) {  
     payload5 = http.getString();   
      //Serial.println(payload);      
    }
    DynamicJsonDocument doc5(1024);
deserializeJson(doc5, payload5);
bool mode_on5 = doc5["bitValue"];
//Serial.println(mode_on5);
//Serial.println("----");

if(mode_on5==false){
  //Serial.println(bitValue);
  digitalWrite(D2, bitValue);
  digitalWrite(D6, !bitValue);
}
//-------------------------------------------- tự động
else{
  float thres = doc5["decimalValue"] ;
  float thresH = thres + 0.2;
  float thresL = thres - 0.2;
  float t = dht.readTemperature();
  if(t<thresL){
    digitalWrite(D2, LOW);
    digitalWrite(D6 , HIGH);
  }
  if(t>thresH){
    digitalWrite(D2, HIGH);
    digitalWrite(D6, LOW);
  }
  }
 


 

//------------------------------------------------  nhiet do
 DynamicJsonDocument gui(1024);
 gui["cid"]= 3;
 gui["deviceName"] = "nhietdo3";
 gui["deviceType"] = true;
 gui["dataType"] = true;
 gui["bitValue"] = true;
 gui["decimalValue"] = dht.readTemperature();
 
  String s;
  serializeJson(gui, s);
http.begin(host + "/glucose/api/devices");
   http.addHeader("Content-Type", "application/json");            
   int httpResponseCode = http.PUT(s); 
   if(httpResponseCode>0){
    String response = http.getString();    
    //Serial.println(httpResponseCode);
    Serial.println(response);          
   }else{
    Serial.print("Error on sending PUT Request: ");
    Serial.println(httpResponseCode);
   }
//-------------------------------------------- doam
DynamicJsonDocument gui2(1024);
 gui2["cid"]= 4;
 gui2["deviceName"] = "doam4";
 gui2["deviceType"] = true;
 gui2["dataType"] = true;
 gui2["bitValue"] = true;
 gui2["decimalValue"] = dht.readHumidity(); 
 String s2;
  serializeJson(gui2, s2);
http.begin(host + "/glucose/api/devices");
   http.addHeader("Content-Type", "application/json");            
   http.PUT(s2);    
    http.end();   //Close connection
  }
  delay(10);   
}

