package com.teamx.raseef.ui.fragments.product.variation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.raseef.data.dataclasses.dashboard.Variation
import com.teamx.raseef.data.dataclasses.dashboard.VariationS
import com.teamx.raseef.databinding.VariationItemLayoutBinding

class VariationCategoryAdapter(
    var cats: ArrayList<Categories>, var changePriceVariation: ChangePriceVariation
) : RecyclerView.Adapter<VariationCategoryVH>(), RadBtnInterface {

    var adapter: VariationButtonAdapter? = null
    var variations: ArrayList<VariationS>? = ArrayList()
    var rec: RecyclerView? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariationCategoryVH {
        return VariationCategoryVH(
            VariationItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    }

    override fun onBindViewHolder(holder: VariationCategoryVH, position: Int) {

        rec = holder.binding.recyclerView3

        variations = cats[position].variation

        Log.d("123123", "onBindViewHolder: $variations")

        adapter = VariationButtonAdapter(variations!!, this)

        Log.d("123123", "onBindViewHolder: ${cats[position].variation.size}")

        rec?.adapter = adapter

        holder.binding.textView32.text = try {

            cats[position].title

        } catch (e: Exception) {
            ""
        }
    }

    override fun getItemCount(): Int {

        return cats.size
    }


    override fun checkRadBtn(variation: VariationS, position: Int, arr: ArrayList<VariationS>) {

/*        for (i in variations!!.indices) {
            variations!!.get(i)?.boolCheck = false
        }
        variations?.get(position)?.boolCheck = true
        adapter = VariationButtonAdapter(variations!!, this)
//        Log.d("123123", "onBindViewHolder: ${cats[position].variation.size}")
        rec?.adapter = adapter*/

        Log.d("123132", "checkRadBtn: ")
        for (cat in cats.indices) {
            if (cats[cat].variation[0].attribute!!.name == variations!![0].attribute!!.name) {
                cats[cat].variation = arr
            }
        }


        var title = ""

        for (i in cats) {
            for (j in i.variation) {

                if (j.boolCheck) {
                    if (title.isEmpty()) {
                        title = "${j.value}"
                    } else {
                        title = "$title/${j.value}"
                    }

                }
            }

        }

        changePriceVariation.onVariationClicked(cats, title)

    }
}

interface ChangePriceVariation {

    fun onVariationClicked(cats: ArrayList<Categories>, title: String)
}

class Categories(
    var title: String,
    var variation: ArrayList<VariationS> = ArrayList())