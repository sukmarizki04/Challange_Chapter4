package com.example.appchapterfour.presentation.ui.formedit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appchapterfour.R
import com.example.appchapterfour.data.entity.NoteEntity
import com.example.appchapterfour.databinding.FragmentEditNoteBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class  EditNoteBottomSheet(private val notes: NoteEntity) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentEditNoteBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialData()
    }

    private fun initialData() {
        binding.etBotSheetTitleEdit.setText(notes.judul)
        binding.etBotSheetNotesEdit.setText(notes.catatan)
    }


}