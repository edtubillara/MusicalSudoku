package sudoku.solver.exactcover;

/**
 * Class: ECNode - Exact Cover Node
 * @author etubil2
 *
 * This class represents a 1 in the Exact Cover matrix. Or it is 
 * used as a sentinel node/header node for the column.
 */
public class ECNode {
	
	private String id;//the solution id is usually stored here
	private int headerLength;
	private String debugID;
	
	private ECNode columnHeader;
	
	private ECNode up;
	private ECNode down;
	private ECNode left;
	private ECNode right;
	
	/**
	 * Constructor -set id and neighbors to itself
	 * @param String id
	 */
	public ECNode(String id){
		this.id = id;
		up = this;
		down = this;
		left = this;
		right = this;
		columnHeader = this;
		
	}

	/**
	 * Constructor -set id and neighbors
	 * @param String id 
	 * @param ECNode up
	 * @param ECNode down
	 * @param ECNode left
	 * @param ECNode right
	 */
	public ECNode(String id, ECNode up, ECNode down, ECNode left, ECNode right){
		
		this.id = id;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		
	}
	
	public void increaseLength(){
		this.headerLength++;
	}
	public void decreaseLength(){
		this.headerLength--;
	}
	public int getLength(){
		return this.headerLength;
	}
	
	/**
	 * Sets the up neighbor
	 * @param ECNode up
	 */
	public void setUp(ECNode up){
		this.up = up;
	}
	
	
	/**
	 * Sets the down neighbor
	 * @param ECNode down
	 */
	public void setDown(ECNode down){
		this.down = down;
	}
	
	
	/**
	 * Sets the left neighbor
	 * @param ECNode left
	 */	
	public void setLeft(ECNode left){
		this.left = left;
	}
	
	/**
	 * Sets the right neighbor
	 * @param ECNode right
	 */
	public void setRight(ECNode right){
		this.right = right;
	}
	
	
	/**
	 * Gets the up neighbor
	 */
	public ECNode getUp(){
		return this.up;
	}
	
	
	/**
	 * Gets the down neighbor
	 */
	public ECNode getDown(){
		return this.down;
	}
	
	
	/**
	 * Gets the left neighbor
	 */	
	public ECNode getLeft(){
		return this.left;
	}
	
	/**
	 * Gets the right neighbor
	 */
	public ECNode getRight(){
		return this.right;
	}
	
	/**
	 * Gets the Node's column header node
	 */
	public ECNode getColumnHeader(){
		return this.columnHeader;
	}
	
	/**
	 * Sets the column header node for this node
	 * @param columnHeader
	 */
	public void setColumnHeader(ECNode columnHeader){
		this.columnHeader = columnHeader;
	}
	
	/**
	 * Gets the Node's id
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * Sets the Node's debug id
	 * @param debugID
	 */
	public void setDebugId(String debugID){
		this.debugID = debugID;
	}
	
	/**
	 * Gets the Node's debug id
	 * @return String debug id
	 */
	public String getDebugId(){
		return this.debugID;
	}
}
