// Hung Auduong
// 5/21/16
// CSE 143 AH
// TA: Radu Cracut
// Assignment #6
//
// this program takes in the words in the dictionary, user's input, and the maximum
// number of combination that can be used to show the combinatoin of different words
// in the dictionary can be spelled out from the user's input.

import java.util.*;

public class Anagrams {
   private Map<String, LetterInventory> map;
   private Set<String> listOfInventories;
   
   // post: constructs an anagram solver. by initializing by taking all the words
   //       in the dictionary and so that we can access the words to see if it can be use
   //       in the anagram result
   public Anagrams(Set<String> list) {
      if (list == null) {
         throw new IllegalArgumentException();
      }
      map = new HashMap<String, LetterInventory>();
      listOfInventories = list;
      for (String word : listOfInventories) {
         LetterInventory inventory = new LetterInventory(word);
         map.put(word, inventory);
      }
   }
   
   // pre: phrase != null (throw illegal argument exception if so)
   // post: returns a set containing all the words from the dictionary that can be
   //       made using some or all of the letter in the given phrase, in alphabetical order.
   public SortedSet<String> getWords(String phrase) {
      if (phrase == null) {
         throw new IllegalArgumentException();
      }
      LetterInventory input = new LetterInventory(phrase);
      SortedSet<String> reducedWords = new TreeSet<String>();
      for (String word : listOfInventories) {
         if (input.subtract(new LetterInventory(word)) != null) {
            reducedWords.add(word);
         }
      }
      return reducedWords;
   }
   
   // pre: phrase != null (if not, throw new IllegalArgumentException)
   // post: calls the print method with a max of 0 to print all the anagram
   //       that can be formed using all of the letters of the given phrase.
   public void print(String phrase){
      if (phrase.equals(null)) {
         throw new IllegalArgumentException();
      }
      print(phrase, 0);
   }
   
   // pre: max >= 0 && phrase != null (if not, throw new IllegalArgumentException)
   // post: takes the reduced list of all the availible words that can be made with the
   //       given phrase then go through and find it with the given phrase and max number
   //       of combination that can be found. if max is 0 that means there is no maximum
   //       combination so it finds all combination
   public void print(String phrase, int max) {
      if (phrase.equals(null) || max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory input = new LetterInventory(phrase);
      List<String> result = new ArrayList<String>();
      
      SortedSet<String> reducedList = getWords(phrase);
      combination (input, result, reducedList, max);
   }
   
   // post: healper method that does the recursive back tracking and then prints
   //       out the result of the number of combination of words that an be
   //       used form the user input.
   private void combination (LetterInventory input, List<String> result,
   SortedSet<String> reducedList, int max) {
      if (input == null) {
         return;
      }
      if (input.isEmpty() && (max == 0 || result.size() <= max)) {
         System.out.println(result);
         return;
      }
      for (String test : reducedList) {
         if (map.containsKey(test)) {
            result.add(test);
            LetterInventory subtractedInventory = input.subtract(map.get(test));
            combination (subtractedInventory, result, reducedList, max);
            result.remove(result.size()-1);
         }
      }
   }
}
