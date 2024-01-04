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


class FragmentLaptop : Fragment() {
    private val productList = listOf(
        CartItems("Dell 15 Laptop", 40000, "https://img.freepik.com/free-psd/laptop-blue-background-mock-up_1022-178.jpg?w=1800&t=st=1704390133~exp=1704390733~hmac=a60bf7a6de520d7521cb39d2908d6c789087b1ab402ba0ac54bfb4e99b666812"),
        CartItems("HP Chromebook", 52990, "https://img.freepik.com/free-psd/laptop-mock-up-design_1307-45.jpg?w=1480&t=st=1704390211~exp=1704390811~hmac=820f3f1b46bbb332cf79f0df12fa63932c5573c8a55cadd80ba763be22fcc844"),
        CartItems("Lenovo IdeaPad", 26990, "https://img.freepik.com/free-psd/laptop-mock-up-front-view_1310-198.jpg?w=2000&t=st=1704390253~exp=1704390853~hmac=bc68a37171a58726034d6bdb8375f54b529dbba6753672377c185ba471a6e066"),
        CartItems("Apple 2023 Macbook", 306699, "https://img.freepik.com/free-photo/electronic-device-balancing-concept_23-2150422322.jpg?w=2000&t=st=1704390227~exp=1704390827~hmac=468a34e8fdc9c011a2b9c37db052a0e5d86cf7609a2bade98f1ab548d4a34367"),
        CartItems("MSI Modern 15", 80330, "https://img.freepik.com/free-psd/realistic-macbook-mock-up_1022-57.jpg?w=1480&t=st=1704390276~exp=1704390876~hmac=17599716d4720e3dcfe8abcc73a678b5345e3386ddd5c13e4d9d1fbf827d8056")
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
