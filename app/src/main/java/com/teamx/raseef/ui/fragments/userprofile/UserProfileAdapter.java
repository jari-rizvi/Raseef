package com.teamx.raseef.ui.fragments.userprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamx.raseef.databinding.ItemProductsBinding;
import com.teamx.raseef.databinding.ItemServiceBinding;

import org.jetbrains.annotations.NotNull;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.NoticationViewHolder> {

    private Context context;

    public UserProfileAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public NoticationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new NoticationViewHolder(ItemServiceBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoticationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class NoticationViewHolder extends RecyclerView.ViewHolder {

        ItemServiceBinding itemNotificationBinding;
        public NoticationViewHolder(@NonNull @NotNull ItemServiceBinding itemNotificationBinding) {
            super(itemNotificationBinding.getRoot());
            this.itemNotificationBinding=itemNotificationBinding;

        }
    }

}
