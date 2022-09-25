package com.example.Utils;

public enum Coco {



    ok("Success", 200);


    public String message;
    public Integer code;


    Coco(String success, int i) {
        this.message = success;
        this.code = i;
    }


}
