package com.example.cargodelivery.controller.SchedulerUtil;

import com.example.cargodelivery.model.dao.OrderDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.SQLException;

public class SchedulerJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        OrderDao orderDao = new OrderDao();
        orderDao.checkStatusDeliver();
    }
}
