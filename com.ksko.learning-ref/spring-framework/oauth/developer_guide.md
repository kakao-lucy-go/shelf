
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
순수 HTTP는 테스팅에 좋지만 인증서버는 SSL이 사용되어야만한다. 보안 컨테이너나 프록시 뒤에서 앱을 실행할 수 있다(OAuth2와는 관련이 없다.). 스프링 시큐리티 requiresChannel() 제약조건을 사용해서 엔드포인트를 보호할 수도 있다. /authorize 엔드포인트는 평범한 앱 시큐리티의 일부분이다. /token 엔드포인트를 위해 sslOnly() 메소드를 사용해서 세팅할 수 있는 AuthorizationServerEndpointsConfigurer 에 플래그가 있다. 두 경우 모두 보안 채널 세팅은 선택적이지만 안전하지 않은 채널에서 요청을 감지하면 보안 채널이라고 생각되는 곳으로 리다이렉션한다.  
  
# 에러 핸들링 커스터마이징  
인증서버에서 에러 핸들링은 표준 스프링 MVC의 기능인 @ExceptionHandler 메소드를 엔드포인트 자체에서 사용한다. 사용자는 WebResponseExceptionTranslator엔드 포인트 자체에 응답을 제공 할 수도 있다. 이는 엔드 포인트 자체에 렌더링 방식이 아닌 응답 컨텐츠를 변경하는 가장 좋은 방법이다. 예외 렌더링은 인증 엔드포인트의 경우에 (/oauth/error) Oauth 에러뷰와 토큰 엔드포인트의 경우에 HttpMessageConverters에 위임한다.  whitelabel 에러 엔드포인트는 HTML 응답으로 제공하지만 사용자들은 아마 커스텀하고싶을 것이다. @Controller에서 @RequestMapping("/oauth/error")를 추가하자.  
  
# 스코프에 사용자 역할 매핑  
때때로 클라이언트에 할당된 스코프 뿐 아니라 사용자 스스로 권한에 따라 토큰의 스코프를 제한하는 것은 유용하다. DefaultOAuth2RequestFactory를 AuthorizationEndpoint에서 사용한다면 flag를 사용자의 역할에 맞는 허가된 스코프를 위해 checkUserScopes=true로 설정할 수 있다. OAuth2RequestFactory를 TokenEndpoint에 주입할 수 있지만 TokenENdpointAuthenticationFilter를 설치해야만 동작한다. - HTTP BasicAuthenticationFilter 이후에 필터를 더할 필요가 있다. 물론, 고유한 규칙을 구현하고 고유한 OAuth2RequestFactory 의 버전을 설치할 수 있다. AuthorizationServerEndpointsConfigurer는 커스텀 OAuth2RequestFactory를 주입할 수 있어서 @EnableAuthorizationServer를 사용한다면 factory를 설정하기 위한 기능을 사용할 수 있다.  
  
# 리소스 서버 구성  
리소스 서버(인증서버와 같거나 분리된 앱)는 OAUth2 토큰에 의해 보호된 리소스를 서비스한다. 스프링 OAUth 는 스프링 시큐리티 인증 필터를 제공한다. @EnableResourceServer를 @Configuration 클래스에 선언해서 스위칭할 수 있고, ResourceServerConfigurer를 사용해서 구성한다. 아래는 구성될 수 있는 기능들이다. :  
1. tokenServices : (ResourceServerTokenServices의 인스턴스인) 토큰 서비스를 정의하는 빈.  
2. resourceId : 리소스를 위한 아이디.(선택적이지만 존재한다면 인증서버에 의해 검증될 수 있어서 추천한다.)  
3. 다른 확장 포인트(들어오는 요청으로부터 토큰을 추출하기 위한 tokenExtractor같은 것들)  
4. 보호된 리소스에 대한 match 요청 (기본은 all)  
5. 보호된 리소스에 대한 접근 규칙 (기본은 "authenticated")  
6. 스프링 시큐리티의 HttpSecurity 구성에 의해 허가된 보호된 리소스에 대한 다른 사용자 정의  
  
** @EnableResourceServer 어노테이션은 OAUth2AuthenticationProcessingFilter 타입의 필터를 자동으로 스프링 시큐리티 필터 체인에 추가한다. **  
  
ResourceServerTokenServices는 인증서버와의 계약의 나머지 절반이다.(?). 리소스서버와 인증서버가 같은 앱에 있고 DefaultTokenServices를 사용한다면 자동으로 필요한 인터페이스를 확장하기 때문에 깊게 생각하지 않아도 된다. 리소스 서버가 분리되어 있다면 인증서버의 기능과 일치하는지 확인해야 하고 토큰을 어떻게 올바르게 디코딩하는지 ResourceServerTokenServices를 제공해야한다. 인증서버와 마찬가지로, DefaultTokenServices를 사용할 수 있고 TokenStore를 통해서 표현된다. 대안은 인증서버(/oauth/check_token) 에 HTTP 리소스를 통해 토큰을 디코딩하기 위한 리소스 서버를 allow하는 스프링 오쓰 기능인 RemoteTokenServices 이다. RemoteTokenServices는 리소스 서버의 트래픽의 거대한 양이 없거나 캐시할 여유가 있는 경유 편하다. /oauth/check_token 엔드포인트를 사용하기위해 AUthorizationServerSecurityConfigurer에서 (기본은 "denyAll()") 접근 규칙을 변경해서 노출시킬 필요가 있다.  
<pre><code>
@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')").checkTokenAccess(
					"hasAuthority('ROLE_TRUSTED_CLIENT')");
		}
</code></pre>  
  
예를들어서 /oauth/check_token 엔드포인트와 /oauth/token_key 엔드포인트를 구성한다. 이 두 엔드포인트는 client credentials를 사용해서 HTTP 기본 인증에 의해 보호된다.  
  
## OAuth-Aware 표현 핸들러 구성  
스프링 시큐리티의 표현기반 접근 제어의 장점을 원할 수 있다. 표현 핸들러는 @EnableResourceServer 설정에 기본적으로 등록되어있을 것이다. 표현들은 #oauth2.clientHadRole, #oauth2.clientHasAnyRole, #oauth2.denyClient를 포함한다.  
  
# OAuth 2.0 클라이언트  
OAuth 2.0 클라이언트 매커니즘은 다른 서버들의 보호된 리소스에 접근해야한다. 구성은 사용자가 엑세스할 수 있는 보호 자원을 설정하는 것을 포함한다. ** 클라이언트는 사용자를 위한 access token과 인증 코드를 저장하는 매커니즘이 필요하다. **  
  
## 보호된 리소스 설정  
보호된 리소스(또는 원격 리소스)는 OAuth2ProtectedResourceDetails 타입의 빈 정의를 사용한다. 보호된 리소스는 아래 프로퍼티들을 가진다. :  
1. id : 리소스의 아이디. id는 리소스를 찾기 위해 클라이언트가 사용한다.OAuth 프로토콜에서 절대 사용되지 않는다. 빈의 id로서 사용된다.  
2. clientId : OAuth 클라이언트 id. OAuth provider가 클라이언트를 식별하는데 사용된다.  
3. clientSecret : 리소스와 관련된 secret. 기본적으로는 비어있다.  
4. accessTokenUri : 엑세스 토큰을 제공하는 OAUth 엔트포인트 provider의 uri  
5. scope : ","로 분리된 문자열의 리스트. 기본으론 스코프가 없다.  
6. clientAuthenticationScheme : 엑세스 토큰 엔드포인트에 인증하기 위한 클라이언트가 사용하는 스키마. 제안되는 값은 : "http_basic", "form". 기본적으론 "http_basic" - 차조 : OAUth 2 spec의 2.1 section.  
  
다른 권한 타입은 Oauth2ProtectedResourceDetails의 확장이 다르다. 사용자 인증이 필요한 권한 타입은 아래와 같은 추가 속성이 있다. :  
* userAuthorizationUri : 유저가 리소스에 접근하는걸 인증할 필요가 있다면 리다이렉트 되는 uri. 항상 요구되는 것은 아니다.  
  
## 클라이언트 구성  
OAUth 2.0 클라이언트를 위해, 구성은 @EnableOAUth2CLient를 사용해서 간단화된다. 아래 두가지를 한다. :  
1. oauth2ClientContextFlter ID와 함께 현재 요청과 내용을 저장하기 위해 필터 빈을 생성한다. 요청 동안 인증하기 위해 필요한 경우에 OAuth 인증 uri로부터 리다이렉션을 관리한다.  
2. 요청 scope에 AccessTokenRequest 타입의 빈을 생성한다. 인증 코드 (또는 암시 적) 권한 부여 클라이언트가 개별 사용자와 관련된 상태가 충돌하지 않도록하는 데 사용할 수 있다.  
  
필터는 앱 안에 연결된다.  
AccessTokenRequest는 OAUth2RestTemplate에 사용될 수 있다. :  
<pre><code>
@Autowired
private OAuth2ClientContext oauth2Context;

@Bean
public OAuth2RestTemplate sparklrRestTemplate() {
	return new OAuth2RestTemplate(sparklr(), oauth2Context);
}
</code></pre>  
OAuth2ClientContext는 다른 유저의 상태를 별도로 유지하기 위해 session 범위에 배치된다. 그렇지 않으면 서버에서 동등한 데이터 구조를 직접 관리하고 요청을 사용자에게 맵핑하고 각 사용자의 OAuth2ClientContext 개별 인스턴스와 연관시켜야한다.  
  
## 보호된 리소스에 접근하기.  
리소스에 대한 모든 구성을 제공하면 그 리소스들에 접근할 수 있다. 자원에 엑세스하기 위해 제안하는 방법은 RestTemplage을 사용하는 것이다. 스프링 시큐리티 OAuth는 OAuth2ProtectedResourceDetails의 인스턴스가 공급될 필요가 있는 RestTemplate의 확장을 가진다. 유저 토큰(authorization code 권한)을 사용하기 위해서 @EnableOAuth2Client 구성을 사용해라.  
  
일반적으로 웹 앱은 password 권한을 사용하지 않고 ResourceOwnerPasswordResourceDetails를 사용하는 것을 피한다. 부득이하게 password 권한을 자바 클라이언트로부터 동작할 필요가 있다면, OAuth2RestTemplate을 구성하고 ResourceOwnerPasswordResourceDetails가 아니라 AccessTokenRequest에 크리덴셜을 추가한다.  
  
## 클라이언트에 토큰 유지  
클라이언트는 토큰을 유지할 필요는 없지만 클라이언트 앱이 재시작하는 매 시간에 새로운 토큰 유형 승인을 요청하지 않아도 되는 것이 좋다. ClientTokenServices 인터페이스는 특정 유저를 위해 OAuth 2.0 토큰을 유지할 필요가 있는 동작을 정의한다. JDBC 확장이 제공되지만, 디비에 인증 인스턴스와 관련되고 엑세스 토큰을 저장하는 것을 위한 자체 서비스를 확장하는게 선호된다. 이 기능을 사용하기를 원한다면 특별하게 구성된 TOkenProvider를 OAuth2RestTemplage에 구성하자.  
  
<pre><code>
@Bean
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public OAuth2RestOperations restTemplate() {
	OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(accessTokenRequest));
	AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
	provider.setClientTokenServices(clientTokenServices());
	return template;
}
</code></pre>  
  
# 외부 OAuth2 provider의 클라이언트에 대한 사용자 정의

