package States

import backgrounds.nightclass
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Fase3State(gameStateManager: GameStateManager?) : State(gameStateManager) {
    override var gsm: GameStateManager? = gameStateManager
    private val ground = Texture("tiles/day/tile.png")
    val background = nightclass(0, 0.toInt(), gsm)

    override fun handleInput() {
        if (Gdx.input.isTouched && !showShadow) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = Gdx.input.y.toFloat()
            if ((touchX >= -30 && touchX <= -30 + go_back.width && touchY >= 600) && touchY <= 600 + (go_back.height)) {
                gsm?.set(FaseSelectorState(gsm))
                dispose()
            }
        }
        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = Gdx.input.y.toFloat()
            if ((touchX >= 450 && touchX <= 450 + go_back.width && touchY >= 600) && touchY <= 600 + (go_back.height) && showShadow) {
                showShadow = false
                settings = false
                dispose()
            }
        }
        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (touchX >= (w - (w / 10)) && touchX <= (w - (w / 10)) + settings_btn.width && touchY >= ((h + 20) - (h / 4)) && touchY <= ((h + 20) - (h / 4)) + settings_btn.height) {
                showShadow = true
                settings = true
                dispose()
            }
        }
        if (Gdx.input.justTouched() && !music){
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (touchX >= 750f && touchX <= (750f+indicator.width) && touchY >= 385f && touchY <= 385f+indicator.height){
                music = true
                preferences.putBoolean("music", music)
                preferences.flush()
            }
        }
        else if (Gdx.input.justTouched() && music){
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (touchX >= 750f && touchX <= (750f+indicator.width) && touchY >= 385f && touchY <= 385f+indicator.height){
                music = false
                preferences.putBoolean("music", music)
                preferences.flush()
            }
        }
        if (Gdx.input.justTouched() && !fx){
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (touchX >= 750f && touchX <= (750f+indicator.width) && touchY >= 200f && touchY <= 200f+indicator.height){
                fx = true
                preferences.putBoolean("fx", fx)
                preferences.flush()
            }
        }
        else if (Gdx.input.justTouched() && fx){
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (touchX >= 750f && touchX <= (750f+indicator.width) && touchY >= 200f && touchY <= 200f+indicator.height){
                fx = false
                preferences.putBoolean("fx", fx)
                preferences.flush()
            }
        }
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(spriteBatch: SpriteBatch?) {
        Gdx.gl.glClearColor(110f, 33f, 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        background.render()
        spriteBatch?.begin()
        spriteBatch?.draw(go_back, -30f, -30f)
        spriteBatch?.draw(go_back, -30f, -30f)
        spriteBatch?.draw(leyenda, 360f, 550f)
        spriteBatch?.draw(locked, 250f, 350f)
        spriteBatch?.draw(locked, 250f, 100f)
        spriteBatch?.draw(locked,475f, 350f)
        spriteBatch?.draw(locked, 475f, 100f)
        spriteBatch?.draw(locked, 700f, 350f)
        spriteBatch?.draw(locked, 700f, 100f)
        spriteBatch?.draw(locked, 925f, 350f)
        spriteBatch?.draw(locked, 925f, 100f)
        spriteBatch?.draw(locked, 1150f, 350f)
        spriteBatch?.draw(locked, 1150f, 100f)
        spriteBatch?.draw(settings_btn, (w - (w / 10)).toFloat(), ((h + 20) - (h / 4)).toFloat())
        if (showShadow && settings) {
            spriteBatch?.setColor(0f, 0f, 0f, 0.5f)
            background.render()
            spriteBatch?.draw(shadow, 0f, 0f, w.toFloat(), h.toFloat())
            spriteBatch?.setColor(1f, 1f, 1f, 1f)
            spriteBatch?.draw(
                settings_menu,
                ((w / 2) - (settings_menu.width / 2)).toFloat(),
                ((h / 2) - (settings_menu.height / 2)).toFloat()
            )
            spriteBatch?.draw(go_back, 450f, -10f)
            if (!music) {
                spriteBatch?.draw(indicator, 750f, 385f)
            } else if (music) {
                spriteBatch?.draw(indicator, 845f, 385f)
            }
            if (!fx) {
                spriteBatch?.draw(indicator, 750f, 200f)
            } else if (fx) {
                spriteBatch?.draw(indicator, 845f, 200f)
            }
        }
        spriteBatch?.end()
    }

    override fun dispose() {

    }
}