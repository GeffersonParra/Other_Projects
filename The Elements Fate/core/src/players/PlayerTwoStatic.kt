package players

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle

class PlayerTwoStatic(var x: Int, var y: Int) {
    private var batch: SpriteBatch = SpriteBatch()
    private var texture: Texture = Texture("players/p2/playerTwo.png")
    private var animation: Animation<TextureRegion>
    private var elapsedTime: Float = 0f
    private var boundingBox: Rectangle = Rectangle(x.toFloat(), y.toFloat(), texture.width / 4f - 10f, texture.height.toFloat())

    init {
        val textureRegion = TextureRegion.split(texture, texture.width / 4, texture.height)[0]
        animation = Animation(0.25f, *textureRegion)
    }

    fun render() {
        elapsedTime += Gdx.graphics.deltaTime

        val currentFrame = animation.getKeyFrame(elapsedTime, true)
        batch.begin()
        batch.draw(currentFrame, x.toFloat(), y.toFloat())
        batch.end()
    }

    fun dispose() {
        batch.dispose()
        texture.dispose()
    }

    fun setPosition(x: Int, y: Float) {
        this.x = x
        this.y = y.toInt()
        boundingBox.setPosition(x.toFloat(), y)
    }

    fun getBoundingBox(): Rectangle {
        return boundingBox
    }

}


