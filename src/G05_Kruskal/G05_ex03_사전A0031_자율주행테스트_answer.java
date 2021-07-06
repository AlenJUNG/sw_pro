package G05_Kruskal;

public class G05_ex03_»çÀüA0031_ÀÚÀ²ÁÖÇàÅ×½ºÆ®_answer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}



import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.FileInputStream;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Collections;

import java.util.LinkedList;

import java.util.StringTokenizer;

  

public class Solution {

  

    static int T, N, M;

    static int Start, End;

    static ArrayList<Edge> edges = new ArrayList<Edge>();

    static ArrayList<Integer> ss = new ArrayList<Integer>();

    static int min;

    static int par[];

  

    public static void main(String args[]) throws Exception {

  

        //System.setIn(new FileInputStream("src/sample.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

  

        T = Integer.parseInt(st.nextToken());

  

        for (int tc = 1; tc <= T; tc++) {

  

            st = new StringTokenizer(br.readLine());

  

            N = Integer.parseInt(st.nextToken());

            M = Integer.parseInt(st.nextToken());

  

            edges.clear();

            ss.clear();

            par = new int[N + 1];

  

            for (int i = 1; i <= N; i++)

                par[i] = i;

  

            int a, b, s;

            for (int i = 1; i <= M; i++) {

                st = new StringTokenizer(br.readLine());

  

                a = Integer.parseInt(st.nextToken());

                b = Integer.parseInt(st.nextToken());

                s = Integer.parseInt(st.nextToken());

  

                edges.add(new Edge(a, b, s));

  

            }

  

            st = new StringTokenizer(br.readLine());

  

            Start = Integer.parseInt(st.nextToken());

            End = Integer.parseInt(st.nextToken());

  

            Collections.sort(edges);

  

             

            int i = 0, j = 0, k = 0;

            min = Integer.MAX_VALUE;

  

            for (k = 0; k < edges.size(); k++) {   //k : 탐색 시작점

  

                for (j = 1; j <= N; j++) {

                    //부모배열 자기자신으로 초기화

                    par[j] = j;

                }

 

                // Kruskal 알고리즘 구현부분

                for (i = k; i < edges.size(); i++) { // k 보다 작은 값 패스. k 부터 오름차순으로 확인

                    Edge e = edges.get(i);

  

                    if (find(e.a) != find(e.b)) {

  

                        union(e.a, e.b); // 트리로 연결되지 않은 간선 -> 연결시켜주자

  

                        if (find(Start) == find(End))  // 고정된 최소값 (k) 가정하에 출발점과 도착점이 연결됨!

                            break;

                    }

                }

 

                //모든 간선을 돌았는데도 연결이 안됬으면 실패!

                if (find(Start) != find(End))

                    break;

  

                for (j = 1; j <= N; j++) {

                    //다시 부모배열 자기자신으로 초기화

                    par[j] = j;

                }

                for (j = i; j >= k; j--) { //역추적 하면서 min값 구하기

                    // 최초로 연결한 간선번호 i ~ 현재 간선번호 k

                    Edge e = edges.get(j);

  

                    if (find(e.a) != find(e.b)) {  //아직 연결 안됬으면

                         

                        union(e.a, e.b); //연결

  

                        if (find(Start) == find(End))  //연결되면 탈출

                            break;

                    }

                }

                if (find(Start) == find(End)) {   //연결되어 있으면

                    min = Math.min(min, edges.get(i).s - edges.get(j).s); //차이의 최소값 갱신

                    if (min == 0) //차이가 0이 나오면 최적의 값 이므로 탈출!

                        break;

                    k = j + 1; //역추적해서 찾은 연결간선 순번부터 다음 진행

                }

            }

            System.out.println("#" + tc + " " + min);

        }

  

    }

  

    static void union(int a, int b) {

        par[find(a)] = find(b);

    }

  

    static int find(int i) {

        if (par[i] == i)

            return i;

        return par[i] = find(par[i]);

    }

  

    static class Edge implements Comparable<Edge> {

        int a, b;

        int s;

  

        Edge(int a, int b, int s) {

            this.a = a;

            this.b = b;

            this.s = s;

        }

  

        @Override

        public int compareTo(Edge arg0) {

            // TODO Auto-generated method stub

            return this.s - arg0.s;

        }

    }

  

}
