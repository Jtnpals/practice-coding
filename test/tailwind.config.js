module.exports = {
  purge: [],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        primary: '#FF6363',
        secondary: {
          100: '#E3E2D5',
          200: '#888883',
        },
      },
      fontFamily: {
        body: ['Noto Sans KR'],
      },
      spacing: {
        256: '64rem',
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
