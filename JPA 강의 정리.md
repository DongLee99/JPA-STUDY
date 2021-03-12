# JPA-STUDY
# JPA 프로그래밍을 강좌를 들으며 혼자 정리하기

---
## JPA 소개
>JPA 란?

** 객체지향 언어를 쓰는 환경에서 관계형 데이터 베이스에 객체를 매핑하는것은 매우 복잡하고 귀찮은 일이다. **
**
이에 기존 관계형 데이터 베이스를 기반으로 하는 쿼리를 작성해서 코딩을 하게 되면 객체와 테이블을 올바르게 설계, 매핑 하는데 어려움을 느끼게 되며 이에 따른 오류가 생기기 마련이다. **
**
이름 해결하기 위해 JPA 를 사용하게 되고 간단하게 생각하면 JPA는 Java Application 과 관계형 DB 사이에 중간 단계를 하며 객체를 분석하고 쿼리문을 생성해주고 패러다임의 불일치를 해결해준다. 
**

---
> JPA가 실무에서 어려운 이유

** JPA 가 실무에서 사용하기 힘들어하는 이유는 2가지의 이유가 있다고 하셨다. **
	
    1. 객체와 테이블을 옳바르게 설계, 매핑을 하지 못해서
    2. JPA 의 내부 동작 방식을 이해하지 못해서

---
> 객체와 관계형DB의 차이점

* 객체
	
    * ( 속성 + 기능 ) 캡슐화
    * 추상화, 은닉, 캡슐화, 상속, 다형성
    
* 관계형DB
	
    * 관계형 DB 로 객체지향 언어의 객체의 관계를 표현하는것은 상당히 까다롭다.
    * 상속이란 개념이 X, 객체의 참조 -> JOIN,PK,FK
    -> 객체를 관계형 DB에 저장 과정
    	
        객체 -> SQL 문으로 변환 -> 관계형DB
        이때 객체 -> SQL 문으로 변환이 매우 까다롭다.
---
> 패러다임의 불일치

1. 상속
2. 연관
3. 객체 그래프 탐색

# Hello.jpa

## 데이터 베이스의 방언

- 데이터 베이스의 방언이란 -> 각 데이터 베이스 만의 고유 언어? 의 개념과 비슷하다 
```ex) Mysql, Oracle 등의 VARCHAR / VARCHAR2 이런식의 차이```
- ```JPA``` => 데이터 베이스의 방언을 극복하게 해줌 이로써 데이터 베이스 환경을 옮길시 개발자의 수고가 줄어들게된다.
( jpa 가 특정 DB에 종속되지 않는다는게 이에 해당하게된다.)

---

## JPA 의 구동 방식
* ```JPA 구동 방식``` 
![](https://images.velog.io/images/donglee99/post/15b47daf-a129-49d2-877b-79d1c22de121/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-03-03%20%EC%98%A4%ED%9B%84%2012.21.31.png)
*  persistence.xml 의 설정 파일을 조회 하고 persistence 클래스와 Entity ManagerFactory 클래스를 생성후 필요할때마다 Entity Manager 를 생성해 사용한다.
    💁 Entity Manager Factory
    * 하나만 생성
    * 애플리케이션 전체에서 공유
    * Entity Manager 는 쓰레드간 공유 X (사용후 버려라)
    * JPA 의 모든 데이터 변경은 한 트랜잭션 안에서만 실행 (간혹 종종 간단한 변경은 트랜잭션 밖에서도 가능한데 이는 DB가 알아서 처리하는 것임)
  * JPA 사용시 Find 후 수정한 객체를 따로 UPDATE 하지 않아도 Commit시 알아서 UPDATE 된다는 편리함이 있다.
  
 ---
  
## JPQL
* ```JPA는 SQL 을 추상화만 하고 JPQL 이라는 객체 지향 쿼리를 제공한다```

---
## JPA에서 가장 중요한것
1. 객체와 관계형 DB의 매핑
2. 영속성 컨텍스트 ( 실제 DB가 내부에서 어떻게 동작하냐)
  
---

# 엔티티 매핑

## 플래시 (flush())

* 영속성 컨텍스트의 변경 내용을 데이터 베이스에 반영한다.
 => 간단하게 영속성 컨텍스트의 내용을 DB에 넣는다. (같은말 이네..) 


* 특징
  
  1. Dirty Checking (변경 감지)
    2. 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
    3. 쓰기 지연 SQL 저장소의 쿼리를 DB에 전송

💁 플러시를 해도 1차 캐시가 유지됨. 
💁 따라서 플러시는 영속성 컨텍스트를 비우는게 X (쓰기 지연 SQL 의 내용의 DB에 쿼리로 보내는것)

---

## 준영속 상태
* 간단하게 영속을 해제 
	
    1. em.detach(entity) 해당 엔티티만 영속 컨텍스트에서 분리
    2. em.clear() 영속 컨텍스트를 비워줌
    3. em.close() 영속 컨텍스트 종료
    
---

## 객체와 테이블 매핑
☝️ ```@Entity``` -> JPA 가 클래스를 관리하도록 지정 (필수)
	
   ** 주의 사항 ** 
	
    1. 기본 생성자는 필수
	2. final, Enum, Interface, inner 클래스 사용 X
    3. 저장할 필드에 final X
    
☝️ ```@Table``` -> 엔티티와 매핑할 테이블 조정
	
   ** 속성 ** 
	
    1. name
	2. catalog
    3. schema
    4. uniqueConst
   		
---
   
   ## 데이터 베이스 스키마 자동생성
   
   * ```hibernate.ddl.auto```의 속성
   value
   	
    1. create
    * Table drop 후 생성
    2. create-drop
    * Table 생성후 종료시 drop
    3. update
    * 기존에 Table에 추가
    4. validated
    * validate (엔티티나 테이블이 항상 매핑이 되어있는지 확인)
    5. none 
    * 아무것도 X
 * 운영중인 장치에 절대 사용하면 X
 
---

## 어노테이션 정리

* ```Column``` 컬럼 매핑
* ```Temporal``` 날짜 타입 매핑
* ```Enumerate``` enum 타입 매핑
* ```Lo``` Blob, Clob 매핑
* ```Transien``` 특정 필드를 컬럼에 매핑X -> DB에 매핑 X

** Column ** 
* 속성
	
    * ```name``` 필드와 매핑할 테이블의 이름 지정
    * ```insertable```, ```updatable``` 등록, 변경 가능 여부
    * ```nullable``` null 값의 허용 여부 결정
    * ```unique``` @Table의 UniqueConstraints와 같지만 한 컬럼에 간단하게 Unique 제약을 걸때 사용한다
    * ```ColumnDefinition``` 데이터 베이스의 컬럼 정보를 정확히 줄 수 있다.
    * ```length``` 문자 길이
    * ```precision.scale```
    
** Enumerated **
* 속성
	
    * ```EnumType.ORDINARY``` enum의 순서를 DB에 저장
    * ```EnumType.STRING``` enum의 이름을 DB에 저장
    💁 ORDINARY 는 사용하면 안된다. enum의 순서를 기록해 나중에 데이터의 오류가 생길수 있다.
    
---
## 기본키 매핑
* 사용 어노테이션 ```@ID```, ```@GeneratedValue```
	
    * 직접 할당 ```@ID``` 만 사용
    * 자동 할당 ```GeneratedValue```
    	* GeneratedValue 
        INDENTITY
        SEQUENCE
        * Table 전략
        
* 권장하는 식별자 전략
	
    * 기본키 제약 조건: Null이면 X, 유일, 변하면 안됨
    🖍 권장 Long + 대체키 + 키 생성 전략
    
☝️ 영속성 컨텍스트에는 PK값이 무조건 필요하다 근데 IDENTITY 는 DB에 들어가야만 null 값이 들어가야PK값이 지정된다 -> 이때만 persist시 바로 쿼리문 작동
# 연관관계 매핑 기초

## 객체를 테이블에 맞춰 모델링

```java
Team team = new Team();
team.setName("TeamA");
em.persist(team);

Member member = new Member();
member.setName("member1");
member.setTeam(team.getId());

--> 객체지향적이라 할 수 없음
```
---

## 객체를 테이블에 맞추면 데이터 중심 모델링시 협력관계 불가능
* 테이블은 외래키로 조인을 통해 연관테이블을 찾는다.
* 객체는 참조를 사용해 연관테이블을 찾는다.

-> 테이블과 객체의 차이


**양방향 연관관계와 연관관계의 주인**

* 처음 Member -> Team 가능
	Team -> Member 불가능
    
* 객체지향에 맞게 코드를 수정하여도 실제적으로 테이블에 영향은 X
* 연관관계가 2개 => 양방향 연관관계
( 테이블의 경우 외래키 하나로만 테이블 연관관계 관리 가능)

**연관관계의 주인**

* 객체의 두 관계중 하나를 연관관계의 주인으로 지정
* 연관관계의 주인만이 외래키를 관리 (등록, 수정)
* 주인이 아닌쪽은 조회만 가능하다

**그렇다면 누구를 주인으로?**
* 외래키가 있는 곳을 주인으로 정한다

---

## 양방향 연관관계의 주의점
* 순수 객체 상태를 고려해 항상 양쪽에 값 설정
* 연관관계 편의 메소드 ( ```Member의 Team 셋팅시 Team.getMember.add();``` 를 해줘야함
* 무한 루프 조심


