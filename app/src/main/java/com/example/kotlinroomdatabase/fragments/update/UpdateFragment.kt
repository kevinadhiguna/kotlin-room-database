package com.example.kotlinroomdatabase.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}