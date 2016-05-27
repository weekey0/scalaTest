package com.test.my.Algs4;



import com.test.my.Algs4.stblib.In;
import com.test.my.Algs4.stblib.StdIn;
import com.test.my.Algs4.stblib.StdOut;

import java.lang.ref.SoftReference;
import java.util.Arrays;

/**二分查找
 * http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BinarySearch.java.html
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt    http://algs4.cs.princeton.edu/11model/tinyT.txt
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
    public static int rank(int key,SoftReference<Integer[]> soft ){
        Integer[] integers = soft.get();
        if (integers!=null){
            int lo = 0;
            int hi = integers.length-1;
            while (lo<=hi){
                int mid = lo +(hi -lo)/2;
                if (key<integers[mid]){ hi = mid -1;}
                else if (key>integers[mid]) lo = mid+1;
                else return  mid;
            }
        }
        return -1;
    }


    public static void main(String[] args){
//        Interlocution();
        Test();
//        int[] whiteList = In.readInts("E:\\test\\scalaTest\\src\\com\\test\\my\\Algs4\\fileIn");
//        Arrays.sort(whiteList);
//        while(!StdIn.isEmpty()){//读取key值，如果不存在在名单中则将其打印
//            int key = StdIn.readInt();
//            if (rank(key,whiteList)<0){
//                StdOut.println(key);
//            }
//        }
//        In in = new In(args[0]);//Data files url
//        In inw = new In(args[1]);//Data files url
//        int[] whiteList = in.readAllInts();
//        int[] blacklist = inw.readAllInts();
//        Arrays.sort(whiteList);
//        use(whiteList,blacklist);
//        useSoft(whiteList, blacklist);

    }

    private static void Test() {
        StdOut.println((0+15)/2);
        StdOut.println(2.0e-6 * 100000000.1);
        StdOut.println(true && false || true && true);
        StdOut.println((1+2.322)/2);
        StdOut.println(1+2+3.0);
        int f = 0;
        int g = 1;
        for(int i=0;i<=15;i++){
            StdOut.println(f);
            f = f+g;
            g = f-g;
        }
        double t= 9.0;
        while(Math.abs(t - 9.0/t)> .001 )
            t = (9.0/t +t)/2.0;
        StdOut.printf("%.5f\n",t);
        int sum =0;
        for(int i =1;i<10;i++)
            for(int j =0 ;j<10;j++)
                sum++;
        StdOut.println(sum);
        int sumN =0;
        for (int i =1;i<10;i*=2)
        for (int j =0;j<10;j++)
            sumN++;
        StdOut.println(sumN);
        StdOut.println('a'+1);
       StdOut.println(Integer.toBinaryString(10));
       StdOut.println(myToBinaryString(10));
        boolean[][] input = {{true,true},{true,false,true},{true,true},{true,false,true},{true,true},{true,false,true}};
        printInput(input);
        for (int i=0;i<100;i++){
            StdOut.println(i +" "+ F(i));
        }

    }
    private static int[] arr = new int[]{};
    private static int F(int i) {

        if (i ==0){ i=0; arr = insertElement(arr,i,0);}

        if (i== 1) {i=1; arr = insertElement(arr,i,1);}

        if (i!=1&&i!=0&& arr.length>=(i-1)) {
            int t = arr[i - 1] + arr[i - 2];
            arr = insertElement(arr,t,i);
            i =t;
        }
        return i;
    }
    private static int[] insertElement(int original[],
                                       int element, int index) {
        int length = original.length;
        int destination[] = new int[length + 1];
        System.arraycopy(original, 0, destination, 0, index);//拷贝original从0-(index-1)值到destination
        destination[index] = element;
        System.arraycopy(original, index, destination, index
                + 1, length - index);//拷贝original从index-(length-1)值到destination
        return destination;
    }

    private static void printInput(boolean[][] input) {
        for (int i=0;i<input.length;i++){
            boolean[] t = input[i];
            for(int j=0;j<t.length;j++){
                StdOut.print("["+i+","+j+"]");
                if (t[j]==true)
                    StdOut.print("*");
                else
                    StdOut.print("  ");
            }
            StdOut.println();
        }
        int i=0,j=0;
        for (boolean[] t:input) {
            for (boolean tt:t) {
                StdOut.print("["+i+","+j+"]");
                StdOut.print(tt?"*":" ");
                j++;
            }
            i++;
            j=0;
            StdOut.println();
        }
    }

    private static String myToBinaryString(int i) {
        String s = "";
        for (int t = i;t>0;t /= 2)
            s = (t%2) +s;
        return s;
    }

    private static void Interlocution() {
        StdOut.println("Math.abs(-2147483648) = "+Math.abs(-2147483648));
        StdOut.println("Math.abs(-2147483648l) = "+Math.abs(-2147483648l));
        StdOut.println("Math.abs(-2147483648.0) = "+Math.abs(-2147483648.0));
        StdOut.println("1/0.0 ="+(1/0.0));
//        a/b的商向0取整；a%b的余数定义是(a/b)*b + a % b 恒等于 a
        StdOut.println("-14 % 3 = "+(-14 % 3 ));
        StdOut.println("14 % -3 = "+(14 % -3 ));
        StdOut.println("-14 % -3 = "+(-14 % -3 ));
//      ------------------result--------------------
//        Math.abs(-2147483648) = -2147483648
//        Math.abs(-2147483648l) = 2147483648
//        Math.abs(-2147483648.0) = 2.147483648E9
//        1/0.0 =Infinity
//        -14 % 3 = -2
//        14 % -3 = 2
//        -14 % -3 = -2
    }

    private static void use(int[] whiteList, int[] blacklist) {
        long begin = System.currentTimeMillis();
        for (int b:
                blacklist) {
            if (rank(b,whiteList)<0){
                StdOut.println(b);
            }
        }
        long end = System.currentTimeMillis();
        StdOut.println("total time : "+(end-begin));
    }

    private static void useSoft(int[] whiteList, int[] blacklist) {
        long begin = System.currentTimeMillis();
        SoftReference<Integer[]> whiteListSoftReference = getIntArraySoftReference(whiteList);
        SoftReference<Integer[]> blacklistSoftReference = getIntArraySoftReference(blacklist);
        Integer[] blacklistIntegers = blacklistSoftReference.get();
        if (blacklistIntegers!=null){
            for (Integer b:
                    blacklistIntegers) {
                if (rank(b,whiteListSoftReference)<0)//key不存在，打印
                    StdOut.println(b);
            }
        }
        long end = System.currentTimeMillis();
        StdOut.println("useSoft total time : "+(end-begin));//1776
    }

    /**
     * 获取软引用对象，内存不足时才会被回收，调用get获取对象，需判断是否为空
     * @param whiteList
     * @return
     */
    private static SoftReference<Integer[]> getIntArraySoftReference(int[] whiteList) {
        Integer[] temp =new Integer[whiteList.length];
        for (int i=0;i<whiteList.length;i++) {
            temp[i] = whiteList[i];
        }
        whiteList = null;
        //Arrays.sort(temp);
        SoftReference<Integer[]> softReference = new SoftReference<Integer[]>(temp);
        temp =null;
        return softReference;
    }
}
