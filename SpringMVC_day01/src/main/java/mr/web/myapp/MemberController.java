package mr.web.myapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mr.web.mapper.MemberMapper;
import mr.web.model.MemberDTO;

//public class MemberController implements Controller{
@Controller
public class MemberController {
	// request, response를 인자로 사용할 필요없음. Exception 처리 필요없음
	
	@Autowired //DI(Dependency injection)
//	private MemberDAO dao;
	private MemberMapper memberMapper;
	
	//list
	@RequestMapping("/memberList.do")
	public String memberList(Model model) {
		// 객체바인딩을 할 때 HttpServletRequest request 대신
		// 스프링에서는 Model 클래스를 제공한다.
		
//		MemberDAO dao = new MemberDAO(); // 스프링에서는 new 연산자를 이용해서 객체생성을 하지 않는다.
		List<MemberDTO> memberList = memberMapper.memberList();
		
		// Object Binding
//		request.setAttribute("memberList", memberList);
		model.addAttribute("memberList", memberList);
		
		// View정보
		return "memberList";
	}
	
	// insert
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberDTO dto){
		// 파라미터로 넘어온 값을 스프링에서
		// DTO 통해 자동으로 모아서 만들어줌
		
		int cnt = memberMapper.memberInsert(dto);
		return "redirect:memberList.do";
	}
	
	// join
	@RequestMapping("/memberJoin.do")
	public String memberJoin(){
	
//		return "/WEB-INF/member/join.html";
		return "join";
	}
	
	// delete
	@RequestMapping("/memberDelete.do")
//	public String memberDelete(int num) {
	public String memberDelete(@RequestParam("num") int num) {
		
		int cnt = memberMapper.memberDelete(num);
		return "redirect:/memberList.do";
	}
	
	// info
	@RequestMapping("/memberInfo.do")
	public String memberInfo(int num, Model model) {
		
		MemberDTO dto = memberMapper.memberInfo(num);		
		model.addAttribute("dto", dto);
		
		return "memberInfo"; // View이름만 넘겨줌
	}
	
	//update
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(MemberDTO dto) {
		int cnt = memberMapper.memberUpdate(dto);
			
		return "redirect:/memberList.do";
	}
	
	// @ResponseBody 메소드에 선언되어 있으면
	// 리턴값이 View로 가지 않고, HTTP Response Body에 쓰여지게된다.
	// API가 필요하다. (jackson-databind,jackson-mapper-asl) 
	
	@RequestMapping("/memberAjaxList.do")
	public @ResponseBody List<MemberDTO> memberAjaxList() {		
		List<MemberDTO> memberList = memberMapper.memberList();
		// $.ajax()=> callback함수에 응답을 해야한다.
		
		// 객체를 JSON으로 변환해서 응답을 해야한다.
		
		
		return memberList; // 객체를 리턴
	}
	
	@RequestMapping("/form.do")
	public String form() {
		
		return "uploadForm";
	}
}

