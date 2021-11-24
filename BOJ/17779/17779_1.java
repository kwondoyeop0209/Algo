/**
* 메모리: 58404 KB, 시간: 404 ms
* 2021.11.24
* by Alub
*/
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int arr[][];
	static int res = Integer.MAX_VALUE;
	static int total = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());

		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				total += arr[i][j];
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					if (x + d1 >= N)
						break;
					for (int d2 = 1; d2 <= N; d2++) {
						if (x + d1 <= N && y - d1 > 0 && x + d2 <= N && y + d2 <= N && x + d1 + d2 <= N
								&& y - d1 + d2 <= N) {
							check(x, y, d1, d2);
						}
					}
				}
			}
		}
		System.out.println(res);

	}

	private static void check(int x, int y, int d1, int d2) {
		int sum[] = new int[5];
		boolean line[][] = new boolean[N + 1][N + 1];
		int check[][] = new int[N + 1][N + 1];

		for (int i = 0; i <= d1; i++) {
			line[x + i][y - i] = true;
			line[x + i + d2][y - i + d2] = true;
		}

		for (int i = 0; i <= d2; i++) {
			line[x + i][y + i] = true;
			line[x + i + d1][y + i - d1] = true;
		}


		for (int i = 1; i < x + d1; i++) {
			for (int j = 1; j <= y; j++) {
				if (line[i][j])
					break;
				sum[0] += arr[i][j];
				check[i][j] = 1;
			}
		}
		for (int i = 1; i <= x + d2; i++) {
			for (int j = N; j > y; j--) {
				if (line[i][j])
					break;
				sum[1] += arr[i][j];
				check[i][j] = 2;
			}
		}

		for (int i = N; i >= x + d1; i--) {
			for (int j = 1; j < y - d1 + d2; j++) {
				if (line[i][j])
					break;
				sum[2] += arr[i][j];
				check[i][j] = 3;
			}
		}

		for (int i = N; i > x + d2; i--) {
			for (int j = N; j >= y - d1 + d2; j--) {
				if (line[i][j])
					break;
				sum[3] += arr[i][j];
				check[i][j] = 4;
			}
		}

		sum[4] = total;

		for (int i = 0; i < 4; i++) {
			sum[4] -= sum[i];
		}

		Arrays.sort(sum);

		res = Math.min(sum[4] - sum[0], res);
	}
}
