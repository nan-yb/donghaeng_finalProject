package kr.or.ddit.member.mySchedule.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.member.mySchedule.dao.IMyScheduleDAO;
import kr.or.ddit.vo.MyScheduleVO;

/**
 * @author 서신원
 * @since 2019. 1. 14.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 1. 14.      서신원        최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class MyScheduleDAOImpl implements IMyScheduleService {

	@Inject
	IMyScheduleDAO scheduleDAO;
	
	@Override
	public List<MyScheduleVO> retrieveMyScheduleList(String mem_id) {
		return scheduleDAO.selectMyScheduleList(mem_id);
	}

	@Override
	public MyScheduleVO retrieveMySchedule(long myschedule_no) {
		return scheduleDAO.selectMySchedule(myschedule_no);
	}

	@Override
	public ServiceResult createMySchedule(MyScheduleVO myschedule) {
		ServiceResult result = null;
		int rowCnt = scheduleDAO.insertMySchedule(myschedule);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyMySchedule(MyScheduleVO myschedule) {
		ServiceResult result = null;
		int rowCnt = scheduleDAO.updateMySchedule(myschedule);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeMySchedule(long myschedule_no) {
		ServiceResult result = null;
		int rowCnt = scheduleDAO.deleteMySchedule(myschedule_no);
		if (rowCnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}


}
