package com.example.kotlinroomdatabase.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlinroomdatabase.R
import com.example.kotlinroomdatabase.databinding.FragmentUpdateBinding
import com.example.kotlinroomdatabase.model.User
import com.example.kotlinroomdatabase.viewModel.UserViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateFirstNameEt.setText(args.currentUser.firstName)
        binding.updateLastNameEt.setText(args.currentUser.lastName)
        binding.updateAgeEt.setText(args.currentUser.age.toString())

        binding.updateBtn.setOnClickListener {  }

        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.updateFirstNameEt.text.toString()
        val lastName = binding.updateLastNameEt.text.toString()
        val age = binding.updateAgeEt.text.toString()

        if (inputCheck(firstName, lastName, binding.updateAgeEt.text)) {
            // Create User Object
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)

            // Update Current User
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully !", Toast.LENGTH_SHORT).show()

            // Navigate back to List Fragment
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // <- whenever we destroy our fragment, _binding is set to null. Hence it will avoid memory leaks.
    }
}