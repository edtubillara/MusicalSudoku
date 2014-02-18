package musicalsudoku.tests;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sudoku.solver.exactcover.ECMatrix;
import sudoku.solver.exactcover.ECReducable;
import sudoku.solver.sudokusolver.SudokuSolver;

/**
 * This class tests that solving the Sudoku puzzle step
 * by step works.
 * @author etubil2
 *
 */
public class TestReducableSolve {

	private short[][] easyPuzzle = {
			{6, 0, 0, 1, 0, 8, 2, 0, 3},
			{0, 2, 0, 0, 4, 0, 0, 9, 0},
			{8, 0, 3, 0, 0, 5, 4, 0, 0},
			{5, 0, 4, 6, 0, 7, 0, 0, 9},
			{0, 3, 0, 0, 0, 0, 0, 5, 0},
			{7, 0, 0, 8, 0, 3, 1, 0, 2},
			{0, 0, 1, 7, 0, 0, 9, 0, 6},
			{0, 8, 0, 0, 3, 0, 0, 2, 0},
			{3, 0, 2, 9, 0, 4, 0, 0, 5}
			};
	
	private static short[][] completeEasyPuzzle = {
		{6, 4, 5, 1, 9, 8, 2, 7, 3},
		{1, 2, 7, 3, 4, 6, 5, 9, 8},
		{8, 9, 3, 2, 7, 5, 4, 6, 1},
		{5, 1, 4, 6, 2, 7, 3, 8, 9},
		{2, 3, 8, 4, 1, 9, 6, 5, 7},
		{7, 6, 9, 8, 5, 3, 1, 4, 2},
		{4, 5, 1, 7, 8, 2, 9, 3, 6},
		{9, 8, 6, 5, 3, 1, 7, 2, 4},
		{3, 7, 2, 9, 6, 4, 8, 1, 5}
		};
	
	@Test
	public void test() {
		
		ECMatrix matrix = SudokuSolver._translateToECMatrix(easyPuzzle);
		ECReducable currPuzzle = new ECReducable(matrix);
		int[] result; 
		short[][] completeResult = new short[9][9];
		
		do{
			result = currPuzzle.reduce();
			if(result!=null){
				
				if(result[0]==69)
					break;
				
				int currY = result[0];
				int currX = result[1];
				completeResult[currY][currX]=(short) (result[2]+1);
			}
		}while(result!=null);
		
		
		for(int y =0; y < 9;y++){
			for(int x =0; x<9;x++){
				System.out.print(completeResult[y][x]+" ");
			}
			System.out.println();
		}
		
		checkResults(completeResult);
		
	}

	private void checkResults(short[][] completeResult) {
		
		for(int y =0; y < completeResult.length;y++){
			for(int x =0; x < completeResult[0].length;x++){
				
				int currComplete = completeResult[y][x];
				int currReal = completeEasyPuzzle[y][x];
		
				String wrongResultMessage = currComplete +
						" was supposed to be " + currReal + "@ Y:"+y+" X: " + x;
				
				assertTrue(wrongResultMessage,currComplete == currReal);
			}
			
		}
		
	}

}