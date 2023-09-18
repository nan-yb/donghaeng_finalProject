package kr.or.ddit.cor.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PackageVO;
import kr.or.ddit.vo.Package_Reserve_ListVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.SurveyVO;

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
public interface IManageDAO {
	public long selectTotalRecord(PagingInfoVO<PackageVO> pagingVO);

	public List<PackageVO> selectPackageList(PagingInfoVO<PackageVO> pagingVO);
	
	public long selectTotalReserveRecord(PagingInfoVO<Package_Reserve_ListVO> pagingVO);

	public List<Package_Reserve_ListVO> selectPackageReserveList(PagingInfoVO<Package_Reserve_ListVO> pagingVO);
	
	
}























