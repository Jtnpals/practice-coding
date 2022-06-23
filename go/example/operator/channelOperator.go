package main

import "fmt"

func main() {
	ch := make(chan int) //정수형 채널 생성

	go func() {
		ch <- 10
	}() //채널에 10을 보냄

	result := <-ch //채널로부터 10을 전달받음
	fmt.Println(result)
}
