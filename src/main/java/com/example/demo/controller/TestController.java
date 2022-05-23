package com.example.demo.controller;


import java.util.HashMap;
import java.util.Map;

import com.rsa.AESUtil;

import com.rsa.ReturnDeal;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public Map<String, Object> getKey() {

        Map<String,Object> map=new HashMap<String,Object>();//定义一个Map集合

        String KEY = "1234567890hijklm";

        String s1 = AESUtil.encrypt("Success",KEY);

        map.put("key",s1);

        return map;

    }


    @GetMapping("/test1")
    public String getKey1() {

        Map<String,Object> map=new HashMap<String,Object>();//定义一个Map集合

        String KEY = "1234567890hijklm";

        map.put("key","Success");
        map.put("key1","Success");
        map.put("key2","Success"); map.put("key3","Success");


        JSONObject a = new JSONObject();
        a = JSONObject.fromObject(map);
        String s1 = AESUtil.encrypt(a.toString(),KEY);

        return s1;

    }
    @ReturnDeal
    @GetMapping("/test2")
    public String getKey2() {

        JSONObject a = new JSONObject();

        a.put("key", "Success");

        //String KEY = "1234567890hijklm";

        //String s1 = AESUtil.encrypt(a.toString().toString(),KEY);



        return a.toString();

    }
    @ReturnDeal
    @GetMapping("/test3")
    public JSONObject getKey3() {

        JSONObject a = new JSONObject();

        a.put("key", "Success");


        return a;

    }

    @ReturnDeal
    @GetMapping("/test4")
    public String getKey4() {

        JSONObject a = new JSONObject();

        a.put("key", "Success");


        return a.toString();

    }



}
