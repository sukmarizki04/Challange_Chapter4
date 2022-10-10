package com.example.appchapterfour.center

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.appchapterfour.center.asist.buttonClickListener
import com.example.appchapterfour.databinding.ActionDialogBinding

class ActionDialog(
    private val listener: buttonClickListener
): DialogFragment() {

    private lateinit var binding: ActionDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActionDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnUbah.setOnClickListener {
            dismiss()
            listener.actionEdit()
        }
        binding.btnHapus.setOnClickListener {
            listener.actionDelete()
        }
    }
}
