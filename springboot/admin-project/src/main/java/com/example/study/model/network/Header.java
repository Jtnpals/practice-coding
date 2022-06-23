package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude 를 이용하여 어떠한 값들만 포함할건지 설정가능
public class Header<T> {
    //api 통신시간
    @JsonProperty("transaction_time") //어노테이션으로 카멜케스로 바꿔줄수도 있음 근데 일일히 하기 귀찮으니 properties에 설정
    private LocalDateTime transactionTime; // ISO, YYYY-MM-DD 등 여러가지 매핑방법이 있지만 그냥 시간으로쓸거임

    //api 응답코드
    private String resultCode;

    //api 부가설명
    private String description;

    private T data;

    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder().transactionTime(LocalDateTime.now()).resultCode("OK").description("OK").build();
    }

    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder().transactionTime(LocalDateTime.now()).resultCode("OK").description("OK").data(data).build();
    }

    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder().transactionTime(LocalDateTime.now()).resultCode("ERROR").description(description).build();
    }
}
