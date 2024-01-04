package com.example.techtrader.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.techtrader.roomdb.CartRepository
import com.example.techtrader.viewmodel.CartViewModel
import com.example.techtrader.viewmodel.CartViewModelFactory
import com.example.techtrader.roomdb.ProductDatabase
import com.example.techtrader.R
import com.example.techtrader.data.adapter.TabAdapter
import com.google.android.material.tabs.TabLayout


class HomeActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var cartViewModal: CartViewModel
    lateinit var cartButton: Button
    lateinit var rlbottom: RelativeLayout
    lateinit var tvtotalqtycount: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_item)
        bindView()
        initModel()
        setTabLayout()
        setViewPager()
        onclick()
        addToCart()
    }

    private fun onclick() {
        cartButton.setOnClickListener(){
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initModel() {
        val cartRepository = CartRepository(ProductDatabase(this))
        val factory = CartViewModelFactory(cartRepository)
        cartViewModal = ViewModelProvider(this, factory).get(CartViewModel::class.java)

    }

    private fun addToCart() {
        cartViewModal.allCartItems().observe(this, Observer {
            tvtotalqtycount.setText("${it.size}")
            cartButton.setText("Go to Cart")
        })
    }


    private fun bindView() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        cartButton = findViewById(R.id.cartButton)
        tvtotalqtycount = findViewById(R.id.tvtotalqtycount)
        rlbottom = findViewById(R.id.rlbottom)
    }

    private fun setViewPager() {
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Mobile"))
        tabLayout.addTab(tabLayout.newTab().setText("Tablet"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabAdapter(this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
    }
}