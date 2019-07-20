https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
스프링 부트 레퍼런스 번역 

46 Testing
======

Spring Boot는 앱을 테스트할 때 도움이 되는 어노테이션과 유틸들을 제공한다. 테스트 지원은 두개의 모듈로서 제공된다 : **spring-boot-test** 는 코어를 포함하고, **spring-boot-test-autoconfigure** 는 auto-configuration을 지원한다.
대부분의 개발자들은 JUnit, AssertJ, Hamcrest, 다른 유용한 라이브러리들 같은 스프링 부트 테스트 모듈들을 가진 **spring-boot-starter-test** 스타터를 사용한다.

46.1 테스트 범위 의존성
----
**spring-boot-starter-test** 스타터는 (test scope안에서) 아래의 라이브러리들을 포함한다.:

* JUnit 4 : Java 앱의 단위 테스트를 위한 사실상의 표준
* Spring Test & Spring Boot Test : 스프링 부트 앱을 위한 유틸리티와 통합 테스트 지원
* AssertJ : 유창한 assert 라이브러리
(assert : 프로그램의 런타임 에러를 체크하고 디버그 정보를 생성하는 디버깅 방법. printf와 같은 기능을 하지만 일괄적으로 on/off할 수 있고, 조건검사를 통해 정형화된 에러메시지를 출력할 수 있다.
printf의 CPU 연산 :  printf를 사용하기 전에 CPU를 초기화하고, 포트를 설정하고, UART 모듈을 초기화하고 한글자씩 출력하는 과정이기 때문에 CPU 연산을 쓰는데다가 시간도 오래걸린다.)

* Hamcrest : 정규 표현식 객체(제약조건 또는 술어라고도 함) 라이브러리
* Mockito : 자바 mock 프레임워크
(mock : 테스트를 위해 가짜(대역) 객체를 만든다. 이때 만들어지는 가짜 객체를 Mock Object라고 한다.)

* JSONassert : JSON을 위한 assert 라이브러리
* JsonPath : Json XPath
(XPath : XML Path Language. XML 문서의 특정 요소나 속성에 접근하기 위한 경로를 지정하는 언어. XML 보다 쉽고.. 수학 함수 등의 표현을 사용할 수 있는.. W3C 표준안.)

일반적으로 이런 common한 라이브러리들은 테스트를 작성할때 유용하다. 만약 이 라이브러리들이 필요에 맞지 않는다면 부가적인 test 의존성을 추가할 수 있다.

46.2
-----
의존성 주입의 주된 장점 중 하나는 코드가 단위 테스트를 더 쉽게 수행한다는 것이다. 심지어 스프링에 상관없이 new 를 사용해서 오브젝트를 인스턴스화할 수 있다. 또한 mock 오브젝트를 실제 의존성 대신에 사용할 수 있다.

자주, 스프링 ApplicationContext와 단위 테스트를 넘어서 통합 테스트를 시작해야 한다. 이는 다른 인프라에 연결할 필요가 있거나 애플리케이션의 디플로이를 요구하는 것 없이 통합 테스트를 수행하기에 유용하다. 

스프링 프레임워크는 그런 통합 테스트를 위한 전용 테스트 모듈을 포함한다. **org.springframework** 에 대한 의존성을 직접 선언할 수 있다. : **org.springframework:spring-test** 나 **spring-boot-starter-test** 스타터를 사용해서 일시적으로 가져온다.
만약 **spring-test** 모듈을 전에 사용해본적이 없으면, 스프링 프레임워크 레퍼런스 문서에서 relevant section을 읽고 시작해야만한다.

(https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/testing.html#testing)

46.3 스프링 부트 애플리케이션 테스팅
-----

스프링 부트 애플리케이션은 스프링 **ApplicationContext** 여서 매우 특별한 것은 바닐라 스프링 컨텍스트에서 일반적으로 수행할 작업을 넘어서는 테스팅을 해야한다.

(바닐라 : plain. 순수함. 예를들어서 Vanilla Javascript : JQuery등을 쓰지 않은 순수 자바스크립트)

! 외부 프로퍼티, 로깅, 스프링 부트의 다른 기능들은 SpringApplication을 사용한다면 기본적으로 설치된다.

스프링 부트는 @SpringBootTest 어노테이션을 제공하고, 스프링 부트 기능을 필요로 할 때 기본적인 **spring-test** @ContextConfiguration 어노테이션을 대안으로 사용한다.
어노테이션은 SpringApplication을 통한 테스트에서 사용되는 ApplicationContext를 생성하는 것으로서 동작한다. 게다가 @SpringBootTest 는 애플리케이션의 더 구체적인 조각들을 테스트하는 것을 제공한다.

! JUnit 4를 사용한다면, @RunWith(SpringRunner.class)를 추가하는 것을 잊지마라. 안그러면 어노테이션은 무시될 수 있다. 만약 JUnit5 를 사용한다면, @ExtendWith(SpringExtension.class)를 @SpringBootTest로서 추가할 필요 없고 다른 @...Test 어노테이션들은 이미 어노테이트 되어있다.

** 본적으로, @SpringBootTest는 서버를 시작하지 않는다.** @SPringBootTest 의 webEnvironment 속성을 사용하여 테스트 실행 방법을 더 자세히 정의할 수 있다. :

* MOCK(default) : 웹 ApplicationContext을 로드하고 mock 웹 환경을 제공한다. 임베디드 서버는 이 어노테이션을 사용할 때 시작되진 않는다. 만약 웹 환경이 클래스패스를 사용하지 않는다면, 이 모드는 일반 웹이 아닌 ApplicationContext를 만드는데 투명하게 적용된다.
 웹 응용 프로그램의 mock 기반 테스트를 위해 @AutoCOnfigureMockMvc 나 @AutoConfigureWebTestClient를 함께 사용할 수 있다. 

 * RANDOM_PORT : **WebServerApplicationContext** 를 로드하고 실제 웹 환경을 제공한다. 임베디드 서버는 랜덤 포트를 listen하고 시작된다.
 * DEFINED_PORT : **WebServerApplicationContext**를 로드하고 실제 웹 환경을 제공한다. 임베디드 서버는 정의된 포트를 listen하고 시작된다. (application.properties에서) 또는 기본 8080포트로.
 * NONE : ApplicationContext를 SpringApplication을 사용해서 로드하지만 어떠한 웹 환경(mock이나 다른것들도) 제공하지 않는다.
 
 ! 만약 테스트가 @Transactional 이라면, 기본적으로 각 테스트 메소드의 끝에는 트랜잭션이 롤백된다. 하지만 RANDOM_PORT나 DEFINED_PORT 와 함께 배열을 사용하면 암시적으로 실제 서블릿 환경을 제공하므로, HTTP 클라이언트와 서버는 별도의 쓰레드에서 실행된다. 이 경우 서버에서 시작된 트랜잭션은 롤백도지 않는다.
 ! webEnvironment = WebEnvironment.RANDOM_PORT 를 쓴 @SpringBootTest는 만약 애플리케이션이 관리 서버에 다른 포트를 사용한다면 분리된 랜덤 포트에서 서버 관리를 시작할 것이다.
 
# 46.3.1 웹 애플리케이션 타입 탐지
만약 Spring MVC를 쓴다면, 보통 MVC 기반의 애플리케이션 컨텍스트는 configure된다. 만약 Spring WebFlux 만을 쓴다면 WebFlux 기반의 애플리케이션 컨텍스트를 대신해 configure하고 탐지할 것이다.

만약 둘다 쓴다면 Spring MVC가 먼저 탐지된다. 이 시나리오에서 반응성 웹 애플리케이션을 테스트하길 원한다면 아래와 같이 **spring.main.web-application-type** 프로퍼티를 세팅하자.

<pre><code>
	@RunWith(SpringRunner.class)
	@SpringBootTest(properties = "spring.main.web-application-type=reactive")
	public class MyWebFluxTests { ... }
</code></pre>

# 46.3.2 Test configure 탐지
만약 스프링 테스트 프레임워크와 친숙하다면, 스프링 @Configuration 을 구체화하기 위해 @ContextConfiguration(classes=...) 를 사용할지모른다. 대안적으로, 테스트안에서 감싸진 @Configuration 클래스들은 자주 사용된다.

스프링 부트 애플리케이션을 테스팅할 때, 이는 자주 요구되진 않는다. 스프링 부트의 @*Test 어노테이션은 모호하게 정의할 때마다 자동으로 우선적으로 configuration을 찾는다.

이 서치 알고리즘은 @SpringBootApplication 이나 @SpringBootConfiguration으로 어노테이트된 클래스들을 발견할 때까지 테스트를 포함하는 패키지로부터 동작한다. 합리적인 방법으로 코드를 구조화하면 주요 구성이 일반적으로 발견된다.

! 애플리케이션의 더 구체적인 조각들을 테스트하기위한 테스트 어노테이션을 사용한다면, 메인 메소드의 애플리케이션 클래스에서 특정 영역에 특정 구성 설정을 추가하지 않아야한다. 
@SpringBootApplication의 기초가 되는 컴포넌트 스캔 configuration은 예상대로 슬라이싱(조각으로 나눈것들)이 동작하는지 확인하는데 사용되는 exclude 필터를 정의한다. @SpringBootApplication 이 어노테이트된 클래스에서 명시적으로 @ComponentScan 을 사용하는 경우 해당 필터가 비활성화된다. 조각들을(슬라이싱) 사용하려면 재정의해야한다.

최우선의 configuration을 커스터마이징 하려고 한다면, nest된 @TestConfiguration 클래스를 사용할 수 있다. 애플리케이션의 최우선 configuration을 대신해서 사용되는 nest된 @Configuration 클래스와는 달리 애플리케이션의 우선 configuration 외에도 중첩된 @TestConfiguration 클래스가 사용된다.

! 스프링의 테스트 프레임워크는 테스트들 사이에 애플리케이션 컨텍스트들을 캐시한다. 그러므로 테스트는 같은 configuration을 공유한다.( 아무리 발견되더라도), 잠재적으로 시간소비하는 컨텍스트를 로드하는 프로세스는 한번 발생한다.

# 46.3.3 테스트 configuration 제외
애플리케이션이 컴포넌트 스캔을 하면(예를 들어서, @SpringBootApplication 이나 @ComponentScan), 특정 테스트에 대해서만 만든 top-level의 configuration 클래스가 우연히 모든 곳에서 선택될 수 있다.  
앞에서 살펴보았듯, 테스트의 내부 클래스에서 @TestConfiguration을 사용해서 최우선 configuration을 커스터마이즈할 수 있다. top-level의 클래스가 위치할 때, src/test/java 에 클래스를 가리키는 @TestConfiguration 은 스캐닝에 의해 선택되지 않아야 한다. 다음 예제와 같이 필요한 경우 해당 클래스를 명시적으로 가져올 수 있다.

<pre><code>
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MyTestsConfiguration.class)
public class MyTests {

	@Test
	public void exampleTest() {
		...
	}

}
</code></pre>

! @SpringBootApplication 이 아니라 @ComponentScan을 직접 사용한다면 TypeExcludeFilter를 등록할 필요가 있다. 그 부분은 Javadoc을 확인할 것.

# 46.3.4 mock 환경 테스팅

기본적으로 @SpringBootTest는 서버를 시작하지 않는다. mock 환경에 반해 테스트를 하길 원하는 웹 엔드포인트를 가진다면, 부가적으로 MockMvc를 아래 예제와 같이 configure 해야한다.

<pre><code>
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcExampleTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void exampleTest() throws Exception {
		this.mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("Hello World"));
	}

}
</code></pre>

! 웹 계층만 focus 맞추고 싶고 완성된 ApplicationContext를 시작하고 싶지 않다면, @WebMvcTest를 사용할 것을 고려해보자.

대안적으로, WebTestClient 를 아래 예제와 같이 configure할 수 있다.


<pre><code>
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class MockWebTestClientExampleTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void exampleTest() {
		this.webClient.get().uri("/").exchange().expectStatus().isOk().expectBody(String.class)
				.isEqualTo("Hello World");
	}

}
</code></pre>

#46.3.5 서버 동작 테스트
서버가 실행되는게 필요하다면, random port를 사용하는 것을 추천한다. @SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT) 를 사용한다면, 가용한 포트는 테스트할 때마다 랜덤으로 선택된다.

@LocalServerPort 어노테이션은 테스트에 실제 사용되는 포트를 주입할 수 있게 해준다. 편리하게도, 시작된 서버에서 REST 콜을 할 필요가 있는 테스트는 WebTestClient 를 부가적으로 @Autowire 할 수 있고, 실행중인 서버에 대한 상대 링크를 확인하고 아래와 같이 response 확인을 위한 전용 API 가 제공된다.

<pre><code>
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomPortWebTestClientExampleTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void exampleTest() {
		this.webClient.get().uri("/").exchange().expectStatus().isOk().expectBody(String.class)
				.isEqualTo("Hello World");
	}

}
</code></pre>

이 설정은 클래스패스에 spring-webflux를 요구한다. webflux를 추가할게 아니라면 스프링 부트는 TestRestTemplate를 제공한다.

<pre><code>
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomPortTestRestTemplateExampleTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void exampleTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).isEqualTo("Hello World");
	}

}
</code></pre>

#46.3.6 JMX 사용
테스트 컨텍스트 프레임워크가 컨텍스트를 캐시하기 때문에 JMX 는 기본적으로 같은 컴포넌트가 같은 도메인에 등록되지 않도록 비활성화 되어있다. 가령 테스트가 MBeanServer 에 접근할 필요가 있다면 더럽게 마킹하는 것을 고려해봐라:

<pre><code>
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.jmx.enabled=true")
@DirtiesContext
public class SampleJmxTests {

	@Autowired
	private MBeanServer mBeanServer;

	@Test
	public void exampleTest() {
		// ...
	}

}
</code></pre>

#46.3.7 Bean을 mocking 하고 spying
(mocking : 테스트를 위해 가짜 객체를 사용할 수 있도록 하는 것)
테스트할 때, 때때로 애플리케이션 컨텍스트안에 특정 컴포넌트를 mocking할 필요가 있다. 예를 들어서, 개발 동안 사용 불가능한 몇몇 원격 서비스를 가질 수 있다. Mocking은 실제 환경에서 트리거하기 어려운 실패를 시뮬레이션하기 원할 때 유용할 수 있다.

스프링 부트는 @MockBean 어노테이션을 포함하는데, 이 어노테이션을 사용해 ApplicationContext 내부의 Bean에 대한 Mockito mock 객체를 정의할 수 있다. 어노테이션을 사용하여 새로운 빈을 추가하거나 기존의 단일 빈 정의를 대체할 수 있다. 
어노테이션은 테스트 클래스, 테스트안의 필드에서 또는 @Configuration 클래스와 필드에서 직접 사용될 수 있다. 필드에 사용될 때, 생성된 mock의 인스턴스 또한 주입된다. Mock 빈은 자동적으로 각 테스트 메소드 후에 리셋된다.

! 스프링 부트의 테스트 어노테이션(가령 @SpringBootTest)중 하나를 사용하면 이 기능은 자동적으로 활성화된다. 이 기능을 다른 배열과 사용하려면, listner가 아래 예제처럼 명시적으로 표현되어야한다.
@TestExecutionListeners(MockitoTestExecutionListener.class)

RemoteService 빈을 mock 확장으로 대체하는 예제이다.

<pre><code>
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTests {

	@MockBean
	private RemoteService remoteService;

	@Autowired
	private Reverser reverser;

	@Test
	public void exampleTest() {
		// RemoteService has been injected into the reverser bean
		given(this.remoteService.someCall()).willReturn("mock");
		String reverse = reverser.reverseSomeCall();
		assertThat(reverse).isEqualTo("kcom");
	}

}
</code></pre>

! @MockBean은 애플리케이션 컨텍스트가 리프레시되는 동안 동작하는 빈의 행위를 mock하는데 사용될 수 없다. 테스트가 실행될 때까지 애플리케이션 컨텍스트 리프레시는 완료되었고 mock된 행위를 configure하기에는 너무 늦는다. 
이 상황에서 mock을 configure하고 생성하기 위해 @Bean을 메소드에 사용하는 것을 추천한다. 

부가적으로, @SpyBean을 Mockito ** spy ** 와 존재하는 어떠한 빈을 wrap 하기 위해 사용한다. 디테일한 것은 문서를 확인할 것.

! 스프링의 테스트 프레임워크가 애플리케이션 컨텍스트를 테스트와 같은 configuration을 공유하는 테스트를 위한 컨텍스트를 재사용하는 것 사이에서 캐시한다. @MockBean이나 @SpyBean의 사용은 캐시 키에 영향을 주므로 대부분의 컨텍스트의 수가 증가한다.
 
@SpyBean을 사용하여 이름으로 매개 변수를 참조하는 @Cacheable 메소드가 있는 bean 을 spy하면, 애플리케이션은 parameter를 컴파일해야만 한다. 그러면 캐싱 인프라에서 매개 변수 이름을 사용할 수 있다.

#46.3.8 자동 configure 된 테스트
스프링 부트의 자동 configure 시스템은 애플리케이션에 잘 동작하지만 때때로 테스트하기에 너무 조금일 수 있다. 애플리케이션의 조각(slice)를 테스트하기위에 요구되는 configuration의 부분들을 로드하는 것을 돕는다. 예를 들어서, Spring MVC 컨트롤러가 URL을 정확하게 매핑했는지 테스트하기를 원할 수 있고,
그 테스트에 DB와 관련없기를 바라거나 JPA 엔티티를 테스트하기를 원할 수 있고 테스트가 동작할 때 웹 계층에는 관심이 없을 수 있다.

** spring-boot-test-autoconfigure 모듈은 조각(slice)을 자동적으로 configure될 수 있는 어노테이션들을 포함한다. 각각은 비슷한 방식으로 동작하고, ApplicationContext를 로드하는 @...Test 어노테이션과 자동 configuration 세팅을 커스터마이징할 수 있는 하나 이상의 @AutoConfigure... 어노테이션을 제공한다.

! 각 조각(slice)는 컴포넌트 스캔을 적절한 컴포넌트로 제한하고 매우 제한된 자동 configuration 클래스들의 set을 로드한다. 그중 하나를 제외해야한다면 대부분 @...Test 어노테이션에서 excludeAutoConfiguration 속성을 제공한다. 대안적으로 @ImportAutoConfiguration#exclude를 사용할 수 있다.

! 여러 조각(slices)들을 하나의 테스트에서 몇몇 @...Test 어노테이션을 사용하는 것으로서 포함하는 것을 지원하지 않는다. 여러 슬라이스들이 필요하다면, @...Test 어노테이션 중 하나를 선택하고 어노테이션을 수동으로 다른 조각들의 @AUtoConfigure... 을 포함하자.

! 표준 @SPringBootTest 어노테이션과 함꼐 @AutoConfigure... 어노테이션을 사용할수도 있다. 이 조합은 슬라이싱 하는 것에 흥미가 없지만 자동 configure된 테스트 빈을 원한다면 사용할 수 있다.

#46.3.9 자동 configure Json 테스트
serialization(직렬화)와 deserialization(비직렬화) 된 JSON 오브젝트를 예상대로 동작하는지 테스트하기 위해 @JsonTest 어노테이션을 사용할 수 있다. @JsonTest는 다음 라이브러리 중 하나일 수 있는 지원되는 JSON 매퍼를 자동으로 configure해준다.:

* Jackson ObjectMapper, @JsonComponent 빈, Jackson module
* GSon
* Jsonb

! 자동 configure 의 리스트는 @JsonTest에 의해 사용가능하다.

자동 configuration 의 요소를 configure할 필요가 있다면 @AutoConfigureJsonTesters 어노테이션을 사용할 수 있다.

스프링 부트는 AssertJ 기반의 JSONAssert 와 JsonPath 라이브러리와 동작하여 Json 이 예상대로 표시되는지 확인하는 helper를 포함한다. JacksonTester, GsonTester, JsonbTester, BasicJsonTest 클래스는 각각 Jackson, Gson, Jsonb 및 Strings에 사용할 수 있다.
@JsonTest를 사용할 때 테스트 클래스의 모든 helper 필드는 @Autowired 가 될 수 있다. 다음 예제는 Jackson의 테스트 클래스이다.

<pre><code>
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.json.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@JsonTest
public class MyJsonTests {

	@Autowired
	private JacksonTester<VehicleDetails> json;

	@Test
	public void testSerialize() throws Exception {
		VehicleDetails details = new VehicleDetails("Honda", "Civic");
		// Assert against a `.json` file in the same package as the test
		assertThat(this.json.write(details)).isEqualToJson("expected.json");
		// Or use JSON path based assertions
		assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
		assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
				.isEqualTo("Honda");
	}

	@Test
	public void testDeserialize() throws Exception {
		String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
		assertThat(this.json.parse(content))
				.isEqualTo(new VehicleDetails("Ford", "Focus"));
		assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
	}

}
</code></pre>

!JSON helper 클래스들은 표준 단위테스트에 직접적으로 사용될 수 있다. 그렇게 되기 위해, @JsonTest를 사용하지 않는다면 @Before 메소드의 helper 의 initFields 메소드를 부른다.

주어진 JSON 경로에 숫자값에 assert 하기 위해 스프링 부트의 AssertJ기반의 helper를 사용한다면, 타입에 의존하는 isEqualTo 를 사용할 수 없을 수 있다. 대신에, 주어진 상황과 맞는 값을 assert 하는 AssertJ의 satisfied 를 사용할 수 있다.
예를 들어서, 아래 예제는 실제 숫자가 0.01 오프셋 내에서 0.15에 가까운 float이라는 것을 나타낸다.

<pre><code>
assertThat(json.write(message))
    .extractingJsonPathNumberValue("@.test.numberValue")
    .satisfies((number) -> assertThat(number.floatValue()).isCloseTo(0.15f, within(0.01f)));
</code></pre>

#46.3.10 자동 configure된 스프링 MVC 테스트
스프링 MVC 컨트롤러가 예상대로 동작하는지 테스트하려면 @WebMvcTest 어노테이션을 사용한다. @WebMvcTest는 Spring MVC 인프라를 자동으로 configure하고 스캔된 빈을 @Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, WebMvcConfigurer 및 HandlerMethodArgumentResolver 로 제한한다.
이 어노테이션을 사용할 때 일반 @ComponentBean은 스캔되지 않는다.

! 자동 configure 된 세팅들은 @WebMvcTest에 의해 사용될 수 있다.
! 가령 Jackson Module 같이 특정 컴포넌트를 등록할 필요가 있다면 테스트에 @Import를 사용해서 부가적인 Configuration 클래스들을 가져올 수 있다.

자주, @WebMvcTest 는 단일 컨트롤러로 제한되고 @MockBean과 함께 사용되어 필요한 공동 작업자에게 mock 구현을 제공한다. @WebMvcTest 는 또한 MockMvc 를 자동 configure 한다. Mock MVC 는 HTTP 서버 전체를 시작할 필요 없이 MVC 컨트롤러를 빠르게 테스트하기 위한 강력한 방법이다.

! 가령 @SpringBootTest 를 @AutoConfigureMockMvc와 함께 어노테이트하는 것에 의해 @WebMvcTest 없이 자동 configure할 수 있다. 아래는 MockMvc 를 사용한 예제다.

<pre><code>
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserVehicleController.class)
public class MyControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserVehicleService userVehicleService;

	@Test
	public void testExample() throws Exception {
		given(this.userVehicleService.getVehicleDetails("sboot"))
				.willReturn(new VehicleDetails("Honda", "Civic"));
		this.mvc.perform(get("/sboot/vehicle").accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
	}

}
</code></pre>

!  자동 configure 할 필요가 있는 경우(예를 들어서 서블릿 필터를 적용해야 하는 경우) @AutoConfigureMockMvc 어노테이션의 속성을 사용할 수 있다.

만약 HtmlUnit 이나 Selenium 을 사용한다면, 자동 configuration 는 HTMLUnit WebClient 빈과/이나 WebDriver 빈을 제공한다. 아래 예제는 HtmlUnit을 사용한 예제이다.

<pre><code>
import com.gargoylesoftware.htmlunit.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserVehicleController.class)
public class MyHtmlUnitTests {

	@Autowired
	private WebClient webClient;

	@MockBean
	private UserVehicleService userVehicleService;

	@Test
	public void testExample() throws Exception {
		given(this.userVehicleService.getVehicleDetails("sboot"))
				.willReturn(new VehicleDetails("Honda", "Civic"));
		HtmlPage page = this.webClient.getPage("/sboot/vehicle.html");
		assertThat(page.getBody().getTextContent()).isEqualTo("Honda Civic");
	}

}
</code></pre>

! 기본적으로, 스프링 부트는 WebDriver 빈을 특별한 스코프에 넣어 각 테스트 후에 드라이버가 종료되고 새 인스턴스가 주입되도록 한다. 이 동작을 원하지 않는다면 @Scope("singleton") 을 WebDriver를 @Bean 정의에 추가할 수 있다.

! 스프링 부트가 생성한 webDriver 스코프는 동일한 이름의 사용자 정의 스코프를 대체한다. webDriver 스코프를 정의하면 @WebMvcTest를 사용할 때 작동하지 않는 것을 알 수 있다.

스프링 시큐리티가 클래스패스에 있다면, @WebMvcTest는 WebSecurityConfigurer 빈을 스캔할 것이다. 시큐리티가 그 테스트에 완전하게 비활성화되는 대신에, 스프링 시큐리티의 테스트 지원을 사용할 수 있다. 어떻게 스프링 시큐리티의 MockMvc 지원을 사용하는 지는 Chapter 80. Testing With Spring Security를 확인하자.

! 때때로 스프링 MVC 테스트를 작성하는 것으로 충분하지 않다;스프링 부트는 end-to-end 테스트를 실 서버에서 도울 수 있다.
















   