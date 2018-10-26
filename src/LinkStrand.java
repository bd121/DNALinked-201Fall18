import java.util.*;

public class LinkStrand implements IDnaStrand{
 
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
	
	private class Node {
	   	String info;
	   	Node next;
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	   }
	   private Node myFirst,myLast;
	   private long mySize;
	   private int myAppends;

	
	public LinkStrand (String s) {
		initialize(s);
		System.out.print("1");
	}

	public LinkStrand () {
		this("");
	}
	
	//gives the number of bp in a strand referenced by single node
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
	}

	@Override
	public void initialize(String source) {
		myFirst = new Node(source); 
		myLast = myFirst;
		myAppends = 0;
		mySize = source.length();
		System.out.println("2");
		// TODO Auto-generated method stub
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
		
	}

	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return new LinkedStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize += dna.length();
		myAppends += 1;
		System.out.println("3");
		return this;
		//return myFirst;
	}

	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		Node current = myFirst; 
		LinkedStrand b = null;
		while (current != null) {
			StringBuilder copy = new StringBuilder(current.info);
			copy.reverse();
			String currentCopy = copy.toString();
			
			if (current == myFirst) {
				b = new LinkedStrand(currentCopy);				
			}
			else {
				b.append(currentCopy);
			}
			current = current.next;
		}
		return b;
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		
		//total index
		//index in current node
		if (index > mySize || index < 0) {
			throw new IndexOutOfBoundsException("index beyond the bounds");
		}
		
		if(index >= myIndex) {
			
			while (myIndex != index) {
				myIndex++;
				myLocalIndex++;
				if (myLocalIndex >= myCurrent.info.length()) {
					myLocalIndex = 0;
					myCurrent = myCurrent.next;
				}
			}
			
		} else {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
			
			while (myIndex != index) {
				myIndex++;
				myLocalIndex++;
				if (myLocalIndex >= myCurrent.info.length()) {
					myLocalIndex = 0;
					myCurrent = myCurrent.next;
				}
			}	
			
		}
		
		return myCurrent.info.charAt(myLocalIndex);
	}

		 //one for the current node in a sequence of calls of charAt, one for the current index into that node,
		 //and one for the overall count, then these instance variables can be updated on each call to charAt
		 //to save the current “progress” we have reached, so that on a subsequent call it will be possible to
		 //continue from there and access the next character in a node, for example, by simply incrementing the index into that node. 
		//You'll need to initialize these instance variables when a strand is created, e.g., in method initialize.
		 //myIndex - value of the parameter in the last call to charAt.
		 //myLocalIndex - the value of the index within the string stored in the node last-referenced by charAt when the method finishes
		 //myCurrent - the node of the internal list referenced in the last call to charAt
		
		
		
		
		//return 0;
	
	
	public String toString() {
		Node current = myFirst;
		StringBuilder stringCopy = new StringBuilder("");
		while (current != null) {
			stringCopy.append(current.info);
			current = current.next;
		}
		return stringCopy.toString();
	}
}

