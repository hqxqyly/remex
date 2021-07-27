package com.github.hqxqyly.remex.crude.sequence.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.github.hqxqyly.remex.crude.sequence.api.SequenceApi;
import com.github.hqxqyly.remex.crude.sequence.entity.SequenceEntity;

/**
 * 序列feign
 * 
 * @author Qiaoxin.Hong
 *
 */
//@FeignClient(name = "${remexProjectName:Please configure the properties remexProjectName}${remexOrgProjectName:-org-service}"
//	, contextId = "remex-crude-sequence-${remexProjectName:Please configure the properties remexProjectName}${remexOrgProjectName:-org-service}")
@FeignClient(name = "${remexProjectName:Please configure the properties remexProjectName}${remexOrgProjectName:-org-service}"
	, contextId = "com.github.hqxqyly.remex.crude.sequence.feign.SequenceFeign")
public interface SequenceFeign extends SequenceApi<SequenceEntity> {

}
