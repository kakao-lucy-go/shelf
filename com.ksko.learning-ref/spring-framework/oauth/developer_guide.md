
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
  
**AuthorizationServerTokenServices** 를 확장할 때, 엑세스 토큰 스토리지와 포맷을 변경하기 위해 플러그될 수 있는 많은 전략들을 가진 DefaultTokenServices를 사용하는 걸 고려해보자. 기본적으로 랜덤한 값으로 토큰을 생성하고 TokenStore에 위임해서 토큰의 영속성을 제외한 모든것을 다룬다. 기본 스토어는 인메모리 확장이지만 다른 확장을 사용할수 있다.  
1. 기본 InMemoryTokenStore는 완병하게 싱글 서버에 유용하다. 대부분 프로젝트는 여기에서 시작할 수 있고 개발 모드에 이 방식으로 운영할 수 있다. 의존성이 필요없이 쉽게 시작할 수 있다.  
2. JdbcTokenStore는 JDBC 버전과 같다. 클래스패스에 spring-jdbc가 있어야한다.  
3. JSON Web Token (JWT) 는 토큰안에 권한에 대한 모든 정보가 인코딩되어 저장되어있다. 한가지 단점은 엑세스 토큰을 쉽게 취소할 수 없어서 보통 짧은 만료 기간으로 권한을 주고 취소는 리프레시 토큰으로 다뤄진다. 또다른 단점은 토큰은 많은 크리덴셜 정보가 들어가기 때문에 꽤나 무겁다. JtwTokenStore는 정말 어떠한 데이터를 영속하지 않아서 저장이란 개념이 아니지만 인증 정보를 번역하는 역할을 DefaultTokenServices가 한다.  
  
## JWT 토큰  
JWT 토큰을 사용하려면 인증서버에 JwtTokenStore가 있어야한다. 리소스서버는 토큰을 디코딩할 필요가 있고, JwtTokenStore는 JwtAccessTokenConveter에 의존성이있고 인증서버와 리소스서버 둘다 같은 확장이 필요하다. 토큰은 기본으로 사인되고, 리소스서버 또한 서명을 인증할 수 있어야해서 같은 서명(signing) 키가 인증서버에도 필요하거나 public key와 private key가 필요하다. public key는 인증서버에 /oauth/token_key 에 노출되고 "denyAll()" 접근 룰제 보호된다. AuthorizationServerSecurityConfigurer(public key르 permitAll()되는) SpEL 표현식으로 주입된다.  
  
JwtTokenStore는 사용하려면 "spring-security-jwt"가 필요하다.  
  
## 권한 타입  
AuthorizationEndpoint에 의해 지원되는 권한 타입은 AuthorizationServerEndpointsConfigurer를 통해 설정된다. 기본적으로 모든 권한 타입은 패스워드를 제외하고 지원된다. 아래 프로퍼티는 권한 타입에 영향을 준다 :  
1. authenticationManager : 패스워드 권한은 AuthenticationManager를 주입함으로서 스위칭된다.  
2. userDetailsService : userDetailsService를 주입하거나 전역으로 구성된 경우(GlobalAuthenticaitonManagerConfigurer) 리프레시 토큰 권한은 유저 디테일 체크를 포함할 것이다. 계정이 여전히 활성화 되어있는지 확인한다.  
3. authorizaitonCodeServices : (AuthorizationCodeServices 인스턴스인) 인증 코드 서비스를 정의한다.  
4. implicitGrantService : implicit 권한 상태를 관리한다.  
5. tokenGranter : (권한을 전체적으로 컨트롤하고 다른 프로퍼티들을 무시) TokenGranter  
  
## Endpoint URL 구성  
AuthorizationServerEndpointsConfigurer 는 pathMapping() 메소드를 가진다. 아래 두개의 인자를 설명한다 :  
1. 기본 엔드포인트 url path  
2. "/"로 시작하는 커스텀 패스  
프레임워크가 제공하는 url 경로는  
* /oauth/authorize : 인증 엔드포인트  
* /oauth/token : 토큰 엔드포인트  
* /oauth/confirm_access : 권한과 승인  
* /oauth/error : 인증서버에 에러 렌더  
* /oauth/check_token : 리소스서버가 엑세스 토큰을 디코드  
* /oauth/token_key : JWT 토큰을 사용한다면 토큰 인증을 위한 public key 노출  
이다.  
  
/oauth/authorize 인증 엔드포인트는 스프링 시큐리티를 사용해서 보호되어야만 하고, 인증된 유저만이 접근가능해야한다. 예를 들어서 표준 스프링 시큐리티 WebSecuriyConfigurer를 사용한다면 :  
<pre><code>
@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers("/login").permitAll().and()
        // default protection for all resources (including /oauth/authorize)
            .authorizeRequests()
                .anyRequest().hasRole("USER")
        // ... more configuration, e.g. for form login
    }
</code></pre>
  
# UI 커스터마이징  
대부분 인증 서버 엔드포인트는 기계가 주로 사용하지만 UI가 필요한 /oauth/error의 HTML 응답과 GET /oauth/confirm_access 가 있다. 프레임워크에서는 whitelabel을 사용해서 제공하고 있어서 대부분 인증서버는 자신만의 것을 제공하고 스타일링할 수 있다. 스프링 MVC 컨트롤러를 제공하는 것과 프레임워크 기본은 디스패처에서 낮은 우선순위를 제공한다. /oauth/confirm_access 엔드포인트에서 AuthorizationRequest 는 유저로부터 승인을 받을 필요가 있는 데이터들을 캐리하는 세션과 바운드한다. 요청으로부터 모든 데이터를 볼 수 있고 렌더할 수 있지만 모든 유저는 권한을 거부하거나 승인하는거에 대한 정보를 POST /oauth/authorize로 back한다. 요청 파라미터는 직접적으로 AuthorizationEndpoint에서 UserApprovalHandler로 넘어가서 더 혹은 덜 해석한다. 기본 UserApprovalHandler는 AuthorizationServerEndpointsConfigurer의 ApprovalStore가 공급되는지 여부에 의존한다(공급이 된다면 ApprovalStoreUserApprovalHandler, 되지 않는다면 TokenStoreUserApprovalHandler). 표준 승인 핸들러는 아래를 따른다. :  
1. TokenStoreUserApprovalHandler : 간단한 yes/no 결정을 user_oauth_approval 을 통해 결정한다.  
2. ApprovalStoreUserApprovalHandler : scope 전체와 "*"??  
  
CSRF 보호를 잊지마십시오.  
  
## SSl 시행  


