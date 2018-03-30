package gomoku;

import java.io.Serializable;
import java.util.Arrays;

public class GameData implements Serializable 
{
	// data members
	private boolean whoseTurn;	// false = black, true = white
	private Stone[][] board;	// the 15x15 grid (2D array) that stores stone objects
	
	// default constructor
	public GameData() 
	{
		super();
		board = new Stone[15][15];
	}
	
	// parameterized constructor
	public GameData( Stone[][] board ) 
	{
		super();
		this.whoseTurn = true;
		this.board = board;
	}

	public Stone[][] getBoard() 
	{
		return board;
	}

	public void setBoard( Stone[][] board ) 
	{
		this.board = board;
	}
	
	public boolean isWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(boolean whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

	public boolean checkMove( Move move )
	{	// returns 1 (true) if game board grid location from move (row, column) contains a stone
		// returns 0 (false) if game board grid location from move (row, column) is empty
		
		if ( board[move.getRow()][move.getColumn()] == null)
			return false;
		else
			return true;
	}
	
	public void addStone( Move move, Stone stone )
	{
		this.board[ move.getRow() ][ move.getColumn() ].setColor( stone.getColor() );
	}
	
	public boolean checkWin( Move move , Stone stone)
	{	// returns a 1 (true) if a move creates a winning condition
		// returns a 0 (false) if a move does not create a winning condition
		boolean toReturn = false;
		
		//	To-Do
		
		return toReturn;
	}

	@Override
	public String toString() 
	{
		return "GameData [board=" + Arrays.toString(board) + "]";
	}
	
}	// end class
