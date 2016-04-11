import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
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

public class CoinJamLarge {
	//private static final String DEBUG = "./testcases/debug.in";

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(args[0]);
			Scanner in = new Scanner(new BufferedReader(fr));
			int t = in.nextInt();
			for (int i = 0; i < t; i++) {
				int n = in.nextInt();
				int j = in.nextInt();
				Map<String, List<BigInteger>> solution = coinJam(n, j);
				System.out.println("Case #" + (i + 1) + ":");
				StringBuilder sb = new StringBuilder();
				for (Map.Entry<String, List<BigInteger>> entry : solution
						.entrySet()) {
					sb.setLength(0);
					sb.append(entry.getKey());
					for (BigInteger divisor : entry.getValue()) {
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

	private static Map<String, List<BigInteger>> coinJam(int n, int j) {
		Map<String, List<BigInteger>> ret = new HashMap<>();
		Integer[] coin = new Integer[n];
		Arrays.fill(coin, 0);
		coin[0] = 1;
		coin[n - 1] = 1;
		boolean isJamCoin = true;
		BigInteger[] divisors = new BigInteger[9];
		BigInteger[] decimals = new BigInteger[9];

		while (true) {
			isJamCoin = true;

			for (int b = 2; b <= 10; b++) {
				BigInteger decimalValue = BigInteger.valueOf(0);
				for (int i = 0; i < n; i++) {
					decimalValue = decimalValue.multiply(new BigInteger(Integer
							.toString(b)));
					if (coin[i] == 1) {
						decimalValue = decimalValue.add(BigInteger.ONE);
					}
				}

				if (decimalValue.isProbablePrime(1)) {
					isJamCoin = false;
					break;
				}

				BigInteger divisor = findFactor(decimalValue);
				decimals[b - 2] = decimalValue;
				divisors[b - 2] = divisor;
			}

			if (isJamCoin) {
				StringBuilder sb = new StringBuilder();
				for (int digit : coin) {
					sb.append(digit);
				}
				ret.put(sb.toString(),
						new ArrayList<BigInteger>(Arrays.asList(divisors)));
			}

			if (ret.size() == j) {
				break;
			}
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

	private static BigInteger findFactor(BigInteger num) {
		final BigInteger two = BigInteger.valueOf(2);
		BigInteger divisor;
		BigInteger c = BigInteger.ONE;
		BigInteger t = two;
		BigInteger h = t;

		if (num.mod(two).compareTo(BigInteger.ZERO) == 0)
			return two;

		do {
			t = t.multiply(t).mod(num).add(c).mod(num);
			h = h.multiply(h).mod(num).add(c).mod(num);
			h = h.multiply(h).mod(num).add(c).mod(num);
			divisor = t.subtract(h).gcd(num);
		} while ((divisor.compareTo(BigInteger.ONE)) == 0);

		return divisor;
	}
}
