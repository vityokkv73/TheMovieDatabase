package com.deerhunter.themoviedatabase.ui.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.Px
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.deerhunter.themoviedatabase.R
import com.deerhunter.themoviedatabase.ui.images.GlideApp
import java.lang.ref.WeakReference

private const val NO_OVERRIDE = -1

fun ImageView.loadImage(
    loadingImage: Any?,
    @Px width: Int = NO_OVERRIDE,
    @Px height: Int = NO_OVERRIDE,
    errorDrawable: Drawable? = context.getDrawable(R.color.image_background_error),
    placeholderDrawable: Drawable? = context.getDrawable(R.color.image_background),
    centerCrop: Boolean = false,
    fitCenter: Boolean = false,
    animate: Boolean = true,
    useSimpleTarget: Boolean = false,
    glideRequest: RequestBuilder<Drawable>? = null,
    requestListener: RequestListener<Drawable>? = null,
    vararg transformations: Transformation<Bitmap>
) {
    if (!isValidGlideContext(context) || (loadingImage is String && loadingImage.isEmpty())) {
        return
    }

    val request = glideRequest?.load(loadingImage) ?: GlideApp.with(context).load(loadingImage).listener(requestListener)
    val defaultTransformations = mutableListOf<Transformation<Bitmap>>()

    if (fitCenter) {
        defaultTransformations.add(FitCenter())
    } else if (centerCrop) {
        defaultTransformations.add(CenterCrop())
    }

    val options = RequestOptions().apply {
        error(errorDrawable)
        placeholder(placeholderDrawable)

        if (defaultTransformations.isNotEmpty() || transformations.isNotEmpty()) {
            transform(*defaultTransformations.plus(transformations).toTypedArray())
        }

        if (!animate) {
            dontAnimate()
        }

        if (width != NO_OVERRIDE && height != NO_OVERRIDE) {
            override(width, height)
        }
    }

    val requestBuilder = request.apply(options)

    // using SimpleTarget prevents flickering of ImageView when we call RecyclerView's notifyDataSetChanged() or notifyItemChanged() functions
    if (useSimpleTarget) {
        @Suppress("UNCHECKED_CAST")
        var simpleTarget = getTag(id) as? CustomTarget<Drawable>
        if (simpleTarget == null) {
            val imageRef = WeakReference(this)
            simpleTarget = object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    imageRef.get()?.setImageDrawable(resource)
                }

                override fun onLoadStarted(placeholder: Drawable?) {
                    super.onLoadStarted(placeholder)
                    imageRef.get()?.setImageDrawable(placeholder)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    imageRef.get()?.setImageDrawable(errorDrawable)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    imageRef.get()?.setImageDrawable(placeholder)
                }
            }
            setTag(id, simpleTarget)
        } else {
            GlideApp.with(context).clear(simpleTarget)
        }
        requestBuilder.into(simpleTarget)
    } else {
        requestBuilder.into(this)
    }
}

fun ImageView.loadImageWithoutPlaceholder(
    imageLink: String?,
    vararg bitmapTransformations: Transformation<Bitmap>,
    centerCrop: Boolean = false,
    fitCenter: Boolean = false,
    animate: Boolean = false,
    useSimpleTarget: Boolean = false,
    glideRequest: RequestBuilder<Drawable>? = null
) {
    loadImage(
        imageLink,
        errorDrawable = null,
        placeholderDrawable = null,
        centerCrop = centerCrop,
        fitCenter = fitCenter,
        animate = animate,
        glideRequest = glideRequest,
        useSimpleTarget = useSimpleTarget,
        transformations = *bitmapTransformations
    )
}

private fun isValidGlideContext(context: Context?): Boolean {
    if (context == null) return false

    if (context is Activity) {
        if (context.isDestroyed || context.isFinishing) {
            return false
        }
    }
    return true
}

fun ImageView.stopImageLoading() {
    GlideApp.with(context).clear(this)
}