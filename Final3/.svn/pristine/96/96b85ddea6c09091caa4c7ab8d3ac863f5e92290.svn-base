package sudoku.solver.exactcover;
/**
 * ECMatrix Row Class -Exact Cover Matrix Row Class
 * @author etubil2
 * 
 * This class is a container of nodes for a Exact Cover Matrix.
 */
public class ECMatrixRow {
	
	private int length; 
	private ECNode[] nodeList;

	/**
	 * Constructor - if restrictionSize is less than 0 it will be 0.
	 * @param int restrictionSize -length of row
	 */
	public ECMatrixRow(int length){
		if(length<0)
			length = 0;
		
		this.length = length;
		nodeList = new ECNode[length];
	}

	/**
	 * Returns the length of this row.
	 * @return
	 */
	public int getLength(){
		return this.length;
	}
	
	/**
	 * Returns the node at index i. Invalid indices will cause the method
	 * to return null.
	 * @param int i -
	 * @return ECNode
	 */
	public ECNode at(int i){
		if(i<0 || i>this.length)
			return null;
		else 
			return this.nodeList[i];
	}
	
	/**
	 * Sets the node at the given index
	 * @param insertNode
	 * @param i
	 */
	public void setNodeAt(int i,String id){
		if(i>=0&&i<this.length){
			this.nodeList[i] = new ECNode(id);
		}
	}
	
	public void connectRow(){
		for(int i = 1; i < this.getLength(); i++){
			
		}
	}
}
