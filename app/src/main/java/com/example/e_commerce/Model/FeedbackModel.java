package com.example.e_commerce.Model;

public class FeedbackModel {
    String id,useremail,feedbacktitle,message;

    public FeedbackModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getFeedbacktitle() {
        return feedbacktitle;
    }

    public void setFeedbacktitle(String feedbacktitle) {
        this.feedbacktitle = feedbacktitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FeedbackModel(String id, String useremail, String feedbacktitle, String message) {
        this.id = id;
        this.useremail = useremail;
        this.feedbacktitle = feedbacktitle;
        this.message = message;
    }
}
