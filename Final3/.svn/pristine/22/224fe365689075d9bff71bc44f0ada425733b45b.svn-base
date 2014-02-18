package sudoku.solver.exactcover;
import java.util.HashMap;


/**
 * Custom Priority Queue for column nodes
 * @author etubil2
 * This class has update key
 */
public class ECColumnPrioQue {

	private HashMap<ECNode,Integer> columnLocMap;
	private ECNode[] heap;
	private int[] columnSizes;
	private int heapSize;
	int maxSize;

	/**
	 * Constructor
	 * @param startSize -what size you want to start with
	 */
	public ECColumnPrioQue(int startSize) {
		if (startSize < 2)
			startSize = 2;
		
		this.heap = new ECNode[startSize];
		this.columnSizes = new int[startSize];
		this.maxSize = startSize;
		this.columnLocMap = new HashMap<ECNode,Integer>();
		this.heapSize=1;
	}

	/**
	 * Insert a column node
	 * @param insertNode -column node
	 * @param key -what key it is associated with
	 */
	public void insert(ECNode insertNode, int key) {
		if (heapSize == maxSize)
			_resize();
		heap[heapSize] = insertNode;
		this.columnSizes[heapSize] = key;
		columnLocMap.put(insertNode,heapSize);
		heapSize++;
		_heapifyUp(heapSize - 1);

	}

	/**
	 * return and remove the first element
	 * @return ECNode
	 */
	public ECNode pop() {
		ECNode retVal = this._removeAt(1);
		return retVal;
	}

	/**
	 * return the first element
	 * @return ECNOde
	 */
	public ECNode peek() {
		if(heapSize==1)
			return null;
		else
			return heap[1];
	}

	/**
	 * grab the size of the heap
	 * @return
	 */
	public int getSize() {
		return this.heapSize -1;
	}

	
	/**
	 * Removes the object
	 * returns true if an element existed
	 * @param node
	 * @return
	 */
	public boolean remove(ECNode node){
		if(!this.columnLocMap.containsKey(node)){
			return false;
		} else {
			
			int removeLoc = this.columnLocMap.get(node);
			_removeAt(removeLoc);
			return true;
		}
	}
	
	/**
	 * remove and return the ECNode at the given index of the heap
	 * @param removeIndex
	 * @return
	 */
	public ECNode _removeAt(int removeIndex){
		if(removeIndex<0||removeIndex>=heapSize)
			return null;
		 ECNode retVal = heap[removeIndex];
		  this._swap(removeIndex,heapSize-1);

		  (heapSize)--;
		  this._heapifyDown(removeIndex);
		  this.columnLocMap.remove(retVal);
		  return retVal;
	}
	
	/**
	 * Update a ECNode and it's key in the priority queue
	 * @param node
	 * @param key
	 */
	public void updateKey(ECNode node, int key) {
		if(!this.columnLocMap.containsKey(node)){
			return;
		}
		
		int nodeLoc = this.columnLocMap.get(node);
		this.columnSizes[nodeLoc]=key;		
		
		int parentLoc = nodeLoc/2;
		int compareWithParent = this.compare(nodeLoc, parentLoc);
		int compareWithChild=0;
		
		int minChildLoc;
		if(2*nodeLoc<heapSize){
			minChildLoc = this._getMinChild(nodeLoc);
			compareWithChild = this.compare(minChildLoc, nodeLoc);
		}else
		minChildLoc = heapSize;
		
		if(parentLoc>0&&compareWithParent<0){
			_heapifyUp(nodeLoc);
		} else if(minChildLoc<heapSize&&compareWithChild<0){
			_heapifyDown(nodeLoc);
		}
		
	}

	/**
	 * Compares two elements
	 * @param loc1
	 * @param loc2
	 * @return value of loc1 - value of loc 2
	 */
	public int compare(int loc1, int loc2) {
		int retVal = columnSizes[loc1] - columnSizes[loc2];
		return retVal;
	}

	/**
	 * updates the heap going up
	 * @param currIndex
	 */
	private void _heapifyUp(int currIndex) {

		while (currIndex > 1) {
			int parentIndex = (currIndex) / 2;
			int comparedResult = compare(currIndex, parentIndex);

			if (comparedResult < 0) {
				_swap(currIndex, parentIndex);
			} else {
				break;
			}
			currIndex = parentIndex;
		}

	}

	/**
	 * updates the heap going down
	 * @param currIndex
	 */
	private void _heapifyDown(int currIndex) {
		  while( 2*currIndex < this.heapSize )
		    {
		    
			  int nMinChild = this._getMinChild(currIndex);
		      if(this.compare(nMinChild, currIndex)<0)
		      	this._swap(currIndex,nMinChild);
		      else 
		    	  break;
			
		      currIndex=nMinChild;
		    }
	}

	/**
	 * returns the location of the child with the lowest value 
	 * @param currIndex -parent
	 * @return
	 */
	private int _getMinChild(int currIndex) {
		int leftChildLoc = 2 * (currIndex);
		int rightChildLoc = 2 * (currIndex) + 1;

		if (leftChildLoc < heapSize && rightChildLoc >= heapSize) {
			return leftChildLoc;
		} else {

			if (this.compare(rightChildLoc, leftChildLoc) < 0)
				return rightChildLoc;
			else
				return leftChildLoc;

		}
	}

	/**
	 * Swaps elements and updates them 
	 * @param loc1
	 * @param loc2
	 */
	private void _swap(int loc1, int loc2) {
		ECNode tempNode = heap[loc1];
		heap[loc1] = heap[loc2];
		heap[loc2] = tempNode;
		
		int tempLoc = columnSizes[loc1];
		columnSizes[loc1] = columnSizes[loc2];
		columnSizes[loc2] = tempLoc;
		
		this.columnLocMap.put(heap[loc1], loc1);
		this.columnLocMap.put(heap[loc2], loc2);
		
	}

	/**
	 * resizes the arrays
	 */
	private void _resize() {

		ECNode[] tempHeap = this.heap;
		int[] tempKey = this.columnSizes;
		int nextSize = this.maxSize * 2;
		this.heap = new ECNode[nextSize];
		this.columnSizes = new int[nextSize];
		copyHeap(this.heap, tempHeap);
		copyColumnSizes(this.columnSizes, tempKey);
		this.maxSize = nextSize;
	}

	private void copyHeap(ECNode[] copyTo, ECNode[] copyFrom) {
		for (int i = 0; i < copyFrom.length; i++) {
			copyTo[i] = copyFrom[i];
		}
	}

	private void copyColumnSizes(int[] copyTo, int[] copyFrom) {
		for (int i = 0; i < copyFrom.length; i++) {
			copyTo[i] = copyFrom[i];
		}
	}
}
