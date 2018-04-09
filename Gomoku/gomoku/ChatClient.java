package gomoku;

import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient
{
  // Private data fields for storing the GUI controllers.
  private LoginControl loginControl;
  private CreateAccountControl createAccountControl;
  private GameplayControl gpc;
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
	    	 gpc.dispalylabel(message);
	      }
	    }
	    else if (arg0 instanceof  GameData)
	    {//get GameData object
	    	GameData gd= (GameData)arg0;
	    	
    	
    	
	    }
  
    
  }  
}
