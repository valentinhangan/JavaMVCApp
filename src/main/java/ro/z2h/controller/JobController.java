package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.domain.Job;
import ro.z2h.service.EmployeeServiceImpl;
import ro.z2h.service.JobServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hangan on 11/13/2014.
 */
@MyController(urlPath = "/job")
public class JobController {
    @MyRequestMethod(urlPath = "/all")

    public List<Job> getAllJobs(){
        List<Job> jobs = new ArrayList();

        JobServiceImpl j = new JobServiceImpl();
        jobs=j.findAllJobs();

        return jobs;
    }
    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(String Id){
        Job j = new Job();
        JobServiceImpl job = new JobServiceImpl();
        j = job.findOneJob(String.valueOf(Id));
        return j;
    }
}
