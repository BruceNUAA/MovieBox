package com.orionitbd.moviebox.moviebox.movie.credit;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orionitbd.moviebox.moviebox.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder>{

    private Context context;
    private List<CreditResponse.Cast> castList;

    public CastAdapter ( Context context, List<CreditResponse.Cast> castList ) {
        this.context = context;
        this.castList = castList;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v= inflater.inflate(R.layout.credit_row,parent,false);
        return new CastViewHolder(v);
    }

    @Override
    public void onBindViewHolder ( @NonNull CastViewHolder holder, int position ) {
        holder.castName.setText(castList.get (position).getCharacter ());
        holder.castOriginalName.setText(castList.get (position).getName ());

        String posterPath = castList.get(position).getProfilePath ().toString ();
        Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+posterPath);
        Picasso.get()
                .load(posterUri)
                .into(holder.castPoster);
    }

    @Override
    public int getItemCount () {
        return castList.size ();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{

        TextView castName,castOriginalName;
        ImageView castPoster;
        public CastViewHolder(final View itemView) {
            super(itemView);

            castName = itemView.findViewById(R.id.creditName);
            castOriginalName = itemView.findViewById(R.id.creditOriginalName);
            castPoster = itemView.findViewById(R.id.creditPoster);

        }
    }
}
