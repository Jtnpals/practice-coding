package main

import "fmt"

func main() {
	age, name := 24, "길동"

	fmt.Printf("안녕 난 %s이야\n", name)
	fmt.Printf("나이는 %d살이야\n", age)
	fmt.Printf("반가워")

	n := 14
	var arr [5]int = [5]int{1, 2, 3, 4, 5}

	fmt.Printf("내 동생은 %d살이야\n", n)
	fmt.Printf("%d \n", arr) //배열 한 번에 출력 가능
	fmt.Printf("%d %d", n, arr)

	fmt.Printf("5>6=%b\n", 5 > 6)
	fmt.Printf("15는 2진수로 %b\n", 15)
	fmt.Printf("저의 성은 %c 입니다\n", '김')
	fmt.Printf("19는 10진수로 %d입니다.\n", 19)
	fmt.Printf("19는 8진수로 %o입니다.\n", 19)
	fmt.Printf("19는 16진수로 %x입니다.\n", 19)
	fmt.Printf("19는 16진수로 %X입니다.\n", 19)
	fmt.Printf("19.1234는 고정 소수점으로 %f입니다.\n", 19.1234)
	fmt.Printf("19.1234는 고정 소수점으로 %F입니다.\n", 19.1234)
	fmt.Printf("19.1234의 지수 표현은 %e입니다.\n", 19.1234)
	fmt.Printf("19.1234의 지수 표현은 %E입니다.\n", 19.1234)
	fmt.Printf("19.1234의 간단한 실수 표현은 %g입니다.\n", 19.1234) // 고정 소수점이 아님
	fmt.Printf("19.1234의 간단한 실수 표현은 %G입니다.\n", 19.1234) // 고정 소수점이 아님
	fmt.Printf("문자열: %s\n", "안녕하세요.")

	var num int = 50
	fmt.Printf("num은 %d입니다.\n", num)

	fmt.Printf("num의 메모리 주소 출력: %p\n", &num) //주솟값을 참조하기 위해 &를 쓴다.
	fmt.Printf("num의 유니코드 값은 %U입니다.\n", num)
	fmt.Printf("num의 타입은 %T입니다.\n", num)
	fmt.Printf("num의 타입은 %T입니다.\n", num)
	fmt.Printf("모든 형식으로 출력: %v, %v\n", 54.234, "Hello")
	fmt.Printf("num의 타입은 %T입니다.\n", num)
	fmt.Printf("7이 어떤 형식인지 표시: %d, %#o, %#x\n", 7, 7, 7) //8진수는 앞에 0이 붙고, 16진수는 0x가 붙습니다.
	fmt.Printf("네 칸 차지하는 13: %4d\n", 13)
	fmt.Printf("빈칸은 0으로 채우고 4칸 차지하는 13: %04d\n", 13)
	fmt.Printf("총 네 칸 차지하고 왼쪽으로 정렬되는 13과 15: %-4d%-4d\n", 13, 15)
	fmt.Printf("12.1234를 소수점 둘째 자리까지만 표시하면 %.2f입니다.\n", 12.1234)

}
