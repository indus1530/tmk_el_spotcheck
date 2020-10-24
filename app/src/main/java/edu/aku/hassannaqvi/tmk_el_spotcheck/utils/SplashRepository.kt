package edu.aku.hassannaqvi.tmk_el_spotcheck.utils

import android.content.Context
import android.widget.ArrayAdapter
import edu.aku.hassannaqvi.tmk_el_spotcheck.contracts.UCContract
import edu.aku.hassannaqvi.tmk_el_spotcheck.core.DatabaseHelper
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.other.SplashscreenActivity.Companion.ucs
import edu.aku.hassannaqvi.tmk_el_spotcheck.ui.other.SplashscreenActivity.Companion.ucsMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private suspend fun getEnumGeoArea(context: Context) = withContext(Dispatchers.IO) {
    val db = DatabaseHelper(context)
    return@withContext db.uCs
}

fun setUcs(def: MutableList<UCContract>, adapter: ArrayAdapter<String>) {
    def.forEach { item ->
        ucs.add(item.uc_name)
        adapter.notifyDataSetChanged()
        ucsMap[item.uc_name] = item
    }
}

suspend fun populatingSpinners(context: Context, adapter: ArrayAdapter<String>) {
    GlobalScope.launch {
        val def = withContext(Dispatchers.Main) { getEnumGeoArea(context) }
        if (def.isNotEmpty())
            withContext(Dispatchers.Main) { setUcs(def, adapter) }
    }
}

private fun String.partialList(min: Int, max: Int): List<String> {
    val items = this.split("|")
    return when {
        items.size < max || items.size < min -> items.subList(0, 0)
        else -> items.subList(min, max)
    }
}