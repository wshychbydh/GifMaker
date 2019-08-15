package com.cool.eye.gifmaker

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.cool.eye.gifmaker.model.GifModel
import com.cool.eye.gifmaker.utils.GifUtil
import com.cool.eye.gifmaker.utils.LocalStorage
import com.eye.cool.photo.PhotoDialogFragment
import com.eye.cool.photo.params.ImageParams
import com.eye.cool.photo.support.OnSelectListener
import com.eye.cool.photo.utils.ImageUtil
import kotlinx.android.synthetic.main.activity_gif_maker.*

/**
 *Created by ycb on 2019/8/15 0015
 */
class MakerActivity : AppCompatActivity(), View.OnClickListener {

  private val gifModel = GifModel()

  private lateinit var dialog: PhotoDialogFragment

  private val adapter = GifAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_gif_maker)
    dialog = PhotoDialogFragment.Builder()
        .setImageParams(
            ImageParams.Builder()
                .setOnSelectListener(object : OnSelectListener {
                  override fun onSelect(path: String) {
                    gifModel.addFrame(ImageUtil.getBitmapFromFile(path, 300, 300))
                    adapter.notifyDataSetChanged()
                  }
                }).build()
        )
        .build()
    recyclerView.layoutManager = GridLayoutManager(this, 3)
    recyclerView.adapter = adapter
    resetBtn.setOnClickListener(this)
    addBtn.setOnClickListener(this)
    createBtn.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.resetBtn -> {
        gifModel.reset()
        adapter.notifyDataSetChanged()
      }
      R.id.addBtn -> {
        dialog.show(supportFragmentManager)
      }
      R.id.createBtn -> {
        create()
      }
    }
  }

  private fun create() {
    if (gifModel.frames.isNullOrEmpty()) {
      Toast.makeText(this, "请选择素材", Toast.LENGTH_SHORT).show()
      return
    }
    val progress = ProgressDialog(this)
    progress.show()
    gifModel.path = LocalStorage.composeGifFilePath(this, nameEt.text.toString())
    val delay = delayEt.text.toString()
    gifModel.delay = if (delay.isEmpty()) 500 else delay.toInt()
    val result = GifUtil.create(gifModel)
    if (result.isNullOrEmpty()) {
      Toast.makeText(this, "创建失败!", Toast.LENGTH_SHORT).show()
    } else {
      pathTv.text = result
      Glide.with(showIv).asGif().load(result).into(showIv)
    }
    progress.cancel()
  }

  inner class GifAdapter : RecyclerView.Adapter<GifViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
      return GifViewHolder(ImageView(this@MakerActivity))
    }

    override fun getItemCount(): Int {
      return gifModel.frames.size
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
      (holder.itemView as ImageView).setImageBitmap(gifModel.frames[position])
    }
  }

  inner class GifViewHolder(view: View) : RecyclerView.ViewHolder(view)
}