import java.util.ArrayList;

/*
 This class codes for sorting the list using radix sort. 
*/

public class Radix {

	// declare instance variables
	private Queue<Integer>[] digits;
	private ArrayList<Integer> numbers;

	// constructor for setting list
	public Radix(ArrayList<Integer> n) {
		numbers = n;
		digits = new Queue[10];
		// instantiating each queue in array
		for (int i = 0; i < digits.length; i++) {
			digits[i] = new Queue<Integer>();
		}
	}

	// sorts the list of numbers using radix sort
	public ArrayList<Integer> radixSort() {
		// sorting each significant digit
		for (int powerof10 = 1; powerof10 <= getGreatestPowerof10(); powerof10 *= 10) {
			// putting each digit in the correct queue
			for (int i = 0; i < numbers.size(); i++) {
				digits[getDigit(numbers.get(i), powerof10)].enqueue(numbers.get(i));
			}
			transfer(); // setting list to new order
			// clearing all queues
			for (int i = 0; i < digits.length; i++) {
				digits[i] = new Queue<Integer>();
			}
		}
		return numbers;
	}

	// setting the list to the new order
	private void transfer() {
		numbers.clear();
		// setting the queue order to the list
		for (int i = 0; i < digits.length; i++) {
			while (!digits[i].isEmpty()) {
				numbers.add(digits[i].dequeue());
			}
		}

	}

	// getting the max value in the list of numbers
	private int getMax() {
		int max = numbers.get(0); // setting max to first
		// setting max to greater number
		for (int i = 1; i < numbers.size(); i++) {
			if (numbers.get(i) > max) {
				max = numbers.get(i);
			}
		}
		return max;
	}

	// biggest power of 10 less than the number
	private int getGreatestPowerof10() {
		int count = 0;
		int num = getMax();
		// dividing number by 10 until its 0
		while (num != 0) {
			num /= 10;
			count++;
		}
		return (int) Math.pow(10, count - 1);
	}

	// get each digit of a number
	private int getDigit(int num, int place) {
		return (num / place) % 10;
	}

}
