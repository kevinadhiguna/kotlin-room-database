package com.example.kotlinroomdatabase.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
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

        binding.updateBtn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.updateFirstNameEt.text.toString()
        val lastName = binding.updateLastNameEt.text.toString()
        // val age = binding.updateAgeEt.text.toString() // <- Error : Type mismatch. Required: Int , Found: String.
        val age = Integer.parseInt(binding.updateAgeEt.text.toString()) // Parses a string returns an integer.

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

    // Inflate the layout to our menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    // Handle clicks on menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    // Implement logic to delete a user
    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->     // Make a "Yes" option and set action if the user selects "Yes"
            mUserViewModel.deleteUser(args.currentUser)    // Execute : delete user
            Toast.makeText(                                // Notification if a user is deleted successfully
                    requireContext(),
                    "Successfully removed ${args.currentUser.firstName}",
                    Toast.LENGTH_SHORT)
                    .show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment) // Navigate to List Fragment after deleting a user
        }
        builder.setNegativeButton("No") { _, _ -> }    // Make a "No" option and set action if the user selects "No"
        builder.setTitle("Delete ${args.currentUser.firstName} ?")  // Set the title of the prompt with a sentence saying the first name of the user inside the app (using template string)
        builder.setMessage("Are you sure to remove ${args.currentUser.firstName} ?")  // Set the message of the prompt with a sentence saying the first name of the user inside the app (using template string)
        builder.create().show()  // Create a prompt with the configuration above to ask the user (the real app user which is human)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // <- whenever we destroy our fragment, _binding is set to null. Hence it will avoid memory leaks.
    }
}