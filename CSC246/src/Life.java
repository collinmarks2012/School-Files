import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Life {
	public static int[][] grid;
	public static int[][] nextGenGrid;
	static Life MyLife = new Life();
	
	public static void main( String []args) {
		Scanner In  = null;
		try {
			In = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int M = In.nextInt();
		int N = In.nextInt();
		grid = new int[M][N];
		In.nextLine();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = In.nextInt();
			}
		}
		System.out.println("Starting State:");
		printGrid(grid);
		for (int casey = 0; casey < Integer.parseInt(args[1]); casey++) {
			nextGenGrid = new int[M][N];
			// Create M X N threads
			LifeThread [][] thr = new LifeThread[M][N];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					thr[i][j] = MyLife.new LifeThread(i,j);
				}
			}
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					thr[i][j].start();
					grid = nextGenGrid;
				}
			}
			//grid = nextGenGrid;
			System.out.println("Generation " + (casey+1));
			printGrid(grid);
			//Block until all threads are finished
			try{
				for (int i = 0; i < M; i++) {
					for (int j = 0; j < N; j++) {
						thr[i][j].join();
					}
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		
	}

	private static void printGrid(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	private class LifeThread extends Thread{

		private int i;
		private int j;
		public LifeThread(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		public void run() {
			int[] Neigh;
			if (i == 0 && j == 0) {
				Neigh = new int[3];
				Neigh[0] = grid[i+1][j];
				Neigh[1] = grid[i+1][j+1];
				Neigh[2] = grid[i][j+1];
			} else if (i == grid.length - 1 && j == 0) {
				Neigh = new int[3];
				Neigh[0] = grid[i][j+1];
				Neigh[1] = grid[i-1][j+1];
				Neigh[2] = grid[i-1][j];
			} else if (i == grid.length - 1 && j == grid[0].length - 1) {
				Neigh = new int[3];
				Neigh[0] = grid[i][j-1];
				Neigh[1] = grid[i-1][j-1];
				Neigh[2] = grid[i-1][j];
			} else if (i == 0 && j == grid[0].length - 1) {
				Neigh = new int[3];
				Neigh[0] = grid[i][j-1];
				Neigh[1] = grid[i+1][j-1];
				Neigh[2] = grid[i+1][j];
			} else if (i == 0) {
				Neigh = new int[5];
				Neigh[0] = grid[i][j-1];
				Neigh[1] = grid[i+1][j-1];
				Neigh[2] = grid[i+1][j];
				Neigh[3] = grid[i+1][j+1];
				Neigh[4] = grid[i][j+1];
			} else if (i == grid.length - 1) {
				Neigh = new int[5];
				Neigh[0] = grid[i][j-1];
				Neigh[1] = grid[i-1][j-1];
				Neigh[2] = grid[i-1][j];
				Neigh[3] = grid[i-1][j+1];
				Neigh[4] = grid[i][j+1];
			} else if (j == 0) {
				Neigh = new int[5];
				Neigh[0] = grid[i-1][j];
				Neigh[1] = grid[i-1][j+1];
				Neigh[2] = grid[i][j+1];
				Neigh[3] = grid[i+1][j+1];
				Neigh[4] = grid[i+1][j];
			} else if (j == grid.length - 1) {
				Neigh = new int[5];
				Neigh[0] = grid[i-1][j];
				Neigh[1] = grid[i-1][j-1];
				Neigh[2] = grid[i][j-1];
				Neigh[3] = grid[i+1][j-1];
				Neigh[4] = grid[i+1][j];
			} else {
				Neigh = new int[8];
				Neigh[0] = grid[i-1][j];
				Neigh[1] = grid[i+1][j];
				Neigh[2] = grid[i][j-1];
				Neigh[3] = grid[i][j+1];
				Neigh[4] = grid[i-1][j-1];
				Neigh[5] = grid[i-1][j+1];
				Neigh[6] = grid[i+1][j-1];
				Neigh[7] = grid[i+1][j=1];
			}
			int NumOnes = numOnes(Neigh);
			if (grid[i][j] == 1 && (NumOnes < 2 || NumOnes > 3)) {
				nextGenGrid[i][j] = 0;
			} else if (grid[i][j] == 0 && NumOnes == 3) {
				nextGenGrid[i][j] = 1;
			}
		}

		private int numOnes(int[] neigh) {
			int NumOnes = 0;
			for (int i = 0; i < neigh.length; i++) {
				if (neigh[i] == 1) {
					NumOnes++;
				}
			}
			return NumOnes;
		}
		
	}
}
