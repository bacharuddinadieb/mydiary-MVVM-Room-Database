package org.d3if4202.tambalin.jurnal08

import java.text.SimpleDateFormat

fun ubahDateToString(systemTime: Long): String {
    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
        .format(systemTime).toString()
}