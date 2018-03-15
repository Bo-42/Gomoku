package gomoku;

public class stone 
{
	// data members
	private boolean color;	// 0: black, 1: white

	//  default constructor
	public stone() 
	{
		super();
	}

	// parameterized constructor
	public stone(boolean color) 
	{
		super();
		this.color = color;
	}

	// getter
	public boolean getColor() 
	{
		return color;
	}

	// setter
	public void setColor(boolean color) 
	{
		this.color = color;
	}
	
}	// end class
