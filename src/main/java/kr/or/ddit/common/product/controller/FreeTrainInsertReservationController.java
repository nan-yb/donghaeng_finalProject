package kr.or.ddit.common.product.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.product.service.ITrafficService;
import kr.or.ddit.vo.AirPlainVO;
import kr.or.ddit.vo.TrainVO;

@Controller
public class FreeTrainInsertReservationController{
	
	@Inject
	ITrafficService service;
	
	@RequestMapping(value="/traffic/trainInsert.do", method=RequestMethod.POST)
	public String postProcess(
			String depPlaceId,
			String arrPlaceId,
			String depPlandTime,
			String person,
			String arrplandtime,
			String depplandtime,
			String adultcharge,
			String traingradename,
			String mem_id
			) {
		
		TrainVO train = new TrainVO();
		train.setAdultcharge(adultcharge);
		train.setArrPlaceId(arrPlaceId);
		train.setArrplandtime(arrplandtime);
		train.setDepPlaceId(depPlaceId);
		train.setDepPlandTime(depPlandTime);
		train.setDepPlandTime2(depplandtime);
		train.setMem_id(mem_id);
		train.setPerson(person);
		train.setTraingradename(traingradename);
		String view = null;
		ServiceResult result = service.createTrain(train);
		
		if(result == ServiceResult.OK){
			System.out.println("인서트 성공");
			view = "redirect:/postboard/postboardRetrieve.do";
		}else{
			System.out.println("실패");
			view = "common/board/postboard/postboardForm";
		}
		return view;
	}

	
	@RequestMapping(value = "traffic/airArriveReservation.do", method = RequestMethod.POST , produces ="application/json;charset=UTF-8")
	public String doAirLineResvArrGet(
			AirPlainVO airPlainVO
			) throws IOException {
		
		airPlainVO.setAirline_routetype("ARRIVE");
		ServiceResult result =  service.createAir(airPlainVO);
		
		switch (result) {
		case OK:
			System.out.println("인서트 성공");
			break;

		default:
			System.out.println("실패");
			
			break;
		}		
		
		return null;
	}
	
	@RequestMapping(value = "traffic/airDeptReservation.do", method = RequestMethod.POST , produces ="application/json;charset=UTF-8")
	public String doAirLineResvDeptGet(
			AirPlainVO airPlainVO
		) throws IOException {
		
		airPlainVO.setAirline_routetype("DEPT");
		ServiceResult result =  service.createAir(airPlainVO);
		
		switch (result) {
		case OK:
			System.out.println("인서트 성공");
			break;

		default:
			System.out.println("실패");
			break;
		}	
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
















