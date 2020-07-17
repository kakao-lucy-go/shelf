package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P_2529 {

	
	public static char[] g;
	public static long min = 9876543210L;
	public static long max = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int n = Integer.parseInt(bf.readLine());
		g = new char[n];
		String[] temp = bf.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			g[i] = temp[i].toCharArray()[0];
			//System.out.println(g[i]);
		}
		bf.close();
		Queue<Node> q = new LinkedList<Node>();
		
		q.add(new Node());

		while(!q.isEmpty()) {
			Node node = q.poll();
			//System.out.println(node.str);
			if(node.array.length > 0 && node.str.length() <= n) {
				for (int i = 0; i < node.array.length; i++) {
					//처음
					if(node.last==-1) {
						q.add(new Node(removeOne(node.array, node.array[i]),node.index,node.array[i],(node.str + node.array[i])));
					}else {
						if(g[node.index]=='>') {
							//System.out.print("in");
							if(node.last>node.array[i]) {
								q.add(new Node(removeOne(node.array, node.array[i]),node.index+1,node.array[i],(node.str + node.array[i])));
							}
						}else {
							if(node.last<node.array[i]) {
								q.add(new Node(removeOne(node.array, node.array[i]),node.index+1,node.array[i],(node.str + node.array[i])));
							}
						}
					}
				}
			}else {
				long tempL = Long.parseLong(node.str);
				if(tempL<min) {min=tempL;}
				else if(tempL>max) {max = tempL;}
			}
		}
		
		System.out.println(String.format("%0"+(n+1)+"d", max));
		System.out.println(String.format("%0"+(n+1)+"d", min));
	}

	public static int[] removeOne(int[] array, int one) {
		int[] result = new int[array.length-1];
		int j=0;
		for (int i = 0; i < array.length; i++) {
			if(array[i]!=one) {
				result[j] = array[i];
				j+=1;
			}
		}
		return result;
	}
	public static class Node{
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		int index=0;
		int last = -1;
		String str = "";
		public Node() {}
		public Node(int[] array, int index, int last, String str) {
			this.array = array;
			this.index = index;
			this.last = last;
			this.str = str;
		}
	}
}

