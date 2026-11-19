package com.qicms.core.services.role;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicms.core.model.dao.roledao.RolepowerlistDao;
import com.qicms.core.model.entity.role.Role;
import com.qicms.core.model.entity.role.Rolepowerlist;
import com.qicms.core.model.entity.system.SystemMenu;

@Service
@Transactional
public class RoleService {
	@Autowired
	private RolepowerlistDao rldao;
	
	public void index(List<SystemMenu>  menulist,Role rolep){
		
		for (SystemMenu systemMenu : menulist) {
			
			rldao.save(new Rolepowerlist(rolep,systemMenu));
		}
	}
	//保存一个对象；	
	public Rolepowerlist sava(Rolepowerlist rolepower){
		return rldao.save(rolepower);
	}

}
