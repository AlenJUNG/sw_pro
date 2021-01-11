package D01_�켱����ť;
// https://coding-factory.tistory.com/603

// 1. ���� �켱������ ��Ҹ� ���� ������ ó���ϴ� ���� (ť�� ���� ���Ҵ� �񱳰� ������ ������ �־����) 
// 2. ���� ��Ҵ� ������ �����Ǿ� ����Ʈ�� ������ �̷���� ����   
// 3. ���α����� ������ �����Ǿ� �ֱ⿡ �ð� ���⵵�� O(NLogN)
// 4. ���޽ǰ� ���� �켱������ �߿���ؾ� �ϴ� ��Ȳ���� ����

import java.util.*;
import java.io.*;

public class D01_�켱����ť {
	static class Node implements Comparable<Node> {
		int idx, dis;

		public Node(int idx, int dis) {
			this.idx = idx;
			this.dis = dis;
		}

		public int getIdx() {
			return this.idx;
		}

		public int getDis() {
			return this.dis;
		}

		// distance�� ª�� ����
		@Override
		public int compareTo(Node others) {
			if (this.dis < others.dis) {
				return -1;
			}
			return 1;
		}

	}

	public static void main(String[] args) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// pq�� ���� �ٸ� (idx, distance) ���� 4�� �Է�
		pq.offer(new Node(1, 20));
		pq.offer(new Node(2, 2));
		pq.offer(new Node(3, 150));
		pq.offer(new Node(4, 8));

		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		// ������ ��� �����°�?

		System.out.println();

		// pq�� ������ (idx, distance) ���� 4�� �Է�
		pq.offer(new Node(1, 20));
		pq.offer(new Node(2, 2));
		pq.offer(new Node(3, 150));
		pq.offer(new Node(4, 8));

		// distance �������� �������� pq.poll�� ���� �� �� ����
		for (int i = 1; i <= 4; i++) {
			Node node = pq.poll();
			int idx = node.getIdx();
			int distance = node.getDis();
			System.out.printf("#%d idx = %d, distance = %d\n", i, idx, distance);
		}

	}

}
