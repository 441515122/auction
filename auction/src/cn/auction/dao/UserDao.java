package cn.auction.dao;

import java.util.List;

import cn.auction.entity.AuctionUser;

public interface UserDao {
	
	public void addUser(AuctionUser auctionUser);
	
	public void updateUser(AuctionUser auctionUser);
	
	public void deleteUser(AuctionUser auctionUser);
	
	public List<AuctionUser> findAllUsers();
	
	public List<AuctionUser> findUsers(AuctionUser auctionUser);
	
	public AuctionUser getUser(int userid);
}
