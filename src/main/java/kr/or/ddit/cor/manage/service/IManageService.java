package kr.or.ddit.cor.manage.service;

import java.util.List;

import kr.or.ddit.vo.PackageVO;
import kr.or.ddit.vo.Package_Reserve_ListVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author sem
 * @since 2018. 12. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2018. 12. 4.      작성자명       게시글 관리를 위한 Business Logic Layer
 * Copyright (c) 2018 by DDIT All right reserved
 * </pre>
 */
public interface IManageService {
	public long retrivePackageCount(PagingInfoVO<PackageVO> pagingVO);
	/**
	 * 검색 조건에 맞는 게시글 목록
	 * @param pagingVO 검색 조건과 페이징 속성을 가진 VO
	 * @return 없다면, .size()==0
	 */
	public List<PackageVO> retrivePackageList(PagingInfoVO<PackageVO> pagingVO);
	
	public long retrivePackageReserveCount(PagingInfoVO<Package_Reserve_ListVO> pagingVO);
	
	public List<Package_Reserve_ListVO> retrivePackageReserveList(PagingInfoVO<Package_Reserve_ListVO> pagingVO);
	
	
}


















