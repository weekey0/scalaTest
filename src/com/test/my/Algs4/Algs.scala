package com.test.my

/**
  * Created by wangzihe on 2016/3/2.
  */
object Algs {
   def gcd(q: Int,p: Int): Int = {
     if (q == 0) return p
     val r = p % q
     gcd(q,r)
   }//不懂呢
  def main(args: Array[String]) {
    val  rs = gcd(21,7)
    println(rs)
    println("sss")
  }
  
}
