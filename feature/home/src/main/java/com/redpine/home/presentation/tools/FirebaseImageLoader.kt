/*
package com.redpine.home.presentation.tools

import android.util.Log
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StreamDownloadTask

import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.security.MessageDigest

*/
/**
 * ModelLoader implementation to download images from FirebaseStorage with Glide.
 *
 *
 *
 * First, register this class in your AppGlideModule:
 * <pre>
 * @Override
 * public void registerComponents(Context context, Registry registry) {
 * // Register FirebaseImageLoader to handle StorageReference
 * registry.append(StorageReference.class, InputStream.class,
 * new FirebaseImageLoader.Factory());
 * }
</pre> *
 *
 *
 *
 * Then load a StorageReference into an ImageView.
 * <pre>
 * StorageReference ref = FirebaseStorage.getInstance().getReference().child("myimage");
 * ImageView iv = (ImageView) findViewById(R.id.my_image_view);
 *
 * GlideApp.with(this)
 * .load(ref)
 * .into(iv);
</pre> *
 *//*

class FirebaseImageLoader : ModelLoader<StorageReference, InputStream> {


    */
/**
     * Factory to create FirebaseImageLoader].
     *//*

    class Factory : ModelLoaderFactory<StorageReference, InputStream> {

        override fun build(factory: MultiModelLoaderFactory): ModelLoader<StorageReference, InputStream> {
            return FirebaseImageLoader()
        }

        override fun teardown() {
            // No-op
        }
    }

    override fun buildLoadData(reference: StorageReference,
                               height: Int,
                               width: Int,
                               options: Options): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(
            FirebaseStorageKey(reference),
            FirebaseStorageFetcher(reference))
    }

    override fun handles(reference: StorageReference): Boolean {
        return true
    }

    private class FirebaseStorageKey(private val mRef: StorageReference) : Key {

        override fun updateDiskCacheKey(digest: MessageDigest) {
            digest.update(mRef.path.toByteArray(Charset.defaultCharset()))
        }
    }

    private class FirebaseStorageFetcher(private val mRef: StorageReference) : DataFetcher<InputStream> {
        private var mStreamTask: StreamDownloadTask? = null
        private var mInputStream: InputStream? = null

        override fun loadData(priority: Priority,
                              callback: DataFetcher.DataCallback<in InputStream>) {
            mStreamTask = mRef.stream
            mStreamTask!!
                .addOnSuccessListener { snapshot ->
                    mInputStream = snapshot.stream
                    callback.onDataReady(mInputStream)
                }
                .addOnFailureListener { e -> callback.onLoadFailed(e) }
        }

        override fun cleanup() {
            // Close stream if possible
            if (mInputStream != null) {
                try {
                    mInputStream!!.close()
                    mInputStream = null
                } catch (e: IOException) {
                    Log.w(TAG, "Could not close stream", e)
                }

            }
        }

        override fun cancel() {
            // Cancel task if possible
            if (mStreamTask != null && mStreamTask!!.isInProgress) {
                mStreamTask!!.cancel()
            }
        }

        override fun getDataClass(): Class<InputStream> {
            return InputStream::class.java
        }

        override fun getDataSource(): DataSource {
            return DataSource.REMOTE
        }
    }

    companion object {

        private val TAG = "FirebaseImageLoader"
    }
}*/
