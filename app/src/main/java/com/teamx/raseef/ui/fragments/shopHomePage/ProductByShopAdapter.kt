package com.teamx.raseef.ui.fragments.shopHomePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.ui.fragments.Home.OnTopProductListener
import com.teamx.raseef.data.models.productsShop.Doc
import com.teamx.raseef.databinding.ItemProductsBinding

class ProductByShopAdapter(val arrayList: ArrayList<Doc>, private val onTopProductListener: OnTopProductListener) : RecyclerView.Adapter<ProductByShopAdapter.ShopProductViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemshopHomeBinding = ItemProductsBinding .inflate(inflater, parent, false)
        return ShopProductViewHolder(itemshopHomeBinding)

    }

    override fun onBindViewHolder(holder: ShopProductViewHolder, position: Int) {
        val product : Doc = arrayList[position]

        holder.binding.name.text = product.name
        holder.binding.rating.text = product.ratings.toString()
        holder.binding.type.text = product.slug
        holder.binding.totalAmount.text = product.price.toString()
        Picasso.get().load(product.image).into(holder.binding.img)

        product.product_order_types.forEach {
            if (it == "CASH_ON_DELIVERY"){
                holder.binding.cashOnDelivery.visibility = View.VISIBLE
            }

            if (it == "PAY_ON_ARRIVAL"){
                holder.binding.payOnArrival.visibility = View.VISIBLE
            }

            if (it == "ONLINE_PAYMENTS"){
                holder.binding.onlinePayment.visibility = View.VISIBLE
            }
        }




        holder.itemView.setOnClickListener {
            onTopProductListener.onTopproductClick(position)
        }

    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    class ShopProductViewHolder(itemshopHomeBinding: ItemProductsBinding) : RecyclerView.ViewHolder(itemshopHomeBinding.root){
        val binding =itemshopHomeBinding

    }
}