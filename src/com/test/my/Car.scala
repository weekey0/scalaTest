package com.test.my

/**
  * Created by wangzihe on 2016/1/27.
  * Created Class Example
  */
//Java example
/**
  * public class Car{
  *   private final int year;
  *   private int miles;
  *   public Car(int yearOfMake){ year = yearOfMake;}
  *   public int getYear(){return year;}
  *   public int getMailes(){reutrn miles;}
  *   public void drive(int distance){
  *     miles += Math.abs(distance);
  *   }
  * }
  */


class Car(val year:Int) {
  private var milesDriven:Int = 0
  def miles() = milesDriven
  def drive(distance: Int): Unit ={
    milesDriven += Math.abs(distance)
  }

 val car =  new Car(2009)
  println("Car made in year "+car.year)
  println("Miles driven "+car.miles)
  println("Drive for 10 miles")
  car.drive(10)
  println("Miles driven "+car.miles)


  def totalResultOverRange(number: Int,codeBlock: Int => Int):Int = {
    var result = 0
    for(i <- 1 to number){
      result += codeBlock(i)
    }
    result
  }
  println(totalResultOverRange(11,i =>i))
  println(totalResultOverRange(11,i =>i/2))

  def main(args: Array[String]) {
    println(totalResultOverRange(11,i =>i/3))
  }
}

