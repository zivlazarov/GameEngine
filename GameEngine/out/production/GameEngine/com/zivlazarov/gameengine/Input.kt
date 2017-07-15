package com.zivlazarov.gameengine

import java.awt.event.*

class Input constructor(gameEngine: GameEngine): KeyListener, MouseListener, MouseWheelListener, MouseMotionListener {

    var gameEngine : GameEngine

    private val NUM_KEYS = 256
    private var keys = BooleanArray(NUM_KEYS)
    private var keysLast = BooleanArray(NUM_KEYS)

    private val NUM_BUTTONS = 5
    private var buttons = BooleanArray(NUM_BUTTONS)
    private var buttonsLast = BooleanArray(NUM_BUTTONS)

    private var mouseX : Int
    private var mouseY : Int
    private var scroll : Int

    init {
        this.gameEngine = gameEngine
        mouseX = 0
        mouseY = 0
        scroll = 0

        gameEngine.window.getCanvas().addKeyListener(this)
        gameEngine.window.getCanvas().addMouseMotionListener(this)
        gameEngine.window.getCanvas().addMouseListener(this)
        gameEngine.window.getCanvas().addMouseWheelListener(this)
    }

    fun update() {

        scroll = 0

        for (i in 0..NUM_KEYS - 1) {
            keysLast[i] = keys[i]
        }

        for (i in 0..NUM_BUTTONS - 1) {
            buttonsLast[i] = buttons[i]
        }
    }

    fun isKey(keyCode : Int) : Boolean {
        return keys[keyCode]
    }

    fun isKeyUp(keyCode : Int) : Boolean {
        return !keys[keyCode] && keysLast[keyCode]
    }

    fun isKeyDown(keyCode : Int) : Boolean {
        return keys[keyCode] && !keysLast[keyCode]
    }

    fun isButton(button : Int) : Boolean {
        return buttons[button]
    }

    fun isButtonsUp(button : Int) : Boolean {
        return !buttons[button] && buttonsLast[button]
    }

    fun isButtonsDown(button : Int) : Boolean {
        return buttons[button] && !buttonsLast[button]
    }

    override fun keyTyped(e: KeyEvent) {

    }

    override fun keyPressed(e: KeyEvent) {
        keys[e.keyCode] = true
    }

    override fun keyReleased(e: KeyEvent) {
        keys[e.keyCode] = false
    }

    override fun mouseReleased(e: MouseEvent) {
        buttons[e.button] = false
    }

    override fun mouseEntered(e: MouseEvent) {

    }

    override fun mouseClicked(e: MouseEvent) {

    }

    override fun mouseExited(e: MouseEvent) {

    }

    override fun mousePressed(e: MouseEvent) {
        buttons[e.button] = true
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        scroll = e.wheelRotation
    }

    override fun mouseMoved(e: MouseEvent) {
        mouseX = (e.x / gameEngine.scale).toInt()
        mouseY = (e.y / gameEngine.scale).toInt()
    }

    override fun mouseDragged(e: MouseEvent) {
        mouseX = (e.x / gameEngine.scale).toInt()
        mouseY = (e.y / gameEngine.scale).toInt()
    }

    fun getMouseX() : Int {
        return mouseX
    }

    fun getMouseY() : Int {
        return mouseY
    }

    fun getScroll() : Int {
        return scroll
    }

}