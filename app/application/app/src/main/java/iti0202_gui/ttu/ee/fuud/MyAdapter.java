package iti0202_gui.ttu.ee.fuud;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewProvider.setText(listItem.getProviders());
        holder.textViewPrice.setText(listItem.getPrice());
        holder.textViewNameEst.setText(listItem.getName_est());
        holder.textViewNameEng.setText(listItem.getName_eng());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewProvider;
        public TextView textViewPrice;
        public TextView textViewNameEst;
        public TextView textViewNameEng;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewProvider = (TextView) itemView.findViewById(R.id.textViewProvider);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            textViewNameEng = (TextView) itemView.findViewById(R.id.textViewNameEng);
            textViewNameEst = (TextView) itemView.findViewById(R.id.textViewNameEst);
        }
    }
}
