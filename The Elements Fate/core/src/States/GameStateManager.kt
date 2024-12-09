package States

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array

class GameStateManager {
    private val states: Array<State> = Array()

    fun push(state: State) {
        states.add(state)
    }

    fun pop() {
        states.pop().dispose()
    }

    fun set(state: State) {
        if (states.size > 0) {
            states.pop().dispose()
        }
        states.add(state)
    }

    fun update(dt: Float) {
        if (states.size > 0) {
            states.peek().update(dt)
        }
    }

    fun render(sb: SpriteBatch) {
        if (states.size > 0) {
            states.peek().render(sb)
        }
    }
}

