package com.sample.samplePoc.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.sample.samplePoc.Domain.Constants;
import com.sample.samplePoc.Domain.Job;
import com.sample.samplePoc.beans.JobSearchBean;
import com.sample.samplePoc.mappers.CommonMapper;

public class JobSpecification implements Specification<Job> {

	private static final long serialVersionUID = 1L;

	private JobSearchBean jobBean;

	JobSpecification() {
	}

	public JobSpecification(JobSearchBean jobBean) {
		this.jobBean = jobBean;
	}

	@Override
	public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		if (!CollectionUtils.isEmpty(jobBean.getIds())) {
			predicates.add(root.get("id").in(jobBean.getIds()));
		}

		if (!CollectionUtils.isEmpty(jobBean.getCategory())) {
			predicates.add(
					root.get("category").in(CommonMapper.convertJobcategoryStringSetToEnumSet(jobBean.getCategory())));
		}

		if (StringUtils.isNotEmpty(jobBean.getTitle())) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), Constants.PERCENTAGE_SEPARATOR
					+ StringUtils.lowerCase(jobBean.getTitle()) + Constants.PERCENTAGE_SEPARATOR));
		}
		if (StringUtils.isNotEmpty(jobBean.getDescription())) {
			predicates.add(
					criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), Constants.PERCENTAGE_SEPARATOR
							+ StringUtils.lowerCase(jobBean.getDescription()) + Constants.PERCENTAGE_SEPARATOR));
		}
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		query.distinct(true);
		return query.getRestriction();
	}

}
