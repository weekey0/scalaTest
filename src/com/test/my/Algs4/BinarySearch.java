package com.test.my.Algs4;



import com.test.my.Algs4.stblib.In;
import com.test.my.Algs4.stblib.StdIn;
import com.test.my.Algs4.stblib.StdOut;

import java.lang.ref.SoftReference;
import java.util.Arrays;

/**
 * Created by wangzihe on 2016/3/18.
 */
public class BinarySearch {
    public static int rank(int key, int[] a){
    //数组必须是有序的
      int lo = 0;
      int hi = a.length -1;
      while(lo <= hi){ //被查找的key要么不存在，要么必然存在与a[lo..hi]之中
          int mid = lo +(hi -lo)/2;
          if (key<a[mid]) hi = mid -1;
          else if(key>a[mid]) lo = mid +1;
          else return mid;
      }
      return -1;
    }
//    assert
    public static void main(String[] args){
//        int[] whilelist = In.readInts("E:\\test\\scalaTest\\src\\com\\test\\my\\Algs4\\fileIn");
//        Arrays.sort(whilelist);
//        while(!StdIn.isEmpty()){//读取key值，如果不存在在名单中则将其打印
//            int key = StdIn.readInt();
//            if (rank(key,whilelist)<0){
//                StdOut.println(key);
//            }
//        }
        In in = new In(args[0]);
        int[] whilelist = in.readAllInts();
        Arrays.sort(whilelist);
        In inw = new In(args[1]);
        while(!StdIn.isEmpty()){//读取key值，如果不存在在名单中则将其打印
            int key = StdIn.readInt();
            if (rank(key,whilelist)<0){
                StdOut.println(key);
            }
        }
    }
}
