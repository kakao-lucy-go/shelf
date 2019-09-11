package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_9461 {

	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		int testcase = Integer.parseInt(bf.readLine());
		long[] ns = new long[testcase];
		long[] p = new long[101];
		p[0] = 1;
		p[1] = 1;
		p[2] = 1;
		p[3] = 2;
		p[4] = 2;
		
		
		for (int i = 5; i < p.length; i++) {
			p[i] = p[i-3] + p[i-2];
		}
		
		for (int i = 0; i < testcase; i++) {
			int n = Integer.parseInt(bf.readLine());
			ns[i] = p[n-1];
		}
		
		for (int i = 0; i < ns.length; i++) {
			
			System.out.println(ns[i]);
		}
		
		
		
		bf.close();
		
	}

}
