package com.example.android.bookinglist;


public class BookingList {
    private String mTitle;
    private String mAuthor;

    public  BookingList(String title, String author){
        mTitle=title;
        mAuthor=author;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

}