package edu.umkc.ch043097.freshstart;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CH043097 on 10/15/2017.
 */

public class JobsService {
    private String uri;
    private static final String TAG = JobsService.class.getName();

    public JobsService(String jobTitle){
        jobTitle = jobTitle.replace(" ", "%20");
        uri = "http://public.api.careerjet.net/search?locale_code=US_en&location=US&keywords=" + jobTitle;

    }

    public List<Job> getJobs() throws Exception{
        try {
            List jobsArray = new ArrayList();
            for (int i = 1; i < 51; i++) {
                JSONObject apiCallResult = JSONService
                        .fetchJSONObject(uri + "&page=" + i);

                Log.i(TAG, uri + "&page" + i);

                JSONArray jobs = apiCallResult.getJSONArray("jobs");
                for (int j = 0; j < 20; j++) {
                    Job job = new Job("", "", "");
                    String description = jobs.getJSONObject(j).getString("description");
                    job.setDescription(description);
                    String title = jobs.getJSONObject(j).getString("title");
                    job.setTitle(title);
                    String locations = jobs.getJSONObject(j).getString("locations");
                    if(locations.contains("-")){
                        String locationsList[];
                        locationsList = locations.split("-");
                        for (int k = 0; k < locationsList.length; k++) {
                            job.setLocation(locationsList[k]);
                            Log.i(TAG, job.toString());
                            jobsArray.add(job);
                        }
                    }
                    else{
                        job.setLocation(locations);
                        Log.i(TAG, job.toString());
                        jobsArray.add(job);
                    }
                }
            }
            //return apiCallResult.toString();
            return jobsArray;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Exception("Problem in JobsService.getJobs()");
        }
    }
}
