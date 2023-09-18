package kr.or.ddit.common.product.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.common.product.service.ITrafficService;
import kr.or.ddit.vo.AirPlainVO;

@Controller
public class FreeAirPalinInsertReservationController {
	
	@Inject
	ITrafficService service;
	
	
	
	
}
