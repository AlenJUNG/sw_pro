package D02_Tree;

import java.util.*;
import java.io.*;

/* Tree : 자료들 사이의 계층적 관계를 나타내는데 사용하는 자료구조. 
 *        기본적으로 1개 이상의 노드를 가지는 집합
 *  1.루트 노드가 존재.
 *	2.사이클이 존재하지 않음. 
 * 	3.다른 노드들은 원소가 중복되지 않는 n개의 부분 트리로 나누어 지며 각각은 루트의 부분 트리.
 */

/* BinarySearchTree : Java에서는 TreeSet과 TreeMap 사용
 * 	1.모든 원소는 키를 가지며, 키는 전체 트리에서 유일한 값을 가짐.
 *  2.왼쪽 자식의 키 값은 부모보다 작아야 한다.
 *  3.오른쪽 자식의 키 값은 부모보다 커야 한다.
 *  4.BST는 그 부분트리도 BST. * 
 */

/* * 주의 *
 * BST로 구현된 set, map 자료구조는 
 * 노드의 추가 및 삭제에 걸리는 시간복잡도가 
 * O(logN)인 자료구조로 일반적으로 HashMap보다 느리다.
 */

public class D02_BinarySearchTree {

	public static void main(String[] args) throws IOException {

		TreeSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2; // 내림차순 정렬
			}
		});

		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2; // 내림차순 정렬
			}
		});

		int keys[] = { 1, 4, 2, 5, 7, 3, 1 };
		int values[] = { 2, 3, 1, 11, 1, 4, 1 };

		for (int i = 0; i < keys.length; i++) {
			set.add(keys[i]);
			map.put(keys[i], values[i]);
		}

		// TreeSet에 값 1이 포함되어 있다면 값 1 삭제 > index 1이 아님
		if (set.contains(1)) {
			set.remove(1);
		}

		// TreeSet 모든 값 출력
		for (int key : set) {
			System.out.print(key + " ");
		}
		System.out.println();

		// TreeMap에 key값 5가 포함되어 있다면 key값 5 + value 11 삭제
		if (map.containsKey(5)) {
			map.remove(5);
		}

		Iterator<Integer> it = map.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			int value = map.get(key);

			System.out.print("(" + key + ", " + value + ") ");
		}

	}

}
