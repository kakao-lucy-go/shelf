import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class P_2839 {

	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int n = Integer.parseInt(bf.readLine());
		
		boolean[] visited = new boolean[5001];
		Arrays.fill(visited, false);
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(n,0));
		int result = -1;
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			if(temp.x == 0) {
				result = temp.count;
				break;
			}
			
			if(temp.x-5 >= 0 && (!visited[temp.x-5])) {
				q.add(new Node(temp.x-5, temp.count+1));
				visited[temp.x-5] = true;
			}
			
			if(temp.x-3 >= 0 && (!visited[temp.x-3])) {
				q.add(new Node(temp.x-3, temp.count+1));
				visited[temp.x-3] = true;
			}
			
		}
		
		System.out.println(result);
		
		
	}
	
	public static class Node {
		int x;
		int count;
		public Node(int x, int count) {
			this.x = x;
			this.count = count;
		}
	}

	
}
