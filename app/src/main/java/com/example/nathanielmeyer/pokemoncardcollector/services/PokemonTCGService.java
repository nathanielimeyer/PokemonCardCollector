package com.example.nathanielmeyer.pokemoncardcollector.services;

import android.util.Log;

import com.example.nathanielmeyer.pokemoncardcollector.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PokemonTCGService {
    public static final String TAG = PokemonTCGService.class.getSimpleName();
    public static void findCardsByName(String query, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.POKEMONTCG_CARDS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.POKEMONTCG_NAME_QUERY_PARAMETER, query);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Log.v(TAG, request.toString());
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
