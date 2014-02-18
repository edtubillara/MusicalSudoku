package sudoku.solver.exactcover;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * ECMatrix Class -Exact Cover Matrix Class
 * 
 * @author etubil2
 * 
 *         This class' objects represent a matrix where the 
 *         columns represent restrictions and the rows represent a set.
 */
public class ECMatrix {
	
	private int rowLength;//how many columns
	private int columnLength;//how many rows
	
	private ECNode columnRoot;
	private ECNode[] columnNodes;
	
	private int[] columnSizes;
	private String[] rowIds;
	
	private Stack<String> exactCoverPartition;//the solution
	private ECColumnPrioQue pq1;

	
	/**
	 * Constructor, build a Exact Cover matrix
	 * @param short[][] matrix 
	 * @param String[] rowIds -will be used in the exact cover
	 * 
	 */
	public ECMatrix(short[][] matrix, String[] rowIds) {
		
		initializeVariables(matrix, rowIds);
		
		_createColumnNodes(rowLength);
		
		putMatrix(matrix);
	}


	/**
	 * initializes the variables
	 * @param short[][] matrix
	 * @param String[]  rowIds
	 */
	private void initializeVariables(short[][] matrix, String[] rowIds) {
		this.pq1 = new ECColumnPrioQue(1024);
		this.columnLength = matrix.length;
		this.rowLength = matrix[0].length;
		
		this.rowIds = new String[rowIds.length];
		
		for(int i =0; i < rowIds.length; i++){
			if(rowIds[i]==null)break;
			this.rowIds[i]= new String(rowIds[i]);
		}
		
		this.exactCoverPartition = new Stack<String>();
	}

	
	
	/**
	 * Initializes the column nodes/headers - makes a torus
	 * @param int restrictionSize -length of column 
	 */
	private void _createColumnNodes(int restrictionSize) {
		
		this.columnNodes = new ECNode[restrictionSize];
		this.columnSizes = new int[restrictionSize];
		
			
			
			columnNodes[0] = new ECNode("col0");
			columnNodes[0].setUp(columnNodes[0]);
			columnNodes[0].setDown(columnNodes[0]);
			
			columnRoot = new ECNode("root");
			columnRoot.setRight(columnNodes[0]);
			columnNodes[0].setLeft(this.columnRoot);
			
			for (int i = 1; i < restrictionSize; i++) {
				
				columnNodes[i] = new ECNode("col" + i);
				ECNode currColumnNode = columnNodes[i];
				currColumnNode.setLeft(columnNodes[i-1]);
				columnNodes[i-1].setRight(currColumnNode);
			
			}
			
			columnNodes[restrictionSize-1].setRight(this.columnRoot);
			
			columnRoot.setLeft(columnNodes[restrictionSize-1]);
		}

	
	/**
	 * Gets a column node that is not in the solution
	 * returns null if the matrix is empty(exact cover was found).
	 * @return ECNode column
	 */
	public ECNode getColumnNode(){
			ECNode node =  pq1.peek();			
			return node;
	}
	
	
	public boolean removeColumn(ECNode column){
		return pq1.remove(column);
	}
	public void addColumn(ECNode column){
		pq1.insert(column,column.getLength());
	}
	 
	/**
	 * Adds a row to the matrix. If the length of the ECMatrixRow is not the
	 * same as the matrix's restriction size or if the given row is null,
	 *  then it will be ignored 
	 * @param ECMatrixRow row
	 */
	public void addRow(ECMatrixRow row){
		//ignore cases
		if(row == null)
			return;
		
		if(row.getLength() != this.rowLength)
			return;
		
		//insert the nodes/1's
		ECNode currRowNode;
		for(int i =0; i < rowLength; i++){
			currRowNode = row.at(i);
			
			if(currRowNode != null){
				
				this.columnSizes[i]++;
				ECNode columnHeader = this.columnNodes[i];
				this.columnRoot = columnHeader;
				ECNode downNode = columnHeader.getDown();
				currRowNode.setColumnHeader(columnHeader);
				
				downNode.setUp(currRowNode);
				columnHeader.setDown(currRowNode);
				currRowNode.setUp(columnHeader);
				currRowNode.setDown(downNode);
			}
		}
	
	
	}

	/**
	 * Gets the restriction size
	 */
	public int getRestrictionSize(){
		return this.rowLength;
	}
	
	/**
	 * Sets its linked lists components according to a short[][] matrix. The
	 * short[][] matrix is now converted to a torus of linked nodes so that
	 * the matrix will be sparse and storage will be reduced to O(n) for the
	 * computations with DLX
	 * @param matrix
	 */
	private void putMatrix(short[][] matrix){
		
		for(int y = 0; y < this.columnLength; y++){
			
			ECNode tempNodeRoot = new ECNode("tempRowRoot");
			ECNode curr = tempNodeRoot;
			for(int x = 0; x < this.rowLength; x++){
				if(matrix[y][x]!=0){
					ECNode latestNode = new ECNode(this.rowIds[y]);
					latestNode.setDebugId("("+y+","+x+")");
					
					
					curr.setRight(latestNode);
					latestNode.setLeft(curr);
					
					ECNode currentColumnHeader = this.columnNodes[x];
					currentColumnHeader.increaseLength();
			
					
					ECNode oldDownNode = currentColumnHeader.getDown();
					
					latestNode.setUp(currentColumnHeader);
					latestNode.setDown(oldDownNode);
					latestNode.setColumnHeader(currentColumnHeader);
					oldDownNode.setUp(latestNode);
					currentColumnHeader.setDown(latestNode);
				 
					columnSizes[x]++;
					
					curr = latestNode;
				}
			}
			
			ECNode firstNode = tempNodeRoot.getRight();
			ECNode lastNode = curr;
			
			lastNode.setRight(firstNode);
			firstNode.setLeft(lastNode);
			
			
		}
		for(int i =0; i < this.columnNodes.length;i++){
			ECNode insertNode =this.columnNodes[i]; 
			this.pq1.insert(insertNode,insertNode.getLength());
		}
	}
	
	/**
	 * Prints out the matrix by iteration of the columns per line.
	 */
	public void print(){
		System.out.println("Columns");
		for(int y = 0;y<this.rowLength;y++){
			
			ECNode column = this.columnNodes[y];
			ECNode curr= column.getDown();
			System.out.print("Column " + y + ": ");
			while(curr!=column){
				System.out.print("< "+curr.getId()+" "+curr.getDebugId()+" >");
				curr = curr.getDown();
			}
			System.out.println();
		}
	}
	
	/**
	 * Adds a solution
	 * @param partitionID
	 */
	public void pushExactCoverPartition(String partitionID){
		this.exactCoverPartition.push(partitionID);
	}
	/**
	 * Removes the latest solution added
	 * @return String solution
	 */
	public String popExactCoverPartition(){
		return this.exactCoverPartition.pop();
	}
	
	/**
	 * Returns a list of the row ids that were included
	 * in the solution
	 * @return String[]
	 */
	public String[] getExactCoverPartition(){
		int partitionSize = this.exactCoverPartition.size();
		String[] retVal = new String[partitionSize];
		Iterator<String> ecpIter = this.exactCoverPartition.iterator();
		for(int rvIndex = 0; rvIndex < partitionSize; rvIndex++){
			retVal[rvIndex]= ecpIter.next();
		}
		return retVal;
	}
	
	/**
	 * Returns a string that appends all the row IDs that are 
	 * in the current solution
	 * @return String[] resultString
	 */
	public String getResultString(){
		String [] result = this.getExactCoverPartition();
		String retVal = "";
		for(int rIndex = 0; rIndex < result.length; rIndex++){
			retVal+=result[rIndex];
		}
		return retVal;
	}
	
	/**
	 * Returns the string of row ids that were passed into this object
	 * when it was created.
	 * @return String[] rowIds
	 */
	public String[] getRowIds(){
		return this.rowIds;
	}
	
	/**
	 * Updates the column node's priority by decreasing 
	 */
	public void updateColumnDecrease(ECNode column){
		column.decreaseLength();
		this.pq1.updateKey(column, column.getLength());
	}
	/**
	 * Updates the column node's priority by increasing 
	 */
	public void updateColumnIncrease(ECNode column){
		column.increaseLength();
		this.pq1.updateKey(column, column.getLength());
	}
	
}
