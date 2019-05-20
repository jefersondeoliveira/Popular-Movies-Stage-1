package com.example.popular_movies_stage_one.utils;

import com.example.popular_movies_stage_one.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<Movie> getMovieFromJson(String json) throws JSONException {

        JSONObject movieJson = new JSONObject(json);
        JSONArray movieArray = movieJson.getJSONArray("results");
        List<Movie>  movieResults = new ArrayList<>();

        for (int i = 0; i < movieArray.length(); i++){

            String posterPath = movieArray.getJSONObject(i).optString("poster_path");
            String title = movieArray.getJSONObject(i).optString("title");
            String releaseDate = movieArray.getJSONObject(i).optString("release_date");
            String voteAverage = movieArray.getJSONObject(i).optString("vote_average");
            String overview = movieArray.getJSONObject(i).optString("overview");

            movieResults.add(new Movie(title, posterPath, releaseDate, voteAverage, overview));
        }

        return movieResults;
    }
}
