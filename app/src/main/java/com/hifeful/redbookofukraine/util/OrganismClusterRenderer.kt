package com.hifeful.redbookofukraine.util

import android.content.Context
import android.graphics.BitmapFactory
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import com.hifeful.redbookofukraine.R

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
        markerOptions.icon(getItemIcon(item))
    }

    override fun onClusterItemUpdated(item: OrganismClusterItem, marker: Marker) {
        marker.setIcon(getItemIcon(item))
    }

    private fun getItemIcon(organismClusterItem: OrganismClusterItem): BitmapDescriptor {
        mImageView.setImageBitmap(BitmapFactory.decodeFile(getUriToResource(organismClusterItem.photoUrl)))
        val icon = mIconGenerator.makeIcon()
        return BitmapDescriptorFactory.fromBitmap(icon)
    }

    override fun shouldRenderAsCluster(cluster: Cluster<OrganismClusterItem>): Boolean =
        false
}