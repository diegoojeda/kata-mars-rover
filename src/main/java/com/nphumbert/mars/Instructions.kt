package com.nphumbert.mars

import java.util.*

interface Instruction {
    fun apply(position: Position): Position
}

class RotateLeft : Instruction {
    override fun apply(position: Position): Position {
        return Position(position.x, position.y, position.orientation.left())
    }
}

class MoveForward : Instruction {

    val moves = HashMap<Orientation, (Position) -> Position>()

    constructor() {
        moves.put(Orientation.NORTH, { Position(it.x, it.y.plus(1), it.orientation) })
        moves.put(Orientation.EAST, { Position(it.x.plus(1), it.y, it.orientation) })
        moves.put(Orientation.SOUTH, { Position(it.x, it.y.minus(1), it.orientation) })
        moves.put(Orientation.WEST, { Position(it.x.minus(1), it.y, it.orientation) })
    }

    override fun apply(position: Position): Position {
        return moves[position.orientation]!!.invoke(position)
    }
}

class RotateRight : Instruction {
    override fun apply(position: Position): Position {
        return Position(position.x, position.y, position.orientation.right())
    }
}


