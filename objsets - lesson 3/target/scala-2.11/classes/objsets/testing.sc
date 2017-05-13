import objsets.{Empty, Tweet, TweetSet}

val set1 = new Empty
val set2 = set1.incl(new Tweet("a", "a body", 20))
val set3: TweetSet = set2.incl(new Tweet("b", "b body", 20))
val set4 = new Empty
val set5 = set4.union(set3)
val set6 = set3.incl(new Tweet("c","c body",30))
val set7 = set3

def test : Int = {
  2
}


abstract class parent{
  def hello : parent
}

object child1 extends  parent{
  def hello : parent = {
    print("child1")
    child2
  }
}

object child2 extends parent{
  def hello : parent = {
    print("child2")
    child1
  }
}


child1.hello.hello

