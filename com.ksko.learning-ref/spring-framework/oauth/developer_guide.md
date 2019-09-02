
spring-security-oauth2 를 클래스패스에 가지고있으면 Authorization과 Resource 서버를 간단하게 세팅하기 위한 자동설정을 사용할 수 있다.  

----
OAuth2 개발자 가이드  
(https://projects.spring.io/spring-security-oauth/docs/oauth2.html)  
  
## 소개  
OAuth 2.0을 지원하기 위한 가이드이다.  
유저가이드는 OAuth 2.0 provider와 OAuth 2.0 client 두 개로 나뉘어져 있다.  
  
## OAuth 2.0 Provider  
OAuth 2.0 provider 매커니즘은 보호된 리소스를 볼 수 있게 한다. provider는 보호된 리소스에 접근하기 위한 OAuth 2.0 토큰을 검증하고 관리한다. provider는 또한 클라이언트가 보호된 리소스로 접근할 권한이 있는지 확인하는 인터페이스를 제공해야만 한다.  
(예를 들어서 facebook, google에서 제공하는 oauth provider service)  
  
## OAuth 2.0 Provider 확장  
provider 역할은 OAuth 2.0에서 인증서비스와 리소스서비스 사이를 분할하고 때때로 같은 앱 안에 있으면서 인증 서비스를 공유하는 리소스 서버를 여러개 가질 수 있다. Spring MVC 컨트롤러 엔드포인트에 의해 다뤄지는 토큰을 위한 요청과 보호된 리소스로의 접근은 스프링 시큐리티 요청 필터 기준에 의해 다뤄진다. 아래 엔드포인트들은 스프링 시큐리티 필터 체인에서 OAuth 2.0 인증 서버를 확장하기 위해 요구된다.  
1. **AuthorizationEndPoint** 는 인증을 위한 요청에 사용된다.  
기본 url : /oauth/authorize  
2. **TokenEndpoint** 는 토큰 접근을 위한 요청에 사용된다.  
기본 url : /oauth/toekn  
  
아래 필터들은 OAuth 2.0 리소스 서버를 확장하는데 요구된다.:  
1. **OAuth2AuthenticationProcessingFilter** 는 주어진 허가된 엑세스 토큰을 주어진 요청을 위한 인증을 로드하는데 사용된다.  
  
모든 OAuth 2.0 provider 기능들에, 설정은 @Configuraion 어뎁터를 사용해서 간단화된다. XML 네임스페이스가 있다.  
  
## 인증 서버 설정  
  
인증서버를 설정할 때, end-user(인증 코드, 유저 크리덴셜, 리프레시 토큰)로부터 엑세스 토큰을 포함하는 권한 타입(grant type)을 고려해야만 한다. 서버의 설정은 client details service와 토큰 서비스의 확장을 제공하고 전체적으로 메카니즘의 특정 측면을 사용가능/불가능하게 한다. 하지만 각 클라이언트는 접근 권한과 특정 인증 매카니즘을 사용할 수 있는 허가를 설정할 수 있다. 즉, provider가 "클라이언트 크리덴셜"을 권한 타입으로 구성되었다고 해서 특정 클라이언트가 해당 권한 부여 유형을 사용할 권한이 있음을 의미하지는 않는다.  
  
@EnableAuthorizationServer 어노테이션은 OAuth 2.0 인증 서버 메카니즘을 사용할 수 있게 하고 AuthorizationServerConfigurer를 확장하는 @Beans를 함께 사용할 수 있다. 아래 기능들은 스프링에 의애 생성되어 AuthorizationServerConfigurer로 넘어가는 별도의 설정으로 위임된다.  
1. ClientDetailServiceConfigurer : client detail service를 정의하는 구성자. 초기화되거나 기존 store를 참조한다.  
2. AuthorizationServerSecurityConfigurer : 토큰 엔드포인트의 보안 제한 조건을 정의한다.  
3. AuthorizationServerEndpointConfigurer : 권한 부여, 토큰 엔드 포인트와 토큰 서비스를 정의한다.  
  
provider 설정의 중요한 면은 인증 코드가 클라이언트에게 제공되는 방식이다. 인증 코드는 클라이언트가 자신의 자격증명을 입력할 수 있는 페이지로 안내하고, 이를 사용해서 provider 인증 서버에서 OAuth 클라이언트로 다시 리디렉션해서 얻어진다.  
  
## 클라이언트 세부 사항 구성  
(AuthorizationServerConfigurer)에서 콜백한 CLientDetailsServiceConfigurer는 인메모리나 JDBC 확장으로 정의될 수 있다. 중요한 속성은:  
1. clientId : 클라이언트 아이디  
2. secret : 클라이언트 시크릿  
3. scope : 클라이언트의 범위가 제한된다. 스코프가 정의되어있지 않거나 비어있으면 (the default) 클라이언트는 제한되지 않는다.  
4. authorizedGrantTypes : 클라이언트가 사용할 수 있는 권한 부여 유형. 기본값은 비어있다.  
5. authorities : 클라이언트에게 부여된 권한(보통 스프링 시큐리티 권한)  
  
client detail은 기본 저장소- 디비 테이블에 JdbcClientDetailsService가 직접 엑세스하거나 ClientDetailsManager 인터페이스를 통해 업데이트할 수 있다.  
  
(참고로, JDBC 스키마는 함께 패키징 되어있지 않다.)  
  
## 토큰 관리  
AuthorizationServerTokenServices 인터페이스는 OAuth 2.0 토큰을 관리하는데 필요한 작업을 정의한다.  
* 엑세스 토큰이 작성되면 이를 나중에 참조할 수 있도록 저장해야한다.  
* 엑세스 토큰은 생성을 인증하는데 사용된 인증을 로드한다.  
  
**AuthorizationServerTokenServices** 를 확장할 때, 
