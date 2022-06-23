package main

import "fmt"

// Go언어에서는 while문을 제공하지 않아 for문만 사용할 수 있습니다.
func main() {
	sum := 0

	for i := 1; i <= 10; i++ {
		sum += i
	}
	fmt.Println("1부터 10까지 정수 합계:", sum)

	n := 2

	for n < 100 {
		fmt.Printf("count %d\n", n)

		n *= 2
	}

	// for {
	// 	fmt.Printf("무한루프입니다.\n")
	// }

	var arr [6]int = [6]int{1, 2, 3, 4, 5, 6}
	// for each
	for index, num := range arr {
		fmt.Printf("arr[%d] is %d\n", index, num)
	}

	var actors [4]string = [4]string{"one", "two", "three", "four"}

	for _, actor := range actors {
		fmt.Printf("count %s\n", actor)
	}
	for index := range actors {
		fmt.Printf("count %d \n", index+1)
	}

	var fruits map[string]string = map[string]string{
		"apple":  "red",
		"banana": "yellow",
		"grape":  "purple",
	}

	for fruit, color := range fruits {
		fmt.Printf("%s : %s\n", fruit, color)
	}
}
