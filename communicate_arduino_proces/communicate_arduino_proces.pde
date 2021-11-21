import processing.serial.*;

int end = 10;
String receivedString;
Serial myPort; 
void setup() {
  myPort= new Serial(this, Serial.list()[7], 250000);
  //println(Serial.list());
  myPort.clear(); 
  receivedString = myPort.readStringUntil(end); 
  receivedString = null;
}

void draw() {
  while (myPort.available () > 0) {
    receivedString = myPort.readStringUntil(end);
  }
  if (receivedString != null) {
    String[] a = split(receivedString, ','); 
    int val1 = Integer.parseInt(a[0].trim());
    int val2 = Integer.parseInt(a[1].trim());
    println(val1);
    println(val2);
  }
}
