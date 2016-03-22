package com.test.my;

/**
 * Created by wangzihe on 2016/1/26.
 */
public class testJ {
    public  static void main(String [] args){
        String str1J = "hello";
        String str2J = "hello";
        String str3J =  new String("hello");
        System.out.println(str1J == str2J);
        System.out.println(str1J.equals(str2J));
        System.out.println(str1J == str3J);//false ,diff, in scala is true
        System.out.println(str1J.equals(str3J));//true. diff, in scala is false
    }
}
