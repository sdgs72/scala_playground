package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }


  /**
   * Exercise 1
    * * finds the value of the pascal triangle at a column and row
    *    1  -> (0,0)
    * 0 1 1 0-> (0,1) , (1,1)
    * 0 1 2 1  -> (0,2) , (1,2) , (2,2)
    * 1 3 3 1 -> (0,3) , (1,3) , (2,3) , (3,3)
    * column and row starts at 0
   */
    def pascal(c: Int, r: Int): Int = {
        if(c == 0)
          1
        else if(c < 0 || c > r || r == 0)
          0
        else
          pascal(c-1,r-1) + pascal(c,r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def balanceIsHelper(chars : List[Char],bracketCount : Int) : Boolean = {
        if(bracketCount < 0)
          false
        else if(chars.isEmpty && bracketCount == 0)
          true
        else
          {
            if(chars.head == ')')
              balanceIsHelper(chars.tail,bracketCount-1)
            else if(chars.head == '(')
              balanceIsHelper(chars.tail,bracketCount+1)
            else
              balanceIsHelper(chars.tail,bracketCount)
          }
      }
      balanceIsHelper(chars,0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {

      def coinHelper(money: Int, coins: List[Int]) : Int = {
        if(money == 0)
           1
        else if(coins.isEmpty || money < 0)
           0
        else
            coinHelper(money-coins.head,coins) +
            coinHelper(money,coins.tail)
      }
      coinHelper(money,coins)
    }

  }

class notes{

  /**
    * Call by value vs Call by Name
    * Call By Value >>> the function parameter's will be evaluated "FIRST" before the function is executed no matter what
    * Call by Name(Lazy) >>> The function paramter's will left as is until it is used in the function
    *
    * One major difference between CBV and CBN are terminations, if a CBV is terminated then CBN will also terminate
    * BUT if CBN terminates CBV might not terminate(due to the nature that CBN can skip some parameter from being evaluated
    * while CBV always evalutates all parameters)s
    *
    *
    * Scala usually use call by value
    * why? (in practice call by value is usually more efficient, parameters
    * not repetitively recalculated)
    *
    */

  def NoReturnType(x :Int) = "No Return Type"
  def helloIamCallByName : Int = 5
  val helloIamCallByValue : Int = 5
  def paramaterCall(x :Int , y: => Int) : String = "x will be call by value,y will be evaluated using call by name aka evaluated every time"
  def loop:Boolean = loop


  /**
    *  If else are expressions in Scala not statements(set of instructions)
    *  && || uses short circuit
    *
    */

  def ifElse = if(true) 3*3 else 3

  /**
    * examples of when call by name parameters are used, in AND and OR functions,
    * it short circuits y and also saves some performance, y doesn't need to be calculated at some instance
    *
    */

  def and(x:Boolean, y : => Boolean) = if(x) y else false
  def or(x:Boolean, y : => Boolean) = if(x) true else y

  /**
    * objects in scala are singletons
    * classes are classes
    */

  /**
    *
    *
    * Nested function to avoid namespace pollution, uses blocks , blocks are expressions and
    * contains a sequence of expressions or definitions
    *
    *  Blocks in scala starts with { and ends with }
    *  Blocks are used to limit scoping(visiblity like your normal programming language)
    */

  def function(x : Integer) = {
    def helper1() = {
      1
    }
    def helper2() = 2
    def helper3() = {
      5
    }
    helper1()+helper2() + helper3()
  }

  {
    def iAmBlock = 3 //i am block is only accessible inside this block
    //semi colon is used for several expressions in a single line
    def hoho = 3;3;3;4+5
    //hoho will be 9
  }

  /**
    * Tail recursion
    * If a function ONLY CALLS A FUNCTION(MAYBE THE SAME FUNCTION) AS THE LAST ACTION than it is a tail recursive call
    * no additional stack space will be used. avoid stackoverflow error
    * use @tailrec to indicate tail recursion.
    *
    *
    * how to achieve this?(if your recursive function doesn't have any need to store any "local variables" then
    * there is no need to use an aditional stack frame.
    * example
    */

    def factorial(x:Int) : Int = {
      if(x == 0)
        1
      else
        x*factorial(x-1) // after hitting the base case need to account the x * so this is not efficeint, we need to store
                        // an additional stack frame for each call
                        //it has x * function
    }

    //use accumulator to be more efficient
   def factorialTailed(x:Int) : Int = {
      @tailrec
      def factorialHelper(x:Int, accu:Int) : Int = {
        if (x == 0)
          accu
        else
          factorialHelper(x - 1, accu * 1)  //no need to store the previous stack frame since everyting is "carried on" by
                                            //the accumulator parameter
                                            //only calls a function so it is now a recursive tail call
      }
      factorialHelper(x,1)
    }


  /**
    *  pattern matching + iterating lists in java
    *
    */
  def sumTest(xs : List[Int]) : Int = {
    xs match{
      case Nil =>
        0
      case head :: tail =>
        head + sumTest(tail)
    }
  }

  def testSum: Int = sumTest((1::2::3::Nil))
}