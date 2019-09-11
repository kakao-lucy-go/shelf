package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_2908 {

	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		String temp = bf.readLine();
		String[] temp2 = temp.split(" ");
		
		char[] word1 = temp2[0].toCharArray();
		char[] word2 = temp2[1].toCharArray();
		
		int answer = 2;
		for (int i = 2; i >= 0; i--) {
			if(word1[i]>word2[i]) {
				answer = 1;
				break;
			}else if(word1[i] < word2[i]) {
				break;
			}
		}
		
		if(answer==1) {
			for (int i = 2; i >=0; i--) {
				System.out.print(word1[i]);
			}
		}else {
			for (int i = 2; i >=0 ; i--) {
				System.out.print(word2[i]);
			}
		}
		System.out.println();
		
		bf.close();
		
	}

}
