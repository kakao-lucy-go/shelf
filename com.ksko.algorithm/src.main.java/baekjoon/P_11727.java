package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_11727 {

	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		int test = Integer.parseInt(bf.readLine());
		int[] array = new int[1001];
		
		Arrays.fill(array, 0);
		array[1] = 1%100007;
		array[2] = 3%100007;
		array[3] = 5%100007;
		
		for (int i = 4; i < array.length; i++) {
			array[i] = (array[i-1] + (array[i-2]*2)) % 100007;
		}
		System.out.println(array[test]);
		
		bf.close();
		
	}
	
}
