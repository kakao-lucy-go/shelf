package baekjoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P_2178 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();

		
		int[][] maze = new int[n][m];
		//System.out.println("미로 입력");
		String temp = sc.nextLine();
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			String[] sArray = s.split("");
			
			for (int j = 0; j < m; j++) {
				maze[i][j] = Integer.parseInt(sArray[j]);
			}
			
		}
/*		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				maze[i][j] = sc.nextInt();
			}
		}
*/
		sc.close();

		System.out.println(searchExit(maze));

	}

	private static int searchExit(int[][] maze) {

		boolean[][] visited = new boolean[maze.length][maze[0].length];
		for (int i = 0; i < visited.length; i++) {

			Arrays.fill(visited[i], false);
		}

		visited[0][0] = true;
		Queue<Dot> q = new LinkedList<Dot>();
		int[] xDist = { 1, -1, 0, 0 };
		int[] yDist = { 0, 0, 1, -1 };

		q.offer(new Dot(0, 0));
		int level = 0;

		w1: while (!q.isEmpty()) {
			Dot dot = q.poll();

			//System.out.println("poll!!!!!!!!" + dot.getX() + ", " + dot.getY());
			for (int i = 0; i < xDist.length; i++) {

				int movedX = dot.getX() + xDist[i];
				int movedY = dot.getY() + yDist[i];

				if (movedX < 0 || movedY < 0 || movedX > maze.length - 1 || movedY > maze[0].length - 1) {
					continue;
				}

				//System.out.println(movedX + ", " + movedY);

				Dot movedDot = new Dot(movedX, movedY);

				if (maze[movedX][movedY] == 1) {
					if (visited[movedX][movedY] == false) {
						//System.out.println("여기다ㅏㅏㅏㅏㅏ");
						movedDot.setSum((dot.getSum() + 1));
						q.offer(movedDot);
						visited[movedX][movedY] = true;
					}
				}

				if (movedX == maze.length - 1 && movedY == maze[0].length - 1) {
					//System.out.println("return이다ㅏㅏㅏㅏㅏㅏ");

					level = movedDot.getSum();
					//System.out.println("==============level : " + level);
					break w1;

				}

			}
		}

		return level;

	}

	public static class Dot {
		int x;
		int y;
		int sum = 1;

		public int getSum() {
			return sum;
		}

		public void setSum(int sum) {
			this.sum = sum;
		}

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}
}

