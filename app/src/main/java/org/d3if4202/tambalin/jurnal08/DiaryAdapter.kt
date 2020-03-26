package org.d3if4202.tambalin.jurnal08

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if4202.tambalin.jurnal08.database.DiaryEntity
import org.d3if4202.tambalin.jurnal08.databinding.TextItemDiaryBinding

class DiaryAdapter(val clickListener: DiaryListener) :
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
        fun bind(
            item: DiaryEntity,
            clickListener: DiaryListener
        ) {
            binding.diary = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        val tanggalDiary: TextView = binding.tvTanggalDiary
        val isiDiary: TextView = binding.tvIsiDiary
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class DiaryDiffCalback : DiffUtil.ItemCallback<DiaryEntity>() {
        override fun areItemsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
            return oldItem.idDiary == newItem.idDiary
        }

        override fun areContentsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
            return oldItem == newItem
        }
    }

    class DiaryListener(val clickListener: (idDiary: Long, isiDiary: String) -> Unit) {
        fun onClick(diaryId: Long, diaryText: String) = clickListener(diaryId, diaryText)
    }

}