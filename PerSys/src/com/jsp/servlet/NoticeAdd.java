package com.jsp.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lah.toolBean.PoolDB;




public class NoticeAdd extends HttpServlet {

    public NoticeAdd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		PoolDB db = new PoolDB();
		
		String adjunctname = "";
		String fileDir = request.getRealPath("upload/");//指定上传文件的保存地址
		String message = "文件上传未成功";
		String address = "";
		String filepath = "";
		String filename = "";
		String title = "";
		String content = "";
		String no = "111";
		
		if(ServletFileUpload.isMultipartContent(request)){//判断是否是multipart/form-data类
			//创建上传所需的两个对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//创建容器来接受解析的内容
	        List<FileItem> items = null;
	        
	        int size = 100*1024*1024;
	        
	        //将上传文件信息放入容器中
	        try {
				items =upload.parseRequest(request);
			} catch (FileUploadException e) {
				System.err.print(e.getMessage());
			}
	        
	        //遍历容器，处理解析的内容，封装两个方法，一个处理普通表单域，一个处理文件的表单域
	        for(FileItem item : items){
	        	if(!item.isFormField()){//忽略不是上传文件的表单域
	        		String name = item.getName();
	        		if(item.getSize()>size){
	        			message = "您上传的文件太大，请选择不超过100M的文件";
	        			break;
	        		}
	        		//获取上传文件大小
	        		String adjunctsize = new Long(item.getSize()).toString();
	        		
	        		//如果上传文件为空
	        		if((name == null) || (name.equals(""))&&(adjunctsize.equals("0"))){
	        			continue;//跳过此次循环
	        		}
	        		
	        		adjunctname = name.substring(name.lastIndexOf("\\")+1,name.length());
	        		filepath = "\\"+fileDir.substring(fileDir.lastIndexOf("PerSys"),fileDir.length())+adjunctname;
	        		filename = new String(adjunctname);
	        		address = fileDir + "\\" +adjunctname;
	        		File saveFile = new File(fileDir);
	        		if(!saveFile.exists()){
	        			saveFile.mkdirs();
	        		}
	        		File uploadFile = new File(address);
	        		
	        		try {
						item.write(uploadFile);
						message = "上传文件成功";
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
	        	}
	        	else{
	        		String name = item.getFieldName();
	        		if(name.equals("title")){
	        			title = item.getString("UTF-8");
	        		}else if(name.equals("content")){
	        			content = item.getString("UTF-8");
	        		}
	                  
	        	}
	        }
	        HttpSession hs = request.getSession();
	        no = (String)hs.getAttribute("no");
	        Date date = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String noticetime = dateFormat.format(date);
	        
	        String sql = "insert into notice(title,content,filename,filepath,no,noticetime) values('"+title+"','"+content+"','"+filename+"','"+filepath+"','"+no+"','"+noticetime+"')";
	        System.err.println(sql);
	        int jud = db.executeUpdate(sql);
	        if(jud == 1){
	        	out.print("<html>");
				out.print("<body>");
				out.print("<h1>公告发布成功</h1>");
				out.print("<h2>"+message+"</h2>");
				out.print("<h2>no:"+no+"</h2>");
				out.print("<h2>title:"+title+"</h2>");
				out.print("<h2>content:"+content+"</h2>");
				out.print("<h2>filename:"+filename+"</h2>");
				out.print("<h2>filepath:"+filepath+"</h2>");
				out.print("<a href='/PerSys/admin/websites/notice.jsp'><h1>点击返回</h1></a>");
				out.print("</body>");
				out.print("</html>");
	        }
	        else{
	        	out.print("<html>");
				out.print("<body>");
				out.print("<h1>公告发布失败</h1>");
				out.print("<h2>"+message+"</h2>");
				out.print("<h2>no:"+no+"</h2>");
				out.print("<h2>title:"+title+"</h2>");
				out.print("<h2>content:"+content+"</h2>");
				out.print("<h2>filename:"+filename+"</h2>");
				out.print("<h2>filepath:"+filepath+"</h2>");
				out.print("<a href='/PerSys/admin/websites/notice.jsp'><h1>点击返回</h1></a>");
				out.print("</body>");
				out.print("</html>");
	        }
	        
		}

		
	}

}
