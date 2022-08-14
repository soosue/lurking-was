### Step1: TDD 실습

- [ ] 요구사항 1 - GET 요청
    - HTTP GET 요청에 대한 RequestLine을 파싱한다.
    - 파싱하는 로직 구현을 TDD로 구현한다.
    - 예를 들어 "GET /users HTTP/1.1"을 파싱하면 다음과 같은 결과를 얻을 수 있어야 한다.
        - method는 GET
        - path는 /users
        - protocol은 HTTP
        - version은 1.1
- [ ] 요구사항 2 - POST 요청
    - HTTP POST 요청에 대한 RequestLine을 파싱한다.
    - 파싱하는 로직 구현을 TDD로 구현한다.
    - 예를 들어 "POST /users HTTP/1.1"을 파싱하면 다음과 같은 결과를 얻을 수 있어야 한다.
        - method는 POST
        - path는 /users
        - protocol은 HTTP
        - version은 1.1
- [ ] 요구사항 3 - Query String 파싱
    - HTTP 요청(request)의 Query String으로 전달되는 데이터를 파싱한다.
    - 클라이언트에서 서버로 전달되는 데이터의 구조는 name1=value1&name2=value2와 같은 구조로 전달된다.
    - 파싱하는 로직 구현을 TDD로 구현한다.
- [ ] 요구사항 4 - enum 적용(선택)
    - HTTP method인 GET, POST를 enum으로 구현한다.