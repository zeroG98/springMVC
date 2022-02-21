package mr.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import mr.web.model.BoardVO;

// persistence Layer(Tier)

public interface BoardMapper {
//	@Select("select * from tbl_board order by bid desc limit #{startIndex}, #{listCnt}")
	public List<BoardVO> getList(BoardVO vo); // 게시판 리스트 가져오기
	public BoardVO read(int bid); // 글보기
	public void insert(BoardVO board); //등록
	public BoardVO view(int bno); // 게시글 보기
	public int delete(int bno);// 게시글 삭제
	public int update(BoardVO board);// 게시글 수정
	
	// 조회수 증가
	public void hit(int bid);
	
	// 전체게시물 개수
	public int selectTotalCnt(BoardVO vo);
	
	
	
}
