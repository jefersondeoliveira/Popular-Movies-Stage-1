package com.example.popular_movies_stage_one;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.popular_movies_stage_one.model.Movie;
import com.example.popular_movies_stage_one.utils.JsonUtils;
import com.example.popular_movies_stage_one.utils.NetworkUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "PUT_YOU_KEY";

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mTvError;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bindViews();
        loadMovieData("popular");

    }

    private void bindViews(){
        mRecyclerView = findViewById(R.id.rc_movies);
        mTvError = findViewById(R.id.tv_error);
        mProgressBar = findViewById(R.id.pb_load);
        bindAdapter();
    }

    private void bindAdapter(){

        mMovieAdapter = new MovieAdapter(new MovieAdapter.MovieAdapterOnClickHandler() {
            @Override
            public void onClick(Movie movie) {
                Intent intentToStartDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
                intentToStartDetailActivity.putExtra("movie", movie);
                startActivity(intentToStartDetailActivity);
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMovieAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();

        if (menuItemSelected == R.id.action_popular) {
            loadMovieData("popular");
            return true;
        }

        if (menuItemSelected == R.id.action_top_rated) {
            loadMovieData("top_rated");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadMovieData(String sort) {
        new FetchMovieTask().execute(sort);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {

            String sortBy = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(API_KEY, sortBy);

            try {
                String response = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);

                return JsonUtils.getMovieFromJson(response);

            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> result) {
            mProgressBar.setVisibility(View.GONE);
            if (result != null) {
                showResults();
                mMovieAdapter.updateData(result);
            } else {
                showError();
            }
        }

    }

    private void showResults() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mTvError.setVisibility(View.GONE);
    }

    private void showError() {
        mRecyclerView.setVisibility(View.GONE);
        mTvError.setVisibility(View.VISIBLE);
    }

}
