package gomoku;

import java.io.Serializable;

public class Move implements Serializable
{
	// private data members
	private int row;
	private int column;
	
	// default constructor
	public Move() 
	{
		super();
		this.row = 0;
		this.column = 0;
	}

	// parameterized constructor
	public Move(int row, int column) 
	{
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "Move [row=" + row + ", column=" + column + "]";
	}
	
}
