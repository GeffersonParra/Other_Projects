package States

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import players.PlayerOne
import players.PlayerTwo

abstract class State protected constructor(gameStateManager: GameStateManager?) {
    protected open var camera: OrthographicCamera = OrthographicCamera()
    protected var touch: Vector3 = Vector3()
    val maintheme = Gdx.audio.newMusic(Gdx.files.internal("music/Main.mp3"))
    val gametheme = Gdx.audio.newMusic(Gdx.files.internal("music/Game.mp3"))
    val indicator = Texture("misc/settings_indicator.png")
    val settings_btn = Texture("buttons/settings_btn.png")
    val preferences = Gdx.app.getPreferences("MyPreferences")
    val leyenda = Texture("misc/leyenda.png")
    val locked = Texture("tiles/locked/locked.png")
    val go_back = Texture("buttons/go_back_btn.png")
    var music = preferences.getBoolean("music", false)
    var fx = preferences.getBoolean("fx", false)
    var settings = false
    var showShadow = false
    val settings_menu = Texture("misc/settings_menu.png")
    val restart = Texture("buttons/restart_btn.png")
    val menulost = Texture("misc/lost.png")
    val menuwin = Texture("misc/win.png")
    val play_btn = Texture("buttons/play_btn.png")
    val pause = Texture("buttons/pause.png")
    val uno = Texture("buttons/1.png")
    val dos = Texture("buttons/2.png")
    val tres = Texture("buttons/3.png")
    val cuatro = Texture("buttons/4.png")
    val cinco = Texture("buttons/5.png")
    val seis = Texture("buttons/6.png")
    val siete = Texture("buttons/7.png")
    val ocho = Texture("buttons/8.png")
    val nueve = Texture("buttons/9.png")
    val diez = Texture("buttons/10.png")
    val shadow = Texture("backgrounds/back_fase.png")
    val leftr = Texture("misc/left-red.png")
    val rightr = Texture("misc/right-red.png")
    val jumpr = Texture("misc/jump-red.png")
    val leftb = Texture("misc/left-blue.png")
    val rightb = Texture("misc/right-blue.png")
    val jumpb = Texture("misc/jump-blue.png")
    open var gsm: GameStateManager? = null

    var h: Int = Gdx.graphics.height
    var w: Int = Gdx.graphics.width

    protected abstract fun handleInput()
    abstract fun update(dt: Float)
    abstract fun render(spriteBatch: SpriteBatch?)

    abstract fun dispose()
}
