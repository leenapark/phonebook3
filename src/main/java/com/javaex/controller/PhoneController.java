package com.javaex.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {

	// 메소드 단위로 기능을 정의하는 spring
	
	// 필드
	// 생성자
	// 메소드 g/s
	
	/******** 메소드 일반*********/
	// 메소드 마다 기능 1개씩 --> 기능 마다 url 부여

	
		// 리스트
		@RequestMapping(value="/list", method= {RequestMethod.POST, RequestMethod.GET})
		public String list(Model model) {	// aaddAttribute 사용 시
			// 확인용
			System.out.println("list");
			
			//dao 를 통해 리스트를 가져온다
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getList();
			
			System.out.println(personList.toString());
			
			//model --> data 를 보내는 방법 --> 담아놓으면 된다
			model.addAttribute("pList", personList);
			
			
			return "/WEB-INF/views/list.jsp";
		}
	
		// 등록폼
		@RequestMapping(value="/writeForm", method= {RequestMethod.POST, RequestMethod.GET})
		public String writeForm() {
			System.out.println("writeForm");
			
			return "/WEB-INF/views/writeForm.jsp";
		}
		
		// http://localhost:8088/phonebook3/phone/write?name=김종국&hp=010-9999-9999&company=02-9999-9999
		// 등록
		@RequestMapping(value="write", method={RequestMethod.GET, RequestMethod.POST})
		public String write(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company) {
			System.out.println("write");
			
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo.toString());
			
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.phoneInsert(personVo);
			
			return "redirect:/phone/list";
		}
		// 수정폼 --> modifyForm
		@RequestMapping(value="modifyForm", method={RequestMethod.GET, RequestMethod.POST})
		public String modifyForm(@RequestParam() String id, Model model) {
			System.out.println("modifyForm");
			// 파라미터 값 확인하기
			System.out.println(id);
			int personId = Integer.parseInt(id);
			
			PhoneDao phoneDao = new PhoneDao();
			
			PersonVo personList = phoneDao.getPerson(personId);
			System.out.println(personList.toString());
			
			model.addAttribute("pList", personList);
			
			return "/WEB-INF/views/updateForm.jsp";
		}
		
		// 수정 --> modify
		
		@RequestMapping(value="modify", method={RequestMethod.GET, RequestMethod.POST})
		public String modify(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company, @RequestParam("id") String id) {
			System.out.println("modify");
			
			// 파라미터 값 확인
			System.out.println(name + ", " + hp + ", " + company + ", " + id);
			
			int personId = Integer.parseInt(id);
			
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.getUpdate(personVo);
						
			return "redirect:/phone/list";
		}
		// 삭제 --> delete
		@RequestMapping(value="delete", method={RequestMethod.GET, RequestMethod.POST})
		public String delete(@RequestParam("id") String id) {
			System.out.println("delete");
			System.out.println(id);
			
			int personId = Integer.parseInt(id);
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.getDelete(personId);
			
			return "redirect:/phone/list";
		}
		
}
