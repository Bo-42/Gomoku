package gomoku;

import java.io.ObjectOutputStream;

import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient
{
  // Private data fields for storing the GUI controllers.
  private LoginControl loginControl;
  private CreateAccountControl createAccountControl;
  private GameplayControl gpc;
  boolean whois;
  // Setters for the GUI controllers.
  public void setLoginControl(LoginControl loginControl)
  {
    this.loginControl = loginControl;
  }
  public void setCreateAccountControl(CreateAccountControl createAccountControl)
  {
    this.createAccountControl = createAccountControl;
  }
  public void setGameplayControl(GameplayControl gpc)
  {
    this.gpc= gpc;
  }
  // Constructor for initializing the client with default settings.
  public ChatClient()
  {
    super("localhost", 8300);
  }
  
  // Method that handles messages from the server.
  public void handleMessageFromServer(Object arg0)
  {
	  if (arg0 instanceof String)
	    {
	      // Get the text of the message.
	      String message = (String)arg0;
	      
	      // If we successfully logged in, tell the login controller.
	      if (message.equals("Success:Login"))
	      {
	        loginControl.loginSuccess();
	      }
	      else if (message.equals("Failure:Login"))
	      {
	    	  loginControl.displayError("Failure:Login");
	      }
	      
	      // If we successfully created an account, tell the create account controller.
	      else if (message.equals("Success:Register"))
	      {
	        createAccountControl.createAccountSuccess();
	      }
	      else if (message.equals("Failure:Register"))
	      {
	    	  createAccountControl.displayError("Failure:Register");
	      }
	      else if (message.substring(0, 6).equals("Winner:"))
	     {
	    	 gpc.displayLabel(message);
	      }
	    }
	    else if (arg0 instanceof  GameData)
	    {
	    	
	    
	    	if(((GameData) arg0).isWhoseTurn()==whois)
	    	{
	    		if (((GameData) arg0).checkWon())
	    		{
	    			gpc.displayLabel("You won!");
	    			gpc.stopMouse();
	    		}
	    		else
	    		{
	    			gpc.displayLabel("Your move");
	    		}
	    		
	    		
	    		//update the game board
	    		gpc.drawChess(((GameData) arg0).getBoard());
	    		
	    	}
	    	else
	    	{
	    		if (((GameData) arg0).checkWon())
	    		{
	    			gpc.displayLabel("Opponent wins!");
	    			gpc.stopMouse();
	    		}
	    		else
	    		{
	    			gpc.displayLabel("Other player's turn");
	    		}
	    		gpc.drawChess(((GameData) arg0).getBoard());
	    	}
	    	
	    
	    }
	    else if (arg0 instanceof Boolean)
	    {
	    	whois=((Boolean) arg0).booleanValue();
	    	gpc.setPlayer(((Boolean) arg0).booleanValue());
	    }
  
    
  }  
}
