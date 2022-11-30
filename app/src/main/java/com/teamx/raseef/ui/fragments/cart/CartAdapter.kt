package com.teamx.raseef.ui.fragments.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.raseef.databinding.ItemCartBinding
import com.teamx.raseef.dummyData.Cart

class CartAdapter(var arrayList: ArrayList<Cart>, val onCartListener: OnCartListener) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val cart: Cart = arrayList[position]
        holder.binding.productName.text = cart.name
        holder.binding.productModifier.text = cart.modifier
        holder.binding.productQuantity.text = "${cart.quantity}"

        cart.productBySlug?.let {
            if (it.product_type.toString().equals("simple")) {

                holder.binding.productprice.text = "${it.price ?: 0 * cart.quantity} AED"


//            holder.binding.productPrice.text = "${it.price ?: 0 * cart.quantity} AED"
////                mViewDataBinding.productPrice.text = data.price.toString()
//            } else {
////                mViewDataBinding.productPrice.text = "${data.min_price.toString()}-${data.max_price.toString()}"
//            holder.binding.productPrice.text = "${it.min_price ?: 0 * cart.quantity} AED"
//            }
            }




            Picasso.get().load(cart.imageUrl).into(holder.binding.img)

//            holder.itemCartBinding.root.setOnClickListener {
//                onCartListener.onItemClickListener(position)
//            }

            holder.binding.addProduct.setOnClickListener {
                onCartListener.onAddClickListener(position)
            }

            holder.binding.subProduct.setOnClickListener {
                onCartListener.onSubClickListener(position)
            }

//        holder.binding.btnDelete.setOnClickListener {
//            onCartListener.onDeleteClickListener(position)
//        }


        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    fun setData(arrayList: ArrayList<Cart>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    class CartViewHolder(var itemCartBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(itemCartBinding.root) {

        val binding = itemCartBinding

    }
}