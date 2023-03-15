package com.Student.Result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.Student.Result.databinding.ActivityUpdateBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    String getId;
    private ActivityUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUpdateBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        binding.updateName.setText(getIntent().getStringExtra("name"));
        binding.updateStudentId.setText(getIntent().getStringExtra("student Id"));
        binding.updateSection.setText(getIntent().getStringExtra("section"));
        binding.updateResult.setText(getIntent().getStringExtra("result"));
        binding.updateDept.setText(getIntent().getStringExtra("dept"));
        binding.updateSemester.setText(getIntent().getStringExtra("semester"));
        getId=getIntent().getStringExtra("id");

        //Log.d(TAG, "onCreate: "+getId);

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.updateName.getText().toString().isEmpty()){
                    binding.updateName.setError("name requried");

                }else if(binding.updateStudentId.getText().toString().isEmpty()){
                    binding.updateStudentId.setError("name requried");

                }else if(binding.updateSection.getText().toString().isEmpty()){
                    binding.updateSection.setError("name requried");

                }else if(binding.updateResult.getText().toString().isEmpty()) {
                    binding.updateResult.setError("name requried");

                }else if(binding.updateDept.getText().toString().isEmpty()) {
                    binding.updateDept.setError("name requried");


                }else if(binding.updateSemester.getText().toString().isEmpty()) {
                    binding.updateSemester.setError("name requried");

                }else {


                    Modelresultlist Modelresultlist=new Modelresultlist();
                    Modelresultlist.setId(getId);
                    Modelresultlist.setName(binding.updateName.getText().toString());
                    Modelresultlist.setStudent_id(binding.updateStudentId.getText().toString());
                    Modelresultlist.setSection(binding.updateSection.getText().toString());
                    Modelresultlist.setResult(binding.updateResult.getText().toString());
                    Modelresultlist.setDeptment(binding.updateDept.getText().toString());
                    Modelresultlist.setSemester(binding.updateSemester.getText().toString());


                    apiInterface.UpdatePerson(Modelresultlist).enqueue(new Callback<com.Student.Result.Modelresultlist>() {
                        @Override
                        public void onResponse(Call<com.Student.Result.Modelresultlist> call, Response<com.Student.Result.Modelresultlist> response) {

                            Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateActivity.this, ListActivity.class));


                        }

                        @Override
                        public void onFailure(Call<com.Student.Result.Modelresultlist> call, Throwable t) {

                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

                        }
                    });

                }




            }
        });


    }

}