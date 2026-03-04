package com.batchExample.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotification implements JobExecutionListener {

    private Logger logger= LoggerFactory.getLogger(JobCompletionNotification.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        //JobExecutionListener.super.beforeJob(jobExecution);
        logger.info("Job Started");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //JobExecutionListener.super.afterJob(jobExecution);
        System.out.println("Job finished with status: " + jobExecution.getStatus());
        if(jobExecution.getStatus()== BatchStatus.COMPLETED){
            logger.info("Job Completed");
        }
    }
}
