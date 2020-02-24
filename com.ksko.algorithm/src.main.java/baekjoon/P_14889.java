
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class P_14889 {

	static int[][] array;
	static int size;
	static boolean[] isVisited;
	static int answer = 987654321;
	
	public static void main(String[] args) throws IOException {

		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));

		size = Integer.parseInt(bf.readLine());
		array = new int[size+1][size+1];
		isVisited = new boolean[size+1];
		Arrays.fill(isVisited, false);
		for (int i = 1; i <= size; i++) {
			String[] tmpS = bf.readLine().split(" ");
			for (int j = 1; j <= size; j++) {
				array[i][j] = Integer.parseInt(tmpS[j-1]);
				//System.out.print(array[i][j] + " ");
			}
			//System.out.println();
		}
		bf.close();
		dfs(0,0);
		System.out.println(answer);
		
	}

	//public static 
	public static void dfs(int start, int count) {
		//팀이 반이 나눠졌다면, 
		if(count==size/2) {
			startLink();
		}else {
			for (int i = start+1; i <= size; i++) {
				if(!isVisited[i]) {
					isVisited[i]=true;
					dfs(i, count+1);
				}
			}
		}
		//back tracking
		isVisited[start] = false;
	}
	
	public static void startLink() {
		int[] start = new int[size/2+1];
		int[] link = new int[size/2+1];
		int sIdx = 1, lIdx = 1;
		
		for (int i = 1; i <= size; i++) {
			if(isVisited[i]) {
				start[sIdx] = i;
				//System.out.println("start : " + i);
				sIdx+=1;
			}else {
				link[lIdx]=i;
				//System.out.println("end : " + i);
				lIdx+=1;
			}
		}
		
		
		int abs = Math.abs(getScore(start)-getScore(link));
		//System.out.println(abs);
		if(abs<answer) {
			answer = abs;
		}
	}
	
	public static int getScore(int[] players) {
		int sum=0;
		for (int i = 1; i <= size/2; i++) {
			for (int j = i+1; j <= size/2; j++) {
				//System.out.println(i+","+j);
				sum+=array[players[i]][players[j]];
				sum+=array[players[j]][players[i]];
			}
		}
		return sum;
	}
	
}
