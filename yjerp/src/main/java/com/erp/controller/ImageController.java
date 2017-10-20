package com.erp.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/image")
public class ImageController {

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public void FileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		File tempPathFile = null;
		String uploadPath = SystemConfig.imguserpath;

		// 返回加前缀的图片数组

		List<String> imgStr = new ArrayList<String>();
		// 用户ID
		int userId = request.getParameter("userId") == null ? 0 : Integer.parseInt(request.getParameter("userId"));
		if (userId <= 0) {

		}
		// 1是头像 2是个人图库
		String imageType = request.getParameter("imageType");
		if (StringUtils.isEmpty(imageType)) {

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
		// org.apache.commons.fileupload.disk

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
				String newFileName = userId + timeFile.format(Timedate) + ".jpg";

				// File fullFile = new File(new String(fi.getName().getBytes(),
				// "utf-8")); // 解决文件名乱码问题

				File savedFile = new File(uploadPath, newFileName);
				fi.write(savedFile);
				imageStr.add(newFileName);

				// 压缩后的图片
				String thumbnailName = userId + timeFile.format(Timedate) + "thuma.jpg";
				Thumbnails.of(uploadPath + "/" + newFileName).scale(1f).outputFormat("jpg")
						.toFile(uploadPath + "/" + thumbnailName);
				imgStr.add(currentDate + "/" + thumbnailName);
			}
		}
		//
		// if (imageType.equals("1") && imgStr.size() > 0)// 1是头像
		// {
		// User user = new User();
		// user.setUserId(userId);
		// user.setHeadImage(imgStr.get(0));
		// userServiceImpl.updateUser(user);
		// UserImgResponse img = new UserImgResponse();
		// img.setImg(SystemConfig.ImgurlPrefix + imgStr.get(0));
		// img.setImgId(userId);// 此处返回userId 而不是imageId user表只有userId没有imageId
		// // TODO:
		// imgResponse.add(img);
		// // 更新user表HandImage
		// } else {
		// // 添加个人图片 userImage
		// List<UserImg> imgs = new ArrayList<UserImg>();
		// for (int j = 0; j < imgStr.size(); j++) {
		// UserImg img = new UserImg();
		// String url = imgStr.get(j).toString();
		// img.setUserId(userId);
		// img.setImageType(0);
		// img.setImagePath(url);
		// img.setImageSort(j);
		// imgs.add(img);
		// }
		// int insertResult = userImgServiceImpl.insertUserImg(imgs);
		// if (insertResult > 0) {
		// List<UserImgResponse> dataImg =
		// businessUtils.GetImgListByUserId(userId);
		// // 返回这次请求的图片数据
		// for (UserImgResponse userImg : dataImg) {
		// if
		// (imgStr.contains(userImg.getImg().replace(SystemConfig.ImgurlPrefix,
		// ""))) {
		// imgResponse.add(userImg);
		// }
		// }
		// }
		// // 入库成功查询用户一遍 用于返回imgId 供客户端删除用
		//
		// }

	}

}
