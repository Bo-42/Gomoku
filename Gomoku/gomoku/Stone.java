package gomoku;

public class Stone 
{
	// data members
	private boolean color;	// 0:false: black, 1:true: white

	//  default constructor
	public Stone() 
	{
		super();
	}

	// parameterized constructor
	public Stone(boolean color) 
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

	@Override
	public String toString() 
	{
		return "stone [color=" + color + "]";
	}
	
}	// end class
