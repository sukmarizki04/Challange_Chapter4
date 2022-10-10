package com.example.appchapterfour.presentation.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.appchapterfour.R
import com.example.appchapterfour.data.entity.AccountEntity
import com.example.appchapterfour.databinding.FragmentRegisterBinding
import com.example.appchapterfour.di.ServiceLocator
import com.example.appchapterfour.presentation.utills.viewModelFactory
import com.example.appchapterfour.wrapper.Resource


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModelFactory {
        RegisterViewModel(ServiceLocator.provideLocalRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDaftar.setOnClickListener {
            submitRegisterForm()
        }
        observeAction()
    }

    private fun observeAction() {
        viewModel.newAccount.observe(requireActivity()){
            when(it) {
                is Resource.Error -> {
                    setFormEnabled(true)
                    Toast.makeText(requireContext(), "There's Error When Inputting Data", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                is Resource.Loading -> {
                    setFormEnabled(false)
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Success Creating Your Account", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun setFormEnabled(b: Boolean) {
        with(binding) {
            etRegUsername.isEnabled = b
            etRegEmail.isEnabled = b
            etRegPass.isEnabled = b
            etkonfir.isEnabled = b
        }
    }

    private fun formValidate() : Boolean{
        val username = binding.etRegUsername.text.toString()
        val email = binding.etRegEmail.text.toString()
        val password = binding.etRegPass.text.toString()
        val comfPass = binding.etkonfir.text.toString()
        var validateForm = true

        if (username.isEmpty()){
            validateForm = false
            binding.tilEtReg.isErrorEnabled = true
            binding.tilEtReg.error = "Username Can't Be Empty"
        } else { binding.tilEtReg.isErrorEnabled = false }

        if (email.isEmpty()){
            validateForm = false
            binding.tilEmail.isErrorEnabled = true
            binding.tilEmail.error = "Email Can't Be Empty"
        } else {
            validateForm = false
            viewModel.checkEmailExcist(email)
            viewModel.isEmailExcist.observe(requireActivity()){
                if (it) {
                    binding.tilEmail.isErrorEnabled = true
                    binding.tilEmail.error = "Email Has Been Registered"
                } else {
                    binding.tilEmail.isErrorEnabled = false
                    validateForm = true
                }
            }
        }

        if (password.isEmpty()){
            validateForm = false
            binding.tilEtRegPass.isErrorEnabled = true
            binding.tilEtRegPass.error = "Password Can't Be Empty"
        } else { binding.tilEtRegPass.isErrorEnabled = false }

        if (comfPass.isEmpty()){
            validateForm = false
            binding.tiKonfir.isErrorEnabled = true
            binding.tiKonfir.error = "Confirmation Password Can't Be Empty"
        } else {
            if (comfPass != password) {
                validateForm = false
                binding.tiKonfir.isErrorEnabled = true
                binding.tiKonfir.error = "Password Didn't Match"
            } else { binding.tiKonfir.isErrorEnabled = false }
        }

        return validateForm
    }

    private fun getEntityDataFromForm(): AccountEntity {
        return AccountEntity(
            username = binding.etRegUsername.text.toString(),
            email = binding.etRegEmail.text.toString(),
            password = binding.etRegPass.text.toString()
        )
    }

    private fun submitRegisterForm(){
        if (formValidate()) {
            viewModel.registerNewAccount(getEntityDataFromForm())
        }
    }

}