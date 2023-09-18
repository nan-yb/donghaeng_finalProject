package kr.or.ddit.cor.packageProduct.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.SurveyVO;
import kr.or.ddit.vo.Survey_EvaluationVO;
import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.CarVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.PackageVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

/**
 * @author sem
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       게시글 관리를 위한 Persistence Layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IPackageDAO {
	public List<CategoryVO> selectCategory();
	public List<CarVO> selectCar(String mem_id);
	public int createPackage(PackageVO packageVO);
}























