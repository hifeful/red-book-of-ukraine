package com.hifeful.redbookofukraine.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hifeful.redbookofukraine.databinding.ItemOrganismBinding
import com.hifeful.redbookofukraine.domain.Organism

class OrganismAdapter : RecyclerView.Adapter<OrganismAdapter.OrganismViewHolder>() {
    var mOrganismList = mutableListOf<Organism>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var mOnOrganismClickListener: OnOrganismClickListener? = null

    inner class OrganismViewHolder(private val organismBinding: ItemOrganismBinding) :
        RecyclerView.ViewHolder(organismBinding.root) {

        fun bind(organism: Organism) {
            organismBinding.apply {
                val context = itemView.context
                val resId = context.resources.getIdentifier(
                    organism.photoUrl,
                    "drawable",
                    context.packageName
                )
                Glide.with(context)
                    .load(resId)
                    .override(300)
                    .centerCrop()
                    .into(organismImage)
                organismImage.transitionName = organism.id.toString()

                organismItemName.text = organism.name
                organismBinding.organismItemNameLatin.text = organism.nameLatin

                root.setOnClickListener { mOnOrganismClickListener?.onoOrganismClick(organism) }
            }
        }

    }

    interface OnOrganismClickListener {
        fun onoOrganismClick(organism: Organism)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganismViewHolder {
        return OrganismViewHolder(ItemOrganismBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun getItemCount(): Int = mOrganismList.size

    override fun onBindViewHolder(holder: OrganismViewHolder, position: Int) {
        holder.bind(mOrganismList[position])
    }

}