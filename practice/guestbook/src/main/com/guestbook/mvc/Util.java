package com.guestbook.mvc;

import java.nio.charset.StandardCharsets;

public class Util {
    public static int parseInt(String l){
        try {
            int r = Integer.parseInt(l);
            return  r;
        }catch (Exception e){
            return -1;
        }
    }
    public static String h(String content){
        if( content == null || content.equals("")){
            return content;
        }
        try{
            byte[] bs = content.getBytes("8859_1");
            content = new String(bs, StandardCharsets.UTF_8);
        }catch (Exception e){

        }
        return content;
    }
}
