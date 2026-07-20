# Week 3 — Spring REST with Spring Boot

This week was all about building REST APIs using Spring Boot. I started from scratch — setting up the project, understanding how Spring handles HTTP requests, and then writing endpoints that return real data. At the end I also wrote proper tests using MockMVC so I'm not manually hitting URLs in the browser every time.

---

## What's in this folder

Everything lives inside `spring-rest-handson/spring-learn`. It's a standard Spring Boot Maven project. Here's the layout:

```
Week_3/
└── spring-rest-handson/
    └── spring-learn/
        ├── pom.xml
        ├── src/main/java/com/cognizant/springlearn/
        │   ├── SpringLearnApplication.java        ← the main class
        │   ├── controller/
        │   │   ├── HelloController.java            ← GET /hello
        │   │   └── CountryController.java          ← GET /country, /countries, /countries/{code}
        │   ├── model/
        │   │   └── Country.java                    ← plain Java object (auto-converts to JSON)
        │   └── service/
        │       ├── CountryService.java             ← business logic
        │       └── exception/
        │           └── CountryNotFoundException.java   ← custom 404 exception
        └── src/main/resources/
            ├── application.properties             ← port config
            ├── date-format.xml                    ← Spring XML bean for SimpleDateFormat
            └── country.xml                        ← Spring XML beans for Country data
```

---

## Java version note

My system default Java is Java 8, but Spring Boot 3.2 needs Java 21. So I set it temporarily in the PowerShell session before running any `mvn` commands:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.11"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

java -version
# java version "21.0.11"
```

This resets every time I close the terminal, so I have to do it again each new session.

---

## How I ran it

```powershell
cd Week_3\spring-rest-handson\spring-learn

mvn clean package -DskipTests

mvn spring-boot:run
```

Tomcat comes up on **port 8083**. Once the Spring Boot banner shows up in the console, it's ready.

To run all the tests:
```powershell
mvn clean test
```

All 5 tests pass with zero failures.

---

## The hands-on exercises

### Hands-On 1 — Setting up the project + loading a Spring bean from XML

This one is mostly about getting the project created and understanding the structure. The interesting bit is the `displayDate()` method — it shows how Spring can manage objects for you through XML config instead of you manually creating them with `new`.

The idea: instead of doing `new SimpleDateFormat("dd/MM/yyyy")` everywhere in your code, you define it once as a Spring bean in `date-format.xml` and just ask Spring for it whenever you need it.

```xml
<!-- date-format.xml -->
<bean id="dateFormat" class="java.text.SimpleDateFormat">
    <constructor-arg value="dd/MM/yyyy"/>
</bean>
```

Then in the code:

```java
ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
Date date = format.parse("31/12/2018");
System.out.println("Parsed Date: " + date);
// prints → Mon Dec 31 00:00:00 IST 2018
```

The `main()` method also has start and end log statements — this is just to confirm it's actually executing when the app boots up.

One thing worth noting — `@SpringBootApplication` on the main class is doing a lot of heavy lifting behind the scenes. It's basically three annotations combined:
- `@Configuration` — this class can define beans
- `@EnableAutoConfiguration` — Spring Boot figures out what to configure automatically based on what's on the classpath
- `@ComponentScan` — scans the package for controllers, services, etc.

---

### Hands-On 2 — Hello World REST endpoint

First real REST endpoint. Nothing fancy, just returns a string.

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("START - sayHello()");
        String response = "Hello World!!";
        LOGGER.info("END - sayHello() : response = {}", response);
        return response;
    }
}
```

Hit `http://localhost:8083/hello` in your browser or Postman and you get back `Hello World!!`.

`@RestController` is just `@Controller + @ResponseBody` combined — it means whatever you return from the method gets written directly to the HTTP response. No view template, no redirect, just the value.

If you open the Network tab in Chrome DevTools (F12) while hitting this URL, you can see the actual HTTP request and response headers — the `Content-Type` will be `text/plain` since we're returning a plain string.

---

### Hands-On 2 — Country endpoint (returning JSON)

This is where it gets more interesting. Instead of returning a string, we return a Java object — and Spring automatically converts it to JSON.

```java
@RequestMapping("/country")
public Country getCountryIndia() {
    ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
    Country india = context.getBean("india", Country.class);
    return india;
}
```

The `Country` bean is defined in `country.xml`:

```xml
<bean id="india" class="com.cognizant.springlearn.model.Country">
    <property name="code" value="IN"/>
    <property name="name" value="India"/>
</bean>
```

Hit `http://localhost:8083/country` and you get:

```json
{
  "code": "IN",
  "name": "India"
}
```

The magic here is **Jackson** — it's a library that comes bundled with `spring-boot-starter-web`. When Spring sees that the return type is an object (not a String), and Jackson is on the classpath, it automatically serializes it to JSON and sets the response `Content-Type` to `application/json`. You don't write a single line of serialization code.

---

### Hands-On 2 — Get all countries

Returns a list of all 4 countries. The list itself is also defined as a Spring bean in `country.xml`:

```xml
<bean id="countryList" class="java.util.ArrayList">
    <constructor-arg>
        <list>
            <ref bean="india"/>
            <ref bean="us"/>
            <ref bean="japan"/>
            <ref bean="germany"/>
        </list>
    </constructor-arg>
</bean>
```

Endpoint: `http://localhost:8083/countries`

```json
[
  { "code": "IN", "name": "India" },
  { "code": "US", "name": "United States" },
  { "code": "JP", "name": "Japan" },
  { "code": "DE", "name": "Germany" }
]
```

When you return a `List<Country>` from a controller method, Jackson turns it into a JSON array automatically. Same magic, different shape.

---

### Hands-On 2 — Get a specific country by code

Here we use a path variable to accept a country code in the URL and look it up.

```java
@GetMapping("/countries/{code}")
public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
    return countryService.getCountry(code);
}
```

The actual lookup happens in `CountryService` using a lambda:

```java
Country result = countryList.stream()
    .filter(country -> country.getCode().equalsIgnoreCase(code))
    .findFirst()
    .orElseThrow(() -> new CountryNotFoundException("Country not found for code: " + code));
```

The `equalsIgnoreCase` makes it case-insensitive, so `/countries/in`, `/countries/IN`, and `/countries/In` all return India. Try it — it works.

---

### Hands-On 2 — What happens when the country doesn't exist

If someone hits `/countries/az` (which doesn't exist), instead of crashing or returning null, we throw a custom exception:

```java
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends Exception {
    // that's literally all it needs
}
```

The `@ResponseStatus` annotation tells Spring — "if this exception gets thrown from a controller method, respond with a 404 and include this reason message". No extra error handling code needed anywhere.

You can test this with curl:
```bash
curl -i http://localhost:8083/countries/az
```

Response:
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Country not found",
  "path": "/countries/az"
}
```

---

### Hands-On 2 — MockMVC Tests

Instead of manually testing endpoints in the browser every time, we write automated tests using MockMVC. It simulates HTTP requests without actually starting a server — so tests run fast.

```java
@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {
        // just checking Spring wired the controller correctly
        assertNotNull(countryController);
    }

    @Test
    public void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/country"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    public void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/az"));
        actions.andExpect(status().isNotFound());
        actions.andExpect(status().reason("Country not found"));
    }
}
```

The `jsonPath("$.code")` syntax is JSONPath — `$` means the root of the JSON object, `.code` means the `code` field. For arrays it's `$[0].code` for the first element.

There are 5 tests total and all pass. Run them with:
```powershell
mvn clean test
```

---

## All the endpoints at a glance

| URL | What it does |
|-----|-------------|
| `GET /hello` | Returns `Hello World!!` |
| `GET /country` | Returns India's details |
| `GET /countries` | Returns all 4 countries |
| `GET /countries/in` | Returns India (works with any case) |
| `GET /countries/us` | Returns United States |
| `GET /countries/az` | Returns 404 — country not found |

Server runs on `http://localhost:8083`

---

## Things I ran into

- Port 8083 was already set in `application.properties` — changed it there when it conflicted with something else running locally
- The XML files (`date-format.xml`, `country.xml`) need to be in `src/main/resources` — that's how `ClassPathXmlApplicationContext` finds them on the classpath
- `JAVA_HOME` resets every new PowerShell session, so had to set it again each time
- `mvn clean test` also triggers a fresh compile, so no need to build separately before running tests
