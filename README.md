# 기타 정리

- 정규식 `\\s+` : 하나 이상의 공백을 의미
- 정규식 `\\s` : 하나의 공백을 의미


- 쿼리 스트링 : url 뒤에 붙는 데이터 전달 방법 - http://localhost:8080?name=soo


- `Version(HTTP_1_1, HTTP_2)`을 찾다가 우연히 `HttpClient` 추상클래스를 알게 되었다. 구현체로 `HttpClientImpl`과 `HttpClientFacade`가 존재 했는데, `Facade`가 뭘까 하고 봤더니 주석으로 "`HttpClientImpl`을 감싸고 있는 클래스다. 모든걸 위임하고 있다." 라고 적혀있었다. `Facade`클래스는 대상 클래스를 감싸고 주요 기능은 대상 클래스에게 위임하고 부가적인 기능을 추가한 클래스이다. `RequestFacade`같은 경우는 `parameter`의 `null`체크와 분기처리를 하고 있고, `HttpClientFacade`의 경우 `finally`로 `Reference.reachabilityFence(this);`를 호출하는 일만 추가되어 있다.

