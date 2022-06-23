package main

//fmt 패키지를 통해 콘솔창에 print 가능하다 기능이 더 많음
//기본 데이터 출력을 위한 자세한 사용법은 링크참조 https://gobyexample.com/string-formatting
import "fmt"

func main() {
	var num1 int = 1
	var num2 int = 1

	fmt.Print("Hello!", num1+num2, "\n")

	fmt.Printf("문자1 %s 문자2 %d", num1, num2)
}
