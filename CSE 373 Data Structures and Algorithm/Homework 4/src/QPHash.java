
import java.util.*;

public class QPHash {
	
	private int size;
	private Hashing[] hashTable;
	int count;
	
	
	private class Hashing {
		String key;
		int value;
		
		Hashing (String key, int value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}
		
		private int getValue() {
			return this.value;
		}
		private void value(int value) {
			this.value = value;
		}
	}
	
		public QPHash(){
			//TODO Implement a default constructor for QPHash
			size = 10061;
			count = 0;
			create(size);
		}
		
		public QPHash(int startSize){
			//TODO Implement a constructor that instantializes the hash array to startSize.
			size = startSize;
			count = 0;
			create(size);
		}
		
		private void create (int size) {
			hashTable = new Hashing[size];
			for (int i = 0; i < size; i++) {
				hashTable[i] = null;
			}
		}

		/**
		 * This function allows rudimentary iteration through the QPHash.
		 * The ordering is not important so long as all added elements are returned only once.
		 * It should return null once it has gone through all elements
		 * @return Returns the next element of the hash table. Returns null if it is at its end.
		 */
		public String getNextKey(){
			//TODO returns the next key in the hash table.
			//You will need external tracking variables to account for this.
			String key = null;
			if(hashTable[count] != null) {
				key = hashTable[count].getKey(); 
				count++;
			} else {
				while (hashTable[count] == null) {
					count++;
					if (count >= size) {
						return null;
					}
				}
				key = hashTable[count].getKey();
				count++;
				
			}
			return key;
		}
		/**
		 * Adds the key to the hash table.
		 * If there is a collision, a new location should be found using quadratic probing.
		 * If the key is already in the hash table, it increments that key's counter.
		 * @param keyToAdd : the key which will be added to the hash table
		 */
		public void insert(String keyToAdd){
			//TODO Implement insert into the hash table.
			//If keyToAdd is already in the hash table, then increment its count.
			int n = 0;
			int hashCode = Math.abs((keyToAdd.hashCode() + (n * n)) % size);
			if (hashTable[hashCode] == null) {
				hashTable[hashCode] = new Hashing(keyToAdd, 1);
			} else {
            Hashing addKey = hashTable[hashCode];
				while (addKey != null && !addKey.getKey().equals(keyToAdd)) {
					n++;
               hashCode = Math.abs((keyToAdd.hashCode() + (n * n)) % size);
               addKey = hashTable[hashCode];
				}
				if (addKey == null) {
				   hashTable[hashCode] = new Hashing(keyToAdd, 1);
            } else {
				   addKey.value(addKey.getValue() + 1); // change this
            }
			}
			
		}
		
		/**
		 * Returns the number of times a key has been added to the hash table.
		 * @param keyToFind : The key being searched for
		 * @return returns the number of times that key has been added.
		 */
		public int findCount(String keyToFind){
			//TODO Implement findCount such that it returns the number of times keyToFind
			// has been added to the data structure.
         int n = 0;
			int hashCode = Math.abs((keyToFind.hashCode() + (n * n)) % size);
         if (hashTable[hashCode] == null) {
			   return 0;
		   } else {
            Hashing findKey = hashTable[hashCode];
				while (findKey != null && !findKey.getKey().equals(keyToFind)) {
					n++;
               hashCode = Math.abs((keyToFind.hashCode() + (n * n)) % size);
               findKey = hashTable[hashCode];
				}
				if (findKey == null) {
				   return 0;
            } else {
				   return findKey.getValue(); // change this
            }
         }
		}

		private int hash(String keyToHash){
			return -1;
			//EXTRA CREDIT: Implement your own String hash function here.
		}
}
