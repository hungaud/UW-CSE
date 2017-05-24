import java.awt.List;

/**
 * @author cse373
 *
 */
class BST{
    
    Node root;

    private class Node{
    	
    	// These attributes of the Node class will not be sufficient 
    	// for those attempting the AVL extra credit.
    	// You are free to add extra attributes as you see fit, 
    	// but do not remove attributes given as it will mess with help code.
        String keyword;
        Record record;
        int size;
        Node l;
        Node r;
        
        // intialize the keyword
        private Node(String k) {        	
            this.keyword = k;	
        }
        
        // post: updates the record. adds the new record in the front of the linked list
        private void update(Record r) {
        	r.next = this.record;
        	this.record = r;
        	
        }
    }

    public BST() {
        this.root = null;
    }
    // post: adds a new record to the specific keyword node.
    public void insert(String keyword, FileData fd) {
        Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        root = inserting (keyword, root,recordToAdd); 
    }
    
    // post: private helper method that recurse through the BST to find the node with the 
    //		 correct key (keyword matches) updates the node with the new record
    private Node inserting(String keyword, Node root, Record recordToAdd) {
    	if (root == null) {
    		Node newNode = new Node(keyword);
    		newNode.update(recordToAdd);
    		return newNode;
    	} else {
	    	if (root.keyword.equals(keyword)) {
	    		root.update(recordToAdd);
	    	} else if ((keyword.compareTo(root.keyword)) < 0 ) {
	    		root.l = inserting(keyword, root.l, recordToAdd);
	    	} else { // keyword.compareTo(root.keyword)) > 0
	    		root.r = inserting(keyword, root.r, recordToAdd);
	    	}
    	}
    	return root;
    }
	
    // post: check if the node exist in the binary search tree
    public boolean contains(String keyword) {
    	return check(root, keyword);
    }
    
    // post: private helper method that recurse through the binary search tree to find the
    //		 keyword 
    private boolean check(Node root, String keyword) {
    	if (root.keyword.equals((keyword))) {
    		return true;
    	} else if (keyword.compareTo(root.keyword) < 0 ) {
    		return check(root.l, keyword);
    	} else if (keyword.compareTo(root.keyword) > 0 ) {
    		return check(root.r, keyword);
    	}
    	return false;
    }
    
    // post: checks if the bst contains the specific node with the keyword that was passed
    //		 in the parameter. if it does, then it returns they record in the node
	public Record get_records(String keyword) {
		if (contains(keyword)) {
			return gettingRecord(keyword, root);
		} else {
			return null;
		}
    }
	
	// post: private helper method that recurse through the BST to get to the specific node.
	//		 when it does it will return they node's record
	private Record gettingRecord(String keyword, Node root) {
		if (keyword.equals(root.keyword)) {
			return root.record;
		} else if (keyword.compareTo(keyword) < 0) {
			return gettingRecord(keyword, root.l);
		} else {
			return gettingRecord(keyword, root.r);
		}
	}
	
	// post: deletes a specific node out of the bst with the same key as the 
	//		 keyword thats passed in the parameter.
    public void delete(String keyword) {
    	if (contains(keyword)) {
    		root = remove(keyword, root);
    	}
    }
    
    // post: private helper method that recurse through out the bst to find the specific
    //		 node to remove from the bst. once found, it will take the most left node in
    //		 the right subtree of the root and move that to be the new root to perserve the
    //		 property of the bst then returns the new root.
    private Node remove(String keyword, Node root) {
    	if (root == null) {
    		return root;
    	}
    	if (keyword.compareTo(root.keyword) < 0) {
    		root.l = remove(keyword, root.l);
    	} else if( keyword.compareTo(root.keyword) > 0 ) {
    		root.r = remove(keyword, root.r);
    	} else {
    		if (root.r == null) {
    			return root.l;
    		}
    		if (root.l == null) {
    			return root.r;
    		}
    		Node temp = root;
    		root = min(temp.r);
    		root.r = removeMin(temp.r);
    		root.l = temp.l;
    	}
    	return root;
    }
    
    // post: private helper method that finds the minimum node and returns it
    private Node min(Node root){
    	if (root.l == null) {
    		return root;
    	} else {
    		return min(root.l);
    	}
    }
    
    // post: private method that returns removes the minimum node. it will recurse through
    //		 the subtree to remove the minimum node.
    private Node removeMin(Node temp) {
    	if (temp.l == null) {
    		return temp.r;
    	} else {
    		temp.l = removeMin(temp.l);
    	}
    	return temp;
    }

    public void print(){
        print(root);
    }

    private void print(Node t){
        if (t != null){
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
            	System.out.println("\t" + r.title);
                r = r.next;
            }
            print(t.r);
        } 
    }
}
