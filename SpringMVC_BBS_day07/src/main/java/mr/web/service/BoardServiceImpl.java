package mr.web.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.web.mapper.BoardMapper;
import mr.web.model.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper; //DI(의존성 주입)	

	@Override
	public List<BoardVO> getList(BoardVO vo) {
		
		System.out.println(vo.getKeyWord());
		System.out.println(vo.getSearchType());
		return mapper.getList(vo);
	}

	@Override
	public void register(BoardVO board) {
		mapper.insert(board);
	}

	@Override
	public BoardVO view(int bid, String mode){
		if(mode.equals("view")) {
			mapper.hit(bid);
		}
				
		return mapper.read(bid);
	}

	@Override
	public int remove(int bid) {
		return mapper.delete(bid);
	}

	@Override
	public int modify(BoardVO board) {
		
		return mapper.update(board);
	}
	
	@Override
	public int selectTotalCnt(BoardVO vo) throws Exception {
		
		return mapper.selectTotalCnt(vo);
	}

}
