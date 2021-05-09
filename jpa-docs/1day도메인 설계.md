# Entity 설계

---

## 🖍 기능 목록

- 회원 기능
    - 회원 등록
    - 회원 조회
- 상품 기능
    - 상품 등록
    - 상품 조회
    - 상품 수정
- 주문 기능
    - 상품 주문
    - 주문 내역 조회
    - 주문 취소

➕ (상품 재고관리, 상품 종류 도서, 음반, 영화, 상품 카테고리로 분류 가능)

---

## 🖍 테이블 설계

![](https://images.velog.io/images/donglee99/post/7ce0b247-9484-48c4-887a-49fa23fbc9fb/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-09%20%EC%98%A4%ED%9B%84%204.56.30.png)

- 다대다는 사용을 지양하라. (일대다, 다대일 형태로 변환 해서 설계)
- 양방향 매핑을 지양하라.
- 연관관계 매핑 시 외래키가 있는 부분이 해당 연관관계의 주인이 된다. ( 연관관계의 주인 단순히 외래키를 누가 관리 하냐의 문제일 뿐 비즈니스 상 의미가 X, 추가적으로 쿼리 발생 O, 유지보수 힘듬)

---

## 🖍 연관 관계 매핑

![](https://images.velog.io/images/donglee99/post/7e3bc8cd-0fd0-496b-b0f3-421a99f7a750/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-09%20%EC%98%A4%ED%9B%84%205.12.31.png)
![](https://images.velog.io/images/donglee99/post/d24a6e4b-4ae3-4c51-8552-48c518f6dee1/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-09%20%EC%98%A4%ED%9B%84%205.15.55.png)
- Order와 Member의 다대일 연관관계를 표현시 외래키를 가지고 있는 Order가 연관관계의 주인이 된다.
- 매핑시 JoinColumnd의 위치와 MappedBy의 위치를 조심하자.

☝️ 연관관계의 주인을 잘못 매핑할 경우 Member를 수정했는데 Order테이블에서 쿼리가 나갈 수 있다.

---

## 🖍 임베디드 타입

![](https://images.velog.io/images/donglee99/post/428602da-c3c2-4fa9-a4cf-af5d852bcb74/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-09%20%EC%98%A4%ED%9B%84%205.46.51.png)

- 임베디드 타입을 사용하는 이유는 여러곳에서 사용될수 있는 멤버를 묶어 하나로 간단하게 표시할수 있게 도와주어 코드의 중복을 줄이고 간결성을 높이기 위해서이다.
- 임베디드 타입을 선언하는 방법은 @Embeddable 어노테이션을 붙여주는 방법이 있다. 사용시에는 @Embedded 를 붙여 사용하면 되며 @Embeddable 이 있다면 @Embedded는 생략이 가능하다.

---

## 🖍 상속 관계 표현하기

![](https://images.velog.io/images/donglee99/post/9a2a441d-187d-49f4-9fad-c11c9de22b8e/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-09%20%EC%98%A4%ED%9B%84%205.32.06.png)![](https://images.velog.io/images/donglee99/post/70b71722-bd23-4186-ae1e-e67bc415106b/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-09%20%EC%98%A4%ED%9B%84%205.32.18.png)

- 상속 관계를 JPA에서 표현 하기위해서는 @Inheritance(strategy = InheritanceType.원하는 타입) 을 적어주면 되는데 이때 ( Joined, Single_Table, Table_Per_Class) 방식이 있다. 이번 예제에서 사용하는 Single_Table전략은 상속관계로 표현 되어있지만 DB 조회시 하나의 테이블에 모든 상속된 값이 들어가있는것처럼 표현된다.

![](https://images.velog.io/images/donglee99/post/ba176b94-1c71-40f7-b446-f9e7145de8fb/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-05-09%20%EC%98%A4%ED%9B%84%205.47.25.png)

---


## ➕ EnumType 사용시 주의점

* Enum Type 을 사용시 Enumerated(EnumType.STRING  or ORDINAL ) 을 사용하게 된다.

* 이때 String은 값 자체를 DB에 저장하는 반면 ORDINAL은 Sequence  로 변환해 DB에 들어가되는데 이는 중간에 다른 값이 추가 됬을경우 Sequece 로 인한 오류가 발생할 가능성이 매우높다.