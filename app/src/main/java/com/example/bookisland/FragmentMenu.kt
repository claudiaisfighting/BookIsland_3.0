package com.example.bookisland

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


// AKA MainActivity3
class FragmentMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_menu)
        val SearchFragment: Fragment = SearchFragment()
        val CollectionsFragment: Fragment = CollectionsFragment()
        val SettingsFragment: Fragment = SettingsFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.search_fragment -> fragment = SearchFragment()
                R.id.collections_fragment -> fragment = CollectionsFragment
                R.id.settings_fragment -> fragment = SettingsFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.search_fragment
    }
    private fun replaceFragment(BookFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, BookFragment)
        fragmentTransaction.commit()
    }

}