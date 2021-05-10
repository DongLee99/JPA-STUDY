---

# 회원 도메인 개발

## 애플리케이션 아키텍쳐

![](https://images.velog.io/images/donglee99/post/cd2d0701-2e8d-4588-aa4e-e979c46c5a9e/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-10%20%EC%98%A4%ED%9B%84%201.09.52.png)

- Controller web : 웹 계층
- Service : 비즈니스 로직, 트랜잭션 처리
- Repository : JPA 직접 사용, EM 사용
- Domain : 엔티티가 모여 있는곳

☝️위 처럼 정해저 있다고는 하나 너무 딱딱하게 코드를 짜게 될시 비효율적일수 있음.

---

## MemberService


![](https://images.velog.io/images/donglee99/post/e14948f0-889f-495b-99bf-6d56fdf4ec36/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-10%20%EC%98%A4%ED%9B%84%209.21.32.png)
- 멤버 서비스의 요구 사항에는 회원가입과, 회원 조회가 있었는데 이때 Join은 회원 가입을 의미한다.
- 회원 가입은 중복된 이름의 멤버를 막는 검증후 memberRepository의 save 를 사용해 회원을 등록하게 된다.
- 이때 영속성 컨텍스트에 member 가 들어가게 되는데 자동으로 pk값이 id가 생성이 되고 return 을 통해 반환을 해주게 된다.

🖍 @AutoWired 

- AutoWired 어노테이션은 사용시 스프링이 스프링 빈에 등록된 값을 인젝션 해주는데 만약 필드값이 하나일 경우 생략이 가능하다.
- 하지만 테스트 코드를 이용한 테스트시 중간에 값 변경이 불가능 하여 어려움을 겪는다.
- 이때 생성자를 만들어 Setter 를 사용하면 이를 해결 할수 있지만 스프링 부트에서는 롬복을 이용한 RequiredArgumentConstrutor 이라는 어노테이션을 사용하면 final로 선언된 필드의 경우 자동으로 생성자를 통한 Setter를 생성해주게 되어 번거로움을 피하게 도와준다.

🖍 @Transcational

- JPA 의 모든 데이터 변경은 한 Transaction 안에서 이루어 져야하는데 이 때문에 있는 어노테이션이다.
- 읽기만을 위한경우 옵션으로 readOnly를 주면 성능향상이 가능하다.