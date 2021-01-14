package D02_Tree;

import java.util.*;
import java.io.*;

// https://coding-factory.tistory.com/555

// TreeSet�� HashSet�� ���������� Set �������̽��� ������ Ŭ����
//  1. ��ü�� �ߺ��ؼ� ������ �� ���� 
//  2. ���� ������ �������� �ʴ´�

// But HashSet���� �޸� TreeSet�� ���� Ž�� Ʈ��(BinarySearchTree) ���� 
//  1. ���� Ž�� Ʈ���� �߰��� �������� �ð��� ���� �� �ɸ����� 
//  2. ����, �˻��� ���� ������ ���̴� �ڷᱸ��

// �׷��� HashSet���� �������� �߰��� ������ �ð��� �� �ɸ����� 
// ** �˻��� ���Ŀ��� �� ���� 

// TreeSet�� �����͸� ������ �� ����Ž��Ʈ��(BinarySearchTree)�� ���·� �����͸� �����ϱ⿡ 
//  1. �⺻������ nature ordering�� �����ϸ� 
//  2. �������� �Ű������� Comparator��ü�� �Է��Ͽ� ���� ����� ���� ���� ����

public class D02_ex01_TreeSet {

	public static void main(String[] args) {
		// TreeSet ����
		TreeSet<Integer> set1 = new TreeSet<Integer>(); // TreeSet ����
		TreeSet<Integer> set2 = new TreeSet<>(); // new���� Ÿ�� �Ķ���� ��������
		TreeSet<Integer> set3 = new TreeSet<Integer>(set1); // set1�� ��� ���� ���� TreeSet����
		TreeSet<Integer> set4 = new TreeSet<Integer>(Arrays.asList(1, 2, 3)); // �ʱⰪ ����

		TreeSet<Integer> set = new TreeSet<Integer>(); // TreeSet ����
		set.add(7);
		set.add(4);
		set.add(9);
		set.add(1);
		set.add(5);

		System.out.println(set); // �׳� ��� ��, ������������ �ڵ� ���

		set.remove(1); // index �ƴ϶� �� 1�� ������
		set.clear(); // ��� �� ����

		TreeSet<Integer> ts = new TreeSet<Integer>(Arrays.asList(1, 2, 3));
		System.out.println(ts.size());

		ts.clear();
		ts = new TreeSet<Integer>(Arrays.asList(4, 2, 3));
		System.out.println(ts); // �������� �ڵ� ���ĵǾ� ���
		System.out.println("ts.first() = " + ts.first()); // �ּҰ� ���
		System.out.println("ts.last() = " + ts.last()); // �ִ밪 ���
		System.out.println("ts.higher(3) = " + ts.higher(3)); // 3���� ū �� 1�� ���
		System.out.println("ts.lower(3) = " + ts.lower(3)); // 3���� ���� �� 1�� ���

		// * TreeSet���� ��ü ��ü�� ������� �� ���� �ݺ��ؼ� �������� �ݺ���(Iterator)�� ����
		Iterator iter = ts.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
