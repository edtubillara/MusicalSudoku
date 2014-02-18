package musicalsudoku.tests;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sudoku.solver.exactcover.ECMatrix;
import sudoku.solver.exactcover.ECUtil;

/**
 * Test cases for find the exact cover
 * @author etubil2
 *
 */
public class TestExactCover {
	
	
	/**
	 * This tests that solving exact cover with complete sets work.
	 */
	@Test
	public void testNoSolve() {
		
		short[][] nMatrix = { {1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
		
		String[] rowIds = {"correct1","correct2","correct3","correct4"};
		
		ECMatrix matrix = new ECMatrix(nMatrix,rowIds);

		ECUtil.findExactCover(matrix);
		
		String line = matrix.getResultString();
		
		System.out.println("TestNoSolve: "+line);
		
		assertTrue(line.contains("correct1"));
		assertTrue(line.contains("correct2"));
		assertTrue(line.contains("correct3"));
		assertTrue(line.contains("correct4"));
	}
	
	/**
	 * A simple solving test
	 */
	@Test
	public void testSolveSimple() {
		
		short[][] nMatrix = { {1,1,0},{1,0,1},{1,0,1},{0,0,1}};
		
		String[] rowIds = {"correct1","wrong1","wrong2","correct2"};
		
		ECMatrix matrix = new ECMatrix(nMatrix,rowIds);
		
		ECUtil.findExactCover(matrix);
		
		String line = matrix.getResultString();
	
		System.out.println("TestSimple: "+line);
		assertTrue(line.contains("correct1"));
		assertTrue(line.contains("correct2"));


	}

	/**
	 * This tests a more complicated test. This test is from an example in
	 * wikipedia: http://en.wikipedia.org/wiki/Knuth's_Algorithm_X
	 */
	@Test
	public void testSolveIntermediate(){
			short[][] nMatrix = { {1,0,0,1,0,0,1},
								  {1,0,0,1,0,0,0},
								  {0,0,0,1,1,0,1},
								  {0,0,1,0,1,1,0},
								  {0,1,1,0,0,1,1},
								  {0,1,0,0,0,0,1}};
			
			String[] rowIds = {"A","B","C","D","E","F"};
			
			ECMatrix matrix = new ECMatrix(nMatrix,rowIds);

			
 			ECUtil.findExactCover(matrix);
			
			String line = matrix.getResultString();
			System.out.println("TestIntermediate: "+line);
			
			assertTrue(line.contains("B"));
			assertTrue(line.contains("D"));
			assertTrue(line.contains("F"));
		}

}
