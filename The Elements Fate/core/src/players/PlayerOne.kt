package players

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle

class PlayerOne(var x: Int, var y: Int) {
    private var batch: SpriteBatch = SpriteBatch()
    private var texture: Texture = Texture("players/p1/playerone.png")
    private var animation: Animation<TextureRegion>
    private var elapsedTime: Float = 0f
    private var boundingBox: Rectangle = Rectangle(x.toFloat(), y.toFloat(), texture.width / 4f - 10f, texture.height.toFloat())
    private var facingRight: Boolean = true


    private var velocityY: Float = 0f
    private var gravity: Float = -300f

    init {
        val textureRegion = TextureRegion.split(texture, texture.width / 4, texture.height)[0]
        animation = Animation(0.25f, *textureRegion)
    }

    fun render() {
        elapsedTime += Gdx.graphics.deltaTime

        // Aplicar gravedad
        velocityY += gravity * Gdx.graphics.deltaTime
        y += (velocityY * Gdx.graphics.deltaTime).toInt()

        // Actualizar la posición del jugador
        val currentFrame = animation.getKeyFrame(elapsedTime, true)
        batch.begin()
        batch.draw(currentFrame, x.toFloat(), y.toFloat())
        batch.end()

        // Actualiza el bounding box
        boundingBox.setPosition(x.toFloat(), y.toFloat())
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

    // Método para aplicar una fuerza vertical al jugador (por ejemplo, un salto)
    fun applyForce(forceY: Float) {
        velocityY += forceY
    }

    // Método para detener la caída
    fun stopFalling() {
        velocityY = 0f
    }

}