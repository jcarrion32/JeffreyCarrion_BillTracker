package com.example.jeffreycarrion_billtracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jeffreycarrion_billtracker.databinding.FragmentLoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : ViewModelFragment() {
    private lateinit var binding: FragmentLoginPageBinding
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginPageBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {

            userAuthentication()
//            findNavController().navigate(
//                LoginFragmentDirections.actionLoginPageToCalendarFragment()
//            )
        }

        binding.btnRegisterAccount.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment()
            )
        }


        return binding.root
    }

    private fun userAuthentication() {
        if (binding.etUsername.text.toString().isNotBlank() && binding.etPassword.text.toString().isNotBlank()
        ) {
            auth.signInWithEmailAndPassword(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        Toast.makeText(context, " User is Authenticated", Toast.LENGTH_SHORT).show()
                        binding.txtError.visibility = View.GONE
                        findNavController().navigate(LoginFragmentDirections.actionLoginPageToCalendarFragment())

                    } else {
                        binding.txtError.visibility = View.VISIBLE
                        Toast.makeText(context, "Authentication is Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(context, "Fields shouldn't be empty", Toast.LENGTH_SHORT).show()
        }
    }
}