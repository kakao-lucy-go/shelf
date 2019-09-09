package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_1912 {

	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		int n = Integer.parseInt(bf.readLine());
		String[] str = bf.readLine().split(" ");
		int[] array = new int[n];
		for (int i = 0; i < str.length; i++) {
			array[i] = Integer.parseInt(str[i]);
		}
		
		int answer = -1000;
		
		for (int i = 0; i < array.length; i++) {
			int temp = array[i];
			if(temp > answer) {
				answer = temp;
			}
			for (int j = i+1; j < array.length; j++) {
				temp+=array[j];
				
				if(temp > answer) {
					answer = temp;
				}
			}
		}
		
		System.out.println(answer);
		
		bf.close();
		
	}

}
