package com.example.appchapterfour.presentation.loginfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.appchapterfour.R
import com.example.appchapterfour.center.MainActivity
import com.example.appchapterfour.databinding.FragmentFormLoginBinding
import com.example.appchapterfour.di.ServiceLocator
import com.example.appchapterfour.presentation.utills.viewModelFactory

class FormLogin : Fragment() {

    private lateinit var binding : FragmentFormLoginBinding
    private var listener: OnIdUserChangedListener? = null

    fun setListener(listener: OnIdUserChangedListener) {
        this.listener = listener
    }

    private val viewModel: ViewModelLogin by viewModelFactory {
        ViewModelLogin(ServiceLocator.provideLocalRepository(requireContext()))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormLoginBinding.inflate(inflater, container ,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginValidation()
        binding.btnLogin.setOnClickListener {
            if (formValidation()) {
                succesLogin()
            }
        }

        binding.tvRegiter.setOnClickListener {
            findNavController().navigate(R.id.action_formLogin_to_fragmentRegister)
        }
    }

    private fun loginValidation() {
        if (viewModel.getIdPreference() != 0) {
            requireActivity().run {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun succesLogin() {
        viewModel.getIdFromEmail(binding.etLoginEmail.text.toString())
        viewModel.idFromEmail.observe(requireActivity()) {
            viewModel.setIdPreference(it)
            listener?.onIdUserChanged()
        }

        requireActivity().run {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun formValidation (): Boolean {
        val email = binding.etLoginEmail.text.toString()
        val pass = binding.etLoginPassword.text.toString()
        var validateForm = true

        if (email.isEmpty()) {
            validateForm = false
            binding.tilEtLoginEmail.isErrorEnabled = true
            binding.tilEtLoginEmail.error = "Don't Empty The Field"
        } else { binding.tilEtLoginEmail.isErrorEnabled = false }

        if (pass.isEmpty()) {
            validateForm = false
            binding.tilEtLoginPassword.isErrorEnabled = true
            binding.tilEtLoginPassword.error = "Don't Empty The Field"
        } else {
            validateForm = false
            viewModel.checkEmailPassword(email, pass)
            viewModel.isPassCorect.observe(requireActivity()) {
                if (!it) {
                    binding.tilEtLoginPassword.isErrorEnabled = true
                    binding.tilEtLoginPassword.error = "Check Your Email & Password!"
                } else {
                    validateForm = true
                    binding.tilEtLoginPassword.isErrorEnabled = false
                }
            }
        }
        return validateForm
    }
}

interface OnIdUserChangedListener{
    fun onIdUserChanged()
}