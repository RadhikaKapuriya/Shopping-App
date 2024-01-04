package com.example.techtrader.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_details")
class CartItems(

    @ColumnInfo(name = "Name")
    val name: String,

    @ColumnInfo(name = "Price")
    val price: Long,

    @ColumnInfo(name = "Image")
    val imageResource: String

    )
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}


