import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author lzb700m
 * @version 1.0 04/08/2016
 */

public class RevengeofthePancakes {

	private static final String DEBUG = "./testcases/debug.in";

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(DEBUG);
			Scanner in = new Scanner(new BufferedReader(fr));
			int t = Integer.parseInt(in.nextLine());
			for (int i = 0; i < t; i++) {
				String pancakes = in.nextLine();
				System.out.println("Case #" + (i + 1) + ": "
						+ flipPancakes(pancakes));
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private static int flipPancakes(String s) {
		int ret = 0;
		boolean[] pancakes = new boolean[s.length()];

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+') {
				pancakes[i] = true;
			} else if (s.charAt(i) == '-') {
				pancakes[i] = false;
			}
		}

		boolean prev = pancakes[0];
		boolean cur = prev;

		for (int i = 1; i < pancakes.length; i++) {
			cur = pancakes[i];
			if (prev ^ cur) {
				ret++;
			}
			prev = cur;
		}

		if (!cur) {
			ret++;
		}

		return ret;
	}
}
