# setting

## husky & commitlint & lint-staged

```bash
npm install --save-dev lint-staged
npm install --save-dev @commitlint/config-conventional @commitlint/cli
npx husky-init && npm install
npx husky add .husky/commit-msg 'npx --no -- commitlint --edit "$1"'
npx husky set .husky/pre-commit 'npx lint-staged'
```

commitlint.config.js

```js
module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
    // 'scope-enum': [2, 'always', ['yourscope', 'yourscope']],
    'type-enum': [
      2,
      'always',
      [
        'feat',
        'fix',
        'docs',
        'chore',
        'style',
        'refactor',
        'ci',
        'test',
        'perf',
        'revert',
      ],
    ],
  },
};
```

## prettier & eslint

package.json

```json
  "lint-staged": {
    "src/**/*.{js,jsx,ts,tsx}": [
      "eslint --max-warnings=0",
      "prettier -w"
    ],
    "src/**/*.{json,css,scss,md}": [
      "prettier -w"
    ]
  }
```

```bash
npm install --save-dev prettier eslint-config-prettier
```

```bash
npm install --save-dev eslint-plugin-unused-imports
npm install --save-dev @typescript-eslint/eslint-plugin @typescript-eslint/parser

```

.eslintrc.json

```json
{
  "plugins": ["unused-imports"],
  "rules": {
    "no-unused-vars": "off", // or "@typescript-eslint/no-unused-vars": "off",
    "unused-imports/no-unused-imports": "error",
    "unused-imports/no-unused-vars": [
      "warn",
      {
        "vars": "all",
        "varsIgnorePattern": "^_",
        "args": "after-used",
        "argsIgnorePattern": "^_"
      }
    ]
  }
}
```
