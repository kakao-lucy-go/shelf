package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P_1012 {

	static int[] xs = {1,-1,0,0};
	static int[] ys = {0,0,1,-1};
	static boolean[][] array;
	
	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int testcase = Integer.parseInt(bf.readLine());
		
		for (int u = 0; u < testcase; u++) {
			
			String temp = bf.readLine();
			String[] temp2 = temp.split(" ");
			
			int width = Integer.parseInt(temp2[0]);
			int height = Integer.parseInt(temp2[1]);
			int cabbages = Integer.parseInt(temp2[2]);
			array = new boolean[height][width];
			for (int i = 0; i < cabbages; i++) {
				temp = bf.readLine();
				temp2 = temp.split(" ");
				array[Integer.parseInt(temp2[1])][Integer.parseInt(temp2[0])] = true;
				
			}
		
			int count = 0;
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					if(array[i][j]) {
						bfs(j,i);
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
		
	}
	
	public static void bfs(int x, int y) {
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(x,y));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for (int i = 0; i < 4; i++) {
				
				if(node.x+xs[i]>=0 && node.x+xs[i]<array[0].length && node.y+ys[i]>=0 && node.y+ys[i]<array.length) {
					if(array[node.y+ys[i]][node.x+xs[i]]) {
						array[node.y+ys[i]][node.x+xs[i]]=false;
						q.add(new Node(node.x+xs[i], node.y+ys[i]));
					}
				}
				
			}
		}
		
		
	}
	
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	

}