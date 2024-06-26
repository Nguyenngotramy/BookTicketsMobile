package com.example.bookticketsmobile.AdminUi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bookticketsmobile.Login
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.ActivityNavigationDrawerAdminBinding

class NavigationDrawerAdmin : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationDrawerAdminBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var fragmentManager: FragmentManager
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.hum)
            setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_add_movies->{
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(AddMoviesFragment())
                    true

                }
                R.id.nav_list_movies->{
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(List_Movies_Fragment())
                    true

                }

                R.id.nav_add_theatercomplex->{
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(AddCinemaClustersFragment())
                    true

                }
                R.id.nav_add_food->{
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(AddFoodFragment())
                    true
                }
                R.id.nav_add_vorcher->{
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(AddVorcherFragment())
                    true
                }
                R.id.nav_add_suatchieu->{
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(AddPerformanceFragment())
                    true
                }
                R.id.nav_list_customer->{
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(ListCustomerFragment())
                    true
                }

                R.id.nav_list_food->{
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(ListFoodFragment())
                    true
                }
                R.id.nav_list_vorcher->{
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(ListVorcherFragment())
                    true
                }
                R.id.nav_list_cc->{
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(ListCinameClusterFragment())
                    true
                }
                R.id.nav_list_performance->{
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(ListPerformanceFragment())
                    true
                }
                R.id.nav_add_seat->{
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    goToFragment(AddSeatFragment())
                    true
                }

                R.id.nav_logout->{
                    logout()
                    true
                }
                else -> false


            }
        }
    }

    private fun goToFragment(fragment: Fragment) {
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragmentAdmin, fragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // Redirect to Login Activity
        val intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}
