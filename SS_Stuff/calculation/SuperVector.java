package calculation;

import java.util.Arrays;
import java.util.Vector;

public class SuperVector {
	
	// Dictionary
	// change(Vector vector, int c1, int c2)
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void change(Vector vector, int c1, int c2) {
		if(c1!=c2) {
			int min = Math.min(c1, c2);
			int max = Math.max(c1, c2);
			Object minObj = vector.get(min);
			Object maxObj = vector.get(max);
			vector.remove(minObj);
			vector.remove(maxObj);
			vector.add(min, maxObj);
			vector.add(max, minObj);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void remove(Vector vector, int... indexes) {
		Arrays.sort(indexes);
		reverseArrayInt(indexes);
		for(int i : indexes) {vector.remove(i);}
	}
	
	public static void reverseArrayInt(int[] array) {
	    int temp;
	    for (int i = 0; i < array.length / 2; i++) {
	      temp = array[i];
	      array[i] = array[(array.length - 1) - i];
	      array[(array.length - 1) - i] = temp;
	    }
	  }
}
