package sudoku.solver.exactcover;

import java.util.Stack;

/**
 * Class ECReducable
 * @author etubil2
 *
 * This makes an object that solves the exact cover
 * step by step. 
 *
 */
public class ECReducable extends ECUtil {
	
	private ECNode columnNode;
	private ECMatrix matrix;
	
	private Stack<ECNode> nodeStack;
	private Stack<Stack<ECNode>> searchStack;
	private Stack<int[]> backtrackedNodes;
	boolean finished;
	/**
	 * Constructor
	 * @param ECMatrix matrix
	 */
	public ECReducable(ECMatrix matrix){
		this.matrix = matrix;
		columnNode = matrix.getColumnNode();//get a column node
		nodeStack = ECUtil.createNodeStack(columnNode,null,matrix);
		
		searchStack = new Stack<Stack<ECNode>>();
		searchStack.push(nodeStack);
	}
	
	/**
	 * Reduces the ECMatrix by solving one step
	 * @return Sudoku parameters
	 */
	public int[] reduce(){
		if(finished)
			return null;
		backtrackedNodes = new Stack<int[]>();
		
		while(!searchStack.empty()){
			
			nodeStack = searchStack.peek();
			ECNode curr = nodeStack.pop();
				
				if(isColumnNode(curr)){	
					
					showColumn(curr,matrix);
					curr = nodeStack.pop();
					if(curr!=null){
						
					restoreRowRec(curr,matrix);
					addBackTrackNodes(curr);
					matrix.popExactCoverPartition();
					}else break;
					
					searchStack.pop();
					
				}else
					return trySolution(curr);
			
		}
		return null;
	}

	private void addBackTrackNodes(ECNode curr) {
		String backTrackId = curr.getId();
		int[] backTrackValues = parseSolutionString(backTrackId);
		backtrackedNodes.add(backTrackValues);
	}

	public Stack<int[]> getBackTrackedNumbers(){
		return this.backtrackedNodes;
	}
	
	/**
	 * Try adding a row to the solution
	 * @param curr
	 * @return
	 */
	private int[] trySolution(ECNode curr) {
		// add row to solution
		String solutionName = curr.getId();
		matrix.pushExactCoverPartition(solutionName);
		
		eliminateRowRec(curr,matrix);
		columnNode = matrix.getColumnNode();
		
		//solution was found
		if(columnNode == null)
			finished = true;
		else
			searchStack.push(createNodeStack(columnNode,curr,matrix));

			int[] retVal = parseSolutionString(solutionName);
			return retVal;
		
	}
}
