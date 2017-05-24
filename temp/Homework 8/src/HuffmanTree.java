// Hung Auduong
// 5/26/16
// CSE 143 AH
// TA: Radu Cracut
// Assignment #8
//
// this program takes a text file and create a binary tree of all the frequency and characters
// that was used into a compressed file with an output of the binary tree. it also does the
// opposite and takes the compressed file and turn it back into the original text file.

import java.io.*;
import java.util.*;

public class HuffmanTree {
   
   private HuffmanNode overallRoot;
   
   // post:: this class is to store information and create a huffman node for the huffman tree.
   private class HuffmanNode implements Comparable <HuffmanNode> {
      public int count;
      public int character;
      public HuffmanNode left;
      public HuffmanNode right;
      
      // post: construct a leaf node with the the given frequency of the character
      public HuffmanNode(int count, int character) {
         this(count, character, null, null);
      }
      
      // post: construct a huffman node with the given data
      public HuffmanNode(int count, int character, HuffmanNode left, HuffmanNode right) {
         this.count = count;
         this.character = character;
         this.left = left;
         this.right = right;
      }
      
      // post: return if the node is a leaf or not
      public boolean isLeaf() {
         return right == null && left == null;
      }
      
      // post: compares the ocunt of the other huffmanNode vs this one.
      public int compareTo(HuffmanNode other) {
         return count - other.count;
      }
   }
   
   // post: construct the initial Huffman tree using the given araay of frequency where
   //       count[i] is the number of occurrences of a character with integer value i
   public HuffmanTree(int[] counts) {
      Queue<HuffmanNode> huffmanQueue = new PriorityQueue<HuffmanNode>(counts.length);
      for (int i = 0; i <counts.length; i++) {
         HuffmanNode temp = new HuffmanNode(counts[i], i);
         if(counts[i] > 0) {
            huffmanQueue.add(temp);
         }
      }
      huffmanQueue.add(new HuffmanNode(1, counts.length));
      while(huffmanQueue.size() > 1) {
         HuffmanNode a = huffmanQueue.remove();
         HuffmanNode b = huffmanQueue.remove();
         huffmanQueue.add(new HuffmanNode(a.count + b.count, 0, a, b));
      }
      overallRoot = huffmanQueue.remove();
   }
   
   // post: writes the trea to a given output stream in standard forma
   public void write(PrintStream output) {
      printTree(output, overallRoot, "");
   }
   
   // post: helper method that recurse through the binary tree and prints
   //       the char value and then the code (0 || 1) how to get to the specific car
   private void printTree(PrintStream output, HuffmanNode root, String code) {
      if (root != null) {
         if (root.isLeaf()) {
            output.println(root.character);
            output.println(code);
         } else {
            printTree(output, root.left, code + "0");
            printTree(output, root.right, code + "1");
         }
      }
   }
   
   // post: constructor that will reconstruct the tree from a file that has already been
   //       converted into a huffman tree. it takes a scanner that contains a tree stored in
   //       standard format
   public HuffmanTree(Scanner input) {
      overallRoot = new HuffmanNode(0, 0);
      while(input.hasNextLine()) {
         int character = Integer.parseInt(input.nextLine());
         String code = input.nextLine();
         overallRoot = makeTree(overallRoot, character, code);
      }
   }
   
   // post: helper method that recurse through the file to find specific chacters by using
   //       the code in the file. form that it creates a huffman node with that character.
   private HuffmanNode makeTree(HuffmanNode root, int character, String code) {
      if (code.length() < 1) {
         return new HuffmanNode(0, character);
      }
      if(root == null) {
         root = new HuffmanNode(0,0);
      }
      if(code.charAt(0) == '0') {
         root.left = makeTree(root.left, character, code.substring(1));
      } else {
         root.right = makeTree(root.right, character, code.substring(1));
      }
      return root;
   }
   
   // post: this method will read the bits inside the input stream and should write the
   //       corresponding character. it will stop reading once it reaches the eof value
   //       value in the parameter.
   public void decode(BitInputStream input, PrintStream output, int eof) {
      HuffmanNode root = overallRoot;
      while(root.character != eof) {
         root = overallRoot;
         while(!root.isLeaf()) {
            int move = input.readBit();
            if (move == 0) {
               root = root.left;
            } else {
               root = root.right;
            }
         }
         if (root.character != eof) {
            output.write(root.character);
         }
      }
   }
}
