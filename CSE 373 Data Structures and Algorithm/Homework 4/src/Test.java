public class Test {

	public static void main(String[] args) {
		FileInput.init();
		
      double errorSum = 0.0;
      double maxDifference = 0.0;
      String mostDifferentWord = "";
      ChainingHash cH = new ChainingHash();
      QPHash qP = new QPHash();
      String[] a = FileInput.readShakespeare();
      String[] b = FileInput.readBacon();
      // this is to check for other tests. if uncomment this, the other has to be commented
      //String[] a = FileInput.readBacon();
      //String[] b = FileInput.readShakespeare();
      double aSize = a.length * 1.0;
      double bSize = b.length * 1.0;
      
      createHashTable (a, b, cH, qP);
      iterate (errorSum, maxDifference, cH, qP, aSize, bSize, mostDifferentWord);
      
      
      //TODO Initialize the hash tables 
		
		
		//TODO Use the FileInput functions to read the two files.
		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		
		
		//TODO Initialize necessary variables for calculating the square error
		// and most distant word.
		
		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.
		
		//TODO Find  words in the second hash table that are not in the first and add their squared error
		// to the total
		
		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.
	}
   
   public static void createHashTable(String[] a, String[] b, ChainingHash cH, QPHash qP) {
      for (String word : a) {
         cH.insert(word);
      }
      for (String word : b) {
         qP.insert(word);
      }
   }
   
   public static void iterate (double errorSum, double maxDifference, ChainingHash cH, QPHash qP,
                               double aSize, double bSize, String mostDifferentWord) {
      
      String word = cH.getNextKey();
      while (word != null) {
         int x = cH.findCount(word);
         int y = qP.findCount(word);
         double difference = Math.abs((x / aSize) - (y / bSize));
         if (difference > maxDifference) {
            maxDifference = difference;
            mostDifferentWord = word;
         }
         errorSum += Math.pow(difference, 2);
         word = cH.getNextKey();

      }
      String word2 = qP.getNextKey();
      while (word2 != null) {
         
         if (cH.findCount(word2) == 0) {
            double wordRatio = qP.findCount(word2) / bSize;
            if (wordRatio > maxDifference) {
               maxDifference = wordRatio;
               mostDifferentWord = word2;
            }
            errorSum += Math.pow(wordRatio, 2);
         }
         word2 = qP.getNextKey();
      }
      System.out.println("Total Square Error: " + errorSum);
      System.out.println("Most different word: " + mostDifferentWord);                      
   }        

}
