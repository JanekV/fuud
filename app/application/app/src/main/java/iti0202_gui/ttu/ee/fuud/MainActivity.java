package iti0202_gui.ttu.ee.fuud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

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
        for (int i=0; i <= 10; i++) {
            ListItem listItem = new ListItem(
                    "heading " + (i+1),
                    "description stuff here"
            );
            listItems.add(listItem);
        }

        adapter = new MyAdapter(listItems, this); // Makes an instance of the adapter with the list of items above
        recyclerView.setAdapter(adapter);
    }


}
