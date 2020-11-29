import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import BreezySwing.*;

/*
Arjun Shah
1/6/2020
B Block
Program Description:
This program takes in a list of numbers and sorts it using
radix sort. Works for negative numbers as well.
*/

public class Main extends GBFrame {

	// GUI components
	private JLabel inputL, sortedL, ecL;
	private JTextField inputF, sortedF;
	private JButton sortB;

	// default constructor for setting GUI components
	public Main() {
		inputL = addLabel("Numbers", 1, 1, 1, 1);
		inputF = addTextField("", 1, 2, 1, 1);
		sortB = addButton("Sort", 3, 1, 1, 1);
		sortedL = addLabel("Sorted", 2, 1, 1, 1);
		ecL = addLabel("Extra Credit - Works with negative numbers", 3, 2, 1, 1);
		sortedF = addTextField("", 2, 2, 1, 1);
		sortedF.setEditable(false);
	}

	// performing actions when buttons are clicked
	public void buttonClicked(JButton button) {
		if (button == sortB) { // when sort button is clicked
			try {
				// parse numbers into list
				ArrayList<Integer> numbers = parse(inputF.getText());

				// split numbers into postive and negative
				ArrayList<Integer> positiveList = getPositive(numbers);
				ArrayList<Integer> negativeList = getNegative(numbers);

				// make all negative numbers positive
				for (int i = 0; i < negativeList.size(); i++) {
					negativeList.set(i, negativeList.get(i) * -1);
				}

				// sort positive and negative lists
				ArrayList<Integer> sortedPositiveList = new ArrayList<Integer>();
				ArrayList<Integer> sortedNegativeList = new ArrayList<Integer>();
				Radix r2 = new Radix(negativeList);
				Radix r1 = new Radix(positiveList);
				if (!positiveList.isEmpty()) { // sort if there are positive numbers
					sortedPositiveList = r1.radixSort();
				}
				if (!negativeList.isEmpty()) { // sort if there are negative numbers
					sortedNegativeList = r2.radixSort();
				}

				// change numbers back to negative and reverse order
				for (int i = 0; i < sortedNegativeList.size(); i++) {
					sortedNegativeList.set(i, sortedNegativeList.get(i) * -1);
				}
				Collections.reverse(sortedNegativeList);
				sortedNegativeList.addAll(sortedPositiveList);
				sortedF.setText(sortedNegativeList + "");
			} catch (Exception e) {
				messageBox("Error! Invalid Inputs!");
			}
		}
	}

	// parsing the string into individual numbers
	private static ArrayList<Integer> parse(String dataSet) {
		String number = "";
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		// looping through each character of string
		for (int i = 0; i < dataSet.length(); i++) {
			if (dataSet.charAt(i) != ',') { // if character not a comma
				number += "" + dataSet.charAt(i); // add it to the number
			} else if (dataSet.charAt(i) != ' ') { // if a comma is reached
				// add the whole number to the array of numbers
				numbers.add(Integer.parseInt(number.trim()));
				number = "";
			}
		}
		// add last number reached to the array
		numbers.add(Integer.parseInt(number.trim()));
		return numbers;
	}

	// getting positive numbers from the list
	private static ArrayList<Integer> getPositive(ArrayList<Integer> nums) {
		ArrayList<Integer> positive = new ArrayList<Integer>();
		// add only positive numbers
		for (int i = 0; i < nums.size(); i++) {
			if (nums.get(i) >= 0) {
				positive.add(nums.get(i));
			}
		}
		return positive;
	}

	// getting negative numbers from the list
	private static ArrayList<Integer> getNegative(ArrayList<Integer> nums) {
		ArrayList<Integer> negative = new ArrayList<Integer>();
		// add only negative numbers
		for (int i = 0; i < nums.size(); i++) {
			if (nums.get(i) < 0) {
				negative.add(nums.get(i));
			}
		}
		return negative;
	}

	// main method
	public static void main(String[] args) {
		Main theGUI = new Main();
		theGUI.setSize(1000, 150);
		theGUI.setVisible(true);
		theGUI.setTitle("Radix Sort");
	}

}
