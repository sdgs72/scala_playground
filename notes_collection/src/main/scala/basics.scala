
/**
  * Created by dionhiananto on 5/15/17.
  */
class basics {



  //TRAIT OBJECT CLASS CASE ABSTRACT
  //http://stackoverflow.com/questions/1991042/what-is-the-advantage-of-using-abstract-classes-instead-of-traits
  //trait vs classes

  //objects are singleton,only one instance exists, they cannot be declared
  //they are kinda like static classes, see case
  object myExampleObject{}



  case class myCaseClassExample(x : Int) {  //case classes must accept constructors so it can be pattern matched

  }

  def namedParameterIsAlsoAnOption : myCaseClassExample = new myCaseClassExample(x = 5)

  //Companion object case class:
  //when you create a case class an object is automatically created behind the scenes,
  //Factory method class
  /*
  case object myCaseClassExample{
    def apply(x : Int) = new myCaseClassExample(x)
    //def apply(x : String) = new myCaseClassExample(100)
  }
  def myCaseClassCompanion : myCaseClassExample = myCaseClassExample("abc")
  */




  //type parameterization or generic type
  class myTypeParameterization[T] {

  }
  def myTypeParameterizationClass[Int] = new myTypeParameterization[Int]()


  //functions are objects in SCALA aswell as function type
  type functionType = Int => Int
  //this is equal to an object
  /*
  trait Function1[A,B]{
    def apply(x : A) : B
  }

  the 1 after the trait represents the number of parameters, there is 22 traits until Function22
  */




  //Tuple Tuples there are  tuples until 22
  def myTuple1 : Tuple3[Int,Char,Char] = Tuple3(1,'c','a')
  def myTuple2 : Tuple4[Int,Char,Char,Int] = Tuple4(4,'c','a',1)


  /*


    Streams are like lists but the tail is only evaluated on demand
    to speed things up and avoid memory overflow
    (
      example use case:
      find the 2nd prime number between 1000 and 10000
      (1000 until 10000) filter isPrime (1)
      this is a problem since we are creating a list of 9900 elements
      and only take two of the first primes

      stream vs list difference in cons implementation
        stream: def cons[T]( h : T , tail : => Stream[T]) => Stream[T]
        List: def cons[T]( h : T , tail : List[T]) => List[T]
        notice that stream's tail is implemented lazily =>


      stream uses lazy val so the tail doesn't get evaluated everytime
      def cons[T]( h : T , tail : => Stream[T]) => Stream[T] = {
        def head = hd
        lazy val tl = tail  //lazy evaluation
      }
    )
   */

  /*

  types of declaration lazy evaluation val variable var
  def -> for functions (call by name), evaluated everytime it is called
  val -> same as java's final (call by value), evaluated only once and evalated once the line is met  <<<< IF THE LINE IS READ IT IS RUN
  lazy  val -> combination of def and val, evaluated only once but only when it is called << LIKE DEF BUT COMPUTED ONLY ONCE
  var -> your normal variable that can be changed everytime

  def expression = {
    val x = { print("x"); 1}
    lazy val y = { print("y"); 2}
    def val z = { print("z"); 3}
    z + y + x + z +y + z
  }
  expression --> the result will be xzyz
   */



  val listExample: List[Int] = List(1,2,3,4,5)
  listExample.flatMap(f :)


}
