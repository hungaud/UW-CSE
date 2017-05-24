// Hung Auduong
// 4/03/16
// CSE 143 AH
// TA: Radu
// Assignment #2
//
// This testing program stub creates a queue of HTML tags 
// in a valid sequence.

import java.util.*;

public class HtmlValidatorTest {
	public static void main(String[] args) {
		// <b>Hi</b><br/>
		// A Queue of tags you may modify and pass to your HtmlValidator object
		Queue<HtmlTag> tags = new LinkedList<HtmlTag>();
		tags.add(new HtmlTag("b", true));      // <b>
		tags.add(new HtmlTag("b", false));     // </b>
		tags.add(new HtmlTag("br"));           // <br/>
		
		// post: to test, comment out each method to test various parts of the code
		HtmlValidator test = new HtmlValidator(tags);
		testAddAndGetTags(tags, test);
		testRemoveAll(tags, test);
		System.out.println("Testing removeAll Method" + test.getTags().toString());
		testValidate(tags,test);
	}
	
	// post: this method is test if adding new htmlTag works or not. it will print all
	//			the tags that are in the queue. this also test if the constructor 
	//			successfully copies the previous tag that was already added. also, it
	//			adds another b without an open to test other conditions.
	public static void testAddAndGetTags(Queue<HtmlTag> tags, HtmlValidator test) {
		test.addTag(new HtmlTag("i", true));
		test.addTag(new HtmlTag("i", false));
		test.addTag(new HtmlTag("b", false));
		System.out.println("Testing addTag Method: " + test.getTags().toString());
	}
	// post: this method test the removeAll method. this method can be comment out to 
	//			test other various condition
	public static void testRemoveAll(Queue<HtmlTag> tags, HtmlValidator test) {
		test.removeAll("b");
	}
	
	// post: this method test if validate works with different tests such as if there was an error
	// that as caught in the queue of html tag
	public static void testValidate(Queue<HtmlTag> tags, HtmlValidator test) {
		test.validate();
	}
}