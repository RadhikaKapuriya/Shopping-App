package com.example.techtrader.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.techtrader.roomdb.CartRepository
import com.example.techtrader.viewmodel.CartViewModel
import com.example.techtrader.viewmodel.CartViewModelFactory
import com.example.techtrader.roomdb.ProductDatabase
import com.example.techtrader.R
import com.example.techtrader.data.adapter.ProductAdapter
import com.example.techtrader.data.model.CartItems


class FragmentMobile : Fragment() {
    private val productList = listOf(
        CartItems("Motorola", 28800, "https://img.freepik.com/free-vector/change-our-date-postponed-wedding-phone-app_52683-39982.jpg?w=1480&t=st=1704390298~exp=1704390898~hmac=30da6f60e24c36bd2e2674e51049bb80d022c66b1bebad700b54fd7aca0dd6ff"),
        CartItems("Redmi Note", 23995, "https://img.freepik.com/free-psd/full-screen-smartphone-mockup-design_53876-65968.jpg?w=1480&t=st=1704391879~exp=1704392479~hmac=a6260140bc410292c5054d043a76ff77b008fa230a35eefa3fbf97c1cd212ae7"),
        CartItems("Oneplus Nord", 24999, "https://img.freepik.com/free-vector/realistic-display-smartphone-with-different-apps_52683-30241.jpg?w=1480&t=st=1704390332~exp=1704390932~hmac=77c98edac5e86862f393f4ca566eb650f89b172ee1e3a9bb944dff492c6cbae0"),
        CartItems("vivo V29e", 18900, "https://img.freepik.com/free-vector/our-new-date-postponed-wedding-phone-app_52683-39981.jpg?w=1480&t=st=1704390367~exp=1704390967~hmac=3252340aac551864bc7f06b2bd581a729c1ac5d685e53387ade18a3cd8b15729"),
        CartItems("Samsung Galaxy", 134999,"https://img.freepik.com/free-photo/smartphone-device-surrounded-by-nature-scene_23-2150165578.jpg?w=1480&t=st=1704390385~exp=1704390985~hmac=5cc3b33f67ba9c3407a5bdeb5e588b8992f4e177b15b4c03936701921ff0e79e")
        // Add more products as needed
    )
    lateinit var cartViewModal: CartViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val cartRepository = CartRepository(ProductDatabase(requireActivity()))
        val factory = CartViewModelFactory(cartRepository)
        cartViewModal = ViewModelProvider(this, factory).get(CartViewModel::class.java)
        recyclerView.layoutManager = GridLayoutManager(requireActivity(),2)
        val adapter = ProductAdapter(requireContext(),productList) { product ->
            cartViewModal.insert(product)
        }
        recyclerView.adapter = adapter
        return view
    }


}
