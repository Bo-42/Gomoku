package gomoku;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;



public class GameplayPanel extends JPanel
{
    private   JLabel label ;
    private ChatClient client;
    private JPanel background;
    private Graphics g;
    private Stone bo[][];
    GameData gd=new GameData();
  public GameplayPanel(GameplayControl gpc, ChatClient client) 
  {
    this.client = client;
    //add one panel named background on the right panel
   JPanel right = new JPanel(new GridLayout(1, 1, 480, 480));
   background=new JPanel();
  background.addMouseListener(new MouseListener(){
    
    public void mousePress(MouseEvent e)
    {
      
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
      // TODO Auto-generated method stub
      double xofpanel=arg0.getX();
      double yofpanel=arg0.getY();
  
   
      int movex=(int) Math.round((xofpanel-10)/30);
      int movey=(int) Math.round((yofpanel-10)/30);
   
      
      System.out.println(movex+" "+movey);
      Move mo=new Move(movey,movex);
      
     
     if(gd.checkMove(mo)==false)
     { g=background.getGraphics();
       g.fillOval((movex)*30+5,(movey)*30+5,15,15);
       g.setColor(Color.black);
     }
     
      try {
        client.sendToServer(mo);
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }
    
    
    
  });
   right.add(background);
   JPanel left = new JPanel(new GridLayout(3, 1, 100, 160));
   JLabel labeltitle = new JLabel("Welcome to Gomoku !");
   labeltitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 46));
   JPanel but = new JPanel(new GridLayout(1,2, 100, 160));
  //JButton btnStart = new JButton("Let's Start it");

  JButton exit = new JButton("Exit");
  JButton quit = new JButton("Quit");
  quit.addActionListener(gpc);
  exit.addActionListener(gpc);
 // but.add(btnStart);
  but.add(quit);
  but.add(exit);
  exit.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    
      }
      
    }
    
    
    );  
    label = new JLabel("");
    label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 44));
    left.add(labeltitle);
    left.add(label);
    left.add(but);
    
  
    JPanel jp = new JPanel(new GridLayout(1, 2, 1, 10));
    jp.add(left);
    jp.add(right);
    this.add(jp);
    
      
  }
 
  
  public void drawChess(Stone[][] board)
    {//NULL is nothing 1 is white 0 is black
    g=background.getGraphics();

    for(int c=0;c<15;c++)
    {
      for(int r=0;r<15;r++)
      {
        if(board[r][c]==null)
        {
       continue;
        }
        else if(board[r][c].getColor()==true)
        {     
        g.fillOval(r*30+5,c*30+5,15,15);
        g.setColor(Color.white);
        }
        else if (board[r][c].getColor()==false)
        {  
     
          g=background.getGraphics();
          g.fillOval(r*30+5,c*30+5,15,15);
          g.setColor(Color.black);
     
        }
      }
    }
  
 
 }

  public void setMessage(String message)
    {
      
      label.setText(message);
      g=background.getGraphics();
      
    for(int i=0;i<15;i++)
      {
       g.drawLine(10,i*30+10,430, i*30+10);
       g.drawLine(i*30+10,10,i*30+10,430);
      }

    }
  
 
  
   
}
