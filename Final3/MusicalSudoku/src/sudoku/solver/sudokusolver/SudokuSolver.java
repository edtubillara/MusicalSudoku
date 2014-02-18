package sudoku.solver.sudokusolver;

import sudoku.solver.exactcover.ECMatrix;
import sudoku.solver.exactcover.ECUtil;

public class SudokuSolver {
	
	/**
	 * main function to call to solve puzzle
	 * @param short [][] puzzle
	 * @return true on success false on failure
	 */
	public static short[][] solvePuzzle(short[][] puzzle) {

		ECMatrix matrix = _translateToECMatrix(puzzle);
		
		boolean status = ECUtil.findExactCover(matrix);
		
		System.out.println("result is "+ status );
		short[][] result = translateResult(matrix.getExactCoverPartition());
		
		return result;
	}

	/**
	 * takes a short[][] type of matrix to make the ECMatrix for solving
	 * @param short[][] puzzle
	 * @return ECMatrix matrix
	 */
	public static ECMatrix _translateToECMatrix(short[][] puzzle) {
		
		int nonEmpty = countNonEmptyBoxes(puzzle);
		
		SudokuModel model = new SudokuModel(puzzle);
		
		int columnLength = nonEmpty+((81-nonEmpty)*9);
		short[][] spareMatrix = new short[columnLength][324];
		String[] rowIds = new String[columnLength];

		int rowNumber = 0;
		
		
		for(int y = 0; y < puzzle.length; y++){
			for(int x = 0; x < puzzle[0].length;x++){
				
				
				short puzzleValue = (short) (puzzle[y][x]-(1));

				if(puzzleValue==-1){
				
					rowNumber = _addPossibleValues(rowNumber,y,x,spareMatrix,rowIds,model);
					
				}else{
					rowNumber = _addRestrictions(rowNumber,y,x,puzzleValue,spareMatrix,rowIds);
				}
				
			}
		}
		ECMatrix retVal = new ECMatrix(spareMatrix,rowIds);
		return retVal;
	}

	/**
	 * Adds and sets the row in the sparse matrix, this is an internal function -made
	 * public for testing
	 * @param int rowNumber
	 * @param int y
	 * @param int x
	 * @param short puzzleValue
	 * @param short[][] spareMatrix
	 * @param String[] rowIds
	 * @return int rowNumber -next row number
	 */
	public static int _addRestrictions(int rowNumber, int y, int x, short puzzleValue,
			short[][] spareMatrix,String[] rowIds) {
		
		spareMatrix[rowNumber][ boxNumber(y,x,puzzleValue) ]=1;
		spareMatrix[rowNumber][ RowNumber(y,puzzleValue) ]=1;
		spareMatrix[rowNumber][ ColumnNumber(x,puzzleValue) ]=1;
		spareMatrix[rowNumber][ CoordinateFilled(y,x) ]=1;
		rowIds[rowNumber]=x+","+y+","+puzzleValue;
		
		return rowNumber+1;
	}

	/**
	 * Returns the array element location to be filled for 
	 * the restriction that every box in the puzzle needs to
	 * be filled.
	 * @param y
	 * @param x
	 * @return int row location
	 */
	private static int CoordinateFilled(int y, int x) {
		int retVal = y*9+x;
		return retVal;
	}

	/**
	 * Returns the array element location to be filled for
	 * the restriction that every column in the puzzle needs
	 * to have 1-9.
	 * @param int x
	 * @param short puzzleValue
	 * @return int row location
	 */
	private static int ColumnNumber(int x, short puzzleValue) {
		int retVal = 81+x*9+puzzleValue;
		return retVal;
	}

	/**
	 * Returns the array element location to be filled for
	 * the restriction that every row in the puzzle needs
	 * to have 1-9.
	 * @param int x
	 * @param short puzzleValue
	 * @return int row location
	 */
	private static int RowNumber(int y, short puzzleValue) {
		int retVal = 162 +y*9+puzzleValue;
		return retVal;
	}

	/**
	 * Returns the array element location to be filled for
	 * the restriction that every section/box in the puzzle needs
	 * to have 1-9.
	 * @param int x
	 * @param short puzzleValue
	 * @return int row location
	 */
	private static int boxNumber(int y, int x, short puzzleValue) {
		int boxNumber;
		if(x<3 && y<3)
			boxNumber = 0;
		else if(x<6 && y<3)
			boxNumber = 1;
		else if(y<3)
			boxNumber = 2;
		else if(x<3 && y < 6 )
			boxNumber = 3;
		else if(x<6 && y < 6 )
			boxNumber = 4;
		else if(y < 6 )
			boxNumber = 5;
		else if(x < 3 )
			boxNumber = 6;
		else if(x < 6 )
			boxNumber = 7;
		else if(x < 9 )
			boxNumber = 8;
		else boxNumber = -1;
		
		int retVal = 243 + boxNumber * 9+puzzleValue;
		return retVal;
	}

	/**
	 * For empty squares, this function adds the possible values as rows in the sparse
	 * matrix
	 * @param int rowNumber
	 * @param int y
	 * @param int x
	 * @param short[][] spareMatrix
	 * @param String[] rowIds
	 * @return next row location (int)
	 */
	private static int _addPossibleValues(int rowNumber, int y, int x, short[][] spareMatrix,String[] rowIds, SudokuModel model) {

		for(short i = 0; i <9; i++){
			if(!model.restrictionIsSatisfied(y, x, i+1))
			rowNumber = _addRestrictions(rowNumber,y,x,i,spareMatrix,rowIds);
		}
		
		return rowNumber;
	}

	/**
	 * Counts the number of non-empty locations in the puzzle
	 * @param short[][] puzzle
	 * @return int count
	 */
	private static int countNonEmptyBoxes(short[][] puzzle) {
		int count = 0;
		for(int y = 0; y < puzzle.length; y++){
			for(int x = 0; x < puzzle[0].length;x++){
				
				if(puzzle[y][x]!=0)
					count++;
			
			}
		}
		return count;
	}

	/**
	 * After solving the puzzle this function is called to get the results
	 * and put it into a short[][] solved variable
	 * @param String[] exactCoverPartition
	 * @return short[][] solved puzzle
	 */
	private static short[][] translateResult(String[] exactCoverPartition) {
		
		short[][] retVal = new short[9][9];
		
		for(int i =0; i < exactCoverPartition.length;i++){

			String[] result = exactCoverPartition[i].split(",");
			int xCoord = Integer.parseInt(result[0]);
			int yCoord = Integer.parseInt(result[1]);
			short value = Short.parseShort(result[2]);
			
			retVal[yCoord][xCoord] = (short)(value+1);
			
		}
		
		return retVal;
	}

	/**
	 * Used for debugging purposes. This function was useless.
	 * @param matrix
	 */
	public static void printSM(short[][] matrix){
		for(int y =0;y<matrix.length;y++){
			for(int x =0; x < matrix[0].length; x++){
				System.out.print(matrix[y][x]+" ");
			}
			System.out.println();
		}
	}
	
}