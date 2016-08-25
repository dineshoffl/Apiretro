package tracker.com.apiretro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Example> {
public TextView textView;
    private ImageView imageView;
    private TextView title;
   private MoviesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView)findViewById(R.id.textView);


        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        imageView = (ImageView) findViewById(R.id.imageView);




        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.whalts.com/agastyaaQbank/service/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        GithubUserAPI githubUserAPI = retrofit.create(GithubUserAPI.class);

        Call<Example> call = githubUserAPI.getUser();
        //asynchronous call
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Example> call, Response<Example> response) {
        textView=(TextView)findViewById(R.id.textView);
        int code = response.code();
        if (code == 200) {
            Example dinesh = response.body();
            adapter=new MoviesAdapter(this, dinesh.getRecords());
            recyclerView.setAdapter(adapter);

        } else {
            Toast.makeText(this, "Did not work: " + String.valueOf(code), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<Example> call, Throwable t) {
        Toast.makeText(this, "Nope", Toast.LENGTH_LONG).show();

    }


    }

