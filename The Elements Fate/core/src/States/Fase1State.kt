package States

import World.TiledGameMap
import backgrounds.dayclass
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.utils.ScreenUtils
import levelOne
import levelTwo


class Fase1State(gameStateManager: GameStateManager?) : State(gameStateManager) {
    override var camera: OrthographicCamera = OrthographicCamera()
    private var map: TiledMap? = null
    private var renderer: OrthogonalTiledMapRenderer? = null
    private val ground = Texture("tiles/day/tile.png")
    override var gsm: GameStateManager? = gameStateManager
    var gameMap: TiledGameMap = TiledGameMap()
    var cam: OrthographicCamera = OrthographicCamera()
    var level: Int = 0
    val background = dayclass(0, 0.toInt(), gsm)

    init {
        renderer = OrthogonalTiledMapRenderer(map)
    }


    override fun handleInput() {
    // Ir atrás en settings
        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = Gdx.input.y.toFloat()
            if ((touchX >= 450 && touchX <= 450 + go_back.width && touchY >= 600) && touchY <= 600 + (go_back.height) && showShadow) {
                settings = false
                showShadow = false
                dispose()
            }
        }

        if (Gdx.input.isTouched && !showShadow) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = Gdx.input.y.toFloat()

            // Botón de volver atrás
            if ((touchX >= -30 && touchX <= -30 + go_back.width && touchY >= 600) && touchY <= 600 + go_back.height) {
                gsm?.set(FaseSelectorState(gsm))
                dispose()
            }
        }
        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (touchX >= 250f && touchX <= 250f + ground.width && touchY >= 350f && touchY <= 350f + ground.height) {
                maintheme.dispose()
                gsm?.set(levelOne(gsm))
                dispose()
            }
        }

        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (touchX >= 475f && touchX <= 475f + ground.width && touchY >= 350f && touchY <= 350f + ground.height) {
                maintheme.dispose()
                gsm?.set(levelTwo(gsm))
                dispose()
            }
        }


        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()

            // Botón de configuración
            if (touchX >= (w - (w / 10)) && touchX <= (w - (w / 10)) + settings_btn.width && touchY >= ((h + 20) - (h / 4)) && touchY <= ((h + 20) - (h / 4)) + settings_btn.height) {
                showShadow = true
                settings = true
                dispose()
            }

            // Música
            if (touchX >= 750f && touchX <= 750f + indicator.width && touchY >= 385f && touchY <= 385f + indicator.height) {
                music = !music
                preferences.putBoolean("music", music)
                preferences.flush()
            }

            // Efectos de sonido (FX)
            if (touchX >= 750f && touchX <= 750f + indicator.width && touchY >= 200f && touchY <= 200f + indicator.height) {
                fx = !fx
                preferences.putBoolean("fx", fx)
                preferences.flush()
            }
        }
    }

    override fun update(dt: Float) {
        handleInput()
        if (music) {
            gametheme.stop()
        }
        if (!music){
            maintheme.stop()
        }
    }

    override fun render(spriteBatch: SpriteBatch?) {
        Gdx.gl.glClearColor(110f, 33f, 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        background.render()
        spriteBatch?.begin()
        spriteBatch?.draw(go_back, -30f, -30f)
        spriteBatch?.draw(leyenda, 360f, 550f)
        spriteBatch?.draw(ground, 250f, 350f)
        spriteBatch?.draw(uno, 250f, 335f, 200f, 200f)
        spriteBatch?.draw(locked, 250f, 100f)
        spriteBatch?.draw(ground, 475f, 350f)
        spriteBatch?.draw(dos, 475f, 335f, 200f, 200f)
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
            spriteBatch?.draw(settings_menu, ((w / 2) - (settings_menu.width / 2)).toFloat(), ((h / 2) - (settings_menu.height / 2)).toFloat())
            spriteBatch?.draw(go_back, 450f, -10f)
            if (!music) {
                spriteBatch?.draw(indicator, 750f, 385f)
            } else {
                spriteBatch?.draw(indicator, 845f, 385f)
            }
            if (!fx) {
                spriteBatch?.draw(indicator, 750f, 200f)
            } else {
                spriteBatch?.draw(indicator, 845f, 200f)
            }
        }
       spriteBatch?.end()
    }

    override fun dispose() {
        maintheme.dispose()
    }
}
