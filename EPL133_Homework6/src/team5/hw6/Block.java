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
	public Block(int xc, int yc, boolean b, Grid Grid) {
		x=xc;
		y=yc;
		border=b;
		grid=Grid;
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
	public boolean isBorder() {
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
	
}