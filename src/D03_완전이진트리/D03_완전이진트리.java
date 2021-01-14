package D03_��������Ʈ��;

import java.util.*;
import java.io.*;

/*  Complete Binary Tree
   	���� : ���� Ʈ���� �� ����
		1. ������ ������ �����ϰ�� ��� ��尡 �� ä���� �ְ�
		2. ������ ������ ��尡 ���� ���ʺ��� ��ĭ���� ä���� �ִ� ������ Ʈ��
	
	ǥ�� : 1���� �迭 > ���ʿ��� ���������� �����ϸ� 1���� ����
		Parent(X) = X/2;
		left_child(X) = X*2;
		right_child(X) = X*2 + 1;
	
	* ���� : 1���� �迭�� 1���� ������, 0���� �����ϸ� ���� �ʿ� */

// Q) ������ȸ�� Ž��

public class D03_��������Ʈ�� {
	static int N, tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D03_��������Ʈ��/D03_��������Ʈ��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		tree = new int[N + 1]; // * 1���� �迭�� 1���� �����Կ� ����

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}

		inorder(1); // ������ȸ ����

		br.close();

	}

	private static void inorder(int node) {
		if (node * 2 <= N) { // 1. ���� �ڽ� ���
			inorder(node * 2);
		}

		System.out.println(tree[node]); // 2. �ڽ� ���

		if (node * 2 + 1 <= N) { // 3. ������ �ڽ� ���
			inorder(node * 2 + 1);
		}
	}
}
