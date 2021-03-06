package com.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.service.utils.DateUtil;
import com.service.utils.SystemConfig;

import net.coobird.thumbnailator.Thumbnails;

@RequestMapping("/image")
@Controller
public class ImageController extends BaseController {

//	@Autowired
//	private PropertiesSystemConfig systetmConfig;
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String FileUpload(HttpServletRequest request, HttpServletResponse response,String controlName) throws Exception {

		String realFileName = "";
		String returnFile = "";
		String uploadPath = SystemConfig.imguserpath;
		String currData = DateUtil.getNowDateExt();
		uploadPath = uploadPath + "/" + currData + "/";
		File dirFile = new File(uploadPath);
		// 没有则创建
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile(controlName);
		if (file == null) {
			return "无上传文件";
		}
		String fileName = file.getOriginalFilename();
		Pattern reg = Pattern.compile("(?i)[.]jpg|JPG|png|PNG|jpeg|JPEG|GIF|gif$");
		Matcher matcher = reg.matcher(fileName);
		if (!matcher.find()) {
			return "上传文件类型有误";
		}

		returnFile = new Date().getTime() + new Random().nextInt(99999)
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
		realFileName = uploadPath + returnFile;

		try {
			InputStream input = file.getInputStream();
			FileOutputStream output = new FileOutputStream(new File(realFileName));
			Streams.copy(input, output, true);
			// 压缩后的图片
			String thumbnailName = new Date().getTime() + new Random().nextInt(99999)
					+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
			Thumbnails.of(realFileName).scale(1f).outputFormat("jpg").toFile(uploadPath + "/" + thumbnailName);
			return SystemConfig.ImgurlPrefix+ "/" + currData + "/" + thumbnailName;
			//return "http://localhost:806" + "/" + currData + "/" + thumbnailName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnFile;

		// DiskFileItemFactory factory = new DiskFileItemFactory();
		// factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		// factory.setRepository(tempPathFile);// 设置缓冲区目录
		//
		// List<String> imageStr = new ArrayList<String>();
		// ServletFileUpload upload = new ServletFileUpload(factory);
		// upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
		// List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
		// Iterator<FileItem> i = items.iterator();
		// while (i.hasNext()) {
		// FileItem fi = (FileItem) i.next();
		// String fileName = fi.getName();
		// if (fileName != null) {
		// SimpleDateFormat timeFile = new
		// SimpleDateFormat("YYYYMMddHHmmssSSS");
		// Date Timedate = new Date();
		// String newFileName = userId + timeFile.format(Timedate) + ".jpg";
		//
		// // File fullFile = new File(new String(fi.getName().getBytes(),
		// // "utf-8")); // 解决文件名乱码问题
		//
		// File savedFile = new File(uploadPath, newFileName);
		// fi.write(savedFile);
		// imageStr.add(newFileName);
		//
		// // 压缩后的图片
		// String thumbnailName = userId + timeFile.format(Timedate) +
		// "thuma.jpg";
		// Thumbnails.of(uploadPath + "/" +
		// newFileName).scale(1f).outputFormat("jpg")
		// .toFile(uploadPath + "/" + thumbnailName);
		// imgStr.add(currentDate + "/" + thumbnailName);
		// return uploadPath + "/" + thumbnailName;
		// }
		// }
		// return "";
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
