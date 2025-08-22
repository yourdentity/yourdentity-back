# yourdentity-back
유어덴티티 백엔드 레포입니다.

---
## Convention

### Code
본 프로젝트는 [네이버 Java 컨벤션](https://naver.github.io/hackday-conventions-java/)을 따릅니다.

- 들여쓰기: 4 spaces
- 중괄호: 같은 줄에 시작
- 네이밍
    - 클래스: PascalCase (`UserService`)
    - 메서드/변수: camelCase (`getUserInfo`)
    - 상수: UPPER_SNAKE_CASE (`MAX_RETRY_COUNT`)
- 최대 줄 길이: 120자
- import 시 `*` 금지
- Javadoc 스타일 주석 권장

**IDE 설정 (IntelliJ)**
- `Settings > Editor > Code Style > Java` 에서 네이버 코드스타일 XML Import
- `Settings > Tools > Actions on Save` → `Reformat code` 활성화
- 저장 시 자동 포맷팅 적용

---

### Git
- **Branch**
    - 기능: `feature/#이슈번호-설명` → 예: `feature/#12-login-api`
    - 버그: `fix/#이슈번호-설명` → 예: `fix/#34-token-expired`
    - 문서: `docs/...`
    - 리팩토링: `refactor/...`

- **Commit Message**
    - 형식: `[type]: 내용`
    - 타입 종류
        - feat: 기능 추가 → `feat: 회원가입 API 구현`
        - fix: 버그 수정 → `fix: 로그인 토큰 만료 오류 수정`
        - refactor: 리팩토링 (기능 변화 없음) → `refactor: UserService 구조 단순화`
        - docs: 문서 변경 → `docs: README 업데이트`
        - test: 테스트 코드 추가/수정 → `test: OrderService 단위 테스트 추가`
        - chore: 설정/빌드 변경 → `chore: .gitignore 업데이트`

---

### Workflow (권장)
1. 이슈 생성 (GitHub Issues)
2. 브랜치 생성 (`feature/#이슈번호-설명`)
3. 개발 & 커밋 (`feat: ...`, `fix: ...`)
4. PR 생성 → 리뷰 → Merge

---

## 참고
- [네이버 Java 코드 컨벤션](https://naver.github.io/hackday-conventions-java/)  
