package com.qicms.core.controller.dossier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qicms.core.model.dao.user.UserDao;
import com.qicms.core.model.entity.role.Role;
import com.qicms.core.model.entity.user.User;

@Controller
@RequestMapping("/")
public class StaffDossierController {

	@Autowired
	private UserDao uDao;

	//人员档案详情
	@RequestMapping("pDossier/staffArc_2/detail/open~uid")
	@ResponseBody
	public Object open(@RequestParam Long uid) {
		return uDao.findOne(uid);
	}

	//档案信息维护
	@RequestMapping("pDossier/staffArc_2/profile/save~edit")
	@ResponseBody
	public Object save(@RequestParam Long uid,
			@RequestParam(required = false) String realName,
			@RequestParam(required = false) String userTel,
			@RequestParam(required = false) Long roleId) {
		User u = uDao.findOne(uid);
		if (realName != null && !realName.isEmpty()) {
			u.setRealName(realName);
		}
		if (userTel != null && !userTel.isEmpty()) {
			u.setUserTel(userTel);
		}
		if (roleId != null) {
			Role role = new Role();
			role.setRoleId(roleId);
			u.setRole(role);
		}
		uDao.save(u);
		return "ok";
	}

	//人员档案页面
	@RequestMapping("pDossier/staffArc_2/panel/ui~main")
	public String page(Model model) {
		return "dossier/stafflist";
	}
}