package D02_Tree;

import java.util.*;
import java.io.*;

/* Tree : �ڷ�� ������ ������ ���踦 ��Ÿ���µ� ����ϴ� �ڷᱸ��. 
 *        �⺻������ 1�� �̻��� ��带 ������ ����
 *  1.��Ʈ ��尡 ����.
 *	2.����Ŭ�� �������� ����. 
 * 	3.�ٸ� ������ ���Ұ� �ߺ����� �ʴ� n���� �κ� Ʈ���� ������ ���� ������ ��Ʈ�� �κ� Ʈ��.
 */

/* BinarySearchTree : Java������ TreeSet�� TreeMap ���
 * 	1.��� ���Ҵ� Ű�� ������, Ű�� ��ü Ʈ������ ������ ���� ����.
 *  2.���� �ڽ��� Ű ���� �θ𺸴� �۾ƾ� �Ѵ�.
 *  3.������ �ڽ��� Ű ���� �θ𺸴� Ŀ�� �Ѵ�.
 *  4.BST�� �� �κ�Ʈ���� BST. * 
 */

/* * ���� *
 * BST�� ������ set, map �ڷᱸ���� 
 * ����� �߰� �� ������ �ɸ��� �ð����⵵�� 
 * O(logN)�� �ڷᱸ���� �Ϲ������� HashMap���� ������.
 */

public class D02_BinarySearchTree {

	public static void main(String[] args) throws IOException {

		TreeSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2; // �������� ����
			}
		});

		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2; // �������� ����
			}
		});

		int keys[] = { 1, 4, 2, 5, 7, 3, 1 };
		int values[] = { 2, 3, 1, 11, 1, 4, 1 };

		for (int i = 0; i < keys.length; i++) {
			set.add(keys[i]);
			map.put(keys[i], values[i]);
		}

		// TreeSet�� �� 1�� ���ԵǾ� �ִٸ� �� 1 ���� > index 1�� �ƴ�
		if (set.contains(1)) {
			set.remove(1);
		}

		// TreeSet ��� �� ���
		for (int key : set) {
			System.out.print(key + " ");
		}
		System.out.println();

		// TreeMap�� key�� 5�� ���ԵǾ� �ִٸ� key�� 5 + value 11 ����
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