package com.example.kotlinroomdatabase.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.kotlinroomdatabase.R

class UpdateFragment : Fragment() {

    // private var _binding: FragmentAddBinding? = null
    // private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // _binding = FragmentAddBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.findViewById<TextView>(R.id.updateFirstName_et).setText(args.currentUser.firstName)
        view.findViewById<TextView>(R.id.updateLastName_et).setText(args.currentUser.lastName)
        view.findViewById<TextView>(R.id.updateAge_et).setText(args.currentUser.age.toString())

        return view
        // return binding.root
    }

    private fun updateItem() {
        // Adding '?' is a temporary solution
        val firstName = view?.findViewById<TextView>(R.id.updateFirstName_et)?.text
        val lastName = view?.findViewById<TextView>(R.id.updateLastName_et)?.text
        val age = view?.findViewById<TextView>(R.id.updateAge_et)?.text.toString()
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}