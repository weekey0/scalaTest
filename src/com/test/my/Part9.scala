package com.test.my

/**
  * Created by wangzihe on 2016/4/5.
  */
object Part9 {
  //********************************模式匹配和正则表达式**********************
  //-------------------------匹配字面量和常量----------------------------------
  def activity(day:String): Unit ={
    day match {
      case "Sunday" => print("Eat,sleep,repeat... ")
      case "Saturday" => print("Hangout with friends... ")
      case "Monday" => print("...code for fun...")
      case "Friday" => print("...read a good book...")
    }
  }
  List("Monday","Sunday","Saturday").foreach{ activity }
  println()
//------------------------------匹配通配符------------------------------------
  object DayOfWeek extends  Enumeration{
    val SUNDAY = Value("Sunday")
    val MONDAY = Value("Monday")
    val TUESDAY = Value("Tuesday")
    val WEDNESDAY = Value("Wednesday")
    val THURSDAY = Value("Thursday")
    val FRIDAY = Value("Friday")
    val SATURDAY = Value("Saturday")
  }
  def activityNew(day:DayOfWeek.Value): Unit ={
    day match {
      case DayOfWeek.SUNDAY => println("Eat,sleep,repeat... ")
      case DayOfWeek.SATURDAY => println("Hangout with friends....")
      case _ => println("..code for fun..")
    }
  }
  activityNew(DayOfWeek.SATURDAY)
  activityNew(DayOfWeek.MONDAY)
  //---------------------------匹配元组和列表-------------------------------------
  def processCoordinates(input: Any): Unit ={
     input match {
       case (a,b) => println("Processing (%d,%d)...",a,b)
       case "done" => println("done")
       case _ => null
     }
  }
  processCoordinates((33,23))
  processCoordinates("done")
  def processItems(items:List[String]): Unit ={
    items match {
      case List("apple","ibm") => println("Apples and IBMs")
      case List("red","blue","white") => println("Stars and Stripes...")
      case List("red","blue",_*)=> println("color red ,blue...")
      case List("apple","orange",otherFruits @ _*) =>println("apples,oranges,and "+
      otherFruits)
    }
  }
  processItems(List("apple","ibm"))
  processItems(List("red","blue","green"))
  processItems(List("red","blue","white"))
  processItems(List("apple","orange","grapes","dates"))

  def main(args: Array[String]) {

  }

}
