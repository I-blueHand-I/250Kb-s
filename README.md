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
- Driver 4988 datasheet : https://www.pololu.com/file/0J450/a4988_DMOS_microstepping_driver_with_translator.pdf
- Arduino nano datasheet : http://ww1.microchip.com/downloads/en/DeviceDoc/ATmega48A-PA-88A-PA-168A-PA-328-P-DS-DS40002061A.pdf

This is the schematic view of the electronic circuit built on fritzing and the photo of the circuit.
![circuit](https://user-images.githubusercontent.com/91726252/142759029-00d4baa5-e7c2-4acb-8c34-1a92d7e3aacc.png)
![001](https://user-images.githubusercontent.com/91726252/142759013-2fd5b77a-6185-4cd4-bb0d-6c4d200bb480.png)

**Software**

The biggest issue for this project was to make processing and arduino communicate without librairies like firmata. 
You'll need to download if it is not, the processing library **Serial**. This librairy will link processing's printed values to arduino canal.
Make sure arduino and processing are on he same **baud** here is **250.000bytes/s**. You will find a code in the repo to link processing and arduino. 
To moove your pallet in the pong game, you will use a joystick. This joystick is used to play pong and to turn stepper motor. 
In repository you will find arduino and processing files. 

This is the arduino code (you'll also find it the repo).
You'll need for arduino the Accelstepper librairy.
AccelStepper : https://github.com/waspinator/AccelStepper
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
For a processing and pong step you can find a pong game already done. I recommand to do it yourself (inspired by models) to link correctly values to arduino.
The pong gameplay can be improoved and bugs & glitch could be corrected. 

This are pictures of how the machine draws the games and scans of drawings.

![006](https://user-images.githubusercontent.com/91726252/142759999-24afa619-531a-4cbe-b8ee-119bf3f77768.png)
![montage4](https://user-images.githubusercontent.com/91726252/142759672-7a6455ca-e54d-433d-a2fa-f7cd7d02fda0.png)

A short video : 
https://user-images.githubusercontent.com/91726252/142759974-290a199b-e3c5-4385-8608-56e26b934acb.mp4

The music in the game is Pong 808 from Hugz. You can find it on spotify : https://open.spotify.com/track/0TRtWhqcXoWJEkF7JQUwxP

# Exhibition

This project has been exposed at the Elia Galery in Paris on the 5 and 6 of june 2021.
More than 150 games were played. 150 drawings were drawn and each are different. 

![montage](https://user-images.githubusercontent.com/91726252/142758852-cce523d6-ded7-42f9-a174-6e011c8072bd.png)
![montage3](https://user-images.githubusercontent.com/91726252/142758946-f8931804-1124-4810-a128-f5544b8be6af.png)
![montage2](https://user-images.githubusercontent.com/91726252/142758895-6b32231d-7875-4817-8c37-f86cd06c7a4a.png)

