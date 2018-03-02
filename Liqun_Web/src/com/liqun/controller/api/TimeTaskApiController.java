package com.liqun.controller.api;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.bson.Document;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.javafaker.Faker;
import com.liqun.aop.DbLoggable;
import com.liqun.entity.IBilldel;
import com.liqun.entity.IBillmain;
import com.liqun.entity.JobEntity;
import com.liqun.entity.SysLogInfo;
import com.liqun.entity.SysOption;
import com.liqun.service.BillHandingServiceImpl;
import com.liqun.service.QuartzService;
import com.liqun.service.SysLogInfoService;
import com.liqun.util.DataGridPage;
import com.liqun.util.ExcelUtil;
import com.liqun.util.StudentVO;

//页面跳转类
@Controller
@RequestMapping("/api")
public class TimeTaskApiController {
		private static final Logger logger = LoggerFactory.getLogger(TimeTaskApiController.class);
		@Autowired
		private Scheduler quartzScheduler;
		
		@Autowired
		private QuartzService quartzService;
		@GetMapping("/listJob")
		@ResponseBody
		public Object billlist(@RequestParam int page, @RequestParam int rows) throws SchedulerException {
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.billlist START");
			List<JobEntity> jobInfos = this.getSchedulerJobInfo();
			// 数据库分页 当前页需要减一
			Page<JobEntity> result=new PageImpl<>(jobInfos, PageRequest.of(page - 1, rows), jobInfos.size());
			if (logger.isInfoEnabled())
				logger.info("BillHandingApiController.billlist END");
			return DataGridPage.pageToGrid(result);
		}
 
		private List<JobEntity> getSchedulerJobInfo() throws SchedulerException {
			List<JobEntity> jobInfos = new ArrayList<JobEntity>();
			List<String> triggerGroupNames = quartzScheduler.getTriggerGroupNames();
			for (String triggerGroupName : triggerGroupNames) {
				Set<TriggerKey> triggerKeySet = quartzScheduler
						.getTriggerKeys(GroupMatcher
								.triggerGroupEquals(triggerGroupName));
				for (TriggerKey triggerKey : triggerKeySet) {
					Trigger t = quartzScheduler.getTrigger(triggerKey);
					if (t instanceof CronTrigger) {
						CronTrigger trigger = (CronTrigger) t;
						JobKey jobKey = trigger.getJobKey();
						JobDetail jd = quartzScheduler.getJobDetail(jobKey);
						JobEntity jobInfo = new JobEntity();
						jobInfo.setJobName(jobKey.getName());
						jobInfo.setJobGroup(jobKey.getGroup());
						jobInfo.setTriggerName(triggerKey.getName());
						jobInfo.setTriggerGroupName(triggerKey.getGroup());
						jobInfo.setCronExpr(trigger.getCronExpression());
						jobInfo.setNextFireTime(trigger.getNextFireTime());
						jobInfo.setPreviousFireTime(trigger.getPreviousFireTime());
						jobInfo.setStartTime(trigger.getStartTime());
						jobInfo.setEndTime(trigger.getEndTime());
						jobInfo.setJobClass(jd.getJobClass().getCanonicalName());
						// jobInfo.setDuration(Long.parseLong(jd.getDescription()));
						Trigger.TriggerState triggerState = quartzScheduler
								.getTriggerState(trigger.getKey());
						jobInfo.setJobStatus(triggerState.toString());// NONE无,
																		// NORMAL正常,
																		// PAUSED暂停,
																		// COMPLETE完全,
																		// ERROR错误,
																		// BLOCKED阻塞
						JobDataMap map = quartzScheduler.getJobDetail(jobKey)
								.getJobDataMap();
						if (null != map&&map.size() != 0) {
							jobInfo.setCount(Integer.parseInt((String) map
									.get("count")));
							jobInfo.setJobDataMap(map);
						} else {
							jobInfo.setJobDataMap(new JobDataMap());
						}
						jobInfos.add(jobInfo);
					}
				}
			}
			return jobInfos;
		}
		 
}
