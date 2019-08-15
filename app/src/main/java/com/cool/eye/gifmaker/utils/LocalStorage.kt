package com.cool.eye.gifmaker.utils

import android.content.Context
import android.os.Environment
import java.io.File

/**
 * Created by cool on 2018/8/15.
 */
object LocalStorage {

  private const val GIF = "gif" // folder root
  private const val GIF_SUFFIX = ".gif"

  // create image storage dir
  fun composeGifDir(context: Context): StringBuilder {
    val sb = StringBuilder()
    val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    sb.append(folder?.absolutePath ?: context.externalCacheDir)
    sb.append(File.separator)
    sb.append(GIF)
    return sb
  }

  @JvmStatic
  fun composeGifFilePath(context: Context, name: String? = null): String {
    val sb = composeGifDir(context)
    val dir = File(sb.toString())
    if (!dir.exists()) dir.mkdirs()
    val fileName = if (name.isNullOrEmpty()) "${System.currentTimeMillis()}" else name
    sb.append(File.separator).append("$fileName$GIF_SUFFIX")
    return sb.toString()
  }
}