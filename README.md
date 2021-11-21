# 250Kb-s


<img width="689" alt="title" src="https://user-images.githubusercontent.com/91726252/142757149-a1dc565e-19ee-48b4-bc63-d593849a5484.png">



250Kb/s is a french program developped in 2021. 
Two players are playing to Pong. While they are playing, values of their analog shifting are tranfered to an arduino.
The controller controls two stepper motors. One stepper motor folows player 1's mouvement, the second one folows player's 2 mouvement.
A graphic tool is attached to a platform which is moving thanks to the motors. 
On a paper, a drawing of the pong game apppears.

![dessin_001](https://user-images.githubusercontent.com/91726252/142757424-12692d34-b661-4564-b0c0-71b7e66ab1d1.png)
Drawing of a pong game in 11 points with brush and ink

## Installation
- Install processing : https://processing.org/download
- Install arduino : https://www.arduino.cc/en/software
- Clone the repository

## How it works

**Hardware**

Not so many electronic componants are necessary to build 250kb/s hardware. It will need a : arduino nano, two drivers 4988 for the stepper motors, two joysticks, and buttons.
This is the schematic view of the electronic circuit built on fritzing and the photo of the circuit.
![circuit](https://user-images.githubusercontent.com/91726252/142757919-56129fbd-1eb7-4bc9-a2fb-76985a4ad843.png)
![001](https://user-images.githubusercontent.com/91726252/142757893-60456501-0df0-4627-b7e1-8ddce378633e.png)

**Software**

The biggest issue for this project was to make processing and arduino communicate without librairies like firmata. 
You'll need to download if it is not, the processing library **Serial**. This librairy will link processing's printed values to arduino canal.
Make sure arduino and processing are on he same **baud** here is **250.000bytes/s**. You will find a code in the repo to link processing and arduino. 
To moove your pallet in the pong game, you will use a joystick. This joystick is use to play pong and to turn stepper motor. 
In repository you will find arduino and processing files. 

This is the arduino code (you'll also find it the repo).
```
//Include the AccelStepper library:
#include <MultiStepper.h>
#include <AccelStepper.h>
//Define stepper motor connections and motor interface type. Motor interface type must be set to 1 when using a driver:
//motor 1
#define dirPin 2
#define stepPin 3
#define motorInterfaceType 1
//motor 2
#define dirPin1 7
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
  pinMode ( analogPin, INPUT);
  pinMode ( analogPin1, INPUT);
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

  Val1 = int(map(Value1, 0, 1023, -1, 1));
  Val2 = int(map(Value2, -100, 1023, -1, 1));
    
  switch (Val1) {
    case 1 :
      stepper1.setSpeed(-600);
      stepper1.run();
    case -1 :
      stepper1.setSpeed(600);
      stepper1.run();
  }
  switch (Val2) {
    case 1 :
      stepper.setSpeed(600);
      stepper.run();
    case -1 :
      stepper.setSpeed(-600);
      stepper.run();
  }
  Serial.print(Val1); // DEC means "send the number in base-10"
  Serial.print(",");
  Serial.print(Val2);
  Serial.println(); // the "ln" will tack on a special character at the end of the transmission
  delay(1); // Don't use a delay smaller than 20 ms or so.
}
```
You'll need for arduino the Accelstepper librairy.
- AccelStepper : https://github.com/waspinator/AccelStepper
