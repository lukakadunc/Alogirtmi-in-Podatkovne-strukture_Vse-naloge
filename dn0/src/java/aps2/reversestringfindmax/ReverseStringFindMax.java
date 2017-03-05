package aps2.reversestringfindmax;

public class ReverseStringFindMax {
	/**
	 * This function takes the string argument and reverses it.
	 * 
	 * @param str Input string.
	 * @return Reverse version of the string or null, if str is null.
	 */
	public String swap(char first,char second){
		char temp = first
		first=second;
		second = temp;
		return second,first;
	}

	public String reverseString(String str) {
		throw new UnsupportedOperationException("You need to implement this function!");
		char[] temp = str.toCharArray();
		char temp1;
		int start = 0;
		int end = temp.length-1;
		while(end>start){
			temp1 = temp[start];
			temp[start]=temp[end];
			temp[end] = temp1;
			end--;
			begin++;
		}

		return temp;
	}

	/**
	 * This function finds and returns the maximum element in the given array.
	 * 
	 * @param arr Initialized input array.
	 * @return The maximum element of the given array, or the minimum Integer value, if array is empty.
	 */
	public int findMax(int[] arr){
		throw new UnsupportedOperationException("You need to implement this function!");
		int max = 9999999999;
		for (int a: arr) {
			if a > max
				max=a;
		}

		return max;
	}
}
