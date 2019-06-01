package com.sample.samplePoc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sample.samplePoc.CommonUtils.PaginationUtils;
import com.sample.samplePoc.Domain.Job;
import com.sample.samplePoc.beans.JobSearchBean;
import com.sample.samplePoc.repositories.JobRepository;
import com.sample.samplePoc.services.JobService;
import com.sample.samplePoc.specifications.JobSpecification;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public Job save(Job job) {
		return jobRepository.save(job);
	}

	@Override
	public Job update(Job job) {
		return jobRepository.findById(job.getId()).map(existingJob -> {
			existingJob.setTitle(job.getTitle());
			existingJob.setCategory(job.getCategory());
			existingJob.setDescription(job.getDescription());
			return jobRepository.save(existingJob);
		}).get();
	}

	@Override
	public void delete(Long id) {
		jobRepository.deleteById(id);

	}

	@Override
	public Page<Job> findAll(JobSearchBean jobSearchBean) {
		final JobSpecification spec = new JobSpecification(jobSearchBean);
		return jobRepository.findAll(spec,
				PaginationUtils.generatePageRequestWithSort(jobSearchBean.getOffset(), jobSearchBean.getLimit(),Sort.by(Order.desc("id"))));
	}

	@Override
	public Job findById(Long id) {
		return jobRepository.findOneById(id);
	}

}
