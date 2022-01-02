package com.taxon_mobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class UserCreature implements Parcelable {

    private List<Species> species;

    protected UserCreature(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserCreature> CREATOR = new Creator<UserCreature>() {
        @Override
        public UserCreature createFromParcel(Parcel in) {
            return new UserCreature(in);
        }

        @Override
        public UserCreature[] newArray(int size) {
            return new UserCreature[size];
        }
    };

    public static UserCreature objectFromData(String str) {

        return new Gson().fromJson(str, UserCreature.class);
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> species) {
        this.species = species;
    }

    public static class Species {
        private int id;
        private int genus_id;
        private String name;
        private String common_name;
        private int price;
        private String description;
        private String image_path;
        private Object prerequisite_id;
        private String created_at;
        private String updated_at;

        public static Species objectFromData(String str) {

            return new Gson().fromJson(str, Species.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGenus_id() {
            return genus_id;
        }

        public void setGenus_id(int genus_id) {
            this.genus_id = genus_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCommon_name() {
            return common_name;
        }

        public void setCommon_name(String common_name) {
            this.common_name = common_name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public Object getPrerequisite_id() {
            return prerequisite_id;
        }

        public void setPrerequisite_id(Object prerequisite_id) {
            this.prerequisite_id = prerequisite_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
