package musicalsudoku.tests;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import sudoku.solver.sudokusolver.SudokuSolver;


/**
 * Test Cases for the Sudoku Solver
 * @author etubil2
 *
 *The sample puzzles that I took from can be found at puzzles.about.com
 */
public class SudokuSolverTests {
		
	
	//sample puzzles, unsolved and solved
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
	
	private static 	

	short[][] mediumPuzzle = {
		{9, 0, 0, 0, 0, 0, 2, 0, 0},
		{8, 0, 0, 2, 9, 0, 0, 0, 0},
		{2, 0, 5, 8, 0, 0, 0, 9, 0},
		{0, 7, 0, 0, 8, 9, 0, 0, 6},
		{0, 0, 0, 6, 0, 2, 0, 0, 0},
		{6, 0, 0, 7, 1, 0, 0, 5, 0},
		{0, 5, 0, 0, 0, 3, 7, 0, 4},
		{0, 0, 0, 0, 4, 8, 0, 0, 5},
		{0, 0, 6, 0, 0, 0, 0, 0, 3}
	};
	
	
	
	private static short[][] completeMediumPuzzle = {
			{9, 6, 1, 4, 7, 5, 2, 3, 8},
			{8, 3, 7, 2, 9, 6, 5, 4, 1},
			{2, 4, 5, 8, 3, 1, 6, 9, 7},
			{5, 7, 2, 3, 8, 9, 4, 1, 6},
			{3, 1, 4, 6, 5, 2, 8, 7, 9},
			{6, 8, 9, 7, 1, 4, 3, 5, 2},
			{1, 5, 8, 9, 6, 3, 7, 2, 4},
			{7, 2, 3, 1, 4, 8, 9, 6, 5},
			{4, 9, 6, 5, 2, 7, 1, 8, 3}
		};


	private static short[][] hardPuzzle= {
		{4, 0, 0, 6, 0, 0, 0, 9, 0},
		{6, 0, 7, 0, 0, 0, 0, 0, 0},
		{0, 3, 0, 0, 0, 5, 0, 7, 0},
		{0, 8, 0, 4, 7, 0, 9, 0, 0},
		{0, 0, 0, 5, 0, 1, 0, 0, 0},
		{0, 0, 4, 0, 8, 2, 0, 5, 0},
		{0, 7, 0, 1, 0, 0, 0, 3, 0},
		{0, 0, 0, 0, 0, 0, 6, 0, 5},
		{0, 9, 0, 0, 0, 8, 0, 0, 2}
		};


	
	private static short[][] completeHardPuzzle= {
			{4, 5, 8, 6, 1, 7, 2, 9, 3},
			{6, 1, 7, 2, 9, 3, 5, 8, 4},
			{9, 3, 2, 8, 4, 5, 1, 7, 6},
			{3, 8, 5, 4, 7, 6, 9, 2, 1},
			{7, 2, 9, 5, 3, 1, 4, 6, 8},
			{1, 6, 4, 9, 8, 2, 3, 5, 7},
			{2, 7, 6, 1, 5, 4, 8, 3, 9},
			{8, 4, 3, 7, 2, 9, 6, 1, 5},
			{5, 9, 1, 3, 6, 8, 7, 4, 2}
			};

	private static short[][] blankPuzzle= {
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			};
	
	
	/**
	 * Tests for the cases where the Sudoku puzzle is already all filled in
	 */
	@Test
	public void testNoSolve() {
		
		
			short[][] result = SudokuSolver.solvePuzzle(completeEasyPuzzle);
						
			for(int y =0; y < result.length;y++){
				for(int x =0; x < result[0].length;x++){
					assertTrue(completeEasyPuzzle[y][x]==result[y][x]);
				}
			}
			
			
	}
	
	
	/**
	 * Tests for solving a puzzle with 36 filled in numbers.
	 */
	@Test
	public void testSolveEasy() {

			long start = System.currentTimeMillis();    
			short[][] result = SudokuSolver.solvePuzzle(easyPuzzle);
			long elapsedTime = System.currentTimeMillis() - start;
			
			
			for(int y =0; y < result.length;y++){
				for(int x =0; x < result[0].length;x++){
					assertTrue(result[y][x]==completeEasyPuzzle[y][x]);
				}
				
			}

			System.out.println("elapsed time for -easy- puzzle solving:" + elapsedTime +" milliseconds");
			
	}

	/**
	 * Tests for solving a puzzle with about 27 filled in numbers
	 */
	@Test
	public void testMedium() {

		long start = System.currentTimeMillis();    
		short[][] result = SudokuSolver.solvePuzzle(mediumPuzzle);
		long elapsedTime = System.currentTimeMillis() - start;
		
		for(int y =0; y < result.length;y++){
			for(int x =0; x < result[0].length;x++){
				assertTrue(result[y][x]==completeMediumPuzzle[y][x]);
			}
			
		}
		System.out.println("elapsed time for -medium- puzzle solving:" + elapsedTime +" milliseconds");
	}

	/**
	 * Tests for solving a puzzle with about 26 filled in numbers
	 */
	@Test
	public void testHard() {

		long start = System.currentTimeMillis();    
		short[][] result = SudokuSolver.solvePuzzle(hardPuzzle);
		long elapsedTime = System.currentTimeMillis() - start;
		
		for(int y =0; y < result.length;y++){
			for(int x =0; x < result[0].length;x++){
				assertTrue(result[y][x]==completeHardPuzzle[y][x]);
			}
			
		}
		System.out.println("elapsed time for -hard- puzzle solving:" + elapsedTime +" milliseconds");
	}
	
	/**
	 * Tests for a solving an empty puzzle
	 */
	@Test
	public void testBlank() {

		long start = System.currentTimeMillis();    
		short[][] result = SudokuSolver.solvePuzzle(blankPuzzle);
		long elapsedTime = System.currentTimeMillis() - start;
		
		
		Map<Integer,Integer> counter = new HashMap<Integer,Integer>();
		
		//map and count all the results
		for(int y =0; y < result.length;y++){
			for(int x =0; x < result[0].length;x++){

				int resultValue = result[y][x];

				if(counter.containsKey(resultValue)){
		
					int currCount = counter.get(resultValue);
					currCount++;
					counter.put(resultValue, currCount);
				}else{
					
					counter.put(resultValue, 1);
					
				}
			}
			
		}
		
		for(int i =1; i <= 9; i++){
			int histogramOfCurr = counter.get(i);
			assertTrue("Count was incorrect: " + histogramOfCurr + "is not 9 for i = "+ i,histogramOfCurr == 9);
		}
		
		System.out.println("elapsed time for -blank- puzzle solving:" + elapsedTime +" milliseconds");
	}

	

	/**
	 * Tests that inserting a possible number is correct in the sparse matrix row
	 * with a value of 1.
	 */
	@Test
	public void testAddingRowsOrigin(){
		short[][] spareMatrix = new short[1][324]; 
		String []rowIds = {"row1"};
		SudokuSolver._addRestrictions(0, 0, 0, (short)0, spareMatrix, rowIds);
		
		assertTrue(spareMatrix[0][0]==1);
		assertTrue(spareMatrix[0][81]==1);
		assertTrue(spareMatrix[0][162]==1);
		assertTrue(spareMatrix[0][243]==1);
	}
	
	/**
	 * Tests that inserting a possible number is correct in the sparse matrix row
	 * with a value of 3.
	 */
	@Test
	public void testAddingRowsOrigin2(){
		short[][] spareMatrix = new short[1][324]; 
		String []rowIds = {"row1"};
		SudokuSolver._addRestrictions(0, 0, 0, (short)2, spareMatrix, rowIds);
		
		assertTrue(spareMatrix[0][0]==1);
		assertTrue(spareMatrix[0][83]==1);
		assertTrue(spareMatrix[0][164]==1);
		assertTrue(spareMatrix[0][245]==1);
	}
	
	
	
	
	
}