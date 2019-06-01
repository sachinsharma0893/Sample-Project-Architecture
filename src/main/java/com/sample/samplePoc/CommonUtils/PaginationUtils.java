package com.sample.samplePoc.CommonUtils;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

public class PaginationUtils {
	public static final int DEFAULT_OFFSET = 1;

	public static final int MIN_OFFSET = 1;

	public static final int DEFAULT_LIMIT = 20;

	public static final int MAX_LIMIT = 100;
	
	public static Pageable generatePageRequest(Integer offset, Integer limit) {
	        if (offset == null || offset < MIN_OFFSET) {
	            offset = DEFAULT_OFFSET;
	        }
	        if (limit == null || limit > MAX_LIMIT) {
	            limit = DEFAULT_LIMIT;
	        }
	        return PageRequest.of(offset - 1, limit);
	        
	    }

	public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, HttpServletRequest request, Integer offset,
			Integer limit) throws URISyntaxException {
		if (offset == null || offset < MIN_OFFSET) {
			offset = DEFAULT_OFFSET;
		}
		if (limit == null || limit > MAX_LIMIT) {
			limit = DEFAULT_LIMIT;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Total-Count", "" + page.getTotalElements());
		return headers;
	}
	
	public static Pageable generatePageRequestWithSort(Integer offset, Integer limit, Sort order) {
        if (offset == null || offset < MIN_OFFSET) {
            offset = DEFAULT_OFFSET;
        }
        if (limit == null || limit > MAX_LIMIT) {
            limit = DEFAULT_LIMIT;
        }
        
        return  PageRequest.of(offset - 1, limit, order);
    }

}
