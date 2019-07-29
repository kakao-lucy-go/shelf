https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
������ ��Ʈ ���۷��� ���� 

46 Testing
======

Spring Boot�� ���� �׽�Ʈ�� �� ������ �Ǵ� ������̼ǰ� ��ƿ���� �����Ѵ�. �׽�Ʈ ������ �ΰ��� ���μ� �����ȴ� : **spring-boot-test** �� �ھ �����ϰ�, **spring-boot-test-autoconfigure** �� auto-configuration�� �����Ѵ�.
��κ��� �����ڵ��� JUnit, AssertJ, Hamcrest, �ٸ� ������ ���̺귯���� ���� ������ ��Ʈ �׽�Ʈ ������ ���� **spring-boot-starter-test** ��Ÿ�͸� ����Ѵ�.

46.1 �׽�Ʈ ���� ������
----
**spring-boot-starter-test** ��Ÿ�ʹ� (test scope�ȿ���) �Ʒ��� ���̺귯������ �����Ѵ�.:

* JUnit 4 : Java ���� ���� �׽�Ʈ�� ���� ��ǻ��� ǥ��
* Spring Test & Spring Boot Test : ������ ��Ʈ ���� ���� ��ƿ��Ƽ�� ���� �׽�Ʈ ����
* AssertJ : ��â�� assert ���̺귯��
(assert : ���α׷��� ��Ÿ�� ������ üũ�ϰ� ����� ������ �����ϴ� ����� ���. printf�� ���� ����� ������ �ϰ������� on/off�� �� �ְ�, ���ǰ˻縦 ���� ����ȭ�� �����޽����� ����� �� �ִ�.
printf�� CPU ���� :  printf�� ����ϱ� ���� CPU�� �ʱ�ȭ�ϰ�, ��Ʈ�� �����ϰ�, UART ����� �ʱ�ȭ�ϰ� �ѱ��ھ� ����ϴ� �����̱� ������ CPU ������ ���µ��ٰ� �ð��� �����ɸ���.)

* Hamcrest : ���� ǥ���� ��ü(�������� �Ǵ� ������ ��) ���̺귯��
* Mockito : �ڹ� mock �����ӿ�ũ
(mock : �׽�Ʈ�� ���� ��¥(�뿪) ��ü�� �����. �̶� ��������� ��¥ ��ü�� Mock Object��� �Ѵ�.)

* JSONassert : JSON�� ���� assert ���̺귯��
* JsonPath : Json XPath
(XPath : XML Path Language. XML ������ Ư�� ��ҳ� �Ӽ��� �����ϱ� ���� ��θ� �����ϴ� ���. XML ���� ����.. ���� �Լ� ���� ǥ���� ����� �� �ִ�.. W3C ǥ�ؾ�.)

�Ϲ������� �̷� common�� ���̺귯������ �׽�Ʈ�� �ۼ��Ҷ� �����ϴ�. ���� �� ���̺귯������ �ʿ信 ���� �ʴ´ٸ� �ΰ����� test �������� �߰��� �� �ִ�.

46.2
-----
������ ������ �ֵ� ���� �� �ϳ��� �ڵ尡 ���� �׽�Ʈ�� �� ���� �����Ѵٴ� ���̴�. ������ �������� ������� new �� ����ؼ� ������Ʈ�� �ν��Ͻ�ȭ�� �� �ִ�. ���� mock ������Ʈ�� ���� ������ ��ſ� ����� �� �ִ�.

����, ������ ApplicationContext�� ���� �׽�Ʈ�� �Ѿ ���� �׽�Ʈ�� �����ؾ� �Ѵ�. �̴� �ٸ� ������ ������ �ʿ䰡 �ְų� ���ø����̼��� ���÷��̸� �䱸�ϴ� �� ���� ���� �׽�Ʈ�� �����ϱ⿡ �����ϴ�. 

������ �����ӿ�ũ�� �׷� ���� �׽�Ʈ�� ���� ���� �׽�Ʈ ����� �����Ѵ�. **org.springframework** �� ���� �������� ���� ������ �� �ִ�. : **org.springframework:spring-test** �� **spring-boot-starter-test** ��Ÿ�͸� ����ؼ� �Ͻ������� �����´�.
���� **spring-test** ����� ���� ����غ����� ������, ������ �����ӿ�ũ ���۷��� �������� relevant section�� �а� �����ؾ߸��Ѵ�.

(https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/testing.html#testing)

46.3 ������ ��Ʈ ���ø����̼� �׽���
-----

������ ��Ʈ ���ø����̼��� ������ **ApplicationContext** ���� �ſ� Ư���� ���� �ٴҶ� ������ ���ؽ�Ʈ���� �Ϲ������� ������ �۾��� �Ѿ�� �׽����� �ؾ��Ѵ�.

(�ٴҶ� : plain. ������. ������ Vanilla Javascript : JQuery���� ���� ���� ���� �ڹٽ�ũ��Ʈ)

! �ܺ� ������Ƽ, �α�, ������ ��Ʈ�� �ٸ� ��ɵ��� SpringApplication�� ����Ѵٸ� �⺻������ ��ġ�ȴ�.

������ ��Ʈ�� @SpringBootTest ������̼��� �����ϰ�, ������ ��Ʈ ����� �ʿ�� �� �� �⺻���� **spring-test** @ContextConfiguration ������̼��� ������� ����Ѵ�.
������̼��� SpringApplication�� ���� �׽�Ʈ���� ���Ǵ� ApplicationContext�� �����ϴ� �����μ� �����Ѵ�. �Դٰ� @SpringBootTest �� ���ø����̼��� �� ��ü���� �������� �׽�Ʈ�ϴ� ���� �����Ѵ�.

! JUnit 4�� ����Ѵٸ�, @RunWith(SpringRunner.class)�� �߰��ϴ� ���� ��������. �ȱ׷��� ������̼��� ���õ� �� �ִ�. ���� JUnit5 �� ����Ѵٸ�, @ExtendWith(SpringExtension.class)�� @SpringBootTest�μ� �߰��� �ʿ� ���� �ٸ� @...Test ������̼ǵ��� �̹� �������Ʈ �Ǿ��ִ�.

** ��������, @SpringBootTest�� ������ �������� �ʴ´�.** @SPringBootTest �� webEnvironment �Ӽ��� ����Ͽ� �׽�Ʈ ���� ����� �� �ڼ��� ������ �� �ִ�. :

* MOCK(default) : �� ApplicationContext�� �ε��ϰ� mock �� ȯ���� �����Ѵ�. �Ӻ���� ������ �� ������̼��� ����� �� ���۵��� �ʴ´�. ���� �� ȯ���� Ŭ�����н��� ������� �ʴ´ٸ�, �� ���� �Ϲ� ���� �ƴ� ApplicationContext�� ����µ� �����ϰ� ����ȴ�.
 �� ���� ���α׷��� mock ��� �׽�Ʈ�� ���� @AutoCOnfigureMockMvc �� @AutoConfigureWebTestClient�� �Բ� ����� �� �ִ�. 

 * RANDOM_PORT : **WebServerApplicationContext** �� �ε��ϰ� ���� �� ȯ���� �����Ѵ�. �Ӻ���� ������ ���� ��Ʈ�� listen�ϰ� ���۵ȴ�.
 * DEFINED_PORT : **WebServerApplicationContext**�� �ε��ϰ� ���� �� ȯ���� �����Ѵ�. �Ӻ���� ������ ���ǵ� ��Ʈ�� listen�ϰ� ���۵ȴ�. (application.properties����) �Ǵ� �⺻ 8080��Ʈ��.
 * NONE : ApplicationContext�� SpringApplication�� ����ؼ� �ε������� ��� �� ȯ��(mock�̳� �ٸ��͵鵵) �������� �ʴ´�.
 
 ! ���� �׽�Ʈ�� @Transactional �̶��, �⺻������ �� �׽�Ʈ �޼ҵ��� ������ Ʈ������� �ѹ�ȴ�. ������ RANDOM_PORT�� DEFINED_PORT �� �Բ� �迭�� ����ϸ� �Ͻ������� ���� ���� ȯ���� �����ϹǷ�, HTTP Ŭ���̾�Ʈ�� ������ ������ �����忡�� ����ȴ�. �� ��� �������� ���۵� Ʈ������� �ѹ鵵�� �ʴ´�.
 ! webEnvironment = WebEnvironment.RANDOM_PORT �� �� @SpringBootTest�� ���� ���ø����̼��� ���� ������ �ٸ� ��Ʈ�� ����Ѵٸ� �и��� ���� ��Ʈ���� ���� ������ ������ ���̴�.
 
# 46.3.1 �� ���ø����̼� Ÿ�� Ž��
���� Spring MVC�� ���ٸ�, ���� MVC ����� ���ø����̼� ���ؽ�Ʈ�� configure�ȴ�. ���� Spring WebFlux ���� ���ٸ� WebFlux ����� ���ø����̼� ���ؽ�Ʈ�� ����� configure�ϰ� Ž���� ���̴�.

���� �Ѵ� ���ٸ� Spring MVC�� ���� Ž���ȴ�. �� �ó��������� ������ �� ���ø����̼��� �׽�Ʈ�ϱ� ���Ѵٸ� �Ʒ��� ���� **spring.main.web-application-type** ������Ƽ�� ��������.

<pre><code>
	@RunWith(SpringRunner.class)
	@SpringBootTest(properties = "spring.main.web-application-type=reactive")
	public class MyWebFluxTests { ... }
</code></pre>

# 46.3.2 Test configure Ž��
���� ������ �׽�Ʈ �����ӿ�ũ�� ģ���ϴٸ�, ������ @Configuration �� ��üȭ�ϱ� ���� @ContextConfiguration(classes=...) �� ��������𸥴�. ���������, �׽�Ʈ�ȿ��� ������ @Configuration Ŭ�������� ���� ���ȴ�.

������ ��Ʈ ���ø����̼��� �׽����� ��, �̴� ���� �䱸���� �ʴ´�. ������ ��Ʈ�� @*Test ������̼��� ��ȣ�ϰ� ������ ������ �ڵ����� �켱������ configuration�� ã�´�.

�� ��ġ �˰����� @SpringBootApplication �̳� @SpringBootConfiguration���� �������Ʈ�� Ŭ�������� �߰��� ������ �׽�Ʈ�� �����ϴ� ��Ű���κ��� �����Ѵ�. �ո����� ������� �ڵ带 ����ȭ�ϸ� �ֿ� ������ �Ϲ������� �߰ߵȴ�.

! ���ø����̼��� �� ��ü���� �������� �׽�Ʈ�ϱ����� �׽�Ʈ ������̼��� ����Ѵٸ�, ���� �޼ҵ��� ���ø����̼� Ŭ�������� Ư�� ������ Ư�� ���� ������ �߰����� �ʾƾ��Ѵ�. 
@SpringBootApplication�� ���ʰ� �Ǵ� ������Ʈ ��ĵ configuration�� ������ �����̽�(�������� �����͵�)�� �����ϴ��� Ȯ���ϴµ� ���Ǵ� exclude ���͸� �����Ѵ�. @SpringBootApplication �� �������Ʈ�� Ŭ�������� ��������� @ComponentScan �� ����ϴ� ��� �ش� ���Ͱ� ��Ȱ��ȭ�ȴ�. ��������(�����̽�) ����Ϸ��� �������ؾ��Ѵ�.

�ֿ켱�� configuration�� Ŀ���͸���¡ �Ϸ��� �Ѵٸ�, nest�� @TestConfiguration Ŭ������ ����� �� �ִ�. ���ø����̼��� �ֿ켱 configuration�� ����ؼ� ���Ǵ� nest�� @Configuration Ŭ�����ʹ� �޸� ���ø����̼��� �켱 configuration �ܿ��� ��ø�� @TestConfiguration Ŭ������ ���ȴ�.

! �������� �׽�Ʈ �����ӿ�ũ�� �׽�Ʈ�� ���̿� ���ø����̼� ���ؽ�Ʈ���� ĳ���Ѵ�. �׷��Ƿ� �׽�Ʈ�� ���� configuration�� �����Ѵ�.( �ƹ��� �߰ߵǴ���), ���������� �ð��Һ��ϴ� ���ؽ�Ʈ�� �ε��ϴ� ���μ����� �ѹ� �߻��Ѵ�.

# 46.3.3 �׽�Ʈ configuration ����
���ø����̼��� ������Ʈ ��ĵ�� �ϸ�(���� ��, @SpringBootApplication �̳� @ComponentScan), Ư�� �׽�Ʈ�� ���ؼ��� ���� top-level�� configuration Ŭ������ �쿬�� ��� ������ ���õ� �� �ִ�.  
�տ��� ���캸�ҵ�, �׽�Ʈ�� ���� Ŭ�������� @TestConfiguration�� ����ؼ� �ֿ켱 configuration�� Ŀ���͸������� �� �ִ�. top-level�� Ŭ������ ��ġ�� ��, src/test/java �� Ŭ������ ����Ű�� @TestConfiguration �� ��ĳ�׿� ���� ���õ��� �ʾƾ� �Ѵ�. ���� ������ ���� �ʿ��� ��� �ش� Ŭ������ ��������� ������ �� �ִ�.

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

! @SpringBootApplication �� �ƴ϶� @ComponentScan�� ���� ����Ѵٸ� TypeExcludeFilter�� ����� �ʿ䰡 �ִ�. �� �κ��� Javadoc�� Ȯ���� ��.

# 46.3.4 mock ȯ�� �׽���

�⺻������ @SpringBootTest�� ������ �������� �ʴ´�. mock ȯ�濡 ���� �׽�Ʈ�� �ϱ� ���ϴ� �� ��������Ʈ�� �����ٸ�, �ΰ������� MockMvc�� �Ʒ� ������ ���� configure �ؾ��Ѵ�.

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

! �� ������ focus ���߰� �Ͱ� �ϼ��� ApplicationContext�� �����ϰ� ���� �ʴٸ�, @WebMvcTest�� ����� ���� ����غ���.

���������, WebTestClient �� �Ʒ� ������ ���� configure�� �� �ִ�.


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

#46.3.5 ���� ���� �׽�Ʈ
������ ����Ǵ°� �ʿ��ϴٸ�, random port�� ����ϴ� ���� ��õ�Ѵ�. @SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT) �� ����Ѵٸ�, ������ ��Ʈ�� �׽�Ʈ�� ������ �������� ���õȴ�.

@LocalServerPort ������̼��� �׽�Ʈ�� ���� ���Ǵ� ��Ʈ�� ������ �� �ְ� ���ش�. ���ϰԵ�, ���۵� �������� REST ���� �� �ʿ䰡 �ִ� �׽�Ʈ�� WebTestClient �� �ΰ������� @Autowire �� �� �ְ�, �������� ������ ���� ��� ��ũ�� Ȯ���ϰ� �Ʒ��� ���� response Ȯ���� ���� ���� API �� �����ȴ�.

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

�� ������ Ŭ�����н��� spring-webflux�� �䱸�Ѵ�. webflux�� �߰��Ұ� �ƴ϶�� ������ ��Ʈ�� TestRestTemplate�� �����Ѵ�.

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

#46.3.6 JMX ���
�׽�Ʈ ���ؽ�Ʈ �����ӿ�ũ�� ���ؽ�Ʈ�� ĳ���ϱ� ������ JMX �� �⺻������ ���� ������Ʈ�� ���� �����ο� ��ϵ��� �ʵ��� ��Ȱ��ȭ �Ǿ��ִ�. ���� �׽�Ʈ�� MBeanServer �� ������ �ʿ䰡 �ִٸ� ������ ��ŷ�ϴ� ���� ����غ���:

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

#46.3.7 Bean�� mocking �ϰ� spying
(mocking : �׽�Ʈ�� ���� ��¥ ��ü�� ����� �� �ֵ��� �ϴ� ��)
�׽�Ʈ�� ��, ������ ���ø����̼� ���ؽ�Ʈ�ȿ� Ư�� ������Ʈ�� mocking�� �ʿ䰡 �ִ�. ���� ��, ���� ���� ��� �Ұ����� ��� ���� ���񽺸� ���� �� �ִ�. Mocking�� ���� ȯ�濡�� Ʈ�����ϱ� ����� ���и� �ùķ��̼��ϱ� ���� �� ������ �� �ִ�.

������ ��Ʈ�� @MockBean ������̼��� �����ϴµ�, �� ������̼��� ����� ApplicationContext ������ Bean�� ���� Mockito mock ��ü�� ������ �� �ִ�. ������̼��� ����Ͽ� ���ο� ���� �߰��ϰų� ������ ���� �� ���Ǹ� ��ü�� �� �ִ�. 
������̼��� �׽�Ʈ Ŭ����, �׽�Ʈ���� �ʵ忡�� �Ǵ� @Configuration Ŭ������ �ʵ忡�� ���� ���� �� �ִ�. �ʵ忡 ���� ��, ������ mock�� �ν��Ͻ� ���� ���Եȴ�. Mock ���� �ڵ������� �� �׽�Ʈ �޼ҵ� �Ŀ� ���µȴ�.

! ������ ��Ʈ�� �׽�Ʈ ������̼�(���� @SpringBootTest)�� �ϳ��� ����ϸ� �� ����� �ڵ������� Ȱ��ȭ�ȴ�. �� ����� �ٸ� �迭�� ����Ϸ���, listner�� �Ʒ� ����ó�� ��������� ǥ���Ǿ���Ѵ�.
@TestExecutionListeners(MockitoTestExecutionListener.class)

RemoteService ���� mock Ȯ������ ��ü�ϴ� �����̴�.

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

! @MockBean�� ���ø����̼� ���ؽ�Ʈ�� �������õǴ� ���� �����ϴ� ���� ������ mock�ϴµ� ���� �� ����. �׽�Ʈ�� ����� ������ ���ø����̼� ���ؽ�Ʈ �������ô� �Ϸ�Ǿ��� mock�� ������ configure�ϱ⿡�� �ʹ� �ʴ´�. 
�� ��Ȳ���� mock�� configure�ϰ� �����ϱ� ���� @Bean�� �޼ҵ忡 ����ϴ� ���� ��õ�Ѵ�. 

�ΰ�������, @SpyBean�� Mockito ** spy ** �� �����ϴ� ��� ���� wrap �ϱ� ���� ����Ѵ�. �������� ���� ������ Ȯ���� ��.

! �������� �׽�Ʈ �����ӿ�ũ�� ���ø����̼� ���ؽ�Ʈ�� �׽�Ʈ�� ���� configuration�� �����ϴ� �׽�Ʈ�� ���� ���ؽ�Ʈ�� �����ϴ� �� ���̿��� ĳ���Ѵ�. @MockBean�̳� @SpyBean�� ����� ĳ�� Ű�� ������ �ֹǷ� ��κ��� ���ؽ�Ʈ�� ���� �����Ѵ�.
 
@SpyBean�� ����Ͽ� �̸����� �Ű� ������ �����ϴ� @Cacheable �޼ҵ尡 �ִ� bean �� spy�ϸ�, ���ø����̼��� parameter�� �������ؾ߸� �Ѵ�. �׷��� ĳ�� �����󿡼� �Ű� ���� �̸��� ����� �� �ִ�.

#46.3.8 �ڵ� configure �� �׽�Ʈ
������ ��Ʈ�� �ڵ� configure �ý����� ���ø����̼ǿ� �� ���������� ������ �׽�Ʈ�ϱ⿡ �ʹ� ������ �� �ִ�. ���ø����̼��� ����(slice)�� �׽�Ʈ�ϱ����� �䱸�Ǵ� configuration�� �κе��� �ε��ϴ� ���� ���´�. ���� ��, Spring MVC ��Ʈ�ѷ��� URL�� ��Ȯ�ϰ� �����ߴ��� �׽�Ʈ�ϱ⸦ ���� �� �ְ�,
�� �׽�Ʈ�� DB�� ���þ��⸦ �ٶ�ų� JPA ��ƼƼ�� �׽�Ʈ�ϱ⸦ ���� �� �ְ� �׽�Ʈ�� ������ �� �� �������� ������ ���� �� �ִ�.

** spring-boot-test-autoconfigure ����� ����(slice)�� �ڵ������� configure�� �� �ִ� ������̼ǵ��� �����Ѵ�. ������ ����� ������� �����ϰ�, ApplicationContext�� �ε��ϴ� @...Test ������̼ǰ� �ڵ� configuration ������ Ŀ���͸���¡�� �� �ִ� �ϳ� �̻��� @AutoConfigure... ������̼��� �����Ѵ�.

! �� ����(slice)�� ������Ʈ ��ĵ�� ������ ������Ʈ�� �����ϰ� �ſ� ���ѵ� �ڵ� configuration Ŭ�������� set�� �ε��Ѵ�. ���� �ϳ��� �����ؾ��Ѵٸ� ��κ� @...Test ������̼ǿ��� excludeAutoConfiguration �Ӽ��� �����Ѵ�. ��������� @ImportAutoConfiguration#exclude�� ����� �� �ִ�.

! ���� ����(slices)���� �ϳ��� �׽�Ʈ���� ��� @...Test ������̼��� ����ϴ� �����μ� �����ϴ� ���� �������� �ʴ´�. ���� �����̽����� �ʿ��ϴٸ�, @...Test ������̼� �� �ϳ��� �����ϰ� ������̼��� �������� �ٸ� �������� @AUtoConfigure... �� ��������.

! ǥ�� @SPringBootTest ������̼ǰ� �Բ� @AutoConfigure... ������̼��� ����Ҽ��� �ִ�. �� ������ �����̽� �ϴ� �Ϳ� ��̰� ������ �ڵ� configure�� �׽�Ʈ ���� ���Ѵٸ� ����� �� �ִ�.

#46.3.9 �ڵ� configure Json �׽�Ʈ
serialization(����ȭ)�� deserialization(������ȭ) �� JSON ������Ʈ�� ������ �����ϴ��� �׽�Ʈ�ϱ� ���� @JsonTest ������̼��� ����� �� �ִ�. @JsonTest�� ���� ���̺귯�� �� �ϳ��� �� �ִ� �����Ǵ� JSON ���۸� �ڵ����� configure���ش�.:

* Jackson ObjectMapper, @JsonComponent ��, Jackson module
* GSon
* Jsonb

! �ڵ� configure �� ����Ʈ�� @JsonTest�� ���� ��밡���ϴ�.

�ڵ� configuration �� ��Ҹ� configure�� �ʿ䰡 �ִٸ� @AutoConfigureJsonTesters ������̼��� ����� �� �ִ�.

������ ��Ʈ�� AssertJ ����� JSONAssert �� JsonPath ���̺귯���� �����Ͽ� Json �� ������ ǥ�õǴ��� Ȯ���ϴ� helper�� �����Ѵ�. JacksonTester, GsonTester, JsonbTester, BasicJsonTest Ŭ������ ���� Jackson, Gson, Jsonb �� Strings�� ����� �� �ִ�.
@JsonTest�� ����� �� �׽�Ʈ Ŭ������ ��� helper �ʵ�� @Autowired �� �� �� �ִ�. ���� ������ Jackson�� �׽�Ʈ Ŭ�����̴�.

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

!JSON helper Ŭ�������� ǥ�� �����׽�Ʈ�� ���������� ���� �� �ִ�. �׷��� �Ǳ� ����, @JsonTest�� ������� �ʴ´ٸ� @Before �޼ҵ��� helper �� initFields �޼ҵ带 �θ���.

�־��� JSON ��ο� ���ڰ��� assert �ϱ� ���� ������ ��Ʈ�� AssertJ����� helper�� ����Ѵٸ�, Ÿ�Կ� �����ϴ� isEqualTo �� ����� �� ���� �� �ִ�. ��ſ�, �־��� ��Ȳ�� �´� ���� assert �ϴ� AssertJ�� satisfied �� ����� �� �ִ�.
���� ��, �Ʒ� ������ ���� ���ڰ� 0.01 ������ ������ 0.15�� ����� float�̶�� ���� ��Ÿ����.

<pre><code>
assertThat(json.write(message))
    .extractingJsonPathNumberValue("@.test.numberValue")
    .satisfies((number) -> assertThat(number.floatValue()).isCloseTo(0.15f, within(0.01f)));
</code></pre>

#46.3.10 �ڵ� configure�� ������ MVC �׽�Ʈ
������ MVC ��Ʈ�ѷ��� ������ �����ϴ��� �׽�Ʈ�Ϸ��� @WebMvcTest ������̼��� ����Ѵ�. @WebMvcTest�� Spring MVC ������ �ڵ����� configure�ϰ� ��ĵ�� ���� @Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, WebMvcConfigurer �� HandlerMethodArgumentResolver �� �����Ѵ�.
�� ������̼��� ����� �� �Ϲ� @ComponentBean�� ��ĵ���� �ʴ´�.

! �ڵ� configure �� ���õ��� @WebMvcTest�� ���� ���� �� �ִ�.
! ���� Jackson Module ���� Ư�� ������Ʈ�� ����� �ʿ䰡 �ִٸ� �׽�Ʈ�� @Import�� ����ؼ� �ΰ����� Configuration Ŭ�������� ������ �� �ִ�.

����, @WebMvcTest �� ���� ��Ʈ�ѷ��� ���ѵǰ� @MockBean�� �Բ� ���Ǿ� �ʿ��� ���� �۾��ڿ��� mock ������ �����Ѵ�. @WebMvcTest �� ���� MockMvc �� �ڵ� configure �Ѵ�. Mock MVC �� HTTP ���� ��ü�� ������ �ʿ� ���� MVC ��Ʈ�ѷ��� ������ �׽�Ʈ�ϱ� ���� ������ ����̴�.

! ���� @SpringBootTest �� @AutoConfigureMockMvc�� �Բ� �������Ʈ�ϴ� �Ϳ� ���� @WebMvcTest ���� �ڵ� configure�� �� �ִ�. �Ʒ��� MockMvc �� ����� ������.

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

!  �ڵ� configure �� �ʿ䰡 �ִ� ���(���� �� ���� ���͸� �����ؾ� �ϴ� ���) @AutoConfigureMockMvc ������̼��� �Ӽ��� ����� �� �ִ�.

���� HtmlUnit �̳� Selenium �� ����Ѵٸ�, �ڵ� configuration �� HTMLUnit WebClient ���/�̳� WebDriver ���� �����Ѵ�. �Ʒ� ������ HtmlUnit�� ����� �����̴�.

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

! �⺻������, ������ ��Ʈ�� WebDriver ���� Ư���� �������� �־� �� �׽�Ʈ �Ŀ� ����̹��� ����ǰ� �� �ν��Ͻ��� ���Եǵ��� �Ѵ�. �� ������ ������ �ʴ´ٸ� @Scope("singleton") �� WebDriver�� @Bean ���ǿ� �߰��� �� �ִ�.

! ������ ��Ʈ�� ������ webDriver �������� ������ �̸��� ����� ���� �������� ��ü�Ѵ�. webDriver �������� �����ϸ� @WebMvcTest�� ����� �� �۵����� �ʴ� ���� �� �� �ִ�.

������ ��ť��Ƽ�� Ŭ�����н��� �ִٸ�, @WebMvcTest�� WebSecurityConfigurer ���� ��ĵ�� ���̴�. ��ť��Ƽ�� �� �׽�Ʈ�� �����ϰ� ��Ȱ��ȭ�Ǵ� ��ſ�, ������ ��ť��Ƽ�� �׽�Ʈ ������ ����� �� �ִ�. ��� ������ ��ť��Ƽ�� MockMvc ������ ����ϴ� ���� Chapter 80. Testing With Spring Security�� Ȯ������.

! ������ ������ MVC �׽�Ʈ�� �ۼ��ϴ� ������ ������� �ʴ�;������ ��Ʈ�� end-to-end �׽�Ʈ�� �� �������� ���� �� �ִ�.
















   