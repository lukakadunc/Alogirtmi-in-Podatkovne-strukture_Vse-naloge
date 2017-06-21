package aps2.suffixarray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SuffixArrayIndex {
	private String text; // input string
	private int[] SA;    // suffix array
	
	public int[] getSuffixArray() { return SA; }
	
	SuffixArrayIndex(String text){
		this.text = text;
		this.SA = new int[text.length()];
		construct();
	}




	
	/**
	 * Constructs the suffix array corresponding to the text in expected
	 * O(n log n) suffix comparisons.
	 */
	private void construct() {
		// throw new UnsupportedOperationException("You need to implement this function!");
		int len = this.text.length();
		String[] array2 = new String[len];
		for(int i = 0; i<len; i++){
			this.SA[i] = i;
			array2[i] = this.text.substring(this.SA[i], len)+"&"+i;

		}
		Arrays.sort(array2);

		for(int i = 0; i<len; i++) {
			String[] temp = array2[i].split("&");
			this.SA[i] = Integer.parseInt(temp[1]);
		}
	}
	
	/**
	 * Returns True, if the suffix at pos1 is lexicographically before
	 * the suffix at pos2.
	 * 
	 * @param int pos1
	 * @param int pos2
	 * @return boolean
	 */
	public boolean suffixSuffixCompare(int pos1, int pos2) {
		String temp1 = this.text.substring(pos1,this.text.length());
		String temp2 = this.text.substring(pos2,this.text.length());
		int temp3 = temp1.compareTo(temp2);
		if(temp3<0)
			return false;
		if(temp3>0)
			return true;
		return false;
	}
	
	/**
	 * Return True, if the query string is lexicographically lesser or
	 * equal to the suffix string at pos1.
	 * 
	 * @param String query The query string
	 * @param int pos2 Position of the suffix
	 * @return boolean
	 */
	public boolean stringSuffixCompare(String query, int pos2) {
		String temp1 = query;
		String temp2 = this.text.substring(pos2,this.text.length());
		if(query.compareTo(temp2) <= 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Returns the positions of the given substring in the text using binary
	 * search. The empty query is located at all positions in the text.
	 * 
	 * @param String query The query substring
	 * @return A set of positions where the query is located in the text
	 */
	public Set<Integer> locate(String query) {
		//throw new UnsupportedOperationException("You need to implement this function!");
		Set<Integer> mnozica = new HashSet<>();
		for (int i = 0; i <= this.SA.length - 1; i++) {
			boolean neki = true;

			for(int j = 0;j < query.length() && this.SA[i]+j < this.SA.length;j++)
				if (query.charAt(j) != text.charAt(SA[i]+j))
					neki = false;


			if(neki)
				mnozica.add(this.SA[i]);
			//System.out.println("DDD"+this.SA[i]);
		}
		return mnozica;
	}
	
	/**
	 * Returns the longest substring in the text which repeats at least 2 times
	 * by examining the suffix array.
	 * 
	 * @return The longest repeated substring in the text
	 */
	public String longestRepeatedSubstring() {
		int temp=0;
		int index = 0;
		int najdaljsi;
		for(int i=0; i<=this.text.length()-2; i++){
			najdaljsi=longestCommonPrefixLen(SA[i], SA[i+1]);
			if(najdaljsi > temp){
				temp=najdaljsi;
				index=i;
			}
		}
		int zacetek = SA[index];
		int konec = temp + zacetek;
		return this.text.substring(zacetek,konec);
	}
	
	/**
	 * Calculates the length of the longest common prefix of two suffixes.
	 * 
	 * @param int pos1 Position of the first suffix in the text
	 * @param int pos2 Position of the second suffix in the text
	 * @return The number of characters in the common prefix of the first and the second suffix
	 */
	public int longestCommonPrefixLen(int pos1, int pos2) {
		//throw new UnsupportedOperationException("You need to implement this function!");
		String a = this.text.substring(pos1, this.text.length());
		String b = this.text.substring(pos2, this.text.length());
		int minLength;
		if(a.length() < b.length())
			minLength=a.length();
		else
			minLength=b.length();

		for (int i = 0; i < minLength; i++) {
			if (a.charAt(i) != b.charAt(i)) {
				return i;
			}
		}
		return minLength;
	}
}

