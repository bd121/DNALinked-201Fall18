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
	   private Node myFirst,myLast; //first;
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
		//first = 
				
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
		return new LinkStrand(source);
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
		Node first = myFirst;
		LinkStrand b = null;
		while (current != null) {
			StringBuilder copy = new StringBuilder(current.info);
			copy.reverse();
			String currentCopy = copy.toString();
			
			if (current == first) {
				b = new LinkStrand(currentCopy);
				mySize += current.info.length();
				
			}
			else {
				Node bnew = new Node(currentCopy);
				bnew.next = b.myFirst;
				b.myFirst = bnew;
				mySize += current.info.length();
				//b.append(currentCopy);
			//b.myFirst = currentCopy;
			//b.append
			//node holding currentCopy
			//currentCopy point to b.myFirst
			//b.myFirst = currentCopy;
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
		if (index >= mySize || index < 0) {
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

