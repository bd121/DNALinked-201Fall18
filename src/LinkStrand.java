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

	/**
	 * Creates a strand representing s. No checking is done to verify s
	 * is valid DNA data.
	 * 
	 * @param s is the source of cgat data for this strand
	 */	
	public LinkStrand (String s) {
		initialize(s);
		System.out.print("1");
	}
	/**
	 * Creates an empty strand.
	 */
	public LinkStrand () {
		this("");
	}
	
	/**
	 * @return the number of base pairs in this strand
	 */	
	@Override
	public long size() {
		return mySize;
	}
	
	/**
	 * Creates the first node to the LinkStrand and instantiates all relevant variables. 
	 * 
	 * @param is the source for the dna sequence to store in the first node.
	 */
	@Override
	public void initialize(String source) {
		myFirst = new Node(source); 
		myLast = myFirst;
				
		myAppends = 0;
		mySize = source.length();
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
		
	}

	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return new LinkStrand(source);
	}

	/**
	 * Simply append a strand of dna data to this strand. No error checking is
	 * done. This method adds a new node to the end of a the linked list.
	 * 
	 * @param dna is the String appended to this strand
	 */
	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		myLast.next = new Node(dna);
		myLast = myLast.next;
		mySize += dna.length();
		myAppends += 1;
		return this;
	}

	/**
	 * Reverses the strand's sequence.
	 * 
	 * @return the new reversed strand 
	 */ 	
	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		Node current = myFirst;
		StringBuilder copy = new StringBuilder(current.info);
		copy.reverse();
		String currentCopy = copy.toString();
		LinkStrand b = new LinkStrand(currentCopy);
		current = current.next;
		
		while (current != null) {
			copy = new StringBuilder(current.info);
			copy.reverse();
			currentCopy = copy.toString();
			Node bnew = new Node(currentCopy);
			bnew.next = b.myFirst;
			b.myFirst = bnew;
			b.mySize += copy.length();
			current = current.next;	
		}


		return b;
	}

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}
	
	/**
	 * Checks to see what character is at an index in the LinkStrand. It is efficient because it saves the previous
	 * index and if possible, counts forward from that index to the new index.
	 * @param is the index to look for in the list
	 * @return is the character found at the index passed in in parameter 
	 */
	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
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

