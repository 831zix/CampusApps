package com.example.campusapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommunityAdapter(private val listData: ArrayList<community>) : RecyclerView.Adapter<CommunityAdapter.DataViewHolder>() {


    private val expandState = SparseBooleanArray()

    init {
        for (i in listData.indices) {
            expandState.put(i, false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_community, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val community = listData[position]

        holder.recruitmentposition.text = community.recruitmentPosition
        holder.organisasi.text = community.organisasi
        holder.slotposisi.text = community.slotPosisi
        holder.tanggalrecruitment.text = community.tanggalRecruitment
        holder.tanggalakhir.text = community.tanggalAkhir
        holder.description.text = community.description

        // Handle expansion logic
        val isExpanded = expandState.get(position)
        holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.clickForMoreInformation.visibility = if (isExpanded) View.GONE else View.VISIBLE

        holder.itemView.setOnClickListener {
            expandState.put(position, !isExpanded)
            notifyItemChanged(position)
        }

        holder.btndaftar.setOnClickListener {
            openUrlInBrowser(holder.itemView.context, community.url)
        }

    }

    private fun openUrlInBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class DataViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val btndaftar: Button = item.findViewById(R.id.btndaftar)
        val recruitmentposition: TextView = item.findViewById(R.id.recruitmentposition)
        val organisasi: TextView = item.findViewById(R.id.organisasi)
        val slotposisi: TextView = item.findViewById(R.id.slotposisi)
        val tanggalrecruitment: TextView = item.findViewById(R.id.tanggalrecruitment)
        val tanggalakhir: TextView = item.findViewById(R.id.tanggalakhir)
        val description: TextView = item.findViewById(R.id.description)
        val expandableLayout: LinearLayout = item.findViewById(R.id.expandable)
        val clickForMoreInformation: TextView = item.findViewById(R.id.clickformore)
    }
}
