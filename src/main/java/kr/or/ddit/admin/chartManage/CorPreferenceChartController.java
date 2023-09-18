package kr.or.ddit.admin.chartManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//여행사 선호도 통계 기능
//관리자가 설문조사를 통해 여행사 선호도를 통계로 나타내는 프로그램

@Controller
public class CorPreferenceChartController {
	@RequestMapping("/admin/corPreferenceChart.do")
	public String getProcess(){
		return "";
	}
}
