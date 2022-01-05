package com.taxon_mobile.models;

import com.google.gson.Gson;

import java.util.List;

public class Evolution {

    private List<Evolutions> evolutions;

    public static Evolution objectFromData(String str) {

        return new Gson().fromJson(str, Evolution.class);
    }

    public List<Evolutions> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(List<Evolutions> evolutions) {
        this.evolutions = evolutions;
    }

    public static class Evolutions {
        private int id;
        private Object species_id;
        private String name;
        private String description;
        private String image_path;
        private int price;
        private Object prerequisite_id;
        private String created_at;
        private String updated_at;

        public static Evolutions objectFromData(String str) {

            return new Gson().fromJson(str, Evolutions.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getSpecies_id() {
            return species_id;
        }

        public void setSpecies_id(Object species_id) {
            this.species_id = species_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
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
