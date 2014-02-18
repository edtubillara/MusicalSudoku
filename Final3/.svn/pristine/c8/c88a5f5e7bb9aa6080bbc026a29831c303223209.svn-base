package sudoku.solver.sudokusolver;


/**
 * SudokuModel Class
 * @author etubil2
 *
 * This class contains the Sudoku matrix
 * as the user inputs the numbers. It calls
 * the appropriate methods to solve the matrix
 *
 */
public class SudokuModel {
	short[][] matrix;
	/**
	 * Constructor
	 */
	public SudokuModel(){
		this.matrix = new short[9][9];
	}
	/**
	 * Constructor
	 * -not a deep copy
	 */
	public SudokuModel(short[][] matrix){
		this.matrix = matrix;
	}
	/**
	 * Solve the current matrix state
	 * -Called by do in background
	 * @return true on success false on failure
	 */
	public void solve(){
		this.matrix = SudokuSolver.solvePuzzle(this.getMatrix());
	}
	
	/**
	 * Sets a value at y,x a value
	 * 
	 * @param int y -Y Coordinate
	 * @param int x -X Coordinate
	 * @param short value -Value to insert
	 * 
	 * @return true on success false on failure
	 * 
	 */
	public synchronized boolean setAt(int y, int x, short value){
		if(!_checkBoundsGood(y, x))
			return false;
		
		if(value == 0){
			matrix[y][x]=0;
			return true;
		}
		if(_allFree(y,x,value)){
			matrix[y][x] = value;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks if the coordinates are within the matrix
	 * @param y 
	 * @param x
	 * @return true if good false if bad
	 */
	private boolean _checkBoundsGood(int y, int x) {
		if(x<0||x>8)
			return false;
		else if(y<0||y>8)
			return false;
		else 
			return true;
		
	}
	
	/**
	 * Checks if the given coordinate is okay to insert
	 * depending if the current rows, column, and box sections
	 * are free of that number.
	 * @return true if no duplicate exists in the rows, column, box.
	 */
	private boolean _allFree(int y, int x, short value) {
		
		if(!_columnFree(y,x,value))
			return false;
		
		if(!_rowFree(y,x,value))
			return false;
		
		if(!_boxFree(y,x,value))
			return false;
		
		return true;
	}

	/**
	 * Checks if the row does not have the number
	 * @return true if row free
	 */
	private boolean _rowFree(int targetY, int targetX, short value) {
		
		for(int x = 0; x < 9; x++)
		{
			if(x!=targetX){
				if(matrix[targetY][x]==value)
					return false;
			}
		}
		
		return true;
	}

	/**
	 * Checks the box section does not have the number
	 * @return true if no duplicate in box
	 */
	private boolean _boxFree(int targetY, int targetX, short value) {
		
		boolean retVal = true;
		//only have to check 4 cells after checking rows and column
		int xCoord1 = (targetX + 1) % 3 + 3 * (targetX / 3);
		int xCoord2 = (targetX + 2) % 3 + 3 * (targetX / 3);
		int yCoord1 = (targetY + 1) % 3 + 3 * (targetY / 3);
		int yCoord2 = (targetY + 2) % 3 + 3 * (targetY / 3);
		
		retVal &= matrix[yCoord1][xCoord1] != value;
		retVal &= matrix[yCoord1][xCoord2] != value;
		retVal &= matrix[yCoord2][xCoord1] != value;
		retVal &= matrix[yCoord2][xCoord2] != value;
		
		return retVal;
	}

	/**
	 *  Checks the column for any duplicates
	 *  @return true if no duplicate in column
	 */
	private boolean _columnFree(int targetY, int targetX, short value) {
		for(int y = 0; y < 9; y++)
		{
			if(y!=targetY){
				if(matrix[y][targetX]==value)
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Gets the value of the Sudoku matrix at the given coordinates
	 * @param y -Y Coordinate
	 * @param x -X Coordinate
	 * @return
	 */
	public short getAt(int y, int x){
		if(!_checkBoundsGood(y,x))
			return -1;
		return matrix[y][x];
	}
	
	/**
	 * returns the short[][] matrix
	 */
	public short[][] getMatrix(){
		return this.matrix;
	}

	public boolean restrictionIsSatisfied(int y, int x, int value){
		return !_allFree(y, x, (short) value);

	}
	
}


