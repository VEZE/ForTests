package simple.fortests;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import simple.fortests.Json.Fetchdata;


/**
 * Created by starzhinskij on 29.01.2018.
 */

public class Gsonx extends AsyncTask<Void, Void, Void> {
    Fetchdata fetchdata;
    FillTopCard fillTopCard;
    String data = "";

    public Gsonx(FillTopCard fillTopCard) {
        this.fillTopCard = fillTopCard;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);


    }

    @Override
    protected Void doInBackground(Void... voids) {
        URL url = null;
        try {
            url = new URL("https://rna.cpl.by/api-feed.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        BufferedReader bufferedReader = new BufferedReader(reader);
//        String line = "";
//        while(line != null){
//            try {
//                line = bufferedReader.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            data = data + line;
//        }

        fetchdata = new Gson().fromJson(reader, Fetchdata.class);
        fillTopCard.asyncResult(fetchdata);
        return null;

    }

}
