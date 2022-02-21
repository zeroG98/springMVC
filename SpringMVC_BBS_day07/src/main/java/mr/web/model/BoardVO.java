package mr.web.model;

import java.util.Date;

public class BoardVO {
	private int bid;
	private String subject;
	private String contents;
	private int hit;
	private String writer;
	private Date regDate;
	
	//paging
	private int viewPage = 1;
	private int startIndex = 0;
	private int listCnt = 5;
	private int blockSize = 5;
	
	
	//search
	private String searchType;
	private String keyWord;
	
		
	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getKeyWord() {
		return keyWord;
	}


	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	public int getBlockSize() {
		return blockSize;
	}


	public int getListCnt() {
		return listCnt;
	}


	public int getViewPage() {
		return viewPage;
	}


	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}


	public int getBid() {
		return bid;
	}
	
	
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "BoardVO [bid=" + bid + ", subject=" + subject + ", contents=" + contents + ", hit=" + hit + ", writer="
				+ writer + ", regDate=" + regDate + "]";
	}
	
	
	
}
