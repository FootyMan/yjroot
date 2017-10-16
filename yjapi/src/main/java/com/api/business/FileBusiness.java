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
import com.api.response.UserImgResponse;
import com.api.response.BaseResponse;
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
	@Autowired
	private BusinessUtils businessUtils;

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public BaseResponse<FileUploadResponse> FileUpload(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BaseResponse<FileUploadResponse> fileResponse = new BaseResponse<FileUploadResponse>();

		File tempPathFile = null;
		String uploadPath = SystemConfig.imguserpath;
		FileUploadResponse fileList = new FileUploadResponse();
		// 返回加前缀的图片数组
		List<UserImgResponse> imgResponse = new ArrayList<UserImgResponse>();
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
			UserImgResponse img = new UserImgResponse();
			img.setImg(SystemConfig.ImgurlPrefix + imgStr.get(0));
			img.setImgId(userId);// 此处返回userId 而不是imageId user表只有userId没有imageId
									// TODO:
			imgResponse.add(img);
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
			}
			int insertResult = userImgServiceImpl.insertUserImg(imgs);
			if (insertResult > 0) {
				imgResponse = businessUtils.GetImgListByUserId(userId);
			}
			// 入库成功查询用户一遍 用于返回imgId 供客户端删除用

		}
		fileList.setImgList(imgResponse);
		fileResponse.setData(fileList);
		return fileResponse;
	}

	/**
	 * 删除磁盘文件
	 * 
	 * @param url
	 */
	public void RemoveFile(String url) {
		if (!StringUtils.isEmpty(url)) {
			url = url.replace(SystemConfig.ImgurlPrefix, "");
			String imgUrl = SystemConfig.imguserpath + url;
			File dirFile = new File(imgUrl);
			dirFile.delete();
		}

	}

}
