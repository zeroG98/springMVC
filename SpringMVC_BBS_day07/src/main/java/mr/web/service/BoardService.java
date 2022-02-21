package mr.web.service;

import java.util.List;

import mr.web.model.BoardVO;

public interface BoardService {
	public List<BoardVO> getList(BoardVO vo); // 게시판 리스트 가져오기
	public void register(BoardVO board); //등록
	public BoardVO view(int bno, String mode); // 게시글 보기
	public int remove(int bno);// 게시글 삭제
	public int modify(BoardVO board);// 게시글 수정
	
	
	public int selectTotalCnt(BoardVO vo) throws Exception;
	
}
