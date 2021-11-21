import processing.serial.*;
import ddf.minim.*;

//communication avec arduino 
int end = 10;
String receivedString;
Serial myPort; 
///////////////
//CLASS MUSIQUE PONG///
Minim minim;
AudioPlayer PL;
////////////////////////
//CLASS SCORE
score[] Score = new score[2];
//CLASS JOUEURS
player[] players =  new player[2];
//CLASS BALL
ball Ball;
//variable d'import de la police///
PFont font;
//bolleenes d'activation du jeu et de menu pause
boolean play = false;
boolean go = false;

void setup() {

  size(1000, 700);
  background(0);
  //Importation de la police
  font = loadFont("RobotRadicals-50.vlw");
  textFont(font);

  //serial communication avec arduino
  myPort= new Serial(this, Serial.list()[7], 250000);
  //println(Serial.list());
  myPort.clear(); 
  receivedString = myPort.readStringUntil(end); 
  receivedString = null;
  /////////////////////////

  //importation de la musique
  minim = new Minim(this);
  PL = minim.loadFile("PONG 808.mp3");
  PL.play();
  PL.loop();
  //definition des scores///
  for (int i = 0; i<2; i++) {
    Score[i] = new score(20, 40);
  }
  //definition de la balle
  Ball = new ball(width/2, height/2, 4, -2);
  //definition des joueurs
  for (int i=0; i<2; i++) {
    players[i] = new player(height/2-50);
  }

  //deifnition de la position des joueurs 1 et 2 
  players[0].xpos = 10;
  players[1].xpos = 970;
  //definition de la position des scores
  Score[0].xpos = width/2-120;
  Score[1].xpos = width/2+100;
  //importation de l'écran d'accueuil
  Score[0].intro(font);
  //timer : intro de la fonction millis qui commence au lancement du programme
  Score[0].begin = millis();
}

void draw() {  
  
  keyPressed();
  //println(play);
  
  //COMMUNICATION AVEC ARDUINO
  //deplacement avec les valeurs analogiques des joystick
  while (myPort.available () > 0) {
    receivedString = myPort.readStringUntil(end);
  }
  if (receivedString != null) {
    String[] a = split(receivedString, ','); 
    int val1 = Integer.parseInt(a[0].trim());
    int val2 = Integer.parseInt(a[1].trim());
    println(val1);
    ///deplacement des joueurs
    switch(val1) {
    case -1 : 
      players[0].moveDown();
      break;
    case 1 :
      players[0].moveUp();
      break;
    }
    switch(val2) {
    case 1 : 
      players[1].moveDown();
      break;
    case -1 :
      players[1].moveUp();
      break;
    }
  }
  /////////////////////////////
  //FIN DE COMMUNICATION AVEC ARDUINO 
  if (play == true) {
    //blur effect
    fill(0, 100);
    rect(0, 0, width, height);
    //rotation des fonctions balle/joueurs/scores
    Ball.display();    
    Ball.contrainte();    
    Ball.bounce();   
    Ball.win();   
    Score[0].counter(font);
    //trigger du mouvement de la balle
    if ( go == true) {     
      Ball.move();
    }
    for (int i = 0; i<2; i++) {
      Score[i].display(font);
      Score[i].victory();
    }
    for (int i = 0; i<2; i++) {
      players[i].moveDown();
      players[i].moveUp();
      players[i].display();
      players[i].contrainte();
    }
    ////////////////////////////////////////
  }
}

void keyPressed() {
  switch(key) {
  case ' ' : 
    play =true;
    break;
  case 'h' : 
    play = false;
    break;
  }
}
