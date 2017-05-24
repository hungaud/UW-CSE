// Hung Auduong
// 5/4/16
// CSE 143 AN
// TA: Radu
// Assignment #5
//
// 	this class is a hangman manager class that takes in all the words that have the same length
//	that the user put in and the maximum number of guess to begin the game. this hangman game is\
//	evil because it delay on choosing the word that the user have to guess until it is forced to.

import java.util.*;

public class HangmanManager {
   private int guessLeft;
   private Set<String> words;
   private SortedSet<Character> letterGuessed;
   private String display;
   
   // pre: length > 1 or max guess > 0 (throw illegalArgumentException if not)
   // post: initialize the game by passing a dictionary of words, the target word length
   //		and the max number of wrong guesses the player is allowed to make. it also
   //		should initialize the game based off of the value that was passed
   public HangmanManager(List<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException("http://bit.ly/1TuiqBF");
      }
      guessLeft = max;
      words = new TreeSet<String>();
      letterGuessed = new TreeSet<Character>();
      for (String temp : dictionary) {
         if (temp.length() == length) {
            words.add(temp);
         }
      }
      display = "-";
      for (int i = 1; i < length; i++) {
         display += " -";
      }
   }
   
   // post: returns the current st of words considered by the Hangman Manager
   public Set<String> words() {
      return words;
   }
   
   // post: return the number of wrong guesses the user have left
   public int guessesLeft() {
      return guessLeft;
   }
   // post: return the letter that the user guessed
   public SortedSet<Character> guesses() {
      return letterGuessed;
   }
   
   // pre: if words is not empty (throw illegalStateException if not)
   // post: return the display of the game. if the letter havent been guessed
   //		than it is shown as dashes.
   public String pattern() {
      if (words.isEmpty()) {
         throw new IllegalStateException("http://bit.ly/1W9cPGW");
      }
      return display;
   }
   // pre: guess > 1 or word != empty (throw IllegalStateException if not)
   //      words is not empty and letterGuessed contains guess
   //      (throw IllegalArgumentException if not)
   // post: record the next guess made by the user. using this guess, it will decide what
   //		set of words to use going forward. it will return the number of occurrences of the
   //		guessed letter in a new pattern and appropriately update the number of guesses.
   public int record (char guess) {
      if (guessLeft < 1 || words.isEmpty()) {
         throw new IllegalStateException("http://bit.ly/1q1pgqn");
      }
      if (!words.isEmpty() && letterGuessed.contains(guess)) {
         throw new IllegalArgumentException("http://bit.ly/24sH4O5");
      }
      Map<String, Set<String>> group = new TreeMap<String, Set<String>>();
      letterGuessed.add(guess);
      createKey(guess, group);
      chooseEntry(group);
      if (!display.contains("" + guess)) {
         guessLeft--;
      }
      words = group.get(display);
      int count = 0;
      for (int i = 0; i < display.length(); i++) {
         if (display.charAt(i) == guess) {
            count++;
         }
      }
      return count;
   }
   
   // post: helper method that create the key and sort each words into the
   //		appropriate key. it will pass the letter that the user guessed and the group
   //		of words. after it creates the key it will search the group of words to see
   //		if it needs to create another key for a set of words.
   private void createKey(char guess, Map<String, Set<String>> group) {
      for (String checkWord : words) {
         String key = "";
         for (int i = 0; i < display.length(); i++) {
            if (letterGuessed.contains(display.charAt(i))) {
               key += display.charAt(i);
            } else {
               if (display.charAt(i) == ' ') {
                  key += display.charAt(i);
               } else if (guess == checkWord.charAt(i / 2)) {
                  key += "" + checkWord.charAt(i / 2);
               } else if (guess != checkWord.charAt(i / 2) && display.charAt(i) == '-') {
                  key += display.charAt(i);
               }
            }
         }
         if (!group.containsKey(key)) {
            Set<String> newKey = new TreeSet<String>();
            group.put(key, newKey);
         }
         group.get(key).add(checkWord);
      }
   }
   
   // post: helper method that takes the group of sets of words. it will then
   //		traverse through all the keys. to find which key holds the most set of
   //		words. then it picks that key and to move forward as the next set of words
   private void chooseEntry (Map<String, Set<String>> group) {
      int biggestEntry = 0;
      for (String entry : group.keySet()) {
         if (group.get(entry).size() > biggestEntry) {
            biggestEntry = group.get(entry).size();
            display = entry;
         }
      }
   }
}
