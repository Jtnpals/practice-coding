package main

import "fmt"

func main() {
	var num1, num2, num3 int

	fmt.Scanln(&num1, &num2, &num3)
	fmt.Println(num1, num2, num3)

	var name, addr string
	var num int
	fmt.Println("이름 번호 주소")
	var re int
	re, _ = fmt.Scanln(&name, &num, &addr) //반환값 받을때 Scanln에서는 반환성공값 숫자가 반환됨
	fmt.Println("이름은 ", name, " 번호는 ", num)
	fmt.Println("주소는 ", addr)
	fmt.Println(re)

	var n1, n2, n3, n4 int
	fmt.Println("IPv4 주소")
	fmt.Scanf("%d.%d.%d.%d", &n1, &n2, &n3, &n4) //포맷에 맞춰 스캔
	fmt.Printf("입력한 IPv4주소는 %d.%d.%d.%d\n", n1, n2, n3, n4)
}
