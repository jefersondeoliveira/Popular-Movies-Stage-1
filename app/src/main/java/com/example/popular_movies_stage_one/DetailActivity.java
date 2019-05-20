package com.example.popular_movies_stage_one;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popular_movies_stage_one.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView mMoviePosterDisplay;
    TextView mMovieTitleDisplay;
    TextView mMovieRateDisplay;
    TextView mMovieReleaseDisplay;
    TextView mMoviePlotSynopsisDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bindViews();

        if(getIntent().hasExtra("movie")){
            Movie movie = (Movie) getIntent().getSerializableExtra("movie");
            mMovieTitleDisplay.setText(movie.getTitle());
            mMoviePlotSynopsisDisplay.setText(movie.getOverview());
            mMovieRateDisplay.setText(movie.getRate());
            mMovieReleaseDisplay.setText(movie.getRelease());
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500"+ movie.getPoster())
                    .into(mMoviePosterDisplay);
        }else{
            finish();
        }

    }

    private void bindViews(){
        mMoviePosterDisplay = findViewById(R.id.iv_detail_movie_poster);
        mMovieTitleDisplay = findViewById(R.id.tv_detail_title);
        mMovieRateDisplay = findViewById(R.id.tv_detail_rate);
        mMovieReleaseDisplay = findViewById(R.id.tv_detail_release_date);
        mMoviePlotSynopsisDisplay = findViewById(R.id.tv_plot_synopsis);
    }
}
