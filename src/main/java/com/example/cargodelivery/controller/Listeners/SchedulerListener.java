package com.example.cargodelivery.controller.Listeners;

import com.example.cargodelivery.controller.SchedulerUtil.SchedulerJob;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.ScheduledExecutorService;

@WebListener
public class SchedulerListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    private ScheduledExecutorService scheduler;

    public SchedulerListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JobDetail job = JobBuilder.newJob(SchedulerJob.class)
                .withIdentity("anyJobName", "group1").build();
        try {
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("anyTriggerName", "group1")
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                    .build();

            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
