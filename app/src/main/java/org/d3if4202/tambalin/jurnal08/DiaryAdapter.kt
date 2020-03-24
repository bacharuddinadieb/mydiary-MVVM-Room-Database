package org.d3if4202.tambalin.jurnal08

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if4202.tambalin.jurnal08.database.DiaryEntity
import org.d3if4202.tambalin.jurnal08.databinding.TextItemDiaryBinding

class DiaryAdapter :
    androidx.recyclerview.widget.ListAdapter<DiaryEntity, DiaryAdapter.ViewHolder>(DiaryDiffCalback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val binding = TextItemDiaryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder internal constructor(val binding: TextItemDiaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DiaryEntity) {
            binding.diary = item
            binding.executePendingBindings()
        }

        val tanggalDiary: TextView = binding.tvTanggalDiary
        val isiDiary: TextView = binding.tvIsiDiary
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.tanggalDiary.text = ubahDateToString(item.tanggalDiary)
        holder.isiDiary.text = item.isiDiary
    }

    class DiaryDiffCalback : DiffUtil.ItemCallback<DiaryEntity>() {
        override fun areItemsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
            return oldItem.idDiary == newItem.idDiary
        }

        override fun areContentsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
            return oldItem == newItem
        }
    }

}