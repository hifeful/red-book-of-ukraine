package com.hifeful.redbookofukraine.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator

class OrganismClusterRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<OrganismClusterItem>
) : DefaultClusterRenderer<OrganismClusterItem>(context, map, clusterManager) {

    private val mIconGenerator = IconGenerator(context)
    private var mImageView: ImageView = ImageView(context)

    init {
        mImageView.layoutParams = ViewGroup.LayoutParams(100, 100)
        mIconGenerator.setContentView(mImageView)
    }

    override fun onBeforeClusterItemRendered(
        item: OrganismClusterItem,
        markerOptions: MarkerOptions
    ) {
        markerOptions.visible(false)
    }

    override fun onClusterItemRendered(clusterItem: OrganismClusterItem, marker: Marker) {
        Glide.with(context)
            .load(context.resources.getIdentifier(clusterItem.organism.photoUrl, DRAWABLE_FOLDER, context.packageName))
            .override(100)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    mImageView.setImageDrawable(resource)
                    val icon = mIconGenerator.makeIcon()
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon))
                    marker.isVisible = true
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    override fun shouldRenderAsCluster(cluster: Cluster<OrganismClusterItem>): Boolean =
        cluster.size > 1


}