module.exports = {
  mode: "jit",
  purge: ["./pages/**/*.{js,ts,jsx,tsx}", "./components/**/*.{js,ts,jsx,tsx}"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    fontFamily: {
      "gogle-noto": ['"Noto Sans KR"'],
      "Poor-Story": ['"Poor Story"'],
    },
    extend: {
      spacing: {
        256: "64rem",
        320: "80rem",
        384: "96rem",
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
