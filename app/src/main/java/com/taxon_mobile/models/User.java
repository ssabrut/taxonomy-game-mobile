package com.taxon_mobile.models;

import com.google.gson.Gson;

public class User {

    private int id;
    private String email;
    private String username;
    private String name;
    private String school;
    private String city;
    private String birthyear;
    private String email_verified_at;
    private String created_at;
    private String updated_at;
    private LoginResponse.User.Stat stat;

    public static LoginResponse.User objectFromData(String str) {

        return new Gson().fromJson(str, LoginResponse.User.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
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

    public LoginResponse.User.Stat getStat() {
        return stat;
    }

    public void setStat(LoginResponse.User.Stat stat) {
        this.stat = stat;
    }

    public static class Stat {
        private int id;
        private int student_id;
        private int power;
        private int evo;
        private int dna;
        private int point;
        private String created_at;
        private String updated_at;

        public static LoginResponse.User.Stat objectFromData(String str) {

            return new Gson().fromJson(str, LoginResponse.User.Stat.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStudent_id() {
            return student_id;
        }

        public void setStudent_id(int student_id) {
            this.student_id = student_id;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getEvo() {
            return evo;
        }

        public void setEvo(int evo) {
            this.evo = evo;
        }

        public int getDna() {
            return dna;
        }

        public void setDna(int dna) {
            this.dna = dna;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
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
