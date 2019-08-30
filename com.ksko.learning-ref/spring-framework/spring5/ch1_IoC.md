# 제어의 역전 (IoC)
제어의 역전은 의존성 주입(DI)라고 하기도 한다. 객체가 동작하는데 필요한 것들을 정의하는 과정이다. 생성자 인수, 팩토리 메소드에 대한 인수, 팩토리 메소드에서 생성되거나 리턴된 객체 인스턴스의 특성들을 통해 의존성을 정의하고, 컨테이너는 빈이 생성될 때 이 의존성을 주입한다.  
  
어떠한 타입의 객체도 다룰 수 있는 설정 매커니즘을 제공하는 BeanFactory의 서브 인터페이스인 ApplicationContext는 아래 네 가지 기능을 제공한다.
  
* 스프링 AOP
* 국제화 메시지 리소스 핸들링
* 이벤트
* 애플리케이션 계층에서 지정한 특정 컨텍스트(ex, webApplicationContext)
  
  
애플리케이션의 뼈대를 이루고 IoC 컨테이너에 의해 인스턴스화 되고 관리되는 객체들을 Beans라고 한다.  ApplicationContext 인터페이스는 Spring IoC 컨테이너를 말하고, beans를 관리한다. 구성은 xml, java, annotation으로 할 수 있다.
  
* xml은 주로 ClassPathXmlApplicationContext 나 FileSystemXmlAplicationContext를 사용하는 것이 일반적이다.  
* 어노테이션 기반 ( 챕터 1.9)  
암시적으로 등록된 post-processor에는,  
	1. AutowiredAnnotationBeanPostProcessor  
		: @Autowired  
	2. CommonAnnotationBeanPostProcessor  
		: @Resource, @PostContruct, @PreDestroy
	3. PersistenceAnnotationBeanPostProcessor  
	4. RequiredAnnotationBeanPostProcessor  
		: @Required  
	5. ConfigurationClassBeanPostProcessor  
		: @Configuration  
를 포함한다.  
  
### @Required  
영향을 받는 빈 프로퍼티 설정 시에 명시적인 프로퍼티나 autowiring을 통해 설정이 되어야만 한다는 것을 의미한다. 그렇지 않다면 NullpointerException 예외가 발생한다.  
  
### @Autowired  
JSR 330의 @Inject 어노테이션 대신에 @Autowired를 사용할 수 있다. Maven을 사용한다면 관련 의존성을 pom.xml에 주입한 후 사용할 수 있다.  즉, @Inject는 자바 기반이기 떄문에 스프링 외에도 사용할 수 있지만 @Autowired는 스프링에서 등장한 어노테이션으로, 스프링에서만 사용할 수 있다.  
@Autowired는 setter, 메소드, 필드에 적용할 수 있다.  
다만, 주입 시에 선언이 되어있어야만 한다. @Order, @Priority 를 사용해서 빈 정의 등록 순서를 설정할 수 있다.  
  
1. @Order  
@Order는 @Bean 메소드에서 선언할 수 있고, 주입 지점에서 우선 순위에 영향을 줄 수 있지만, @DependsOn 선언으로 결정되는 싱글톤 시작 순서에는 영향을 끼치지 않는다. @DependsOn은 주입 때 의존하는 빈의 이름을 설정하는 어노테이션이다.  
@Autowired의 옵션으로 required 속성이 있다. 이는 해당 타입의 빈 객체가 없어도 예외를 발생시키지 않는다. 혹은 null 객체도 wrapping할 수 있는 java.util.Optional을 사용한 인자를 주입받거나  
<pre><code>
public class SimpleMovieLister {
	@Autowired
	public void setMovieFinder(Optional<MovieFinder> movieFinder) {}
}
</code></pre>  
  
파라미터 앞에 @Nullable을 선언해서 예외를 피할 수도 있다.  
뿐만 아니라 BeanFactory, ApplicationContext, Environment같은 종속성 인터페이스에도 사용할 수 있다.  
  
2. @Primary  
한 타입에 여러 후보가 있을 때 특정 빈에 우선순위를 부여한다.  
<pre><code>
@Configuration
public class MovieConfiguration {

    @Bean
    @Primary
    public MovieCatalog firstMovieCatalog() { ... }

    @Bean
    public MovieCatalog secondMovieCatalog() { ... }

    // ...
}
</code></pre>  
이 경우에는 firstMovieCatalog 가 자동 주입된다.  
<pre><code>
public class MovieRecommender {

    @Autowired
    private MovieCatalog movieCatalog;

    // ...
}
</code></pre>  
  
### @Qualifier  
마찬가지로, 한 타입에 여러 후보가 있을 때, @Qualifier 를 지정하고 이름을 부여하면 빈 이름과 일치하는 빈을 사용한다.  
  
### @Required 와 @Autowired  
적절한 빈을 찾아 주입하는 것에 대해서 같은 기능을 수행하지만,  
@Required는 이름을, @Autowired는 타입을 우선으로 검색한다.  
  
## custom annotation 만들기  
예제 먼저 보면,  
<pre><code>
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Genre {

    String value();
}
</code></pre>  
  
어노테이션 커스터마이징에 사용하는 메타 데이터  
@Target : 어디에 선언?  
ElementType.TYPE : 클래스, 인터페이스, enum 선언부  
ElementType.CONSTRUCTOR : 생성자 선언부  
ElementType.LOCAL_VARIABLE : 지역 변수 선언부  
ElementType.METHOD : 메소드 선언부  
ElementType.PACKAGE : 패키지 선언부  
ElementType.PARAMETER : 파라미터 선언부  
@Retention : 유지 방식은?  
RetentionPolicy.RUNTIME : 컴파일러에 의해 class 파일에 추가되고 런타임  시 VM에서 유지  
RetentionPolicy.SOURCE : 컴파일 시 class 파일에 추가되지 않는다.   
RetentionPolicy.CLASS : 클래스 안에 애노테이션이 추가되지만 런타임 시 VM에서는 사용되지 않는다.  
  
활용예제 -  
<pre><code>
public class MovieRecommender {

    @Autowired
    @Genre("Action")
    private MovieCatalog actionCatalog;

    private MovieCatalog comedyCatalog;

    @Autowired
    public void setComedyCatalog(@Genre("Comedy") MovieCatalog comedyCatalog) {
        this.comedyCatalog = comedyCatalog;
    }

    // ...
}
</code></pre>  
  
### @PostContruct and @PreDestory  
@PostContruct는 빈이 생성된 후 별도의 초기화 작업을 실행하는 메소드를 선언한다.  
@PreDestroy는 빈을 제거하기 전에 해야할 작업을 선언한다.  

