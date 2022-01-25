package com.example.android_projekt_tamasarnold;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public void getTodos(View view) {

        Call<List<Todo>> call = apiInterface.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.e(TAG, "onResponse: " +  response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });



    }

    public void getTodoUsingRouteParameter(View view) {

        Call<Todo> todoCall = apiInterface.getTodo(3);
        todoCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.e(TAG, "onResponse: " + response.body() );
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });

    }

    public void getTodosUsingQuery(View view) {

        Call<List<Todo>> listCall = apiInterface.getTodosUsingQuery(3, false);
        listCall.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.e(TAG, "onResponse: " + response.body() );
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {

            }
        });

    }

    public void postTodo(View view) {

        Todo todo = new Todo(3, "Get me milk", false);

        Call<Todo> todoPostCall = apiInterface.postTodo(todo);
        todoPostCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.e(TAG, "onResponse: " + response.body() );
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {

            }
        });

    }
   /* private void generateDataList(List<Todo> todosList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new TodoAdapter(this,todoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }*/
}