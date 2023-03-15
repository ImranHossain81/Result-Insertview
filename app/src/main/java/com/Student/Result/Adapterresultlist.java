package com.Student.Result;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapterresultlist extends RecyclerView.Adapter<Adapterresultlist.MyHolder> {

    ApiInterface apiInterface;
    ArrayList<Modelresultlist> resultlist;
    Context context;

    public Adapterresultlist(ArrayList<Modelresultlist> resultlist, Context context) {
        this.resultlist = resultlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemresultlist, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterresultlist.MyHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(resultlist.get(position).getName());
        holder.student_id.setText(resultlist.get(position).getStudent_id());
        holder.section.setText(resultlist.get(position).getSection());
        holder.result.setText(resultlist.get(position).getResult());
        holder.deptment.setText(resultlist.get(position).getDeptment());
        holder.semester.setText(resultlist.get(position).getSemester());



         holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent=new Intent(context, UpdateActivity.class);
                        intent.putExtra("name",holder.name.getText().toString());
                        intent.putExtra("student id",holder.student_id.getText().toString());
                        intent.putExtra("section",holder.section.getText().toString());
                        intent.putExtra("result",holder.result.getText().toString());
                        intent.putExtra("deptment",holder.deptment.getText().toString());
                        intent.putExtra("semester",holder.semester.getText().toString());
                         context.startActivity(intent);

                   }
         });

// api delete long click listview...
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete this item ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Modelresultlist Modelresultlist=new Modelresultlist();
                                Modelresultlist.setId(resultlist.get(position).getId());

                                apiInterface.deleteresultlist(Modelresultlist).enqueue(new Callback<Modelresultlist>() {

                                    @Override
                                    public void onResponse(Call<Modelresultlist> call, Response<Modelresultlist> response) {
                                        Toast.makeText(context, "Succesfully Deleteed", Toast.LENGTH_SHORT).show();

                                        resultlist.remove(position);
                                        notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onFailure(Call<Modelresultlist> call, Throwable t) {
                                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                notifyDataSetChanged();

                return false;
            }
        });

//

    }

    @Override
    public int getItemCount() {
        return resultlist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name,student_id,section,result,deptment,semester;

        public MyHolder(@NonNull View itemView) {
            super(itemView);


            name=itemView.findViewById(R.id.tex_name);
            student_id=itemView.findViewById(R.id.tex_student_id);
            section=itemView.findViewById(R.id.tex_section);
            result=itemView.findViewById(R.id.tex_result);
            deptment=itemView.findViewById(R.id.text_deptment);
            semester=itemView.findViewById(R.id.text_semester);


        }
    }

    public void search_list(ArrayList<Modelresultlist> list) {
        resultlist = list;
        notifyDataSetChanged();

    }
}

