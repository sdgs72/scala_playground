/*


Review what we did


  1. Make Move trait + 4 case classes (left,right,up,down doesn't accept anything)
  2. Make Block object(pos1,pos2) with following property
      isLegal => use the terrain defined in game def
      isStanding => pos1 and pos2 are the same
      legalNeighbors => traverse 4 directions that are legal
                    => we return 4 blocks in this case
                    => for each state we store a copy of the block


  3. Creation of the combinatrics function:
      use stream object for "all" possible solutions
      use for combinatrics for the following:
      from(currentState){  // current block states
        var temp
        for eachBlock in currentState
          for eachNeighBor in eachBlock.legalNeighbor filter unexplored
            eachNeighBor :: temp  //also check if explored using Set(block) before appendingd
        temp ::: from(temp)
     }




 */