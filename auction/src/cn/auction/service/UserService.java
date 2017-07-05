package cn.auction.service;

import cn.auction.entity.AuctionUser;

public interface UserService {
	
	public AuctionUser login(AuctionUser auctionUser);
	
	public void register(AuctionUser auctionUser);
}
