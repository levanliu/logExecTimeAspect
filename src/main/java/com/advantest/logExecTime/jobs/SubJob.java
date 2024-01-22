package com.advantest.logExecTime.jobs;

import org.eclipse.core.runtime.IProgressMonitor;

import java.util.List;

public class SubJob extends CalculateJob {
    private final List<Integer> arrayList1;
    private final List<Integer> arrayList2;
    private final int index;

    public SubJob(String methodName, List<Integer> arrayList1, List<Integer> arrayList2, int index, JobManager jobManager) {
        super(methodName,jobManager);
        this.arrayList1 = arrayList1;
        this.arrayList2 = arrayList2;
        this.index = index;
    }

    @Override
    protected void performRun(IProgressMonitor monitor) throws InterruptedException {
        int sum = arrayList1.get(index) + arrayList2.get(index);
        arrayList1.set(index, sum);
        Thread.sleep(500); // Sleep for 0.5 seconds
        System.out.println("SubJob executed at index: " + index);
    }

    @Override
    protected String getJobId() {
        return Thread.currentThread().getName();
    }

    @Override
    protected JobType getJobType() {
        return JobType.SubType;
    }
}
