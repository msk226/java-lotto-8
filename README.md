# 로또

## 1. 구현 의도 및 설계 방향

1. Enum 활용

- `Rank` Enum: 각 등수별 당첨 조건과 상금을 명확히 정의하여, 당첨 판정 로직의 가독성 및 유지보수성 향상

2. 테스트를 용이하게 하기 위한 설계

- 정책 인터페이스 도입: `NumberGenerator` 인터페이스를 통해 번호 생성 전략을 분리하여, 테스트 시 고정된 번호를 반환하는 Mock 구현체 사용 가능
- 의존성 주입: `LottoMachine`이 `NumberGenerator`를 생성자 주입받아, 다양한 번호 생성 전략을 유연하게 적용 가능
- 이를 통해 단위 테스트 뿐 만 아니라 통합 테스트도 수월하게 수행할 수 있도록 설계

3. 상수화

- 상수 클래스 활용: `LottoConstants` 클래스를 통해 반복적으로 사용되는 상수들을 한 곳에 모아 관리하여 코드의 가독성 및 유지보수성 향상
- 상수 클래스 분리 기준 : 해당 상수를 다른 곳에서도 재사용하는 경우가 많을 것으로 예상되는 경우 별도의 상수 클래스로 분리
- 도메인 내 상수 : 특정 도메인에만 국한되어 사용되는 상수는 해당 도메인 클래스 내에 정의

4. 예외 처리 및 재시도

- 사용자 입력 예외 처리: 입력 검증 실패 시 명확한 예외 메시지를 제공하여 사용자가 문제를 쉽게 인지하고 수정할 수 있도록 함
- 재시도 관련 로직을 적절히 메서드화 하여 코드 중복 최소화 및 가독성 향상

5. 메서드는 하나의 기능에 집중

- 단일 책임 원칙 준수: 각 메서드는 하나의 기능에 집중하도록 설계하여 코드의 가독성 및 유지보수성 향상
- 예: 입력 처리, 검증, 로또 생성, 당첨 판정, 결과 집계 등 각 기능별로 메서드 분리

---

## 2. 코딩 컨벤션

- 클래스 및 메서드 명명: 직관적이고 명확한 이름 사용 (예: `Lotto`, `LottoMachine`, `InputReader`)
- 접근 제어자: 불필요한 공개를 피하기 위해 적절한 접근 제어자 사용 (`private`, `protected`, `public`)
- 상수 사용: 매직 넘버 대신 의미 있는 상수로 대체 (예: `MAX_NAME_LENGTH`, `MOVE_THRESHOLD`)
- 클래스는 상수, 멤버 변수, 생성자, 메서드 순으로 작성
- 주석 작성: 불필요한 주석은 지양
- 코드 포맷팅: 일관된 들여쓰기 및 공백 사용
    - 각 메서드 사이에 한 줄 공백 추가
    - 파일 하단에 공백 한 칸 유지
- 의미 없는 공백 라인 제거
- 불필요한 import 제거
- 예외 처리: 명확한 예외 메시지 제공 및 적절한 예외 타입 사용

## 3. 구현 요구사항 체크리스트

### 1. 입력

- [x] `구입금액을 입력해 주세요.` 문구 출력 후 금액 입력받기
- [x] 금액을 기반으로 구매 개수 계산 (`금액 / 1000`)
- [x] `X개를 구매했습니다.` 문구 출력
- [x] 생성된 각 로또의 번호를 `[1, 2, 3, 4, 5, 6]` 형태로 출력
- [x] `당첨 번호를 입력해 주세요.` 문구 출력 후 쉼표로 구분된 번호 입력
- [x] `보너스 번호를 입력해 주세요.` 문구 출력 후 입력받기

---

### 2. 입력 검증

- [x] 금액이 비어있거나 숫자가 아닐 경우 예외 발생
- [x] 금액이 1000원 단위가 아닐 경우 예외 발생
- [x] 당첨 번호 입력이 비어있거나 숫자 이외가 섞여 있으면 예외 발생
- [x] 당첨 번호가 6개가 아니면 예외 발생
- [x] 당첨 번호에 중복이 있으면 예외 발생
- [x] 번호가 1~45 범위를 벗어나면 예외 발생
- [x] 보너스 번호가 당첨 번호와 중복되면 예외 발생

---

### 3. 로또 생성 (자동)

- [x] 번호 생성 전략 인터페이스 `NumberGenerator` 정의
- [x] 실제 구현체 `RandomNumberGenerator`:
    - [x] 1~45 범위에서 중복 없이 6개 번호 생성
    - [x] 번호는 오름차순 정렬
- [x] `LottoMachine`을 통해 자동으로 N장 발행
- [x] `Lotto` 생성 시 번호 개수(6개) 검증 수행

---

### 4. 당첨 판정

- [x] `WinningLotto` 클래스에서 당첨 번호(6개)와 보너스 번호 관리
- [x] `Lotto` 한 장에 대해 일치 개수 및 보너스 일치 여부 계산
- [x] `Rank`를 통해 등수 판정:
    - [x] 6개 일치 → 1등 (2,000,000,000원)
    - [x] 5개 + 보너스 → 2등 (30,000,000원)
    - [x] 5개 일치 → 3등 (1,500,000원)
    - [x] 4개 일치 → 4등 (50,000원)
    - [x] 3개 일치 → 5등 (5,000원)
    - [x] 나머지 → 꽝

---

### 5. 결과 집계 및 수익률

- [x] 모든 로또 티켓의 `Rank`를 집계하여 등수별 개수 구하기
- [x] 총 상금 합계 계산
- [x] 수익률(%) = `(총 상금 / 구입 금액) * 100` 계산

---

### 6. 예외 처리

- [x] 예외 발생 시 `[ERROR] 메시지` 형식으로 출력
- [x] 프로그램은 예외 메시지를 출력 후 종료
- [x] 모든 예외 메시지는 요구사항 문구와 정확히 일치해야 함

--- 

## 4. 프로젝트 구조

```
└── src
    ├── main
    │   └── java
    │       └── lotto
    │           ├── Application.java
    │           ├── app
    │           │   └── LottoProcessor.java
    │           ├── common
    │           │   ├── constants
    │           │   │   └── LottoConstants.java
    │           │   └── exception
    │           │       └── ExceptionMessage.java
    │           ├── domain
    │           │   ├── Lotto.java
    │           │   ├── LottoMachine.java
    │           │   ├── LottoResult.java
    │           │   ├── Money.java
    │           │   ├── WinningLotto.java
    │           │   └── enums
    │           │       └── Rank.java
    │           ├── io
    │           │   ├── input
    │           │   │   ├── ConsoleInputReader.java
    │           │   │   ├── InputReader.java
    │           │   │   └── LottoParser.java
    │           │   └── output
    │           │       ├── ConsoleOutputPresenter.java
    │           │       └── OutputPresenter.java
    │           └── policy
    │               ├── NumberGenerator.java
    │               └── random
    │                   └── RandomNumberGenerator.java
    └── test
        └── java
            └── lotto
                ├── ApplicationTest.java
                ├── app
                │   └── LottoProcessorTest.java
                ├── domain
                │   ├── LottoMachineTest.java
                │   ├── LottoResultTest.java
                │   ├── LottoTest.java
                │   ├── MoneyTest.java
                │   ├── WinningLottoTest.java
                │   └── enums
                │       └── RankTest.java
                ├── io
                │   ├── FakeInputReader.java
                │   └── FakeOutputPresenter.java
                └── policy
                    ├── fake
                    │   └── FixedNumberGenerator.java
                    └── random
                        └── RandomNumberGeneratorTest.java

```