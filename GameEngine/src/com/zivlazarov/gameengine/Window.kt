package com.zivlazarov.gameengine

import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferStrategy
import java.awt.image.BufferedImage
import javax.swing.JFrame

open class Window constructor(var gameEngine: GameEngine) {

    private lateinit var frame : JFrame
    private lateinit var image : BufferedImage
    private lateinit var canvas : Canvas
    private lateinit var bs : BufferStrategy
    private lateinit var g : Graphics

    init {
        image = BufferedImage(gameEngine.width, gameEngine.height, BufferedImage.TYPE_INT_RGB)

        canvas = Canvas()
        var s = Dimension((gameEngine.width * gameEngine.scale).toInt(), (gameEngine.height * gameEngine.scale).toInt())
        canvas.preferredSize = s
        canvas.maximumSize = s
        canvas.minimumSize = s

        frame = JFrame(gameEngine.title)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.layout = BorderLayout()
        frame.add(canvas, BorderLayout.CENTER)
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isResizable = false
        frame.isVisible = true

        canvas.createBufferStrategy(2)
        bs = canvas.bufferStrategy
        g = bs.drawGraphics
    }

    fun update() {
        g.drawImage(image, 0, 0, canvas.width, canvas.height, null)
        bs.show()

    }

    fun getImage() : BufferedImage {
        return image
    }

    fun getCanvas() : Canvas {
        return canvas
    }

    fun getFrame() : JFrame {
        return frame
    }


}