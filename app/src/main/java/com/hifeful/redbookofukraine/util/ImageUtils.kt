package com.hifeful.redbookofukraine.util

import android.content.ContentResolver
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.model.MarkerOptions
import com.hifeful.redbookofukraine.R
import kotlin.math.ceil

const val PACKAGE_NAME = "com.hifeful.redbookofukraine"
const val DRAWABLE_FOLDER = "drawable"

fun prepareBitmap(
    context: Context,
    fileName: String,
): Bitmap {
    return Glide.with(context)
        .asBitmap()
        .override(50)
        .load(context.resources.getIdentifier(fileName, DRAWABLE_FOLDER, context.packageName))
        .listener(object : RequestListener<Bitmap> {
            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return true
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        }).submit().get()
}

fun dp(context: Context, value: Int): Int {
    return if (value == 0) {
        0
    } else ceil(context.resources.displayMetrics.density * value).toInt()
}

fun getUriToResource(fileName: String): String {
    return "${ContentResolver.SCHEME_ANDROID_RESOURCE}://$PACKAGE_NAME/$DRAWABLE_FOLDER/$fileName"
}