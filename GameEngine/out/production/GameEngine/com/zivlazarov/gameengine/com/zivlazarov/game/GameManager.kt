package com.zivlazarov.gameengine.com.zivlazarov.game

import com.zivlazarov.gameengine.AbstractGame
import com.zivlazarov.gameengine.GameEngine
import com.zivlazarov.gameengine.Renderer
import java.awt.event.KeyEvent

class GameManager constructor() : AbstractGame() {

    init {

    }

    override fun update(gameEngine: GameEngine, dt: Float) {
        if (gameEngine.getInput().isKeyDown(KeyEvent.VK_A)) {
            println("A was Pressed!")
        }
    }

    override fun render(gameEngine: GameEngine, renderer: Renderer) {

    }

}

fun main(args : Array<String>) {
    var gameEngine = GameEngine(GameManager())
    gameEngine.start()
}