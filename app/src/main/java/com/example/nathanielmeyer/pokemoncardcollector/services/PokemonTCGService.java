package com.example.nathanielmeyer.pokemoncardcollector.services;

import android.util.Log;

import com.example.nathanielmeyer.pokemoncardcollector.Constants;
import com.example.nathanielmeyer.pokemoncardcollector.models.Attack;
import com.example.nathanielmeyer.pokemoncardcollector.models.Card;
import com.example.nathanielmeyer.pokemoncardcollector.models.Weakness;

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

        Log.v(TAG, request.toString());
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public static ArrayList<Card> processResults(Response response) {
        ArrayList<Card> cards = new ArrayList<>();
        try{
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
//                JSONObject pokemonJSON = new JSONObject(jsonData);
                JSONObject pokemonJSON = new JSONObject("{\"cards\":[{\"id\":\"base5-63\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/base5/63.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/base5/63_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"63\",\"artist\":\"Kagemaru Himeno\",\"rarity\":\"Common\",\"series\":\"Base\",\"set\":\"Team Rocket\",\"setCode\":\"base5\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\"],\"name\":\"Sleep Powder\",\"text\":\"The Defending Pokémon is now Asleep.\",\"damage\":\"\",\"convertedEnergyCost\":1},{\"cost\":[\"Grass\"],\"name\":\"Poisonpowder\",\"text\":\"The Defending Pokémon is now Poisoned.\",\"damage\":\"10\",\"convertedEnergyCost\":1}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"gym1-47\",\"name\":\"Erika's Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/gym1/47.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/gym1/47_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"ability\":{\"name\":\"Photosynthesis\",\"text\":\"All Energy cards attached to Erika's Oddish provide Grass Energy instead of their usual type. This power works even while Erika's Oddish is Asleep, Confused, or Paralyzed.\",\"type\":\"Pokémon Power\"},\"hp\":\"40\",\"retreatCost\":[\"Colorless\"],\"number\":\"47\",\"artist\":\"Atsuko Nishida\",\"rarity\":\"Uncommon\",\"series\":\"Gym\",\"set\":\"Gym Heroes\",\"setCode\":\"gym1\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\",\"Grass\"],\"name\":\"Poisonpowder\",\"text\":\"Flip a coin. If heads, the Defending Pokémon is now Poisoned.\",\"damage\":\"\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"dp6-112\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/dp6/112.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/dp6/112_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"112\",\"artist\":\"Ken Sugimori\",\"rarity\":\"Common\",\"series\":\"Diamond & Pearl\",\"set\":\"Legends Awakened\",\"setCode\":\"dp6\",\"types\":[\"Psychic\"],\"attacks\":[{\"cost\":[\"Colorless\"],\"name\":\"Poisonpowder\",\"text\":\"Flip a coin. If heads, the Defending Pokémon is now Poisoned.\",\"damage\":\"\",\"convertedEnergyCost\":1},{\"cost\":[\"Psychic\",\"Colorless\"],\"name\":\"Ram\",\"text\":\"\",\"damage\":\"20\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Psychic\",\"value\":\"+10\"}]},{\"id\":\"neo1-68\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/neo1/68.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/neo1/68_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"40\",\"retreatCost\":[\"Colorless\"],\"number\":\"68\",\"artist\":\"Sumiyoshi Kizuki\",\"rarity\":\"Common\",\"series\":\"Neo\",\"set\":\"Neo Genesis\",\"setCode\":\"neo1\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Colorless\"],\"name\":\"Hide\",\"text\":\"Flip a coin. If heads, during your opponent's next turn, prevent all effects of attacks, including damage, done to Oddish.\",\"damage\":\"\",\"convertedEnergyCost\":1},{\"cost\":[\"Grass\",\"Grass\"],\"name\":\"Absorb\",\"text\":\"Remove a number of damage counters from Oddish equal to half the damage done to the Defending Pokémon (after applying Weakness and Resistance) (rounded up to the nearest 10). If Oddish has fewer damage counters than that, remove all of them.\",\"damage\":\"20\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"bw7-1\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/bw7/1.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/bw7/1_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"1\",\"artist\":\"Kanako Eo\",\"rarity\":\"Common\",\"series\":\"Black & White\",\"set\":\"Boundaries Crossed\",\"setCode\":\"bw7\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\"],\"name\":\"Absorb\",\"text\":\"Heal 10 damage from this Pokémon.\",\"damage\":\"10\",\"convertedEnergyCost\":1},{\"cost\":[\"Colorless\",\"Colorless\"],\"name\":\"Acid\",\"text\":\"\",\"damage\":\"20\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}],\"resistances\":[{\"type\":\"Water\",\"value\":\"-20\"}]},{\"id\":\"gym1-78\",\"name\":\"Erika's Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/gym1/78.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/gym1/78_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"78\",\"artist\":\"Ken Sugimori\",\"rarity\":\"Common\",\"series\":\"Gym\",\"set\":\"Gym Heroes\",\"setCode\":\"gym1\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\"],\"name\":\"Blot\",\"text\":\"If there are any damage counters on Erika's Oddish, remove 1 of them.\",\"damage\":\"\",\"convertedEnergyCost\":1},{\"cost\":[\"Colorless\",\"Grass\"],\"name\":\"Sporadic Sponging\",\"text\":\"If Erika's Oddish has any damage counters on it, flip a coin. If heads, remove 1 of those damage counters.\",\"damage\":\"20\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"ecard1-122\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/ecard1/122.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/ecard1/122_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"122\",\"artist\":\"Masako Yamashita\",\"rarity\":\"Common\",\"series\":\"E-Card\",\"set\":\"Expedition Base Set\",\"setCode\":\"ecard1\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Colorless\"],\"name\":\"Tackle\",\"text\":\"\",\"damage\":\"10\",\"convertedEnergyCost\":1},{\"cost\":[\"Colorless\",\"Grass\"],\"name\":\"Sleep Seed\",\"text\":\"The Defending Pokémon is now Asleep.\",\"damage\":\"10\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Psychic\",\"value\":\"×2\"}]},{\"id\":\"gym2-70\",\"name\":\"Erika's Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/gym2/70.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/gym2/70_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"40\",\"retreatCost\":[\"Colorless\"],\"number\":\"70\",\"artist\":\"Ken Sugimori\",\"rarity\":\"Common\",\"series\":\"Gym\",\"set\":\"Gym Challenge\",\"setCode\":\"gym2\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\"],\"name\":\"Strange Powder\",\"text\":\"Flip a coin. If heads, the Defending Pokémon is now Confused; if tails, the Defending Pokémon is now Asleep.\",\"damage\":\"10\",\"convertedEnergyCost\":1}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"ex5-68\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/ex5/68.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/ex5/68_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"68\",\"artist\":\"Yuka Morii\",\"rarity\":\"Common\",\"series\":\"EX\",\"set\":\"Hidden Legends\",\"setCode\":\"ex5\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Colorless\"],\"name\":\"Poisonpowder\",\"text\":\"Flip a coin. If heads, the Defending Pokémon is now Poisoned.\",\"damage\":\"\",\"convertedEnergyCost\":1},{\"cost\":[\"Grass\"],\"name\":\"Razor Leaf\",\"text\":\"\",\"damage\":\"10\",\"convertedEnergyCost\":1}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"dp6-111\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/dp6/111.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/dp6/111_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"40\",\"retreatCost\":[\"Colorless\"],\"number\":\"111\",\"artist\":\"Hiroki Fuchino\",\"rarity\":\"Common\",\"series\":\"Diamond & Pearl\",\"set\":\"Legends Awakened\",\"setCode\":\"dp6\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Colorless\"],\"name\":\"Stun Spore\",\"text\":\"Flip a coin. If heads, the Defending Pokémon is now Paralyzed.\",\"damage\":\"\",\"convertedEnergyCost\":1},{\"cost\":[\"Grass\"],\"name\":\"Blot\",\"text\":\"Remove 1 damage counter from Oddish.\",\"damage\":\"10\",\"convertedEnergyCost\":1}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"+10\"}]},{\"id\":\"xy7-1\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/xy7/1.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/xy7/1_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"1\",\"artist\":\"MAHOU\",\"rarity\":\"Common\",\"series\":\"XY\",\"set\":\"Ancient Origins\",\"setCode\":\"xy7\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\"],\"name\":\"Trip Over\",\"text\":\"Flip a coin. If heads, this attack does 10 more damage.\",\"damage\":\"10+\",\"convertedEnergyCost\":1}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"ex13-73\",\"name\":\"Oddish δ\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/ex13/73.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/ex13/73_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"40\",\"retreatCost\":[\"Colorless\"],\"number\":\"73\",\"artist\":\"Atsuko Nishida\",\"rarity\":\"Common\",\"series\":\"EX\",\"set\":\"Holon Phantoms\",\"setCode\":\"ex13\",\"types\":[\"Water\"],\"attacks\":[{\"cost\":[\"Colorless\"],\"name\":\"Tackle\",\"text\":\"\",\"damage\":\"10\",\"convertedEnergyCost\":1},{\"cost\":[\"Water\"],\"name\":\"Blot\",\"text\":\"Remove 2 damage counters from Oddish.\",\"damage\":\"10\",\"convertedEnergyCost\":1}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"hgss3-60\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/hgss3/60.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/hgss3/60_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"40\",\"retreatCost\":[\"Colorless\"],\"number\":\"60\",\"artist\":\"Mitsuhiro Arita\",\"rarity\":\"Common\",\"series\":\"HeartGold & SoulSilver\",\"set\":\"HS—Undaunted\",\"setCode\":\"hgss3\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Colorless\"],\"name\":\"Ram\",\"text\":\"\",\"damage\":\"10\",\"convertedEnergyCost\":1},{\"cost\":[\"Grass\"],\"name\":\"Find a Friend\",\"text\":\"Flip a coin. If heads, search your deck for a Grass Pokémon, show it to your opponent, and put it into your hand. Shuffle your deck afterward.\",\"damage\":\"\",\"convertedEnergyCost\":1}],\"weaknesses\":[{\"type\":\"Psychic\",\"value\":\"×2\"}]},{\"id\":\"ex10-64\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/ex10/64.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/ex10/64_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"64\",\"artist\":\"Kouki Saitou\",\"rarity\":\"Common\",\"series\":\"EX\",\"set\":\"Unseen Forces\",\"setCode\":\"ex10\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\"],\"name\":\"Stun Spore\",\"text\":\"Flip a coin. If heads, the Defending Pokémon is now Paralyzed.\",\"damage\":\"10\",\"convertedEnergyCost\":1}],\"weaknesses\":[{\"type\":\"Psychic\",\"value\":\"×2\"}]},{\"id\":\"ecard2-97\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/ecard2/97.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/ecard2/97_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"97\",\"artist\":\"Kyoko Umemoto\",\"rarity\":\"Common\",\"series\":\"E-Card\",\"set\":\"Aquapolis\",\"setCode\":\"ecard2\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\"],\"name\":\"Stun Spore\",\"text\":\"Flip a coin. If heads, the Defending Pokémon is now Paralyzed.\",\"damage\":\"\",\"convertedEnergyCost\":1},{\"cost\":[\"Colorless\",\"Colorless\"],\"name\":\"Ram\",\"text\":\"\",\"damage\":\"20\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]},{\"id\":\"base2-58\",\"name\":\"Oddish\",\"nationalPokedexNumber\":43,\"imageUrl\":\"https://images.pokemontcg.io/base2/58.png\",\"imageUrlHiRes\":\"https://images.pokemontcg.io/base2/58_hires.png\",\"subtype\":\"Basic\",\"supertype\":\"Pokémon\",\"hp\":\"50\",\"retreatCost\":[\"Colorless\"],\"number\":\"58\",\"artist\":\"Keiji Kinebuchi\",\"rarity\":\"Common\",\"series\":\"Base\",\"set\":\"Jungle\",\"setCode\":\"base2\",\"types\":[\"Grass\"],\"attacks\":[{\"cost\":[\"Grass\"],\"name\":\"Stun Spore\",\"text\":\"Flip a coin. If heads, the Defending Pokémon is now Paralyzed.\",\"damage\":\"10\",\"convertedEnergyCost\":1},{\"cost\":[\"Grass\",\"Grass\"],\"name\":\"Sprout\",\"text\":\"Search your deck for a Basic Pokémon named Oddish and put it onto your Bench. Shuffle your deck afterward. (You can't use this attack if your Bench is full.)\",\"damage\":\"\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Fire\",\"value\":\"×2\"}]}]}");
                JSONArray cardsJSON = pokemonJSON.getJSONArray("cards");
                for (int i = 0; i < cardsJSON.length(); i++) {
                    JSONObject cardJSON = cardsJSON.getJSONObject(i);
                    String id = cardJSON.getString("id");
                    Log.v(TAG, id);
                    String cardName = cardJSON.getString("name");
                    Log.v(TAG, cardName);
                    int nationalPokedexNumber = cardJSON.getInt("nationalPokedexNumber");
                    String imageUrl = cardJSON.getString("imageUrl");
                    Log.v(TAG, imageUrl);
                    String imageUrlHiRes = cardJSON.getString("imageUrlHiRes");
                    Log.v(TAG, imageUrlHiRes);
                    String subtype = cardJSON.getString("subtype");
                    String supertype = cardJSON.getString("supertype");
                    int hp = cardJSON.getInt("hp");

                    ArrayList<String> retreatCosts = new ArrayList<>();
                    JSONArray retreatCostJSON = cardJSON.getJSONArray("retreatCost");
                    for (int y = 0; y < retreatCostJSON.length(); y++) {
                        retreatCosts.add(retreatCostJSON.getString(y));
                    }

                    int number = cardJSON.getInt("number");
                    String artist = cardJSON.getString("artist");
                    String series = cardJSON.getString("series");
                    String set = cardJSON.getString("set");
                    String setCode = cardJSON.getString("setCode");

                    ArrayList<String> types = new ArrayList<>();
                    JSONArray typesJSON = cardJSON.getJSONArray("types");
                    for (int y = 0; y < typesJSON.length(); y++) {
                        types.add(typesJSON.getString(y));
                    }

                    ArrayList<Attack> attacks = new ArrayList<>();
                    JSONArray attacksJSON = cardJSON.getJSONArray("attacks");
                    for (int y = 0; y < attacksJSON.length(); y++) {
                        JSONObject attackJSON = attacksJSON.getJSONObject(y);
                        ArrayList<String> costs = new ArrayList<>();
                        JSONArray costsJSON = attackJSON.getJSONArray("cost");
                        for (int z = 0; z < costsJSON.length(); z++) {
                            costs.add(costsJSON.getString(z));
                        }
                        String attackName = attackJSON.getString("name");
                        String text = attackJSON.getString("text");
                        String damage = attackJSON.getString("damage");
                        int convertedEnergyCost = attackJSON.getInt("convertedEnergyCost");
                        Attack attack = new Attack(costs, attackName, text, damage, convertedEnergyCost);
                        attacks.add(attack);
                    }

                    ArrayList<Weakness> weaknesses = new ArrayList<>();
                    JSONArray weaknessesJSON = cardJSON.getJSONArray("weaknesses");
                    for (int y = 0; y < weaknessesJSON.length(); y++) {
                        JSONObject weaknessJSON = weaknessesJSON.getJSONObject(y);
                        String type = weaknessJSON.getString("type");
                        String value = weaknessJSON.getString("value");
                        Weakness weakness = new Weakness(type, value);
                        weaknesses.add(weakness);
                    }

                    Card card = new Card(id, cardName, nationalPokedexNumber, imageUrl,
                            imageUrlHiRes, subtype, supertype, hp, retreatCosts, number, artist,
                            series, set, setCode, types, attacks, weaknesses);
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
