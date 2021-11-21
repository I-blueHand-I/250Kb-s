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
This is the schematic view of the electronic circuit built on fritzing.
![circuit](https://user-images.githubusercontent.com/91726252/142757919-56129fbd-1eb7-4bc9-a2fb-76985a4ad843.png)
![001](https://user-images.githubusercontent.com/91726252/142757893-60456501-0df0-4627-b7e1-8ddce378633e.png)

**Software**

The biggest issue for this project was to make processing and arduino communicate without librairies like firmata. 
You'll need to download if it is not, the processing library **Serial**. This librairy will link processing's printed values to arduino canal.
Make sure arduino and processing are on he same **baud** here is **250.000bytes/s**. You will find a code in the repo to link processing and arduino. 
To moove your pallet in the pong game, you will use a joystick. This joystick is use to play pong and to turn stepper motor. 
In repository you will find arduino and processing files. 


