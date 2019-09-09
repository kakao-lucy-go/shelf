package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_1149 {

	public static int answer = 987654321;
	static int[][] array;
	static int[][] distance;
	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		int home = Integer.parseInt(bf.readLine());
		array = new int[home+1][3];
		Arrays.fill(array[0], 0);
		distance = new int[home+1][3];
		Arrays.fill(distance[0],0);
		//가중치 세팅
		for (int i = 1; i < home+1; i++) {
			String temp = bf.readLine();
			String[] temp2 = temp.split(" ");
			for (int j = 0; j < 3; j++) {
				array[i][j] = Integer.parseInt(temp2[j]);
				
			}
		
		}
		
		
		dp(distance);
		
		for (int i = 0; i < 3; i++) {
			if(distance[home][i] < answer) {
				answer = distance[home][i];
			}
		}
		System.out.println(answer);
		bf.close();
		
	}
	
	public static void dp(int[][] distance) {
		

		for (int i = 1; i < distance.length; i++) {
			distance[i][0] = min(distance[i-1][1], distance[i-1][2]) + array[i][0];
			distance[i][1] = min(distance[i-1][0], distance[i-1][2]) + array[i][1];
			distance[i][2] = min(distance[i-1][0], distance[i-1][1]) + array[i][2];
		}
		
	}
	
	public static int min(int a, int b) {
		return a>b?b:a;
	}
}
