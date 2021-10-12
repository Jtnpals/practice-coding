윈도우 - 맥 크로스 환경에서 환경변수 관련 개발용 라이브러리

```bash
$ npm install --save-dev cross-env
```

### create-next-app

```bash
$ yarn create next-app --typescript new-project
```

### Path Aliases

`tsconfig.json`

```json
{
    "compilerOptions": {
        {...}
        "baseUrl": ".",
        "paths": {
            "@components/*": ["components/*"],
            "@styles/*": ["styles/*"],
            "@pages/*": ["pages/*"],
            "@hooks/*": ["hooks/*"]
        }
    {...}
}
```

### Tailwind CSS and JIT mode

```bash
$ yarn add -D tailwindcss@latest postcss@latest autoprefixer@latest postcss-cli
```

tailwind config 파일 생성
`npx tailwindcss init -p`

tailwind.config.js

```javascript
module.exports = {
  mode: "jit",
  purge: ["./pages/**/*.{js,ts,jsx,tsx}", "./components/**/*.{js,ts,jsx,tsx}"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {},
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
```

`styles` 폴더내 `Home.module.css` 삭제 및 `global.css`파일 내용 지우기

이후 `tailwind.css` 파일 추가

```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

`package.json` 파일 수정

```json
{
    {...}
    "scripts": {
        "dev": "next dev",
        "build": "next build",
        "start": "next start",
        "lint": "next lint",
        "css:dev": "cross-env TAILWIND_MODE=watch postcss ./styles/tailwind.css -o ./styles/globals.css --watch",
        "css:build": "postcss ./styles/tailwind.css -o ./styles/globals.css",
    },
    {...}
}
```

`yarn dev` 및 `yarn css:dev` 실행

[tailwind jit](https://tailwindcss.com/docs/just-in-time-mode)
