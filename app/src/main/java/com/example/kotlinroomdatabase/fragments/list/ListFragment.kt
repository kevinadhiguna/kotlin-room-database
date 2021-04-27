package com.example.kotlinroomdatabase.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinroomdatabase.R
import com.example.kotlinroomdatabase.viewModel.UserViewModel
import com.example.kotlinroomdatabase.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        // val view = inflater.inflate(R.layout.fragment_list, container, false) // <- This is not required.

        // RecyclerView
        val adapter = ListAdapter()
        // val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview) // <- This is replaced.
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    // Implement logic to delete all users
    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->     // Make a "Yes" option and set action if the user selects "Yes"
            mUserViewModel.deleteAllUsers()    // Execute : delete all users
            Toast.makeText(                                // Notification if a user is deleted successfully
                    requireContext(),
                    "Successfully removed everything",
                    Toast.LENGTH_SHORT)
                    .show()
            // Note: No need to navigate app user to List Fragment since deleting all users takes place at List Fragment.
        }
        builder.setNegativeButton("No") { _, _ -> }    // Make a "No" option and set action if the user selects "No"
        builder.setTitle("Delete everything ?")  // Set the title of the prompt with a sentence saying the first name of the user inside the app (using template string)
        builder.setMessage("Are you sure to remove everything ?")  // Set the message of the prompt with a sentence saying the first name of the user inside the app (using template string)
        builder.create().show()  // Create a prompt with the configuration above to ask the user (the real app user which is human)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // <- whenever we destroy our fragment, _binding is set to null. Hence it will avoid memory leaks.
    }
}