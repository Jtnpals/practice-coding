package main

import "fmt"

//변수의 선언과 초기화
var a int = 1
var b string = "Hello"
var num int
var word string
var tf bool

//초기값을 지정하지 않으면, Go는 Zero Value를 기본적으로 할당
//숫자형에는 0, bool 타입에는 false, 그리고 string 형에는 "" (빈문자열)을 할당
func main() {
	fmt.Println(num, " ", word, " ", tf)

	//Short Assignment statement :=
	//함수안에서밖에 사용하지 못하지만 형선언 없이 타입 추론가능

	text := "hi"
	count := 3

	fmt.Println(text, count)
	fmt.Printf("문자 %s 그리고 숫자 %d \n", text, count)

	var c, d int = 10, 20
	fmt.Println(c, d)

	i, j, k := 1, 2, 3
	fmt.Println(i, j, k)

}
