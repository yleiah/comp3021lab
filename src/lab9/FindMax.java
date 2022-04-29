package lab9;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().printMax(1);
	}

	public void printMax() {
		// this is a single threaded version
		int max = findMax(0, array.length - 1);
		System.out.println("the max value is " + max);
	}
	
	public void printMax(int i) {
		// this is a multi-threaded version
		int p1 = array.length/3;
		int p2 = 2*p1;
		
		FindMaxT f1 = new FindMaxT(0,p1);
		FindMaxT f2 = new FindMaxT(p1,p2);
		FindMaxT f3 = new FindMaxT(p2,array.length - 1);
		
		Thread t1 = new Thread(f1);
		Thread t2 = new Thread(f2);
		Thread t3 = new Thread(f3);
		
		t1.run();
		t2.run();
		t3.run();
		
		
		
		int max = Math.max(f1.getOutput(),f2.getOutput());
		max = Math.max(max,f3.getOutput());
		System.out.println("the max value is " + max);
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	
	class FindMaxT implements Runnable{
		int left;
		int right;
		
		int output;
		
		public FindMaxT(int l, int r) {
			left = l;
			right = r;
		}
		
		public int getOutput(){
			return output;
		}
		
		@Override
		public void run(){
			output = findMax(left,right);
		}
	}
	
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	
	
}
