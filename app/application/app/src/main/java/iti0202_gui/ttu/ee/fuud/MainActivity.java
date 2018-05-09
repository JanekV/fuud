package iti0202_gui.ttu.ee.fuud;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String BITSTOP_DATA = "https://api.fuud.ituk.ee/bitstop";
    public static final String DAILY_DATA = "https://api.fuud.ituk.ee/daily";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // Each card item is the same size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        // Where items go to the list
        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();


        StringRequest stringRequestBitstop = getStringRequest(progressDialog, BITSTOP_DATA);
        progressDialog.show();

        StringRequest stringRequestDaily = getStringRequest(progressDialog, DAILY_DATA);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestBitstop);
        requestQueue.add(stringRequestDaily);

        adapter = new MyAdapter(listItems, getApplicationContext());    // Makes an instance of the adapter with the list of items above
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private StringRequest getStringRequest(final ProgressDialog progressDialog, String URL) {
        return new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) { // for each item (meal)
                                JSONObject o = array.getJSONObject(i);
                                ListItem item = new ListItem(           // get data about the meal
                                        o.getString("providers"),
                                        o.getString("price"),
                                        o.getString("name_eng"),
                                        o.getString("name_est")
                                );
                                listItems.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }


}
