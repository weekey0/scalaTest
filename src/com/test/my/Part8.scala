package com.test.my

/**
  * Created by wangzihe on 2016/3/23.
  */
object Part8 {
  //********************************使用容器**********************
  //-------------------------常见的scala容器----------------------------------
  val colors1 = Set("Blue","Green","Red")
  var colors = colors1
  println("colors1(colors) : "+colors)
  colors += "Black"
  println("colors : "+colors)
  println("colors1 :"+colors1)
  //-------------------------使用Set----------------------------------
  val feeds1 = Set("blog.toolshed.com","pragdave.pragprog.com",
    "pragmactic-osxer.blogspot.com","vita-contemplativa.blogspot.com")
  val feeds2 = Set("blog.toolshed.com","martinfowler.com/bliki")
  val blogSpotFeeds = feeds1 filter(_ contains("blogspot"))
  println("blogspot feeds : "+ blogSpotFeeds.mkString(", "))
  val mergedFeeds = feeds1 .++(feeds2) //feeds1 ++ feeds2 //交集
  println("# of merged feeds: "+ mergedFeeds.size+"\n"+mergedFeeds)
  val onlyInFeed2 = feeds2 -- feeds1//交集中feeds2独有部分
  println("only In feed2: "+ onlyInFeed2.mkString(", "))
  val commonFeeds = feeds1 .&(feeds2)
  println("common feeds: "+commonFeeds.mkString(", "))
  def main(args: Array[String]) {

  }
}
