package com.taxon_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.taxon_mobile.R;
import com.taxon_mobile.helpers.Const;
import com.taxon_mobile.models.Evolution;
import com.taxon_mobile.viewmodels.EvolutionViewModel;
import com.taxon_mobile.viewmodels.LogViewModel;
import com.taxon_mobile.viewmodels.UserStatViewModel;
import com.taxon_mobile.views.MainActivity;
import com.taxon_mobile.views.fragments.EvolutionFragment;

import java.util.List;

public class EvolutionAdapter extends RecyclerView.Adapter<EvolutionAdapter.EvolutionViewHolder> {
    private Context context;
    public static List<Evolution.Evolutions> listEvolution;

    public EvolutionAdapter(Context context) {
        this.context = context;
    }

    private List<Evolution.Evolutions> getListEvolution() {
        return listEvolution;
    }

    public void setListEvolution(List<Evolution.Evolutions> listEvolution) {
        this.listEvolution = listEvolution;
    }

    @NonNull
    @Override
    public EvolutionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_evolution, parent, false);
        return new EvolutionAdapter.EvolutionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvolutionViewHolder holder, int position) {
        final Evolution.Evolutions result = getListEvolution().get(position);
        Glide.with(context).load(Const.IMG_URL + result.getImage_path()).into(holder.evolution_image_path);
        holder.evolution_name.setText(result.getName());
        holder.evolution_description.setText(result.getDescription().substring(0, 20) + "...");
        holder.evolution_price.setText("Harga: " + String.valueOf(result.getPrice()));

        holder.evolution_buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.evo >= result.getPrice()) {
                    MainActivity.evo -= result.getPrice();
                    EvolutionViewModel.unlockEvolution("Bearer " + MainActivity.token, result.getId());
                    UserStatViewModel.saveUserStat("Bearer " + MainActivity.token, MainActivity.power, MainActivity.evo, MainActivity.dna, MainActivity.point);
                    LogViewModel.log("Bearer " + MainActivity.token, "Evolution", "User id : " + MainActivity.user.getId() + " unlocked Evolution id " + result.getId());
                    LogViewModel.log("Bearer " + MainActivity.token, "UserStat", "User id : " + MainActivity.user.getId() + " UserStat evo subtracted by " + result.getPrice());
                } else {
                    Toast.makeText(EvolutionFragment.context, "Evo tidak cukup!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListEvolution().size();
    }

    public class EvolutionViewHolder extends RecyclerView.ViewHolder {
        ImageView evolution_image_path;
        TextView evolution_name, evolution_description, evolution_price;
        CardView evolution_buy_btn;

        public EvolutionViewHolder(@NonNull View itemView) {
            super(itemView);
            evolution_image_path = itemView.findViewById(R.id.evolution_image_path);
            evolution_name = itemView.findViewById(R.id.evolution_name);
            evolution_description = itemView.findViewById(R.id.evolution_description);
            evolution_price = itemView.findViewById(R.id.evolution_price);
            evolution_buy_btn = itemView.findViewById(R.id.evolution_buy_btn);
        }
    }
}
