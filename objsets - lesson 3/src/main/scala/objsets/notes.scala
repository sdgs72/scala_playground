package objsets

//too import classes from other
/**
  * import sample.TargetObject
  * import sample.{Object1,Object2}
  * import sample._ //import all objects
  */

/*
class notes {

  A val is similar to a final
  variable in Java. Once initialized, a val can never be reassigned. A var, by
  contrast, is similar to a non-final variable in Java.

  val vs var vs def

  val -> java's final variable , you cannot change this
  var -> java's variable, it is mutable
  def -> starts a function definition


  class test(z : Int){

    def x: Int = z

  }

  /**
    * abstract classes syntax abstract class name
    * it has the same behaviour as java abstract classes in a sense that
    * 1. it can't be instantiated(newed)
    * 2. you must left out the functions as unimplemented
    * 2. you can't have concrete functions
    * 3. you can have constant values
    * CAN HAVE PARAMETERS
    */
  abstract class IntSet{
    def foo :Int = 5
    def contains(x : Int) : Boolean
    def incl(x : Int) : IntSet
    def union(other : IntSet) : IntSet
  }

  //if you have fields use class EmptySet(x : Int) extends IntSet
  //you can also use object since there is no need to instantiate emptySet multiple times(like python's Nil)
  class EmptySet extends IntSet{  //the leaf node

    def contains(x : Int) : Boolean = {
      false
    }

    def incl(x : Int) : IntSet = {
      new NonEmptySet(x,new EmptySet,new EmptySet)
    }

    def union(other : IntSet) : IntSet = other
  }

  class NonEmptySet(elem : Int, left : IntSet,right : IntSet ) extends IntSet{  //the inner node

    def contains(x : Int) : Boolean = {
      if (elem < x) left.contains(x)
      else if (elem > x) right contains x //scala inflix syntax
      else true
    }

    def incl(x : Int) : IntSet = {
      if (elem < x) new NonEmptySet(elem,left incl x,right)
      else if (elem > x) new NonEmptySet(elem,left,right.incl(x))
      else new EmptySet
    }

    def union(other : IntSet)  : IntSet = {
      //  ( (left union right) union other) incl elem
      left.union(right).union(other).incl(elem)
    }
  }


  //must use OVERRIDE keyword EXPLICITLY when overriding functions,otherwise compiler error
  class overrideExample extends EmptySet{
    override def contains(x: Int) : Boolean = true

  }

  /**
    *
    * objects are singleton classes, they will be created automatically once referenced
    * "Somewhat static classes"
    * only 1 instance of this class can exist
    */
  object EmptySet2 extends IntSet{
    def contains(x : Int) : Boolean = {
      false
    }

    def incl(x : Int) : IntSet = {
      new NonEmptySet(x,EmptySet2,EmptySet2)  //don't use new since EmptySet2 is scala object
    }

    def union(other : IntSet) : IntSet = other

  }


  /**
    * Traits are Scala's way to support multiple inheritence
    * Traits are the same as java's interface but stronger
    * Traits can also have concrete functions
    * use extends if it is the first trait
    * use with keyword for more than one trait/abstract classes
    * CANNOT HAVE PARAMETERS
    */
  trait ExampleTrait{
    def traitIsJavaInterface(x : String) : String
    def butTheFunctionCanBeConcrete(x : String) : String = {
      ""
    }
  }

  trait ExampleTrait2{}

  abstract class ExampleAbstract{}


  //use extend to inherit the first class otherwise use with
  class implementTrait extends ExampleAbstract with ExampleTrait{
    def traitIsJavaInterface(x : String) : String = {
      ""
    }
  }
  //to implement an trait as it's only implementation use extend key word
  class implementTrait2 extends ExampleTrait2 {}

  class implementTrait3  extends ExampleTrait with ExampleTrait2{
    def traitIsJavaInterface(x : String) : String = {
      ""}

  }

  class implementTrait4 extends implementTrait2{}


  /**
    *   Scala's Class Hierarchy
    *
    *   all types/objects/PRIMITIVE TOO/ANYTHING is a subtype/inherited from scala.Any
    *   any primitive type(Int,Boolean) is a subtype/inherited from scala.AnyVal
    *   any reference type(List,String) is a subtype/inherited from scala.AnyRef (alias of java.lang.Object)
    *
    *   Nothing(scala.Nothing) <<< from all objects/ANYTHING/PRIMITIVE TOO
    *   Null(scala.Null)  <<< inherited from all reference objects
    */

  //use AnyVal to return you can also use Any
  def returnIntOrBool : AnyVal= {
    if(true) 1 else false
  }

  def returnIntOrBoolAny : Any= {
    if(true) 1 else false
  }

  /**
    * Typed parameters use []
    * example below is your ConsList (same as in Lisp)
    */
  trait List[T]{
    def isEmpty : Boolean
    def head : T
    def tail : List[T]
  }

  /**
    * notice that the declaration of the class is using val,
    * this is the same as declaring a field in the class
    *
    */
  class Cons[T](val head : T, val tail : List[T]) extends List[T]{
    def isEmpty : Boolean = false
    //when using val parameters, it will be same as having a val field
    //val head = head
    //def head : T = _head
    //def tail : List[T] = _tail

  }

  class NillCons[T]extends List[T]{
    def isEmpty : Boolean = false
    def head : T = throw Error
    def tail : List[T] = throw Error
  }

  def singleton[Int] (x : Int) : List[Int] = new NillCons[Int]
  //you don't have to declare the generic type, scala can do it for you
  def singletonLazy[Int] (x: Int) : List[Int] = new NillCons[Int]

}


*/