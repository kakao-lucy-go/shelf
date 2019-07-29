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
		//����
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//�Ÿ����� ������ �迭 
		distance = new int[n][1<<n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance[i], MAX);
		}
		bf.close();
		System.out.println(tsp(0,1));
	}
	
	//ó������ ������������ ����Ѵ�.
	public static int tsp(int cur, int visit) {
		System.out.println(visit);
		//������ �Դٸ� 
		if(visit == (1 << n)-1) {
			System.out.println("�� : " + visit);
			//ó������ ���ư� �� �ִ����� üũ�Ѵ�.
			if(map[cur][0] != 0) {
				return map[cur][0];
			}else {
				return MAX;
			}
			
		}
		
		//������ �ִٸ� �ٷ� ��ȯ
		if(distance[cur][visit] != MAX) {
			return distance[cur][visit];
		}
		
		//Ű����Ʈ��, ��� -> ���� -> ������ �̴�.
		for (int i = 0; i < n; i++) {
			
			//���� �ִµ� �湮���� ���� i��°�� �Ʒ� ��ͷ� �� �� or �������� �湮��Ų��.
			if((visit & (1<<i)) == 0 && map[cur][i] != 0) {
				
				//��ͷ� ����, �������������� ������ �������鼭 �ּҰ��� ã�´�.
				//or �������� �湮��Ŵ
				distance[cur][visit] = Math.min(distance[cur][visit], map[cur][i] + tsp(i, (visit | 1<<i)));
			}
		}
		
		//������ 
		return distance[cur][visit];
	}

}
