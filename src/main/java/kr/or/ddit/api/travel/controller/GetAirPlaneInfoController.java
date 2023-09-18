package kr.or.ddit.api.travel.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import kr.or.ddit.common.product.service.ITrafficService;
import kr.or.ddit.vo.AirPlainVO;

@Controller
public class GetAirPlaneInfoController {
	
	@Inject
	ITrafficService service;
	
	@ResponseBody
	@RequestMapping(value = "traffic/airArrive.do", method = RequestMethod.POST , produces ="application/json;charset=UTF-8")
	public String doAirLineArrGet(
			String searchType           , 
			String airline              ,
			String domestic_round_way   ,
			String domestic_start       ,
			String domestic_end         ,
			String domestic_startDate   ,
			String domestic_endDate     ,
			String domestic_ea_adult    ,
			String domestic_ea_children ,
			String domestic_ea_infant   ,
			String domestic_seat     	,
			String domestic_airline
			
	) throws IOException {
		
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("searchType", searchType);
		params.add("airline", airline);
		params.add("domestic_round_way", domestic_round_way);
		params.add("domestic_start", domestic_start);
		params.add("domestic_end", domestic_end);
		params.add("domestic_startDate", domestic_startDate);
		params.add("domestic_endDate", domestic_endDate);
		params.add("domestic_ea_adult", domestic_ea_adult);
		params.add("domestic_ea_children", domestic_ea_children);
		params.add("domestic_ea_infant", domestic_ea_infant);
		params.add("domestic_seat", domestic_seat);
		params.add("ddomestic_airline", domestic_airline);
		
		String url = "https://www.airport.co.kr/extra/ticket/webtourDomestic/layOut.do";
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.postForEntity( url, params, String.class );
		return response.getBody().trim();
	}
	
	@ResponseBody
	@RequestMapping(value = "traffic/airDept.do", method = RequestMethod.POST , produces ="application/json;charset=UTF-8")
	public String doAirLineDeptGet(
			String searchType           , 
			String airline              ,
			String domestic_round_way   ,
			String domestic_start       ,
			String domestic_end         ,
			String domestic_startDate   ,
			String domestic_endDate     ,
			String domestic_ea_adult    ,
			String domestic_ea_children ,
			String domestic_ea_infant   ,
			String domestic_seat     	,
			String domestic_airline
			
	) throws IOException {
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("searchType", searchType);
		params.add("airline", airline);
		params.add("domestic_round_way", domestic_round_way);
		params.add("domestic_start", domestic_start);
		params.add("domestic_end", domestic_end);
		params.add("domestic_startDate", domestic_startDate);
		params.add("domestic_endDate", domestic_endDate);
		params.add("domestic_ea_adult", domestic_ea_adult); 
		params.add("domestic_ea_children", domestic_ea_children);
		params.add("domestic_ea_infant", domestic_ea_infant);
		params.add("domestic_seat", domestic_seat);
		params.add("ddomestic_airline", domestic_airline);
		
		String url = "https://www.airport.co.kr/extra/ticket/webtourDomestic/layOut.do";
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> response = template.postForEntity( url, params, String.class );
		return response.getBody().trim();
	}
	

}
