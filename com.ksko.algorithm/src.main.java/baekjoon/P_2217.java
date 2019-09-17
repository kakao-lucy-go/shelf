package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_2217 {

	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		int n = Integer.parseInt(bf.readLine());
		int[] weight = new int[n];
		
		for (int i = 0; i < n; i++) {
			weight[i] = Integer.parseInt(bf.readLine());
		}
		
		Arrays.sort(weight);
		int sum = 0;
		int answer = 0;
		
		for (int i = 1; i <= n; i++) {
			int temp = i;
			int index = n-1;
			while(temp > 0) {
				sum=weight[index];
				index--;
				temp--;
			}
			
			if(sum*i > answer) {
				answer = sum*i;
			}
		}
		
		System.out.println(answer);
		bf.close();
		
	}
	
	
}
