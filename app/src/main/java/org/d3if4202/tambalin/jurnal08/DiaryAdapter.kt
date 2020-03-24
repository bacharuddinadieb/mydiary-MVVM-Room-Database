package org.d3if4202.tambalin.jurnal08

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.d3if4202.tambalin.jurnal08.database.DiaryEntity

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {
    var data = listOf<DiaryEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(
                R.layout.text_item_diary,
                parent, false
            )
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tanggalDiary: TextView = itemView.findViewById(R.id.tv_tanggalDiary)
        val isiDiary: TextView = itemView.findViewById(R.id.tv_isiDiary)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.tanggalDiary.text = ubahDateToString(item.tanggalDiary)
        holder.isiDiary.text = item.isiDiary
    }

}