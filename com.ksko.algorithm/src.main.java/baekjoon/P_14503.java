package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.
public class P_14503_1 {

	static boolean[][] array;
	//북동남서
	static int[] xs = {0,1,0,-1};
	static int[] ys = {-1,0,1,0};
	static boolean[][] isVisited;
	static int answer = 0;
	static int y;
	static int x;
	static int d;
	
	public static void main(String[] args) throws IOException {

		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));

		String[] temp = bf.readLine().split(" ");

		array = new boolean[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])];
		isVisited = new boolean[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])];
		
		temp = bf.readLine().split(" ");
		
		//청소기 (r,c)
		y = Integer.parseInt(temp[0]);
		x = Integer.parseInt(temp[1]);
		d = Integer.parseInt(temp[2]);
		
		//세팅 끝
		for (int i = 0; i < array.length; i++) {
			temp = bf.readLine().split(" ");
			for (int j = 0; j < array[0].length; j++) {
				
				int u = Integer.parseInt(temp[j]);
				//false 빈칸 true 벽
				if(u==1) {
					array[i][j] = true;
					//isVisited[i][j]=true;
				}
			}
		}
		

		bf.close();
		
		//d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.
		
		//1. 현재위치 청소
		//isVisited[r][c] = true;
		arrow();
		System.out.println(answer);
		
	}

	//count : 청소할 공간이 없는 카운트
	public static void arrow() {
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(y,x,d));
		while(!q.isEmpty()) {
			Node temp = q.poll();
			//1. 현재 위치 청소 
			//벽이 아니면서 아직 청소안했을 경우 
			if(!array[temp.y][temp.x] && !isVisited[temp.y][temp.x]) {
				//array[y][x] = true;
				//System.out.println("청소합니다 : " + answer + " y " + temp.y + " , x " + temp.x);
				isVisited[temp.y][temp.x] = true;
				answer+=1;
			}
			
			int count = 0;//갈 수 있는데 청소 할 수 있는 곳을 제외하고 갈수없는 곳 + 갈수있는데 청소되어있거나 벽이있는 경우를 센다.
			//int noCount = 0;
			for (int i = 0; i < 4; i++) {
				//System.out.println((y+ys[i]) + " , " + (x+xs[i]));
				if(temp.y+ys[i] >= 0 && temp.y+ys[i] < array.length && temp.x+xs[i] >= 0 && temp.x+xs[i] < array[0].length) {
					//벽이거나 청소한 구역이면 갈 필요 없다.
					if(array[temp.y+ys[i]][temp.x+xs[i]] || isVisited[temp.y+ys[i]][temp.x+xs[i]]) {
						count+=1;
					}
				}else {
					count+=1;
				}
			}
	
			//System.out.println("count :" + count);
			if(count>=4) {
				
				//후진
				int back = d;
				if(d==1) {
					back = 3;
				}else if(d==2) {
					back = 0;
				}else if(d==3) {
					back = 1;
				}else {
					back = 2;
				}
				//System.out.println("back : " + back);
				
				//후진? 근데 벽이다
				if(array[temp.y+ys[back]][temp.x+xs[back]]) {
					//System.out.println("후진불가. 벽임");
					//return;
				}else {
					//System.out.println("후진가능");
					q.add(new Node(temp.y+ys[back], temp.x+xs[back], d));
					//return;
					
				}
			}else {
				
				//2. 왼쪽방향으로 회전
				if(d == 0) {
					d=3;
				}else {
					d-=1;
				}
				//System.out.println("d : " + d);
				if(temp.y+ys[d] >= 0 && temp.y+ys[d] < array.length && temp.x+xs[d] >= 0 && temp.x+xs[d] < array[0].length) {
					//2_1 청소할 공간이 있다면 (벽이아니면서 아직 청소안함)
					if(!array[temp.y+ys[d]][temp.x+xs[d]]) {
						if(!isVisited[temp.y+ys[d]][temp.x+xs[d]]) {
							//벽도 아닌데 청소 안했음. 청소하러 가자.
							q.add(new Node(temp.y+ys[d], temp.x+xs[d],d));
						}else {
							//벽은 아닌데 청소는 함. 
							q.add(new Node(temp.y, temp.x,d));
						}
						
					}else {
						q.add(new Node(temp.y, temp.x,d));
					}
				}
			
			}

			
		}
	
		
				
	}
	
	static class Node {
		int x;
		int y;
		int d;
		public Node(int y, int x, int d) {
			this.x=x;
			this.y=y;
			this.d=d;
		}
	}
}
