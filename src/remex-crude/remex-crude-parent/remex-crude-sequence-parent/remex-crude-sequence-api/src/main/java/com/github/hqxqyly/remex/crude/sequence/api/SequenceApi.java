package com.github.hqxqyly.remex.crude.sequence.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.crude.sequence.entity.SequenceEntity;
import com.github.hqxqyly.remex.crude.sequence.req.SequenceByKeyOpReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;

/**
 * 用户安全接口
 * 
 * @author Qiaoxin.Hong
 *
 * @param <BEAN>
 */
@RequestMapping("/sequence/")
public interface SequenceApi<BEAN extends SequenceEntity> {

	/**
	 * 获取下一个序列，并不会直接修改数据
	 * @param seq
	 * @return
	 */
	@PostMapping("nextSeq")
	Result<String> nextSeq(SequenceByKeyOpReq seq);
	
	/**
	 * 获取下一个序列，并直接修改数据"
	 * @param seq
	 * @return
	 */
	@PostMapping("nextAndSaveSeq")
	Result<String> nextAndSaveSeq(SequenceByKeyOpReq seq);
}
