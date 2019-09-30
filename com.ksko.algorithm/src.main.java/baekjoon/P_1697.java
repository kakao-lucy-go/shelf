import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*수빈이는 동생과 숨바꼭질을 하고 있다. 
 * 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 
 * 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 
 * 수빈이는 걷거나 순간이동을 할 수 있다. 
 * 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.*/
public class P_1697 {

	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		String str = bf.readLine();
		bf.close();
		String[] str2 = str.split(" ");
		
		//n : 수빈이가 있는 위치 
		int n = Integer.parseInt(str2[0]);
		
		//k : 동생이 있는 위치 
		int k = Integer.parseInt(str2[1]);
		boolean[] visited = new boolean[100001];
		Arrays.fill(visited, false);
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(n, 0));
		visited[n] = true;
		int result = 0;
		
		while(!q.isEmpty()) {
			Node temp = q.poll();
			
			//규모 넘어가는거 방지 
			if(temp.x > 100000 || temp.x < 0) {
				break;
			}
			
			//동생을 찾으면 끝 
			if(temp.x == k) {
				result = temp.count;
				break;
			}
			
			//방문하지 않았으면 방문하도록 한다.
			if(temp.x+1 < 100001 && temp.x+1 >= 0 && !visited[temp.x+1]) {
				q.add(new Node(temp.x+1, temp.count+1));
				visited[temp.x+1] = true;
			}
			
			if(temp.x-1 < 100001 && temp.x-1 >= 0 && !visited[temp.x-1]) {
				q.add(new Node(temp.x-1, temp.count+1));
				visited[temp.x-1] = true;
			}
			
			if(temp.x*2 < 100001 && temp.x*2 >= 0 && !visited[temp.x*2]) {
				q.add(new Node(temp.x*2, temp.count+1));
				visited[temp.x*2] = true;
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
