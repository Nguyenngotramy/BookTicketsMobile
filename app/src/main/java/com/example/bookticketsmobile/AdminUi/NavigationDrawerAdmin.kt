package com.example.bookticketsmobile.AdminUi

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.ActivityNavigationDrawerAdminBinding

class NavigationDrawerAdmin : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationDrawerAdminBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

      toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        /*supportActionBar?.setDisplayHomeAsUpEnabled(true)*/
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.hum)
            setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setOnClickListener {
            // Mở drawer khi người dùng chạm vào thanh actionBar
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "clickHome", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
