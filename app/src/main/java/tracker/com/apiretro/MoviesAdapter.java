package tracker.com.apiretro;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {


Context context;

    private List<Record> record;

    public MoviesAdapter(List<Record> list) {
        record = list;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author,lang;
       public ImageView imageView;

      public ProgressBar process;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textView);
            author = (TextView) view.findViewById(R.id.textView3);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            process=(ProgressBar)view.findViewById(R.id.progressBar);
                    }

        }



    public MoviesAdapter(Context c, List<Record> record) {
        this.context=c;
        this.record = record;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Record dinesh = record.get(position);
        holder.title.setText(dinesh.getCategoryId());
        holder.author.setText(dinesh.getCategoryName());

        Picasso.with(context)
                .load("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png")

                // optional
                .resize(400,400)                        // optional
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.process.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });




    }

    @Override
    public int getItemCount() {
        return record.size();
    }
}
