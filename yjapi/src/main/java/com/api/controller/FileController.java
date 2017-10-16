package com.api.controller;

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 
import com.api.business.FileBusiness;
import com.api.response.FileUploadResponse;
import com.api.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/file")
@Api(tags = "文件上传")
public class FileController {


	@Autowired
	private FileBusiness fileBusiness;
	/**
	 * 用户图片上传
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-fileUpload", value = "用户上传头像和图片库 因文件上传不是标准的json "
			+ "除图片需增加参数userId 和imageType 1头像2图片库", notes = "用户上传图片")
	public BaseResponse<FileUploadResponse> fileUpload(@ApiParam(value = "输入") HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return fileBusiness.FileUpload(request, response);
	}

}
