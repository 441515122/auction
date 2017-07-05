package cn.auction.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.auction.dao.UserDao;
import cn.auction.entity.AuctionUser;
import cn.auction.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	@Override
	public AuctionUser login(AuctionUser auctionUser) {
		List<AuctionUser> list = userDao.findUsers(auctionUser);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void register(AuctionUser auctionUser) {
		userDao.addUser(auctionUser);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
}
