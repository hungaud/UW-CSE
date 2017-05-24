// Hung Auduong
// 
// CSE
// 
// Assignment #4
//

import java.util.*;



public class ChainingHash {
	
	private int size;
	private Node[] hashTable;
	private int count;
	private Node nextKey = null;
	
	private class Node {
		String key;
		int value;
		//Node Nodes;
		Node next;
		
		private Node(String key, int value) {
			this.key = key;
			this.value = value;
			next = null;
		}
		
		private void chaining(Node n) {
			next = n;
			
		}
		
		private String getKey() {
			return key;
		}
		private int getValue() {
			return value;
		}
		private void value(int value) {
			this.value = value;
		}
		
		private Node getNext() {
			return next;
		}
	}
	/* big array and each index is a hash code. 
	 * element should be a linked list of a map map. key is the the string word and value is the number of word appeared
	 * should probably create a linked list class kind of like record class on last homework assignment
	 * 
	 */
	
	public ChainingHash() {
			//TODO Implement a default constructor for ChainingHash
		size = 10061;
		count = 0;
		hashTable = new Node[size];
		for (int i = 0; i < size; i++) {
			hashTable[i] = null;
		}
	}
		
	public ChainingHash(int startSize) {
			//TODO Implement a constructor that instantializes the hash array to startSize.
		size = startSize;
		count = 0;
		hashTable = new Node[size];
		for (int i = 0; i < size; i++) {
			hashTable[i] = null;
		}
	}
	private void create (int size) {
		hashTable = new Node[size];
		for (int i = 0; i < size; i++) {
			hashTable[i] = null;
		}
	}

		/**
		 * This function allows rudimentary iteration through the ChainingHash.
		 * The ordering is not important so long as all added elements are returned only once.
		 * It should return null once it has gone through all elements
		 * @return Returns the next element of the hash table. Returns null if it is at its end.
		 */
	public String getNextKey() {
			//TODO returns the next key in the hash table.
			//You will need external tracking variables to account for this.
		
		if (count >= size) {
			return null;
		} else {
			if (hashTable[count] == null) {
				while (hashTable[count] == null) {
					count++;
					if (count >= size) {
						return null;
					}
				}
				nextKey = hashTable[count];
			} else if (count == 0 && hashTable != null) {
            nextKey = hashTable[count];
         }
			String key = nextKey.getKey();
			if (nextKey.getNext() != null) {
				nextKey = nextKey.getNext();	
			} else {
				count++;
				nextKey = hashTable[count];
			}
			return key;
		}
	}
		/**
		 * Adds the key to the hash table.
		 * If there is a collision, it should be dealt with by chaining the keys together.
		 * If the key is already in the hash table, it increments that key's counter.
		 * @param keyToAdd : the key which will be added to the hash table
		 */
	public void insert(String keyToAdd){
			//TODO Implement insert into the hash table.
			//If keyToAdd is already in the hash table, then increment its count.
		int hash = Math.abs(keyToAdd.hashCode() % size);
  
		if (hashTable[hash] == null) {
			hashTable[hash] = new Node(keyToAdd, 1);
		} else {
			Node addKey = hashTable[hash];
			while (addKey.getNext() != null && !addKey.getKey().equals(keyToAdd)) {
				addKey = addKey.getNext();
			} 
			if (addKey.getKey().equals(keyToAdd)) {
				int value = addKey.getValue();
				addKey.value(value + 1);
			} else {
				addKey.chaining(new Node(keyToAdd, 1));
			}
		}// this or dont even use a 2nd node and add the key to the end of the chain
	}

		/**
		 * Returns the number of times a key has been added to the hash table.
		 * @param keyToFind : The key being searched for
		 * @return returns the number of times that key has been added.
		 */
	public int findCount(String keyToFind){
			//TODO Implement findCount such that it returns the number of times keyToFind
			// has been added to the data structure.
		int hash = Math.abs(keyToFind.hashCode() % size);
		if (hashTable[hash] == null) {
			return 0;
		} else {
			Node findKey = hashTable[hash];
			while (findKey != null && !findKey.getKey().equals(keyToFind)) {
				findKey = findKey.next;
			}
			if (findKey == null) {
				return 0;
			} else {
				return findKey.getValue();
			}
		}
	}

	private int hash(String keyToHash){
			return -1;
			//EXTRA CREDIT: Implement your own String hash function here.
	}
}
