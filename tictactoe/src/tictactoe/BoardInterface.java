//edit package name
package tictactoe;

import javafx.scene.control.Button;

interface BoardInterface {
  
  public void initBoard();
  
  public boolean availableToCheck(Button arrBtn[][]);
  
  public short checkOnGame(Button arrBtn[][]);
  
  public boolean checkWinner(int row1 , int col1 , int row2 , int col2 , int row3 , int col3);
  
  public void hilightWin();
  
  public void updateScore();
  
  public boolean checkDraw();
  
}