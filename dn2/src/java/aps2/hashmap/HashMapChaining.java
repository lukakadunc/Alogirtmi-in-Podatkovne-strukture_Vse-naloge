package aps2.hashmap;

import java.util.LinkedList;

/**
 * Hash map employing chaining on collisions.
 */
public class HashMapChaining {
	private LinkedList<Element> table[];
	private HashFunction.HashingMethod h;
	
	public HashMapChaining(int m, HashFunction.HashingMethod h) {
		this.h = h;
		this.table = new LinkedList[m];
		for (int i=0; i<table.length; i++) {
			table[i] = new LinkedList<Element>();
		}
	}
	
	public LinkedList<Element>[] getTable() {
		return this.table;
	}



	public int hash(int k,int length){
		if(this.h.equals("DivisionMethod"))
			return HashFunction.DivisionMethod(k,length);
		else
			return HashFunction.KnuthMethod(k,length);

	}


	/**
	 * If the element doesn't exist yet, inserts it into the set.
	 * 
	 * @param k Element key
	 * @param v Element value
	 * @return true, if element was added; false otherwise.
	 */
	public boolean add(int k, String v) {
		//throw new UnsupportedOperationException("You need to implement this function!");
		//System.out.println(this.h);
		Element temp = new Element(k,v);
		return this.table[hash(k,table.length)].add(temp);
	}

	/**
	 * Removes the element from the set.
	 * 
	 * @param k Element key
	 * @return true, if the element was removed; otherwise false
	 */
	public boolean remove(int k) {
		throw new UnsupportedOperationException("You need to implement this function!");
	}

	/**
	 * Finds the element.
	 * 
	 * @param k Element key
	 * @return true, if the element was found; false otherwise.
	 */
	public boolean contains(int k) {
		int hash = hash(k,table.length);
		boolean flag=false;

		for(int i=0; i<table[hash].size(); i++){
			if(table[hash(k,table.length)].get(i).equals(new Element(k,null))) //Nima veze valeu zato sem dal kar null
				flag = true;

		}
		return flag;


		//throw new UnsupportedOperationException("You need to implement this function!");

	}
	
	/**
	 * Maps the given key to its value, if the key exists in the hash map.
	 * 
	 * @param k Element key
	 * @return The value for the given key or null, if such a key does not exist.
	 */
	public String get(int k) {
		//throw new UnsupportedOperationException("You need to implement this function!");
		int hash = hash(k,table.length);
		for(int i=0; i<table[hash].size(); i++) {
			if(table[hash].get(i).equals(new Element(k,null)))
				return this.table[hash(k,table.length)].get(i).value;
		}
		return null;
	}
}

