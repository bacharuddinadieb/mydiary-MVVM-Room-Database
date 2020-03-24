package org.d3if4202.tambalin.jurnal08

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import org.d3if4202.tambalin.jurnal08.database.DiaryEntity
import java.text.SimpleDateFormat

fun ubahDateToString(systemTime: Long): String {
    return SimpleDateFormat("EEEE, MMMM dd yyyy")
        .format(systemTime).toString()
}

fun formatDiary(diary: List<DiaryEntity>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        diary.forEach {
            append(it.idDiary)
            append("<br>")
            append("${ubahDateToString(it.tanggalDiary)}<br>")
            append(it.isiDiary)
            append("<br>")
            append("<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

class TextItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
