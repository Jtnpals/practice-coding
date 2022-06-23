package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    private SearchParam searchParam;

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getRequest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd ){
        String password = "1234"; // 로컬변수로 password가 이미 선언된경우 @RequestParam에 name값으로 명시해 매핑 가능
        System.out.println("id : " + id + " password : " + pwd );
        return id + pwd;
    }

    // 여러개의 파라미터를 받을 때 객체로 넘길수 있음
    @GetMapping("/getMultiParameter")
    public String getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return "ok";
    }

    //객체 자체를 리턴하면 잭슨라이브러리를 통해 json 형태로 리턴됨
    @GetMapping("/getMultiParameterWithJson")
    public SearchParam getMultiParameterWithJson(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){
        // {"resultCode": "OK", "description": "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}