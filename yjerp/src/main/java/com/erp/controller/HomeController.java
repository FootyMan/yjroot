package com.erp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.erp.huashe.DetailsInfo;
import com.erp.huashe.HauSheList;
import com.erp.huashe.Label;
import com.erp.huashe.ListDiscovers;
import com.erp.huashe.Member;
import com.erp.huashe.Photo;
import com.erp.huashe.UserInfo;
import com.erp.model.PositionModel;
import com.erp.model.UserImageModel;
import com.erp.model.UserModel;
import com.erp.utils.PositionUtils;
import com.google.gson.Gson;
import com.service.api.impl.InvitationCodeServiceImpl;
import com.service.api.impl.LabletTypeServiceImpl;
import com.service.api.impl.ProvinceServiceImpl;
import com.service.api.impl.UserBrowseExtServiceImpl;
import com.service.api.impl.UserDatumServiceImpl;
import com.service.api.impl.UserImgServiceImpl;
import com.service.api.impl.UserLableMappingServiceImpl;
import com.service.api.impl.UserPositionServiceImpl;
import com.service.api.impl.UserServiceImpl;
import com.service.bean.InvitationCode;
import com.service.bean.LabletType;
import com.service.bean.Province;
import com.service.bean.User;
import com.service.bean.UserBrowseExt;
import com.service.bean.UserDatum;
import com.service.bean.UserImg;
import com.service.bean.UserLableMapping;
import com.service.bean.UserPosition;
import com.service.easemob.EaseMobBusiness;
import com.service.easemob.NeteaseBusiness;
import com.service.easemob.NeteaseModel;
import com.service.erp.impl.UserServiceImplERP;
import com.service.utils.Md5Util;
import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;

@Controller
public class HomeController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserImgServiceImpl userImgServiceImpl;
	@Autowired
	private UserPositionServiceImpl userPositionServiceImpl;
	@Autowired
	private UserBrowseExtServiceImpl userBrowseExtServiceImpl;
	@Autowired
	private UserDatumServiceImpl userDatumServiceImpl;
	@Autowired
	private InvitationCodeServiceImpl invitationCodeServiceImpl;
	@Autowired
	private UserServiceImplERP userServiceImplERP;
	@Autowired
	private ProvinceServiceImpl provinceServiceImpl;
	@Autowired
	private LabletTypeServiceImpl labletTypeServiceImpl;
	@Autowired
	private UserLableMappingServiceImpl userLableMappingServiceImpl;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main() {
		return new ModelAndView("/index");

	}

	@RequestMapping(value = "/main/home", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("/home");

	}

	@RequestMapping(value = "/main/test", method = RequestMethod.GET)
	@ResponseBody
	public String test() {

		Gson gson = new Gson();
		for (int i = 1; i < 12; i++) {

			String listJs = sendPost("http://api.ihuashe.com/member/discover?page=" + 1
					+ "&sex=2&token=ed15251098dc72f9a8e0e74487ab2ae0", "");
			System.out.println(listJs);
			if (!StringUtils.isEmpty(listJs)) {
				HauSheList list = gson.fromJson(listJs, HauSheList.class);

				for (ListDiscovers dis : list.getInfo().discovers) {

					if (dis.getName().indexOf("花蛇小秘书") == -1) {
						System.out.println(dis.getName());
						// 获取详情
						String ditailsJs = sendPost("http://api.ihuashe.com/member/otherInfo?member_id=" + dis.getId()
								+ "&token=ed15251098dc72f9a8e0e74487ab2ae0 ", "");
						if (!StringUtils.isEmpty(ditailsJs)) {
							DetailsInfo info = gson.fromJson(ditailsJs, DetailsInfo.class);
							if (info.getInfo().getPhoto() != null && info.getInfo().getPhoto().size() > 0) {
								InsertUser(info.getInfo());
							}

						}

					}
				}
			}

		}
		return "";

	}

	/**
	 * 导入用户注册环信
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main/registerEasemob", method = RequestMethod.GET)
	@ResponseBody
	public String RegisterEasemob() {

		List<User> userResult = userServiceImplERP.ImportUser();
		for (User user : userResult) {
			// 注册环信
			String easemobId = user.getUserId() + SystemConfig.EaseSuffixId;
			// String result = EaseMobBusiness.AccountCreate(easemobId);
			// Map map = (Map) JSON.parse(result);
			String headImage;
			if (user.getHeadImage().indexOf("http") != -1) {
				headImage = user.getHeadImage();
			} else {
				headImage = SystemConfig.ImgurlPrefix + user.getHeadImage();
			}
			NeteaseModel netease = NeteaseBusiness.RollCreateaccId(easemobId, user.getNickName(), headImage);
			if (netease != null && netease.getCode() == 200) {
				// 更新用户
				User upUser = new User();
				upUser.setUserId(user.getUserId());
				upUser.setEasemobId(easemobId);
				upUser.setIsEasemob(1);
				upUser.setImToken(netease.getInfo().getToken());
				userServiceImpl.updateUser(upUser);
				netease = NeteaseBusiness.UpdateUinfo(easemobId, user.getNickName(), headImage);

			} else {

				// 已存在则更新
				netease = NeteaseBusiness.RefreshToken(easemobId);
				User upUser = new User();
				upUser.setUserId(user.getUserId());
				upUser.setEasemobId(easemobId);
				upUser.setIsEasemob(1);
				upUser.setImToken(netease.getInfo().getToken());
				userServiceImpl.updateUser(upUser);
				netease = NeteaseBusiness.UpdateUinfo(easemobId, user.getNickName(), headImage);
				System.out.println(netease.getCode());
			}
		}

		return "成功";
	}

	/**
	 * 添加
	 * 
	 * @param userModel
	 * @return
	 */
	private int InsertUser(UserInfo info) {
		List<Photo> imgList = info.getPhoto();
		Member member = info.getMember();
		int insertResult = 0;
		if (!StringUtils.isEmpty(member.getName())) {
			// 去掉重复数据
			int number = userServiceImplERP.selectUserByNickName(member.getName());
			if (number <= 0) {

				User entiyUser = new User();
				entiyUser = SetUserEntity(member);
				insertResult = userServiceImplERP.InsertUserErp(entiyUser);
				if (insertResult > 0) {
					int userId = entiyUser.getUserId();
					// 添加基本资料
					UserDatum datum = SetUserDatum(member, userId);
					int datumId = userDatumServiceImpl.insertDatum(datum);
					if (datumId > 0) {
						AddUserImage(imgList, userId);
						// // 注册环信
						// String easemobId = userId +
						// SystemConfig.EaseSuffixId;
						// String result =
						// EaseMobBusiness.AccountCreate(easemobId);
						// Map map = (Map) JSON.parse(result);
						// if (map != null && !map.containsKey("error")) {
						// // 更新用户
						// User upUser = new User();
						// upUser.setUserId(userId);
						// upUser.setEasemobId(easemobId);
						// upUser.setIsEasemob(1);
						// userServiceImpl.updateUser(upUser);
						//
						// }
						// 添加浏览记录表
						UserBrowseExt ext = new UserBrowseExt();
						ext.setUserId(userId);
						ext.setBrowseNumber(0);
						userBrowseExtServiceImpl.insertBrowseExt(ext);
						// 添加经纬度

						UserPosition position = new UserPosition();
						position.setIsPosition(1);
						position.setUserId(userId);

						position.setLongitude(StringUtils.isEmpty(member.getLongitude()) ? 39.9151190
								: Double.parseDouble(member.getLongitude()));
						position.setLatitude(StringUtils.isEmpty(member.getLatitude()) ? 116.4039630
								: Double.parseDouble(member.getLatitude()));
						userPositionServiceImpl.insertPosition(position);
						// 添加标签
						List<Label> lables = info.getLables();
						if (lables != null) {
							List<UserLableMapping> userLableMappings = new ArrayList<UserLableMapping>();
							for (Label label : lables) {
								LabletType type = labletTypeServiceImpl.selectlabletByName(label.getLable_name());
								if (type != null) {
									UserLableMapping mapping = new UserLableMapping();
									mapping.setUserId(userId);
									mapping.setLableId(type.getLableId());
									mapping.setLableType(type.getLableType());
									userLableMappings.add(mapping);

								}
							}
							if (userLableMappings.size() > 0) {
								userLableMappingServiceImpl.insertlabletMapping(userLableMappings);
							}
						}

					}
				}

			}

		}

		return insertResult;
	}

	/**
	 * 更新和添加设置对象
	 * 
	 * @param userModel
	 * @param userid
	 * @return
	 */
	public User SetUserEntity(Member userModel) {
		User entiyUser = new User();

		// 添加
		String phone = "0" + game(10);
		User userData = userServiceImpl.selectUserByphone(phone);
		while (userData != null) {
			phone = "0" + game(11);
			userData = userServiceImpl.selectUserByphone(phone);
		}
		// 获取邀请码
		InvitationCode inCode = invitationCodeServiceImpl.selectCodeByvalid();
		entiyUser.setInviteCode(inCode.getCode());// inCode.getCode()
		entiyUser.setPhone(phone);
		entiyUser.setDeviceType(2);
		entiyUser.setDeviceToken("XXXX-XXXX-XXXX-XXXX");
		String headImg = userModel.getAvatar();
		entiyUser.setHeadImage(headImg);
		entiyUser.setNickName(userModel.getName());
		entiyUser.setIsImport(1);
		entiyUser.setPassWord(Md5Util.stringByMD5("yj123"));
		return entiyUser;
	}

	/**
	 * 基本资料
	 * 
	 * @param userModel
	 * @param userId
	 * @return
	 */
	public UserDatum SetUserDatum(Member userModel, int userId) {
		UserDatum datum = new UserDatum();
		datum.setUserId(userId);
		datum.setAge(userModel.getAge());
		datum.setGender(userModel.getSex());

		Province Province = provinceServiceImpl.SelectProvincesByName(userModel.getCity());
		datum.setCityId(1);
		if (Province != null) {
			datum.setCityId(Province.getProvinceId());
		}

		datum.setWeight(userModel.getWeight() + "KG");
		datum.setHeight(userModel.getHeight() + "CM");
		datum.setShape(userModel.getShape());
		datum.setSexuat(userModel.getSex_orientation());
		datum.setSign(userModel.getPersonalized_signature());
		return datum;
	}

	/**
	 * 添加图片
	 * 
	 * @param imgList
	 * @param userId
	 * @return
	 */
	public int AddUserImage(List<Photo> imgList, int userId) {
		int result = 0;
		if (imgList != null && imgList.size() > 0) {

			// 添加图片
			if (imgList != null && imgList.size() > 0) {
				List<UserImg> entiyImgs = new ArrayList<UserImg>();
				for (int i = 0; i < imgList.size(); i++) {
					Photo photo = imgList.get(i);
					UserImg img = new UserImg();
					img.setUserId(userId);
					img.setImageType(0);
					String paht = photo.getAvatar();
					img.setImagePath(paht);
					img.setImageSort(i);
					img.setImgStatus(1);
					entiyImgs.add(img);
				}
				result = userImgServiceImpl.insertUserImg(entiyImgs);
			}
		}
		return result;
	}

	/**
	 * 获取count个随机数
	 * 
	 * @param count
	 *            随机数个数
	 * @return
	 */
	public static String game(int count) {
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num) + ""), "");
		}
		return sb.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Cookie",
					"XSRF-TOKEN=eyJpdiI6InRJVWpuVEp5R2N4dkthU2h3MnZVblE9PSIsInZhbHVlIjoiSjNzY2ZYc3ZKaW1wZGV4QmNONDZzZlNKWDlLMlJCaUp3UElUdmlkNjJCYW1jUndWbkJyN1lha1wvZVRNWm5YdnJPcWRCTUc4b1I4UUNoYTBuZndETmh3PT0iLCJtYWMiOiJhNmNjYWUyNGZhMjM3ZGY0OWIzZDQ5NWNmNTRhMWNiMWQ1ODcxZTYxMmUyY2Q0NGExYjM5ZWEyNTEwNDlmMWU5In0%3D; ihuashe_prod_session=eyJpdiI6IkR4Z2wwVjBsSWxKZFpHMVRMOWh6a2c9PSIsInZhbHVlIjoibXA5emlCdms3MW83clJpRjc5RnlyY2JJNllReWJFd1FYQkhzS1gyYlJlQU8zZ3NFTFlsUDhsQzY1N2JHUnY4Qjh4ME5sQTVIN2dYdVhLSHFJWXY1OEE9PSIsIm1hYyI6IjY4ZDFjNDRmMTA5ZjgwYmMyMmQ4MDQ2ZjVkODE0ODJjZWQxNmQzMjExYTRkMjljMzBkZWMyMjhlOGI4YzIxYWMifQ%3D%3D; member_id=eyJpdiI6IkNnckNcL0lTYWk5WXE2V09UbExuMHdRPT0iLCJ2YWx1ZSI6IlNHdlByeVFSRFpwZHZxMFdDM3RONWc9PSIsIm1hYyI6ImVkZGQxZTJhOWZhYWFiZTFlOTdlN2FiMTI1MjllYzNmNmE3N2Y4Nzg5MDAwY2RkM2Q5MDEzZWY3ZjliY2VjMDcifQ%3D%3D");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
