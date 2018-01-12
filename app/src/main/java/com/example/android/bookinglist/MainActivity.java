package com.example.android.bookinglist;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;
    private BookListAdapter  mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView bookListView = (ListView) findViewById(R.id.list);

        mBookInput = (EditText) findViewById(R.id.bookInput);
        mTitleText = (TextView) findViewById(R.id.title);
        mAuthorText = (TextView) findViewById(R.id.author);
        mSearchButton=(Button) findViewById(R.id.searchButton);

        mAdapter = new BookListAdapter(this, new ArrayList<BookingList>());
        bookListView.setAdapter(mAdapter);

        mSearchButton.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        BookingList currentBoolinglist = mAdapter.getItem(position);
        Uri bookinglistUri = Uri.parse(currentBoolinglist.getUrl());
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookinglistUri);
        startActivity(websiteIntent);
    }
});
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            new FetchBook(mTitleText, mAuthorText, mBookInput).execute(queryString);
        }

        else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            } else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }
}