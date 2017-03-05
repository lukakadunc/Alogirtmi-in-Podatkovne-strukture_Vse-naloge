package aps2.bstmap;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the (unbalanced) Binary Search Tree set node.
 */
public class BSTMapNode {
	private static int counter;
	private BSTMapNode left, right, parent;
	private int key;
	private String value;

	public BSTMapNode(BSTMapNode l, BSTMapNode r, BSTMapNode p, int key, String value) {
		super();
		this.left = l;
		this.right = r;
		this.parent = p;
		this.key = key;
		this.value = value;
	}

	public BSTMapNode getLeft() {
		return left;
	}

	public void setLeft(BSTMapNode left) {
		this.left = left;
	}

	public BSTMapNode getRight() {
		return right;
	}

	public void setRight(BSTMapNode right) {
		this.right = right;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public int compare(BSTMapNode node) {
		counter++;
		return node.key-this.key;
	}

	public int getCounter() {
		return counter;
	}

	public void resetCounter() {
		counter = 0;
	}

	/**
	 * If the element doesn't exist yet, adds the given element to the subtree.
	 * 
	 * @param element Given key/value wrapped inside an empty BSTNode instance
	 * @return true, if the element was added; false otherwise.
	 */
	public boolean add(BSTMapNode element) {
		//look left side if you can add new element
		int comp = this.compare(element);
		if(this.left == null && comp < 0){
			this.left = element;
			element.parent = this;
			return true;
		}
		else if(this.left != null && comp < 0)
			return this.left.add(element);

		//look right side if you can add new element
		if (this.right == null && comp > 0){
			this.right = element;
			element.parent = this;
			return true;
		}
		else if (this.right != null && comp > 0)
			return  this.right.add(element);

		return false;
	}


	/**
	 * Finds and removes the element with the given key from the subtree.
	 * 
	 * @param element Given key wrapped inside an empty BSTNode instance
	 * @return true, if the element was found and removed; false otherwise.
	 */
	public boolean remove(BSTMapNode element) {
		int comp = this.compare(element);
		//System.out.println(this.toString() + " " + element.toString() + " compare: " + comp);

		//element was found
		if(comp == 0){
			BSTMapNode min = this.findMin();                                 //find smallest node in subtree

			//mn.left  = this.left;
			min.left  = null;                                                //becasue mn is the smallest element
			min.right = this.right;

			if(this.compare(this.parent) > 0 && this.compare(min) == 0)
				this.parent.left = min.left;
			else if(this.compare(this.parent) < 0 && this.compare(min) == 0)
				this.parent.right = min.right;
			else if(this.compare(this.parent) > 0 && this.compare(min) != 0)
				this.parent.left = min;
			else if(this.compare(this.parent) < 0 && this.compare(min) != 0)
				this.parent.right = min;

			//System.out.println(this.parent.toString() + " | " + min.right.toString());
			//System.out.println(this.parent.right.toString());

			return true;
		}
		//moving down the tree
		else if(comp < 0 && this.left != null)
			return this.left.remove(element);
		else if(comp > 0 && this.right != null)
			return this.right.remove(element);
		else
			return false;
	}

	/**
	 * Checks whether the element with the given key exists in the subtree.
	 * 
	 * @param element Query key wrapped inside an empty BSTNode instance
	 * @return true, if an element with the given key is contained in the subtree; false otherwise.
	 */
	public boolean contains(BSTMapNode element) {
		int comp = this.compare(element);
		if(element == null)
			return false;
		else if(comp == 0)
			return true;
		else if(comp < 0 && this.left != null)
			return this.left.contains(element);
		else if(comp > 0 && this.right != null)
			return this.right.contains(element);
		else
			return false;
	}


	/**
	 * Maps the given key to its value.
	 * 
	 * @param element Query key wrapped inside an empty BSTNode instance
	 * @return String value of the given key; null, if an element with the given key does not exist in the subtree.
	 */
	public String get(BSTMapNode element) {
		if(element == null)
			return null;
		else if(this.compare(element) == 0)
			return this.value;
		else if(this.compare(element) < 0 && this.left != null)
			return this.left.get(element);
		else if(this.compare(element) > 0 && this.right != null)
			return this.right.get(element);
		else
			return null;
	}

	/**
	 * Finds the smallest element in the subtree.
	 * 
	 * @return Smallest element in the subtree
	 */
	public BSTMapNode findMin() {
		//throw new UnsupportedOperationException("You need to implement this function!");
		if(this.left == null){
			return this;
		}
		else {
			this.left.findMin();
			return this;
		}





	}
	
	/**
	 * Depth-first pre-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by pre-order traversing the tree.
	 */
	List<Integer> traversePreOrder() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}

	/**
	 *
	 * /***work- ccs = 1s4
	 * Depth-first in-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by in-order traversing the tree.
	 */
	List<Integer> traverseInOrder() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}

	/**
	 * Depth-first post-order traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by post-order traversing the tree.
	 */
	List<Integer> traversePostOrder() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}

	/**
	 * Breadth-first (or level-order) traversal of the BST.
	 * 
	 * @return List of keys stored in BST obtained by breadth-first traversal of the tree.
	 */


	List<Integer> traverseLevelOrder() {
		throw new UnsupportedOperationException("You need to implement this function!");
	}
}
