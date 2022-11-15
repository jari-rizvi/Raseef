package com.teamx.raseef.ui.fragments.intro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.data.models.IntroModel
import com.teamx.raseef.databinding.ItemIntroBinding
import com.teamx.raseef.dummyData.SpecialOffer

class IntroAdapter(private val arrayList : ArrayList<IntroModel>):RecyclerView.Adapter<IntroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val itemIntroBinding = ItemIntroBinding.inflate(inflater,parent,false)
        return IntroViewHolder(itemIntroBinding)
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {

         val intro: IntroModel = arrayList.get(position)
        holder.binding.imgIntro.setImageResource(intro.image)
        holder.binding.tvHead.text = intro.title
        holder.binding.tvDesc.text = intro.description

        holder.itemView.setOnClickListener {



        }
    }

    override fun getItemCount(): Int {


        return arrayList.size


    }


}

class IntroViewHolder(itemIntroBinding: ItemIntroBinding): RecyclerView.ViewHolder(itemIntroBinding.root){

    val binding = itemIntroBinding
}

