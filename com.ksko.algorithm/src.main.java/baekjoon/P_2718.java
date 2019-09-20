import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_2718 {

	//501은 4를 곱했을 때 Integer 표현 수 아래로 내려올 임의의 수
	static int[][] array = new int[13][501];

	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));

		int testcase = Integer.parseInt(bf.readLine());
		for (int j = 0; j < 13; j++) {
			Arrays.fill(array[j], 1);
		}

		for (int i = 0; i < testcase; i++) {
			int n = Integer.parseInt(bf.readLine());


			bitmasking(n,0);

			System.out.println(array[0][n]);
		}

		bf.close();
		
	}

	//n : 줄 수 status : 채운 상태
	public static int bitmasking(int n, int status) {

		//마지막 줄에 가운데가 채워져 버리면 맨위, 맨 하단이 전 줄에서 가로막대로 채워져야 하는데 채워지지 못한다.
		if(n<0 || (n == 1 && status == 6)) { return 0;}
		if(n==1 || n==0) {return 1;}
		if(array[status][n] > 1) { return array[status][n];}

		int count = 0;
		if (status == 0){
			count += bitmasking(n-1, 12);
			count += bitmasking(n-1, 3);
			count += bitmasking(n-1, 9);
			count += bitmasking(n-1, 0);
			count += bitmasking(n-2, 0);
		}else if(status == 12) {
			count += bitmasking(n-1, 0);
			count += bitmasking(n-1, 3);

		}else if(status == 6) {
			count += bitmasking(n-1, 9);

		}else if(status == 3) {
			count += bitmasking(n-1, 12);
			count += bitmasking(n-1, 0);

		}else if(status == 9) {
			count += bitmasking(n-1, 0);
			count += bitmasking(n-1, 6);
		}

		array[status][n] = count;
		return count;
	}

}
