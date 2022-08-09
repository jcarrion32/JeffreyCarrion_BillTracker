package com.example.jeffreycarrion_billtracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jeffreycarrion_billtracker.databinding.FragmentCreateAccountPageBinding
import com.google.firebase.auth.FirebaseAuth

class CreateAccountFragment: ViewModelFragment() {
    private lateinit var binding: FragmentCreateAccountPageBinding
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountPageBinding.inflate(layoutInflater)

        binding.btnCreateAccount.setOnClickListener {
            userCreation(binding.etCreateUsername.text.toString(), binding.etCreatePassword.text.toString())
        }

        binding.ibBackLogin.setOnClickListener{
            findNavController().navigate(
                CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment()
            )
        }

        return binding.root
    }

    private fun userCreation(userName: String, password: String) {
//        var user = auth.currentUser
        if (userName.isNotBlank() && password.isNotBlank()
        ) {
            auth.createUserWithEmailAndPassword(
                userName,
                password
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        auth.signOut()
                        Toast.makeText(context, " Account been created", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment())

                    } else {
                        Toast.makeText(context, "Authentication is Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(context, " Empty", Toast.LENGTH_SHORT).show()
        }
    }
}