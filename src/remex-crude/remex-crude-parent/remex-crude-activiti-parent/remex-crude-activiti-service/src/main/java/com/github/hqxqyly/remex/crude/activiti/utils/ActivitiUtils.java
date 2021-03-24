package com.github.hqxqyly.remex.crude.activiti.utils;

import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;

import com.github.hqxqyly.remex.boot.utils.ApplicationContextUtils;
import com.github.hqxqyly.remex.boot.utils.Assist;
import com.github.hqxqyly.remex.crude.activiti.msg.MsgCrudeActivitiEnum;

/**
 * activiti数据工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ActivitiUtils {
	
	/**
	 * 取得TaskService
	 * @return
	 */
	public static TaskService getTaskService() {
		return ApplicationContextUtils.getBean(TaskService.class);
	}
	
	/**
	 * 取得IdentityService
	 * @return
	 */
	public static IdentityService getIdentityService() {
		return ApplicationContextUtils.getBean(IdentityService.class);
	}

	/**
	 * 根据ID查询task
	 * @param id
	 */
	public static Task findTaskById(String id) {
		Task task = getTaskService().createTaskQuery().taskId(id).singleResult();
		Assist.notNull(task, MsgCrudeActivitiEnum.ACTIVITI_TASK_NOT_EXISTS);
		return task;
	}
	
	/**
	 * 根据ID查询user
	 * @param id
	 * @return
	 */
	public static User findUserById(String id) {
		User user = getIdentityService().createUserQuery().userId(id).singleResult();
		Assist.notNull(user, MsgCrudeActivitiEnum.ACTIVITI_USER_NOT_EXISTS);
		return user;
	}
	
	/**
	 * 根据流程实例ID查询task
	 * @param processInstanceId
	 * @return
	 */
	public static Task findTaskByProcessInstanceId(String processInstanceId) {
		Task task = getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();
		Assist.notNull(task, MsgCrudeActivitiEnum.ACTIVITI_TASK_NOT_EXISTS);
		return task;
	}
}
