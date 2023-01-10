package com.teamx.raseef.ui.fragments.product.variation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.data.dataclasses.dashboard.Variation
import com.teamx.raseef.data.dataclasses.dashboard.VariationS
import com.teamx.raseef.databinding.VariationBtnItemLayoutBinding


class VariationButtonAdapter(
    var rdnBtns: ArrayList<VariationS>,
    var radBtnInterface: RadBtnInterface
) :
    RecyclerView.Adapter<VariationButtonVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariationButtonVH {
        return VariationButtonVH(
            VariationBtnItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: VariationButtonVH, position: Int) {
        holder.binding.radName.text = try {
            rdnBtns[position].meta
        } catch (e: Exception) {
            ""
        }
        holder.binding.radBtn.setOnClickListener {
            Log.d("123123", "VariationButtonAdapter:${rdnBtns[position].meta} ")
            for (r in rdnBtns.indices) {
                rdnBtns.get(r).boolCheck = false
            }
            rdnBtns.get(position).boolCheck = true

            radBtnInterface.checkRadBtn(rdnBtns[position], position, rdnBtns)

            notifyDataSetChanged()
        }
        holder.binding.radBtn.isChecked = rdnBtns[position].boolCheck

    }

    override fun getItemCount(): Int {

        return rdnBtns.size
    }
}

interface RadBtnInterface {
    fun checkRadBtn(variation: VariationS, position: Int, arr: ArrayList<VariationS>)
}