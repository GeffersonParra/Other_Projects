package World

import com.badlogic.gdx.graphics.OrthographicCamera

abstract class GameMap {
    abstract fun render(camera: OrthographicCamera)

    abstract fun update(deltaTime: Float)

    abstract fun dispose()

    fun getTileByLocation(layer: Int, x: Float, y: Float): TileType? {
        val col = Math.floor((x / 16).toDouble()).toInt()
        val row = Math.floor((y / 16).toDouble()).toInt()
        return getTileByCoordinate(layer, col, row)
    }

    abstract fun getTileByCoordinate(layer: Int, col: Int, row: Int): TileType?

    abstract fun getWidth(): Int

    abstract fun getHeight(): Int

    abstract fun getLayers(): Int
}

