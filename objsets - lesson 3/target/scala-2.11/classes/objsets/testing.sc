import objsets.{Empty, Tweet}

val set1 = new Empty
val set2 = set1.incl(new Tweet("a", "a body", 20))
val set3 = set2.incl(new Tweet("b", "b body", 20))
val set4 = new Empty
val set5 = set4.union(set3)
val set6 = set3.incl(new Tweet("c","c body",30))
val set7 = set3

def test : Int = {
  2
}


val meme = test

