package com.zivlazarov.gameengine.com.zivlazarov.game

import com.zivlazarov.gameengine.AbstractGame
import com.zivlazarov.gameengine.GameEngine
import com.zivlazarov.gameengine.Renderer
import com.zivlazarov.gameengine.com.zivlazarov.audio.SoundClip
import com.zivlazarov.gameengine.com.zivlazarov.gameengine.gfx.ImageTile
import java.awt.event.KeyEvent

class GameManager constructor() : AbstractGame() {

    private var image : ImageTile
    private var clip : SoundClip

    init {
        image = ImageTile("/test.png", 16, 16)
        clip = SoundClip("/audio/test.wav")
        clip.setVolume(-20f)
    }

    override fun update(gameEngine: GameEngine, dt: Float) {
        if (gameEngine.getInput().isKeyDown(KeyEvent.VK_A)) {
            clip.play()
        }

        temp += dt * 16

        if (temp > 3) {
            temp = 0.0F
        }
    }

    var temp : Float = 0.0F

    override fun render(gameEngine: GameEngine, renderer: Renderer) {
        renderer.drawImageTile(image, gameEngine.getInput().getMouseX() - 8, gameEngine.getInput().getMouseY() - 16, temp.toInt(), 0)
        renderer.drawFillRectangle(gameEngine.getInput().getMouseX() - 16, gameEngine.getInput().getMouseY() - 16, 32, 32, (0xffffccff).toInt())
    }

}

fun main(args : Array<String>) {
    var gameEngine = GameEngine(GameManager())
    gameEngine.start()
}