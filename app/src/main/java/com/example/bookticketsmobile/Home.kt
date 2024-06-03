package com.example.bookticketsmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bookticketsmobile.databinding.ActivityHomeBinding

private lateinit var binding:ActivityHomeBinding
class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val home = HomeFragment()
        val account = AccountFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, home)
            commit()
        }
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
//                    replaceFragment(R.id.frame_layout, home, R.id.home, addToBackStack = true)
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, home)
                        commit()
                    }
                }
                R.id.cinema -> {

                }
//                R.id.cinema -> {}
//                R.id.news -> {}
                R.id.account -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, account)
                        commit()
                    }
                }
            }
            true
        }
    }
}

//fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, containerId: Int, addToBackStack: Boolean = false) {
//    // Bắt đầu một giao dịch Fragment
//    val transaction: FragmentTransaction = fragmentManager.beginTransaction()
//
//    // Thay thế Fragment hiện tại bằng Fragment mới
//    transaction.replace(containerId, fragment)
//
//    // Nếu addToBackStack được đặt là true, cho phép quay lại Fragment trước đó khi nhấn nút Back
//    if (addToBackStack) {
//        transaction.addToBackStack(null)
//    }
//
//    // Thực hiện giao dịch
//    transaction.commit()
//}
