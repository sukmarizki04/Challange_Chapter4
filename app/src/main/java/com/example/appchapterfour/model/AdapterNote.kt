package com.example.appchapterfour.model

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appchapterfour.R
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
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listnote[position])
    }

    override fun getItemCount(): Int {
        return listnote.size
    }
    inner class NoteViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SuspicitionIdentation")
        fun bind(note: Anote){
            binding.dataNote == note
            binding.ivDelete.setOnClickListener{
                    val dialog = Dialog(context)
                    dialog.setContentView(R.layout.custom_dialog_delete)
                    dialog.window.
            }

        }
    }

}

interface OnAdapterListener{

}
