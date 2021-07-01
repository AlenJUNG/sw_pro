package D04_인덱스트리;

import java.io.*;
import java.util.*;

public class D04_ex08_P0075_공놀이 {
	static class Node implements Comparable<Node>{
		int id, type, value;
		public Node(int id, int type, int value) {
			this.id = id;
			this.type = type;
			this.value = value;
			
		}
		@Override
		public int compareTo(Node other) {
			if(this.value < other.value) {
				return -1;
			}
			return 1;
		}
	}
	static int TC, N, Q, size;
	static int tree_A[], tree_B[];
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex08_P0075_공놀이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			pq = new PriorityQueue<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				int a = Integer.parseInt(st.nextToken());
				pq.offer(new Node(0, 0, a));
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				int b = Integer.parseInt(st.nextToken());
				pq.offer(new Node(0, 1, b));
			}
			
			size = 1;
			while(size > N * 2) {
				size *= 2;
			}
			
			tree_A = new int[size * 2];
			tree_B = new int[size * 2];
			
			int no = 1;
			int temp = 0;
			
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				// 처음이면 전과 비교할 필요 없음
				// type이 0이면 A, 1이면 B
				if(no == 1) {
					if(now.type == 0) {						
						update(tree_A, no, 1);
						no++;
						temp = now.value;
						continue;
					}else {
						update(tree_B, no, 1);
						no++;
						temp = now.value;
						continue;
					}
				}else {
					
					
				}
			}
		
			
			
		}
		
		br.close();
		bw.flush();
		bw.close();

	}

}

