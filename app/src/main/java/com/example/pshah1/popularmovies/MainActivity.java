package com.example.pshah1.popularmovies;


        import android.arch.lifecycle.ViewModelProvider;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;


        import com.example.pshah1.popularmovies.Util.Constant;
        import com.example.pshah1.popularmovies.Util.NetworkUtil;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String movieTitle[];
    private String posterUrl[];
    private RecyclerView recyclerView;
    private SampleAdapter sampleAdapter;
    // private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        URL url = NetworkUtil.buildURL(Constant.BASEURL);



        new PopularMovies().execute(url);
    }

    class PopularMovies extends AsyncTask<URL,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            String data = null;

            try {
                data = NetworkUtil.getResponse(urls[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try{

                JSONObject jobj1 = new JSONObject(data);
                JSONArray jsonArray = jobj1.getJSONArray("results");
                int arraylength = jsonArray.length();
                movieTitle = new String[arraylength];
                posterUrl = new String[arraylength];

                for(int i =0; i < arraylength ; i++)
                {
                    JSONObject jobj2 = jsonArray.getJSONObject(i);

                                       // JSONObject jobj3 = jobj2.getJSONObject("")
                    movieTitle[i] = jobj2.getString("title");
                    posterUrl[i] = jobj2.getString("poster_path");

                    Log.w("Title", movieTitle[i]);
                }

            } catch (JSONException e) {
                e.printStackTrace();

            }
            Log.d("data", data);
            return data;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


          //  LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this) );
            recyclerView.setHasFixedSize(true);
            sampleAdapter = new SampleAdapter(MainActivity.this,movieTitle,posterUrl);
            recyclerView.setAdapter(sampleAdapter);
        }
    }

}