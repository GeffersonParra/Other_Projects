package backgrounds

import States.GameStateManager
import States.State
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion

class dayclass(var x: Int, var y: Int, gameStateManager: GameStateManager?) : State(gameStateManager) {

    private val batch: SpriteBatch = SpriteBatch()
    private val texture: Texture = Texture("backgrounds/day.png")
    private lateinit var animation: Animation<TextureRegion>
    private var elapsedTime: Float = 0f

    init {
        // Define the number of rows and columns in the sprite sheet
        val rows = 8  // Adjust based on your sprite sheet layout
        val cols = 6  // Adjust based on your sprite sheet layout

        // Calculate sprite width and height based on texture dimensions
        val spriteWidth = texture.width / cols
        val spriteHeight = texture.height / rows

        // Split the texture into individual TextureRegion objects
        val frames = Array<TextureRegion>(rows * cols) { TextureRegion() }

        // Populate the frames array with TextureRegion objects
        for (row in 0..(rows-1)) {
            for (col in 0..(cols-1)) {
                frames[row * cols + col] = TextureRegion(texture, col * spriteWidth, row * spriteHeight, spriteWidth, spriteHeight)
            }
        }

        // Create an animation using the array of TextureRegion objects
        animation = Animation<TextureRegion>(0.2f, *frames)  // Note the use of *frames to spread the array
    }

    fun render() {
        batch.begin()
        batch.draw(animation.getKeyFrame(elapsedTime, true), x.toFloat(), y.toFloat(), w.toFloat(), h.toFloat()) // Draw current frame
        elapsedTime += Gdx.graphics.deltaTime
        batch.end()
    }

    override fun handleInput() {
        // Handle any input logic if needed
    }

    override fun update(dt: Float) {
        // Update logic if needed
    }

    override fun render(spriteBatch: SpriteBatch?) {
        // Render logic if needed
    }

    override fun dispose() {

    }
}
