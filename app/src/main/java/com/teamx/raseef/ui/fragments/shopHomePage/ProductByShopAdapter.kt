package com.teamx.raseef.ui.fragments.shopHomePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.databinding.ItemProductsBinding
import com.teamx.raseef.databinding.ItemServiceBinding
import com.teamx.raseef.ui.fragments.Home.OnTopProductListener
import com.teamx.zeus.data.models.productsShop.Doc

class ProductByShopAdapter(val arrayList: ArrayList<Doc>, val onTopProductListener: OnTopProductListener) : RecyclerView.Adapter<ProductByShopAdapter.ShopProductViewHolder>() {



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