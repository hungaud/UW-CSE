// Hung Auduong
// 4/03/16
// CSE 143 AH
// TA: Radu
// Assignment #1
//
// This class keeps a track of how many alphabetic letters there are in an inventory
// it stores the amount of alphabetic letters in the string to the inventory
// and ignores any non alphabetic characters or negative count.

public class LetterInventory {
   public static final int ALPHABET = 26;
   private int[] inventory;
   private int size;
   
   // post: helper method for the constructor, sets the size and initialize
   //			the array for letter inventory
   private LetterInventory() {
      size = 0;
      inventory = new int[ALPHABET];
   }
   
   // post: constructs an inventory of alphabetic letters from a string that
   //			is passed through the parameters. it iterate through the string and
   //			increase the size and increase the count in the index of which represents
   //			the letter
   public LetterInventory(String data) {
      this();
      String lowerAlphabet = data.toLowerCase();
      for (int i = 0; i < data.length(); i++) {
         char ch = lowerAlphabet.charAt(i);
         if (ch >= 'a' && ch <= 'z') {
            size++;
            inventory[ch - 'a']++;
         }
      }
   }
   
   // pre: a <= character >= z (throw IllegalArgumentException if not)
   // post: returns a count of how many of a specific letter there are in the inventory.
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      if (letter < 'a' || letter > 'z') {
         throw new IllegalArgumentException("Not a Letter");
      }
      return inventory [letter - 'a'];
   }
   
   // pre: 'a' <= letter >= 'z' or value >= 0 (throw IllegalArgumentException if not)
   // post: set the count for the given letter to the given value.
   public void set(char letter, int value) {
      letter = Character.toLowerCase(letter);
      if ((letter < 'a' || letter > 'z') || value < 0) {
         throw new IllegalArgumentException("Non alphabetic or value is negative");
      }
      size += value;
      size -= inventory[letter - 'a'];
      inventory[letter - 'a'] = value;
   }
   
   // post: return the sum of all the counts in the inventory
   public int size() {
      return size;
   }
   
   // post: return the true if size is 0, false if not
   public boolean isEmpty() {
      return size == 0;
   }
   
   // post: return a string representation of the inventory with the letters
   //			all lower case and in sorted order.
   public String toString() {
      String result = "[";
      for (int i = 0; i < ALPHABET; i++) {
         for (int j = 1; j <= inventory[i]; j++){
            result += (char) ('a' + i);
         }
      }
      return result + "]";
   }
   
   // post: constructs and return a new LetterInventory object that represents the sum of
   //			this LetterInventory and the other given LetterInventory. counts for each letter
   //			should be added together.
   public LetterInventory add(LetterInventory other) {
      LetterInventory result = new LetterInventory();
      for (int i = 0; i < ALPHABET; i++) {
         result.set((char) (i + 'a'), inventory[i] + other.get((char) (i + 'a')));
      }
      return result;
   }
   
   // post: construct and return a new LetterInventory object that represents the result of
   //			subtracting the other inventory from this inventory. if it's negative,
   //			it returns null
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory result = new LetterInventory();
      for (int i = 0; i < ALPHABET; i++) {
         if (inventory[i] - other.get( (char) (i + 'a') ) < 0 ) {
            return null;
         }
         result.set((char) (i + 'a'), inventory[i] - other.get((char) (i + 'a')));
      }
      return result;
   }
}
