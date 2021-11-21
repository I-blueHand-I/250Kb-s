import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 
import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class PONG_JOYSTICK extends PApplet {




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

public void setup() {

  
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

public void draw() {  
  
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

public void keyPressed() {
  switch(key) {
  case ' ' : 
    play =true;
    break;
  case 'h' : 
    play = false;
    break;
  }
}
class ball {

  float xpos, ypos, directionX, directionY;

  ball(float tempX, float tempY, float dirX, float dirY) {
    xpos = tempX;
    ypos = tempY; 
    directionX = dirX;
    directionY = dirY;
  }
  public void move() {
    xpos = xpos + directionX;
    ypos = ypos + directionY;
  }
  public void display() {  
    noStroke();
    fill(255);
    rect (xpos, ypos, 20, 20);
  }
  public void bounce() {
    //collision entre la balle des les joueurs
    //J1
    if ( xpos <= players[0].xpos + 20 &&
      ypos +20 >= players[0].ypos &&
      ypos <= players[0].ypos + 70) {
      directionX = -directionX;
      accel();
    }
    //J2
    if ( xpos + 20 >= players[1].xpos && 
      ypos <= players[1].ypos + 70 && 
      ypos + 20 >= players[1].ypos) {
      directionX = -directionX;
      accel();
    }
  }
  public void contrainte() {
    if (ypos >= 690) {
      directionY = -directionY;
    }
    if (ypos <= 0 ) {
      directionY = -directionY;
    }
  }
  public void accel() {
    directionX= directionX*1.1f;
  }
  public void win() {
    if ( xpos <= 0) {
      Score[1].count = Score[1].count+1;
      xpos = width/2;
      ypos = height/2;
      directionX = 4;
    }
    if ( xpos >= 1000) {
      Score[0].count = Score[0].count+1;
      xpos = width/2;
      ypos = height/2;
      directionX = -4;
    }
  }
}
class player {
  float ypos;
  float xpos;

  player( float tempY) {
    ypos = tempY;
  }
  public void moveUp() {
    ypos = ypos - 5;
  }
  public void moveDown() {
    ypos = ypos + 5;
  }
  public void display() {
    noStroke();
    fill(255);
    rect(xpos, ypos, 20, 70);
  }
  public void contrainte() {
    for (int i = 0; i <2; i ++) {
      if ( players[i].ypos >= 630 ) {
        players[i].ypos = 630;
      }
      if ( players[i].ypos <= 0 ) {
       players[i].ypos = 0;
      }
    }
  }
}
class score {

  float xpos, ypos;
  int count;
  int begin; 
  int duration = 10;
  int time = 5;

  score ( float tempX, float tempY) {
    xpos = tempX;
    ypos = tempY;
  }

  public void display(PFont font) {    
    text(count, xpos, ypos);
  }
  public void win() {
    count = count + 1;
  }
  public void victory() {
    if ( Score[0].count == 7) {
      text("PLAYER 1 WINS", 330, height/2);
      text("PRESS START TO PLAY AGAIN", 180, 600);
      play = false;
      Score[0].count = 0;
      Score[1].count = 0;
    }
    if ( Score[1].count == 7) {
      text("PLAYER 2 WINS", 330, height/2);
      text("PRESS START TO PLAY AGAIN", 180, 600);
      play = false;
      Score[1].count = 0;
      Score[0].count = 0;
    }
  }
  public void intro(PFont font) {
    text("press space to play", 250, height/2);
  }
  public void pause(PFont font) {
    text("PAUSE", width/2-70, height/2);
  }
  public void counter(PFont font) {
    if (time > 0) {  
      time = duration - (millis() - begin)/1000;
      text(time, width/2-5, 300); 
      if ( time <= 0) go = true;
    }
  }
}
  public void settings() {  size(1000, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "PONG_JOYSTICK" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
