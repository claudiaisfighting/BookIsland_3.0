package com.example.bookisland

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            val i = Intent(view.context, MainActivity::class.java)
            startActivity(i)
        }

        // Inflate the layout for this fragment
        return view
    }


    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

}