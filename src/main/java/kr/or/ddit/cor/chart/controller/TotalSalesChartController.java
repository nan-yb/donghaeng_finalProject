package kr.or.ddit.cor.chart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 총 매충 통계 기능
// 자신의 업체의 총 매출을 통계낼 수 있는 프로그램

@Controller
public class TotalSalesChartController {
	@RequestMapping("/cor/totalSalesChart.do")
	public String getProcess(){
		return "corporation/totalSalesChartView";
	}
}
