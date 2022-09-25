package com.example.appchapterfour.model

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appchapterfour.center.asist.NoteDiffCall
import com.example.appchapterfour.data.Anote
import com.example.appchapterfour.databinding.ListItemBinding

class AdapterNote(var lister: OnAdapterListener): RecyclerView.Adapter<AdapterNote.NoteViewHolder>() {

    private lateinit var context: Context
    val listnote = ArrayList<Anote>()

    fun setListNotes(listNotes: List<Anote>){
        val diffCallback = NoteDiffCall(this.listnote, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listnote.clear()
        this.listnote.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = listitem
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    inner class NoteViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

}

interface OnAdapterListener{

}
