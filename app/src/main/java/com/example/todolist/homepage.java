package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {


    private EditText editTextTask;
    private Button buttonAdd;
    private ListView listViewTasks;
    private ArrayAdapter<String> tasksAdapter;
    private ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewTasks = findViewById(R.id.listViewTasks);

        taskList = new ArrayList<>();
        tasksAdapter = new ArrayAdapter<>(this, R.layout.list_color, taskList);

        listViewTasks.setAdapter(tasksAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });





        // Add long press listener to delete tasks
        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                removeTask(position);
                return true;
            }
        });
    }

    private void addTask() {
        String task = editTextTask.getText().toString().trim();
        if (!task.isEmpty() && !taskList.contains(task)) {
            taskList.add(task);
            tasksAdapter.notifyDataSetChanged();
            editTextTask.getText().clear();
        } else if (task.isEmpty()) {
            Toast.makeText(this,"Task cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Task already exists",Toast.LENGTH_SHORT).show();
        }
    }

    private void removeTask(int position) {
        if (position >= 0 && position < taskList.size()) {
            taskList.remove(position);
            tasksAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Task removed", Toast.LENGTH_SHORT).show();
        }
    }
}