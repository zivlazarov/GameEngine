package com.zivlazarov.gameengine

import com.zivlazarov.gameengine.com.zivlazarov.gameengine.gfx.Font
import com.zivlazarov.gameengine.com.zivlazarov.gameengine.gfx.Image
import com.zivlazarov.gameengine.com.zivlazarov.gameengine.gfx.ImageTile

import java.awt.image.DataBufferInt

class Renderer(gameEngine: GameEngine) {

    private val pW: Int
    private val pH: Int
    private val p: IntArray
    private val font = Font.STANDARD

    init {
        pW = gameEngine.width
        pH = gameEngine.height
        p = (gameEngine.window.getImage().raster.dataBuffer as DataBufferInt).data
    }

    fun clear() {
        for (i in p.indices) {
            p[i] = 0
        }
    }

    fun setPixel(x: Int, y: Int, value: Int) {
        if (x < 0 || x >= pW || y < 0 || y >= pH || value == 0xffff00ff.toInt()) {
            return
        }
        p[x + y * pW] = value
    }

    fun drawText(text: String, offX: Int, offY: Int, color: Int) {
        var text = text

        text = text.toUpperCase()
        var offset = 0

        for (i in 0..text.length - 1) {
            val unicode = text.codePointAt(i) - 32

            for (y in 0..font.getFontImage().getH()!! - 1) {

                for (x in 0..font.getWidths()[unicode] - 1) {
                    if (font.getFontImage().getP()!![x + font.getOffsets()[unicode] + y * font.getFontImage().getW()!!] == (0xffffffff).toInt()) {
                        setPixel(x + offX + offset, y + offY, color)
                    }
                }
            }

            offset += font.getWidths()[unicode]
        }
    }

    fun drawImage(image: Image, offX: Int, offY: Int) {

        if (offX < -image.getW()!!) {
            return
        }

        if (offY < -image.getH()!!) {
            return
        }

        if (offX >= pW) {
            return
        }

        if (offY >= pH) {
            return
        }

        var newX = 0
        var newY = 0
        var newWidth = image.getW()!!
        var newHeight = image.getH()!!

        if (offX < 0) {
            newX -= offX
        }

        if (offY < 0) {
            newY -= offY
        }

        if (newWidth + offX >= pW) {
            newWidth -= newWidth + offX - pW
        }

        if (newHeight + offY >= pH) {
            newHeight -= newHeight + offY - pH
        }

        for (y in newY..newHeight - 1) {
            for (x in newX..newWidth - 1) {
                setPixel(x + offX, y + offY, image.getP()!![x + y * image.getW()!!])
            }
        }
    }

    fun drawImageTile(image: ImageTile, offX: Int, offY: Int, tileX: Int, tileY: Int) {

        if (offX < -image.getTileW()) {
            return
        }

        if (offY < -image.getTileH()) {
            return
        }

        if (offX >= pW) {
            return
        }

        if (offY >= pH) {
            return
        }

        var newX = 0
        var newY = 0
        var newWidth = image.getTileW()
        var newHeight = image.getTileH()

        if (offX < 0) {
            newX -= offX
        }

        if (offY < 0) {
            newY -= offY
        }

        if (newWidth + offX >= pW) {
            newWidth -= newWidth + offX - pW
        }

        if (newHeight + offY >= pH) {
            newHeight -= newHeight + offY - pH
        }

        for (y in newY..newHeight - 1) {
            for (x in newX..newWidth - 1) {
                setPixel(x + offX, y + offY, image.getP()!![x + tileX * image.getTileW() + (y + tileY * image.getTileH()) * image.getW()!!])
            }
        }
    }

    fun drawRectangle(offX : Int, offY : Int, width : Int, height : Int, color : Int) {
        for (y in 0..height) {
            setPixel(offX, y + offY, color)
            setPixel(offX + width, y + offY, color)
        }

        for (x in 0..width) {
            setPixel(x + offX, offY, color)
            setPixel(x + offX, offY + height, color)

        }
    }

    fun drawFillRectangle(offX : Int, offY : Int, width : Int, height : Int, color : Int) {

        if (offX < -width) {
            return
        }

        if (offY < -height) {
            return
        }

        if (offX >= pW) {
            return
        }

        if (offY >= pH) {
            return
        }

        var newX = 0
        var newY = 0
        var newWidth = width
        var newHeight = height

        if (offX < 0) {
            newX -= offX
        }

        if (offY < 0) {
            newY -= offY
        }

        if (newWidth + offX >= pW) {
            newWidth -= newWidth + offX - pW
        }

        if (newHeight + offY >= pH) {
            newHeight -= newHeight + offY - pH
        }

        for (y in newY..newHeight) {

            for (x in newX..newWidth) {
                setPixel(x + offX, y + offY, color)

            }
        }

    }

}
