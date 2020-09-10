package main

//상수는 변하지 않는값 떄문에 무조건 선언과 동시에 초기화해줘야함
//Short Assignment statement := 키워드 사용불가

import "fmt"

const text = "mike"

//소괄호로 묶어서 한번에 선언가능
const (
	c1 = 10 //첫 번째 값은 무조건 초기화해야 함
	c2
	c3
	c4 = "test" //다른 형 선언 가능
	c5
	c6 = iota //c8까지 index값 저장
	c7
	c8
	c9 = "new"
	c10
	c11 = "end"
)

func main() {
	const a int = 1
	const b, d = 10, 20 //여러 값을 초기화할 수 있음

	fmt.Println(text)

	fmt.Println(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11)
}

//결과값
// mike
// 10 10 10 test test 5 6 7 new new end
// 초기화되지 않은 상수들은 맨처음 초기화된 값을 따라감
