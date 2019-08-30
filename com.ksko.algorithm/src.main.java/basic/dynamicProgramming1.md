# 동적 계획법 (dynamic programming)  
분할정복과 같은 접근 방식을 의미한다. 작은 문제들로 나눈 뒤 각 문제들의 답을 계산하고(cache) 이 답들로부터 원 문제의 답을 도출한다.  
  
## 분할정복과 동적 계획법의 차이  
* 분할 정복 : 서로 연관이 없기 때문에 단순 재귀로 해결한다.  
* 동적 계획법 : 나눠진 부분이 중복된다.  
  
![Alt text](/imgs/분할정복.jpg)
![Alt text](/imgs/동적계획.jpg)

## 대표적인 문제 - 이항 계수  
n개의 서로 다른 원소 중에서 r개를 순서없이 골라내는 경우의 수  
![Alt text](/imgs/이항계수.jpg)  
f(n,r)의 값은 항상 정해져있다는 것이 포인트이다.  
  
<pre><code>
int cache[30][30];
int solve(int n, int r) {
	if(r==0 || n==r) return 1;
	if(cache[n][r] != -1) {
		return cache[n][r];
	}
	return cache[n][r] = solve(n-1,r-1) + solve(n-1,r);
}

</code></pre>  
  
## 시간복잡도  
부분문제 * 한 부분문제의 시간 복잡도  
  
## 접근법  
* 상향식 접근법 : 동적 프로그래밍  - 함수 호출x  
* 하향식 접근법 : 메모이제이션, 분할정복  - 함수 호출  
  
### 상향식 예제 - 피보나치 수열  
  
<pre><code>

int fibo(int n) {
	if(n==0) return 0;
	if(n==1) return 1;
	memo[0] = 0;
	memo[1] = 1;
	for~ {
		memo[i] = memo[i-1] + memo[i-2];
	}
}

</code></pre>  
  
### 하향식 예제 -피보나치 수열  
  
<pre><code>
int fibo(int i, int[] memo) {
if(i==0 || 1) return i;
if(memo[i]==0) {
	memo[i] = fibo(i-1, memo) + fibo(i-2, memo);
}
return memo[i];
</code></pre>  
  
## 적용 요건  
1. 중복 부분문제 구조 : 점화식 사용. 메모이제이션. 이미 계산된 값을 참조한다.  
2. 최적 부분문제 구조 : 최적화 원칙. 어떤 문제의 해가 최적일때 그 해를 구성하는 작은 문제들의 해도 최적이다.  
  
## 특징  
재귀호출은 시스템 호출 스택을 사용해서 메모리 사용량이 증가해 스택오버플로우가 발생할 수 있다.  
완전검색을 효율적으로 할 수 있다.  
점화식을 찾는다.  
