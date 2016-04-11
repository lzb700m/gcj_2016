import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author lzb700m
 * @version 1.0 04/08/2016
 */

public class FractilesSmall {
	private static final String DEBUG = "./testcases/debug.in";

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(DEBUG);
			Scanner in = new Scanner(new BufferedReader(fr));
			int t = Integer.parseInt(in.nextLine());
			for (int i = 0; i < t; i++) {
				int k = in.nextInt();
				int c = in.nextInt();
				int s = in.nextInt();
				Long[] positions = fractiles(k, c, s);
				System.out.print("Case #" + (i + 1) + ":");
				for (long pos : positions) {
					System.out.print(" " + pos);
				}
				System.out.println();
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private static Long[] fractiles(int k, int c, int s) {
		assert k == s;
		Long[] ret = new Long[k];
		ret[0] = 1l;

		for (int i = 1; i < k; i++) {
			ret[i] = ret[i - 1] + (long) Math.pow(k, c - 1);
		}

		return ret;
	}
}
