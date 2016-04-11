import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lzb700m
 * @version 1.0 04/08/2016
 */

public class CoinJam {
	// private static final String DEBUG = "./testcases/debug.in";

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(args[0]);
			Scanner in = new Scanner(new BufferedReader(fr));
			int t = in.nextInt();
			for (int i = 0; i < t; i++) {
				int n = in.nextInt();
				int j = in.nextInt();
				Map<String, List<Long>> solution = coinJam(n, j);
				System.out.println("Case #" + (i + 1) + ":");
				StringBuilder sb = new StringBuilder();
				for (Map.Entry<String, List<Long>> entry : solution.entrySet()) {
					sb.setLength(0);
					sb.append(entry.getKey());
					for (long divisor : entry.getValue()) {
						sb.append(" " + divisor);
					}
					System.out.println(sb.toString());
				}
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

	}

	private static Map<String, List<Long>> coinJam(int n, int j) {
		Map<String, List<Long>> ret = new HashMap<>();
		Integer[] coin = new Integer[n];
		Arrays.fill(coin, 0);
		coin[0] = 1;
		coin[n - 1] = 1;
		boolean isJamCoin = true;
		Long[] divisors = new Long[9];
		Long[] decimals = new Long[9];

		while (true) {
			isJamCoin = true;

			for (int b = 2; b <= 10; b++) {
				long decimalValue = 0;
				for (int i = 0; i < n; i++) {
					decimalValue = decimalValue * b + coin[i];
				}

				long divisor = isPrime(decimalValue);
				if (divisor == -1) {
					isJamCoin = false;
					break;
				}

				decimals[b - 2] = decimalValue;
				divisors[b - 2] = divisor;
			}

			if (isJamCoin) {
				StringBuilder sb = new StringBuilder();
				for (int digit : coin) {
					sb.append(digit);
				}

				ret.put(sb.toString(),
						new ArrayList<Long>(Arrays.asList(divisors)));
			}

			if (ret.size() == j) {
				break;
			}

			Arrays.fill(decimals, 0l);
			Arrays.fill(divisors, 0l);
			nextCoin(coin);
		}

		return ret;
	}

	private static void nextCoin(Integer[] coin) {
		int n = coin.length;
		int carry = 1;

		for (int i = n - 2; i > 0; i--) {
			int sum = coin[i] + carry;
			coin[i] = sum % 2;
			carry = sum / 2;
			if (carry == 0) {
				break;
			}
		}
	}

	private static long isPrime(long num) {
		long divisor = -1;

		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				divisor = i;
				break;
			}
		}

		return divisor;
	}
}
