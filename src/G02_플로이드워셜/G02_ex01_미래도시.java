package G02_�÷��̵����;

import java.util.*;
import java.io.*;

public class G02_ex01_�̷����� {
	static final int INF = (int) 1e9; // ������ �ǹ��ϴ� ������ 10���� ����
	// ����� ����(N), ������ ����(M), ���� �� ���(MID), ���� ������ ���(END)
	static int START = 1;
	static int N, M, END, MID;
	static int graph[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G02_�÷��̵����/G02_ex01_�̷�����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];

		// �ִ� �Ÿ� ���̺��� ��� ���� + �ڱ� �ڽſ��� �ڱ� �ڽ����� ���� ����� 0���� �ʱ�ȭ
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (from == to) {
					graph[from][to] = 0;
				} else {
					graph[from][to] = INF;
				}
			}
		}

		// �� ������ ���� ������ �Է� �޾�, �� ������ �ʱ�ȭ
		for (int i = 1; i <= M; i++) {
			// A�� B�� ���ο��� ���� ����� 1�̶�� ����
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[from][to] = graph[to][from] = 1;
		}

		st = new StringTokenizer(br.readLine());
		END = Integer.parseInt(st.nextToken());
		MID = Integer.parseInt(st.nextToken());

		// ��ȭ�Ŀ� ���� �÷��̵� ���� �˰����� ����
		for (int mid = 1; mid <= N; mid++) {
			for (int from = 1; from <= N; from++) {
				for (int to = 1; to <= N; to++) {
//					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
					int cost = graph[from][mid] + graph[mid][to];
					if (graph[from][to] > cost) {
						graph[from][to] = cost;
					}
				}
			}
		}

		int ans = graph[1][MID] + graph[MID][END];

		if (ans >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}
}
