package D02_Tree;

import java.util.*;
import java.io.*;

// https://coding-factory.tistory.com/557

/* TreeMap은 이진트리를 기반으로 한 Map 컬렉션

	같은 Tree구조로 이루어진 TreeSet과의 차이점은 
	1. TreeSet은 그냥 값만 저장한다면 
	2. TreeMap은 키와 값이 저장된 Map, Etnry를 저장함 
	
	TreeMap에 객체를 저장하면 자동으로 정렬되는데, 
	1. 키는 저장과 동시에 자동 오름차순으로 정렬되고 
	2. 숫자 타입일 경우에는 값으로, 
	3. 문자열 타입일 경우에는 유니코드로 정렬함
	
	정렬 순서는 기본적으로 부모 키값과 비교해서 
	키 값이 낮은 것은 왼쪽 자식 노드에 
	키값이 높은 것은 오른쪽 자식 노드에 
	Map.Entry 객체를 저장
	
	TreeMap은 일반적으로 Map으로써의 성능이 HashMap보다 떨어짐 
	장점 : 하지만 정렬된 상태로 Map을 유지해야 하거나 
		  정렬된 데이터를 조회해야 하는 범위 검색이 필요한 경우, 
		  TreeMap을 사용하는 것이 효율성 ↑
	
	단점 : TreeMap은 데이터를 저장할 때 즉시 정렬하기에 
		  추가나 삭제가 HashMap보다 오래 걸립니다. */

public class D02_ex02_TreeMap {

	public static void main(String[] args) {
		// TreeMap 선언
		TreeMap<Integer, String> map1 = new TreeMap<Integer, String>(); // TreeMap생성
		TreeMap<Integer, String> map2 = new TreeMap<>(); // new에서 타입 파라미터 생략가능
		TreeMap<Integer, String> map3 = new TreeMap<>(map1); // map1의 모든 값을 가진 TreeMap생성
		// 초기값 설정
		TreeMap<Integer, String> map6 = new TreeMap<Integer, String>() {
			{
				put(1, "a");
			}
		};

		// TreeMap생성
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		// 값 추가
		map.put(1, "사과");
		map.put(2, "복숭아");
		map.put(3, "수박");

		System.out.println(map);

		map = new TreeMap<>() {
			{
				put(1, "복숭아");
				put(2, "수박");
				put(3, "사과");

			}
		};

		System.out.println(map);

		map.remove(1);
		System.out.println(map);
		map.clear();
		System.out.println(map);

		map = new TreeMap<>() {
			{
				put(1, "배");
				put(2, "멜론");
				put(3, "딸기");
			}
		};

		System.out.println("map > " + map);
		System.out.println("map.get(1) > " + map.get(1));
		System.out.println("map.firstEntry > " + map.firstEntry());
		System.out.println("map.firstKey > " + map.firstKey());
		System.out.println("map.lastEntry > " + map.lastEntry());
		System.out.println("map.lastKey > " + map.lastKey());

		// 초기값 설정
		TreeMap<Integer, String> tm = new TreeMap<Integer, String>() {
			{
				put(1, "사과"); // 값 추가
				put(2, "복숭아");
				put(3, "수박");
			}
		};

		// 전체값 출력 방법 1 : Use keySet
		for (Integer i : tm.keySet()) {
			System.out.println("Use keySet > [Key]:" + i + " [Value]:" + tm.get(i));
		}

		// 전체값 출력 방법 2 : Use Iterator
		Iterator<Integer> keys = tm.keySet().iterator();
		while (keys.hasNext()) {
			int key = keys.next();
			System.out.println("Use Iterator > [Key]:" + key + " [Value]:" + tm.get(key));
		}

	}

}
