import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author lzb700m
 * @version 1.0 04/08/2016
 */

public class CountingSheep {
	// private static final String DEBUG = "./testcases/debug.in";

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(args[0]);
			Scanner in = new Scanner(new BufferedReader(fr));
			int t = in.nextInt();
			for (int i = 0; i < t; i++) {
				long n = in.nextInt();
				System.out.println("Case #" + (i + 1) + ": " + countSheep(n));
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private static String countSheep(long n) {
		String ret = "INSOMNIA";
		if (n == 0) {
			return ret;
		}

		Set<Integer> allDigits = new HashSet<>();
		List<Integer> digits = new ArrayList<>();

		int i = 0;
		while (true) {
			long num = (i + 1) * n;
			digits = new ArrayList<>();

			while (num > 0) {
				digits.add((int) num % 10);
				num /= 10;
			}

			for (int digit : digits) {
				if (!allDigits.contains(digit)) {
					allDigits.add(digit);
				}
			}

			if (allDigits.size() == 10) {
				ret = Long.toString((i + 1) * n);
				break;
			}

			i++;
		}
		return ret;
	}
}
