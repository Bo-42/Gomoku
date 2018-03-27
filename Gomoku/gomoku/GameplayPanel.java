package gomoku;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;



public class GameplayPanel extends JPanel
{
		private 	JLabel label ;
		
	  
	public GameplayPanel(GameplayControl gpc) 
	{
		
	 JPanel right = new JPanel(new GridLayout(1, 1, 500, 500));
	 JPanel background=new JPanel();
	 background.setPreferredSize(new Dimension(100,100));
	 background.setBackground(Color.pink);
 	 right.add(background);
	 JPanel left = new JPanel(new GridLayout(3, 1, 100, 160));
	 JLabel labeltitle = new JLabel("Welcome to Gomoku !");
	 labeltitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 46));
	 JPanel but = new JPanel(new GridLayout(1,3, 100, 160));
	JButton btnStart = new JButton("Let's Start it");

	JButton exit = new JButton("Exit");
	JButton quit = new JButton("Quit");
	quit.addActionListener(gpc);
	exit.addActionListener(gpc);
	but.add(btnStart);
	but.add(quit);
	but.add(exit);
	btnStart.addActionListener(gpc);
	
	btnStart.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						Graphics g=background.getGraphics();
					
						for(int i=0;i<=15;i++)
						{
							g.drawLine(10,i*30+10,460, i*30+10);
							g.drawLine(i*30+10,10,i*30+10,460);
						}
					}
			
				}
				
				
				
				
				
				);	
		
		exit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
		
			}
			
		}
		
		
		);	
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 46));
		left.add(labeltitle);
		left.add(label);
		left.add(but);
		
	
		JPanel jp = new JPanel(new GridLayout(1, 2, 1, 10));
		jp.add(left);
		jp.add(right);
		this.add(jp);
		
		
	}

	public void drawChess(JPanel jpanel)
	  {
	  	
	
     
	  }
	
	public void setMessage(String message)
	  {
	  	
	    label.setText(message);
     
	  }
}
