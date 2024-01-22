package com.advantest.logExecTime.jobs;

import org.eclipse.core.runtime.jobs.Job;

import java.util.HashMap;
import java.util.Map;

public class JobManager {

    private Map<String,Long> jobMap = new HashMap<>();

    public void jobStarted(String jobId, JobType jobType){
        long startTime = System.currentTimeMillis();
        jobMap.put(jobId,startTime);
        System.out.println("Job Start: " + jobId + " " +  startTime + " " + jobType);
    }

    public void jobFinished(String jobId,JobType jobType){
        long finishTime = System.currentTimeMillis();
        jobMap.remove(jobId);
        System.out.println("Job Finish: " + jobId + " " +  finishTime + " " + jobType);
    }
}
