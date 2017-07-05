package cn.auction.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.auction.dao.AuctionDao;
import cn.auction.entity.Auction;
import cn.auction.service.UpdateAuctionService;

public class UpdateAuctionServiceImpl implements UpdateAuctionService {
	
	private AuctionDao auctionDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateAuction(Auction auction) {
		auctionDao.updateAuction(auction);
	}

	public void setAuctionDao(AuctionDao auctionDao) {
		this.auctionDao = auctionDao;
	}
	
	
}
