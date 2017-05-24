// Hung Auduong
// 2/26/16
// CSE 143 AN
// TA: Ryan Parson
// Assignment #6
//
// this program takes in the words in the dictionary, user's input, and the maximum
// number of combination that can be used to show the combonation of different words
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
   
   // pre: max >= 0 (if not, throw new IllegalArgumentException)
   // post: finds all the number of combination of the words that can be made from
   //       the list of words in the dictionary to the input word that the user input.
   //       it also creates a new list of words in the dictionary with only relevant words.
   public void print(String phrase){
      if (phrase.equals(null)) {
         throw new IllegalArgumentException();
      }
      LetterInventory input = new LetterInventory(phrase);
      ArrayList<String> result = new ArrayList<String>();
      
      SortedSet<String> reducedList = getWords(phrase);
      combination (input, result, reducedList);
   }
   
   // post: healper method that does the recursive back tracking and then prints
   //       out the result of the number of combonation of words that an be
   //       used form the user input. 
   private void combination (LetterInventory input, ArrayList<String> result,
   								  SortedSet<String> reducedList) {   
   	if (input == null) {
         return;
      }
      if (input.isEmpty()) {
   		System.out.println(result);
         return;
   	}
   	for (String test : reducedList) {
         if (map.containsKey(test)) {
            result.add(test);
            LetterInventory subtractedInventory = input.subtract(map.get(test));
            combination (subtractedInventory, result, reducedList);
            result.remove(result.size()-1);
         }
      }
   }
   
   public void print(String phrase, int max) {
   	if (phrase.equals(null) || max < 0) {
   		throw new IllegalArgumentException();
   	}
   	LetterInventory input = new LetterInventory(phrase);
      ArrayList<String> result = new ArrayList<String>();
      
      SortedSet<String> reducedList = getWords(phrase);
      numCombo (input, result, reducedList, max);
      
   	
   }
   
   private void numCombo (LetterInventory input, ArrayList<String> result,
			  SortedSet<String> reducedList, int max) {
   	if (input == null) {
         return;
      }
      if (input.isEmpty() && (result.size() <= max || max == 0)) { // || max == 0)
         System.out.println(result);
         return;
      }
   	for (String test : reducedList) {
         if (map.containsKey(test)) {
            result.add(test);
            LetterInventory subtractedInventory = input.subtract(map.get(test));
            numCombo (subtractedInventory, result, reducedList, max);
            result.remove(result.size()-1);
         }
      }
   }
}













