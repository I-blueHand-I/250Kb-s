 /*Example sketch to control a stepper motor with A4988 stepper motor driver, AccelStepper library and Arduino: acceleration and deceleration. More info: https://www.makerguides.com */
// Include the AccelStepper library:
#include <MultiStepper.h>
#include <AccelStepper.h>
// Define stepper motor connections and motor interface type. Motor interface type must be set to 1 when using a driver:
#define dirPin 2
#define stepPin 3
#define motorInterfaceType 1


#define dirPin1 8
#define stepPin1 10
#define motorInterfaceType1 1

int analogPin = A0;
int analogPin1 = A1;

int Val1;
int Val2;

// Create a new instance of the AccelStepper class:
AccelStepper stepper = AccelStepper(motorInterfaceType, stepPin, dirPin);
AccelStepper stepper1 = AccelStepper(motorInterfaceType1, stepPin1, dirPin1);


void setup() {
  pinMode (analogPin, INPUT);
  pinMode (analogPin1, INPUT);
  Serial.begin(250000); //Begin Serial Communication with a baud rate of 250.000

  // Set the maximum speed and acceleration:
  stepper.setMaxSpeed(1000);
  stepper.setAcceleration(100);

  stepper1.setMaxSpeed(1000);
  stepper1.setAcceleration(100);
}

void loop() {
  //New variables are declared to store the readings of the respective pins
  int Value1 = analogRead(analogPin);
  int Value2 = analogRead(analogPin1);

  Val1 = int(map(Value1, -100, 970, -1, 1));
  Val2 = int(map(Value2, -200, 1010, -1, 1));

  
  switch (Val1) {
    case 1 :
      stepper.setSpeed(600);
      stepper.run();
    case -1 :
      stepper.setSpeed(-600);
      stepper.run();
  }
  switch (Val2) {
    case 1 :
      stepper1.setSpeed(600);
      stepper1.run();
    case -1 :
      stepper1.setSpeed(-600);
      stepper1.run();
  }

  Serial.print(Val1); // DEC means "send the number in base-10"
  Serial.print(",");
  Serial.print(Val2);
  Serial.println(); // the "ln" will tack on a special character at the end of the transmission
  delay(1); 
}
