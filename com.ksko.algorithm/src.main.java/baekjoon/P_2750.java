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
		
		//�Ǻ� ó������ �Ѵ�.
		int pivot = left;
		int l = pivot+1;
		int r = right;
		
		//������ ������ �����ʺ��� �۾ƾ��Ѵ�.(�ε�����)
		while(l<=r) {
			
			//l<=right �� r>=pivot �� �����... l�� Ŀ���ٰ� ���� �Ѿ���� �ֱ⋚���̴�! 5 4 3 2 1 �� ��� �Ǻ� 5���� ū�Գ��Ë����� ã�µ� ū�� �����ϱ�..
			while(l<=right && array[l] <= array[pivot]) {
				l++;
			}
			
			//�Ǻ��� ������ ����� �� �� ����. 
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

