package com.taxon_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.taxon_mobile.R;
import com.taxon_mobile.models.Species;

import java.util.List;

public class UserCreatureAdapter extends RecyclerView.Adapter<UserCreatureAdapter.UserCreatureViewHolder> {

    private Context context;
    private List<Species> listUserCreature;

    public UserCreatureAdapter(Context context) {
        this.context = context;
    }

    private List<Species> getListUserCreature() {
        return listUserCreature;
    }

    public void setListUserCreature(List<Species> listUserCreature) {
        this.listUserCreature = listUserCreature;
    }

    @NonNull
    @Override
    public UserCreatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_user_creature, parent, false);
        return new UserCreatureAdapter.UserCreatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCreatureViewHolder holder, int position) {
        final Species result = getListUserCreature().get(position);
//        Glide.with(context).load(result).into(holder.user_creature_image);
//        holder.user_creature_common_name.setText(result.getCommon_name());
    }

    @Override
    public int getItemCount() {
        return getListUserCreature().size();
    }

    public class UserCreatureViewHolder extends RecyclerView.ViewHolder {
        ImageView user_creature_image;
        TextView user_creature_common_name, user_creature_name, user_creature_price;
        CardView user_creature_buy_btn;

        public UserCreatureViewHolder(@NonNull View itemView) {
            super(itemView);
            user_creature_image = itemView.findViewById(R.id.user_creature_image);
            user_creature_common_name = itemView.findViewById(R.id.user_creature_common_name);
            user_creature_name = itemView.findViewById(R.id.user_creature_name);
            user_creature_price = itemView.findViewById(R.id.user_creature_price);
            user_creature_buy_btn = itemView.findViewById(R.id.user_creature_buy_btn);
        }
    }
}
