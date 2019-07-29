package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_2750 {

	static int[] array;

	public static void main(String[] args) throws IOException {
		// 
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int n = Integer.parseInt(bf.readLine());
		array = new int[n];
		
		//setting
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(bf.readLine());
		}
		
		sort(0,(n-1));
			
		for (int i = 0; i < n; i++) {
			
			System.out.println(array[i]);
		}
		
	}
	
	
	public static void sort(int left, int right) {
		
		//피봇 처음으로 한다.
		int pivot = left;
		int l = pivot+1;
		int r = right;
		
		//왼쪽은 무조건 오른쪽보다 작아야한다.(인덱스가)
		while(l<=r) {
			
			//l<=right 랑 r>=pivot 의 존재는... l이 커지다가 끝을 넘어갈수도 있기떄문이다! 5 4 3 2 1 의 경우 피봇 5보다 큰게나올떄까지 찾는데 큰게 없으니까..
			while(l<=right && array[l] <= array[pivot]) {
				l++;
			}
			
			//피봇은 정렬의 대상이 될 수 없다. 
			while(r >= pivot+1 && array[r] >= array[pivot]) {
				r--;
			}
			
			if(l<r) {
				swap(l,r);
			}else {
				swap(pivot, r);
			}
			
			sort(left, r-1);
			sort(r+1, right);
		}
		
		
	}
	
	public static void swap(int a, int b) {
	
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
		
	}

}

