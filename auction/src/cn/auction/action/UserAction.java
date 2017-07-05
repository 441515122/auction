package cn.auction.action;

import java.util.Map;

import cn.auction.entity.AuctionUser;
import cn.auction.service.UserService;

public class UserAction extends BaseAction{
	
	private AuctionUser user;
	private UserService userService;
	private String inputCode;
	
	public String login(){
		AuctionUser loginUser = userService.login(user);
		if(loginUser != null){
			Map<String, Object> session = super.session;
			session.put("user", loginUser);
			return SUCCESS;
		}else{
			super.addActionMessage("登录名或密码错误");
			return ERROR;
		}
	}
	
	public void validateLogin(){
		String numrand = (String) super.session.get("numrand");
		if(inputCode != null && !inputCode.equals(numrand)){
			super.addFieldError("inputCode", "验证码不正确");
		}
	}
	
	public String register(){
		user.setUserisadmin(false);
		userService.register(user);
		return SUCCESS;
	}
	
	public void validateRegister(){
		if(user.getUsername() == null || "".equals(user.getUsername())){
			super.addFieldError("user.username", "用户名不能为空");
		}else if(user.getUserpassword() == null || "".equals(user.getUserpassword())){
			super.addFieldError("user.userpassword", "密码不能为空");
		}else if(user.getUsercardno() == null || "".equals(user.getUsercardno())){
			super.addFieldError("user.usercardno", "身份证不能为空");
		}else if(user.getUsertel() == null || "".equals(user.getUsertel())){
			super.addFieldError("user.usertel", "电话号码不能为空");
		}
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AuctionUser getUser() {
		return user;
	}

	public void setUser(AuctionUser user) {
		this.user = user;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	
	
}
