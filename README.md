# Tailwindcss - Next.js - Typescript 세팅

---

### Next.js - Tailwind 세팅

```bash
npx create-next-app --example with-tailwindcss [프로젝트 이름]
# or
yarn create next-app --example with-tailwindcss [프로젝트 이름]
```

### Typescript 세팅

```bash
yarn add --dev typescript @types/react @types/node
```

`@types/react` `@types/node` -> 리액트에서 사용될 타입이 정의된 패키지

### ESlint 세팅

```bash
yarn add --dev eslint eslint-config-prettier eslint-plugin-prettier eslint-plugin-react
```

eslint 환경에 알아두면 좋을 정보 :[Link] (https://pravusid.kr/javascript/2019/03/10/eslint-prettier.html)

### Prettier 세팅

```bash
yarn add --dev prettier
```

### 추가 VScode 세팅

ESLint , Prettier, Material Icon Theme, Tailwindcss/ IntelliSense 확장 설치

`yarn build` 명령어로 tsconfig.json 자동 생성 또는 `tsc --init` 명령어로 tsconfig.json 파일 생성

추가적으로 .prettierrc / .prettierignore / .eslintrc 등등 추가
