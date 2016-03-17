package com.test.my

import java.util

import com.test.my.social.Acquaintance
import com.test.my.society.professional.Executive

object test{

  def main(args:Array[String]):Unit = {
   println("hahaaha")
   var s = new Acquaintance
    s.socialize(new Executive)
  }


  val str1 = "hello"
  val str2 = "hello"
  val str3 = new String("hello")
  println(str1 == str2)
  println(str1 eq str2)
  println(str1 == str3)
  println(str1 eq str3)


  val list1 = new util.ArrayList[Int];
  {
    println("Created list1")
  }

  val list2 = new util.ArrayList[Int]()
  {
    println("Created list2")
  }
  println(list1.getClass)
  println(list2.getClass)

  class Microwave {
    def start() = println("Started")

    def stop() = println("Stopped")

    private def turnTable() = println("turning table")
  }
  val microwave = new Microwave
  microwave.start()
//  microwave.trunTable() //Error
  class Vehicle{
    protected def checkEngine(){}

  }
  class Car extends Vehicle{
    def start(){ checkEngine()}
    def tow(car: Car): Unit ={
      car.checkEngine()
    }
    def tow(vehicle: Vehicle): Unit ={
//      vehicle.checkEngine() //error
    }
  }

  class GasStation{
    def fillGas(vehicle: Vehicle): Unit ={
//      vehicle.checkEngine() //Error
    }
  }



  override def toString = s"test(str1=$str1, str2=$str2, str3=$str3, list1=$list1, list2=$list2, microwave=$microwave)"
}

package society{
  package professional{
  class Executive{
      private [professional] var workDetails = "sdfsd"
      private[society] var friends = "ssss"
      private[this] var secrets = "qqqq"
        def help(another:Executive):String ={
          println(another.workDetails)
          //          println(another.secrets) Error
         another.friends

        }
    }
  }
}
package social{

import com.test.my.society.professional
import com.test.my.society.professional.Executive

class Acquaintance{
    def socialize(preson: professional.Executive): Unit ={
      println(preson.help(new Executive))

    }
  }
}
