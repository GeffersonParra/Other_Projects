package com.mygdx.game

import States.GameStateManager
import States.MenuState
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MyGdxGame : ApplicationAdapter() {
    private var gsm: GameStateManager? = null
    private var batch: SpriteBatch? = null

    override fun create() {
        batch = SpriteBatch()
        gsm = GameStateManager()
        gsm!!.push(MenuState(gsm))
    }

    override fun render() {
        Gdx.gl.glClearColor(110f, 33f, 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gsm!!.update(Gdx.graphics.deltaTime)
        batch?.let { gsm!!.render(it) }
    }

    override fun dispose() {
        batch!!.dispose()
    }
}