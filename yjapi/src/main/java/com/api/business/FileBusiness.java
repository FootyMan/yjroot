package com.api.business;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
 
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.api.response.FileUploadResponse;
import com.api.response.baseResponse;
import com.api.utils.ResultEnum;
import com.myErp.impl.UserImgServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserImg;
import com.myErp.utils.StringUtils;
import com.myErp.utils.SystemConfig;

@Service("FileBusiness")
public class FileBusiness {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserImgServiceImpl userImgServiceImpl;
	private String uploadPath = SystemConfig.imguserpath;
	File tempPathFile;


	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public baseResponse<FileUploadResponse> FileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		baseResponse<FileUploadResponse> fileResponse = new baseResponse<FileUploadResponse>();
		FileUploadResponse fileList = new FileUploadResponse();
		// 返回加前缀的图片数组
		List<String> imgResponse = new ArrayList<String>();
		List<String> imgStr = new ArrayList<String>();
		// 用户ID
		int userId = request.getParameter("userId") == null ? 0 : Integer.parseInt(request.getParameter("userId"));
		if (userId <= 0) {
			fileResponse.setCode(ResultEnum.ServiceErrorCode);
			fileResponse.setMsg("用户ID为空");
			return fileResponse;
		}
		// 1是头像 2是个人图库
		String imageType = request.getParameter("imageType");
		if (StringUtils.isEmpty(imageType)) {
			fileResponse.setCode(ResultEnum.ServiceErrorCode);
			fileResponse.setMsg("图片类型为空");
			return fileResponse;
		}
		// 以年与日存储目录路径
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		System.out.println(uploadPath);

		String currentDate = sdf.format(date);
		uploadPath = uploadPath + currentDate;
		System.out.println(uploadPath);
		File dirFile = new File(uploadPath);
		// 没有则创建
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		factory.setRepository(tempPathFile);// 设置缓冲区目录

		List<String> imageStr = new ArrayList<String>();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
		List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
		Iterator<FileItem> i = items.iterator();
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			String fileName = fi.getName();
			if (fileName != null) {
				SimpleDateFormat timeFile = new SimpleDateFormat("YYYYMMddHHmmssSSS");
				Date Timedate = new Date();
				String newFileName = timeFile.format(Timedate) + ".jpg";

				// File fullFile = new File(new String(fi.getName().getBytes(),
				// "utf-8")); // 解决文件名乱码问题

				File savedFile = new File(uploadPath, newFileName);
				fi.write(savedFile);
				imageStr.add(newFileName);
				imgStr.add(currentDate + "/" + newFileName);
			}
		}

		if (imageType.equals("1") && imgStr.size() > 0)// 1是头像
		{
			User user = new User();
			user.setUserId(userId);
			user.setHeadImage(imgStr.get(0));
			userServiceImpl.updateUser(user);
			imgResponse.add(SystemConfig.Imgurl + imgStr.get(0));
			// 更新user表HandImage
		} else {
			// 添加个人图片 userImage
			List<UserImg> imgs = new ArrayList<UserImg>();
			for (int j = 0; j < imgStr.size(); j++) {
				UserImg img = new UserImg();
				String url = imgStr.get(j).toString();
				img.setUserId(userId);
				img.setImageType(0);
				img.setImagePath(url);
				img.setImageSort(j);
				imgs.add(img);
				imgResponse.add(SystemConfig.Imgurl + url);
			}
			userImgServiceImpl.insertUserImg(imgs);
		}
		fileList.setImgList(imgResponse);
		fileResponse.setData(fileList);
		return fileResponse;
	}
 
}
