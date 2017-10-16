package edu.umkc.ch043097.freshstart;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JobsResultActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getName();
    // First parm is input type to doInBackground
    // Second parm is input type to onProgressUpdate
    // Third parm is return type of doInBackground and
    //    input type of onPostExecute
    private class JobServiceTask extends AsyncTask<String, Void, List> {

        // String... means the client of this routine can pass
        //   zero or more string values. They are received
        //   as an array: parms[0], parms[1], etc.
        // For more info, see: http://stackoverflow.com/questions/3158730/java-3-dots-in-parameters?rq=1
        protected List doInBackground(String... parms) {

            JobsService jobsservice = new JobsService(parms[0]);
            try {
                List jobs = jobsservice.getJobs();

                return jobs;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ArrayList();
        }

        protected void onProgressUpdate(Void... values) {

        }

        //  invoked on the UI thread after the background computation finishes
        protected void onPostExecute(List jobs) {
            // Example assert statements
            //Assert.assertNull(temperature);
            Assert.assertNotNull("Error: job is null",jobs);
            Assert.assertTrue(3<4);
            HashMap<String, Integer> map = processJobs(jobs);

            String output = "";
            for(Iterator<Map.Entry<String,Integer>>it=map.entrySet().iterator();it.hasNext();) {
                Map.Entry<String, Integer> entry = it.next();
                output += entry.getKey();
                output += (":" + entry.getValue() + "\n");
            }
            //updateUI(map.toString().replace("{","").replace("}",""));
            updateUI(output);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_result);

        String job_title = getIntent().getStringExtra("JOB_TITLE");

        new JobServiceTask().execute(job_title);

    }

    private void updateUI(String job) {


        TextView jobOutput = (TextView) findViewById(R.id.jobs_result_text_view);
        jobOutput.setText(job);
    }

    private HashMap<String, Integer> processJobs(List<Job> jobs){

        HashMap<String, Integer> locationCounts = new HashMap<>();

        for (int i = 0; i < jobs.size(); i++) {
            String location = jobs.get(i).getLocation();
            if(!locationCounts.containsKey(location)){
                locationCounts.put(location, 1);
            }
            else{
                locationCounts.put(location, locationCounts.get(location) + 1);
            }
        }

        for(Iterator<Map.Entry<String,Integer>>it=locationCounts.entrySet().iterator();it.hasNext();){
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() <= 10) {
                it.remove();
            }
        }
        return locationCounts;
    }

}