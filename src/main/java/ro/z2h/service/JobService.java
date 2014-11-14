package ro.z2h.service;

import ro.z2h.domain.Job;

import java.util.List;

/**
 * Created by hangan on 11/13/2014.
 */
public interface JobService {
    public List<Job> findAllJobs();
    public Job findOneJob(String id);
}
