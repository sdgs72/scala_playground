package funsets


/**
 * 2. Purely Functional Sets.
 */
object FunSets {
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type Set = Int => Boolean //it is a fucntion that will return true if the element exists in our set

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
    * returns a function that will return true if the element exists in the set
    * the function will be type x : Int => Boolean
   */
    def singletonSet(elem: Int): Set = {
      //closures elem is stored in the 'closure'
      ( x : Int ) => x == elem : Boolean
    }

  def addElement(s : Set,elem : Int) : Set = {
    (x : Int ) =>  x == elem || s(x)  : Boolean
  }

  def addElements ( s : Set, arrs : List[Int]) : Set = {
    (x : Int) => if (s(x)) true else helperAddElements(arrs,x)
  }

  def buildSet(arrs : List[Int]): Set ={
    def helper(x : Int,arrs : List[Int]) : Boolean ={
      if(arrs.isEmpty)
        false
      else if(x == arrs.head)
        true
      else
        helper(x,arrs.tail)
    }

    (x : Int) => helper(x,arrs) : Boolean

  }

  private def helperAddElements( arrs : List[Int] , x : Int) : Boolean = {
    if(arrs.isEmpty)
      false
    else if(x == arrs.head)
      true
    else
      helperAddElements(arrs.tail,x)
  }

  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
    def union(s: Set, t: Set): Set = (x : Int) => (s(x) || t(x)) : Boolean
  //this is complex to churn but basically, it the first set and second set are all stored
  //in the closure
  
  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
    def intersect(s: Set, t: Set): Set = (x : Int) => (s(x) && t(x)) : Boolean
  
  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
    def diff(s: Set, t: Set): Set = (x : Int) => (s(x) && !t(x)) : Boolean
  
  /**
   * Returns the subset of `s` for which `p` holds.
   */
    def filter(s: Set, p: Int => Boolean): Set = (x : Int) => (s(x) && p(x)) : Boolean
  

  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
    def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (s(a) && !p(a)) false
      else iter(a+1)
    }
    iter(-bound)
  }
  
  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
    def exists(s: Set, p: Int => Boolean): Boolean = {
      def wrapper : Int => Boolean = {
        (x : Int) => {
          !p(x)
        }
      }

      !forall(s, wrapper)
    }
  
  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
    def map(s: Set, f: Int => Int): Set = (x : Int) => {
      def iter(a: Int): Boolean = {
        if (a > bound) false
        else if(s(a) && f(a) == x) true
        else iter(a+1)
      }
      iter(-bound)
    }
  
  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}
