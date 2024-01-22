package com.advantest.logExecTime.services;

import com.advantest.logExecTime.execTime.LogExecTime;
import com.advantest.logExecTime.jobs.AddJob;
import com.advantest.logExecTime.jobs.CalculateJob;
import com.advantest.logExecTime.jobs.JobManager;
import com.advantest.logExecTime.jobs.SubJob;

import java.util.ArrayList;
import java.util.List;

public class CalculateImpl implements Calculate {
    private final List<Integer> arrayList1 = new ArrayList<>();
    private final List<Integer> arrayList2 = new ArrayList<>();
    public JobManager jobManager = new JobManager();

    public CalculateImpl() {
        // Initialize arrayList.
        for (int i = 0; i < 100; i++) {
            arrayList1.add(i);
            arrayList2.add(2 * i);
        }
    }

    @LogExecTime
    @Override
    public void addTwoArrayList() {
        for (int i = 0; i < arrayList1.size(); i++) {
            CalculateJob addJob = new AddJob("Demo AddJob " + i, arrayList1, arrayList2, i, jobManager);
            addJob.schedule();
        }
    }

    @LogExecTime
    @Override
    public void subTwoArrayList() {
        for (int i = 0; i < arrayList1.size(); i++) {
            CalculateJob subJob = new SubJob("Demo SubJob " + i, arrayList1, arrayList2, i, jobManager);
            subJob.schedule();
        }
    }
}
