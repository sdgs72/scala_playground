package forcomp


import Anagrams._
/**
  * Created by dionhiananto on 5/14/17.
  */

object main extends App {

  /* matching with pairs */
  def mergesort(xs: List[Int]): List[Int] = {
    val middlePoint: Int = xs.length / 2
    if (middlePoint == 0) xs
    else {
      def merge(xs: List[Int], ys: List[Int]): List[Int] = {
        (xs, ys) match {
          case (Nil, ys) => ys
          case (xs, Nil) => xs
          case (x :: xs1, y :: ys1) => {
            if (x < y) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
          }
        }
      }

      val (fst, snd) = xs.splitAt(middlePoint)
      merge(mergesort(fst), mergesort(snd))
    }
  }

  println(mergesort(List(100000, -1, 10, 50, 15)).toString)

  //implicit parameters
  def mergesort[T](xs: List[T])(implicit x : Ordering[T]): List[T] = {
    Nil
  }

  /*
  class lol[T](x : T){
    implicit def hehe : Boolean = {
      false
    }
  }
  */


  //higher order function -> a function that accepts another function
  //foldLeft ->
  //foldRight


  def listExample : List[Int]= List(1,2,3,4,50)
  def mapExample : List[Int] = listExample.map((x :Int) => x*x)
  def mapExample2 : List[(Int,Int)] = listExample.map((x :Int) => (x,x) : (Int,Int))


  def mapExample3 : List[Int] = listExample.map(_ + 3)

  /*
  wildcard pattern
  (x : Int ,y : Int) => x + y
  can be rewritten as
  _ + _
  the underscore will go from leftmost paramter to the right
  */

  def foldLeftExample : Int = listExample.foldLeft(0)(
    (x1 : Int, x2 : Int) =>
      x1 + x2 + 3
  )

  def foldLeftExamplePattern : Int = listExample.foldLeft(0)(
    _ + _ + 10
  )


  def asdf = listExample.sortWith( (x1 : Int , x2 : Int) => x1 > x2)

  println(asdf)

  //scala vectors immutable with linear access for all elements log32(n)
  //lists only offer fast access to the first element


  def v : Vector[Int] = Vector(1,2,3)

  def loopExample : List[Int] = List(1,2,3)
  def loopExample2 : List[(Int,Int)] = List((1,2),(2,3),(3,4))


  //syntax for
  /*
      for (s) yield e

      s consists of a generator and  a filter
      generator's syntax using arrows
      p <- e    P CAN BE A PATTERN
      filter is in the form of if
      instead of (s) can use {s} for multiple line loops

   */




  for ( elem <- loopExample if elem > 2) yield elem
  //the above and below are equal
  loopExample.filter((x :Int) => x > 2)

  //pattern matching + for on forloop on steroids
  for ( (elem1,elem2) <- loopExample2 if elem1 > 2) yield elem2




  def n : Int = 5
  def multiLineFor : IndexedSeq[(Int,Int)]= {
    for {
    i <- 1.until(n)
    j <- {
      if(i == 3)
        1.until(n) //the last generator will loop faster
      else
        Nil //skip all non threes just like filter, same as break
      //if i != 3 it won't yield
    }
    }
    yield (i,j)
  }

  //If the for loop didn't arrive at the bottom it will skip (same as break)
  println(multiLineFor.toString)
  //nQueens


}