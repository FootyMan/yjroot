package com.erp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.erp.aspect.Log;
import com.erp.service.MemberService;
import com.erp.utils.R;
import com.service.bean.User;
import com.service.utils.MandDaoClient;
import com.service.utils.StringUtils;

@RequestMapping("/erp/message")
@Controller
public class MessageController extends BaseController {

	private String prefix = "erp/message";

	@Autowired
	MemberService memberService;// 会员

	@RequiresPermissions("erp:message:page")
	@GetMapping("/page")
	String msgpage(Model model) {
		return prefix + "/message";
	}

	/**
	 * 添加用户
	 * 
	 * @param userModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("erp:message:page")
	@Log("发送短信")
	@PostMapping("/send")
	@ResponseBody
	R send(@RequestParam Map<String, Object> params) {
		int type = 1;
		MandDaoClient client;
		String phone = params.get("phone").toString();
		String msg = params.get("msgContent").toString();
		String result_mt = "";
		try {
			client = new MandDaoClient();
			String content = URLEncoder.encode(MandDaoClient.sign + msg, "utf8");
			// 单个会员
			if (type == 1 && !StringUtils.isEmpty(phone)) {
				result_mt = client.mdSmsSend_u(phone, content, "", "", "");
			}
			// 所有
			else {

				Predicate<User> contain = n -> n.getIsImport() == 0;
				List<User> user_data = memberService.ImportUser();
				user_data.stream().filter(contain).forEach(P -> {

					client.mdSmsSend_u(P.getPhone(), content, "", "", "");
				});
			}
			if (result_mt.startsWith("-") || result_mt.equals(""))// 发送短信，如果是以负号开头就是发送失败。
			{
				System.out.print("发送失败！返回值为：" + result_mt + "请查看webservice返回值对照表");
				return R.error();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return R.ok();
	}
}
