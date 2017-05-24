// Hung Auduong
// 1/11/16
// CSE 143 AN
// TA: Ryan Parson
// Assignment #1
// This class keeps a track of how many alphabetic letters there are in an inventory
// it stores the ammount of alphabetic letters in the string to the inventory
// and ignores any non alphabetic characters.

public class LetterInventory {
   public static final int ALPHABET = 26;
   private int[] inventory;
   private int size;
   
   // this constructor construct set the size to be 0 and intialize the array inventory
   public LetterInventory() {
      size = 0;
      inventory = new int[ALPHABET];
   }
   
   // this constructor construct an inventory of alphabetic letters. it takes a string
   // and then looks at each individual characters. if the the character is alphabetic
   // then the in increase the size and the count for the respective
   // index in the inventory array
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
   
   //pre: checks if letter is alphabetic (throw IllegalArgumentException if not)
   //post: returns the index number in the inventory array
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      if (letter < 'a' || letter > 'z') {
         throw new IllegalArgumentException();
      }
      return inventory[letter - 'a'];
   }
   
   //pre: check if the character is alphabetic or if the value is positive
   //     (throw IllegalArgumentException if not)
   //post: sets the count for the given letter to the value
   public void set(char letter, int value) {
      letter = Character.toLowerCase(letter);
      if (value < 0 || letter < 'a' || letter > 'z') {
         throw new IllegalArgumentException();
      }
      size += value;
      size -= inventory[letter - 'a'];
      inventory[letter - 'a'] = value;
   }
   
   // returns the sum of how many letters there are in the array
   public int size() {
      return size;
   }
   
   // returns true if the inventory is empty (all counts are 0)
   public boolean isEmpty() {
      return size == 0;
   }
   
   // returns a string representaiton of the inventory with all lowercase letter and
   // in order of a,b,c... the number of each letter will match the value in each index
   // of the inventory array
   public String toString() {
      String result = "[";
      for (int i = 0; i < ALPHABET; i++) {
         if (inventory[i] > 0) {
            for (int j = 1; j <= inventory[i]; j++) {
               char letter = (char) ('a' + i);
               result += letter;
            }
         }
      }
      return result + "]";
   }
   
   // construct a new LetterInventory. Then it adds this letter inventory
   // with the other letter inventory together and then returns the
   // new letter inventory.
   public LetterInventory add(LetterInventory other) {
      LetterInventory result = new LetterInventory();
      for (int i = 0; i < ALPHABET; i++) {
         result.set( (char) (i + 'a'), inventory[i] + other.get( (char) (i + 'a') ));
      }
      return result;
   }
   
   // construct and return a new LetterInventory that represents the result of
   // subtracting the other LetterInventory from this LetterInventory. if the result
   // is negative then it returns a null
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory result = new LetterInventory();
      for (int i = 0; i < ALPHABET; i++) {
         if (inventory[i] - other.get( (char) (i + 'a') ) < 0 ) {
            return null;
         }
         result.set( (char) (i + 'a'), inventory[i] - other.get( (char) (i + 'a') ));
      }
      return result;
   }
}
