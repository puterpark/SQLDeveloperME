package com.kosmo.member;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.kosmo.db.DBConnect;

public class MemberCallTest {

	public static void memberPrint() {
		MemberCrud m = new MemberCrud();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		list = m.memberList();
		
		MemberVO vo = new MemberVO();
		
		System.out.println();
		System.out.println("번호\t" + "아이디   \t" + "비밀번호\t" + "이름\t\t" + "회원구분\t" + "가입일");
		
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			System.out.print(vo.getMseq() + "\t");
			System.out.print(vo.getMid() + "     \t");
			System.out.print(vo.getMpw() + "\t\t");
			System.out.print(vo.getMname() + "\t\t");
			System.out.print(vo.getMgubun() + "\t\t");
			System.out.println(vo.getRegdate());
		}
		System.out.println();
	}
	
	
	
	public static void main(String[] args) {

		
		DBConnect c = new DBConnect();
		Connection conn = c.dbConn();
		
		if(conn == null) {
			System.out.println("연결 실패");
		} else {
			System.out.println("연결 성공");
			System.out.println();
			
			MemberCrud m = new MemberCrud();
			MemberVO vo = new MemberVO();
				
			
//			memberPrint();
			
			/*<!-- 회원 추가 --!>*/
			
			/*vo.setMid("maem_e");
			vo.setMpw("0217");
			vo.setMname("송현지");
			
			int res = m.memberInsert(vo);
			System.out.println(res + "건 추가 완료");*/
			
//			if(res <= 0)
//				System.out.print("회원추가 실패");
//			else
//				System.out.println("회원 추가 완료");
			
			/*vo.setMid("test");
			vo.setMpw("0707");
			vo.setMname("더미맨");
			
			int res = m.memberInsert(vo);
			System.out.println(res + "건 추가 완료");*/
			
			
			/*<!-- 회원 삭제 --!>*/
			
//			m.memberDelete(3);
//			System.out.println("해당 회원 삭제 완료");
			
			
			
			/*<!-- 회원 수정 --!>*/
			
//			vo.setMpw("1021");
//			vo.setMname("강태길");
//			vo.setMseq(4);
//			
//			m.memberUpdate(vo);
//			System.out.println("해당 회원 수정 완료");
			
			/*<!-- 회원 로그인 --!>*/
			
			
//			vo.setMid("puterpark");
//			vo.setMpw("r2d2");
//			m.login(vo);
//			if(vo.getMname() == null) {
//				System.out.println("로그인 실패! \n아이디와 비밀번호를 확인해주세요.");
//			} else {
//				System.out.println("회원 구분 : " + vo.getMgubun());
//				System.out.println(vo.getMname() + "님 이럇샤이마세!\n");
//			}
			
			while(true) {
			
				System.out.println("-----------------");
				System.out.println("회원관리");
				System.out.println("-----------------");
				System.out.println("1. 로그인");
				System.out.println("2. 회원목록");
				System.out.println("3. 회원추가");
				System.out.println("4. 회원수정");
				System.out.println("5. 회원삭제");
				System.out.println("0. 종료");
				System.out.println("-----------------");
				System.out.println();

				Scanner s = new Scanner(System.in);

				int num = s.nextInt();

				
				if(num == 0) {
					System.out.println("종료합니다.");
					System.out.println();
					break;
				}
				
				switch(num) {
				case 1:
					System.out.println("회원 로그인입니다.");
					Scanner id = new Scanner(System.in);
					Scanner pw = new Scanner(System.in);

					System.out.print("아이디 : ");
					String mid = id.nextLine();

					System.out.print("비밀번호 : ");
					String mpw = pw.nextLine();

					vo.setMid(mid);
					vo.setMpw(mpw);
					m.login(vo);
					
					System.out.println();
					
					if(vo.getMname() == null) {
						System.out.println("로그인 실패! \n아이디와 비밀번호를 확인해주세요.");
					} else {
						System.out.println("회원 구분 : " + vo.getMgubun());
						System.out.println(vo.getMname() + "님 이럇샤이마세!\n");
					}
					break;

				case 2:
					System.out.println("회원 목록을 출력합니다.");
					memberPrint();
					break;

				case 3:
					System.out.println("회원 추가를 합니다.");
					Scanner idInsert = new Scanner(System.in);
					Scanner pwInsert = new Scanner(System.in);
					Scanner nmInsert = new Scanner(System.in);

					System.out.print("아이디 : ");
					String iid = idInsert.nextLine();

					System.out.print("비밀번호 : ");
					String ipw = pwInsert.nextLine();

					System.out.print("이름 : ");
					String inm = nmInsert.nextLine();

					vo.setMid(iid);
					vo.setMpw(ipw);
					vo.setMname(inm);

					int res = m.memberInsert(vo);
					
					if(res <= 0)
						System.out.print("회원 추가 실패");
					else
						System.out.println("회원 추가 완료");
					System.out.println();
					break;

				case 4:
					System.out.println("회원 정보를 수정합니다.");
					Scanner seqUpdate = new Scanner(System.in);
					Scanner pwUpdate = new Scanner(System.in);
					Scanner nmUpdate = new Scanner(System.in);

					System.out.print("수정할 시퀀스 번호: ");
					int useq = seqUpdate.nextInt();

					System.out.print("수정할 비밀번호 : ");
					String upw = pwUpdate.nextLine();

					System.out.print("수정할 이름 : ");
					String unm = nmUpdate.nextLine();

					vo.setMseq(useq);
					vo.setMpw(upw);
					vo.setMname(unm);

					m.memberUpdate(vo);
					System.out.println("해당 회원 수정 완료");
					System.out.println();
					break;

				case 5:			
					System.out.println("회원 정보를 삭제합니다.");

					Scanner seqDelete = new Scanner(System.in);

					System.out.print("삭제할 시퀀스 번호: ");
					int dseq = seqDelete.nextInt();

					m.memberDelete(dseq);
					System.out.println("해당 회원 삭제 완료");
					System.out.println();
					break;
				}
			}
		}
	}

}
