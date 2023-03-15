package com.Student.Result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.Student.Result.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity {

    ArrayList<Modelresultlist> resultlist=new ArrayList<>();
    Adapterresultlist adapterresultlist;
    ApiInterface apiInterface;

    private ActivityListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //recycler view serup...........
        binding .recyclerResultlist.setLayoutManager(new LinearLayoutManager(this));
        adapterresultlist = new Adapterresultlist(resultlist,this);


        //get data from the database by api call.......

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.getAllData().enqueue(new Callback<List<Modelresultlist>>() {
            @Override
            public void onResponse(Call<List<Modelresultlist>> call, Response<List<Modelresultlist>> response) {

                resultlist.addAll(response.body());
                binding.recyclerResultlist.setAdapter(adapterresultlist);
                adapterresultlist.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Modelresultlist>> call, Throwable t) {

                Toast.makeText(ListActivity.this,  "Something is wrong !", Toast.LENGTH_SHORT).show();


            }
        });

        binding .searchResultlist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                ArrayList<Modelresultlist> search_arraylist=new ArrayList<>();
                for (Modelresultlist item: resultlist){

                    if (item.getName().toLowerCase().contains(editable.toString().toLowerCase())
                            || item.getStudent_id().toLowerCase().contains(editable.toString().toLowerCase())
                            || item.getSection().toLowerCase().contains(editable.toString().toLowerCase())
                            || item.getResult().toLowerCase().contains(editable.toString().toLowerCase())
                            || item.getDeptment().toLowerCase().contains(editable.toString().toLowerCase())
                            || item.getSemester().toLowerCase().contains(editable.toString().toLowerCase())

                    )
                    {
                        search_arraylist.add(item);
                    }
                }

                adapterresultlist.search_list(search_arraylist);

            }
        });


    }

}