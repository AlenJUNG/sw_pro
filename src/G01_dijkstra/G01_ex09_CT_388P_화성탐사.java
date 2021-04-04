package G01_dijkstra;

import java.io.*;
import java.util.*;

public class G01_ex09_CT_388P_ȭ��Ž�� {
	static int move_x[] = { -1, 0, 0, 1 };
	static int move_y[] = { 0, -1, 1, 0 };

	// �Ÿ��� ª�� ���� ���� �켱������ �������� ����
	static class Node implements Comparable<Node> {
		int x, y, c;

		public Node(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public int compareTo(Node other) {
			if (this.c < other.c) {
				return -1;
			}
			return 1;
		}
	}

	static final int INF = 9 * 125 * 125;
	static int TC, N, ans;
	static int d[][], map[][]; // * Point ������ �°� �������ش�
	static boolean check[][];
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex09_CT_388P_ȭ��Ž��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N + 1][N + 1];
			d = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp; // * ��ü Map ���� �Է� x, y ��ǥ�� cost ���� �ȴ�
					d[i][j] = INF; // dijkstra�� ���� INF ó�� > ���� Arrays.fill �� ����
				}
			}

			// ���۳�� 1, 1���� ���ͽ�Ʈ�� ����
			dijkstra(1, 1);

			bw.write("#" + tc + " " + d[N][N] + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static void dijkstra(int start_x, int start_y) {
		pq = new PriorityQueue<>();
		// Node(x, y, map[x][y])
		pq.offer(new Node(start_x, start_y, map[start_x][start_y]));
		// cost�� Ȯ�� �迭�� d�迭 �ʱ�ȭ = map[x][y]
		d[start_x][start_y] = map[start_x][start_y];
		check = new boolean[N + 1][N + 1];

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			// �̹� ó���� ����� ����
			if (check[now.x][now.y]) {
				continue;
			}
			check[now.x][now.y] = true;

			// ���� ���� ������ ���� Ȯ��
			for (int i = 0; i < 4; i++) {
				int nx = now.x + move_x[i];
				int ny = now.y + move_y[i];
				
				// ���� ������ ����� ���� ����
				if (nx < 1 || nx > N || ny < 1 || ny > N) {
					continue;
				}

				// ���� ��带 ����, �ٸ� ���� �̵��ϴ� �Ÿ��� �� ª�� ���
				if (d[nx][ny] > now.c + map[nx][ny]) {
					d[nx][ny] = now.c + map[nx][ny];
					pq.offer(new Node(nx, ny, d[nx][ny]));
				}
			}
		}

	}
}
