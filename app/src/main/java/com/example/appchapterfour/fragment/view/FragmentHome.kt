package com.example.appchapterfour.fragment.view

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appchapterfour.R
import com.example.appchapterfour.data.Anote
import com.example.appchapterfour.data.UserNote
import com.example.appchapterfour.databinding.CustomDialogBinding
import com.example.appchapterfour.databinding.FragmentHomeBinding
import com.example.appchapterfour.model.AdapterNote
import com.example.appchapterfour.model.NoteAddUpdateViewModel

class FragmentHome : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var dialogbinding : CustomDialogBinding
    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel
    lateinit var dataUserShared: SharedPreferences

    private var note : Anote? = null
    private var user : UserNote? = null

    private lateinit var adapter: AdapterNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var context = binding.rvNotes.context
        noteAddUpdateViewModel = obtainViewModel(requireActivity())
        note = Anote()
        user = UserNote()
        dialogbinding = CustomDialogBinding.inflate(layoutInflater)
        dataUserShared = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        getData()
        setadapter()

        binding.fabAdd.setOnClickListener(){
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.custom_dialog);
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            val judul : EditText = dialog.findViewById(R.id.edtJudul)
            val catatan : EditText = dialog.findViewById(R.id.edtCatatan)
            val submit : Button = dialog.findViewById(R.id.btnSubmit)

            submit.setOnClickListener {
                when {
                    judul.
                }
            }
        }

    }

    private fun getData() {
        TODO("Not yet implemented")
    }

    private fun setadapter() {
        TODO("Not yet implemented")
    }

    private fun obtainViewModel(requireActivity: FragmentActivity): NoteAddUpdateViewModel {

    }
}