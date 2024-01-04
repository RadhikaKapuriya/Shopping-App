package com.example.techtrader.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techtrader.roomdb.CartRepository
import com.example.techtrader.viewmodel.CartViewModel
import com.example.techtrader.viewmodel.CartViewModelFactory
import com.example.techtrader.roomdb.ProductDatabase
import com.example.techtrader.R
import com.example.techtrader.data.adapter.CartRVAdapter
import com.example.techtrader.data.model.CartItems


class CartActivity : AppCompatActivity(), CartRVAdapter.CartItemClickInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var cartRVAdapter: CartRVAdapter
    lateinit var cartViewModal: CartViewModel
    lateinit var list: List<CartItems>
    lateinit var backButton: ImageView
    lateinit var doneButton: Button
    lateinit var tvCartHeader: TextView
    lateinit var tvTotalAmount: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        recyclerView = findViewById(R.id.recyclerView)
        doneButton = findViewById(R.id.doneButton)
        tvCartHeader = findViewById(R.id.tvcartheader)
        tvTotalAmount = findViewById(R.id.tvtotal)
        backButton = findViewById(R.id.backbutton)
        val cartRepository = CartRepository(ProductDatabase(this))
        val factory = CartViewModelFactory(cartRepository)
        cartViewModal = ViewModelProvider(this, factory).get(CartViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        list = ArrayList<CartItems>()
        cartRVAdapter = CartRVAdapter(list,this)


        cartViewModal.allCartItems().observe(this, Observer {
            cartRVAdapter.list = it
            tvCartHeader.setText("Proceed to Buy (${it.size} item)")
            val totalCost  = it.sumOf { it.price }
            tvTotalAmount.setText("₹${totalCost}")
            cartRVAdapter.notifyDataSetChanged()
        })
        recyclerView.adapter = cartRVAdapter
        backButton.setOnClickListener(){
           finish()
        }
        doneButton.setOnClickListener(){
            if (cartRVAdapter.list.size > 0) {
                showAlertDialog()
            }
        }
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setMessage("Your order has been placed successfully")
        alertDialog.setPositiveButton(
            "OK"
        ) { _, _ ->
            finish()
            cartViewModal.deleteAll()
        }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    override fun onItemClick(cartItems: CartItems) {
        cartViewModal.delete(cartItems)
        cartRVAdapter.notifyDataSetChanged()
    }
}