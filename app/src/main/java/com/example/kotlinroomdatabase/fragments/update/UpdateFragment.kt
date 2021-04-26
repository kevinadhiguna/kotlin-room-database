package com.example.kotlinroomdatabase.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.kotlinroomdatabase.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.updateBtn.setOnClickListener {  }

        binding.updateFirstNameEt.setText(args.currentUser.firstName)
        binding.updateLastNameEt.setText(args.currentUser.lastName)
        binding.updateAgeEt.setText(args.currentUser.age.toString())

        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_update, container, false)
//
//        view.findViewById<TextView>(R.id.updateFirstName_et).setText(args.currentUser.firstName)
//        view.findViewById<TextView>(R.id.updateLastName_et).setText(args.currentUser.lastName)
//        view.findViewById<TextView>(R.id.updateAge_et).setText(args.currentUser.age.toString())

        // return view
        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.updateFirstNameEt.text.toString()
        val lastName = binding.updateLastNameEt.text.toString()
        val age = binding.updateAgeEt.text.toString()

        // Adding '?' is a temporary solution
//        val firstName = view?.findViewById<TextView>(R.id.updateFirstName_et)?.text
//        val lastName = view?.findViewById<TextView>(R.id.updateLastName_et)?.text
//        val age = view?.findViewById<TextView>(R.id.updateAge_et)?.text.toString()
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // <- whenever we destroy our fragment, _binding is set to null. Hence it will avoid memory leaks.
    }
}