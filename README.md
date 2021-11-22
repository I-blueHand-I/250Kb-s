# 250Kb-s

<img width="900" alt="title2" src="https://user-images.githubusercontent.com/91726252/142782329-2acd0404-e953-48d3-9b03-eb35a76f742f.png">

250Kb/s is a french program developped in 2021. 
Two players are playing to Pong. While they are playing, values of their analog shifting are tranfered to an arduino.
The controller controls two stepper motors. One stepper motor folows player 1's mouvement, the second one folows player's 2 mouvement.
A graphic tool is attached to a platform which is moving thanks to the motors. 
On a paper, a drawing of the pong game apppears.

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

You'll need for arduino the Accelstepper librairy.
AccelStepper : https://github.com/waspinator/AccelStepper

For the pong with processing you can find a pong game already done. I recommand to do it yourself (inspired by models) to link correctly values to arduino.
The pong gameplay can be improoved and bugs & glitch could be corrected. 

![005](https://user-images.githubusercontent.com/91726252/142760173-62a0a48b-d2f5-4c34-a54e-006e002a3fe6.png)
Picture of how the machine draws.

![dessin_001](https://user-images.githubusercontent.com/91726252/142757424-12692d34-b661-4564-b0c0-71b7e66ab1d1.png)
Drawing of a pong game in 11 points with brush and ink

A short video : 
https://user-images.githubusercontent.com/91726252/142759974-290a199b-e3c5-4385-8608-56e26b934acb.mp4

The music in the game is Pong 808 from Hugz. You can find it on spotify : https://open.spotify.com/track/0TRtWhqcXoWJEkF7JQUwxP

# Exhibition

This project has been exposed at the Elia Art Galery in Paris on the 5 and 6 of june 2021.
More than 150 games were played. 150 drawings were drawn and each are different. 

![montage](https://user-images.githubusercontent.com/91726252/142781797-6e3b89ab-7480-479d-b069-1ed79aae8382.png)
![montage3](https://user-images.githubusercontent.com/91726252/142781803-d69ad6e4-2818-435a-abb6-0da0ced6365a.png)
![montage2](https://user-images.githubusercontent.com/91726252/142758895-6b32231d-7875-4817-8c37-f86cd06c7a4a.png)

Shield: [![CC BY-SA 4.0][cc-by-sa-shield]][cc-by-sa]

This work is licensed under a
[Creative Commons Attribution-ShareAlike 4.0 International License][cc-by-sa].

[![CC BY-SA 4.0][cc-by-sa-image]][cc-by-sa]

[cc-by-sa]: http://creativecommons.org/licenses/by-sa/4.0/
[cc-by-sa-image]: https://licensebuttons.net/l/by-sa/4.0/88x31.png
[cc-by-sa-shield]: https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg

