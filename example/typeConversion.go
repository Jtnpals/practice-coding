package main

import "fmt"

func main() {
	rawLiteral := `엔터
	를 바로 먹
	는다`

	fmt.Println(rawLiteral)

	var i int = 100
	var j uint = uint(i) //0 이상의 정수 음수x
	var f float32 = float32(i)
	println(f, j)

	str := "ABC"
	bytes := []byte(str)
	str2 := string(bytes)
	println(bytes, str2)
}
