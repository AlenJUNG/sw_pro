package D01_우선순위큐;
// https://coding-factory.tistory.com/603

// 1. 높은 우선순위의 요소를 먼저 꺼내서 처리하는 구조 (큐에 들어가는 원소는 비교가 가능한 기준이 있어야함) 
// 2. 내부 요소는 힙으로 구성되어 이진트리 구조로 이루어져 있음   
// 3. 내부구조가 힙으로 구성되어 있기에 시간 복잡도는 O(NLogN)
// 4. 응급실과 같이 우선순위를 중요시해야 하는 상황에서 쓰임

import java.util.*;
import java.io.*;

public class D01_우선순위큐 {
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

		// distance가 짧은 기준
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
		// pq에 각기 다른 (idx, distance) 정보 4번 입력
		pq.offer(new Node(1, 20));
		pq.offer(new Node(2, 2));
		pq.offer(new Node(3, 150));
		pq.offer(new Node(4, 8));

		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		// 정보가 어떻게 나오는가?

		System.out.println();

		// pq에 동일한 (idx, distance) 정보 4번 입력
		pq.offer(new Node(1, 20));
		pq.offer(new Node(2, 2));
		pq.offer(new Node(3, 150));
		pq.offer(new Node(4, 8));

		// distance 오름차순 기준으로 pq.poll이 됨을 알 수 있음
		for (int i = 1; i <= 4; i++) {
			Node node = pq.poll();
			int idx = node.getIdx();
			int distance = node.getDis();
			System.out.printf("#%d idx = %d, distance = %d\n", i, idx, distance);
		}

	}

}
