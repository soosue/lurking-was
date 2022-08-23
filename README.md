# 기타 정리

- 정규식 `\\s+` : 하나 이상의 공백을 의미
- 정규식 `\\s` : 하나의 공백을 의미


- 쿼리 스트링 : url 뒤에 붙는 데이터 전달 방법 - http://localhost:8080?name=soo


- `Version(HTTP_1_1, HTTP_2)`을 찾다가 우연히 `HttpClient` 추상클래스를 알게 되었다. 구현체로 `HttpClientImpl`과 `HttpClientFacade`가 존재 했는데, `Facade`가 뭘까 하고 봤더니 주석으로 "`HttpClientImpl`을 감싸고 있는 클래스다. 모든걸 위임하고 있다." 라고 적혀있었다. `Facade`클래스는 대상 클래스를 감싸고 주요 기능은 대상 클래스에게 위임하고 부가적인 기능을 추가한 클래스이다. `RequestFacade`같은 경우는 `parameter`의 `null`체크와 분기처리를 하고 있고, `HttpClientFacade`의 경우 `finally`로 `Reference.reachabilityFence(this);`를 호출하는 일만 추가되어 있다.


- `Java`에서 `IO`란 `Java program`에 들어오는 모든 데이터를 `Input`. `Java program`에서 나가는 모든 데이터를 `Output`이라고 한다. 즉, `java.io` 안의 클래스들은 `Java Program`에 들어오고 나가는 데이터에 대한 처리를 하는 클래스들이라고 생각하면 된다.
- `read()`라는 메소드는 `byte`를 읽어 들이고 `eof`일 경우 -1을 반환한다. 한 바이트씩 읽을 수 있다. 정수를 반환하고 정수의 4바이트 중에서 마지막 바이트에다 읽어들인 한 바이트를 저장한다. 바이트를 리턴하면 끝을 나타내는 값을 리턴할 수 없기 때문에 바이트가 아닌 `int`값을 리턴한다. 음수의 경우 맨 좌측 비트가 1이 되고 읽어들일 것이 있다면 항상 양수를 리턴한다.


- `SocketInputStream`에서 데이터를 읽는 방법. `Socket`에서 `InputStream`과 `OutputStream`을 가져올 수 있는데,


- `\r\n` : 윈도우에서 줄바꿈
- `\r` : mac 에서 줄바꿈
- `\n` : unix, 예전 mac 에서 줄바꿈


- http request와 http response에 구조(규칙)가 존재하는 것 같다.
  ```
    response 구조
    
    HTTP/버전 상태숫자 상태영어 - start line
    헤더                     - header
    헤더
            <-- 한줄 비워주기 header가 끝남을 의미. body와 구분을 위함
    바디                     - body
  
    HTTP/1.1 200 OK
    Content-Type: text/html;charset=utf-8
    Content-Length: 11
  
    Hello World
  ```
  ```
    request 구조
    
    Method uri HTTP/버전
    헤더
    헤더
            <-- 한 줄 비우기, body와 구분을 위함
    바디
  
    GET /users/index.html HTTP/1.1
    Connection: keep-alive
  ```


- request header referer 은 이전 페이지를 의미한다.

- request inputStream 을 BufferedReader.readLine()으로 읽는데, body 부분을 읽을 수가 없다. 왤까? body 마지막 부분에 cr(캐리지리턴)lf(라인피드)없다. 따라서 readLine할 때, 읽지 못한다. 그러므로 바디의 길이를 기억하고 있다가, 길이만큼 읽어야 한다. 버퍼리더는 맨 처음에 글자가 몇 개 있는지 기억하고 마지막 인덱스를 기억한다. 읽을 때 마지막 인덱스를 넘어가면 read가 되지 않고 buffer를 다시 채우는 작업을 하므로, 값을 읽을 수가 없게 된다.


- 301(Moved Permanently), 302(Found, Moved Temporarily), 303(See Other) 리다이렉션. status code를 301, 302, 303로 설정해놓고 Location 헤더를 등록해주면 해당 헤더값으로 리다이렉션이 된다. index.html(상대경로), /index.html(컨텍스트 경로(templates)에서 시작), http://localhost:8080/index.html 처럼 경로를 명시할 수 있다.


- response header에 `Set-Cookie: key=value`를 설정하면 client(브라우저)는 다음 요청부터 자동으로 request header에 `Cookie: key=value`를 추가하여 전송해준다.


- maxAge 같은 생명주기, Secure, HttpOnly 같은 옵션들은 Cookie헤더에 전송이 되지 않고 브라우저가 판단 후에 키밸류만 전송하게 되는것인가?
- cookie에 `; `와 같은 값이 들어갈 수 있는가?


- 하다보니 귀찮은 것들 : 응답 헤더 만들기(200, 302), 파일에서 byte[] 읽기, 응답 만들때 try catch, Logger 만들기, dos 계속 선언해주는것, `src/main/resources`경로 계속 명시해주는것,


- Logger 이야기 - 구현체를 선택하는 방법은 어떻게 되는가? 구현체 자체를 slf4j의 LoggerFactory에서 import 해서 스태틱 메서드로 사용을 하고 있음. 따라서 classpath에 StaticLoggerBinder라는 클래스가 존재하면 동작한다.(예를 들면 logback.jar에 해당 경로에 해당 클래스가 존재한다.) 
- ```
  configurations.all  {
    exclude group: 'ch.qos.logback', module: 'logback-classic'
  }
  ```
  이렇게 의존성을 제거하면 해당 클래스를 찾을 수 없다는 표시가 나온다. 하지만 컴파일은 이미 완료가 된 것이므로, 컴파일에러가 뜨지는 않고, StaticLoggerBinder를 호출하는 시점에 클래스로더가 해당 클래스파일을 찾을 때 찾지 못하는 예외가 발생하게 된다.
- spi라는 패키지의 인터페이스들을 위 클래스가 구현하고 있었다. java service program


- builder패턴은 한 번에 만들어서 응답하기에 좋다. 생성자로 했으면 보기 불편했을 것 같음.











spring try catch finally에서 resource 닫는 표준 방법 확인해보기
