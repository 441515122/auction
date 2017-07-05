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
			super.addActionMessage("��¼�����������");
			return ERROR;
		}
	}
	
	public void validateLogin(){
		String numrand = (String) super.session.get("numrand");
		if(inputCode != null && !inputCode.equals(numrand)){
			super.addFieldError("inputCode", "��֤�벻��ȷ");
		}
	}
	
	public String register(){
		user.setUserisadmin(false);
		userService.register(user);
		return SUCCESS;
	}
	
	public void validateRegister(){
		if(user.getUsername() == null || "".equals(user.getUsername())){
			super.addFieldError("user.username", "�û�������Ϊ��");
		}else if(user.getUserpassword() == null || "".equals(user.getUserpassword())){
			super.addFieldError("user.userpassword", "���벻��Ϊ��");
		}else if(user.getUsercardno() == null || "".equals(user.getUsercardno())){
			super.addFieldError("user.usercardno", "���֤����Ϊ��");
		}else if(user.getUsertel() == null || "".equals(user.getUsertel())){
			super.addFieldError("user.usertel", "�绰���벻��Ϊ��");
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
