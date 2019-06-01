package com.sample.samplePoc.mappers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.EnumUtils;

import com.sample.samplePoc.ExceptionUtils.ResponseMessage;
import com.sample.samplePoc.ExceptionUtils.ValidationException;
import com.sample.samplePoc.enums.JobCategories;

public class CommonMapper {

	public static Set<String> convertJobCategoriesEnumToStringSet(JobCategories[] jobcategories) {
		Set<String> stringSet = new HashSet<>(jobcategories.length>0?jobcategories.length:1);
		if (ArrayUtils.isNotEmpty(jobcategories)) {
			Arrays.asList(jobcategories).stream().forEach(category -> stringSet.add(category.name()));
		}
		return stringSet;
	}
	public static Set<JobCategories> convertJobcategoryStringSetToEnumSet(Set<String> jobcategories) {
		Set<JobCategories> jobCategoriesSet = new HashSet<>(jobcategories.size()>0?jobcategories.size():1);
		jobcategories.stream().forEach(category -> {
			if (EnumUtils.isValidEnum(JobCategories.class, category)) {
				jobCategoriesSet.add(JobCategories.valueOf(category));
			}
			else {
				throw new ValidationException(ResponseMessage.Code.INVALID_JOB_CATEGORY_FOUND,
						"error.invalid.jobCategory", new Object[] { CommonMapper.convertJobCategoriesEnumToStringSet(JobCategories.values()) });
			}
		});
		return jobCategoriesSet;
	}

}
