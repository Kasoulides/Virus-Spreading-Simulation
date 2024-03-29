package team5.hw6;

import java.awt.Color;
import java.util.Random;

import edu.princeton.cs.introcs.*;

/**
 * This class represents a person
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 *
 */
public class Person {

	static final double R = 0.3;
	private int TTI;
	private int PTP;
	private int FTP;
	private int SP;
	private boolean infected;
	private boolean selfProtected;
	private boolean immune;
	private int counter;
	private int currentX;
	private int currentY;
	private Grid grid;
	private Color col;
	private boolean toMove;
	// counters
	private static int c = 0;
	private static int spff = 0; // counter for people infected from floor while self-Protected
	private static int spfp = 0; // counter for people infected from person while self-Protected

	private static int ff = 0; // counter for normal people infected from floor
	private static int fp = 0; // counter for normal people infected from person

	
		

	/**
	 * This is the constructor of the person.
	 * 
	 * @param g      This is the grid that the person i placed into
	 * 
	 * @param selfPr This is the chance out of 100 for the person to be self
	 *               protected.
	 * 
	 * @param imm    This is the chance out of 100 for the person to be immune
	 * 
	 * @param inf    This is the chance out of 100 for the person to be infected
	 * 
	 * @param tti    This is the time needed for a person to stay in a block to
	 *               infect it
	 * 
	 * @param ptp    This is the chance out of 100 of an infected person to infect
	 *               another one
	 * 
	 * @param ftp    This is the chance out of 100 for a person to be infected by an
	 *               infected block
	 * 
	 * @param sp     This is a chance out of 100 making a person get infected harder
	 * 
	 * @since 1.0
	 */
	public Person(Grid g, int selfPr, int imm, int inf, int tti, int ptp, int ftp, int sp) {
		TTI = tti;
		PTP = ptp;
		FTP = ftp;
		SP = sp;

		grid = g;
		counter = 0;

		this.Calculate(imm, selfPr, inf);

		Random r = new Random();

		do {
			currentX = r.nextInt(g.getHeight());
			currentY = r.nextInt(g.getWidth());

		} while (grid.isTaken(currentX, currentY));

		grid.setPos(currentX, currentY, this);
	
		if (infected)
			col = StdDraw.RED;
		else if (selfProtected)
			col = StdDraw.BLUE;
		else if (immune)
			col = StdDraw.GREEN;
		else
			col = StdDraw.BOOK_LIGHT_BLUE;

		drawCircle();

	}

	/**
	 * This is the constructor of the person.
	 * 
	 * This constructor sets TTI = 20, PTP = 50, FTP = 30.
	 * 
	 * @param g      This is the grid that the person i placed into
	 * 
	 * @param selfPr This is the chance out of 100 for the person to be self
	 *               protected.
	 * 
	 * @param imm    This is the chance out of 100 for the person to be immune
	 * 
	 * @param inf    This is the chance out of 100 for the person to be infected
	 * 
	 * @since 1.0
	 */
	public Person(Grid g, int selfPr, int imm, int inf) {
		this(g, selfPr, imm, inf, 20, 50, 30, 30);
	}
	
	/**
	 * This method is used by a person to move to a different position or stay at
	 * the same one, this happens randomly.
	 * 
	 * @since 1.0
	 */
	public void move() {

		this.checkInfection();// checks before
		
		
		Random r = new Random();

	
		int nx=0;
		int ny=0;
		
		grid.clearPos(currentX, currentY);
		

		if (grid.isTrapped(currentX, currentY)) {
			nx = 0;
			ny = 0;
			
		} else {
			
			do {
				
				
				int new1=r.nextInt(9)+1;
				
				switch(new1) {
				case 1:
					nx=-1;
					ny=-1;
					break;
				case 2:
					nx=-1;
					ny=0;
					break;
				case 3:
					nx=-1;
					ny=1;
					break;
				case 4:
					nx=0;
					ny=-1;
					break;
				case 5:
					nx=0;
					ny=0;
					break;
				case 6:
					nx=0;
					ny=1;
					break;
				case 7:
					nx=1;
					ny=-1;
					break;
				case 8:
					nx=1;
					ny=0;
					break;
				case 9:
					nx=1;
					ny=1;
					break;
				}
				//nx = r.nextInt(3) - 1;
				//ny = r.nextInt(3) - 1;
			} while (!(isValid(currentX + nx, currentY + ny)));
		}
		
		if(currentX + nx < 0 || currentX + nx == grid.getHeight() || currentY + ny < 0 || currentY + ny == grid.getWidth()) {
			toMove=true;
			counter=0;
			return ;
		}

		if (nx == 0 && ny == 0)
			counter++;
		else
			counter = 0;

		/*
		 * if (nx != 0 && ny != 0) { grid.clearPos(currentX, currentY); }
		 */

	

		
		currentX += nx;
		currentY += ny;

		grid.setPos(currentX, currentY, this);
		this.checkInfection();// checks after move

		this.drawCircle();
	}
	

	private void Calculate(int imm, int selfPr, int inf) {

		if (c == 0) {
			infected = true;
			c++;
			return;
		}
		Random r = new Random();

		if (r.nextInt(100) < imm) {
			immune = true;
		} else if (r.nextInt(100) < inf) {
			infected = true;
		} else if (r.nextInt(100) < selfPr) {
			selfProtected = true;
		}

	}
	

	private void checkInfection() {
		Random r = new Random();
		if (infected || immune) {
			return;
		}

		else if (grid.checkAround(currentX, currentY)) {
			if (selfProtected) {
				if (r.nextInt(100 + SP) < PTP) {
					spfp++;
					infected = true;
					col = StdDraw.RED;
				}
			} else if (r.nextInt(100) < PTP) {
				fp++;
				infected = true;
				col = StdDraw.RED;
			}
		} else if (grid.getTrace(currentX, currentY) > 0 && counter > TTI) {
			if (selfProtected) {

				if (r.nextInt(100 + SP) < FTP) {
					spff++;
					infected = true;
					col = StdDraw.RED;
				}
			} else if (r.nextInt(100) < FTP) {
				ff++;
				infected = true;
				col = StdDraw.RED;

			}
		}

	}

	/**
	 * This method returns the value of counter.
	 * 
	 * @return int The value of counter
	 * 
	 * @since 1.0
	 */
	public int getCounter() {
		return counter;
	}

	private void drawCircle() {
		StdDraw.setPenColor(col);
		StdDraw.filledCircle(currentY + 0.5, currentX + 0.5, R);
	}

	private void eraseCircle() {
		if(grid.getTrace(currentX, currentY) > 1)
			StdDraw.setPenColor(StdDraw.GRAY);
		else
			StdDraw.setPenColor(StdDraw.WHITE);
		
		StdDraw.filledCircle(currentY + 0.5, currentX + 0.5, R + 0.01);
	}

	/**
	 * This method returns the boolean value of infected.
	 * 
	 * @return boolean The value of infected
	 * 
	 * @since 1.0
	 */
	public boolean isInfected() {
		return infected;
	}

	/**
	 * This method checks if a position on the grid is valid for a person to move
	 * on.
	 * 
	 * It checks if the coordinates are in bounds and uses isTaken to check if
	 * another person is already there. It returns true if the position is valid,
	 * otherwise false.
	 * 
	 * @param x The x coordinate of the position
	 * 
	 * @param y The y coordinate of the position
	 * 
	 * @return boolean True if the position is valid, otherwise false
	 */
	private boolean isValid(int x, int y) {
		if(!grid.isBorder(currentX, currentY))
			return  ((x >= 0) && (x < grid.getHeight()) && (y >= 0) &&
					(y < grid.getWidth()) && !grid.isTaken(x, y));
		else if(x<0 || x==grid.getHeight() || y<0 || y==grid.getWidth() && !grid.getBorders()[currentX][currentY].getGrid().isFull())
			return true;
		return false;
	}
	/**
	 * This method returns the persons grid.
	 * 
	 * @return Grid returns the persons grid
	 * 
	 * @since 1.0
	 */
	public Grid getGrid() {
		return grid;
	}
	

	/**
	 * This method returns the value of currentX.
	 * 
	 * @return int The value of currentX
	 * 
	 * @since 1.0
	 */
	public int getCurrentX() {
		return currentX;
	}

	/**
	 * This method returns the value of currentY.
	 * 
	 * @return int The value of currentY
	 * 
	 * @since 1.0
	 */
	public int getCurrentY() {
		return currentY;
	}

	/**
	 * This method returns the value of immune.
	 * 
	 * @return boolean The value of immune
	 * 
	 * @since 1.0
	 */
	public boolean getImmune() {
		return immune;
	}

	/**
	 * This method returns the value of selfProtected.
	 * 
	 * @return boolean The value of selfProtected
	 * 
	 * @since 1.0
	 */
	public boolean getSelfProtected() {
		return selfProtected;
	}

	/**
	 * This is a getter method for the static counter spff.
	 * 
	 * @return the spff
	 * 
	 * @since 1.0
	 */
	public static int getSpff() {
		return spff;
	}

	/**
	 * This is a getter method for the static counter spfp.
	 * 
	 * @return the spfp
	 * 
	 * @since 1.0
	 */
	public static int getSpfp() {
		return spfp;
	}

	/**
	 * This is a getter method for the static counter ff.
	 * 
	 * @return the ff
	 * 
	 * @since 1.0
	 */
	public static int getFf() {
		return ff;
	}

	/**
	 * This is a getter method for the static counter fp.
	 * 
	 * @return the fp
	 * 
	 * @since 1.0
	 */
	public static int getFp() {
		return fp;
	}
	
	/**
	 * This method sets the grid that this person is into.
	 * 
	 * @param other This is the grid that this person is into
	 * 
	 * @since 1.0
	 */
	public void setGrid(Grid other) {
		
		grid = other;
	}
	
	/**
	 * This method sets the value of the x coordinate of this person.
	 * 
	 * @param otherX This is the new x coordinate
	 * 
	 * @since 1.0
	 */
	public void setX(int otherX) {
		currentX = otherX;
	}
	
	/**
	 * This method sets the value of the y coordinate of this person.
	 * 
	 * @param otherY This is the new y coordinate
	 * 
	 * @since 1.0
	 */
	public void setY(int otherY) {
		currentY = otherY;
	}
	
	/**
	 * This method returns the value of toMove.
	 * 
	 * @return boolean The value of toMove
	 * 
	 * @since 1.0
	 */
	public boolean hasToMove() {
		return toMove;
	}
	
	/**
	 * This method sets the value of toMove to false.
	 * 
	 * @since 1.0
	 */
	public void resetToMove() {
		toMove = false;
	}
	
	/**
	 * This method sets the value of counter to 0.
	 * 
	 * @since 1.0
	 */
	public void resetCounter() {
		counter = 0;
	}


	/**
	 * This method returns true if this object is equal to the other one, otherwise false.
	 * 
	 * @param obj The other object that is compared to this one
	 * 
	 * @return boolean True if this object if this object is equal to the other one, otherwise false
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
		Person other = (Person) obj;
		if (FTP != other.FTP)
			return false;
		if (PTP != other.PTP)
			return false;
		if (SP != other.SP)
			return false;
		if (TTI != other.TTI)
			return false;
		if (col == null) {
			if (other.col != null)
				return false;
		} else if (!col.equals(other.col))
			return false;
		if (counter != other.counter)
			return false;
		if (currentX != other.currentX)
			return false;
		if (currentY != other.currentY)
			return false;
		if (grid == null) {
			if (other.grid != null)
				return false;
		} else if (!grid.equals(other.grid))
			return false;
		if (immune != other.immune)
			return false;
		if (infected != other.infected)
			return false;
		if (selfProtected != other.selfProtected)
			return false;
		if (toMove != other.toMove)
			return false;
		return true;
	}
	
	public String toString() {
		String s="";
		s+="The coordinates of this person are ("+this.currentX+","+this.currentY+").\n";
		s+="This person is ";
		if(infected)
			s+="infected, is not immune and is ";
		else if(immune)
			s+="not infected, immune and is ";
		if(selfProtected)
			s+="self protected.\n";
		else
			s+="not self protected.\n";
		String c;
		if(infected)
			c="red";
		else if(immune)
			c="green";
		else if(selfProtected)
			c="blue";
		else
			c="light blue";
		s+="Its color is "+c+".\n";
		s+="This person is in grid:\n";
		s+=this.grid.toString();
		return s;
	}

}
