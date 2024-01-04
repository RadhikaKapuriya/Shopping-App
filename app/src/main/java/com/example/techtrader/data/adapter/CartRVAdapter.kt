package com.example.techtrader.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.techtrader.R
import com.example.techtrader.data.model.CartItems


class CartRVAdapter(

    var list: List<CartItems>,
    val cartItemClickInterface: CartItemClickInterface
) : RecyclerView.Adapter<CartRVAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val amountTV = itemView.findViewById<TextView>(R.id.idTVTotalAmount)
        val deleteIV = itemView.findViewById<Button>(R.id.idIVDelete)
        val ivproductimage = itemView.findViewById<ImageView>(R.id.ivproductimage)

    }

    interface CartItemClickInterface{
        fun onItemClick(cartItems: CartItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_rv_item, parent, false)

        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).name
        holder.amountTV.text = "₹"+list.get(position).price.toString()
        Glide.with(holder.ivproductimage.context)
            .load(list.get(position).imageResource)
            .override( 200, 200 )
            .diskCacheStrategy( DiskCacheStrategy.ALL )
            .into(holder.ivproductimage);
        holder.deleteIV.setOnClickListener{
            cartItemClickInterface.onItemClick(list.get(position))
        }
    }
}