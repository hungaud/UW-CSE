// Hung Auduong
// 4/03/16
// CSE 143 AH
// TA: Radu
// Assignment #2
//
// this program takes a queue of HtmlTag that was passed in. it will analyze the
//	website's HTML tags and see if there is any error in the the website.
// we can also manipulate the website's tags in various way.

import java.util.*;

public class HtmlValidator {
   
   private Queue <HtmlTag> tag;
   
   // post: constructor that initialize a validator that stores an empty queue of HTML tags
   public HtmlValidator() {
      tag = new LinkedList<HtmlTag>();
   }
   
   // pre: tags != null (throw IllegalArgumentException if not)
   // post: initialize validator with an entirely separate copy of the queue that was
   //		passed in. after executing, the queue that was passed in the parameter
   //		should not have change in anyway.
   public HtmlValidator(Queue<HtmlTag> tags) {
      if (tags == null) {
         throw new IllegalArgumentException("Error, Queue is null");
      }
      tag = new LinkedList<HtmlTag>(tags);
   }
   
   // pre: tag != null (throw IllegalArgumentException if not)
   // post: add the tag that was passed in the parameter into the validator's queue.
   public void addTag (HtmlTag tag) {
      if (tag == null) {
         throw new IllegalArgumentException("Error, Tag is null");
      }
      this.tag.add(tag);
   }
   
   // post: returns an exact copy of the validator's queue of HTML tag in proper order.
   //		it should also reflect any changes that was made into the Validator's queue
   //		such as adding or removing tags
   public Queue<HtmlTag> getTags() {
      Queue <HtmlTag> copyOfTag = new LinkedList<HtmlTag>(tag);
      return copyOfTag;
   }
   
   // pre: element != null (throw IllegalArgumentException if not)
   // post: remove from the validator's queue any tags that match the given string
   //		that was passed in the parameter. if the string that was passed in does
   //		not match any of the tags, then the queue should not be modified.
   public void removeAll (String element) {
      if (element == null) {
         throw new IllegalArgumentException("element is null");
      }
      for (int i = 0; i < tag.size(); i++) {
         HtmlTag temp = tag.remove();
         if (!element.equals(temp.getElement())) {
            tag.add(temp);
         } else {
            i--;
         }
      }
   }
   
   // post: traverse through and print in an indented text representation of all the
   // 		HTML tags in the queue. if there's an error such as opening tags without
   //		a closing tag and is not self closing, an error message will appear describing
   //		the type of error.
   public void validate() {
      Stack<HtmlTag> s = new Stack<HtmlTag>();
      while (!tag.isEmpty()) {
         HtmlTag temp = tag.remove();
         
         if (temp.isOpenTag() && !temp.isSelfClosing()) {
            if (!s.isEmpty() && temp.getElement().equals(s.peek().getElement())) {
               System.out.println("ERROR unclosed tag: " + s.pop().toString());
            } else {
               for (int j = 0; j < s.size(); j++) {
                  System.out.print("    ");
               }
               s.push(temp);
               System.out.println(temp.toString());
            }
            
         } else if (!temp.isOpenTag()) {
            if (!s.isEmpty() && temp.matches(s.peek())) {
               s.pop();
               for (int j = 0; j < s.size(); j++) {
                  System.out.print("    ");
               }
               System.out.println(temp.toString());
            } else {
               System.out.println("ERROR unexpected tag: " + temp.toString());
            }
            
         } else {
            for (int j = 0; j < s.size(); j++) {
               System.out.print("    ");
            }
            System.out.println(temp.toString());
         }
      }
   }
}
