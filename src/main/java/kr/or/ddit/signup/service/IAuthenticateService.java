package kr.or.ddit.signup.service;

import kr.or.ddit.vo.PersonVO;

public interface IAuthenticateService {
	
	public Object authenticate(PersonVO person);
}
