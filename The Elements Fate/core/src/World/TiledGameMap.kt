package World

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer

class TiledGameMap : GameMap() {

    var tiledMap: TiledMap? = null
    var tiledMapRenderer: OrthogonalTiledMapRenderer? = null

    init {
        tiledMap = TmxMapLoader().load("levels/prueba.tmx")
        tiledMapRenderer = OrthogonalTiledMapRenderer(tiledMap!!)
    }

    override fun render(camera: OrthographicCamera) {
        tiledMapRenderer?.setView(camera)
        tiledMapRenderer?.render()
    }

    override fun update(deltaTime: Float) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        tiledMap?.dispose()
    }

    override fun getTileByCoordinate(layer: Int, col: Int, row: Int): TileType? {
        TODO("Not yet implemented")
    }

    override fun getWidth(): Int {
        TODO("Not yet implemented")
    }

    override fun getHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun getLayers(): Int {
        TODO("Not yet implemented")
    }
}