
public class ArrayQueue {
   private String[] queueArray;
   private int size;
   private int front;
   private int back;
   
   public ArrayQueue(){
      queueArray = new String[100];
      size = 0;
      front = 0;
      back = -1;
   }
   
   public ArrayQueue(int startSize){
      queueArray = new String[startSize];
      size = 0;
      front = 0;
      back = -1;
   }
   /**
   * @function returns the number of elements in the queue
   * @return size
   */
   public int getSize(){
      return size;
   }
   /**
   * @function adds a string to the end of the queue
   * @param toEnqueue: the input to be inserted
   */
   public void enqueue(String toEnqueue){
      if (isFull()) {
         System.err.println("Array is full, cant enqueue");
      } else {
         size++;
         if (back + 1 < queueArray.length) {
            back++;
            queueArray[back] = toEnqueue;
         } else {
            back = 0;
            queueArray[back] = toEnqueue;
         }
      }
   }
   
   /**
   * @function removes the string from the front of the queue
   * @return the string from the front of the queue
   */
   public String dequeue(){
      String toDequeue = "";
      if (isEmpty()) {
         return null; // Array already empty
      } else {
         size --;
         toDequeue = queueArray[front];
         queueArray[front] = null;
         if (front + 1 < queueArray.length) {
            front++;
         } else {
            front = 0;
         }
      }
      return toDequeue;
   }
   
   /**
   *
   * @return returns true if the queue is empty, false otherwise
   */
   public boolean isEmpty(){
      return size == 0;
   }
   
   /**
   *
   * @return returns true if the queue is full, false otherwise
   */
   public boolean isFull(){
      return queueArray.length == size;
   }
   
}
