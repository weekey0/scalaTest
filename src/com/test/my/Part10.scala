package com.test.my

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by wangzihe on 2016/5/26.
  */
object Part10 {
  //********************************并发编程**********************
  //-------------------------使用Actor的并发----------------------------------
  def sumOfFactors(number: Int) ={
    (0 /: (1 to number)){(sum,i)=>if (number%i ==0)sum+i else sum}
  }
  def isPerfect(candidate:Int) = 2*candidate == sumOfFactors(candidate)
  println("6 is perfect? "+isPerfect(6))
  println("33550336 is perfect? "+ isPerfect(33550336))
  println("33550337 is perfect? "+ isPerfect(33550337))
  def sumOfFactorsInRange(lower:Int,upper:Int,number: Int) = {
    (0 /: (lower to upper)){(sum,i)=>if (number % i ==0) sum+i
      else sum}
  }
  def isPerfectConcurrent(candidate: Int) ={
    val RANCE = 1000000
    val numberOfPartitions = (candidate.toDouble / RANCE).ceil.toInt
//    val caller = self
//例子的版本scala 2.7.4
  }
  class HelloActor extends Actor{
    override def receive: Actor.Receive = {
      case "hello" => println("您好！ ")
      case _       => println("您是？")
    }
  }
/*  object Main extends App{*/
    val system = ActorSystem("HelloSystem")
    val helloActor = system.actorOf(Props[HelloActor],name = "helloActor")
    helloActor ! "hello"
    helloActor ! "喂"
  /*}*/

  object Greeter{
    case object Greet
    case object Done
  }
  class Greeter extends Actor {
    override def receive: Actor.Receive = {
      case msg@Greeter.Greet =>
        println(s"Hello Akka,receive $msg")
        sender() ! Greeter.Done
    }
  }
  class HelloWorld extends Actor{
    override def preStart ={
      val greeter = context.actorOf(Props[Greeter],"greeter")
    }
    override def receive: Actor.Receive = {
      case Greeter.Done =>
        context.stop(self)
    }
  }

  def main (args: Array[String]) {
    akka.Main.main(Array(classOf[HelloWorld].getName))
  }
}
