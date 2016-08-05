package com.example.admin.listviewpicassofragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListviewFragment extends Fragment{

    private static final String TAG = ListviewFragment.class.getSimpleName() + "TAG_";

    private ArrayList<Student> students;
    private ArrayList<String> array;
    private ArrayAdapter<String> adapter;
    private ListView mListview;
    private CallbackComponent myComponent;

    interface CallbackComponent {
        void itemClicked(int position);
    }

    public void loadJson(String urlJson){
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Student>>() {}.getType();

        array = new ArrayList<String>();
        students = gson.fromJson(urlJson, listType);
        for(Student std: students){
            String text = std.name + " " + std.age + " " + std.grade;
            array.add(text);
        }
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,array);

        mListview.setAdapter(adapter);
        mListview.deferNotifyDataSetChanged();

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String[] str = adapter.getItem(position).split(" ");
                Toast.makeText(view.getContext(), str[0], Toast.LENGTH_SHORT).show();
                myComponent.itemClicked(position);
            }
        });

    }

    public ListviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myComponent = (CallbackComponent) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listview, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListview = (ListView) view.findViewById(R.id.a_main_list);
        new getJson(this).execute();

    }
}
