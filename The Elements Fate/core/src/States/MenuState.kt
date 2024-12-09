package States

import backgrounds.nightclass
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.ScreenViewport
import players.PlayerOne
import players.PlayerOneStatic
import players.PlayerTwo
import players.PlayerTwoStatic

class MenuState(gameStateManager: GameStateManager?) : State(gameStateManager) {
    private val stage = Stage(ScreenViewport())
    private val table = Table()
    private val scrollPane = ScrollPane(table)
    private val trophy_item = Texture("misc/trophy_item.png")
    private val trophy_btn = Texture("buttons/trophy_btn.png")
    private val info_btn = Texture("buttons/Info_btn.png")
    private val ground = Texture("tiles/night/nighttile.png")
    private val advise1 = Texture("misc/advise.png")
    private val trophy_menu = Texture("misc/trophy_menu.png")
    private val logo = Texture("misc/logo.png")
    override var gsm: GameStateManager? = gameStateManager
    private var info = false
    private var trophy = false
    val p1 = PlayerOneStatic(400, 130)
    val p2 = PlayerTwoStatic(1050, 130)
    val background = nightclass(0, 0.toInt(), gsm) // Create the dayclass object


    init {
        Gdx.input.inputProcessor = stage
        setupTable()
    }

    private fun setupTable() {
        table.top()
        table.setFillParent(true)

        for (i in 1..2) {
            val label = Image(trophy_item)
            table.add(label).pad(20f)
            table.setSize(50f, 50f)
            table.row()
        }

        scrollPane.setFillParent(true)
        scrollPane.setFadeScrollBars(false)
        scrollPane.isVisible = false
        scrollPane.setScrollbarsOnTop(true)
        scrollPane.height=10f
        scrollPane.width=5f
        scrollPane.setPosition(20f, -150f)
        scrollPane.setColor(0f, 0f, 0f, 1f)
        stage.addActor(scrollPane)
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = Gdx.input.y.toFloat()
            if ((touchX >= (w / 2.2f) && touchX <= w / 2.2f + (play_btn.width / 1.5) && touchY >= h / 1.7f - (play_btn.height / 2)) && touchY <= (h / 4) + (play_btn.height) && !showShadow) {
                gsm?.set(FaseSelectorState(gsm))
                dispose()
            }
        }
        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if ((touchX >= (w - (w / 10)) && touchX <= w - (w / 10) + info_btn.width && touchY >= h / 2 - 400) && touchY <= (h / 2) - 400 + info_btn.height) {
                info = true
                showShadow = true
                dispose()
            }
        }
        if (Gdx.input.justTouched()) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = Gdx.input.y.toFloat()
            if ((touchX >= 450 && touchX <= 450 + go_back.width && touchY >= 600) && touchY <= 600 + (go_back.height) && showShadow) {
                settings = false
                info = false
                showShadow = false
                dispose()
            }
            else if ((touchX >= -30 && touchX <= -30 + go_back.width && touchY >= 600) && touchY <= 600 + (go_back.height) && trophy){
                trophy = false
                showShadow = false
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

        if (Gdx.input.justTouched()){
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
            if (touchX >= -40f && touchX <= -40f + trophy_btn.width &&
                touchY >= ((h + 20) - (h / 4)) && touchY <= ((h + 20) - (h / 4)) + trophy_btn.height) {
                showShadow = true
                trophy = true
                scrollPane.isVisible = true
            }
        }
    }
    override fun update(dt: Float) {
        handleInput()
        stage.act(dt)
        if (music){
            maintheme.play()
            maintheme.isLooping()
        }
        if (!music){
            maintheme.stop()
        }
    }

    override fun render(spriteBatch: SpriteBatch?) {
        table.setFillParent(true);
        table.top();

        spriteBatch?.begin()
        background.render()
        spriteBatch?.draw(play_btn, ((w / 2) - (play_btn.width / 2)).toFloat(), ((h / 2) - (play_btn.height / 2)).toFloat())
        spriteBatch?.draw(info_btn, (w - (w / 10)).toFloat(), (-400 + (h / 2)).toFloat())
        spriteBatch?.draw(settings_btn, (w - (w / 10)).toFloat(), ((h + 20) - (h / 4)).toFloat())
        spriteBatch?.draw(trophy_btn, -40f, ((h + 20) - (h / 4)).toFloat())
        spriteBatch?.draw(
            ground,
            ((w / 5) - (ground.width / 3)).toFloat(),
            -200f,
            (w / 4).toFloat(),
            (h / 2).toFloat()
        )
        spriteBatch?.draw(
            ground,
            (w - (w / 2.6f)) - (ground.width / 3),
            -200f,
            (w / 4).toFloat(),
            (h / 2).toFloat()
        )
        p1.render()
        p2.render()
        spriteBatch?.draw(logo, 5f, (-350 + (h / 2)).toFloat(), 200f, 200f)
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
            }
            else if (music) {
                spriteBatch?.draw(indicator, 845f, 385f)
            }
            if (!fx) {
                spriteBatch?.draw(indicator, 750f, 200f)
            }
            else if (fx) {
                spriteBatch?.draw(indicator, 845f, 200f)
            }
        } else if (showShadow && info) {
            spriteBatch?.setColor(0f, 0f, 0f, 0.5f)
            background.render()
            spriteBatch?.draw(shadow, 0f, 0f, w.toFloat(), h.toFloat())
            spriteBatch?.setColor(1f, 1f, 1f, 1f)
            spriteBatch?.draw(
                advise1,
                ((w / 2) - (advise1.width / 2)).toFloat(),
                ((h / 2) - (advise1.height / 2)).toFloat()
            )
            spriteBatch?.draw(go_back, 450f, -10f)
        } else if (showShadow && trophy) {
            spriteBatch?.setColor(0f, 0f, 0f, 0.5f)
            background.render()
            spriteBatch?.draw(shadow, 0f, 0f, w.toFloat(), h.toFloat())
            spriteBatch?.setColor(1f, 1f, 1f, 1f)
            spriteBatch?.draw(go_back, -30f, -20f)
            stage.draw()
            spriteBatch?.draw(
                trophy_menu,
                ((w / 2) - (trophy_menu.width / 2)).toFloat(),
                ((h / 2) - (trophy_menu.height / 2)).toFloat()
            )
        }
        spriteBatch?.end()
    }

    override fun dispose() {
    }
}
