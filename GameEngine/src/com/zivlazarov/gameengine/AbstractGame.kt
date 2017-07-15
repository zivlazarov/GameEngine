package com.zivlazarov.gameengine

/**
 * Created by Ziv on 7/8/2017.
 */
abstract class AbstractGame {
    abstract fun update(gameEngine: GameEngine, dt: Float)
    abstract fun render(gameEngine: GameEngine, renderer: Renderer)
}