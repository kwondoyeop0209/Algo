/**
* 메모리: 84880 KB, 시간: 904 ms
* 2021.11.26
* by Alub
*/
import java.io.*;
import java.util.*;

public class Main {

	public static class Ball implements Comparable<Ball> {

		int color;
		int size;
		int index;

		public Ball(int color, int size, int index) {
			super();
			this.color = color;
			this.size = size;
			this.index = index;
		}

		@Override
		public int compareTo(Ball o) {
			return this.size - o.size;
		}

	}

	static int N;
	static ArrayList<Ball> list = new ArrayList<>();
	static int res[];
	static int color[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		color = new int[N + 1];
		res = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			Ball ball = new Ball(color, size, i);
			list.add(ball);
		}

		Collections.sort(list);

		int total = 0;
		int same = 0;
		for (int i = 0; i < list.size(); i++) {
			Ball now = list.get(i);
			Ball before = list.get(same);
			while (now.size > before.size) {
				total += before.size;
				color[before.color] += before.size;
				before = list.get(++same);
			}
			res[now.index] = total - color[now.color];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(res[i]).append("\n");
		}
		System.out.println(sb);
	}
}
