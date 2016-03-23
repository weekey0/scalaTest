package com.test.my



/**
  * Created by wangzihe on 2016/2/26.
  */
object Part6 {
//*******************函数值和闭包********************************
//----------------------函数值-------------------------------------
  def totalResultOverRange(number:Int ,codeBlock: Int => Int) : Int = {
    var result = 0
    for(i <- 1 to number)
      result += codeBlock(i)
   result
  }

  println(totalResultOverRange(11,i =>i))
  println("i/2 = "+ totalResultOverRange(11,i =>i/2))
  val dval = totalResultOverRange(11,i=> if (i%2==0) 1 else 0)
  println("偶数求和 = "+dval)
//  println("奇数求和 = "+totalResultOverRange(11,i =>if(i%2!=0) 1else 0))//Error:(18, 60) Invalid literal number
  println("奇数求和 = "+totalResultOverRange(11,i =>if(i%2!=0) 1 else 0))
//-------------------------具有多参数的函数值---------------------------------
  def inject(arr:Array[Int],initial:Int,operation:(Int,Int) => Int) :Int = {
    var carryOver = initial
    arr.foreach(element =>carryOver = operation(carryOver,element))
    carryOver
  }
  val arrays = Array(1,23,667,78,5,85,32,2,4,3,2)
  val sum = inject(arrays,0,(carr,elem) => carr+ elem)
  println("Sum of elements in array "+(arrays mkString ",") +" is "+sum)
  println("Sum of elements in array %s is %d".format(arrays mkString ",", sum))
  println(s"Sum of elements in array ${arrays mkString ","} is $sum")
  val maxs = inject(arrays, Integer.MIN_VALUE,
    (carryOver, elem) => Math.max(carryOver, elem))
  println(s"Max of elements in array ${arrays mkString(",")} is $maxs")

  val sumnew = (0 /: arrays){ (elem,sum) => elem+sum}
  val maxNew  = (Integer.MIN_VALUE /: arrays){
    (large,elem) =>Math.max(large,elem)
  }
  println(s"Sum of elements in array ${arrays mkString("[", ",", "]")} is $sumnew")
  println("Max of elemtns in array %s is %d".format(arrays mkString("[",",","]") ,maxNew))
//----------------Curry化------------------------------
//  val sumError = inject(arrays,0 {(carr,elem) => carr+ elem})
  def foo(a:Int)(b:Int)(c:Int){}
  println(foo _)
//  def inject(arr:Array[Int],initial:Int)(operation :(Int,Int) => Int) :Int ={//double definition
  def inject(arr:Array[Int],initial:Int)(operation :(Int,Int) => Int)(a:Int) :Int ={
    var carrayOver = initial
    arr.foreach(element => carrayOver = operation(carrayOver,element))
    carrayOver
  }
  val sumCurry = inject(arrays,0){ (carryOver,elem) => carryOver + elem }(0)//inject(arrays,0)((carryOver,elem) => carryOver+elem)
  println(s"Sum of elements in array ${arrays toList} is $sumCurry")
//----------------重用函数值-------------------------------
  class Equipemt(val routine : Int => Int){
    def simulate(input : Int) = {
      print("Running simulation ...")
      routine
    }
  }
  val equipemt1 = new Equipemt({input => println("calc with "+ input); input})
  val equipemt2 = new Equipemt({input => println("calc with "+ input); input})
  equipemt1.simulate(4)
  equipemt2.simulate(7)
  val  calculator = {input: Int => println("calc with "+ input); input}
  def calculator(input: Int) = { println("calc with "+ input); input }
  val equipemt3 = new Equipemt(calculator)
  val equipemt4 = new Equipemt(calculator)
  equipemt3.simulate(4)
  equipemt4.simulate(3)
  println()
//------------------参数的位置记法-----------------------------
  val arr = Array(1,2,4,5,6)
  println("Sum of all values in array is "+
    (0 /: arr){ (sum,elem) => sum + elem}
  )
  println("Sum of all values in array is "+
    (0 /: arr){_ + _}
  )
  arr.exists(p => p>0)
  val negativeNumberExists =arr.exists{ _< 0}
  println("Array has negative number? "+negativeNumberExists)

  def max2(a:Int,b:Int) :Int = if(a>b) a else b
  var max = (Integer.MIN_VALUE /: arr){(large,element) => max2(large,element)}
  max = (Integer.MIN_VALUE /: arr){ max2(_,_)}
  max = (Integer.MIN_VALUE /: arr){max2 _}
  max =  (Integer.MIN_VALUE /: arr){max2}
  //----------------Execute Around Method 模式-------------------------
  class Resource private(){
    println("Starting transaction...")
    private def cleanUp(){ println("Ending transaction...")}
    def op1 = println("Operation 1")
    def op2 = println("Operation 2")
    def op3 = println("Operation 3")
  }
  object Resource {
    def use(codeBlock:Resource =>Unit): Unit ={
      val resource = new  Resource
      try {
        codeBlock(resource)
      }finally {
          resource.cleanUp()
      }
    }
  }
  Resource.use{ resurce =>
    resurce.op1
    resurce.op2
    resurce.op3
    resurce.op1
  }

  import java.io._

  def writeToFile(fileName:String)(codeBlock : PrintWriter => Unit) = {
    val writer = new PrintWriter(new File(fileName))
    try { codeBlock(writer)}finally { writer.close()}
  }
  writeToFile("output.txt"){ writer
   => writer write "hello form scala"
  }
//---------------------偏应用函数------------------------------
  import java.util.Date

  def log(date: Date,message: String): Unit ={
    println(date +"----" + message)
  }
  val date = new Date
  log(date,"message1")
  log(date,"message2")
  log(date,"message3")
  val logWithDateBound = log(new Date,_ : String)
  logWithDateBound("message4")
  logWithDateBound("message5")
  logWithDateBound{"message6"}
//-------------------------闭包--------------------------------
  def loopThrough(number: Int)(closure: Int => Unit): Unit ={
  for(i <- 1 to number) {closure(i)}
  }
  var result = 0
  val addIt = {value: Int => result += value}
  loopThrough(10){addIt}
  println("Total of values from 1 to 10 is "+ result)
  result = 0
  loopThrough(5){addIt}
  println("Total of values from 1 to 5 is "+ result)
  var product = 1
  loopThrough(5){product *= _}
  println("Product of values from 1 to 5 is "+ product)


  def main(args: Array[String]) {
    println("i/3 = "+totalResultOverRange(11,i=>i/3))
  }
}
