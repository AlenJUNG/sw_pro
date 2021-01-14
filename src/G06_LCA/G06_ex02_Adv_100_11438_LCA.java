package G06_LCA;
import java.io.*;
import java.util.*;

// https://code0xff.tistory.com/135
// https://www.acmicpc.net/problem/11438

public class G06_ex02_Adv_100_11438_LCA {
	static int MAX_N = 100000;	//	�������� ���� �ִ� �Է�ġ �Է�, ���� ���� ����
	static int MAX_D = 17;	//	// 2^n ���� MAX_N�� �ѵ��� ����
	static int N, M;		//	N : ����� ����, M : ���� ������ �˰���� ���� ����
	static int parent[][];	// �θ� ����
	static int depth[];		// �� �������� ���� > 0���� ����
	static ArrayList<Integer> wire[];	// �׷��� ���� : �� �迭�� ArrayList �Ҵ�
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex02_Adv_100_11438_LCA.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[MAX_D + 1][MAX_N + 1];
		depth = new int[N + 1];
		
		wire = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			wire[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			wire[a].add(b);
			wire[b].add(a);
		}

		/********************** �Էº� �� **********************/
				
		// DFS or BFS ����
		// STEP 01. ��� ��忡 ���� ���� �˻�/�� ����
//		DFS(1, 0);
		BFS(1, 0);
		
		// STEP 02. �ּ� ���� ������ ã�� �� ��� Ȯ�� > LCA ���� �� ���
		M = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			bw.write(LCA(a, b)+"\n");
		}		
		
		/****************** ����� close ó�� ******************/
		br.close();
		bw.flush();
		bw.close();

	}
	
	// STEP 01 : ��� ��忡 ���� ����(depth) ���
	private static void BFS(int node, int dep) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(node);			// ���� node q�� �ֱ�
		parent[0][node] = 0;	// ���� node �θ� ���� 0 �Է�
		depth[node] = dep;		// ���� node ���� ���� 0 �Է�
		
		while(!q.isEmpty()) {	// * ! �� Ȯ���ϱ�
			int v = q.poll();
			
			for(int next : wire[v]) {
				if(next == parent[0][v]) {	// v�� ���� ������ �θ�� continue
					continue;
				}
				// v�� ���� ������ �θ� �ƴ� ���
				q.offer(next);				//	�������� q�� �ֱ�
				parent[0][next] = v;		//	���������� �ٷ� �� �θ������� parent[2^0��]�� �Է� 
				depth[next] = depth[v] + 1;	//  ** �߿� : �������� ���� ���� �Է�
				
				// DP : �� ������ �Ÿ��� �θ� ���� �Է�
				for(int i = 1; i <= MAX_D; i++) {
					// ���������� �θ������� 0�� ��� = root�� ��� > break
					if(parent[i - 1][next] == 0) {
						break;
					}
					// ���������� root�� �ƴ� ��� > ��ȭ�� ����
					parent[i][next] = parent[i - 1][ parent[i - 1][next] ];
				}				
			}
		}		
	}
	
	// STEP 01 : ��� ��忡 ���� ����(depth) ���
	private static void DFS(int v, int dep) {
		// �������� ������ ��尡 �ƴ� ��� return ó�� 
		if(depth[v] != -1) {
			return;
		}
						
		depth[v] = dep;	// ������ depth�� tree �迭�� �Է�
		
		// A. ������ ����Ǿ� �ִ� ��� �����鿡 ���Ͽ�
		for(int next : wire[v]) {
			if(depth[next] != -1) {	// B. �̹湮 ������ �ƴϸ� PASS
				continue;
			}
			
			parent[0][next] = v;	// C. �θ� ���� ���� : next�� �θ� ������ parent[0][next]�� ����
			
			// DP
			for(int i = 1; i <= MAX_D ; i++) {
				if(parent[i - 1][next] == 0) {	// �θ������� 0�̶�� break
					break;
				}
				// �θ������� 0�� �ƴ� ���
				parent[i][next] = parent[i - 1][ parent[i - 1][next] ];
			}
			
			DFS(next, dep + 1);
		}
	}
	
	// STEP 02 : �ּҰ��������� ã�� �� ��� Ȯ��
	private static int LCA(int a, int b) {
		// A. �� ����� depth ���߱�
			// A-1 : b�� �� ���� ����
		if(depth[a] > depth[b]) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		
			// A-2 : ���� ���̰� �����ϵ��� ����
		for(int i = MAX_D; i >= 0; i--) {
			if(depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[i][b];
			}
		}
		
//		���� b�� ���� ������ �� ���� a�� b�� ���ٸ� a�� ��� �� return
		if(a == b) {
			return a;
		}
		
		// MAX_D > 0 ���� �θ� ���� ���� ��츦 ã�� �����ϱ�
		for(int i = MAX_D; i >= 0; i--) {
			if(parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		return parent[0][a];	// parent[0][b] �� return �ص� �������
	}

}
