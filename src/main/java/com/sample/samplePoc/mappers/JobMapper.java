package com.sample.samplePoc.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.sample.samplePoc.DTO.JobDTO;
import com.sample.samplePoc.Domain.Job;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, componentModel = "spring")
public interface JobMapper {

	public JobDTO jobToJobDTO(Job job);

	public Job jobDTOToJob(JobDTO jobDTO);

}
