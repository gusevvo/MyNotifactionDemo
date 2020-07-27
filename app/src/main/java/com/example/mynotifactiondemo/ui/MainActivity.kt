package com.example.mynotifactiondemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupHamburgerMenuItem()
        setupActionBar()
        setupNavigationView()
    }

    private fun setupNavigationView() {
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            drawer_layout.closeDrawers()

            when (menuItem.itemId) {
                R.id.nav_cargo -> {
                    Toast.makeText(this, "Cargo", Toast.LENGTH_LONG).show()
                }
                R.id.nav_limit -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.limitsListFragment)
                }
                R.id.nav_logout -> {
                    loginViewModel.logout()
                    findNavController(R.id.nav_host_fragment).popBackStack(R.id.launchFragment, false)
                }
            }
            true
        }
    }

    private fun setupHamburgerMenuItem() {
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.action_bar_drawer_open,
            R.string.action_bar_drawer_close
        )
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun setupActionBar() {
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            android.R.id.home -> this.onHamburgerMenuItemClick()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onHamburgerMenuItemClick(): Boolean {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        return true
    }
}