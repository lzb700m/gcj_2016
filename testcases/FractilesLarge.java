import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author lzb700m
 * @version 1.0 04/09/2016
 */

public class FractilesLarge {
	// private static final String DEBUG = "./testcases/debug.in";

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(args[0]);
			Scanner in = new Scanner(new BufferedReader(fr));
			int t = Integer.parseInt(in.nextLine());
			for (int i = 0; i < t; i++) {
				int k = in.nextInt();
				int c = in.nextInt();
				int s = in.nextInt();
				ArrayList<Long> positions = fractiles(k, c, s);
				System.out.print("Case #" + (i + 1) + ":");
				if (positions == null) {
					System.out.print(" IMPOSSIBLE");
				} else {
					for (Long pos : positions) {
						System.out.print(" " + pos);
					}
				}
				System.out.println();
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private static ArrayList<Long> fractiles(int k, int c, int s) {
		int required = (int) Math.ceil(1.0 * k / c);
		if (s < required) {
			return null;
		}
		ArrayList<Long> ret = new ArrayList<Long>();
		for (int i = 0; i < required - 1; i++) {
			long originalPos = i * c + 1;
			long pos = originalPos;
			for (int j = 1; j < c; j++) {
				pos = (pos - 1) * k + originalPos + j;
			}
			ret.add(pos);
		}

		ret.add((long) Math.pow(k, c - 1) * (k - 1) + 1);
		return ret;
	}
}
