package com.example.housecleaners;

public class Feed {
    String feedbackGiver;
    String feedback;
    String feedbackReceiver;
    String date;

    public Feed(String feedbackGiver, String feedback, String feedbackReceiver, String date) {
        this.feedbackGiver = feedbackGiver;
        this.feedback = feedback;
        this.feedbackReceiver = feedbackReceiver;
        this.date = date;
    }

    public String getFeedbackGiver() {
        return feedbackGiver;
    }

    public void setFeedbackGiver(String feedbackGiver) {
        this.feedbackGiver = feedbackGiver;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedbackReceiver() {
        return feedbackReceiver;
    }

    public void setFeedbackReceiver(String feedbackReceiver) {
        this.feedbackReceiver = feedbackReceiver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
