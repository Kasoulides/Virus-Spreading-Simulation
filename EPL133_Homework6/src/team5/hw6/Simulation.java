package team5.hw6;

import edu.princeton.cs.introcs.*;

import java.util.*;

/**
 * This class represents a virus-spreading simulation between different areas
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 *
 */
public class Simulation { // testing

	static void drawFrame(Grid g) {

		StdDraw.clear();

		StdDraw.setXscale(0, g.getWidth());
		StdDraw.setYscale(g.getHeight(), 0);

		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i <= g.getWidth(); i++)
			StdDraw.line(i, 0, i, g.getHeight());
		for (int i = 0; i <= g.getHeight(); i++)
			StdDraw.line(0, i, g.getWidth(), i);

		for (int i = 0; i < g.getHeight(); i++)
			for (int j = 0; j < g.getWidth(); j++)
				if (g.getBorders()[i][j].getBorder()) {
					StdDraw.setPenColor(StdDraw.YELLOW);
					StdDraw.square(j + 0.5, i + 0.5, 0.49);
				}

	}

	public static boolean isValid(String s, int height, int width, boolean arr[][]) {
		if (s == null) {
			return false;

		} else if (s.indexOf(',') <= 0) {
			System.out.println("Wrong input!! The correct form is x,y .Please try again.\n");
			return false;
		}
		try {
			int x = Integer.parseInt(s.substring(0, s.indexOf(',')));
			int y = Integer.parseInt(s.substring(s.indexOf(',') + 1));
		} catch (Exception valid) {
			System.out.println("Wrong input!! The correct form is x,y .Please try again.\n");
			return false;
		}

		int x = Integer.parseInt(s.substring(0, s.indexOf(',')));
		int y = Integer.parseInt(s.substring(s.indexOf(',') + 1));

		if (x < 0 || x >= height || y < 0 || y >= width) {
			System.out.println("Values are not included in the simulation!! "
					+ "Please try again.\n");
			return false;
		}

		if (arr[x][y]) {
			System.out.println(x + "," + y + " is already a border!!"
					+ " Please try again.\n");
			return false;
		}

		if (x == 0 || y == 0 || x == height - 1 || y == width - 1)
			return true;
		else {
			System.out.println(x + "," + y + " isn't on the border of the simulation!!"
					+ " Please try again.\n");
			return false;

		}

	}

	public static void main(String[] args) throws IncorrectAnswerException, OutOfRangeException,
			PeopleOverloadingException, SimulationSizeException, BordersOutOfRangeException {

		// INITIALIZATIONS
		int height = 0, width = 0, areas = 0, N = 0, time = 0, selfPr = 0, imm = 0, inf = 0, TTI = 2, PTP = 30,
				FTP = 30, MAXtrace = 2, PTF = 30, SP = 30, numOfBorders = 0, newGrid = 0, totalPerson = 0;
		String answer = "Y", newAnswer, sureExit;
		boolean exit, flag[];
		Person[] persons = null;
		Block borders[][] = null;

		StdOut.println("+--------------------------------------------------------+");
		StdOut.println("|       WELCOME TO OUR VIRUS-SPREADING SIMULATION        |");
		StdOut.println("|                                                        |");
		StdOut.println("|   The purpose of this program is to simulate a virus 	 |");
		StdOut.println("| spreading through different areas and to provide data  |");
		StdOut.println("|  about the level of the spread in different scenarios. |");
		StdOut.println("|                                                        |");
		StdOut.println("+--------------------------------------------------------+");

		StdOut.println("Follow the instructions and you'll be fine\n");
		StdOut.println("LETS BEGIN!!\n\n\n");

		// INITIALIZE FLAGS

		flag = new boolean[6];

		flag = new boolean[12];

		for (int i = 0; i < flag.length; i++)
			flag[i] = false;

		// USER INPUTS 1st part
		boolean done1 = false;
		while (!done1) {
			try {

				// DURATION OF SIMULATION

				if (!flag[0]) {
					StdOut.println("Enter the duration of the simulation(must be in " 
				+ "minutes): ");
					time = StdIn.readInt();
					if (time < 0)
						throw new NegativeNumberException(
								"The duration of the " + "simulation cannot be a negative number:");
					flag[0] = true;
				}
				// CHANCE OF A PERSON BEING SELF-PROTECTED

				if (!flag[1]) {
					StdOut.println("Enter the chance of a person being Self " 
				+ "Protected(must be between 1-100): ");
					selfPr = StdIn.readInt();
					if (selfPr < 1 || selfPr > 100)
						throw new OutOfRangeException("The number must be between 1-100");
					flag[1] = true;
				}
				// CHANCE OF A PERSON BEING IMMUNE

				if (!flag[2]) {
					StdOut.println("Enter the chance of a person being Immune"
				+ "(must be between 1-100): ");
					imm = StdIn.readInt();
					if (imm < 1 || imm > 100)
						throw new OutOfRangeException("The number must be between 1-100");
					flag[2] = true;
				}
				// CHANCE OF A PERSON BEING INFECTED

				if (!flag[3]) {
					StdOut.println("Enter the chance of a person being Infected" 
				+ "(must be between 1-100): ");
					inf = StdIn.readInt();
					if (inf < 1 || inf > 100)
						throw new OutOfRangeException("The number must be between 1-100");
					flag[3] = true;
				}
				// ADVANCE SETTINGS

				if (!flag[4]) {
					StdOut.println("Would you like to open Advanced Settings? (Y/n) ");
					answer = StdIn.readString();
					if (!answer.equals("Y") && !answer.equals("n"))
						throw new IncorrectAnswerException("The answer must be either Y or n");
					flag[4] = true;
				}
				if (answer.equals("Y")) {
					StdOut.println("ADVANCED SETTINGS ");
					exit = false;

					// ADVANCED USER INPUTS
					newAnswer = "Y";
					sureExit = null;
					int num;
					do {
						StdOut.println("1. Time to Infect(time needed for a" +
					"person to stay in a block to infect it)");
						StdOut.println("2. Person to Person(chances of an" + 
					" infected person to infect another one)");
						StdOut.println("3. Floor to Person(chances of a " + 
					"person getting infected by an infected block)");
						StdOut.println("4. Person to Floor(chances of a " + 
					"person infecting a block)");
						StdOut.println("5. Max trace time(time that an" + 
					" infected block stays infected)");
						StdOut.println("6. Protection Level(protection level making it"+
					" harder for a self protected person to get infected)");
						StdOut.println("7. Exit");
						StdOut.println("\nChoose a number to continue..");
						num = StdIn.readInt();
						if (num < 1 || num > 7) {
							throw new OutOfRangeException("The number must be " + "between 1 and 7 inclusive");
						}
						switch (num) {
						case 1:
							StdOut.println("Set new Time to Infect: ");
							TTI = StdIn.readInt();
							if (TTI < 0)
								throw new NegativeNumberException
								("The time to " + "infect cannot be a negative number");
							break;
						case 2:
							StdOut.println("Set new Person to Person"
						+ "(must be between 1-100): ");
							PTP = StdIn.readInt();
							if (PTP < 1 || PTP > 100)
								throw new OutOfRangeException
								("The number must be between 1-100");
							break;
						case 3:
							StdOut.println("Set new Floor to Person"
						+ "(must be between 1-100): ");
							FTP = StdIn.readInt();
							if (FTP < 1 || FTP > 100)
								throw new OutOfRangeException
								("The number must be between 1-100");
							break;
						case 4:
							StdOut.println("Set new Person to Floor"
						+ "(must be between 1-100): ");
							PTF = StdIn.readInt();
							if (PTF < 1 || PTF > 100)
								throw new OutOfRangeException
								("The number must be between 1-100");
							break;
						case 5:
							StdOut.println("Set new Max trace time: ");
							MAXtrace = StdIn.readInt();
							if (MAXtrace < 0)
								throw new NegativeNumberException
								("The max trace time cannot be a negative number");
							break;
						case 6:
							StdOut.println("Set new Protection Level" 
						+ "(must be between 1-100): ");
							SP = StdIn.readInt();
							if (SP < 0)
								throw new NegativeNumberException
								("The number must be between 1-100");
							break;
						case 7:
							exit = true;
							break;

						}

						if (!exit) {
							StdOut.println("Would you like to change anything else? " + "(Y/n) ");
							newAnswer = StdIn.readString();
							if (!newAnswer.equals("Y") && !newAnswer.equals("n"))
								throw new IncorrectAnswerException
								("The answer must be either Y or n");

						} else {
							StdOut.println("Are you sure? (Y/n) ");
							sureExit = StdIn.readString();
							if (!sureExit.equals("Y") && !sureExit.equals("n"))
								throw new IncorrectAnswerException
								("The answer must be either Y or n");
							if (sureExit.equals("Y")) {
								exit = true;
							} else if (sureExit.equals("n")) {

								if (!newAnswer.equals("Y")) {

									exit = false;

								}

							}

						}
					} while (newAnswer.equals("Y") && !exit);

				}

				done1 = true;

			} catch (InputMismatchException e) {
				StdOut.println("The input you have entered doesn't match the " + "required type.\n ");
				done1 = false;
			} catch (Exception e) {
				StdOut.println("Wrong Input.Please try again!\n" + e.getMessage() + "\n");
				done1 = false;

			}

		}

		Grid G[] = null;
		ArrayList<Person> AL[] = null;
		boolean done2 = false;
		while (!done2) {
			try {

				if (!flag[5]) {

					StdOut.println("How many areas do you want? (1 to 10)");
					areas = StdIn.readInt();
					if (areas < 1 || areas > 10)
						throw new OutOfRangeException("Should be between 1-10 inclusive");
					flag[5] = true;

					G = new Grid[areas];
					AL = new ArrayList[areas];
				}
				for (int z = 1; z <= areas; z++) {

					StdOut.println("Enter the height of the simulation area no"
					+ z + " (must be between 5-50): ");
					height = StdIn.readInt();
					if (height < 5 || height > 50)
						throw new SimulationSizeException
						("The height of the " + "simulation  must be between 5-50.\n  ");

					StdOut.println("Enter the width of the  simulation area no" 
					+ z + " (must be between 5-50): ");
					width = StdIn.readInt();
					if (width < 5 || width > 50)
						throw new SimulationSizeException
						("The width of the simulation  must be between 5-50.\n  ");

					StdOut.println("Enter the number of people in the  simulation area no"
					+ z + "(can't be more than "
							+ (height * width) + "):");

					N = StdIn.readInt();

					totalPerson += N;

					if (N > height * width)
						throw new PeopleOverloadingException(
								"The number of people can't exceed the maximun capacity of the simulation.\n");

					borders = new Block[height][width];
					for (int h = 0; h < height; h++)
						for (int w = 0; w < width; w++)
							borders[h][w] = new Block(h, w, false);

					G[z - 1] = new Grid(height, width, MAXtrace, TTI, PTF, borders, z - 1);

					drawFrame(G[z - 1]);

					AL[z - 1] = new ArrayList<Person>();

					persons = new Person[N];
					for (int k = 0; k < N; k++) {
						persons[k] = new Person(G[z - 1], selfPr, imm, inf, TTI, PTP, FTP, SP);
						AL[z - 1].add(persons[k]);
					}

				}
				done2 = true;
			} catch (InputMismatchException e) {
				StdOut.println("The input you have entered doesn't match the " + "required type.\n ");
				done2 = false;
			} catch (Exception e) {
				StdOut.println("Wrong Input.PLease try again!\n" + e.getMessage() + "\n");
				done2 = false;
			}

		}

		for (int i = 1; i <= areas; i++) {
			int cnt = 0;
			boolean done3 = false, exit3 = false;
			String sureExit3 = null, newAnswer3 = "Y";

			boolean temp[][] = new boolean[height][width];
			for (int h = 0; h < height; h++)
				for (int w = 0; w < width; w++)
					temp[h][w] = false;

			if (areas > 1) {
				do {
					try {

						// NUMBER OF BORDER BLOCKS

						StdOut.println("Enter the number of border blocks in the area no"
						+ i + "(can't be more than "+ (2 * G[i - 1].getHeight() 
								+ 2 * G[i - 1].getWidth() - 4 - cnt) + " or less than 0):");
						numOfBorders = StdIn.readInt();

						if (numOfBorders > (2 * G[i - 1].getHeight() + 2 * G[i - 1].getWidth() - 4 - cnt)
								|| numOfBorders < 0)

							throw new BordersOutOfRangeException
							("The number of border blocks in this area cant be more than "
							+ (2 * G[i - 1].getHeight() + 2 * G[i - 1].getWidth() - 4 - cnt)
							+ " or less than 0.");
						
						done3 = true;
						if(numOfBorders > 0) {
							
						
						StdOut.println(
								"Enter the number of the area where the border blocks"
								+ " are connected to(can't be more than "
								+ areas + " and can't be " + i + ").");
						newGrid = StdIn.readInt();
						
						if (newGrid > areas || newGrid <= 0 || newGrid == i)
							throw new OutOfRangeException(
									"The number of the area where the border blocks "
									+ "are connected to cant be more than can't be more than "
											+ areas + " and can't be " + i + ".");

						// BORDER BLOCKS

						String bo;

						for (int j = 1; j <= numOfBorders; j++) {

							StdOut.println("Enter the coordinate of the border block no" + j
									+ " of this area(the correct form is x,y ).");

							do {
								bo = StdIn.readString();
							} while (!isValid(bo, height, width, temp));

							int xb = Integer.parseInt(bo.substring(0, bo.indexOf(',')));
							int yb = Integer.parseInt(bo.substring(bo.indexOf(',') + 1));
							cnt++;
							temp[xb][yb] = true;
							G[i - 1].getBorders()[xb][yb].setGrid(G[newGrid - 1]);
							G[i - 1].getBorders()[xb][yb].setBorder();
						}

						StdOut.println("Would you like to add more border blocks for a different area? " + "(Y/n) ");
						newAnswer3 = StdIn.readString();
						if (!newAnswer3.equals("Y") && !newAnswer3.equals("n"))
							throw new IncorrectAnswerException("The answer must be either Y or n");

						if (!newAnswer3.equals("Y")) {
							StdOut.println("Are you sure? (Y/n) ");
							sureExit3 = StdIn.readString();
							if (!sureExit3.equals("Y") && !sureExit3.equals("n"))
								throw new IncorrectAnswerException("The answer must be either Y or n");
							if (sureExit3.equals("Y")) {
								done3 = true;
							} else if (sureExit3.equals("n"))
								done3 = false;

						}
					}
						StdOut.println("Processing data..Please wait\n");

					} catch (InputMismatchException e) {
						StdOut.println("The input you have entered doesn't match the " + "required type.\n ");
						done3 = false;
					} catch (Exception e) {
						StdOut.println("Wrong Input.Please try again!\n" + e.getMessage() + "\n");
						done3 = false;
					}
				} while (!done3);

			}
		}
		// ARRAY FOR STATS

		int A[][] = new int[5][time];
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A[0].length; j++)
				A[i][j] = 0;

		StdOut.println("\nTHE SIMULATION HAS STARTED!\n");
		StdOut.println("PERSON COLORING:");
		StdOut.println("+ RED        --> INFECTED");
		StdOut.println("+ GREEN      --> IMMUNE");
		StdOut.println("+ BLUE       --> SELF PROTECTED");
		StdOut.println("+ LIGHT BLUE --> NORMAL");
		StdOut.println("\nBLOCK COLORING:");
		StdOut.println("+ WHITE      --> NOT INFECTED");
		StdOut.println("+ GRAY       --> INFECTED");
		StdOut.println("+ YELLOW     --> BORDER");

		try {
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		// SIMULATION PART
		ArrayList<Person> removed[] = new ArrayList[areas];
		ArrayList<Person> added[] = new ArrayList[areas];

		for (int i = 0; i < areas; i++) {
			removed[i] = new ArrayList<Person>();
			added[i] = new ArrayList<Person>();
		}

		for (int i = 0; i < time; i++) {
			
			for (int j = 0; j < areas; j++) {
				
				if(areas > 1)
				drawFrame(G[j]);
				
				G[j].showTrace();
				G[j].reduceTrace();
				

		
				for (Person p : AL[j]) {

					int cx = p.getCurrentX();
					int cy = p.getCurrentY();

					p.move();

					if (p.hasToMove()) {

						StdOut.println("*----------------------------------*");
						StdOut.println("|One person from grid " + (j + 1) + " has moved.     ");
						StdOut.print("|New Grid: grid ");
						p.setGrid(G[j].getBorders()[cx][cy].getGrid());

						StdOut.println(p.getGrid().getID() + 1);
						StdOut.println("*----------------------------------*");

						G[j].getBorders()[cx][cy].getGrid().placeRandom(p);
						StdOut.println();

						removed[G[j].getID()].add(p);
						added[G[j].getBorders()[cx][cy].getGrid().getID()].add(p);

				

					}
					p.resetToMove();

				}
				
			

				try {
					Thread.sleep(1500);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

			}

			for (int k = 0; k < areas; k++) {

				for (Person p : removed[k]) {
					if (p != null)
						AL[k].remove(p);
				}
				removed[k].clear();

				for (Person p : added[k]) {
					if (p != null)
						AL[k].add(p);
				}
				added[k].clear();

				AL[k].trimToSize();

			}
			for (int a = 0; a < areas; a++) {
				for (Person p : AL[a]) {
					if (p.getImmune())
						A[0][i]++;
					if (p.isInfected())
						A[1][i]++;
					if (p.getSelfProtected())
						A[2][i]++;
					if (!p.getImmune() && !p.isInfected() & !p.getSelfProtected())
						A[3][i]++;
					if (p.getSelfProtected() && p.isInfected())
						A[4][i]++;
				}
			}
			StdOut.println("\n*--------------------------------------------------*");
			if (i == 0)
				StdOut.println((i + 1) + " minute has passed.\n");
			else
				StdOut.println((i + 1) + " minutes have passed.\n");

			for (int k = 0; k < areas; k++)
				StdOut.println("Grid " + (G[k].getID() + 1) + " contains " + 
					G[k].getNumOfPerson() + " person.");

			StdOut.println("*--------------------------------------------------*\n");
		}

// CREATION OF SIMULATION GRAPH

		StdDraw.clear();
		StdDraw.setXscale(0, time);
		StdDraw.setYscale(0, totalPerson);

		int tempx = 0;
		int tempy = 0;

		// for immune
		StdDraw.setPenColor(StdDraw.GREEN);
		for (int i = 0; i < time; i++) {
			StdDraw.line(tempx, tempy, tempx + 1, A[0][i]);
			StdDraw.filledCircle(tempx + 1, A[0][i], 0.1);
			tempx++;
			tempy = A[0][i];
		}

		// for infected
		tempx = 0;
		tempy = 0;
		StdDraw.setPenColor(StdDraw.RED);
		for (int i = 0; i < time; i++) {
			StdDraw.line(tempx, tempy, tempx + 1, A[1][i]);
			StdDraw.filledCircle(tempx + 1, A[1][i], 0.1);
			tempx++;
			tempy = A[1][i];
		}

		// for self protected
		tempx = 0;
		tempy = 0;
		StdDraw.setPenColor(StdDraw.BLUE);
		for (int i = 0; i < time; i++) {
			StdDraw.line(tempx, tempy, tempx + 1, A[2][i]);
			StdDraw.filledCircle(tempx + 1, A[2][i], 0.1);
			tempx++;
			tempy = A[2][i];

		}

		// for normal
		tempx = 0;
		tempy = 0;
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		for (int i = 0; i < time; i++) {
			StdDraw.line(tempx, tempy, tempx + 1, A[3][i]);
			StdDraw.filledCircle(tempx + 1, A[3][i], 0.1);
			tempx++;
			tempy = A[3][i];
		}
		StdOut.println("\n\nRESULTS OF SIMULATION!\n");
		StdOut.println("+ Number of person for each category in the beginning"
				+ " of the simulation.\n");
		StdOut.println("+ IMMUNE are: " + A[0][0] + " out of " + totalPerson);
		StdOut.println("+ NORMAL are: " + A[3][0] + " out of " + totalPerson);
		StdOut.println("+ INFECTED are: " + A[1][0] + " out of " + totalPerson);
		StdOut.println("+ SELF PROTECTED are: " + A[2][0] + " out of " + totalPerson);

		StdOut.println();
		StdOut.println("+ Change in the number of person for each category during the simulation.\n");
		StdOut.println("+ INFECTED: from " + A[1][0] + " to " + A[1][time - 1]
				+ " with chances of getting infected from another person " + PTP 
				+ "% and from an infected block " + FTP+ "%");
		StdOut.println();
		StdOut.println("+ SELF PROTECTED that got infected: from " + A[4][0] + " to " + A[4][time - 1]
				+ " with chances of getting infected when protected -" + SP + "%");
		StdOut.println("	-SELF PROTECTED that got infected from another Person: " + Person.getSpfp());
		StdOut.println("	-SELF PROTECTED that got infected from an infected Block: " + Person.getSpff());
		StdOut.println();
		StdOut.println("+ NORMAL that got infected: " + (Person.getFp() + Person.getFf()));
		StdOut.println("	-NORMAL that got infected from another Person: " + Person.getFp());
		StdOut.println("	-NORMAL that got infected from an infected Block: " + Person.getFf());

		StdOut.println(
				"\nThe graph represents the change for each category of person "
				+ "during the time of the simulation.");

	}
}
