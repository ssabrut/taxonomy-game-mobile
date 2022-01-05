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
import com.taxon_mobile.helpers.Const;
import com.taxon_mobile.models.Creature;
import com.taxon_mobile.viewmodels.SpeciesViewModel;
import com.taxon_mobile.views.MainActivity;

import java.util.List;

public class CreatureAdapter extends RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder> {
    private Context context;
    public static List<Creature.Creatures> listCreature;

    public CreatureAdapter(Context context) {
        this.context = context;
    }

    private List<Creature.Creatures> getListCreature() {
        return listCreature;
    }

    public void setListCreature(List<Creature.Creatures> listCreature) {
        this.listCreature = listCreature;
    }

    @NonNull
    @Override
    public CreatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_creature, parent, false);
        return new CreatureAdapter.CreatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatureViewHolder holder, int position) {
        final Creature.Creatures result = getListCreature().get(position);
        Glide.with(context).load(Const.IMG_URL + result.getImage_path()).into(holder.creature_image_path);
        holder.creature_common_name.setText(result.getCommon_name());
        holder.creature_name.setText(result.getName());
        holder.creature_description.setText(result.getDescription().substring(0, 20) + "...");
        holder.creature_price.setText(String.valueOf(result.getPrice()));

        holder.creature_buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeciesViewModel.unlockCreature("Bearer " + MainActivity.token, result.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListCreature().size();
    }

    public class CreatureViewHolder extends RecyclerView.ViewHolder {
        ImageView creature_image_path;
        CardView creature_buy_btn;
        TextView creature_common_name, creature_name, creature_description, creature_price;

        public CreatureViewHolder(@NonNull View itemView) {
            super(itemView);
            creature_image_path = itemView.findViewById(R.id.creature_image_path);
            creature_common_name = itemView.findViewById(R.id.creature_common_name);
            creature_name = itemView.findViewById(R.id.creature_name);
            creature_description = itemView.findViewById(R.id.creature_description);
            creature_buy_btn = itemView.findViewById(R.id.creature_buy_btn);
            creature_price = itemView.findViewById(R.id.creature_price);
        }
    }
}
