// Hung Auduong
// 2/4/16
// CSE 143 AN
// TA: Ryan Parson
// Assignment #4
//
// this class is the hangman manager that does most of the games work, it takes in
// all the words that have the same length that the user puts in and the maximum number of guess
// to begin the game. this hangman game is an "evil" game because it doesnt chose the word
// at the begining but rather it delays chosing it until it is forced to. 

import java.util.*;

public class HangmanManager {
   private int guessLeft; //guess remaining
   private Set<String> words; // words availible
   private SortedSet<Character> letterGuessed; // which letter is guessed
   private String display; // which letter guessed correctly or dashes
   
   // pre: length > 1 or max guess > 0. (throw illegalArgumentException if not)
   // post: intializes the game by passing
   //       a dictionary of words, the target word's length and the max number of
   //       wrong guesses a player can make.
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      guessLeft = max;
      words = new TreeSet<String>();
      letterGuessed = new TreeSet<Character>();
      for(String temp : dictionary) {
         if (temp.length() == length) {
            words.add(temp);
         }
      }
      display = "-";
      for (int i = 1; i < length; i++) {
         display += " -";
      }
   }
   
   // post: returns the set of words
   public Set<String> words() {
      return words;
   }
   
   // post: returns the amount of guesses the user have left
   public int guessesLeft() {
      return guessLeft;
   }
   
   //post: return the letter that user guessed
   public SortedSet<Character> guesses() {
      return letterGuessed;
   }
   
   // pre: if word size is bigger than 0 (throw IllegalStateException if not)
   // post: return the display of the game
   public String pattern(){
      if (words.size() == 0) {
         throw new IllegalStateException();
      }
      return display;
   }
   
   // pre: guess > 1 or word != empty (throw IllegalStateException if not)
   //      words is not empty and letterGuessed contains guess
   //      (throw IllegalArgumentException if not)
   // post: record the next guest made by the user, using that guess, it decides
   //       what set of words to use going forward, also remembers the number of time the
   //       letter that was guessed was found and returns that number. and also 
   //       updates the amount of guesses the user have left.
   public int record(char guess) {
      if (guessLeft < 1 || words.isEmpty()) {
         throw new IllegalStateException();
      }
      if (!words.isEmpty() && letterGuessed.contains(guess)) {
         throw new IllegalArgumentException();
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
   
   // post: helper method. this method creates the key and sorts each word
   //       into the appropriate key.
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
   
   // post: this method chooses which key to move forward with the game
   //       by using that key's value. it will take the key with the most value in it.
   private void chooseEntry(Map<String, Set<String>> group) {
      int biggestEntry = 0;
      for(String entry : group.keySet()) {
         if (group.get(entry).size()> biggestEntry) {
            biggestEntry = group.get(entry).size();
            display = entry;
         }
      }
   }
}
