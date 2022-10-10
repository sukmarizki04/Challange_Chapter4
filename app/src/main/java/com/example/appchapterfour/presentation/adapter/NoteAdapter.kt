package com.example.appchapterfour.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appchapterfour.data.entity.NoteEntity
import com.example.appchapterfour.databinding.NoteItemBinding

class NoteAdapter(
    private val listener: itemClickListener
): RecyclerView.Adapter<NoteAdapter.NoteListViewHolder>() {

    private lateinit var dataset: List<NoteEntity>
    fun submitData(value: List<NoteEntity>) {
        dataset = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteListViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val note = dataset[position]
        holder.bindingView(note)
    }

    override fun getItemCount(): Int = dataset.size
    class NoteListViewHolder(
        private val binding: NoteItemBinding,
        private val listener: itemClickListener
    ):RecyclerView.ViewHolder(binding.root) {

        fun bindingView(note: NoteEntity) {
            with(itemView) {
                with(binding) {
                    tvItemNotesTitle.text = note.judul
                    tvItemNotesNote.text = note.catatan

                    cvItemNotes.setOnClickListener {
                        listener.onItemClicked()
                    }
                }
            }
        }

    }

}
