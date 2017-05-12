package objsets

class notes {


  class test(z : Int){

    def x: Int = z

  }

  /**
    * abstract classes syntax abstract class name
    * it has the same behaviour as java abstract classes in a sense that
    * 1. it can't be instantiated(newed)
    * 2. you must left out the functions as unimplemented
    * 3. you can have constant values
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

  //too


}
