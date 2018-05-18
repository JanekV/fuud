package iti0202_gui.ttu.ee.fuud;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String BITSTOP_DATA = "https://api.fuud.ituk.ee/bitstop";
    public static final String DAILY_DATA = "https://api.fuud.ituk.ee/daily";
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // Each card item is the same size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = (SearchView) findViewById(R.id.searchView);

        listItems = new ArrayList<>();

        adapter = new MyAdapter(listItems, getApplicationContext());    // Makes an instance of the adapter with the list of items above
        recyclerView.setAdapter(adapter);

        // Where items go to the list
        loadRecyclerViewData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        listItems = new ArrayList<>();

                        adapter = new MyAdapter(listItems, getApplicationContext());    // Makes an instance of the adapter with the list of items above
                        recyclerView.setAdapter(adapter);

                        // Where items go to the list
                        loadRecyclerViewData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();


        StringRequest stringRequestBitstop = getStringRequest(BITSTOP_DATA);
        StringRequest stringRequestDaily = getStringRequest(DAILY_DATA);

        progressDialog.dismiss();


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestBitstop);
        requestQueue.add(stringRequestDaily);

    }



    @NonNull
    private StringRequest getStringRequest(String URL) {
        return new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("data");
                            System.out.println(array.toString());
                            for (int i = 0; i < array.length(); i++) { // for each item (meal)
                                JSONObject o = array.getJSONObject(i);
                                ListItem item = new ListItem(           // get data about the meal
                                        o.getString("providers"),
                                        o.getString("price"),
                                        o.getString("name_eng"),
                                        o.getString("name_est")
                                );
                                listItems.add(item);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }


}
