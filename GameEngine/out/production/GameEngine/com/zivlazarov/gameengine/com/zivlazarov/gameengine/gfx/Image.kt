package com.zivlazarov.gameengine.com.zivlazarov.gameengine.gfx

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import kotlin.jvm.javaClass
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by Ziv on 7/8/2017.
 */
class Image constructor(path : String) {

    private var w : Int?
    private var h : Int?
    private var p : IntArray?

    init {
        var image : BufferedImage? = null

        try {
            image = ImageIO.read(Image::class.java.getResourceAsStream(path))
        } catch (e : IOException) {
            e.printStackTrace()
        }

        w = image?.getWidth()
        h = image?.getHeight()
        p = image?.getRGB(0, 0, w!!, h!!, null, 0, w!!)

        image?.flush()
    }

    fun getW() : Int? {
        return w
    }

    fun getH() : Int? {
        return h
    }

    fun getP() : IntArray? {
        return p
    }
}