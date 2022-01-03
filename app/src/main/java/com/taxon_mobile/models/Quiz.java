package com.taxon_mobile.models;

import com.google.gson.Gson;

import java.util.List;

public class Quiz {

    private List<Quizzes> quizzes;

    public static Quiz objectFromData(String str) {

        return new Gson().fromJson(str, Quiz.class);
    }

    public List<Quizzes> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quizzes> quizzes) {
        this.quizzes = quizzes;
    }

    public static class Quizzes {
        private int id;
        private String question;
        private String answer;
        private int point;
        private String created_at;
        private String updated_at;

        public static Quizzes objectFromData(String str) {

            return new Gson().fromJson(str, Quizzes.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
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
