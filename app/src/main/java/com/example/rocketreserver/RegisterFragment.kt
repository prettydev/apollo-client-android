package com.example.rocketreserver

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.toDeferred
import com.example.rocketreserver.databinding.RegisterFragmentBinding
import java.lang.Exception

class RegisterFragment : Fragment() {
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitProgressBar.visibility = View.GONE
        binding.submit.setOnClickListener {

            val username = binding.name.text.toString()
            val email = binding.email.text.toString()
            val password1 = binding.password.text.toString();
            val password2 = binding.confirmPassword.text.toString()

            Log.e("error test", username+email+password1+password2);

//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || password1.length === 0 || password2.length === 0) {
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    binding.emailLayout.error = getString(R.string.invalid_email)
//                }
//                if (password1.length === 0) {
//                    binding.passwordLayout.error = getString(R.string.invalid_password)}
//                if (password2.length === 0) {
//                    binding.confirmPasswordLayout.error = getString(R.string.invalid_password)
//                }
//                return@setOnClickListener
//            }

            binding.submitProgressBar.visibility = View.VISIBLE
            binding.submit.visibility = View.GONE
            lifecycleScope.launchWhenResumed {
                val response = try {
                    apolloClient(requireContext()).mutate(RegisterMutation(email, username, password1, password2)).toDeferred().await()
                } catch (e: Exception) {
                    Log.e("graphql exception:", e.toString())
                    null
                }
//
                Log.e("response graphql:", response.toString());

//                val login = response?.data?.login
//                if (login == null || response.hasErrors()) {
//                    binding.submitProgressBar.visibility = View.GONE
//                    binding.submit.visibility = View.VISIBLE
//                    return@launchWhenResumed
//                }
//
//                User.setToken(requireContext(), login)
//                findNavController().popBackStack()
            }
        }
    }
}