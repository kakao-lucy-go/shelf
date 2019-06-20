package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_2098 {

	static int n = 0;
	static int[][] distance;
	static int[][] map;
	static int MAX = 987654321;
	
	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		//세팅
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//거리값을 저장할 배열 
		distance = new int[n][1<<n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance[i], MAX);
		}
		bf.close();
		System.out.println(tsp(0,1));
	}
	
	//처음에서 목적지까지를 계산한다.
	public static int tsp(int cur, int visit) {
		
		//끝까지 왔다면 
		if(visit == (1 << n)-1) {
			//처음으로 돌아갈 수 있는지를 체크한다.
			if(map[cur][0] != 0) {
				return map[cur][0];
			}else {
				return MAX;
			}
			
		}
		
		//직행이 있다면 바로 반환
		if(distance[cur][visit] != MAX) {
			return distance[cur][visit];
		}
		
		//키포인트는, 출발 -> 경유 -> 목적지 이다.
		for (int i = 0; i < n; i++) {
			
			//길이 있는데 
			if((visit & (1<<i)) == 0 && map[cur][i] != 0) {
				
				//재귀로 인해, 목적지에서부터 집으로 내려오면서 최소값을 찾는다.
				distance[cur][visit] = Math.min(distance[cur][visit], map[cur][i] + tsp(i, (visit | 1<<i)));
			}
		}
		
		//마지막 
		return distance[cur][visit];
	}

}
