package com.zivlazarov.gameengine

open class GameEngine constructor(abstractGame: AbstractGame) : Runnable {

    private lateinit var thread : Thread
    lateinit var window : Window
    private lateinit var renderer : Renderer
    private lateinit var input : Input
    private var abstractGame : AbstractGame
    private var running = false
    private val UPDATE_CAP : Double = 1.0 / 60.0
    var width : Int = 320
    var height : Int = 240
    var scale : Float = 4f
    var title : String = "GameEngine v1.0"

    init {
        this.abstractGame = abstractGame
    }

    fun start() {
        window = Window(this)
        renderer = Renderer(this)
        input = Input(this)

        thread = Thread(this)
        thread.run()

    }

    fun stop() {

    }

    override fun run() {
        running = true
        var render : Boolean = false
        var firstTime : Double = 0.0
        var lastTime : Double = System.nanoTime().toDouble() / 1000000000.0
        var passedTime : Double = 0.0
        var unprocessedTime : Double = 0.0

        var frameTime : Double = 0.0
        var frames : Int = 0
        var fps : Int = 0

        while (running) {
            render = false

            firstTime = System.nanoTime().toDouble() / 1000000000.0
            passedTime = firstTime - lastTime
            lastTime = firstTime

            unprocessedTime += passedTime
            frameTime += passedTime

            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP
                render = true

                abstractGame.update(this, (UPDATE_CAP).toFloat())

                input.update()

                if (frameTime >= 1.0) {
                    frameTime = 0.0
                    fps = frames
                    frames = 0
                }
            }

            if (render) {

                renderer.clear()
                abstractGame.render(this, renderer)
                renderer.drawText("FPS : $fps", 0, 0, (0xff00ffff).toInt())
                window.update()
                frames++

            } else {
                try {
                    Thread.sleep(1)
                } catch (e :InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        dispose()
    }

    fun dispose() {

    }

    fun getInput() : Input {
        return input
    }

}
