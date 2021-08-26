서비스 시나리오
배달의 민족 커버하기 - https://1sung.tistory.com/106

기능적 요구사항

고객이 메뉴를 선택하여 주문한다
고객이 결제한다
주문이 되면 주문 내역이 입점상점주인에게 전달된다
상점주인이 확인하여 요리해서 배달 출발한다
고객이 주문을 취소할 수 있다
주문이 취소되면 배달이 취소된다
고객이 주문상태를 중간중간 조회한다
주문상태가 바뀔 때 마다 카톡으로 알림을 보낸다
비기능적 요구사항

트랜잭션
결제가 되지 않은 주문건은 아예 거래가 성립되지 않아야 한다 Sync 호출
장애격리
상점관리 기능이 수행되지 않더라도 주문은 365일 24시간 받을 수 있어야 한다 Async (event-driven), Eventual Consistency
결제시스템이 과중되면 사용자를 잠시동안 받지 않고 결제를 잠시후에 하도록 유도한다 Circuit breaker, fallback
성능
고객이 자주 상점관리에서 확인할 수 있는 배달상태를 주문시스템(프론트엔드)에서 확인할 수 있어야 한다 CQRS
배달상태가 바뀔때마다 카톡 등으로 알림을 줄 수 있어야 한다 Event driven



```yaml
server:
  port: 8088

---

spring:
  profiles: default
  cloud:
    gateway:
      routes:
        - id: Contract
          uri: http://localhost:8081
          predicates:
            - Path=/contracts/** 
        - id: Pay
          uri: http://localhost:8082
          predicates:
            - Path=/pays/** 
        - id: Reservation
          uri: http://localhost:8083
          predicates:
            - Path=/reservations/** 
        - id: Mypage
          uri: http://localhost:8084
          predicates:
            - Path= /myPages/**
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins:
              - "*"
            allowedMethods:
              - "*"
            allowedHeaders:
              - "*"
            allowCredentials: true


---

spring:
  profiles: docker
  cloud:
    gateway:
      routes:
        - id: Contract
          uri: http://Contract:8080
          predicates:
            - Path=/contracts/** 
        - id: Pay
          uri: http://Pay:8080
          predicates:
            - Path=/pays/** 
        - id: Reservation
          uri: http://Reservation:8080
          predicates:
            - Path=/reservations/** 
        - id: Mypage
          uri: http://Mypage:8080
          predicates:
            - Path= /myPages/**
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins:
              - "*"
            allowedMethods:
              - "*"
            allowedHeaders:
              - "*"
            allowCredentials: true

server:
  port: 8080
