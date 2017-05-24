// Hung Auduong
// 4/28/16
// CSE 143 AN
// TA: Radu
// Assignment #4
//
// this program reads the input file with a grammar (BNF) and allow the user to
// randomly generate elements of the grammar.


import java.util.*;

public class GrammarSolver {
   private SortedMap<String, String[]> grammarMap;
   
   // pre: list.size() != null (if not throw IllegalArgumentException)
   // post: initialize a new grammar solver over the given BNF grammar rules where each
   //		rule corresponds to one line of text.
   public GrammarSolver(List<String> rules) {
      if (rules.isEmpty()) {
         throw new IllegalArgumentException("set is empty");
      }
      grammarMap = new TreeMap<String, String[]>();
      createMap(rules);
   }
   
   // post: a helper method to make the constructor short. it splits the strings up into parts
   //       and put them in the map. if the key is already inside the map, then it will
   //		throw an illegal argument exception
   private void createMap(List<String> grammar) {
      for (String s : grammar) {
         String[] line = s.split("::=");
         String key = line[0].trim();
         String value = line[1];
         String[] values = value.split("[|]");
         if (grammarMap.containsKey(key)) {
            throw new IllegalArgumentException("key already there");
         } else {
            grammarMap.put(key, values);
         }
      }
   }
   
   // post: returns the grammar map if it contains the key of the map or not.
   public boolean contains(String symbol) {
      return grammarMap.containsKey(symbol);
   }
   
   // pre: symbol = true and times > 0. (if not throw new IllegalArgumentException)
   // post: randomly generate the number of sentences as an array
   public String generate(String Symbol) {
      if (!grammarMap.containsKey(Symbol) || Symbol.length() < 0) {
         throw new IllegalArgumentException();
      }
      String result = "";
      result = createSentence(Symbol);
      return result;
   }
   
   // post: helper method that takes the split "values" randomly to create the sentence
   //       as an array. if the doesnt reach a terminal value it recurse through the method
   //       until it reaches a terminal value then it returns the terminal value.
   private String createSentence(String nonTerminal) {
      if (!grammarMap.containsKey(nonTerminal)) {
         return " " + nonTerminal;
      }
      String[] rules = grammarMap.get(nonTerminal);
      Random r = new Random();
      int n = r.nextInt(rules.length);
      String rule = rules[n].trim();
      String[] parts = rule.split("[ \t]+");
      String result = "";
      for (String temp : parts) {
         result += createSentence(temp);
      }
      return result;
   }
   
   // post: returns the nonterminal keys from the grammar as a sorted, comma-separated list
   //       that is enclosed in a sqr bracket.
   public Set<String> getSymbols() {
      Set<String> nonterminalKeys = grammarMap.keySet();
      return nonterminalKeys;
   }
}