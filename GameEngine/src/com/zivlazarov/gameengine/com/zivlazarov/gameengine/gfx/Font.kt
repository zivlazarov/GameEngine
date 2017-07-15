package com.zivlazarov.gameengine.com.zivlazarov.gameengine.gfx

/**
 * Created by Ziv on 7/14/2017.
 */
class Font(path : String) {

    companion object {
        val STANDARD = Font("/fonts/standard.png")
    }

    private var fontImage : Image
    private var offsets : IntArray
    private var widths : IntArray

    init {
        fontImage = Image(path)
        offsets = IntArray(59)
        widths = IntArray(59)

        var unicode = 0

        for (i in 0..fontImage.getW()!!.minus(1)) {

            if (fontImage.getP()!!.get(i) == (0xff0000ff).toInt()) {
                offsets[unicode] = i
            }

            if (fontImage.getP()!!.get(i) == (0xffff0000).toInt()) {
                widths[unicode] = i - offsets[unicode]
                unicode++
            }
        }
    }

    fun getFontImage() : Image {
        return fontImage
    }

    fun getWidths() : IntArray {
        return widths
    }

    fun getOffsets() : IntArray {
        return offsets
    }

}