package iti0202_gui.ttu.ee.fuud;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter{

    private MyAdapter adapter;
    private List<ListItem> listItemsToFilter;

    public CustomFilter(List<ListItem> listItemsToFilter, MyAdapter adapter) {
        this.adapter = adapter;
        this.listItemsToFilter = listItemsToFilter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            List<ListItem> filteredFuud = new ArrayList<>();

            for (int i = 0; i < listItemsToFilter.size(); i++) {
                if (listItemsToFilter.get(i).getName_est().toUpperCase().contains(constraint)
                        || listItemsToFilter.get(i).getName_eng().toUpperCase().contains(constraint)
                        || listItemsToFilter.get(i).getProviders().toUpperCase()
                        .contains(constraint)) {
                    filteredFuud.add(listItemsToFilter.get(i));
                }
            }
            results.count = filteredFuud.size();
            results.values = filteredFuud;
        } else {
            results.count = listItemsToFilter.size();
            results.values = listItemsToFilter;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setListItems((List<ListItem>) results.values);
        adapter.notifyDataSetChanged();
    }

}
