package jp.mixi.practice.serializable;

import java.util.Date;

public class User {
    private String name;
    private int id;
    private int age;
    private String keyword;
    private Status status;
    
    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public static class Status{
        private Date mPostedDate;
        private String mText;
        public Date getPostedDate() {
            return mPostedDate;
        }
        public String getText(){
            return mText;
        }
        public void setPostedDate(Date date){
            mPostedDate = date;
        }
        public void setText(String text) {
            mText = text;
        }
    }

}
