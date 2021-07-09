package G05_Kruskal;

import java.util.*;
import java.io.*;

/*
 * �Ͻ� : 2021.07.07
 * �õ� : 2
 * �߿�) ������ ���⼺�� �ִ� ���, Ʈ���� Ư¡
 * 1. ���� ������ in-degree�� root ���� 0�̸� �������� ��� 1
 * 2. ��忡�� ������ out-degree�� leaf ��忡�� 0�̸� �������� 1 �̻�
 */

public class G05_ex13_����P0001_���ڴ»��̿� {
	static int TC, N, parent[], indegree[], ansS, ansE;

	static class Node {
		int start, end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static ArrayList<Node> al;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex13_����P0001_���ڴ»��̿�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			parent = new int[N + 1];
			indegree = new int[N + 1];
			al = new ArrayList<Node>();
			ansS = ansE = 0;

			int a, b;
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				al.add(new Node(a, b));
				indegree[b]++; // * �ٽ��ڵ� : indegree�� ���
			}

			// 1. indegree�� 2�� �ƴ� ��� ���� �����
			for (int i = 0; i < al.size(); i++) {
				// * ���� Node�� ������ �̻��ϰ� ������
				int start = al.get(i).start;
				int end = al.get(i).end;
				if (indegree[end] != 2) {
					// ����Ŭ�� �ִٸ�
					if (find(start) == find(end)) {
						ansS = start;	// * ���� ������(find(start)) �ƴ϶� start
						ansE = end;
						// ������ ���̱⿡ break�� ���� ����
					} else {
						union(start, end);
					}
				}
			}

			// 2. 1. ��� ���� ���� indegree�� 2�� ��찡 �����ִٸ� �߰� ���
			for (int i = 0; i < al.size(); i++) {
				int start = al.get(i).start;
				int end = al.get(i).end;
				if (indegree[end] == 2) {
					// ����Ŭ�� �ִٸ�
					if (find(start) == find(end)) {
						ansS = start;
						ansE = end;
					} else {
						union(start, end);
					}
				}
			}

			bw.write("#" + tc + " " + ansS + " " + ansE + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
