package gomoku;

import java.awt.*;
import javax.swing.*;

import gomoku.LoginData;
import gomoku.CreateAccountData;

import java.io.IOException;
import java.util.ArrayList;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer
{
  // Data fields for this chat server.
  private JTextArea log;
  private JLabel status;
  private boolean running = false;
  private Database db = new Database();
  ArrayList<ConnectionToClient> clients = new ArrayList<ConnectionToClient>(2);
  GameData game = new GameData();

  // Constructor for initializing the server with default settings.
  public ChatServer()
  {
    super(12345);
    this.setTimeout(500);
  }

  // Getter that returns whether the server is currently running.
  public boolean isRunning()
  {
    return running;
  }
  
  // Setters for the data fields corresponding to the GUI elements.
  public void setLog(JTextArea log)
  {
    this.log = log;
  }
  public void setStatus(JLabel status)
  {
    this.status = status;
  }

  // When the server starts, update the GUI.
  public void serverStarted()
  {
    running = true;
    status.setText("Listening");
    status.setForeground(Color.GREEN);
    log.append("Server started\n");
  }
  
  // When the server stops listening, update the GUI.
   public void serverStopped()
   {
     status.setText("Stopped");
     status.setForeground(Color.RED);
     log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
   }
 
  // When the server closes completely, update the GUI.
  public void serverClosed()
  {
    running = false;
    status.setText("Close");
    status.setForeground(Color.RED);
    log.append("Server and all current clients are closed - press Listen to restart\n");
  }

  // When a client connects or disconnects, display a message in the log.
  public void clientConnected(ConnectionToClient client)
  {
    log.append("Client " + client.getId() + " connected\n");
    clients.add(client);
  }

  // When a message is received from a client, handle it.
  public void handleMessageFromClient(Object arg0, ConnectionToClient client)
  {
    // If we received LoginData, verify the account information.
    if (arg0 instanceof LoginData)
    {
    	LoginData loginData = (LoginData)arg0;
    	
    	if (checkLogin(loginData.getUsername(), loginData.getPassword()))
    	{
    		log.append(loginData.getUsername() + " successfully logged in!\n");
    		try {
				client.sendToClient("Success:Login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else
    	{
    		System.out.println("Incorrect login.");
    		try {
				client.sendToClient("Failure:Login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    // If we received CreateAccountData, create a new account.
    else if (arg0 instanceof CreateAccountData)
    {
    	CreateAccountData createAccountData = (CreateAccountData)arg0;
    	if (registerUsername(createAccountData.getUsername(), createAccountData.getPassword()))
    	{
    		log.append(createAccountData.getUsername() + " successfully registered!\n");
    		try {
				client.sendToClient("Success:Register");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else
    	{
    		System.out.println("Username already exists.");
    		try {
				client.sendToClient("Failure:Register");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    else if (arg0 instanceof Move)
    {
    	long id = client.getId();
    	boolean clientNo;
    	Move move = (Move)arg0;
    	
    	if (id == clients.get(0).getId())
    	{
    		clientNo = false;
    	}
    	else
    	{
    		clientNo = true;
    	}
    	if (game.checkMove(move))
    	{
    		game.setWhoseTurn(clientNo);
    		try {
				client.sendToClient(game);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else
    	{
    		game.addStone(move, new Stone(clientNo));
    		if (game.checkWin(move))
    		{
    			log.append(clientNo + " wins!\n");
    			for (ConnectionToClient c : clients)
    			{
    				try {
						c.sendToClient("Winner:" + clientNo);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    		else
    		{
    			for (ConnectionToClient c : clients)
    			{
    				try {
						c.sendToClient(game);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    	}
    }
  }

  // Method that handles listening exceptions by displaying exception information.
  public void listeningException(Throwable exception) 
  {
    running = false;
    status.setText("Exception occurred while listening");
    status.setForeground(Color.RED);
    log.append("Listening exception: " + exception.getMessage() + "\n");
    log.append("Press Listen to restart server\n");
  }
  
  protected boolean registerUsername(String username, String password)
  {
	  ArrayList<String> result;
	  result = db.query("select username from userdata where username = \'" + username + "\'");
	  if (result == null && db.executeDML("insert into userdata values (\'" + username + "\', aes_encrypt(\'" + password + "\',\'test\'))"))
	  {
		  return true;
	  }
	  return false;
  }
  
  protected boolean checkLogin(String username, String password)
  {
	  ArrayList<String> result;
	  result = db.query("select username from userdata where username = \'" + username + "\' and aes_decrypt(password,\'test\') = \'" + password + "\'");
	  if (result != null)
	  {
		  for (String s : result)
		  {
			  if (s.startsWith(username))
			  {
				  return true;
			  }
		  }
	  }
	  return false;
  }
}
