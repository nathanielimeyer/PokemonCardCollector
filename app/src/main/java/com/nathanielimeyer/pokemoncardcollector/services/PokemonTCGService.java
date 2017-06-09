package com.nathanielimeyer.pokemoncardcollector.services;

import android.util.Log;

import com.nathanielimeyer.pokemoncardcollector.Constants;
import com.nathanielimeyer.pokemoncardcollector.models.Card;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokemonTCGService {
    private static final String TAG = PokemonTCGService.class.getSimpleName();
    public static void findCardsByName(String query, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.POKEMONTCG_CARDS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.POKEMONTCG_NAME_QUERY_PARAMETER, query);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public static ArrayList<Card> processResults(Response response) {
        ArrayList<Card> cards = new ArrayList<>();
        try{
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONObject pokemonJSON = new JSONObject(jsonData);
                JSONArray cardsJSON = pokemonJSON.getJSONArray("cards");
                Log.d(TAG, "Query returned " + cardsJSON.length() + " cards.");
                for (int i = 0; i < cardsJSON.length(); i++) {
                    JSONObject cardJSON = cardsJSON.getJSONObject(i);
                    String id = cardJSON.optString("id");
                    String cardName = cardJSON.optString("name");
                    String nationalPokedexNumber = cardJSON.optString("nationalPokedexNumber");
                    String imageUrl = cardJSON.optString("imageUrl");
                    String imageUrlHiRes = cardJSON.optString("imageUrlHiRes");
                    String subtype = cardJSON.optString("subtype");
                    String supertype = cardJSON.optString("supertype");
                    int hp = cardJSON.optInt("hp");

                    ArrayList<String> retreatCosts = new ArrayList<>();
                    JSONArray retreatCostJSON = cardJSON.optJSONArray("retreatCost");
                    if (retreatCostJSON != null) {
                        for (int y = 0; y < retreatCostJSON.length(); y++) {
                            retreatCosts.add(retreatCostJSON.optString(y));
                        }
                    }
                    String number = cardJSON.optString("number");
                    String artist = cardJSON.optString("artist");
                    String series = cardJSON.optString("series");
                    String set = cardJSON.optString("set");
                    String setCode = cardJSON.optString("setCode");

                    ArrayList<String> types = new ArrayList<>();
                    JSONArray typesJSON = cardJSON.optJSONArray("types");
                    if (typesJSON != null) {
                        for (int y = 0; y < typesJSON.length(); y++) {
                            types.add(typesJSON.optString(y));
                        }
                    }
                    String rarity = cardJSON.optString("rarity");
                    String text = cardJSON.optString("text");
                    Card card = new Card(id, cardName, nationalPokedexNumber, imageUrl,
                            imageUrlHiRes, subtype, supertype, hp, retreatCosts, number, artist,
                            series, set, setCode, types, rarity, text);
                    cards.add(card);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
