package org.d3if4202.tambalin.jurnal08

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.d3if4202.tambalin.jurnal08.database.DiaryEntity

@BindingAdapter("tanggalDiary")
fun TextView.setTanggalDiary(item: DiaryEntity?) {
    item?.let {
        text = ubahDateToString(item.tanggalDiary)
    }
}

@BindingAdapter("isiDiary")
fun TextView.setIsiDiary(item: DiaryEntity?) {
    item?.let {
        text = item.isiDiary
    }
}