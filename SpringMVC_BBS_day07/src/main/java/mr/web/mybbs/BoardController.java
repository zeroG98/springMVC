package mr.web.mybbs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mr.web.model.BoardVO;
import mr.web.service.BoardService;

@Controller
public class BoardController {

	// @Autowired는 타입으로 Bean을 조회
	// 해당타입이 여러개인 경우에는 이름으로 조회
	// @Qualifier("A") 구분자 역할
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/list.do")
	public String list(BoardVO vo, Model model) throws Exception{
		
		// 전체 게시물의 개수
		int totalCnt = service.selectTotalCnt(vo);
		System.out.println("게시물 개수:"+totalCnt);
		
		// 한페이지당 게시물 개수
		int listCnt = vo.getListCnt();
		
		// 사용자가 클릭한 페이지, 첫페이지
		int viewPage = vo.getViewPage();
		System.out.println("vp : "+viewPage);
		
		// 전체 페이지 개수
		int totalPage = (int) Math.ceil((double)totalCnt/listCnt);
		
		System.out.println("전체페이지 수 :" + totalPage);
		
		if(viewPage > totalPage || viewPage < 1) {
			viewPage = 1;
		}
	
		int startIndex=(viewPage -1)*listCnt;
		System.out.println("startIndex : "+ startIndex);
		
		
		
		vo.setStartIndex(startIndex);
		
		// 행번호를 역순으로 시작하기 Row 계산
		
		// totalCnt 104 (11page)
		// 1페이지 -> 104 ~ 95
		// 2페이지 -> 94 ~ 85
		// 3페이지 -> 84 ~ 75
		// 4페이지 -> 74 ~ 65.....
		
		// 1페이지의 startRow = totalCnt - 0;
		// 2페이지의 startRow = totalCnt - 10;
		// 3페이지의 startRow = totalCnt - 20;
		// 4페이지의 startRow = totalCnt - 30;
		
		int startRowNo = totalCnt - (viewPage - 1)*listCnt;
		model.addAttribute("rowNumber", startRowNo);
		
		
		///////// page + nation //////////
		// 한블럭에 보여줄 페이지버튼
		int blockSize=vo.getBlockSize();
		
		// 현재 페이지의 블럭위치
		int blockNum = (viewPage-1)/blockSize;
		
		// 블럭의 시작값
		int blockStart = (blockSize*blockNum)+1;
		// 블럭의 마지막값
		int blockEnd = blockStart + (blockSize-1);
		if(blockEnd > totalPage) {
			blockEnd = totalPage;
		}
		
		
		int prevPage = blockStart - 1;
		int nextPage = blockEnd + 1;
		
		if(nextPage > totalPage) {
			nextPage = totalPage;
		}
		
		model.addAttribute("blockStart", blockStart);
		model.addAttribute("blockEnd", blockEnd);
		
		model.addAttribute("viewPage", viewPage);
		model.addAttribute("prevPage", prevPage);
		model.addAttribute("nextPage", nextPage);
		
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("totalPage", totalPage);
		
		
		List<BoardVO> list = service.getList(vo);
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		
		System.out.println("keyWord : "+vo.getKeyWord());
		System.out.println("searchType: "+vo.getSearchType());
		
		return "boardList"; //boardList.jsp
	}
	
//	@RequestMapping("/register.do")
	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	public String registerForm() {
	
		return "register"; // 글쓰기 화면
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public String register(BoardVO board) {
//	public void register(BoardVO board) {
//		for(int i=1; i<=100; i++) {
//			BoardVO vo = new BoardVO();
//			vo.setSubject(i + "번째 제목 입니다....");
//			vo.setContents(i + "번째 내용 입니다....");
//			vo.setWriter("아무개0"+(i%10));
//			
//			service.register(vo);
//		}
		service.register(board);
		return "redirect:/list.do"; // 글등록 
	}
	
	
	@RequestMapping("/view.do")
	public String view(@RequestParam("bid") int bid, int viewPage, int totalCnt, String keyWord, String searchType, Model model) {
		BoardVO board = service.view(bid, "view");
//		BoardVO board = service.view(bid);
		model.addAttribute("board", board);
		model.addAttribute("viewPage", viewPage);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("searchType", searchType);
		return "view";
	}
	
	
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String modifyForm(int bid, int viewPage, String keyWord, String searchType, int totalCnt, Model model) {
		BoardVO board = service.view(bid, "modify");
		
		model.addAttribute("board", board);
		model.addAttribute("viewPage", viewPage);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("searchType", searchType);
		model.addAttribute("totalCnt", totalCnt);
		return "modify"; // modify.jsp
	}
	
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modify(BoardVO board, RedirectAttributes rttr, int totalCnt, Model model) {
		service.modify(board);
//		int viewPage = board.getViewPage();
//		model.addAttribute("viewPage", viewPage);
		rttr.addAttribute("viewPage", board.getViewPage());
		rttr.addAttribute("keyWord", board.getKeyWord());
		rttr.addAttribute("searchType", board.getSearchType());
		rttr.addAttribute("totalCnt", totalCnt);
		
		
		return "redirect:/list.do";
	}
	
	
	@RequestMapping("/remove.do")
	public String remove(int bid, int viewPage, String keyWord, String searchType, int totalCnt, RedirectAttributes rttr) {
		service.remove(bid);
		
		rttr.addAttribute("viewPage",viewPage);
		rttr.addAttribute("keyWord",keyWord);
		rttr.addAttribute("searchType",searchType);
		rttr.addAttribute("totalCnt",totalCnt);
		return "redirect:/list.do"; 
	}
	
}
