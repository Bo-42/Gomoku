package gomoku;

import java.io.Serializable;
import java.util.Arrays;

public class GameData implements Serializable 
{
	// data members
	private Stone[][] board;	// the 15x15 grid (2D array) that stores stone objects
	
	// default constructor
	public GameData() 
	{
		super();
		board = new Stone[15][15];
	}
	
	// parameterized constructor
	public GameData(Stone[][] board) 
	{
		super();
		this.board = board;
	}

	public Stone[][] getBoard() {
		return board;
	}

	public void setBoard(Stone[][] board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "GameData [board=" + Arrays.toString(board) + "]";
	}
	
}	// end class
