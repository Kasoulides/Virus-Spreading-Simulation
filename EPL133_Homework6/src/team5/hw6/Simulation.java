package team5.hw6;

import edu.princeton.cs.introcs.*;

import java.util.*;

/**
 * This class represents a virus-spreading simulation
 * 
 * @author Christos Eleftheriou
 * @author Christos Kasoulides
 * 
 * @version 1.0
 * 
 * @since 14/04/20
 *
 */
public class Simulation {		//testing

	static void drawFrame(int h, int w) {

		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i <= w; i++)
			StdDraw.line(i, 0, i, h);
		for (int i = 0; i <= h; i++)
			StdDraw.line(0, i, w, i);
	}

	public static void main(String[] args)
			throws IncorrectAnswerException, OutOfRangeException, 
			PeopleOverloadingException, SimulationSizeException {
		int height = 0, width = 0, areas = 0, N = 0, time = 0, selfPr = 0, imm = 0, inf = 0,
				TTI = 2, PTP = 30, FTP = 30,MAXtrace = 2, PTF = 30, SP = 30;
		String answer, newAnswer, sureExit;
		boolean exit;

		// USER INPUTS
		boolean done = false;
		while (!done) {
			try {
				/*StdOut.println("Enter the height of the simulation block(must be between 5-100): ");
				height = StdIn.readInt();
				if (height < 5 || height > 100)
					throw new SimulationSizeException("The height of the "
							+ "simulation  must be between 5-100.\n  ");

				StdOut.println("Enter the width of the simulation block"
						+ "(must be between 5-100): ");
				width = StdIn.readInt();
				if (width < 5 || width > 100)
					throw new SimulationSizeException
					("The width of the simulation  must be between 5-100.\n  ");

				StdOut.println("Enter the number of people in the simulation: ");
				N = StdIn.readInt();
				if (N > height * width)
					throw new PeopleOverloadingException("The number of people "
							+ "can't exceed the maximun capacity of the simulation.\n");
*/
				StdOut.println("Enter the duration of the simulation(must be in "
						+ "minutes): ");
				time = StdIn.readInt();
				if (time < 0)
					throw new NegativeNumberException("The duration of the "
							+ "simulation cannot be a negative number:");

				StdOut.println("Enter the chance of a person being Self "
						+ "Protected(must be between 1-100): ");
				selfPr = StdIn.readInt();
				if (selfPr < 1 || selfPr > 100)
					throw new OutOfRangeException("The number must be between 1-100");

				StdOut.println("Enter the chance of a person being Immune"
						+ "(must be between 1-100): ");
				imm = StdIn.readInt();
				if (imm < 1 || imm > 100)
					throw new OutOfRangeException("The number must be between 1-100");

				StdOut.println("Enter the chance of a person being Infected"
						+ "(must be between 1-100): ");
				inf = StdIn.readInt();
				if (inf < 1 || inf > 100)
					throw new OutOfRangeException("The number must be between 1-100");

				StdOut.println("Would you like to open Advanced Settings? (Y/n) ");
				answer = StdIn.readString();
				if (!answer.equals("Y") && !answer.equals("n"))
					throw new IncorrectAnswerException("The answer must be either Y or n");

				if (answer.equals("Y")) {
					StdOut.println("ADVANCED SETTINGS ");
					exit = false;

					// ADVANCED USER INPUTS
					newAnswer = "Y";
					sureExit = null;
					int num;
					do {
						StdOut.println("1. Time to Infect(time needed for a"
								+ "person to stay in a block to infect it)");
						StdOut.println("2. Person to Person(chances of an"
								+ " infected person to infect another one)");
						StdOut.println("3. Floor to Person(chances of a "
								+ "person getting infected by an infected block)");
						StdOut.println("4. Person to Floor(chances of a "
								+ "person infecting a block)");
						StdOut.println("5. Max trace time(time that an"
								+ " infected block stays infected)");
						StdOut.println(
								"6. Protection Level(protection level making it"
								+ " harder for a self protected person to get infected)");
						StdOut.println("7. Exit");
						StdOut.println("\nChoose a number to continue..");
						num = StdIn.readInt();
						if (num < 1 || num > 7) {
							throw new OutOfRangeException("The number must be "
									+ "between 1 and 7 inclusive");
						}
						switch (num) {
						case 1:
							StdOut.println("Set new Time to Infect: ");
							TTI = StdIn.readInt();
							if (TTI < 0)
								throw new NegativeNumberException("The time to "
										+ "infect cannot be a negative number");
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
							StdOut.println("Would you like to change anything else? "
									+ "(Y/n) ");
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

				done = true;

			} catch (InputMismatchException e) {
				StdOut.println("The input you have entered doesn't match the "
						+ "required type.\n ");
				done = false;
			} catch (Exception e) {
				StdOut.println("Wrong Input.PLease try again!\n"
			+ e.getMessage() + "\n");
				done = false;

			}

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Grid G[];

		try {
			StdOut.println("How many areas do you want? (up to 4)");
			areas = StdIn.readInt();
			if (areas <= 0 || areas > 4)
				throw new OutOfRangeException("Should be between 1-4 inclusive");

			G = new Grid[areas];

			for (int z = 1; z <= areas; z++) {

				StdOut.println("Enter the height of the " + z + " simulation block(must be between 5-100): ");
				height = StdIn.readInt();
				if (height < 5 || height > 100)
					throw new SimulationSizeException("The height of the " + "simulation  must be between 5-100.\n  ");

				StdOut.println("Enter the width of the " + z + "  simulation block" + "(must be between 5-100): ");
				width = StdIn.readInt();
				if (width < 5 || width > 100)
					throw new SimulationSizeException("The width of the simulation  must be between 5-100.\n  ");

				StdOut.println("Enter the number of people in the " + z + "  simulation: ");
				N = StdIn.readInt();
				if (N > height * width)
					throw new PeopleOverloadingException(
							"The number of people " + "can't exceed the maximun capacity of the simulation.\n");

				boolean borders[][] = new boolean[height][width];
				int xb = 0;
				int yb = 0;
				StdOut.println("Enter the borders of this area, give negative number to stop");
				xb = StdIn.readInt();
				yb = StdIn.readInt();
				if (xb >= 0 && yb <= 0)
					borders[xb][yb] = true;
				while (xb >= 0 && yb >= 0) {
					xb = StdIn.readInt();
					yb = StdIn.readInt();
					if (xb >= 0 && yb <= 0)
						borders[xb][yb] = true;
				}

				int A[][] = new int[5][time];
				for (int i = 0; i < A.length; i++)
					for (int j = 0; j < A[0].length; j++)
						A[i][j] = 0;
				StdDraw.setXscale(0, width);
				StdDraw.setYscale(height, 0);

				drawFrame(height, width);

				Grid grid = new Grid(width, height, MAXtrace, TTI, PTF, borders, z);

				Person[] persons = new Person[N];

				StdOut.println("\nTHE SIMULATION HAS STARTED!\n");
				StdOut.println("PERSON COLORING:");
				StdOut.println("+ RED        --> INFECTED");
				StdOut.println("+ GREEN      --> IMMUNE");
				StdOut.println("+ BLUE       --> SELF PROTECTED");
				StdOut.println("+ LIGHT BLUE --> NORMAL");
				StdOut.println("\nBLOCK COLORING:");
				StdOut.println("+ WHITE      --> NOT INFECTED");
				StdOut.println("+ GRAY       --> INFECTED");

				for (int i = 0; i < N; i++) {
					persons[i] = new Person(grid, selfPr, imm, inf, TTI, PTP, FTP, SP);
				}

				for (int j = 0; j < time; j++) {
					grid.showTrace();
					grid.reduceTrace();

					drawFrame(height, width);

					for (int i = 0; i < N; i++) {

						persons[i].move();

						if (persons[i].getImmune())
							A[0][j]++;
						if (persons[i].isInfected())
							A[1][j]++;
						if (persons[i].getSelfProtected())
							A[2][j]++;
						if (!persons[i].getImmune() && !persons[i].isInfected() && !persons[i].getSelfProtected())
							A[3][j]++;

						if (persons[i].getSelfProtected() && persons[i].isInfected())
							A[4][j]++;

					}

					try {
						Thread.sleep(500);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}

				}
			}
		} catch (InputMismatchException e) {
			StdOut.println("The input you have entered doesn't match the " + "required type.\n ");
			done = false;
		} catch (Exception e) {
			StdOut.println("Wrong Input.PLease try again!\n" + e.getMessage() + "\n");
			done = false;

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		// SIMULATION 
		/*int A[][] = new int[5][time];
		for (int i = 0; i < A.length; i++)
			for (int j = 0; j < A[0].length; j++)
				A[i][j] = 0;

		StdDraw.setXscale(0, width);
		StdDraw.setYscale(height, 0);

		drawFrame(height, width);

		Grid grid = new Grid(width, height, MAXtrace, TTI, PTF);

		Person[] persons = new Person[N];

		StdOut.println("\nTHE SIMULATION HAS STARTED!\n");
		StdOut.println("PERSON COLORING:");
		StdOut.println("+ RED        --> INFECTED");
		StdOut.println("+ GREEN      --> IMMUNE");
		StdOut.println("+ BLUE       --> SELF PROTECTED");
		StdOut.println("+ LIGHT BLUE --> NORMAL");
		StdOut.println("\nBLOCK COLORING:");
		StdOut.println("+ WHITE      --> NOT INFECTED");
		StdOut.println("+ GRAY       --> INFECTED");

		for (int i = 0; i < N; i++) {
			persons[i] = new Person(grid, selfPr, imm, inf, TTI, PTP, FTP, SP);
		}

		for (int j = 0; j < time; j++) {
			grid.showTrace();
			grid.reduceTrace();

			drawFrame(height, width);

			for (int i = 0; i < N; i++) {

				persons[i].move();

				if (persons[i].getImmune())
					A[0][j]++;
				if (persons[i].isInfected())
					A[1][j]++;
				if (persons[i].getSelfProtected())
					A[2][j]++;
				if (!persons[i].getImmune() && !persons[i].isInfected() && 
						!persons[i].getSelfProtected())
					A[3][j]++;

				if (persons[i].getSelfProtected() && persons[i].isInfected())
					A[4][j]++;

			}
			
			  try {Thread.sleep(500);
			  } 
			  catch (InterruptedException ex) {
			  Thread.currentThread().interrupt(); 
			  }
			 

		}
*/
		// CREATION OF SIMULATION GRAPH
		StdDraw.clear();
		StdDraw.setXscale(0, time);
		StdDraw.setYscale(0, N);

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
		StdOut.println("+ IMMUNE are: " + A[0][0] + " out of " + N);
		StdOut.println("+ NORMAL are: " + A[3][0] + " out of " + N);
		StdOut.println("+ INFECTED are: " + A[1][0] + " out of " + N);
		StdOut.println("+ SELF PROTECTED are: " + A[2][0] + " out of " + N);

		StdOut.println();
		StdOut.println("+ INFECTED: from " + A[1][0] + " to " + A[1][time - 1] + 
				" with chances of getting infected from another person "+ PTP + 
				"% and from an infected block " + FTP + "%" );
		StdOut.println();
		StdOut.println("+ SELF PROTECTED that got infected: from "
				+ A[4][0] + " to " + A[4][time - 1]
				+ " with chances of getting infected when protected -" + SP + "%");
		StdOut.println("	-SELF PROTECTED that got infected from another Person: "
				+ Person.getSpfp());
		StdOut.println("	-SELF PROTECTED that got infected from an infected Block: "
				+ Person.getSpff());
		StdOut.println();
		StdOut.println("+ NORMAL that got infected: " + (Person.getFp() + Person.getFf()));
		StdOut.println("	-NORMAL that got infected from another Person: " + Person.getFp());
		StdOut.println("	-NORMAL that got infected from an infected Block: " + Person.getFf());
	

	}

}
