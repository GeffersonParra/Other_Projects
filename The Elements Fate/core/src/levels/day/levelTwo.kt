import States.Fase1State
import States.Fase3State
import States.GameStateManager
import States.MenuState
import States.State
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.utils.ScreenUtils
import players.PlayerOne
import players.PlayerTwo

class levelTwo(gameStateManager: GameStateManager?) : State(gameStateManager) {

    private var map: TiledMap? = null
    private var renderer: OrthogonalTiledMapRenderer? = null
    private var player1: PlayerOne? = null
    private var player2: PlayerTwo? = null
    private val sueloObjects = mutableListOf<RectangleMapObject>()
    private val lavaObjects = mutableListOf<RectangleMapObject>()
    private val aguaObjects = mutableListOf<RectangleMapObject>()
    private val portalBObjects = mutableListOf<RectangleMapObject>()
    private val portalRObjects = mutableListOf<RectangleMapObject>()
    private val BoundsObjects = mutableListOf<RectangleMapObject>()
    private var lost = false
    private var ispaused = false
    private var azul = false
    private var rojo = false
    override var gsm: GameStateManager? = gameStateManager

    init {
        create()
        maintheme.stop()
    }

    fun create() {
        player1 = PlayerOne(25, 400)
        player2 = PlayerTwo(75, 400)
        camera = OrthographicCamera()
        camera.setToOrtho(false, 513f, 255f)
        camera.update()

        map = TmxMapLoader().load("levels/prueba2.tmx")
        if (map != null) {
            renderer = OrthogonalTiledMapRenderer(map)
        } else {
            Gdx.app.error("levelOne", "El mapa no pudo ser cargado.")
            return
        }

        val objects = map!!.layers["Capa de Objetos 1"].objects
        for (obj in objects) {
            if (obj is RectangleMapObject) {
                val objName = obj.name
                val propName = obj.properties["name"] as String?

                if (objName == null && propName == null) {
                    println("Objeto encontrado sin nombre: x=${obj.rectangle.x}, y=${obj.rectangle.y}, width=${obj.rectangle.width}, height=${obj.rectangle.height}")
                } else {
                    println("Objeto encontrado: objName=$objName, propName=$propName, x=${obj.rectangle.x}, y=${obj.rectangle.y}, width=${obj.rectangle.width}, height=${obj.rectangle.height}")
                    if (objName == "suelo" || propName == "suelo") {
                        sueloObjects.add(obj)
                    } else if (objName == "lava" || propName == "lava") {
                        lavaObjects.add(obj)
                    } else if (objName == "agua" || propName == "agua") {
                        aguaObjects.add(obj)
                    } else if (objName == "portalb" || propName == "portalb") {
                        portalBObjects.add(obj)
                    } else if (objName == "portalr" || propName == "portalr") {
                        portalRObjects.add(obj)
                    } else if (objName == "bounds" || propName == "bounds") {
                        BoundsObjects.add(obj)
                    }
                }
            }
        }
    }

    private fun checkCollisionsWithSuelo(player: PlayerOne) {
        for (suelo in sueloObjects) {
            val rect = suelo.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                player.setPosition(player.x, rect.y + rect.height)
                player.stopFalling()
                rojo = false
                break
            }
        }
    }

    private fun checkCollisionsWithLava(player: PlayerOne) {
        for (lava in lavaObjects) {
            val rect = lava.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                player.setPosition(player.x, rect.y + rect.height)
                player.stopFalling()
                rojo = false
                break
            }
        }
    }

    private fun checkCollisionsWithAgua(player: PlayerOne) {
        for (agua in aguaObjects) {
            val rect = agua.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                lost = true
                rojo = false
                break
            }
        }
    }

    private fun checkCollisionsWithportalR(player: PlayerOne) {
        for (portalr in portalRObjects) {
            val rect = portalr.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                rojo = true
                break
            }
        }
    }

    private fun checkCollisionsWithBounds(player: PlayerOne) {
        for (bounds in BoundsObjects) {
            val rect = bounds.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                lost = true
                break
            }
        }
    }

    private fun checkCollisionsWithSueloPlayerTwo(player: PlayerTwo) {
        for (suelo in sueloObjects) {
            val rect = suelo.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                player.setPosition(player.x, rect.y + rect.height)
                player.stopFalling()
                azul = false
                break
            }
        }
    }

    private fun checkCollisionsWithLavaPlayerTwo(player: PlayerTwo) {
        for (lava in lavaObjects) {
            val rect = lava.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                lost = true
                azul = false
                break
            }
        }
    }

    private fun checkCollisionsWithAguaPlayerTwo(player: PlayerTwo) {
        for (agua in aguaObjects) {
            val rect = agua.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                player.setPosition(player.x, rect.y + rect.height)
                player.stopFalling()
                azul = false
                break
            }
        }
    }

    private fun checkCollisionsWithPortalBPlayerTwo(player: PlayerTwo) {
        for (portalb in portalBObjects) {
            val rect = portalb.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                azul = true
                break
            }
        }
    }

    private fun checkCollisionsWithBoundsPlayerTwo(player: PlayerTwo) {
        for (bounds in BoundsObjects) {
            val rect = bounds.rectangle
            if (player.getBoundingBox().overlaps(rect)) {
                lost = true
                break
            }
        }
    }


    override fun handleInput() {
        for (i in 0 until 10) {
            if (Gdx.input.isTouched(i) && !ispaused && !lost && (!rojo || !azul)) {
                val touchX = Gdx.input.getX(i).toFloat()
                val touchY = (Gdx.graphics.height - Gdx.input.getY(i)).toFloat()

                // izquierda para p1
                if (touchX >= 10 && touchX <= 10 + leftr.width && touchY >= 10 && touchY <= 10 + leftr.height) {
                    player1?.setPosition(player1!!.x - 3, player1!!.y.toFloat())
                }

                // derecha para p1
                if (touchX >= 175 && touchX <= 175 + leftr.width && touchY >= 10 && touchY <= 10 + leftr.height) {
                    player1?.setPosition(player1!!.x + 3, player1!!.y.toFloat())
                }

                // salto para p1
                if (touchX >= 10 && touchX <= 10 + leftr.width && touchY >= 165 && touchY <= 165 + leftr.height) {
                    player1?.setPosition(player1!!.x, (player1!!.y + 2).toFloat())
                }

                // izquierda para p2
                if (touchX >= 1200 && touchX <= 1200 + leftr.width && touchY >= 10 && touchY <= 10 + leftr.height) {
                    player2?.setPosition(player2!!.x - 3, player2!!.y.toFloat())
                }

                // derecha para p2
                if (touchX >= 1365 && touchX <= 1365 + leftr.width && touchY >= 10 && touchY <= 10 + leftr.height) {
                    player2?.setPosition(player2!!.x + 3, player2!!.y.toFloat())
                }

                // salto para p2
                if (touchX >= 1365 && touchX <= 1365 + leftr.width && touchY >= 165 && touchY <= 165 + leftr.height) {
                    player2?.setPosition(player2!!.x, (player2!!.y + 2).toFloat())
                }

                if (touchX >= (w / 2) - 50f && touchX <= (w / 2) - 50f + pause.width &&
                    touchY >= (h - 150).toFloat() && touchY <= (h - 150).toFloat() + pause.height) {
                    ispaused = true
                }
            }
        }

        if (Gdx.input.justTouched() && ispaused) {
            val touchX = Gdx.input.x.toFloat()
            val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()

            if (ispaused) {
                if (touchX >= 450f && touchX <= 450f + go_back.width &&
                    touchY >= -25f && touchY <= -25f + go_back.height
                ) {
                    gsm?.set(Fase1State(gsm))
                }
            }
            if (ispaused) {
                if (touchX >= 640f && touchX <= 640f + 250f &&
                    touchY >= -30f && touchY <= -30f + 250f
                ) {
                    ispaused = false
                }

                if (Gdx.input.justTouched()) {
                    val touchX = Gdx.input.x.toFloat()
                    val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
                    if (touchX >= 900f && touchX <= 900f + restart.width &&
                        touchY >= 30f && touchY <= 30f + restart.height
                    ) {
                        gsm?.set(levelTwo(gsm))
                    }
                }

                // Verificar botón de música
                if (touchX >= 750f && touchX <= (750f + indicator.width) && touchY >= 385f && touchY <= 385f + indicator.height) {
                    music = !music // Cambiar el estado de la música
                    preferences.putBoolean("music", music)
                    preferences.flush()
                }

                // Verificar botón de efectos de sonido
                if (touchX >= 750f && touchX <= (750f + indicator.width) && touchY >= 200f && touchY <= 200f + indicator.height) {
                    fx = !fx // Cambiar el estado de los efectos de sonido
                    preferences.putBoolean("fx", fx)
                    preferences.flush()
                }
            }
        }
        if (Gdx.input.justTouched() && lost) {
            if (Gdx.input.justTouched()) {
                val touchX = Gdx.input.x.toFloat()
                val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
                if (touchX >= 900f && touchX <= 900f + restart.width &&
                    touchY >= 30f && touchY <= 30f + restart.height
                ) {
                    gsm?.set(levelTwo(gsm))
                }
            }
            if (Gdx.input.justTouched()) {
                val touchX = Gdx.input.x.toFloat()
                val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
                if (touchX >= 450f && touchX <= 450f + go_back.width &&
                    touchY >= -25f && touchY <= -25f + go_back.height) {
                    gsm?.set(Fase1State(gsm))
                }
            }
        }
        if (Gdx.input.justTouched() && azul && rojo) {
            if (Gdx.input.justTouched()) {
                val touchX = Gdx.input.x.toFloat()
                val touchY = (Gdx.graphics.height - Gdx.input.y).toFloat()
                if (touchX >= 650f && touchX <= 650f + go_back.width &&
                    touchY >= -10f && touchY <= -10f + go_back.height) {
                    gsm?.set(Fase1State(gsm))
                }
            }
        }
    }


    override fun update(dt: Float) {
        handleInput()
        player1?.let {
            checkCollisionsWithSuelo(it)
            checkCollisionsWithLava(it)
            checkCollisionsWithAgua(it)
            checkCollisionsWithportalR(it)
            checkCollisionsWithBounds(it)
        }

        player2?.let {
            checkCollisionsWithSueloPlayerTwo(it)
            checkCollisionsWithLavaPlayerTwo(it)
            checkCollisionsWithAguaPlayerTwo(it)
            checkCollisionsWithPortalBPlayerTwo(it)
            checkCollisionsWithBoundsPlayerTwo(it)
        }
        if (music){
            gametheme.play()
            gametheme.isLooping()
        }
        if (!music){
            gametheme.stop()
        }
        if (rojo && azul){
            gametheme.stop()
        }
    }

    override fun render(spriteBatch: SpriteBatch?) {
        ScreenUtils.clear(0f, 0f, 0f, 1f)

        camera.update()
        renderer?.setView(camera)
        renderer?.render()
        if (!ispaused){
            player1?.render()
            player2?.render()
        }

        spriteBatch?.begin()
        spriteBatch?.draw(pause, (w/2)-100.toFloat(), (h - 175).toFloat(), 200f, 200f)
        spriteBatch?.draw(leftr, 0F, 10F)
        spriteBatch?.draw(rightr, 175F, 10F)
        spriteBatch?.draw(jumpr, 0F, 165F)
        spriteBatch?.draw(leftb, 1200F, 10F)
        spriteBatch?.draw(rightb, 1365F, 10F)
        spriteBatch?.draw(jumpb, 1365F, 165F)
        if (ispaused){
            spriteBatch?.setColor(0f, 0f, 0f, 0.5f)
            spriteBatch?.draw(shadow, 0f, 0f, w.toFloat(), h.toFloat())
            spriteBatch?.setColor(1f, 1f, 1f, 1f)
            spriteBatch?.draw(
                settings_menu,
                ((w / 2) - (settings_menu.width / 2)).toFloat(),
                ((h / 2) - (settings_menu.height / 2)).toFloat()
            )
            spriteBatch?.draw(go_back, 450f, -10f)
            spriteBatch?.draw(play_btn, 640f, -30f, 250f, 250f)
            spriteBatch?.draw(restart, 850f, -30f, 250f, 250f)
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
        }
        if(lost){
            spriteBatch?.setColor(0f, 0f, 0f, 0.5f)
            spriteBatch?.draw(shadow, 0f, 0f, w.toFloat(), h.toFloat())
            spriteBatch?.setColor(1f, 1f, 1f, 1f)
            spriteBatch?.draw(menulost, w/4f, 50f)
            spriteBatch?.draw(restart, 850f, -30f, 250f, 250f)
            spriteBatch?.draw(go_back, 450f, -10f)
        }
        if(azul && rojo){
            spriteBatch?.setColor(0f, 0f, 0f, 0.5f)
            spriteBatch?.draw(shadow, 0f, 0f, w.toFloat(), h.toFloat())
            spriteBatch?.setColor(1f, 1f, 1f, 1f)
            spriteBatch?.draw(menuwin, w/4f, 50f)
            spriteBatch?.draw(go_back, 650f, -10f)
        }
        spriteBatch?.end()
    }

    override fun dispose() {
        gametheme.dispose()
        map?.dispose()
        renderer?.dispose()
        player1?.dispose()
        player2?.dispose()
    }
}
