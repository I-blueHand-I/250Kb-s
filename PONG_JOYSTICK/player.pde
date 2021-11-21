class player {
  float ypos;
  float xpos;

  player( float tempY) {
    ypos = tempY;
  }
  void moveUp() {
    ypos = ypos - 5;
  }
  void moveDown() {
    ypos = ypos + 5;
  }
  void display() {
    noStroke();
    fill(255);
    rect(xpos, ypos, 20, 70);
  }
  void contrainte() {
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
