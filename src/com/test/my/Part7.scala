package com.test.my

import java.io.StringWriter
import java.util.{Date, Calendar}

/**
  * Created by wangzihe on 2016/3/1.
  */
object Part7 {

  //********************************Trait和类型转换**********************
  //-------------------------Trait----------------------------------
  class Human(val name: String) {
    def listen() = println("---Your friend " + name + " is listening")
  }

  class Man(override val name: String) extends Human(name)

  class Woman(override val name: String) extends Human(name)

  trait Friend {
    val name: String

    def listen() = println("Your friend " + name + " is listening")
  }

  class HumanNew(val name: String) extends Friend

  class ManNew(override val name: String) extends HumanNew(name)

  class WomanNew(override val name: String) extends HumanNew(name)

  trait Walk {
    val name: String
    val speed: Float

    def walking() = println(name + " is walking,speed is " + speed + " m/s")
  }
  trait go{
    val namesss :String
    def go() = println(namesss+" go!!!")
  }

  class Animal
//0.
//  abstract class Dog(val name: String) extends Animal with Friend with Walk {
//  1.
//  abstract class Dog(val name: String) extends Animal with Friend with Walk with go{
  abstract class Dog(val name: String,val namesss :String) extends Animal with Friend with Walk with go{
    override def listen = println("%s is dog and listening quietly".format(name))
  }
//0.
//  class ShepherdDog(override val name: String)(val speed: Float) extends Dog(name)
//  1.
//  class ShepherdDog(override val name: String)(val speed: Float) extends Dog(name) {
//  override val namesss: String ="haah"
//}
class ShepherdDog(override val name: String, override val namesss:String)(val speed: Float) extends Dog(name,namesss)

  val john = new ManNew("John")
  val sara = new WomanNew("Sara")
//  0.
//  val tom = new ShepherdDog("Tom")(11.3f)
  val tom =new ShepherdDog("tom","dd")(22)
  tom.walking
  tom.listen
//  1.
  tom.go()
  john.listen()
  sara.listen
  val mansBestFriend: Friend = tom
  mansBestFriend.listen()

  def helpAsFriend(friend: Friend) = friend listen()

  helpAsFriend(john)
  helpAsFriend(tom)

  //  ---------------------------选择性混入-----------------------------------
  class Cat(val name: String) extends Animal
  def useFriend(friend: Friend) = friend.listen()
  val alf = new Cat("alf")
//  val friend : Friend = alf //ERROR
//  useFriend(alf) //ERROR
  val snowy = new Cat("Snowy") with  Friend
  val friend :Friend = snowy
  friend.listen
  useFriend(snowy)
//-----------------------------以trait进行装饰----------------------------------------------------------------
  abstract class Check {
    def check() : String = "Checked Application Details..."
  }
  trait CreditCheck extends Check{
    override def check(): String = "Checked Credit..."+ super.check()
  }
  trait EmploymentCheck extends Check{
    override def check(): String = "Checked Employment..."+super.check()
  }
  trait CriminalRecordCheck extends Check{
    override def check(): String = "Check Criminal Records..."+super.check()
  }

  val apartmentApplication = new Check with CreditCheck with CriminalRecordCheck
  println( apartmentApplication check)
  val employmentApplication = new Check with CriminalRecordCheck with EmploymentCheck{}
  println(employmentApplication check())
  //  val noApplication = new CreditCheck// Error Trait CreditCheck is abstract; Can not be instantiable
  var noApplication = new CreditCheck {}
  println(noApplication check())
  noApplication = new CreditCheck with CriminalRecordCheck /*{ println("ddd")}*/
  println(noApplication check)
//---------------------------------Trait方法的延迟绑定-------------------------------------
  abstract class Writer{
    def writeMessage(message: String)
  }
  trait UpperCaseWriter extends  Writer{
    abstract override def writeMessage(message: String) = {
      super.writeMessage(message.toUpperCase)
    }
  }
  trait ProfanityFilteredWriter extends Writer{
    abstract override def writeMessage(message: String) =
    super.writeMessage(message.replace("stupid","s-----"))
  }
  class StringWriterDelegate extends Writer{
    val writer = new StringWriter()
//    override def writeMessage(message: String): Unit =writer.write(message)
    def writeMessage(message: String) = writer.write(message)
    override def toString(): String =writer.toString
  }
  val myWriterProfanityFirst =
    new StringWriterDelegate with UpperCaseWriter with ProfanityFilteredWriter
  val myWriterProfanityLast =
    new StringWriterDelegate with ProfanityFilteredWriter with UpperCaseWriter

  myWriterProfanityFirst writeMessage "There is no sin except stupidity"
  myWriterProfanityLast writeMessage "There is no sin except stupidity"

  println(myWriterProfanityFirst)
  println(myWriterProfanityLast)
  //--------------------------------隐私类型转换---------------------------------------------
  class DateHelper(number: Int){
    def days(when: String) : Date = {
      val date = Calendar.getInstance()
      when match {
        case "ago" => date.add(Calendar.DAY_OF_MONTH,-number)
        case "from_now" => date.add(Calendar.DAY_OF_MONTH,number)
        case _ =>
      }
      date.getTime()
    }
  }

  implicit def convertInt2DateHelper(number: Int) = new DateHelper(number)
  val ago = "ago"
  val from_now = "from_now"
  val past = 2 days ago //  val past = {2.days(ago)}
  val appointment = 5 days from_now
  println(past)
  println(appointment)

  class DateHelperNew(number:Int){
    def daysNew(when: String) : Date = {
      val date = Calendar.getInstance()
      when match {
        case DateHelperNew.agoN => date.add(Calendar.DAY_OF_MONTH,-number)
        case DateHelperNew.from_nowN => date.add(Calendar.DAY_OF_MONTH,number)
        case _ => date

      }
      date.getTime
    }
  }
  object DateHelperNew{
    val agoN = "ago"
    val from_nowN = "from_now"

    implicit  def convertInt2DateHelperNew(number: Int) = new DateHelperNew(number)
  }

  import DateHelperNew._
  val pastNew = 2 daysNew agoN
  var appointmentNew = 5 daysNew (from_nowN)
  println(pastNew)
  println(appointmentNew)
  appointmentNew = 5 daysNew(from_now)
  println(appointmentNew)


  def main(args: Array[String]) {

  }
}
