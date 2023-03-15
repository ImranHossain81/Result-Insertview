package com.Student.Result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.Student.Result.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    private ActivityMainBinding binding;
    RadioButton EEE,IT,SWE,CSE,CVEL;
    String selectDeptment;
    String selectsemester;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //semester = getResources().getStringArray(R.array.semester);
        //spinner = (Spinner) findViewById(R.id.spi_semester);


        binding.spiSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectsemester = binding.spiSemester.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //connect interface class to call the api..

        Retrofit instance = ApiClient.getClient();
        apiInterface = instance.create(ApiInterface.class);


        EEE=findViewById(R.id.red_EEE);
        IT=findViewById(R.id.red_IT);
        SWE=findViewById(R.id.red_SWE);
        CSE=findViewById(R.id.red_CSE);
        CVEL=findViewById(R.id.red_CVEL);


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(EEE.isChecked())
                {
                    selectDeptment="EEE";
                }
                else if (IT.isChecked())
                {
                    selectDeptment="IT";
                }
                else if (SWE.isChecked())
                {
                    selectDeptment="SWE";
                }
                else if (CSE.isChecked())
                {
                    selectDeptment="CSE";
                }
                else if (CVEL.isChecked())
                {
                    selectDeptment="CVEL";
                }


                Modelresultlist Modelresultlist=new Modelresultlist();
                Modelresultlist.setName(binding.etName.getText().toString());
                Modelresultlist.setStudent_id(binding.etStudentId.getText().toString());
                Modelresultlist.setSection(binding.etSection.getText().toString());
                Modelresultlist.setResult(binding .etResult.getText().toString());
                Modelresultlist.setDeptment(selectDeptment);
                Modelresultlist.setDeptment(selectsemester);




                if(binding.etName.getText().toString().isEmpty())
                {
                    binding.etName.setError("Invalid name !");
                    Toast.makeText(MainActivity.this, "Name can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if(binding.etStudentId.getText().toString().isEmpty())
                {
                    binding.etStudentId.setError("Invalid student id !");
                    Toast.makeText(MainActivity.this, "Student Id can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if(binding.etSection.getText().toString().isEmpty())
                {
                    binding.etSection.setError("Invalid section !");
                    Toast.makeText(MainActivity.this, "Section can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if(binding.etResult.getText().toString().isEmpty())
                {
                    binding.etResult.setError("Invalid result !");
                    Toast.makeText(MainActivity.this, "Result can't be empty", Toast.LENGTH_SHORT).show();
                }





                apiInterface.insertresultlist(Modelresultlist).enqueue(new Callback<Modelresultlist>() {
                    @Override
                    public void onResponse(Call<Modelresultlist> call, Response<Modelresultlist> response) {

                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Modelresultlist> call, Throwable t) {

                        Toast.makeText(MainActivity.this, "failour", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        binding.showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,ListActivity.class));

            }
        });
    }
}