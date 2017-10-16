package edu.umkc.ch043097.freshstart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button jobsSearchButton = (Button) findViewById(R.id.job_title_search_button);
        final EditText jobsSearchJobTitle = (EditText) findViewById(R.id.job_title_editText);
        jobsSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, JobsResultActivity.class);
                intent.putExtra("JOB_TITLE", jobsSearchJobTitle.getText().toString());
//                Toast.makeText(MainActivity.this, jobsSearchJobTitle.getText().toString(),
//                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
