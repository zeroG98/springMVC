package mr.web.myapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {
	
	// 스프링에서는 FileUpload시 사용되는 API 제공 :MultipartHttpServletRequest
	// HttpServletRequest는 파라미터값만 받는다.
	
	
	@RequestMapping("/upload.do")
	public String upload(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, Model model) 
			throws IOException {
		
		String UPLOAD_DIR = "file_repo";		
		// 실제 물리적인 경로를 얻어오기                                                                                    //\\(윈도우), /(리눅스)
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		
		Map map = new HashMap();
		//MultipartHttpServletRequest를 이용해서 일반데이터와 첨부파일 데이터를 모두 받을 수 있따.
		
//		String id=multipartHttpServletRequest.getParameter("id");
//		String name=multipartHttpServletRequest.getParameter("name");
//		System.out.println(id + ": "+ name);
		
		Enumeration<String> enu=multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()) {
			String name = enu.nextElement(); // 파라미터의 이름
			String paramValue = multipartRequest.getParameter(name);
			
			System.out.println(name + ":"+paramValue);
			
			map.put(name, paramValue);
		}
		
		// 파일명을 갖고 있는 파라미터 읽어오기
		Iterator<String> iter=multipartRequest.getFileNames();//파일명이 아니라 파라미터명(file1, file2, ....)
		List<String> fileList=new ArrayList<String>();
		while(iter.hasNext()) {
			String fParamName = iter.next();
			System.out.println(fParamName); //file1, file2,....
			
			// 파일정보를 얻어온다.(파일명, 타입, 크기.......)
			MultipartFile mFile=multipartRequest.getFile(fParamName);
			String originName = mFile.getOriginalFilename(); // 실제 업로된 파일명
			System.out.println(originName);
			
			fileList.add(originName);
			
			File file = new File(uploadPath +"\\"+fParamName);
			if(mFile.getSize()!=0) {
				if(!file.exists()) {
					if(file.getParentFile().mkdirs()) { //부모폴더로 이동해서 생성
						file.createNewFile(); //임시로 파일을 생성
					}
				}
				mFile.transferTo(new File(uploadPath+"\\"+originName)); //파일 업로드
			}
		}
		
		map.put("fileList", fileList);
		model.addAttribute("map", map);
		
		return "result"; //result.jsp
	}
	
	@RequestMapping("/download.do")
	public void download(@RequestParam("fileName") String fileName, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String UPLOAD_DIR = "file_repo";		
		// 실제 물리적인 경로를 얻어오기                                                                                    //\\(윈도우), /(리눅스)
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		
		File file = new File(uploadPath+"\\"+fileName);
		
		// 한글 깨짐 방지
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replace("+", " "); // 어떤 브라우저는 파일명의 공백 부분을 + 처리하는 경우가 있음, + ---> " " 
		
		// 다운로드 준비과정: 서버에서 클라이언트에게 다운로드 준비를 시켜놓는 과정
		response.setContentLength((int)file.length());
		response.setContentType("application/x-msdownload;charset=utf-8");
		
		response.setHeader("Content-Disposition", "attachment;fileName="+fileName+";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		// 실제 다운로드 
		FileInputStream fis = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		
		byte[] buffer= new byte[1024];
		while(true) {
			int cnt = fis.read(buffer);
			if(cnt==-1) {
				break;
			}
			out.write(buffer, 0, cnt); //읽어들인만큼 클라이언트에 계속 씀 
		}
		
		fis.close();
		out.close();
	}
}
