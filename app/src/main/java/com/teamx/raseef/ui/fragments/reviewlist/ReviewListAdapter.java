package com.teamx.raseef.ui.fragments.reviewlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamx.raseef.databinding.ItemCurrentorderBinding;
import com.teamx.raseef.databinding.ItemReviewlistBinding;

import org.jetbrains.annotations.NotNull;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.NoticationViewHolder> {

    private Context context;

    public ReviewListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public NoticationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new NoticationViewHolder(ItemReviewlistBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoticationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class NoticationViewHolder extends RecyclerView.ViewHolder {

        ItemReviewlistBinding itemNotificationBinding;
        public NoticationViewHolder(@NonNull @NotNull ItemReviewlistBinding itemNotificationBinding) {
            super(itemNotificationBinding.getRoot());
            this.itemNotificationBinding=itemNotificationBinding;

        }
    }

}
