package kr.or.ddit.signup.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CorporationVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.PersonVO;

@Repository
public interface ISignupDAO {
	/**
	 * 회원 신규 등록
	 * @param member 등록할 회원의 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public int insertMember(MemberVO member);

	
	public int insertPerson(PersonVO person);

	public int insertCorporation(CorporationVO corp);
	
	/**
	 * 페이징을 위한 전체 레코드수 조회
	 * @param pagingVO
	 * @return
	 */
	public long selectTotalRecord(PagingInfoVO pagingVO);
	
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return 존재하지 않는다면, .size()==0
	 */
	public List<MemberVO> selectMemberList(PagingInfoVO pagingVO);
	/**
	 * 회원정보 상세 조회
	 * @param mem_id 조회할 회원의 아이디
	 * @return 존재하지 않는다면, null 반환
	 */
	public MemberVO selectMember(String mem_id);
	
	public PersonVO selectPerson(String person_id);

	/**
	 * 회원정보 상세 조회
	 * @param mem_id 조회할 회원의 아이디
	 * @return 존재하지 않는다면, null 반환
	 */
	public CorporationVO selectCorporation(String cor_id);
	/**
	 * 회원 정보 수정
	 * @param member 수정할 정보를 가진 VO
	 * @return 성공(>0) 실패
	 */
	public int updateMember(MemberVO member);
	/**
	 * 회원 정보 삭제
	 * @param mem_id 삭제할 회원의 아이디
	 * @return 성공(>0) 실패
	 */
	public int deleteMember(String mem_id);
}
