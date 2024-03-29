package team5.hw6;

import java.util.Arrays;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

/**
 * This class represents the grid
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 *
 */
public class Grid {
	
	private Block[][] borders; //the blocks that are considered borders are true
	private Person[][] persons;
	private int[][] trace;
	private boolean[][] exists;
	private final int MAX_TRACE;
	private int height;
	private int width;
	private final int TO_FLOOR;
	private final int TIME_INF;

	private int ID;
	private int numOfPerson;

	/**
	 * This is the constructor of grid.
	 * 
	 * @param h        This is the height of the grid
	 * 
	 * @param w        This is the height of the grid
	 * 
	 * @param tti      This is the time needed for a person to stay in a block to
	 *                 infect it
	 * 
	 * @param maxTrace This is the time an infected block stays infected
	 * 
	 * @param ptf      This is the chance out of 100 for a person infect a block
	 * 
	 * 
	 * @since 1.0
	 */
	public Grid(int h, int w, int maxTrace, int tti, int ptf, Block bord[][], int id) {
		numOfPerson = 0;
		MAX_TRACE = maxTrace;
		TO_FLOOR = ptf;
		TIME_INF = tti;
		ID = id;
		
		height = h;
		width = w;

		this.trace = new int[height][width];
		exists = new boolean[height][width];
		persons = new Person[height][width];
		borders=bord;
	}

	/**
	 * This method reduces the trace to positions by 1.
	 * 
	 * @since 1.0
	 */
	public void reduceTrace() {

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (trace[i][j] > 0)
					trace[i][j]--;

	}

	/**
	 * This method shows if a block is Infected or not.
	 * 
	 * @since 1.0
	 */
	public void showTrace() {

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (trace[i][j] > 0) {
					StdDraw.setPenColor(StdDraw.GRAY);
					StdDraw.filledSquare(i + 0.5, j + 0.5, 0.45);

				} else if (trace[i][j] - 1 == 0) {
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledSquare(i + 0.5, j + 0.5, 0.46);

				}

	}

	/**
	 * This method checks if the array can fit more people.
	 * 
	 * @return boolean True if the array is full, otherwise false
	 * 
	 * @since 1.0
	 */
	public boolean isFull() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (!exists[i][j])
					return false;

		return true;
	}

	/**
	 * This method checks if an infected person exists around the x,y position.
	 * 
	 * @param x The x coordinate of the position
	 * 
	 * @param y The y coordinate of the position
	 * 
	 * @return boolean True if there is an infected person around, otherwise false
	 * 
	 * @since 1.0
	 */
	public boolean checkAround(int x, int y) {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if ((x + i >= 0) && (x + i < height) && (y + j >= 0) && 
						(y + j < width) && !(i == 0) && !(j == 0))
					if (exists[x + i][y + j] && persons[x + i][y + j].isInfected())
						return true;

		return false;

	}

	/**
	 * This method checks if a person is trapped at a position and can not move.
	 * 
	 * @param x The x coordinate of the position
	 * 
	 * @param y The y coordinate of the position
	 * 
	 * @return boolean True if the person is trapped, otherwise false
	 * 
	 * @since 1.0
	 */
	public boolean isTrapped(int x, int y) {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if(borders[x][y].getBorder())
					return false;
				else if ((x + i >= 0) && (x + i < height) && (y + j >= 0) &&
						(y + j < width) && !(i == 0) && !(j == 0)) {
					if (!exists[x + i][y + j])
						return false;
				}
				

		return true;
	}

	/**
	 * This method sets a x,y position on the array true, if the person is infected
	 * sets the trace to MAX.
	 * 
	 * @param x The x coordinate of the position
	 * 
	 * @param y The y coordinate of the position
	 * 
	 * @param p The person
	 * 
	 * @since 1.0
	 */
	public void setPos(int x, int y, Person p) {
		exists[x][y] = true;
		persons[x][y] = p;
		numOfPerson ++;

		if (p.isInfected() && p.getCounter() >= TIME_INF) {
			if ((new Random()).nextInt(100) < TO_FLOOR)
				trace[x][y] = MAX_TRACE;
		}

	}

	/**
	 * This method clears the x,y position(sets it to false in the exists array)
	 * sets the x,y position in the array persons to null. It is used after a person
	 * moved to delete the previous position.
	 * 
	 * @param x The x coordinate of the position
	 * 
	 * @param y The y coordinate of the position
	 * 
	 * @since 1.0
	 */
	public void clearPos(int x, int y) {
		exists[x][y] = false;
		persons[x][y] = null;
		numOfPerson --;
	}
	
	
	

	/**
	 * This method returns true if that x,y position is taken by another person.
	 * 
	 * @param x The x coordinate of the position
	 * 
	 * @param y The y coordinate of the position
	 * 
	 * @return boolean True if that x,y position is taken, otherwise false
	 * 
	 * @since 1.0
	 */
	public boolean isTaken(int x, int y) {
		return exists[x][y];
	}
	
	/**
	 * This method returns true if this is a border, otherwise false.
	 * 
	 * @param x The x coordinate of the border
	 * 
	 * @param y The y coordinate of the border
	 * 
	 * @return boolean True if it is a border, otherwise false
	 * 
	 * @since 1.0
	 */
	public boolean isBorder(int x,int y) {
		return borders[x][y].getBorder();
	}
	
	/**
	 * This method returns the value of ID.
	 * 
	 * @return int The value of ID
	 * 
	 * @since 1.0
	 */
	public int getID() {
		return ID;
	}
	/**
	 * This method returns the number of Person in the grid.
	 * 
	 * @return int the number of Person
	 * 
	 * @since 1.0
	 */
	public int getNumOfPerson() {
		return numOfPerson;
	}
	
	/**
	 * This method returns the borders array.
	 * 
	 * @return Block[][] The borders array
	 * 
	 * @since 1.0
	 */
	public Block[][] getBorders() {
		return borders;
	}

	/**
	 * This method returns the trace of the x,y position.
	 * 
	 * @param x The x coordinate of the position
	 * 
	 * @param y The y coordinate of the position
	 * 
	 * @return int The trace of the position
	 * 
	 * @since 1.0
	 */
	public int getTrace(int x, int y) {
		return trace[x][y];

	}

	/**
	 * This method returns the value of height.
	 * 
	 * @return int The value of height
	 * 
	 * @since 1.0
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * This method sets the value of height.
	 * 
	 * @param height The new value of height
	 * 
	 * @since 1.0
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * This method returns the value of width
	 * 
	 * @return int The value of width
	 * 
	 * @since 1.0
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * This method sets the value of width.
	 * 
	 * @param width The new value of width.
	 * 
	 * @since 1.0
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * This method places a person on a random place. 
	 * 
	 * @param p This is the person that is going to be placed
	 * 
	 * @since 1.0
	 */
	public void placeRandom(Person p) {
		
		int newX,newY;
		Random r = new Random();
		do {
			newX = r.nextInt(height);
			newY = r.nextInt(width);

		} while (isTaken(newX,newY));
		
		p.setX(newX);
		p.setY(newY);
		p.resetCounter();
		setPos(newX,newY,p);

		
	}



	/**
	 * This method returns true if this object is equal to the other one, otherwise false.
	 * 
	 * @param obj The object that is compared to this one
	 * 
	 * @return boolean True if this object is equal to the other one, otherwise false
	 * 
	 * @since 1.0
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid other = (Grid) obj;
		if (ID != other.ID)
			return false;
		if (MAX_TRACE != other.MAX_TRACE)
			return false;
		if (TIME_INF != other.TIME_INF)
			return false;
		if (TO_FLOOR != other.TO_FLOOR)
			return false;
		if (!Arrays.deepEquals(borders, other.borders))
			return false;
		if (!Arrays.deepEquals(exists, other.exists))
			return false;
		if (height != other.height)
			return false;
		if (!Arrays.deepEquals(persons, other.persons))
			return false;
		if (!Arrays.deepEquals(trace, other.trace))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	
	public String toString() {
	
		return "Grid " + (ID+1) + " has a height of " + height + ", a width of " + width + ".";
	}
	
	

}
