package com.hifeful.redbookofukraine.util

import android.content.ContentResolver
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.model.MarkerOptions
import com.hifeful.redbookofukraine.R
import kotlin.math.ceil

const val PACKAGE_NAME = "com.hifeful.redbookofukraine"
const val DRAWABLE_FOLDER = "drawable"

fun createUserBitmap(
    context: Context,
    options: MarkerOptions,
    fileName: String,
    handleBitmap: (MarkerOptions, Bitmap) -> Unit
) {
    val result: Bitmap =
        Bitmap.createBitmap(dp(context, 62), dp(context, 76), Bitmap.Config.ARGB_8888)
    result.eraseColor(Color.TRANSPARENT)
    val canvas = Canvas(result)
    ContextCompat.getDrawable(context, R.drawable.live_pin)?.apply {
        setBounds(0, 0, dp(context, 62), dp(context, 76))
        draw(canvas)
    }
    val roundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bitmapRect = RectF()
    canvas.save()
    Glide.with(context)
        .asBitmap()
        .load(context.resources.getIdentifier(fileName, DRAWABLE_FOLDER, context.packageName))
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                val shader = BitmapShader(resource, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                val matrix = Matrix()
                val scale: Float = dp(context, 52) / resource.width.toFloat()
                matrix.postTranslate(dp(context, 5).toFloat(), dp(context, 5).toFloat())
                matrix.postScale(scale, scale)
                roundPaint.shader = shader
                shader.setLocalMatrix(matrix)
                bitmapRect[
                        dp(context, 5).toFloat(),
                        dp(context, 5).toFloat(),
                        dp(context, 52 + 5).toFloat()
                ] = dp(context, 52 + 5).toFloat()
                canvas.drawRoundRect(
                    bitmapRect,
                    dp(context, 26).toFloat(),
                    dp(context, 26).toFloat(),
                    roundPaint
                )

                handleBitmap(options, result)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}

fun prepareBitmap(
    context: Context,
    options: MarkerOptions,
    fileName: String,
    handleBitmap: (MarkerOptions, Bitmap) -> Unit
) {
    Glide.with(context)
        .asBitmap()
        .placeholder(R.drawable.live_pin)
        .override(50)
        .load(context.resources.getIdentifier(fileName, DRAWABLE_FOLDER, context.packageName))
        .circleCrop()
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                handleBitmap(options, resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}

fun setImage(context: Context, fileName: String, imageView: ImageView) =
    Glide.with(context)
        .load(context.resources.getIdentifier(fileName, DRAWABLE_FOLDER, context.packageName))
        .into(imageView)

fun dp(context: Context, value: Int): Int {
    return if (value == 0) {
        0
    } else ceil(context.resources.displayMetrics.density * value).toInt()
}

fun getUriToResource(fileName: String): String {
    return "${ContentResolver.SCHEME_ANDROID_RESOURCE}://$PACKAGE_NAME/$DRAWABLE_FOLDER/$fileName"
}