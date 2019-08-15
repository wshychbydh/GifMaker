package com.cool.eye.gifmaker.utils

import com.cool.eye.gifmaker.model.GifModel
import com.cool.eye.gifmaker.support.AnimatedGifEncoder
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 *Created by ycb on 2019/8/15 0015
 */
object GifUtil {

    /**
     *@param gif
     *@return Gif's absolute address if create successful, null otherwise
     */
    fun create(gif: GifModel): String? {
        val baos = ByteArrayOutputStream()
        val gifEncoder = AnimatedGifEncoder()
        gifEncoder.start(baos)
        gifEncoder.setRepeat(gif.repeat)//设置生成gif的开始播放时间。0为立即开始播放
        gifEncoder.setDelay(gif.delay)
        gif.frames.forEach {
            gifEncoder.addFrame(it)
        }
        gifEncoder.finish()
        try {
            val fos = FileOutputStream(gif.path)
            baos.writeTo(fos)
            baos.flush()
            fos.flush()
            baos.close()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return gif.path
    }
}