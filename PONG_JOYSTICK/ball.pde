class ball {

  float xpos, ypos, directionX, directionY;

  ball(float tempX, float tempY, float dirX, float dirY) {
    xpos = tempX;
    ypos = tempY; 
    directionX = dirX;
    directionY = dirY;
  }
  void move() {
    xpos = xpos + directionX;
    ypos = ypos + directionY;
  }
  void display() {  
    noStroke();
    fill(255);
    rect (xpos, ypos, 20, 20);
  }
  void bounce() {
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
  void contrainte() {
    if (ypos >= 690) {
      directionY = -directionY;
    }
    if (ypos <= 0 ) {
      directionY = -directionY;
    }
  }
  void accel() {
    directionX= directionX*1.1;
  }
  void win() {
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
