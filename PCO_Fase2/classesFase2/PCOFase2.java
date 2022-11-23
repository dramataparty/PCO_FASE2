import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class PCOFase2 {

	/**
	 * Tests whether two grids read from two files are valid.
	 * Creates a game with the valid grid, generates a piece
	 * for that game, and prints it in the standard output.
	 * 
	 * Then, asks the user the number of lines and columns and 
	 * initial difficulty desired for a new game and creates a second 
	 * game. Then it lets the user choose the number of permutations
	 * for for several randomly generated pieces, as well as the 
	 * column where to place them, until there is no more possibility
	 * of placing new pieces or the user exits the game.
	 * @param args Not used
	 */
	public static void main(String[] args) {
		// Este deve dar true
		Symbol[][] grid1 = readGridFromFile("/Users/diogoforte/Desktop/PCO_FASE2/PCO_Fase2/classesFase2/grid1.txt");
		System.out.println("Valid grid? " + Game.validGrid(grid1));
		// Este deve dar false
		System.out.println("Valid grid? " + 
                Game.validGrid(readGridFromFile("/Users/diogoforte/Desktop/PCO_FASE2/PCO_Fase2/classesFase2/grid2.txt")));

		Game g1 = new Game(grid1, new Random(2));
		g1.generatePiece();
		System.out.println(g1);

		// Let's play a new game, now 
		System.out.println("==========================");
		System.out.println("Let's play a new game, now");
		System.out.println("==========================");

		
		Scanner myReader = new Scanner(System.in);
		System.out.println("How many lines in grid?");
		int nrLines = myReader.nextInt();
		System.out.println("How many columns in grid?");
		int nrCols = myReader.nextInt();
		System.out.println("What difficulty level?");
		int diffic = myReader.nextInt();
		
		Game g = new Game(nrLines,nrCols,diffic,new Random(1));
		System.out.println();
		boolean wantToPlay = true;
		boolean canPlay = g.canPlay();

		while(wantToPlay && canPlay) {
			g.generatePiece();
			System.out.println(g);
			System.out.println("How many permutations in piece?");
			int permut = myReader.nextInt();
			g.permutatePiece(permut);
			System.out.println(g.currentPiece());
			int column;
			do {
				System.out.println("What is the target column? (1 to " + nrCols + ", -1 to exit)");
				column = myReader.nextInt();
			} while((column < 1 && column != -1) || 
					column > nrCols);
			if(column == -1) {
				wantToPlay = false;
			} else {
				if(!g.canPlayInColumn(column)){ 
					canPlay = false;
			    } else {           // Faz jogada
				    g.placePiece(column);
			    }
		    }
		}
		System.out.println(g.toString());

		if(!canPlay) {
			System.out.println("Not possible to play anymore");
		} else {
			System.out.println("See you soon!");
		}
		
		myReader.close();
	}
	
	/**
	 * Read a grid from a text file
	 * @param fileName The name of the file
	 * @return the grid read from the file, or null if some
	 *         problem occurred
	 */
	private static Symbol[][] readGridFromFile(String fileName) {
		Symbol[][] grid = null;
		String symbolTextValues = symbolTextValues();	
		try {
			Scanner leitor = new Scanner(new File(fileName));
			int nLins = leitor.nextInt();
			int nCols = leitor.nextInt();
			leitor.nextLine();
			grid = new Symbol[nLins][nCols];
			int l = 0;
			while(leitor.hasNextLine()) {
				String line = leitor.nextLine();
				for(int j = 0 ; j < nCols ; j++) {
					int index = symbolTextValues.indexOf(line.charAt(j));
					grid[l][j] = Symbol.values()[index];
				}
				l++;
			}
			leitor.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grid;		
	}

	/**
	 * The textual representation of every element of the Symbol
	 * enumerate type
	 * @return A string with the textual representations of all the
	 *         elements of Symbol
	 */
	private static String symbolTextValues() {
		StringBuilder sb = new StringBuilder();
		for(Symbol s : Symbol.values()) {
			sb.append(s.toString());
		}
		return sb.toString();
	}

}
