package mr.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mr.web.model.MemberDTO;

// MemberMapper.xml의 namespace의 이름와 interface의 이름이 같으면
// 자동으로 연결된다.(스프링이 연결시켜줌)
// @Mapper 생략가능하다.

//@Mapper // MyBatis(SqlSessionFactory + SqlSession)
public interface MemberMapper {
	@Select("select * from member")
	public List<MemberDTO> memberList();
	
	@Insert("insert into member(id, pw, name, age, email, tel) \r\n" + 
			"      	values(#{id}, #{pw}, #{name}, #{age}, #{email}, #{tel})")
	public int memberInsert(MemberDTO dto);
	
	@Delete("delete from member where num=#{num}")
	public int memberDelete(int num);
	@Select("select * from member where num=#{num}")
	public MemberDTO memberInfo(int num);
	@Update("update member set age=#{age}, email=#{email}, tel=#{tel} where num=#{num}")
	public int memberUpdate(MemberDTO dto);
}
