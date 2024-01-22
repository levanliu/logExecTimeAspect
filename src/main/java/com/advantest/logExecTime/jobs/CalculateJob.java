package com.advantest.logExecTime.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public abstract class CalculateJob extends Job {

    protected JobManager jobManager;

    public CalculateJob(String name, JobManager jobManager) {
        super(name);
        this.jobManager = jobManager;
    }

    protected abstract void performRun(IProgressMonitor monitor) throws InterruptedException;

    protected abstract String getJobId();

    protected abstract JobType getJobType();

    @Override
    final protected IStatus run(IProgressMonitor monitor) {
        try {
            jobManager.jobStarted(getJobId(), getJobType());
            performRun(monitor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jobManager.jobFinished(getJobId(), getJobType());
        }

        return Status.OK_STATUS;
    }
}
