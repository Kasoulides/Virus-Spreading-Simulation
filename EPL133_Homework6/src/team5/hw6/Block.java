package team5.hw6;

/**
 * This class represents a block.
 * 
 *  @author Christos Eleftheriou
 *  @author Christos Kasoulides
 *  
 *  @version 1.0
 *  
 *  @since 04/05/20
 */
public class Block {

	private int x;
	private int y;
	private boolean border;
	private Grid grid;
	
	/**
	 * This is the constructor of Block.
	 * 
	 * @param xc This is the x coordinate 
	 * 
	 * @param yc This is the y coordinate
	 * 
	 * @param b This is the boolean that decides if this block is a border or not
	 * 
	 * @param grid If this block is a border, this is the grid to which this block sends people
	 */
	public Block(int xc, int yc, boolean b ) {
		x=xc;
		y=yc;
		border=b;
		grid=null;
	}
	
	/**
	 * This method returns true  if this object is equal to the other one, otherwise false.
	 * 
	 *  @param obj The other object that is compared to this one
	 *  
	 *  @return boolean True if this object is equal to the other one, otherwise false
	 *  
	 *  @since 1.0
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (border != other.border)
			return false;
		if (grid == null) {
			if (other.grid != null)
				return false;
		} else if (!grid.equals(other.grid))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * This method returns the value of x.
	 * 
	 * @return int The value of x
	 * 
	 * @since 1.0
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * This method returns the value of y.
	 *  
	 * @return int The value of y
	 * 
	 * @since 1.0
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * This method returns the value of border.
	 * 
	 * @return boolean True if it is a border, otherwise false
	 * 
	 * @since 1.0
	 */
	public boolean getBorder() {
		return border;
	}
	
	/**
	 * This method returns the grid
	 * 
	 * @return Grid The grid that this block, if it is a border, leads to
	 * 
	 * @since 1.0
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * This method sets the grid that this block leads to.
	 * 
	 * @param g The grid that this block leads to
	 * 
	 * @since 1.0
	 */
	public void setGrid(Grid g) {
		grid=g;
	}
	
	/**
	 * This method sets the value of border to true.
	 * 
	 * @since 1.0
	 */
	public void setBorder() {
		border=true;
	}
	
	
	public String toString() {
		String s="";
		s+="This block's coordinates are ("+this.x+","+this.y+").\n";
		if(border)
			s+="This block is a border.\n";
		else
			s+="This block is not a border.\n";
		s+="It is connected to grid: \n";
		s+=this.grid.toString();
		return s;
			
	}
}
