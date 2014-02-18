package sudoku.solver.exactcover;

import java.util.Stack;

/**
 * Class: ExactCoverUtil -Exact Cover Utility Class
 * 
 * @author etubil2
 * 
 *         This class implements Algorithm X/Dancing Links (by Donald Knuth) for
 *         the exact cover problem.
 * 
 *         It contains operations to perform on ECMatrix objects.
 */
public class ECUtil {

	/**
	 * This is the wrapper method that uses Algorithm X to find the rows which
	 * exact cover is satisfied.
	 * 
	 * @param matrix
	 * @return
	 */
	public static boolean findExactCover(ECMatrix matrix) {

		return findEC(matrix);
		
	}
	

	
	protected static int[] parseSolutionString(String solutionName) {
		String[] result = solutionName.split(",");
		
	
		int xCoord = Integer.parseInt(result[0]);
		int yCoord = Integer.parseInt(result[1]);
		int value = Integer.parseInt(result[2]);
		
		int[] retVal = new int[3];
		retVal[0]=yCoord;
		retVal[1]=xCoord;
		retVal[2]=value;
		return retVal;
	}

	/**
	 * Creates a node in the search tree (which is a stack)
	 * @param columnNode
	 * @param parent
	 * @param matrix
	 * @return
	 */
	public static Stack<ECNode> createNodeStack(ECNode columnNode,ECNode parent,ECMatrix matrix){
		Stack<ECNode> nodeStack = new Stack<ECNode>();
		ECNode currRowNode = columnNode.getDown();
		nodeStack.push(parent);
		nodeStack.push(columnNode);

		while (currRowNode != columnNode) {//add the possible rows to the stack
			nodeStack.push(currRowNode);
			currRowNode=currRowNode.getDown();
		}
		
		hideColumn(columnNode,matrix);
		 
		return nodeStack;
	
	}
	
	
	/**
	 * This is the Non-Recusrive Method that finds the exact cover
	 * It is faster than the recursive method
	 * @param matrix
	 * @return
	 */
	protected static boolean findEC(ECMatrix matrix){

		ECNode columnNode = matrix.getColumnNode();//get a column node
		Stack<ECNode> nodeStack = createNodeStack(columnNode,null,matrix);
		
		Stack<Stack<ECNode>> searchStack = new Stack<Stack<ECNode>>();
		searchStack.push(nodeStack);

	
		
		while(!searchStack.empty()){
			
			nodeStack = searchStack.peek();
				
				ECNode curr = nodeStack.pop();
				if(ECUtil.isColumnNode(curr)){
					
					showColumn(curr,matrix);
					curr = nodeStack.pop();
					
					if(curr!=null){
					restoreRowRec(curr,matrix);
					matrix.popExactCoverPartition();
					}else{
						break;
					}
					
					searchStack.pop();
				}else{

					// add row to solution
					String solutionName = curr.getId();
	
					matrix.pushExactCoverPartition(solutionName);
					
					eliminateRowRec(curr,matrix);
					columnNode = matrix.getColumnNode();
					
					if(columnNode == null)//solution was found
						return true;
					
						searchStack.push(createNodeStack(columnNode,curr,matrix));
				}
			
		}
		return false;
		
	}
	/**
	 * Checks if the node is a column node
	 * @param node
	 * @return
	 */
	protected static boolean isColumnNode(ECNode node){
		return node.getColumnHeader()==node;
	}
	
	
	/**
	 * The recursive main function that solves the exact cover problem
	 * @param matrix
	 * @param depth
	 * @return
	 */
	protected static int _findECRec(ECMatrix matrix, int depth) {

		ECNode columnNode = matrix.getColumnNode();//get a column node

		if (columnNode == null)// solution was found, matrix is empty
			return 1;

		ECNode currRowNode = columnNode.getDown();
		hideColumn(columnNode,matrix);

		while (currRowNode != columnNode) {//for every row that has 1 in this column do:

			// add row to solution
			String solutionName = currRowNode.getId();
			matrix.pushExactCoverPartition(solutionName);

			eliminateRowRec(currRowNode,matrix);

			int result = _findECRec(matrix, depth + 1); //--Recursive Call

			if (result == 1)
				return 1;

			// backtrack, we went wrong somewhere
			restoreRowRec(currRowNode,matrix);

			matrix.popExactCoverPartition();//remove the row that was added to the solution

			currRowNode = currRowNode.getDown();

		}
		showColumn(columnNode,matrix);
		return -1;// no solution at this branch
	}

	/**
	 * Given a row node, this function hides its columns because the this 
	 * row was added to the solution and it satisfies the column restrictions.
	 * @param ECNode RowNode
	 */
	protected static void eliminateRowRec(ECNode RowNode,ECMatrix matrix) {
		
		ECNode curr = RowNode.getRight();

		while (curr != RowNode) {
			ECNode currColumnNode = curr.getColumnHeader();
			hideColumn(currColumnNode,matrix);
			curr = curr.getRight();
		}

	}

	/**
	 * When backtracking, all the pointers changed have to be 
	 * restored
	 * @param RowNode
	 */
	protected static void restoreRowRec(ECNode RowNode,ECMatrix matrix) {

		ECNode curr = RowNode.getLeft();
		while (curr != RowNode) {
			ECNode currColumnNode = curr.getColumnHeader();
			showColumn(currColumnNode,matrix);
			curr = curr.getLeft();
		}


	}



	/**
	 * A restriction has been satisfied and so we have to hide 
	 * the column and the rows that satisfy the restriction too.
	 * @param columnNode
	 */
	public static void hideColumn(ECNode columnNode,ECMatrix matrix) {
		// hide the column from its column neighbors
		ECNode left = columnNode.getLeft();
		ECNode right = columnNode.getRight();
		matrix.removeColumn(columnNode);
		left.setRight(right);
		right.setLeft(left);
		

		// remove the rows that have this column as 1
		ECNode currRowNode = columnNode.getDown();

		while (currRowNode != columnNode) {

			ECNode nextRowNode = currRowNode.getDown();

			// hide the row node from its up&down neighbors
			ECNode rowElem = currRowNode.getLeft();
			while (rowElem != currRowNode) {
				ECNode cc = currRowNode.getColumnHeader();
				matrix.updateColumnDecrease(cc);
				ECNode up = rowElem.getUp();
				ECNode down = rowElem.getDown();
				up.setDown(down);
				down.setUp(up);

				rowElem = rowElem.getLeft();

			}

			currRowNode = nextRowNode;
		}
	}
	
	/**
	 * When backtracking the changed pointers have to be restored
	 * @param columnNode
	 */
	protected static void showColumn(ECNode columnNode,ECMatrix matrix) {

		ECNode currRowNode = columnNode.getUp();
		while (currRowNode != columnNode) {

			// hide the row from its up&down neighbors
			ECNode rowElem = currRowNode.getRight();
			while (rowElem != currRowNode) {
				
				ECNode cc = currRowNode.getColumnHeader();
				matrix.updateColumnIncrease(cc);
				ECNode up = rowElem.getUp();
				ECNode down = rowElem.getDown();
				up.setDown(rowElem);
				down.setUp(rowElem);

				rowElem = rowElem.getRight();
			}

			currRowNode = currRowNode.getUp();
		}

		// show the column from its column neighbors
		ECNode left = columnNode.getLeft();
		ECNode right = columnNode.getRight();
		left.setRight(columnNode);
		right.setLeft(columnNode);
		matrix.addColumn(columnNode);

	}
	

}