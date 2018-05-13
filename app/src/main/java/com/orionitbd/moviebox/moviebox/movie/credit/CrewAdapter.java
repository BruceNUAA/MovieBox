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

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CastViewHolder>{

    private Context context;
    private List<CreditResponse.Crew> crewList;

    public CrewAdapter ( Context context, List<CreditResponse.Crew> castList ) {
        this.context = context;
        this.crewList = castList;
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
        holder.castName.setText(crewList.get (position).getName ());
        holder.castOriginalName.setText(crewList.get (position).getDepartment ());

        String posterPath = crewList.get(position).getProfilePath ().toString ();
        Uri posterUri = Uri.parse("http://image.tmdb.org/t/p/w342/"+posterPath);
        Picasso.get()
                .load(posterUri)
                .into(holder.castPoster);
    }

    @Override
    public int getItemCount () {
        return crewList.size ();
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
