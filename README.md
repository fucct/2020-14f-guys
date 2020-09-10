# PELOTON 프로젝트
[![License: MIT](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)
![Language: Java](https://img.shields.io/github/languages/top/woowacourse-teams/2020-14f-guys)
![issue:close](https://img.shields.io/github/issues-closed/woowacourse-teams/2020-14f-guys?color=red)
![pr:close](https://img.shields.io/github/issues-pr-closed/woowacourse-teams/2020-14f-guys?color=blueviolet)


![https://user-images.githubusercontent.com/49060374/91448157-8026b880-e8b4-11ea-9273-1c77fe0a09b2.png](https://user-images.githubusercontent.com/49060374/91448157-8026b880-e8b4-11ea-9273-1c77fe0a09b2.png)

## 소개

- 목표 달성을 위한 동기부여 서비스
    - 목표를 함께 달성할 Peloton을 모집할 수 있습니다.
    - 성취도에 따라서 모인 금액을 차등 지급합니다.
    - 그룹별, 주제별 랭킹 시스템을 도입하여 동기와 소속감을 부여합니다.

- API 문서 : [링크](https://dev.peloton.ga/)
- Android : 구글 플레이 스토어에 `펠로톤` 이라고 검색하시면 찾을 수 있습니다.

## 사용자 스토리

- 데일리
    - 제이슨네 데일리는 매일 지각자가 나왔다. 그래서 펠로톤을 사용해 아침 10시까지 데일리에 참석하지 않을 경우 지각으로 간주하기로 했다. 보스독을 비롯한 10명의 크루들은 만원을 입금하고, 펠로톤 기간을 2주로 정했다. 2주의 펠로톤이 끝난 뒤, 성취도(지각 비율)에 따라 크루들은 돈을 분배받았다. 지각을 제일 많이한 보스독은 가장 적은 돈을 가져갔고, 지각을 한번도 하지 않은 터틀은 가장 많은 돈을 가져갔다.
- 스터디
    - 카일은 대학교 커뮤니티에서 익명의 사람들과 기상 스터디를 하기로 했다. 아침에 일어난 걸 증명하기 위해 펠로톤 서비스에 그룹을 등록한다. 카일은 3만원을 입금하고, 매일 아침 펠로톤 서비스에 6시에 기상하여 인증 사진을 등록한다. 정상 출근율이 30%인 호돌이는 입금 금액에 30%만 돌려받고, 나머지 회원들도 정상 출근율에 비례하여 금액을 돌려받는다. 남는 금액에 대해서 성취도가 100%인 스터디원들에게 상금으로 분배된다.
- 개인
    - 디디는 좋은 개발자가 되기위해 1일 1커밋을 다짐했다. 이와 동시에 펠로톤에서 제공하는 1일1커밋 그룹에 만원을 내고 참여하여 한달동안 1일 1커밋을 완성했다. 약 500명의 사람들이 참여했는데, 이 중 성공하지 못한 사람의 돈 일부가 성공한 사람들에게 입금되었다. 디디는 한달간 1일 1커밋도 지켰을 뿐 아니라 상금도 받게되어 기분이 좋다.  ***⇒ 선입금 및 미션 완료 시점에 돌려받는 형태***

## 사용 기술

### Back-end

- Java8
- Spring boot
- Spring Data Jdbc
- Liquibase
- Maria DB / H2

### Front-end

- Javascript es6
- React Native with Expo
- Recoil

### Infra

- AWS EC2, S3
- Docker
- Log4j2
- Jenkins
- Nginx

### Tools

- Intellij
- Mac os
- Git
- Linux Ubuntu

## 사용 예시

![peloton](image/peloton.gif)

## 기능 목록

### 회원 관련
- [x] 회원 가입하는 기능
    - [x] 카카오 로그인을 통한 가입 기능
    - [x] 카카오 계정에 대한 Jwt 발급 기능
- [x] 회원 정보 조회하는 기능
- [x] 회원 정보 수정하는 기능
- [x] 회원 탈퇴하는 기능
- [x] 자신이 속한 레이스 조회 기능

### 💶캐시 관련

- [x] 캐시를 충전하는 기능
- [x] 캐시를 사용하는 기능
- [ ] 캐시를 출금하는 기능

### 🏃‍♀️레이스 관련

- [x] 레이스를 생성하는 기능
    - [x] 레이스 생성 시 미션 생성 기능
- [x] 레이스의 링크를 통해 레이스에 참여하는 기능
    - [x] 참여 시 라이더 생성
- [x] 레이스의 링크를 카톡으로 공유하는 기능
- [x] 사용자가 레이스에 참여하면 캐시를 사용하는 기능
- [x] 레이스를 시작하는 기능
- [ ] 라이더에게 미션을 수행하는 날에 미리 알림을 주는 기능
- [x] 라이더가 미션 수행을 인증하는 기능
- [x] 레이스가 끝나는 기능

### 정산 관련
- [x] 라이더별로 성취율을 계산하는 기능
- [x] 성취율에 따라 금액을 분배하는 기능(수수료 포함)

### 라이더 관련

- [x] 라이더를 기준으로 데이터 조회 기능

### 인증 관련
- [x] 인증 사진을 촬영하는 기능 
- [x] 로그인된 멤버 기준으로 24시간 이내의 인증 필요한 미션을 조회하는 기능
- [ ] 다른 라이더의 거짓 인증을 신고하는 기능
- [x] 특정 라이더의 미션 인증을 실패로 수정하는 기능

## 도메인 설계

![domain](image/domain.png)

최대한 Aggregate 개념을 기준으로 분리해보았습니다. 
