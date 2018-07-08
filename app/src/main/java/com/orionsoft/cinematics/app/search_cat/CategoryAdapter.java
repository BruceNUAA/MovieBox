package com.orionsoft.cinematics.app.search_cat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orionsoft.cinematics.app.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private List<CategoryResponse.Genre>genreList;
    private Context context;

    public CategoryAdapter ( Context context , List<CategoryResponse.Genre> genreList) {
        this.genreList = genreList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        View v= LayoutInflater.from (parent.getContext ())
                .inflate (R.layout.list_item_category,parent,false);

        return new ViewHolder (v);
    }

    @Override
    public void onBindViewHolder ( @NonNull ViewHolder holder, final int position ) {

        holder.categoryname.setText (genreList.get (position).getName ());
        holder.linearLayout.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Toast.makeText (context, "You Clicked "  +  genreList . get (position) . getName () , Toast.LENGTH_SHORT).show ();
            }
        });
    }

    @Override
    public int getItemCount () {
        return genreList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView categoryname;
        public LinearLayout linearLayout;

        public ViewHolder ( View itemView ) {
            super (itemView);

            categoryname=(TextView)itemView.findViewById (R.id.category);
            linearLayout=(LinearLayout)itemView.findViewById (R.id.linearLayout);
        }
    }
}
