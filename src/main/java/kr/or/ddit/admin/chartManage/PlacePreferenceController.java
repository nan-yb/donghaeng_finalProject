package kr.or.ddit.admin.chartManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//여행지 선호도 조사 기능
//관리자가 회원들이 간 여행 지역을 통계처리하여 보여주는 프로그램

@Controller
public class PlacePreferenceController {
	@RequestMapping("admin/placePreferenceChart.do")
	public String getProcess(){
		return "";
	}
}
