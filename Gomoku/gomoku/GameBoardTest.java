package gomoku;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest
{


 private GameplayPanel ggui;
  private GameBoardControl gbc;

  private GameBoard gb;
  ChatClient client = new ChatClient();
  JPanel container = new JPanel();
  GameplayControl gpc;
  private Stone[][] board;
   Random rand=new Random();
 
   @Before
   public void setUp() throws Exception
   
   {    
     ChatClient cc= new ChatClient();
     gbc = new  GameBoardControl(cc);
     gpc=new GameplayControl(container,cc);
     ggui = new GameplayPanel(gpc);
     gb=new GameBoard(gbc);
    
   }

   
   @Test
   public void testupdateBoard()
   {  
     board = new Stone[15][15];
   
     for (int i = 0; i < 5; i++)
     {
      
       int row = (int)rand.nextInt(15);
      
       int col = (int)rand.nextInt(15);
       boolean c=rand.nextBoolean();
       this.board[row][col] = new Stone(c);
    
   
     }
     gb.updateBoard(board);
    
   }
   
  
   
 
}
