
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P_17144 {

	static int[] xs = {1,-1,0,0};
	static int[] ys = {0,0,1,-1};
	
	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		String[] temp = bf.readLine().split(" ");
		int r = Integer.parseInt(temp[0]);
		int c = Integer.parseInt(temp[1]);
		int t = Integer.parseInt(temp[2]);
		int[][] array = new int[r][c];
		Queue<Node> q = new LinkedList<Node>();
		Node[] air = new Node[2];//[0,0][1,1]
		int airCnt = 0;
		//세팅 
		for (int i = 0; i < r; i++) {
			temp = bf.readLine().split(" ");
			for (int j = 0; j < c; j++) {
				array[i][j] = Integer.parseInt(temp[j]);
				//공기청정기가 아니고 미세먼지가 있는 칸일 경우
				if(array[i][j] > 0) {
					q.add(new Node(j,i, array[i][j]));
				}
				//공청기 세팅 
				if(array[i][j] < 0) {
					air[airCnt] = new Node(j,i);
					airCnt+=1;
				}
			}
			
		}
		bf.close();
		
		//t초만큼 이제 돔
		for (int i = 0; i < t; i++) {
			//1. 확산
			//더해지는 경우엔 원래 값을 5로 나눈 값이고, 빠질 때에는 다 더해진 값에서 뺀다.
			while(!q.isEmpty()) {
				Node node = q.poll();
				int count = 0;
				for (int j = 0; j < 4; j++) {
					//확산할 수 있고
					if(node.x + xs[j] < c && node.x + xs[j] >= 0 && node.y + ys[j] >=0 && node.y + ys[j] < r) {
						//공청기 아닌 경우 
						if(array[node.y + ys[j]][node.x + xs[j]] >= 0) {
							//확산
							array[node.y + ys[j]][node.x + xs[j]] += node.value / 5;
							count++;
						}
					}
					
				}
				array[node.y][node.x] -= (node.value/5)*count;
			}
			
			//2. 정화
			//1구역
			for (int j = air[0].y-1; j >=0; j--) {
				//공청기 가기 직전 노드는 정화
				if(array[j+1][0] == -1) {
					array[j][0] = 0;
				}else {
					array[j+1][0] = array[j][0];
				}
			}
			
			//6구역
			for (int j = air[1].y+1; j < r; j++) {
				//공청기 가기 직전 노드는 정화
				if(array[j-1][0] == -1) {
					array[j][0] = 0;
				}else {
					array[j-1][0] = array[j][0];
				}
			}
			
			//2구역 
			for (int j = 1; j < c; j++) {
				array[r-1][j-1] = array[r-1][j];
				array[0][j-1] = array[0][j];
			}
			
			//3 구역
			for (int j = 1; j <= air[0].y; j++) {
				array[j-1][c-1] = array[j][c-1];
			}
			
			//4구역
			for (int j = r-1; j > air[1].y; j--) {
				array[j][c-1] = array[j-1][c-1];
			}
			
			//5구역
			for (int j = c-1; j >1; j--) {
				array[air[0].y][j] = array[air[0].y][j-1];
				array[air[1].y][j] = array[air[1].y][j-1];
			}
			array[air[0].y][1] = 0;
			array[air[1].y][1] = 0;
			
			//3. 큐에 다시 넣기
			for (int j = 0; j < r; j++) {
				for (int j2 = 0; j2 < c; j2++) {
					if(array[j][j2] > 0) {
						q.add(new Node(j2, j,array[j][j2]));
					}
				}
			}
		}
		
		int total = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(array[i][j]>0) {
					total += array[i][j];
				}
			}
		}
		System.out.println(total);
	}


	static class Node {
		int x;
		int y;
		int value;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Node(int x, int y, int value) {
			this.x=x;
			this.y=y;
			this.value=value;
		}
	}
}
