package practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Problem
 * 
 * Given a list of space separated words, reverse the order of the words. Each
 * line of text contains L letters and W words. A line will only consist of
 * letters and space characters. There will be exactly one space character
 * between each pair of consecutive words.
 * 
 * Input
 * 
 * The first line of input gives the number of cases, N. N test cases follow.
 * 
 * For each test case there will a line of letters and space characters
 * indicating a list of space separated words. Spaces will not appear at the
 * start or end of a line.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: " followed by the
 * list of words in reverse order.
 * 
 * @author lzb700m
 *
 */
public class ReverseWords {
	// private static final String SMALL_IN = "./testcases/B-small-practice.in";
	// private static final String LARGE_IN = "./testcases/B-large-practice.in";
	// private static final String SMALL_OUT =
	// "./testcases/B-small-practice.out";
	// private static final String LARGE_OUT =
	// "./testcases/B-large-practice.out";

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(args[0]);
			Scanner in = new Scanner(new BufferedReader(fr));
			int t = Integer.parseInt(in.nextLine());
			for (int i = 0; i < t; i++) {
				String[] line = in.nextLine().split(" ");
				StringBuilder sb = new StringBuilder();
				sb.append(line[line.length - 1]);
				for (int j = line.length - 2; j >= 0; j--) {
					sb.append(" " + line[j]);
				}
				System.out.println("Case #" + (i + 1) + ": " + sb.toString());
			}

			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
