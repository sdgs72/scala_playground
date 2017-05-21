
//taken from a coursera
class Pouring (capacity: Vector[Int]) {


  //STATE IS A VECTOR WHERE EACH ELEMENT INDICATES THE AMOUNT OF WATER FOR EACH GLASS
  type State = Vector[Int]
  val initialState = capacity map (_ => 0) //initial state of every glass is empty

  //MOVES
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    //updated: a copy of the list with one single replaced element
    def change(state: State) = state updated(glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    def change(state: State) = state updated(glass, capacity(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State) = {
      val amount = state(from) min (capacity(to) - state(to))
      state updated(from, state(from) - amount) updated(to, state(to) + amount)
    }
  }

  val glasses = 0 until capacity.length

  //generate all possible moves
  val moves = {
    val emptyEachGlassMoves: List[Move] = (for (g <- glasses) yield Empty(g)).toList
    val fillEachGlassMoves: List[Move] = (for (g <- glasses) yield Fill(g)).toList
    val pourMoves: List[Move] = (for {
      from <- glasses
      to <- glasses
      if from != to
    }
      yield Pour(from, to)).toList
    emptyEachGlassMoves ++ fillEachGlassMoves ++ pourMoves
  }


  class Path(history : List[Move],val endState : State){
    def extend(move: Move) = new Path(move :: history, move change endState)
    override def toString = (history.reverse mkString " ") + " -->" + endState
  }

  val initialPath = new Path(Nil, initialState)

  //explored: the states that have been visited
  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    //generate all the possible new paths
    else {
      val more = for {
        path <- paths
        //next path by extending current path
        //for all possible moves
        next <- moves map path.extend
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }

  //all possible paths
  //the first element gives the initial path set
  //the set of all possible paths at length 1
  val pathSets = from(Set(initialPath), Set(initialState))

  //the volume we want to see in one of glasses
  def solutions(target: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path

}
