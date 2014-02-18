package musicalsudoku.tests;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import sudoku.solver.exactcover.ECColumnPrioQue;
import sudoku.solver.exactcover.ECNode;

public class TestECColumnPrioQue {

	ECColumnPrioQue pq;

	
	private ECNode insertNode(String title, int value){
		ECNode node = new ECNode(title);
		pq.insert(node, value);
		return node;
	}
	
	@Before
	public void setUp() throws Exception {
		pq = new ECColumnPrioQue(2);
	}

	@Test
	public void testEmptyNoFail() {
		assertTrue(pq.peek()==null);
		assertTrue(pq.pop()==null);
		assertTrue(pq.pop()==null);
		assertTrue(pq.getSize()==0);
	}

	@Test
	public void testInsert1elem(){
		insertNode("node",0);
		ECNode peekNode = pq.peek();
		assertTrue(peekNode.getId().equals("node"));
		assertTrue(pq.getSize()==1);
		
		peekNode = pq.pop();
		assertTrue(pq.getSize()==0);
		assertTrue(peekNode.getId().equals("node"));
		
	}
	@Test
	public void testInsert2elem(){
		insertNode("node2",2);
		insertNode("node1",1);
		
		ECNode peekNode = pq.peek();
		assertTrue(peekNode.getId().equals("node1"));
		pq.pop();
		
		peekNode = pq.peek();
		assertTrue(peekNode.getId().equals("node2"));
		pq.pop();
		
		assertTrue(pq.peek()==null);
	}
	
	@Test
	public void testInsert3elem(){
		insertNode("node1",1);
		insertNode("node3",3);
		insertNode("node2",2);
		assertTrue(pq.pop().getId().equals("node1"));
		assertTrue(pq.pop().getId().equals("node2"));
		assertTrue(pq.pop().getId().equals("node3"));
		assertTrue(pq.pop()==null);
	}
	@Test
	public void testInsert100InReverse(){
		for(int i=100; i >= 0; i--){
			String nodeName = "node"+i;
			insertNode(nodeName,i);
		}
		for(int i=0;i<100;i++){
			ECNode popNode = pq.pop();
			String nodeID = popNode.getId();
			assertTrue("Error@insert10@"+i,nodeID.equals("node"+i));
		}
		
	}
	
	@Test
	public void testUpdateKey1(){
	
		ECNode node1 = insertNode("node10",10);
		insertNode("node33",33);
		insertNode("node34",34);
		pq.updateKey(node1, 54);
		
		assertTrue(pq.pop().getId().equals("node33"));
		assertTrue(pq.pop().getId().equals("node34"));
		assertTrue(pq.pop().getId().equals("node10"));		
		
	}
	@Test
	public void testUpdateKey2(){
		
		ECNode node1 = insertNode("node10",10);
		insertNode("node33",33);
		ECNode node2 = insertNode("node34",34);
		
		assertTrue(pq.peek().getId().equals("node10"));
		pq.updateKey(node1, 50);
		assertTrue(pq.peek().getId().equals("node33"));
		pq.updateKey(node2, 1);
		assertTrue(pq.peek().getId().equals("node34"));
		pq.updateKey(node1, 0);
		assertTrue(pq.peek().getId().equals("node10"));
	}
	
	@Test
	public void testUpdateAllKeys(){
		int SIZE = 100000;
		ECNode[] collection = new ECNode[SIZE];
		//insert a 100000 nodes in decreasing order
		for(int i = SIZE-1; i >= 0; i--){
			String nodeName = "node"+(SIZE-1-i);
			collection[SIZE-1-i]=insertNode(nodeName,i);
		}
		//update each key according to the index of collection
		for(int i=0; i < SIZE; i++){
			pq.updateKey(collection[i], i);
		}
		//make sure each node popped in the correct order
		for(int i=0; i < SIZE; i++){
			String popId = pq.pop().getId(); 
			String solutionString = "node"+i;
			String errorMessage = "id@ "+i+" is "+popId+" should be "+solutionString;
			assertTrue(errorMessage,popId.equals(solutionString));
		}
	}
	@Test
	public void testRemoveAt(){
		insertNode("node1",1);
		insertNode("node2",2);
		insertNode("node3",3);
		
		String removeID = pq._removeAt(2).getId();
		assertTrue(removeID.equals("node2"));
		assertTrue(pq.pop().getId().equals("node1"));
		assertTrue(pq.pop().getId().equals("node3"));
		
	}
}
