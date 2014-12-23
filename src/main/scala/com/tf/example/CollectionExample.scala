package com.tf.example

case class CollectionExample(interface: String = "localhost", port: Int = 8080)

object CollectionExample {
  //列表 List. Public是Scala的缺省访问级别。
  val numbers = List(1, 2, 3, 4)
  //numbers: List[Int] = List(1, 2, 3, 4)

  //集 Set集没有重复
  Set(1, 1, 2)
  //res0: scala.collection.immutable.Set[Int] = Set(1, 2)

  //元组 Tuple元组是在不使用类的前提下，将元素组合起来形成简单的逻辑集合。
  val hostPort = ("localhost", 80)
  // hostPort: (String, Int) = (localhost, 80)
  //与样本类不同，元组不能通过名称获取字段，而是使用位置下标来读取对象；而且这个下标基于1，而不是基于0。
  hostPort._1
  //res0: String = localhost
  hostPort._2
  //res1: Int = 80

  //元组可以很好得与模式匹配相结合。
  hostPort match {
    case ("localhost", port) => {}
    case (host, port) => {}
  }
  // 在创建两个元素的元组时，可以使用特殊语法：->
  1 -> 2
  //res0: (Int, Int) = (1,2)

  //映射 Map它可以持有基本数据类型。第一个参数是映射的键，第二个参数是映射的值。映射的值可以是映射甚或是函数。
  //Scala里允许你对任何对象调用->的机制被称为隐式转换
  Map(1 -> 2)
  Map("foo" -> "bar")
  Map(1 -> Map("foo" -> "bar"))
  Map("timesTwo" -> { CollectionExample(_,_) })
  Map((1, "one"), (2, "two"))

  // Map.get 使用 Option 作为其返回值，表示这个方法也许不会返回你请求的值 选项 OptionOption 是一个表示有可能包含值的容器。
  // Option基本的接口是这样的：Option本身是泛型的，并且有两个子类： Some[T] 或 None
  trait Option[T] {
    def isDefined: Boolean
    def get: T
    def getOrElse(t: T): T
  }

  val numbers = Map(1 -> "one", 2 -> "two")
  //numbers: scala.collection.immutable.Map[Int,String] = Map((1,one), (2,two))
  numbers.get(2)
  //res0: Option[java.lang.String] = Some(two)
  // getOrElse 让你轻松地定义一个默认值。
  numbers.get(3).getOrElse(0) * 2
  //res1: Option[java.lang.String] = None

  // 函数组合子（Functional Combinators）List(1, 2, 3) map squared对列表中的每一个元素都应用了squared平方函数，
  // 并返回一个新的列表List(1, 4, 9)。我们称这个操作map 组合子。 （如果想要更好的定义，你可能会喜欢Stackoverflow上对组合子的说明。）他们常被用在标准的数据结构上。
  // mapmap对列表中的每个元素应用一个函数，返回应用后的元素所组成的列表。
  numbers.map((i: Int) => i * 2)
  //res0: List[Int] = List(2, 4, 6, 8)
  //或传入一个部分应用函数
  def timesTwo(i: Int): Int = i * 2
  //timesTwo: (i: Int)Int
  numbers.map(timesTwo _)
  //res0: List[Int] = List(2, 4, 6, 8)

  //foreach很像map，但没有返回值。foreach仅用于有副作用[side-effects]的函数。
  val doubled = numbers.foreach((i: Int) => i * 2)
  // doubled: Unit = ()

  //filter移除任何对传入函数计算结果为false的元素。返回一个布尔值的函数通常被称为谓词函数[或判定函数]。
  numbers.filter((i: Int) => i % 2 == 0)
  //res0: List[Int] = List(2, 4)
  def isEven(i: Int): Boolean = i % 2 == 0
  numbers.filter(isEven _)
  //res2: List[Int] = List(2, 4)

  //zip将两个列表的内容聚合到一个对偶列表中。
  List(1, 2, 3).zip(List("a", "b", "c"))
  //res0: List[(Int, String)] = List((1,a), (2,b), (3,c))

  //partition将使用给定的谓词函数分割列表。
  val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  numbers.partition(_ % 2 == 0)
  //res0: (List[Int], List[Int]) = (List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))

  //find返回集合中第一个匹配谓词函数的元素。
  numbers.find((i: Int) => i > 5)
  //res0: Option[Int] = Some(6)

  //drop 将删除前i个元素
  numbers.drop(5)
  //res0: List[Int] = List(6, 7, 8, 9, 10)

  // dropWhile 将删除元素直到找到第一个匹配谓词函数的元素。
  // 例如，如果我们在numbers列表上使用dropWhile奇数的函数, 1将被丢弃（但3不会被丢弃，因为他被2“保护”了）。
  numbers.dropWhile(_ % 2 != 0)
  //res0: List[Int] = List(2, 3, 4, 5, 6, 7, 8, 9, 10)

  // foldLeft
  numbers.foldLeft(0)((m: Int, n: Int) => m + n)
  // res0: Int = 55

  // 0为初始值（记住numbers是List[Int]类型），m作为一个累加器。直接观察运行过程：
  numbers.foldLeft(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
  /*
  m: 0 n: 1
  m: 1 n: 2
  m: 3 n: 3
  m: 6 n: 4
  m: 10 n: 5
  m: 15 n: 6
  m: 21 n: 7
  m: 28 n: 8
  m: 36 n: 9
  m: 45 n: 10
  res0: Int = 55


  foldRight和foldLeft一样，只是运行过程相反。
  scala> numbers.foldRight(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
  m: 10 n: 0
  m: 9 n: 10
  m: 8 n: 19
  m: 7 n: 27
  m: 6 n: 34
  m: 5 n: 40
  m: 4 n: 45
  m: 3 n: 49
  m: 2 n: 52
  m: 1 n: 54
  res0: Int = 55
  */

  //flatten将嵌套结构扁平化为一个层次的集合。
  List(List(1, 2), List(3, 4)).flatten
  //res0: List[Int] = List(1, 2, 3, 4)

  //flatMap是一种常用的组合子，结合映射[mapping]和扁平化[flattening]。 flatMap需要一个处理嵌套列表的函数，然后将结果串连起来。
  val nestedNumbers = List(List(1, 2), List(3, 4))
  //nestedNumbers: List[List[Int]] = List(List(1, 2), List(3, 4))
  nestedNumbers.flatMap(x => x.map(_ * 2))
  //res0: List[Int] = List(2, 4, 6, 8)
  //可以把它看做是“先映射后扁平化”的快捷操作：先调用map，然后可以马上调用flatten，这就是“组合子”的特征，也是这些函数的本质。
  nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten
  //res1: List[Int] = List(2, 4, 6, 8)
  def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
    numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) =>
      fn(x) :: xs
    }
  }
  ourMap(numbers, timesTwo(_))
  //res0: List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

  //为什么是List[Int]()？Scala没有聪明到理解你的目的是将结果积聚在一个空的Int类型的列表中。
  //Map?所有展示的函数组合子都可以在Map上使用。Map可以被看作是一个二元组的列表，所以你写的函数要处理一个键和值的二元组。
  val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
  //extensions: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101), (joe,201))
  //现在筛选出电话分机号码低于200的条目。
  //extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200)
  //res0: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101))
  //因为参数是元组，所以你必须使用位置获取器来读取它们的键和值。我们其实可以使用模式匹配更优雅地提取键和值。
  //extensions.filter({case (name, extension) => extension < 200})
  //res0: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101))

  //函数组合让我们创建两个函数：
  def f(s: String) = "f(" + s + ")"
  def g(s: String) = "g(" + s + ")"
  //compose 组合其他函数形成一个新的函数 f(g(x))
  val fComposeG = f _ compose g _
  //fComposeG: (String) => java.lang.String = <function>
  fComposeG("yay")
  // res0: java.lang.String = f(g(yay))

  //andThen 和 compose很像，但是调用顺序是先调用第一个函数，然后调用第二个，即g(f(x))
  val fAndThenG = f _ andThen g _
  //  fAndThenG: (String) => java.lang.String = <function>
  fAndThenG("yay")
  // res1: java.lang.String = g(f(yay))

  /*
      柯里化 vs 偏应用case 语句那么究竟什么是case语句？这是一个名为PartialFunction的函数的子类。
      多个case语句的集合是什么？他们是共同组合在一起的多个PartialFunction。
      理解PartialFunction(偏函数)对给定的输入参数类型，函数可接受该类型的任何值。换句话说，一个(Int) => String 的函数可以接收任意Int值，并返回一个字符串。
      对给定的输入参数类型，偏函数只能接受该类型的某些特定的值。一个定义为(Int) => String 的偏函数可能不能接受所有Int值为输入。
      isDefinedAt 是PartialFunction的一个方法，用来确定PartialFunction是否能接受一个给定的参数。
      注意 偏函数PartialFunction 和我们前面提到的部分应用函数是无关的。
  */
  val one: PartialFunction[Int, String] = { case 1 => "one" }
  //one: PartialFunction[Int,String] = <function1>
  one.isDefinedAt(1)
  //res0: Boolean = true
  one.isDefinedAt(2)
  //res1: Boolean = false
  // 您可以调用一个偏函数。
  one(1)
  //  res2: String = one

  // PartialFunctions可以使用orElse组成新的函数，得到的PartialFunction反映了是否对给定参数进行了定义。
  val two: PartialFunction[Int, String] = { case 2 => "two" }
  val three: PartialFunction[Int, String] = { case 3 => "three" }
  val wildcard: PartialFunction[Int, String] = { case _ => "something else" }
  val partial = one orElse two orElse three orElse wildcard
  //partial: PartialFunction[Int,String] = <function1>
  partial(5)
  // res24: String = something else
  partial(3)
  // res25: String = three

  // case 之谜上周我们看到一些新奇的东西。我们在通常应该使用函数的地方看到了一个case语句。
  case class PhoneExt(name: String, ext: Int)
  val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))
  extensions.filter { case PhoneExt(name, extension) => extension < 200 }
  //res0: List[PhoneExt] = List(PhoneExt(steve,100))

  /*
  什么是静态类型？它们为什么有用？按Pierce的话讲：“类型系统是一个语法方法，它们根据程序计算的值的种类对程序短语进行分类，通过分类结果错误行为进行自动检查。”
  类型允许你表示函数的定义域和值域。例如，从数学角度看这个定义：
  f: R -> N

  它告诉我们函数“f”是从实数集到自然数集的映射。
  抽象地说，这就是 具体 类型的准确定义。类型系统给我们提供了一些更强大的方式来表达这些集合。
  鉴于这些注释，编译器可以 静态地 （在编译时）验证程序是 合理 的。也就是说，如果值（在运行时）不符合程序规定的约束，编译将失败。
  一般说来，类型检查只能保证 不合理 的程序不能编译通过。它不能保证每一个合理的程序都 可以 编译通过。
  随着类型系统表达能力的提高，我们可以生产更可靠的代码，因为它能够在我们运行程序之前验证程序的不变性（当然是发现类型本身的模型bug！）。学术界一直很努力地提高类型系统的表现力，包括值依赖（value-dependent）类型！
  需要注意的是，所有的类型信息会在编译时被删去，因为它已不再需要。这就是所谓的擦除。
  Scala中的类型Scala强大的类型系统拥有非常丰富的表现力。其主要特性有：

  * 参数化多态性 粗略地说，就是泛型编程
  * （局部）类型推断 粗略地说，就是为什么你不需要这样写代码val i: Int = 12: Int
  * 存在量化 粗略地说，为一些没有名称的类型进行定义
  * 视窗 我们将下周学习这些；粗略地说，就是将一种类型的值“强制转换”为另一种类型

  参数化多态性多态性是在不影响静态类型丰富性的前提下，用来（给不同类型的值）编写通用代码的。
  例如，如果没有参数化多态性，一个通用的列表数据结构总是看起来像这样（事实上，它看起来很像使用泛型前的Java）：

  List最常用的操作符是发音为“cons”的‘::’。Cons把一个新元素组合到已有List的最前端，然后返回结果List。
  类List没有提供append操作，因为随着列表变长append的耗时将呈线性增长，而使用::做前缀则仅花费常量时间
  */
  2 :: 1 :: "bar" :: "foo" :: Nil
  // res5: List[Any] = List(2, 1, bar, foo)

  //现在我们无法恢复其中成员的任何类型信息。
  //res5.head
  // res6: Any = 2

  // List有个叫“:::”的方法实现叠加功能
  val oneTwo = List(1, 2)
  val threeFour = List(3, 4)
  val oneTwoThreeFour = oneTwo ::: threeFour
  println(oneTwo + " and " + threeFour + " were not mutated.")
  println("Thus, " + oneTwoThreeFour + " is a new List.")
  //List(1, 2) and List(3, 4) were not mutated.
  //Thus, List(1, 2, 3, 4) is a new List.

  //所以我们的应用程序将会退化为一系列类型转换（“asInstanceOf[]”），并且会缺乏类型安全的保障（因为这些都是动态的）。
  //多态性是通过指定 类型变量 实现的。
  def drop1[A](l: List[A]) = l.tail
  //drop1: [A](l: List[A])List[A]

  drop1(List(1,2,3))
  //res1: List[Int] = List(2, 3)

  //Scala有秩1多态性粗略地说，这意味着在Scala中，有一些你想表达的类型概念“过于泛化”以至于编译器无法理解。假设你有一个函数
  def toList[A](a: A) = List(a)
  //你希望继续泛型地使用它：
  def foo[A, B](f: A => List[A], b: B) = f(b)
  //这段代码不能编译，因为所有的类型变量只有在调用上下文中才被固定。即使你“钉住”了类型B：
  def foo[A](f: A => List[A], i: Int) = f(i)

  //Scala类型推断系统的实现稍有不同，但本质类似：推断约束，并试图统一类型。
  //在Scala中所有类型推断是 局部的 。Scala一次分析一个表达式。例如：
  def id[T](x: T) = x
  //  id: [T](x: T)T
  val x = id(322)
  //  x: Int = 322
  val x = id("hey")
  //  x: java.lang.String = hey
  val x = id(Array(1,2,3,4))
  //  x: Array[Int] = Array(1, 2, 3, 4)

  //  类型信息都保存完好，Scala编译器为我们进行了类型推断。请注意我们并不需要明确指定返回类型。
  //  变性 VarianceScala的类型系统必须同时解释类层次和多态性。类层次结构可以表达子类关系。
  //  在混合OO和多态性时，一个核心问题是：如果T’是T一个子类，Container[T’]应该被看做是Container[T]的子类吗？变性（Variance）注解允许你表达类层次结构和多态类型之间的关系：
  //  含义Scala 标记协变covariantC[T’]是 C[T] 的子类[+T]逆变contravariantC[T] 是 C[T’]的子类[-T]不变invariantC[T] 和 C[T’]无关[T]子类型关系的真正含义：对一个给定的类型T，如果T’是其子类型，你能替换它吗？
  class Covariant[+A]
  //defined class Covariant

  val cv: Covariant[AnyRef] = new Covariant[String]
  //cv: Covariant[AnyRef] = Covariant@4035acf6
  //val cv: Covariant[String] = new Covariant[AnyRef]
  //  <console>:6: error: type mismatch;
  class Contravariant[-A]
  val cv: Contravariant[String] = new Contravariant[AnyRef]
  //    cv: Contravariant[AnyRef] = Contravariant@49fa7ba

  //val fail: Contravariant[AnyRef] = new Contravariant[String]
  //    <console>:6: error: type mismatch;
        ^
  /*
        逆变似乎很奇怪。什么时候才会用到它呢？令人惊讶的是，函数特质的定义就使用了它！
        trait Function1 [-T1, +R] extends AnyRef

        如果你仔细从替换的角度思考一下，会发现它是非常合理的。让我们先定义一个简单的类层次结构：
        scala> class Animal { val sound = "rustle" }
        defined class Animal

        scala> class Bird extends Animal { override val sound = "call" }
        defined class Bird

        scala> class Chicken extends Bird { override val sound = "cluck" }
        defined class Chicken

        假设你需要一个以Bird为参数的函数：
        scala> val getTweet: (Bird => String) = // TODO

        标准动物库有一个函数满足了你的需求，但它的参数是Animal。在大多数情况下，如果你说“我需要一个___，我有一个___的子类”是可以的。但是，在函数参数这里是逆变的。如果你需要一个接受参数类型Bird的函数变量，但却将这个变量指向了接受参数类型为Chicken的函数，那么给它传入一个Duck时就会出错。然而，如果将该变量指向一个接受参数类型为Animal的函数就不会有这种问题：
        scala> val getTweet: (Bird => String) = ((a: Animal) => a.sound )
        getTweet: Bird => String = <function1>

        函数的返回值类型是协变的。如果你需要一个返回Bird的函数，但指向的函数返回类型是Chicken，这当然是可以的。
        scala> val hatch: (() => Bird) = (() => new Chicken )
        hatch: () => Bird = <function0>

          边界Scala允许你通过 边界 来限制多态变量。这些边界表达了子类型关系。
          scala> def cacophony[T](things: Seq[T]) = things map (_.sound)
          <console>:7: error: value sound is not a member of type parameter T
            def cacophony[T](things: Seq[T]) = things map (_.sound)
            ^

            scala> def biophony[T <: Animal](things: Seq[T]) = things map (_.sound)
            biophony: [T <: Animal](things: Seq[T])Seq[java.lang.String]

              scala> biophony(Seq(new Chicken, new Bird))
              res5: Seq[java.lang.String] = List(cluck, call)

              类型下界也是支持的，这让逆变和巧妙协变的引入得心应手。List[+T]是协变的；一个Bird的列表也是Animal的列表。List定义一个操作::(elem T)返回一个加入了elem的新的List。新的List和原来的列表具有相同的类型：
              scala> val flock = List(new Bird, new Bird)
              flock: List[Bird] = List(Bird@7e1ec70e, Bird@169ea8d2)

              scala> new Chicken :: flock
              res53: List[Bird] = List(Chicken@56fbda05, Bird@7e1ec70e, Bird@169ea8d2)

              List 同样 定义了::[B >: T](x: B) 来返回一个List[B]。请注意B >: T，这指明了类型B为类型T的超类。这个方法让我们能够做正确地处理在一个List[Bird]前面加一个Animal的操作：
              scala> new Animal :: flock
              res59: List[Animal] = List(Animal@11f8d3a8, Bird@7e1ec70e, Bird@169ea8d2)

              注意返回类型是Animal。
              量化有时候，你并不关心是否能够命名一个类型变量，例如：
              scala> def count[A](l: List[A]) = l.size
              count: [A](List[A])Int

              这时你可以使用“通配符”取而代之：
              scala> def count(l: List[_]) = l.size
              count: (List[_])Int

              这相当于是下面代码的简写：
              scala> def count(l: List[T forSome { type T }]) = l.size
              count: (List[T forSome { type T }])Int

              注意量化会的结果会变得非常难以理解：
              scala> def drop1(l: List[_]) = l.tail
              drop1: (List[_])List[Any]

              突然，我们失去了类型信息！让我们细化代码看看发生了什么：
              scala> def drop1(l: List[T forSome { type T }]) = l.tail
              drop1: (List[T forSome { type T }])List[T forSome { type T }]

              我们不能使用T因为类型不允许这样做。
              你也可以为通配符类型变量应用边界：
              scala> def hashcodes(l: Seq[_ <: AnyRef]) = l map (_.hashCode)
                hashcodes: (Seq[_ <: AnyRef])Seq[Int]

                  scala> hashcodes(Seq(1,2,3))
                  <console>:7: error: type mismatch;
                    found   : Int(1)
                    required: AnyRef
                    Note: primitive types are not implicitly converted to AnyRef.
                    You can safely force boxing by casting x.asInstanceOf[AnyRef].
                    hashcodes(Seq(1,2,3))
                    ^

                    scala> hashcodes(Seq("one", "two", "three"))
                    res1: Seq[Int] = List(110182, 115276, 110339486)
  */

  def apply(args: Seq[String]): CollectionExample = {
    @annotation.tailrec
    def go(flags: CollectionExample, args: Seq[String]): CollectionExample = args match {
      case ("-p" | "--port") +: port +: tail =>
        go(flags.copy(port = port.toInt), tail)
      case unknown +: tail =>
        throw new IllegalArgumentException("Unknown flag: %s".format(unknown))
      case _ =>
        flags
    }
    go(CollectionExample(), args)
  }
}
