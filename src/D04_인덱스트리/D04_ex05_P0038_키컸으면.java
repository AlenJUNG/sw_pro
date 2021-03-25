package D04_ÀÎµ¦½ºÆ®¸®;

import java.io.*;
import java.util.*;

public class D04_ex05_P0038_Å°ÄÇÀ¸¸é {
	static class Node{
		int s, e, i, h, t;
		public Node(int s, int e, int i, int h, int t) {
			this.s = s;
			this.e = e;
			this.i = i;
			this.h = h;
			this.t = t;
		}
	}
	
	static int T, N, Q, ans;
	static Node arr[];
	static int idx_Tree[];
	
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/D04_ÀÎµ¦½ºÆ®¸®/D04_ex05_P0038_Å°ÄÇÀ¸¸é.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i <= N - 1; i++) {
				int height = Integer.parseInt(st.nextToken());
				arr[i] = new Node(0, 0, i, height, 0);
			}
			
			int S, E, value;
			for(int i = N; i <= N + Q - 1; i++) {
				st = new StringTokenizer(br.readLine());
				S = Integer.parseInt(st.nextToken());
				E = Integer.parseInt(st.nextToken());
				value = Integer.parseInt(st.nextToken());
				arr[i] = new Node(S, E, i, value, 1);
			}
			
			System.out.println("O");
			
			Arrays.sort(arr, 0, N + Q, new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					if(o1.h > o2.h) {
						return -1;
					}else if(o1.h == o2.h) {
						if(o2.t > o1.t) {
							return -1;
						}
					}
					return 1;
				}
			});
			
			System.out.println("O");
			
			
		}
		
		
		
		br.close();
		bw.flush();
		bw.close();
	}

}
