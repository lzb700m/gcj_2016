package practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Problem
 * 
 * You receive a credit C at a local store and would like to buy two items. You
 * first walk through the store and create a list L of all available items. From
 * this list you would like to buy two items that add up to the entire value of
 * the credit. The solution you provide will consist of the two integers
 * indicating the positions of the items in your list (smaller number first).
 * 
 * Input
 * 
 * The first line of input gives the number of cases, N. N test cases follow.
 * For each test case there will be:
 * 
 * One line containing the value C, the amount of credit you have at the store.
 * 
 * One line containing the value I, the number of items in the store.
 * 
 * One line containing a space separated list of I integers. Each integer P
 * indicates the price of an item in the store.
 * 
 * Each test case will have exactly one solution.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: " followed by the
 * indices of the two items whose price adds up to the store credit. The lower
 * index should be output first.
 * 
 * @author lzb700m
 *
 */
public class StoreCredit {
	// private static final String SMALL_IN = "./testcases/A-small-practice.in";
	// private static final String LARGE_IN = "./testcases/A-large-practice.in";
	// private static final String SMALL_OUT =
	// "./testcases/A-small-practice.out";
	// private static final String LARGE_OUT =
	// "./testcases/A-large-practice.out";

	private static int[] twoSumIndex(int[] arr, int target) {
		// return 1 based indices of the 2 number
		int[] ret = new int[2];
		Map<Integer, ArrayList<Integer>> items = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < arr.length; i++) {
			if (items.containsKey(arr[i])) {
				items.get(arr[i]).add(i);
			} else {
				ArrayList<Integer> lst = new ArrayList<Integer>();
				lst.add(i);
				items.put(arr[i], lst);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			int remain = target - arr[i];
			if (remain > 0 && items.containsKey(remain)) {
				if (remain != arr[i]) {
					ret[0] = i + 1;
					ret[1] = items.get(remain).get(0) + 1;
					break;
				} else if (items.get(remain).size() > 1) {
					ret[0] = items.get(remain).get(0) + 1;
					ret[1] = items.get(remain).get(1) + 1;
					break;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(args[0]);
			Scanner in = new Scanner(new BufferedReader(fr));
			int t = in.nextInt();
			for (int i = 0; i < t; i++) {
				int c = in.nextInt();
				int n = in.nextInt();
				int[] price = new int[n];
				for (int j = 0; j < n; j++) {
					price[j] = in.nextInt();
				}
				// solve the problem here
				int[] ret = twoSumIndex(price, c);
				System.out.println("Case #" + (i + 1) + ": " + ret[0] + " "
						+ ret[1]);
				in.close();
			}
		} catch (FileNotFoundException ex) {

			ex.printStackTrace();
		}
	}
}
