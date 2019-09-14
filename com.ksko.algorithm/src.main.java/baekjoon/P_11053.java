package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_11053 {

	static int n;
	static int[] array;
	static int[] result;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		n = Integer.parseInt(bf.readLine());
		String temp = bf.readLine();
		String[] temp2 = temp.split(" ");
		array = new int[n];
		result = new int[n];
		Arrays.fill(result, 1);
		answer = 0;
		
		for (int i = 0; i < temp2.length; i++) {
			array[i] = Integer.parseInt(temp2[i]);
		}
		
		//초기화 O(n)
		for (int i = 1; i < temp2.length; i++) {
			if(array[0] < array[i]) {
				result[i] = 2;
			}
		}
		
		//
		for (int i = 1; i < temp2.length; i++) {
			for (int j = i+1; j < temp2.length; j++) {
				
				if(array[i] < array[j]) {
					
					if(result[j] < (result[i]+1)) {
						result[j] = result[i]+1;
					}
					
				}
			}
		}
		
		for (int i = 0; i < temp2.length; i++) {
			if(answer < result[i]) {
				answer = result[i];
			}
		}
		
		bf.close();
		System.out.println(answer);
	}
	
	
}
