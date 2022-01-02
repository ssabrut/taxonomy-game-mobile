package com.taxon_mobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Species implements Parcelable {


    private List<UserCreatures> userCreatures;

    protected Species(Parcel in) {
    }

    public static final Creator<Species> CREATOR = new Creator<Species>() {
        @Override
        public Species createFromParcel(Parcel in) {
            return new Species(in);
        }

        @Override
        public Species[] newArray(int size) {
            return new Species[size];
        }
    };

    public static Species objectFromData(String str) {

        return new Gson().fromJson(str, Species.class);
    }

    public List<UserCreatures> getUserCreatures() {
        return userCreatures;
    }

    public void setUserCreatures(List<UserCreatures> userCreatures) {
        this.userCreatures = userCreatures;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class UserCreatures {
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

        public static UserCreatures objectFromData(String str) {

            return new Gson().fromJson(str, UserCreatures.class);
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
