package com.github.hqxqyly.remex.crude.sequence.controller.preinstall;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.RestController;

import com.github.hqxqyly.remex.crude.sequence.api.SequenceApi;
import com.github.hqxqyly.remex.crude.sequence.controller.SequenceController;
import com.github.hqxqyly.remex.crude.sequence.entity.SequenceEntity;

/**
 * 序列预设接口
 * 
 * @author Qiaoxin.Hong
 *
 */
@ConditionalOnMissingBean(value = SequenceController.class, ignored = SequencePreinstallController.class)
@RestController
public class SequencePreinstallController extends SequenceController<SequenceEntity> implements SequenceApi<SequenceEntity> {

}
