# 만들면서 배우는 스프링
[Next Step - 과정 소개](https://edu.nextstep.camp/c/4YUvqn9V)

## @MVC 구현하기

### 학습목표
- @MVC를 구현하면서 MVC 구조와 MVC의 각 역할을 이해한다.
- 새로운 기술을 점진적으로 적용하는 방법을 학습한다.

### 시작 가이드
1. 미션을 시작하기 전에 학습 테스트를 먼저 진행합니다.
    - [Junit3TestRunner](study/src/test/java/reflection/Junit3TestRunner.java)
    - [Junit4TestRunner](study/src/test/java/reflection/Junit4TestRunner.java)
    - [ReflectionTest](study/src/test/java/reflection/ReflectionTest.java)
    - [ReflectionsTest](study/src/test/java/reflection/ReflectionsTest.java)
    - 나머지 학습 테스트는 강의 시간에 풀어봅시다.
2. 학습 테스트를 완료하면 LMS의 1단계 미션부터 진행합니다.

## 학습 테스트
1. [Reflection API](study/src/test/java/reflection)
2. [Servlet](study/src/test/java/servlet)

--- 

# TODO

### 1단계
기능 요구 사항
1. - [X] @MVC Framework 테스트 통과하기
      - [X] AnnotationHandlerMapping#initialize 가 호출되면 @RequestMapping 정보를 참고해 맵핑 정보를 초기화한다
      - [X] AnnotationHandlerMapping#handler 를 호출하면 @RequestMapping 에 맵핑된 handleExecution 을 반환한다

2. - [ ] JspView 클래스를 구현한다.
  
- DispatcherServlet 클래스의 service 메서드에서 어떤 부분이 뷰에 대한 처리를 하고 있는지 파악해서 JspView 클래스로 옮겨보자.

