package com.teamx.raseef.ui.fragments.shopHomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamx.raseef.databinding.ItemProductsBinding;
import com.teamx.raseef.databinding.ItemReviewlistBinding;

import org.jetbrains.annotations.NotNull;

public class ProductByShopAdapter extends RecyclerView.Adapter<ProductByShopAdapter.NoticationViewHolder> {

    private Context context;

    public ProductByShopAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public NoticationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new NoticationViewHolder(ItemProductsBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoticationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class NoticationViewHolder extends RecyclerView.ViewHolder {

        ItemProductsBinding itemNotificationBinding;
        public NoticationViewHolder(@NonNull @NotNull ItemProductsBinding itemNotificationBinding) {
            super(itemNotificationBinding.getRoot());
            this.itemNotificationBinding=itemNotificationBinding;

        }
    }

}
