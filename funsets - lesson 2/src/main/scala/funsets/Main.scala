package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  val mySet = singletonSet(10)
  val newSet = addElements(singletonSet(1),2::3::4::Nil)
  printSet(diff(mySet,newSet))
  val builtSet = buildSet(2::3::4::5::100::Nil)
  printSet(builtSet)
}


class notes{
  /**
    *
    * Higher order functions
    *  accepts functions as a parameter
    *  meaning of Int => Int is a signature of type Integer that takes in one integer and returns an integer
    */
  def higherOrderFunction(func : Int => Int) : Int = {
    func(5)
  }

  def higherOrderFunction(func : (Int,Int) => Int) : Int = {
    func(5,10)
  }

  /**
    * anonymous function syntax
    *
    */
  def y :Int = ((x :Int,y :Int) => {x*x} : Int)(5,3)
  def x = () => {(false)} : Boolean //return type of unknown function


  /**
    * multiple parameter lists, can return a partial function, currying
    *
   */

  //This is different than if you do partialFunction(x : Int, y: Int))
  def partialFunction(x : Int)(y : Int) : Int = {
    x + y
  }

  //usage of multiple parameter lists
  def appliedX : Int => Int = partialFunction(5)
  def appliedXY : Int =  appliedX(3)

  /**
    * remember that function signatures are right associative
    * example Int => Int => Int will be processed as Int => (Int => Int)
    * The signature of the below function will be  (Int => Int ) => (Int,Int) => Int
    * in other words
    * (Int => Int ) => ( (Int,Int) => Int )
    * in other if you give the function the first parameter it will return a function
    * that takes in two parameters and return an integer
    *
    */
  def sum(f : Int => Int)(a : Int, b : Int) : Int = {
    if(a > b) 0
    else f(a) + sum(f)(a+1,b)
  }


  /**
    *
    *
    * rational class
    * use override to overide methods from the base object
    * use this to access this object
    */

  class Rational(x : Int,  y : Int){
    private def gcd(a : Int, b : Int) : Int = if (b == 0) a else gcd(b,a%b)
    private val gcdResult = gcd(numerator,denominator) // use call by value so it doesn't called everytime
    def numerator : Int = x / gcdResult //you can also use val numerator : Int so it doesn't get computed everytime
    def denominator : Int= y / gcdResult

    def this(x :Int) = this(x,1)  //other constructors

    def add(other : Rational): Rational ={
      new Rational(
       this.numerator + other.numerator,
        this.denominator + other.denominator
      )
    }


    /**
      *function names can be symbols/operator
      *the precedence of custom operator follows the ORIGINAL INTENDED USE OF IT
      * multiplacation is more prioritized than addition
      */
    //prefix operator
    def unary_- = this.numerator * -1

    //operator inflix
    def + (other : Rational) = this.numerator + other.numerator

    override def toString() = numerator + "/" + denominator
  }

  //use new to create a new object
  def rationalTest = new Rational(3,5)
  //use . to access properties like java
  rationalTest.numerator
  rationalTest.denominator
  rationalTest.add(rationalTest)

  //inflix operators
  rationalTest add rationalTest
  rationalTest + rationalTest
  -rationalTest
  /**
    * require and assertions
    * use
   */

  type myType = Int => Int

}