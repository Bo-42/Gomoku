package gomoku;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GameplayControl implements ActionListener
{
	// Private data fields for the container and chat client.
	  private JPanel container;
	  private ChatClient client;
	 
	// Constructor for the login controller.
	  public GameplayControl(JPanel container, ChatClient client)
	  {
	    this.container = container;
	    this.client = client;
	  }

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		  // Get the name of the button clicked.
	    String command = ae.getActionCommand();
		
	    // The Cancel button takes the user back to the initial panel.
	    if (command == "Let's Start it")
	    {
	    	
	    
	    }
	    else if(command=="Quit")
	    {
	    	dispalylabel("You Quit it!");
	    	
	 
	    	  
	    	
	    }
	    else if(command=="Exit")
	    {
	    	
	    	
	    	CardLayout cardLayout = (CardLayout)container.getLayout();
	        cardLayout.show(container, "1"); 
	    	  
	    	
	    }
		
		
		
	}

	private void dispalylabel(String message) {
		// TODO Auto-generated method stub
		 GameplayPanel  gameplayPanel = (GameplayPanel)container.getComponent(3);
		 gameplayPanel.setMessage(message);
		
	}
	
	  

}
