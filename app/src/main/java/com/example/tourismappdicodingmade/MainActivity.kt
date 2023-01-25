package com.example.tourismappdicodingmade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.tourismappdicodingmade.databinding.ActivityMainBinding
import com.example.tourismappdicodingmade.ui.favorite.FavoriteFragment
import com.example.tourismappdicodingmade.ui.home.HomeFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainAppBar.toolbar)

        setDrawerToggle()
        setInitialFragment(savedInstanceState)
        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun setDrawerToggle() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawerLayout,
            binding.mainAppBar.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.mainDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setInitialFragment(bundle: Bundle?) {
        if (bundle == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
        }

        supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)

        when(item.itemId) {
            R.id.nav_home -> {
                fragment = HomeFragment()
                title = getString(R.string.menu_home)
            }
            R.id.nav_favorite -> {
                fragment = FavoriteFragment()
                title = getString(R.string.menu_favorite)
            }
            R.id.nav_map -> {
                Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
            }
        }

        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }

        supportActionBar?.title = title

        binding.mainDrawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}