package com.sample.samplePoc.resources;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.samplePoc.CommonUtils.PaginationUtils;
import com.sample.samplePoc.DTO.JobDTO;
import com.sample.samplePoc.Domain.Job;
import com.sample.samplePoc.ExceptionUtils.ResponseMessage;
import com.sample.samplePoc.beans.JobSearchBean;
import com.sample.samplePoc.mappers.JobMapper;
import com.sample.samplePoc.services.JobService;
import com.sample.samplePoc.validators.JobResourceValidator;

@RestController
@RequestMapping(value = "/api/v1/job")
public class JobResource {

	private MessageSourceAccessor messageSourceAccesor;

	@Autowired
	private JobResourceValidator jobResourceValidator;

	@Autowired
	private JobService jobService;

	@Autowired
	private JobMapper jobMapper;

	@Autowired
	public JobResource(MessageSource messageSource) {
		this.messageSourceAccesor = new MessageSourceAccessor(messageSource);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<JobDTO>> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer offset,
			@RequestParam(value = "per_page", required = false, defaultValue = "10") Integer limit,
			HttpServletRequest request, @RequestParam(value = "jobId", required = false) Set<Long> jobIds,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "categories", required = false) Set<String> categories) throws URISyntaxException {
		JobSearchBean jobSearchBean = new JobSearchBean(jobIds, title, null, categories, offset, limit);
		jobResourceValidator.validateJobSearch(jobSearchBean);
		Page<Job> page = jobService.findAll(jobSearchBean);
		return new ResponseEntity<List<JobDTO>>(
				page.getContent().stream().map(jobMapper::jobToJobDTO).collect(Collectors.toList()),
				PaginationUtils.generatePaginationHttpHeaders(page, request, offset, limit), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<JobDTO> save(@RequestBody JobDTO jobDTO) throws URISyntaxException {
		jobResourceValidator.validateJobCreation(jobDTO);
		return new ResponseEntity<JobDTO>(jobMapper.jobToJobDTO(jobService.save(jobMapper.jobDTOToJob(jobDTO))),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JobDTO> update(@PathVariable Long id, @RequestBody JobDTO jobDTO) throws URISyntaxException {
		jobDTO.setId(id);
		jobResourceValidator.validateJobUpdation(jobDTO);
		return new ResponseEntity<JobDTO>(jobMapper.jobToJobDTO(jobService.update(jobMapper.jobDTOToJob(jobDTO))),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) throws URISyntaxException {
		Set<Long> idsSet = new HashSet<>(1);
		idsSet.add(id);
		jobResourceValidator.validateJobIdsExistence(idsSet);
		jobService.delete(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(ResponseMessage.Code.JOB_DELETED_SUCCESSFULLY,
				messageSourceAccesor.getMessage("message.job.deleted")), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<JobDTO> findById(@PathVariable Long id) throws URISyntaxException {
		Set<Long> idsSet = new HashSet<>(1);
		idsSet.add(id);
		jobResourceValidator.validateJobIdsExistence(idsSet);
		return new ResponseEntity<JobDTO>(jobMapper.jobToJobDTO(jobService.findById(id)), HttpStatus.OK);
	}

}
