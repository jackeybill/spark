// 包你可以将代码组织在包里。
package com.tf.example

package object jackey {
  // 在文件头部定义包，会将文件中所有的代码声明在那个包中。
  // 值和函数不能在类或单例对象之外定义。单例对象是组织静态函数(static function)的有效工具。

  import akka.actor.{ Actor, ActorRef, ActorRefFactory, Props }
  import _root_.spray.http.HttpHeaders.Location
  import _root_.spray.http.StatusCodes.Created
  import _root_.spray.routing.{ Directives, RequestContext, Route }
  import Directives._

  // All classes from the java.lang package are imported by default, while others need to be imported explicitly.
  import java.util.{Date, Locale}
  import java.text.DateFormat
  import java.text.DateFormat._

  // 单例对象单例对象用于持有一个类的唯一实例。通常用于工厂模式。
  object FrenchDate {
    def main(args: Array[String]) {
      val now = new Date
      val df = getDateInstance(LONG, Locale.FRANCE)
      println(df format now) // <> df.format(now)

      // 直接访问这些成员
      println("the color is: " + com.tf.example.jackey.FrenchDate.toString() )

      // try也是面向表达式的 异常Scala中的异常可以在try-catch-finally语法中通过模式匹配使用。
      val result: Int = try {
        //remoteCalculatorService.add(1, 2)
        1
      } catch {
        case e: Exception => {
          //log.error(e, "the remote calculator service is unavailable. should have kept your trusty HP.")
          0
        }
      } finally {
        //remoteCalculatorService.close()
      }
    }
  }
  //特质（Traits）特质是一些字段和行为的集合，可以扩展或混入（mixin）你的类中。
  trait Ord {
    val name: String
    def test(that: Any): Boolean
  }

  trait CountMe {
    var num: Int
    def count(str: String): String
  }

  //通过with关键字，一个类可以扩展多个特质：constructor
  abstract class Continuation(run: PartialFunction[Any, Route], ctx: RequestContext) extends Actor with Ord with CountMe {
    //1.不变量（val）, 不能改变这个不变量的值.
    val two = 1 + 1
    //two: Int = 2

    //2.需要修改这个名称和结果的绑定，可以选择使用var
    var nameVar = "steve"
    //name: java.lang.String = steve

    nameVar = "jackey"
    //name: java.lang.String = jackey

    //3. 匿名函数
    (x: Int) => x + 1
    //res2: (Int) => Int = <function1>
    // 这个函数为名为x的Int变量加1。
    //res2(1)
    //res3: Int = 2

    //4.传递匿名函数，或将其保存成不变量。
    val addOne = (x: Int) => x + 1
    //addOne: (Int) => Int = <function1>
    addOne(1)
    // res4: Int = 2

    //5.如果你的函数有很多表达式，可以使用{}来格式化代码，使之易读。
    def timesTwo(i: Int): Int = {
      println("hello world")
      i * 2
    }
    // 对匿名函数也是这样的。
    { i: Int =>
      println("hello world")
      i * 2
    }
    //  res0: (Int) => Int = <function1>

    //6. 在将一个匿名函数作为参数进行传递时，这个语法会经常被用到。
    //   部分应用（Partial application）
    //   你可以使用下划线“_”部分应用一个函数，结果将得到另一个函数。Scala使用下划线表示不同上下文中的不同事物，你通常可以把它看作是一个没有命名的神奇通配符。在{ _ + 2 }的上下文中，它代表一个匿名参数。你可以这样使用它：
    def adder(m: Int, n: Int) = m + n
    //adder: (m: Int,n: Int)Int
    val add2 = adder(2, _:Int)
    //add2: (Int) => Int = <function1>
    add2(3)
    //res50: Int = 5

    //7.  你可以部分应用参数列表中的任意参数，而不仅仅是最后一个。
    //    柯里化函数有时会有这样的需求：允许别人一会在你的函数上应用一些参数，然后又应用另外的一些参数。
    //      例如一个乘法函数，在一个场景需要选择乘数，而另一个场景需要选择被乘数。
    def multiply(m: Int)(n: Int): Int = m * n
    // multiply: (m: Int)(n: Int)Int

    //你可以直接传入两个参数。
    multiply(2)(3)
    // res0: Int = 6

    //你可以填上第一个参数并且部分应用第二个参数。
    val timesTwo = multiply(2) _
    // timesTwo: (Int) => Int = <function1>

    //timesTwo(3)
    // res1: Int = 6

    //8. 你可以对任何多参数函数执行柯里化。例如之前的adder函数
    (adder _).curried
    // res1: (Int) => (Int) => Int = <function1>

    //9. 可变长度参数这是一个特殊的语法，可以向方法传入任意多个同类型的参数。例如要在多个字符串上执行String的capitalize函数，可以这样写：
    def capitalizeAll(args: String*) = {
      args.map { arg =>
        arg.capitalize
      }
    }

    capitalizeAll("rarity", "applejack")
    // res2: Seq[String] = ArrayBuffer(Rarity, Applejack)

    // val calc = new Calculator
    //  calc: Calculator = Calculator@e75a11

    // 颜色的值就是绑定在一个if/else表达式上的。Scala是高度面向表达式的：大多数东西都是表达式而非指令。
    var brand = "new"
    val color: String = if (brand == "TI") {
      "blue"
    } else if (brand == "HP") {
      "black"
    } else {
      "white"
    }
    // 当你可以调用一个不带括号的“函数”，但是对另一个却必须加上括号的时候，你可能会想哎呀，我还以为自己知道Scala是怎么工作的呢。也许他们有时需要括号？你可能以为自己用的是函数，但实际使用的是方法。
    // 在实践中，即使不理解方法和函数上的区别，你也可以用Scala做伟大的事情。如果你是Scala新手，而且在读两者的差异解释，你可能会跟不上。不过这并不意味着你在使用Scala上有麻烦。它只是意味着函数和方法之间的差异是很微妙的，只有深入语言内部才能清楚理解它。

    // 抽象类你可以定义一个抽象类，它定义了一些方法但没有实现它们。取而代之是由扩展抽象类的子类定义这些方法。你不能创建抽象类的实例。
    abstract class Shape {
      def getArea():Int    // subclass should define this
    }
    // defined class Shape
    class Circle(r: Int) extends Shape {
      def getArea():Int = { r * r * 3 }
    }
    // defined class Circle
    //不能创建抽象类的实例。
    //val s = new Shape
    // <console>:8: error: class Shape is abstract; cannot be instantiated

    val c = new Circle(2)
    // c: Circle = Circle@65c0035b

    //其实函数也可以是泛型的，来适用于所有类型。当这种情况发生时，你会看到用方括号语法引入的类型参数。下面的例子展示了一个使用泛型键和值的缓存。

    trait Cache[K, V] {
      def get(key: K): V
      def put(key: K, value: V)
      def delete(key: K)
    }
    //方法也可以引入类型参数。
    def remove[K](key: K)

    def receive = run andThen {
      case route =>
        try route(ctx)
        catch { case util.control.NonFatal(e) => ctx.failWith(e) }
        finally context.stop(self)
    }

    def test(that:Any): Boolean ={
      return false;
    }

    // apply 方法当类或对象有一个主要用途的时候，apply方法为你提供了一个很好的语法糖。
    class Tangle(r: Int) extends Shape {
      def getArea():Int = { r * r * 3 }
      def getShare():Int = { r * r * 3 }

      def apply() = new Circle(r)
    }

    // 实例化对象看起来像是在调用一个方法
    val newFoo = new Tangle(4)
    //newFoo: Tangle = Tangle@5b83f762

    //使用守卫进行匹配
    val times = 1
    times match {
      case i if i == 1 => "one"
      case i if i == 2 => "two"
      case _ => "some other number"
    }

    // 匹配类型你可以使用 match来分别处理不同类型的值。
    def bigger(o: Any): Any = {
      o match {
        case i: Int if i < 0 => i - 1
        case i: Int => i + 1
        case d: Double if d < 0.0 => d - 0.1
        case d: Double => d + 0.1
        case text: String => text + "s"
      }
    }

    //


  }

  object AppExample extends App {

    override def main(args: Array[String]) {
      // yield ??
      val res = for (a <- args) yield a.toUpperCase
      println("Arguments: " + res.toString)

      /** Basic command line parsing. */
      var verbose = false

      for (a <- args) a match {
        case "-h" | "-help"    =>
          println("Usage: scala Main [-help|-verbose]")
        case "-v" | "-verbose" =>
          verbose = true
        case x =>
          println("Unknown option: '" + x + "'")
      }
      if (verbose)
        println("How are you today?")
    }

    /* Adding ! as a method on int's */
    def fact(n: Int): BigInt =
      if (n == 0) 1 else fact(n-1) * n
    class Factorizer(n: Int) {
      def ! = fact(n)
    }
    implicit def int2fact(n: Int) = new Factorizer(n)
    println("10! = " + (10!))

    /* Defines a new method 'sort' for array objects */
    implicit def arrayWrapper[A : ClassManifest](x: Array[A]) =
      new {
        def sort(p: (A, A) => Boolean) = {
          util.Sorting.stableSort(x, p); x
        }
      }
    val x = Array(2, 3, 1, 4)
    println("x = "+ x.sort((x: Int, y: Int) => x < y))

    /** Maps are easy to use in Scala. */
    object Maps {
      val colors = Map("red" -> 0xFF0000,
        "turquoise" -> 0x00FFFF,
        "black" -> 0x000000,
        "orange" -> 0xFF8040,
        "brown" -> 0x804000)
      def main(args: Array[String]) {
        for (name <- args) println(
          colors.get(name) match {
            case Some(code) =>
              name + " has code: " + code
            case None =>
              "Unknown color: " + name
          }
        )
      }
    }

    /** Print prime numbers less than 100, very inefficiently */
    def isPrime(n: Int) = (2 until n) forall (n % _ != 0)
    for (i <- 1 to 100 if isPrime(i)) println(i)

    //



  }

  object Continuation {
    def props(run: PartialFunction[Any, Route], ctx: RequestContext): Props =
      Props(classOf[Continuation], run, ctx)
  }

  class AndThen(message: Any, ref: ActorRef, factory: ActorRefFactory) {
    def -!>(run: PartialFunction[Any, Route])(ctx: RequestContext): Unit = {
      val continuation = factory actorOf Continuation.props(run, ctx)
      ref.tell(message, continuation)
    }
  }

  implicit class MessageOps(message: Any)(implicit factory: ActorRefFactory) {
    def -!>(ref: ActorRef): AndThen = new AndThen(message, ref, factory)
  }

  def created(location: String): Route =
    respondWithSingletonHeader(Location(location)) {
      complete { Created }
    }

}
