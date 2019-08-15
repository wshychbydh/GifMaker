package com.cool.eye.gifmaker.support

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 *Created by ycb on 2019/8/15 0015
 */
//@GlideModule
//class ClientGlideModule : AppGlideModule() {
//
//  override fun applyOptions(context: Context, builder: GlideBuilder) {
//    val options = RequestOptions()
//        .dontAnimate()
//        .format(DecodeFormat.PREFER_RGB_565)
//        .disallowHardwareConfig()
//    builder.setDefaultRequestOptions(options)
//  }
//
//  override fun registerComponents(context: Context, glide: Glide, registry: Registry) {}
//}