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

  void display(PFont font) {    
    text(count, xpos, ypos);
  }
  void win() {
    count = count + 1;
  }
  void victory() {
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
  void intro(PFont font) {
    text("press space to play", 250, height/2);
  }
  void pause(PFont font) {
    text("PAUSE", width/2-70, height/2);
  }
  void counter(PFont font) {
    if (time > 0) {  
      time = duration - (millis() - begin)/1000;
      text(time, width/2-5, 300); 
      if ( time <= 0) go = true;
    }
  }
}
