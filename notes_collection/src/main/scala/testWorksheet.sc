val listExample: List[Int] = List(1,2,3,4,5)
listExample.flatMap(
  (f : Int) => List(f)
)

val listExample2 : List[List[Int]] = List(List(1,2,3),List(4,5,6))
listExample2.flatMap(
  (f : List[Int] )=> f
)

listExample2.flatten
