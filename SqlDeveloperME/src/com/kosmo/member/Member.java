package com.kosmo.member;

import java.util.ArrayList;

public interface Member {
	/**
	 * 로그인 (mid/mpw) -- (mgubun, mname)  
	 */
	public MemberVO login(MemberVO vo);
	
	/**
	 * 회원목록
	 * @return
	 */
	public ArrayList<MemberVO> memberList();
	
	/**
	 * 회원정보 수정 (mpw , mname, mseq)
	 */
	public void memberUpdate(MemberVO vo);
	
	/**
	 * 회원정보 삭제
	 */
	public void memberDelete(int mseq);
	
	/**
	 * 회원가입 (mid, mpw, mname)
	 */
	public int memberInsert(MemberVO vo);
	
	
	
	
	
}
