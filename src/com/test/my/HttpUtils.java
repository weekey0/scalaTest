package com.test.my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtils {
    public static String getAjaxCotnent(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("D:\\Documents\\Downloads\\Compressed\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe D:/image/phantomjs/codes.js "+url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while((tmp = br.readLine())!=null){
            sbf.append(tmp);
        }
        //System.out.println(sbf.toString());
        return sbf.toString();
    }

    public static void main(String[] args) throws IOException {
        String ajaxCotnent = getAjaxCotnent("http://www.baidu.com");

        System.out.println(ajaxCotnent);

    }
}