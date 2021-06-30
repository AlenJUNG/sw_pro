package D04_인덱스트리;

import java.io.*;
import java.util.*;

public class Solution_210630 {
	static class Tanks{
		int id, x, y, s;
		public Tanks(int id, int x, int y, int s) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
	static int TC, N, size;
	static Tanks tank[];
	static long tree[], ans;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex02_P0058_탱크_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		TC = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			tank = new Tanks[N + 1];
			int a, b, c;
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				tank[i] = new Tanks(i, a, b, c);
			}
			
			// 1. y 내림차순 정렬
			Arrays.sort(tank, 1, N + 1, new Comparator<Tanks>() {

				@Override
				public int compare(Tanks o1, Tanks o2) {
					if(o1.y > o2.y) {
						return -1;
					}
					return 1;
				}
			});
			
			// 2. 리라벨링
			for(int i = 1; i <= N; i++) {
				tank[i].id = i;
			}
			
			// 3. x 내림차순 정렬
			Arrays.sort(tank, 1, N + 1, new Comparator<Tanks>() {

				@Override
				public int compare(Tanks o1, Tanks o2) {
					if(o1.x > o2.x) {
						return -1;
					}
					return 1;
				}
			});
			
			size = 1;
			while(size < N) {
				size *= 2;
			}
			
			tree = new long[size * 2];
			
			ans = 0;
			for(int i = 1; i <= N; i++) {
				int idx = tank[i].id + size - 1;
				tree[idx] = tank[i].s;
				update(idx, tree[idx]);
				ans += getSum(1, tank[i].id - 1);
			}
			
			bw.write("#"+tc+" "+ans+"\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

	private static long getSum(int start, int end) {
		long res = 0;
		int s = start + size - 1;
		int e = end + size - 1;
		
		while(s <= e) {
			if(s % 2 == 1) {
				res += tree[s];
				s++;
			}
			if(e % 2 == 0) {
				res += tree[e];
				e--;
			}
			s /= 2;
			e /= 2;
			
		}
		return res;
	}

	private static void update(int idx, long x) {
		idx /= 2;
		while(idx > 0) {
			tree[idx] += x;
			idx /= 2;
		}		
	}

}
