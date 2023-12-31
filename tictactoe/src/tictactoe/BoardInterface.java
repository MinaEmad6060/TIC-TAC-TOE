package tictactoe;


import javafx.scene.control.Button;


interface BoardInterface {
  
  public void initBoard();
  
  public boolean availableToCheck();
  
  public short checkOnGame();
  
  public void checkWinner();
  
  public void hilightWin(int row1 , int col1 , int row2 , int col2 , int row3 , int col3);
  
  public void updateScore();
  
  public boolean checkDraw();
  
  public void drawAlert();
  
}