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
       case _ => println("not match ..")
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
//-----------------------------类型和卫述句的匹配---------------------------------------
  def process(input: Any): Unit ={
  input match {
    case (a:Int,b:Int) => print("Processing (int, int)... ")
    case (a:Double,b:Double) => print("Processing (double,double)...")
    case msg: Int if(msg >1000000) => println("Processing int> 1000000")
    case msg: Int =>print("Processing int... ")
//    case msg: Int =>print("Processing int... ")
//    case msg: Int if(msg >1000000) => println("Processing int> 1000000")
    case msg: String => println("Processing string...")
    case _ =>printf("Can't handle %s ... ",input)
  }
}
  process((34.3,-123,3))
  process(0)
  process(1000001)
  process(3.2)
//  ----------------------case表达式里的模式变量和常量-------------------------------------
  class Sample{
    val max = 100
    val MIN = 0
    def process(input:Int): Unit ={
      input match {
//        case max => println("Don't try this at home")//Compiler error
        case this.max => println("You matched max")
        case MIN => println("You matched min")
        case _ => println("Unreachable")
      }
    }
  }
  new Sample().process(100)
  new Sample().process(0)
  new Sample().process(10)
//-----------------------------使用case类进行模式匹配-------------------------------------
//  abstract case class Trade()//error
 sealed abstract class Trade()//{
//    def stockSymbol: String
//    def quantity: Int
//  }
  case class Sell(stockSymbol: String,quantity: Int) extends Trade
  case class Buy(stockSymbol: String,quantity: Int) extends Trade
  case class Hedge(stockSymbol: String,quantity: Int) extends Trade
  class TradeProcessor{
    def processTransaction(request: Trade): Unit ={
      request match {
        case Sell(stock,1000)=>println("Selling 1000-units of "+stock)
        case Sell(stock,quantity) =>
          printf("Selling %d units of %s\n",quantity,stock)
        case Buy(stock,quantity) if(quantity >2000) =>
          printf("Buying %d (large) units of %s\n",quantity,stock)
        case Buy(stock,quantity) => printf("Buying %d units of %s\n",quantity,stock)
      }
    }
  }
  val tradeProcessor = new TradeProcessor
  tradeProcessor.processTransaction(Sell("GOOG",500))
  tradeProcessor.processTransaction(Buy("GOOG",700))
  tradeProcessor.processTransaction(Sell("GOOG",1000))
  tradeProcessor.processTransaction(Buy("GOOG",3000))

//  error,The argument types of an anonymous function must be fully known. (SLS 8.5)
//  Expected type was: Unit  def acceptStuff(thing : Unit)
//  case class Apple()
//  case class Orange()
//  case class Book()
//  class ThingsAcceptor{
//    def acceptStuff(thing : Any): Unit ={
//      case Apple() =>println("Thanks for the Apple")
//      case Orange() =>println("Thanks for the Orange")
//      case Book() =>println("Thanks for the Book")
//      case _ => println("Excuse me, why did you send me a "+thing)
//    }
//  }
//  val acceptor = new ThingsAcceptor
//  acceptor.acceptStuff(Apple())
//  acceptor.acceptStuff(Book())
//  acceptor.acceptStuff(Apple)
//-------------------------使用提取器进行匹配---------------------------------------
  StockService process "GOOG"
  StockService process("IBM")
  StockService process "ERR"
  object StockService {
    def process(input: String) = {
      input match {
        case Symbol() => println("Look up price for valid symbol "+ input)
        // case ReceiveStockPrice(symbol,price)=>printf("Received price %f for symbol %s\n",price,symbol)
        case ReceiveStockPrice(symbol @ Symbol(),price)=>
          printf("Received price %f for symbol %s\n",price,symbol)
        case _ => println("Invalid input "+ input)
      }
    }
  }
  object Symbol{
    def unapply(symbol:String):Boolean = symbol == "GOOG" || symbol == "IBM"
  }
  object ReceiveStockPrice {
    def unapply(s: String): Option[(String,Double)] = {
      try{
        if(s contains(":")){
          val splitQuote = s split ":"
          Some(splitQuote(0),splitQuote(1).toDouble)
        }else{
          None
        }
      }catch {
        case _ : NumberFormatException => None
      }
    }
  }
  StockService process "GOOG"
  StockService process("GOOG:310.84")
  StockService process("GOOG:BUY")
  StockService process("ERR:12.21")
//  ------------------------------正则表达式--------------------------------
  val pattern = "(S|s)cala".r
  val str = "Scala is scalable and cool"
  println(pattern findFirstIn str)
  println(pattern findAllIn(str) mkString(", "))
  println("cool".r replaceFirstIn(str, "awesome"))
//  ----------------------------把正则表达式当做提取器------------------------------------
  def process(input:String): Unit ={
//    val GoogStock = """^GOOG:(\d*\.\d+)""".r
    val MatchStock = """^(.+):(\d*\.\d+)""".r
    input match {
//      case GoogStock(price) =>println("Price of GOOG is "+ price)
      case MatchStock("GOOG",price) => println("Price of GOOG is "+ price)
      case MatchStock("IBM",price) => println("IBM's trading at "+ price)
      case MatchStock(symbol,price) => printf("Price of %s is %s\n",symbol,price)
      case _ =>println("not processing "+ input)
    }
  }
  process("GOOG:310.84")
  process("GOOG:310")
  process("IBM:84.01")
  process("GE:15.99")

  def main(args: Array[String]) {

  }



}
