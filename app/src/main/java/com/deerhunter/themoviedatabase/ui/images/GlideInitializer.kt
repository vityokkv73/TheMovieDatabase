package com.deerhunter.themoviedatabase.ui.images

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.*
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.deerhunter.themoviedatabase.data.Images
import com.deerhunter.themoviedatabase.prefs.IConfigurationPrefs
import com.deerhunter.themoviedatabase.ui.images.di.GlideComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import timber.log.Timber
import java.io.InputStream
import javax.inject.Inject

@GlideModule
class GlideInitializer : AppGlideModule() {

    @Inject
    lateinit var configurationPrefs: IConfigurationPrefs

    @Inject
    lateinit var activityManager: ActivityManager

    init {
        XInjectionManager.findComponent<GlideComponent>().inject(this)
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memorySizeCalculator = MemorySizeCalculator.Builder(context).build()

        builder.setMemoryCache(LruResourceCache(getCacheSize()))
            .setDefaultRequestOptions(
                RequestOptions()
                    .format(DecodeFormat.PREFER_RGB_565)
                    .disallowHardwareConfig()
            )

        Timber.d(
            "Glide memory cache size suggestions: memory size calculator = %d, memory manager = %d",
            memorySizeCalculator.memoryCacheSize, activityManager.memoryClass * 1024 * 1024 / 4
        )
        builder.setLogLevel(Log.ERROR)
    }

    private fun getCacheSize() = (activityManager.memoryClass * 1024 * 1024 / 4).toLong()

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        glide.registry.replace(String::class.java, InputStream::class.java, ApiModelLoader.Factory(configurationPrefs.getConfiguration().images))
    }
}

class ApiModelLoader(
    private val images: Images,
    modelLoader: ModelLoader<GlideUrl, InputStream>,
    modelCache: ModelCache<String, GlideUrl>
) : BaseGlideUrlLoader<String>(modelLoader, modelCache) {


    override fun getUrl(model: String, width: Int, height: Int, options: Options): String {
        return images.secureBaseUrl + images.posterSizes.last() + "/" + model.removePrefix("/")
    }

    override fun handles(model: String): Boolean {
        return true
    }

    class Factory(private val images: Images) : ModelLoaderFactory<String, InputStream> {

        private val modelCache = ModelCache<String, GlideUrl>(500)

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
            return ApiModelLoader(
                images,
                multiFactory.build(GlideUrl::class.java, InputStream::class.java),
                modelCache
            )
        }

        override fun teardown() {
        }
    }
}