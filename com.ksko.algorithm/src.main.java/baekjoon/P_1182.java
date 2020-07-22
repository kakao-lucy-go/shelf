package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_1182 {

	public static int result = 0;
	public static int s = 0;
	public static int n = 0;
	public static int[] array;
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
	
		String[] temp = bf.readLine().split(" ");
	
		n = Integer.parseInt(temp[0]);
		s = Integer.parseInt(temp[1]);
		
		//System.out.println(n + " , " + s);
		temp = bf.readLine().split(" ");
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(temp[i]);
			//System.out.print(array[i] + " , ");
		}
		bf.close();
		cal(0,0);
		if(s==0) {
			result -= 1;	// 부분수열의 크기는 무조건 양수이다. 아무것도 선택하지 않는다는 선택지는 포함되면 안된다.
		}
//		
		System.out.println(result);
		
	}
	
	public static void cal(int index, int total) {

		if(index == n) {
			if(total==s) {
				result+=1;
				//return;
				}
			return;
		}
		
		cal(index+1, total);
		cal(index+1, total+array[index]);
		
	}
}
