package com.sample.samplePoc.validators;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.samplePoc.DTO.JobDTO;
import com.sample.samplePoc.ExceptionUtils.ResponseMessage;
import com.sample.samplePoc.ExceptionUtils.ValidationException;
import com.sample.samplePoc.beans.JobSearchBean;
import com.sample.samplePoc.enums.JobCategories;
import com.sample.samplePoc.mappers.CommonMapper;
import com.sample.samplePoc.services.JobService;

@Component
public class JobResourceValidator {

	@Autowired
	private JobService jobservice;
	

	public void validateJobSearch(JobSearchBean jobSearchBean) {
		validateCategories(jobSearchBean.getCategory());
		if (CollectionUtils.isNotEmpty(jobSearchBean.getIds())) {
			validateJobIdsExistence(jobSearchBean.getIds());
		}
	}

	public void validateJobCreation(JobDTO jobDTO) {
		Set<String> categorySet = new HashSet<>(1);
		categorySet.add(jobDTO.getCategory());
		validateCategoriesExistence(categorySet);
		validateCategories(categorySet);
		validateJobTitle(jobDTO.getTitle());
	}

	public void validateJobUpdation(JobDTO jobDTO) {
		Set<String> categorySet = new HashSet<>(1);
		Set<Long> idsSet = new HashSet<>(1);
		categorySet.add(jobDTO.getCategory());
		idsSet.add(jobDTO.getId());
		validateJobIdsExistence(idsSet);
		validateCategoriesExistence(categorySet);
		validateCategories(categorySet);
		validateJobTitle(jobDTO.getTitle());
	}

	public void validateJobIdsExistence(Set<Long> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			ids.stream().forEach(id -> {
				if (Objects.isNull(jobservice.findById(id))) {
					throw new ValidationException(ResponseMessage.Code.INVALID_JOB_ID_FOUND, "error.invalid.job.id",
							new Object[] { id });
				}
			});
		}
	}

	public void validateCategories(Set<String> categories) {
		if (CollectionUtils.isNotEmpty(categories)) {
			categories.stream().forEach(category -> {
				if (!EnumUtils.isValidEnum(JobCategories.class, category)) {
					throw new ValidationException(ResponseMessage.Code.INVALID_JOB_CATEGORY_FOUND,
							"error.invalid.jobCategory", new Object[] { CommonMapper.convertJobCategoriesEnumToStringSet(JobCategories.values()) });
				}

			});
		}
	}

	public void validateCategoriesExistence(Set<String> categories) {
		if (CollectionUtils.isEmpty(categories)) {
			throw new ValidationException(ResponseMessage.Code.INVALID_JOB_CATEGORY_FOUND, "error.empty.jobCategory",
					new Object[] { JobCategories.values() });
		}
	}

	public void validateJobTitle(String title) {
		if (StringUtils.isEmpty(title)) {
			throw new ValidationException(ResponseMessage.Code.JOB_TITLE_REQUIRED, "error.job.title.required");
		}
		if (StringUtils.length(title) > 250) {
			throw new ValidationException(ResponseMessage.Code.JOB_TITLE_LIMIT_EXCEED, "error.job.title.limit.exceed");
		}
	}

}
