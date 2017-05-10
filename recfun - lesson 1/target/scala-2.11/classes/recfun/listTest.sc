def sumTest(xs : List[Int]) : Int = {
  xs match{
    case Nil =>
      0
    case head :: tail =>
      head + sumTest(tail)
  }
}

sumTest(0::1::5::Nil)
