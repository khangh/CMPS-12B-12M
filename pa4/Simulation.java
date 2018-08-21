/**
 * File: Simulation.java
 * Name: Sophia Tacderas
 * ID: 1465379
 * Due: 11/22/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa4
 * Purpose: Implements two ADTS, Job and Queue.
 * Borrows/modifies code from: SimulationStub.java
 */

import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.text.DecimalFormat;

public class Simulation{

    // The following function assembles the initial backup and/or storage queues.
    public static Job getJob(Scanner in) {
        String[] s = in.nextLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]);
        return new Job(a, d);
    }

    public static void main(String[] args) throws IOException{

        boolean trcFlag = false; // flag for traceFile prints

        // check number of command line arguments is 1
        if (args.length != 1) {
            System.out.println("Usage: Simulation <input file>");
            System.out.println();
            System.exit(1);
        }

        // open files
        Scanner inFile = new Scanner(new File(args[0]));
        PrintWriter rptFile = new PrintWriter(new FileWriter(args[0]+".rpt"));
        PrintWriter trcFile = new PrintWriter(new FileWriter(args[0]+".trc"));

        // read in jobs from input file
        String strJob = inFile.nextLine(); // # jobs in String format
        int job = Integer.parseInt(strJob); // # of jobs converted to int
        int sim = job - 1; // # of simulations

        Queue jobQueue = new Queue();
        Queue tempJobQueue = new Queue(); // loaded w/ complete jobQueue, otherwise simulation will run endlessly

        // enqueue jobs from input file into jobQueue and tempJobQueue
        while (inFile.hasNextLine()) {
            Job getJob = getJob(inFile);
            jobQueue.enqueue(getJob); // load job record into jobQueue
            tempJobQueue.enqueue(getJob); // also load job record into tempJobQueue, will be used at end of simulation
        }

        // add some lines to report file
        rptFile.println("Report file: " + args[0] + ".rpt");
        rptFile.println(job + " Jobs:");
        rptFile.println(jobQueue.toString());
        rptFile.println();
        rptFile.println("***********************************************************");
        // add some lines to trace file
        trcFile.println("Trace file: " + args[0]+ ".trc");
        trcFile.println(job + " Jobs:");
        trcFile.println(jobQueue.toString());
        trcFile.println();

        // run simulation with p processors for p=1 to p=sim
        for (int p = 1; p <= sim; p++) {

            // print processor heading in trace file
            trcFile.println("*****************************");
            if (p==1) {
                trcFile.println((p + " processor:"));
            }else {
                trcFile.println((p + " processors:"));
            }
            trcFile.println("*****************************");

            // declare and initialize an array of p processor Queues and any necessary storage Queues
            Queue[] processorQueue = null;
            processorQueue = new Queue[p];

            // adds storage processor queues based on # of simulations
            for (int ctr = 0; ctr < p; ctr++) {
                processorQueue[ctr] = new Queue();
            }
            Queue completeQueue = new Queue(); // queue for completed jobs

            int time = 0; // start timer
            int totalWait = 0; // how long unprocessed jobs had to wait
            int maxWait = 0; // longest an unprocessed job had to wait
            double averageWait = 0; // average time for completion of processed jobs, double so decimal value is kept

            // determine the time of the next arrival or finish event and update time
            while (completeQueue.length() != job) {     // while unprocessed jobs remain

                // complete all processes finishing now
                for (int ctr = 0; ctr < p; ctr++) {
                    if (processorQueue[ctr].length() > 0) {
                        Job currJob = (Job)processorQueue[ctr].peek();
                        if (currJob.getFinish() == time) {      // check if job Record has finished

                            trcFlag = true; // set to true for completed job

                            int waitTime = currJob.getWaitTime(); // get the job's wait time
                            if (waitTime > maxWait) {
                                maxWait = waitTime; // see if that is the longest wait time
                            }
                            totalWait += waitTime; // add wait time to total wait time

                            // since job has finished, dequeue from processor Queue and enqueue to complete Queue
                            completeQueue.enqueue(processorQueue[ctr].dequeue());

                            // After the processor Queue is dequeued and it is not empty,
                            // compute for the finish time of new front job record.
                            if (processorQueue[ctr].isEmpty() != true) {
                                Job frontJob = (Job)processorQueue[ctr].peek();
                                frontJob.computeFinishTime(time);
                            }

                        }
                    }
                }

                // If there are any jobs arriving now, assign them to a processor
                // Queue of minimum length and with lowest index in the queue array.
                if (jobQueue.isEmpty() != true) {

                    // define Job type jobRecord, cast object from Queue to Job
                    Job jobRecord = (Job)jobQueue.peek();
                    // gets arrival time for job record to get processed
                    int jobArrival = jobRecord.getArrival();

                    while (jobArrival == time) {

                        trcFlag = true; // set flag to true for arrival event

                        // Default minimum length to that of the 1st processor Queue will be used
                        // to store index of processor Queue with the least # of jobs.
                        Job newJob = (Job)jobQueue.dequeue();
                        int minQueueLength = processorQueue[0].length();
                        int minQueueLengthCtr = 0;

                        // find the processor Queue with the least # of jobs
                        for (int ctr = 0; ctr < p; ctr++) {
                            if (processorQueue[ctr].length() < minQueueLength) {
                                minQueueLength = processorQueue[ctr].length();
                                minQueueLengthCtr = ctr;
                            }
                        }

                        // The processor Queue with the least # of jobs will receive the jobQueue record.
                        processorQueue[minQueueLengthCtr].enqueue(newJob);

                        // check if the processor Queue only has a front record and compute its finish time
                        if (processorQueue[minQueueLengthCtr].length() == 1) {
                            Job frontJob = (Job)processorQueue[minQueueLengthCtr].peek();
                            frontJob.computeFinishTime(time);
                        }

                        // peek into jobQueue to satisfy while loop
                        // handles situations where there are multiple jobQueue records with same arrival time
                        if (jobQueue.isEmpty() != true) {
                            jobRecord = (Job) jobQueue.peek(); // peek into front record
                            jobArrival = jobRecord.getArrival(); // and get its arrival time
                        } else {
                            jobArrival = 0; // if jobQueue is empty, set arrival time to 0
                        }

                    }

                }

                // print in traceFile the time and current values of jobQueue, completeQueue, and all processor Queues
                // only do this if there are any changes in arrival and finish (when flag=true or time=0)
                if ((trcFlag == true) || (time == 0)) {
                    trcFile.println("time=" + time);
                    trcFile.println("0: " + jobQueue.toString() + completeQueue.toString());
                    for (int i=0; i < p; i++) {
                        trcFile.println(i+1 + ": " + processorQueue[i].toString());
                    }
                    trcFile.println(); // print extra line in traceFile to separate printed info for each time
                }

                time++; // increment timer until all jobs finish
                trcFlag = false; // reset flag to false after iteration

            }

            // compute the total wait, maximum wait, and average wait for all jobs
            averageWait = ((double)totalWait / job); // cast total wait to double to get decimal place
            // reduces average wait to 2 decimal places
            averageWait = Math.round(averageWait * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat("#0.00");

            // update report file
            if (p == 1) {
                rptFile.println("1 processor: totalWait=" + totalWait + ", maxWait=" + maxWait +
                        ", averageWait=" + df.format(averageWait));
            } else {
                rptFile.println(p + " processors: totalWait=" + totalWait + ", maxWait=" + maxWait +
                        ", averageWait=" + df.format(averageWait));
            }

            // then reset finish times
            // at end of each simulation reload jobQueue with jobRecords stored in tempJobQueue
            for (int i = 1; i<=job; i++) {
                Job resetJob = (Job)tempJobQueue.dequeue();
                resetJob.resetFinishTime(); // reset the finish time of each jobRecord
                jobQueue.enqueue(resetJob); // load to jobQueue
                tempJobQueue.enqueue(resetJob); // load to tempJobQueue
            }

        }

        // close input and output files
        inFile.close();
        rptFile.close();
        trcFile.close();

    }

}
