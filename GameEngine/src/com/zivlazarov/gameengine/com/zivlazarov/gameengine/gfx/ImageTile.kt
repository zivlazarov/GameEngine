package com.zivlazarov.gameengine.com.zivlazarov.gameengine.gfx

/**
 * Created by Ziv on 7/14/2017.
 */
class ImageTile(path : String, tileW : Int, tileH : Int): Image(path) {

    private var tileW : Int
    private var tileH : Int

    init {
        this.tileW = tileW
        this.tileH = tileH
    }

    fun getTileW() : Int {
        return tileW
    }

    fun getTileH() : Int {
        return tileH
    }

    fun setTileW(w : Int) {
        tileW = w
    }

    fun setTileH(h : Int) {
        tileH = h
    }

}