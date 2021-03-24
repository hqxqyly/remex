package com.github.hqxqyly.remex.crude.sequence.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.hqxqyly.remex.boot.sequence.utils.SequenceUtils;
import com.github.hqxqyly.remex.boot.validator.annotation.RemexValidate;
import com.github.hqxqyly.remex.crude.sequence.entity.SequenceEntity;
import com.github.hqxqyly.remex.crude.sequence.req.SequenceByKeyOpReq;
import com.github.hqxqyly.remex.fast.common.structure.rsp.Result;
import com.github.hqxqyly.remex.fast.server.structure.controller.agile.AgileBaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "序列接口")
@RequestMapping("/sequence/")
public class SequenceController<BEAN extends SequenceEntity> extends AgileBaseController<BEAN> {

	@RemexValidate
	@PostMapping("nextSeq")
	@ApiOperation("获取下一个序列，并不会直接修改数据")
	public Result<String> nextSeq(@RequestBody SequenceByKeyOpReq seq) {
		String nextSeq = SequenceUtils.nextSeq(seq.getSeqType());
		return newResult(nextSeq);
	}
	
	@RemexValidate
	@PostMapping("nextAndSaveSeq")
	@ApiOperation("获取下一个序列，并直接修改数据")
	public Result<String> nextAndSaveSeq(@RequestBody SequenceByKeyOpReq seq) {
		String nextSeq = SequenceUtils.nextAndSaveSeq(seq.getSeqType());
		return newResult(nextSeq);
	}
}
