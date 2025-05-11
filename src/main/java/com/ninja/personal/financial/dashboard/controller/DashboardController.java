package com.ninja.personal.financial.dashboard.controller;


import com.ninja.personal.financial.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController
{
    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public Map<String, Object> getDashboardSummary() {
        return dashboardService.getDashboardSummary();
    }

}
