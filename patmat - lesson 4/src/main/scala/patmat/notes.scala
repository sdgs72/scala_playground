package patmat

/**
  * Created by dionhiananto on 5/13/17.
  */
class notes {


  //EVERYTHING IS CLASSES IN CLASS, but some of them are treated uniquely
  //for example the Int type is a class in Scala but when it is compiled,
  //it is converted into JVM's Int type so it won't hit performance,
  //you can even extend the Int class

  //ALL primitive types are also treated as classes
  /*
  final abstract class Int private extends AnyVal {

  class myInt extends Int{

  }
  */


  //functions and other primitive types can be represented as classes
  //example: how to implement function
  trait function[A,B]{  // A => B
    def apply(x : A) : B
  }

  trait interface2{

  }

  //declaring functions Int => Double
  class myFunc extends function[Int,Double]{
    def apply(x : Int): Double ={
      x*x
    }
  }

  //anonymous class syntax(can be used on abstract classes/interface)
  new function[Int,Double] with interface2{
    def apply(x : Int) : Double ={
      x*x
    }
  }

  val f = new myFunc
  f.apply(7)  // same as f = (x : Int) => x*x; f(7)


  /*
  Covariance problem in java
  Child1 [] arr1 = new Child1[10]
  Parent [] arr2 = arr1     //Java allows this but scala doesn't allow this
  arr2[0] = new Child2()
  arr1.get(0) <<<< ????? crash type???

  SCALA DOESNT ALLOW COVARIANCE TYPE
  so Array[Parent] is not a subtype of Array[Child] they are their own types
   */


  //TYPE BOUNDS
  class grandParent{

  }
  class parent extends grandParent{

  }
  class child1 extends parent{

  }
  class child2 extends parent{

  }

  def returnSubtype[S <: parent](param1 : S) : S ={
    param1
  }//this means that param1 must be a subtype of parent
  //and can be either child1 or child2 in this case

  def returnSuperType[S >: child1](param1 : S) : S= {
      param1
  } // only accept super type of child1 which means, only accepts parent in this case

  def tightBound[S >: child1 <: grandParent](param1 : S) : S = {
    param1
  }

  /*
  def x
  x.isInstanceOf[Int] // same as x instanceOf T in java returns boolean
  x.asInstanceOf[Int] // same  (Int)x in java treats it as Int if it is an error it will throw an exception
  //^^ same as type casting
  */

  //pattern matching
  //declare the class using case
  abstract class expr{
    def eval : Int = this match {
      case Number(n) => n
      case Sum(e1,e2) => e1.eval + e2.eval
      case _ => 0
    }

  }
  case class Number(x : Int) extends expr{

  }

  //this will create an object behind the scenes
  //so that we can just call Number(x) instead of new Number(x) for pattern matching
  /*
  object Number{
    def apply(x : Int) = new Number(x)
  }


   */


  case class Sum(e1 : expr , e2 :expr) extends expr{

  }

  /*
    List Convention:
    1 :: 2 :: 3 :: Nil will be evaluated as 1 :: (2 :: (3 :: Nil))

  */
  def recurseList (x : List[Int]) : Unit = {

  }


}
