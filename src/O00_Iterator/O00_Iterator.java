package O00_Iterator;

import java.io.*;
import java.util.*;

/* Iterator : ��� �÷���Ŭ�������� �����͸� ���� �� ���
 *  ����Ʈ�� ���� �޾� ���������� ���� ���
 * 	����, ǥ��ȭ�� �ȵǸ� ��� �÷���Ŭ�������� �����͸� �д� �޼��带 ������ �˾ƾ��ϰ�
 * 	���� �÷��ǿ� ������ �������
 * 
 * 	1. hasNext : ���� �����Ͱ� �ִ��� boolean (true/false)�� ��ȯ
 * 	2. next : ���� ��Ҹ� ��Ÿ��
 *  3. remove : next �޼��尡 ȣ���� �����͸� ����
 */

public class O00_Iterator {
	static class PhoneInfo {
		String name;
		int age;

		public PhoneInfo(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void showInfo() {
			System.out.println("�̸� : " + name);
			System.out.println("���� : " + age);
		}
	}

	public static void main(String[] args) {
		LinkedList<PhoneInfo> ll = new LinkedList<>();
		ll.add(new PhoneInfo("ȫ�浿", 20));
		ll.add(new PhoneInfo("��ҿ�", 22));
		ll.add(new PhoneInfo("������", 30));

		Iterator<PhoneInfo> it = ll.iterator();
		PhoneInfo info = null;

		System.out.println("Iterator �ݺ��ڸ� �̿��Ͽ� ��°� ���ÿ� ����");
		while (it.hasNext()) {
			info = it.next();
			info.showInfo();
			if ("ȫ�浿".compareTo((info.getName())) == 0) {
				it.remove();
			}
		}

		System.out.println("\n<���� �� ���>");
		it = ll.iterator();
		while (it.hasNext()) {
			info = it.next();
			info.showInfo();
		}

		System.out.println("\n<phoneInfo ù ���� name �������� ���>");
		it = ll.descendingIterator();
		while (it.hasNext()) {
			info = it.next();
			info.showInfo();
		}
	}
}