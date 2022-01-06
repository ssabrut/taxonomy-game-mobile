package com.taxon_mobile.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.taxon_mobile.R;
import com.taxon_mobile.helpers.Const;
import com.taxon_mobile.models.UserCreature;

import java.util.List;

public class UserCreatureAdapter extends RecyclerView.Adapter<UserCreatureAdapter.UserCreatureViewHolder> {

    private Context context;
    public static List<UserCreature.UserCreatures> listUserCreature;

    public UserCreatureAdapter(Context context) {
        this.context = context;
    }

    private List<UserCreature.UserCreatures> getListUserCreature() {
        return listUserCreature;
    }

    public void setListUserCreature(List<UserCreature.UserCreatures> listUserCreature) {
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
        final UserCreature.UserCreatures result = getListUserCreature().get(position);
        Glide.with(context).load(Const.IMG_URL + result.getImage_path()).into(holder.user_creature_image);
        holder.user_creature_common_name.setText(result.getCommon_name());
        holder.user_creature_name.setText(result.getName());
        holder.user_creature_description.setText(result.getDescription().substring(0, 20) + "...");

        holder.user_creature_open_creature_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("speciesId", result.getId());
                Navigation.findNavController(view).navigate(R.id.action_upgradeFragment_to_userCreatureDetailFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListUserCreature().size();
    }

    public class UserCreatureViewHolder extends RecyclerView.ViewHolder {
        ImageView user_creature_image;
        CardView user_creature_open_creature_btn;
        TextView user_creature_common_name, user_creature_name, user_creature_description;

        public UserCreatureViewHolder(@NonNull View itemView) {
            super(itemView);
            user_creature_image = itemView.findViewById(R.id.user_creature_image);
            user_creature_common_name = itemView.findViewById(R.id.user_creature_common_name);
            user_creature_name = itemView.findViewById(R.id.user_creature_name);
            user_creature_description = itemView.findViewById(R.id.user_creature_description);
            user_creature_open_creature_btn = itemView.findViewById(R.id.user_creature_open_creature_btn);
        }
    }
}
