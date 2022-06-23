package main

import "fmt"

func main() {
	//상수값을 0부터 순차적으로 부여하기 위해 iota 라는 identifier를 사용할 수 있다.
	//iota가 지정된 Apple에는 0이 할당되고, 나머지 상수들을 순서대로 1씩 증가된 값을 부여받는다.
	const (
		Apple  = iota // 0
		Grape         // 1
		Orange        // 2
	)

	fmt.Print(Apple, Grape, Orange)
}
