package com.teamx.raseef.ui.fragments.pastOrder;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamx.raseef.databinding.ItemCurrentorderBinding;
import com.teamx.raseef.ui.fragments.notification.Notifications;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PastOrderAdapter extends RecyclerView.Adapter<PastOrderAdapter.NoticationViewHolder> {

    private Context context;

    public PastOrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public NoticationViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new NoticationViewHolder(ItemCurrentorderBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoticationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class NoticationViewHolder extends RecyclerView.ViewHolder {

        ItemCurrentorderBinding itemNotificationBinding;
        public NoticationViewHolder(@NonNull @NotNull ItemCurrentorderBinding itemNotificationBinding) {
            super(itemNotificationBinding.getRoot());
            this.itemNotificationBinding=itemNotificationBinding;

        }
    }

}
