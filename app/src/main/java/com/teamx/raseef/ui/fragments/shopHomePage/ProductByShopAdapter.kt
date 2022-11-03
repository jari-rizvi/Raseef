//package com.teamx.raseef.ui.fragments.shopHomePage
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.teamx.raseef.databinding.ItemShopHomePageBinding
//import com.teamx.raseef.ui.fragments.Home.OnTopProductListener
//
//class ProductByShopAdapter(val arrayList: ArrayList<>, val onTopProductListener: OnTopProductListener) : RecyclerView.Adapter<ProductByShopAdapter.ShopProductViewHolder>() {
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopProductViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val itemshopHomeBinding = ItemShopHomePageBinding .inflate(inflater, parent, false)
//        return ShopProductViewHolder(itemshopHomeBinding)
//
//    }
//
//    override fun onBindViewHolder(holder: ShopProductViewHolder, position: Int) {
////        val product : Doc = arrayList[position]
////
////        holder.binding.name.text = product.name
////        holder.binding.type.text = product.product_type
////        holder.binding.totalAmount.text = product.price.toString()+" AED"
////        Picasso.get().load(product.image).into(holder.binding.productimg)
//
//        holder.itemView.setOnClickListener {
//            onTopProductListener.onTopproductClick(position)
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//       return arrayList.size
//    }
//
//    class ShopProductViewHolder(itemshopHomeBinding: ItemShopHomePageBinding) : RecyclerView.ViewHolder(itemshopHomeBinding.root){
//        val binding =itemshopHomeBinding
//
//    }
//}