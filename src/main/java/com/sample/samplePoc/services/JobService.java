package com.sample.samplePoc.services;

import org.springframework.data.domain.Page;

import com.sample.samplePoc.Domain.Job;
import com.sample.samplePoc.beans.JobSearchBean;

public interface JobService {

	public Job save(Job job);

	public Job update(Job job);

	public void delete(Long id);

	public Page<Job> findAll(JobSearchBean jobSearchBean);
	
	public Job findById(Long id);

}
