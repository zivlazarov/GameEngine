package com.zivlazarov.gameengine.com.zivlazarov.audio

import javax.sound.sampled.*
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream

class SoundClip(path: String) {

    private var clip: Clip? = null
    private var gainControl: FloatControl? = null

    init {

        try {
            val audioSrc = SoundClip::class.java.getResourceAsStream(path)
            val bufferedIn = BufferedInputStream(audioSrc)
            val ais = AudioSystem.getAudioInputStream(bufferedIn)
            val baseFormat = ais.format
            val decodeFormat = AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.sampleRate,
                    16,
                    baseFormat.channels,
                    baseFormat.channels * 2,
                    baseFormat.sampleRate,
                    false)

            val dais = AudioSystem.getAudioInputStream(decodeFormat, ais)

            clip = AudioSystem.getClip()
            clip!!.open(dais)

            gainControl = clip!!.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl

        } catch (e: UnsupportedAudioFileException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: LineUnavailableException) {
            e.printStackTrace()
        }

    }

    fun play() {
        if (clip == null) {
            return
        }

        stop()
        clip!!.framePosition = 0

        while (!clip!!.isRunning) {
            clip!!.start()
        }
    }

    fun stop() {
        if (clip!!.isRunning) {
            clip!!.stop()
        }
    }

    fun close() {
        stop()
        clip!!.drain()
        clip!!.close()
    }

    fun loop() {
        clip!!.loop(Clip.LOOP_CONTINUOUSLY)
        play()
    }

    fun setVolume(value: Float) {
        gainControl!!.value = value
    }

    val isRunning: Boolean
        get() = clip!!.isRunning
}
