package D02_Tree;

import java.util.*;
import java.io.*;

// https://coding-factory.tistory.com/557

/* TreeMap�� ����Ʈ���� ������� �� Map �÷���

	���� Tree������ �̷���� TreeSet���� �������� 
	1. TreeSet�� �׳� ���� �����Ѵٸ� 
	2. TreeMap�� Ű�� ���� ����� Map, Etnry�� ������ 
	
	TreeMap�� ��ü�� �����ϸ� �ڵ����� ���ĵǴµ�, 
	1. Ű�� ����� ���ÿ� �ڵ� ������������ ���ĵǰ� 
	2. ���� Ÿ���� ��쿡�� ������, 
	3. ���ڿ� Ÿ���� ��쿡�� �����ڵ�� ������
	
	���� ������ �⺻������ �θ� Ű���� ���ؼ� 
	Ű ���� ���� ���� ���� �ڽ� ��忡 
	Ű���� ���� ���� ������ �ڽ� ��忡 
	Map.Entry ��ü�� ����
	
	TreeMap�� �Ϲ������� Map���ν��� ������ HashMap���� ������ 
	���� : ������ ���ĵ� ���·� Map�� �����ؾ� �ϰų� 
		  ���ĵ� �����͸� ��ȸ�ؾ� �ϴ� ���� �˻��� �ʿ��� ���, 
		  TreeMap�� ����ϴ� ���� ȿ���� ��
	
	���� : TreeMap�� �����͸� ������ �� ��� �����ϱ⿡ 
		  �߰��� ������ HashMap���� ���� �ɸ��ϴ�. */

public class D02_ex02_TreeMap {

	public static void main(String[] args) {
		// TreeMap ����
		TreeMap<Integer, String> map1 = new TreeMap<Integer, String>(); // TreeMap����
		TreeMap<Integer, String> map2 = new TreeMap<>(); // new���� Ÿ�� �Ķ���� ��������
		TreeMap<Integer, String> map3 = new TreeMap<>(map1); // map1�� ��� ���� ���� TreeMap����
		// �ʱⰪ ����
		TreeMap<Integer, String> map6 = new TreeMap<Integer, String>() {
			{
				put(1, "a");
			}
		};

		// TreeMap����
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		// �� �߰�
		map.put(1, "���");
		map.put(2, "������");
		map.put(3, "����");

		System.out.println(map);

		map = new TreeMap<>() {
			{
				put(1, "������");
				put(2, "����");
				put(3, "���");

			}
		};

		System.out.println(map);

		map.remove(1);
		System.out.println(map);
		map.clear();
		System.out.println(map);

		map = new TreeMap<>() {
			{
				put(1, "��");
				put(2, "���");
				put(3, "����");
			}
		};

		System.out.println("map > " + map);
		System.out.println("map.get(1) > " + map.get(1));
		System.out.println("map.firstEntry > " + map.firstEntry());
		System.out.println("map.firstKey > " + map.firstKey());
		System.out.println("map.lastEntry > " + map.lastEntry());
		System.out.println("map.lastKey > " + map.lastKey());

		// �ʱⰪ ����
		TreeMap<Integer, String> tm = new TreeMap<Integer, String>() {
			{
				put(1, "���"); // �� �߰�
				put(2, "������");
				put(3, "����");
			}
		};

		// ��ü�� ��� ��� 1 : Use keySet
		for (Integer i : tm.keySet()) {
			System.out.println("Use keySet > [Key]:" + i + " [Value]:" + tm.get(i));
		}

		// ��ü�� ��� ��� 2 : Use Iterator
		Iterator<Integer> keys = tm.keySet().iterator();
		while (keys.hasNext()) {
			int key = keys.next();
			System.out.println("Use Iterator > [Key]:" + key + " [Value]:" + tm.get(key));
		}

	}

}
