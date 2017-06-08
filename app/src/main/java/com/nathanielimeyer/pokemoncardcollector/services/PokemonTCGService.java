package com.nathanielimeyer.pokemoncardcollector.services;

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
                for (int i = 0; i < cardsJSON.length(); i++) {
                    JSONObject cardJSON = cardsJSON.getJSONObject(i);
                    String id = cardJSON.getString("id");
                    String cardName = cardJSON.getString("name");
                    int nationalPokedexNumber = cardJSON.getInt("nationalPokedexNumber");
                    String imageUrl = cardJSON.getString("imageUrl");
                    String imageUrlHiRes = cardJSON.getString("imageUrlHiRes");
                    String subtype = cardJSON.getString("subtype");
                    String supertype = cardJSON.getString("supertype");
                    int hp = cardJSON.getInt("hp");

                    ArrayList<String> retreatCosts = new ArrayList<>();
                    JSONArray retreatCostJSON = cardJSON.getJSONArray("retreatCost");
                    for (int y = 0; y < retreatCostJSON.length(); y++) {
                        retreatCosts.add(retreatCostJSON.getString(y));
                    }

                    String number = cardJSON.getString("number");
                    String artist = cardJSON.getString("artist");
                    String series = cardJSON.getString("series");
                    String set = cardJSON.getString("set");
                    String setCode = cardJSON.getString("setCode");

                    ArrayList<String> types = new ArrayList<>();
                    JSONArray typesJSON = cardJSON.getJSONArray("types");
                    for (int y = 0; y < typesJSON.length(); y++) {
                        types.add(typesJSON.getString(y));
                    }

                    Card card = new Card(id, cardName, nationalPokedexNumber, imageUrl,
                            imageUrlHiRes, subtype, supertype, hp, retreatCosts, number, artist,
                            series, set, setCode, types);
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
