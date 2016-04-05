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
  val feedNew = feeds1 :: feeds2 :: Nil //List
  println(feedNew)
  val onlyInFeed2 = feeds2 -- feeds1//交集中feeds2独有部分
  println("only In feed2: "+ onlyInFeed2.mkString(", "))
  val commonFeeds = feeds1 .&(feeds2)
  println("common feeds: "+commonFeeds.mkString(", "))
  val urls  = feeds1 map("http://" + _)
  val arrayUrls = urls.toArray
  println("One url:"+arrayUrls(0))
  println("Refresh Feeds:")
  feeds1 foreach( feed => println(" Refreshing "+ feed))
  //-------------------------使用Map----------------------------------
  val feeds = Map("Andy Hunt" -> "blog.toolshed.com","Dave Thomas"
  -> "pragdave.pragprog.com","Dan Steinberg" -> "dimsumthinking.com/blog")
  val filterNameStartWithD = feeds filterKeys(_ startsWith "D")
  println("# of Filtered: "+ filterNameStartWithD.size)
  // key and vlue fileter miss
// errors
//  val filterKeyAndValue = feeds filter( element =>
//  val (key ,value)= element(key startsWith "D") && (value contains "blog")
//  )
  println("Get Andy's Feed: "+ feeds.get("Andy Hunt"))
  println("Get Bill's Feed: "+ feeds.get("Bill Who"))
  //use apply()
  try {
    println("Get Andy's Feed Using apply(): " + feeds("Andy Hunt"))
    print("Get Bill's Feed: ")
    println(feeds("Bill Who"))
  } catch {
    case ex: NoSuchElementException =>println("Not found"+ex.getMessage)
    case ex:Exception =>println("Exception"+ex.getMessage)
    case _ => println("ddd")
  }
  val newFeeds1 = feeds.updated("Venkat Subramainam","agiledeveloper.com/blpg")
  println("Venkat's blog in original feeds: "+ feeds.get("Venkat Subramainam"))
  println("Venkat's blog in new feed: "+newFeeds1("Venkat Subramainam"))

  val mutableFeeds = scala.collection.mutable.Map(
    "Scala Bool Forum" -> "forums.pragprog.com/forums/87")
  mutableFeeds("Groovy Book Forum") = "forums.pragprog.com/forums/55"
  println("Number of forums : "+ mutableFeeds.size)
//  ------------------------使用List--------------------------------------
  val feedsList = List("blog.toolshed.com","pragdave.pragprog.com","dismsumthinking.com/blog")
  println("First feed: "+ feedsList.head)
  println("Second feed: "+ feedsList(1))
  val prefixedList = "forums.pragprog.com/forums/87" :: feedsList
  println("First feed Ind Prefixed: "+prefixedList.head)
  val feedWithForums  = feedsList ::: List("forums.pragprog.com/forums/87","forums.pragprog.com.forums/55")
  println("First feed in feeds with forum: "+ feedWithForums.head)
  println("Last feed in feeds with forum: "+ feedWithForums.last)
  println("Feeds with blog: "+feedsList.filter(_ contains("blog")).mkString(", "))
  println("All feeds have com: "+ feedsList.forall(_ contains "com"))
  println("All feeds have dave: "+ feedsList.forall(_ contains("dave")))
  println("Any feed has dave: "+feedsList.exists(_ contains("dave")))
  println("Any feed has bill: "+feedsList.exists(_ contains("bill")))
  println("Feed url lengths: "+ feedsList.map(_.length))
//  val total = feedsList.foldLeft(0){ (total, feed) => total + feed.length}
//  val total = (0 /: feedsList){(total, feed)=> total + feed.length}
  val total = (0 /: feedsList){_ +_.length}
  println("Total length of feed urls: "+total)

  class Cow {
    def ^(moon :Moon) = println("Cow jumped over the moon")
  }
  class Moon{
    def ^:(cow: Cow) = println("This cow jumped over the moon too")
    def ha_:(cow: Cow) = println("ha: not allow")
  }
  val cow  = new Cow
  val moon = new Moon

  cow ^ moon
  cow ^: moon
  class Simple{
    def unary_+ = println("Called unary +")
    def unary_- = println("Called unary -")
    def unary_! = println("Called unary !")
    def unary_~ = println("Called unary ~")
  }
  val simple = new Simple
  +simple
  -simple
  !simple
  ~simple

  val result = for(i<- 1 to 10)
    yield i * 2
  println(result)//Vector(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
  val result2 = (1 to 10).map(_ * 2)
  println(result2)
  val doubleEven = for(i <- 1 to 10; if i % 2 == 0)
    yield i *2
  println(doubleEven)
  val doubleEven2  = for{
    i <- 1 to 10
    if i % 2 == 0
  }
    yield i * 2
  println(doubleEven2)
  class Person(val firstName:String, val lastName: String)
  object Person{
    def apply(firstName:String, lastName:String) : Person =
    new Person(firstName,lastName)
  }
  val friends = List(Person("Brain","Sletten"),Person("Neal","Ford"),
  Person("Scott","Davis"),Person("Stuart","Halloway"))
  val lastNames = for(friend <- friends; lastName = friend.lastName)
    yield  lastName
  println(lastNames.mkString(", "))
  for(i <- 1 to 3;j <- 4 to 6){
    print("["+i+","+j+"]")
  }
  def main(args: Array[String]) {

  }
}
