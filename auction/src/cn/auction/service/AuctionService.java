package cn.auction.service;

import java.util.List;

import cn.auction.entity.Auction;
import cn.auction.entity.AuctionRecord;
import cn.auction.entity.AuctionUser;

public interface AuctionService {
	
	public List<Auction> findAllAuction();

	public void delete(Auction auction);

	public Auction getAuction(Integer auctionid);
	
	public List<Auction> findAuctionByPages(int pageNo, int pageSize);
	
	public long getAllCounts();

	public void addAuction(Auction auction);

	public void updateAuction(Auction auction);

	public void createAuctionBid(AuctionRecord record) throws Exception;

	public List<Auction> findAuctionByPages(int pageNo, int pageSize,
			Auction auctionExample);

	public int getAllCounts(Auction auctionExample);
	
	/**
	 * 查询已过期的商品
	 * @author YS
	 * @date : 2017年6月21日 下午2:44:38
	 */  
	public List<Auction> selectBiddedAuction(AuctionUser user);
	
	/**
	 * 查询正在拍卖的商品
	 * @author YS
	 * @date : 2017年6月21日 下午2:45:05
	 */  
	public List<Auction> selectBiddingAuction(AuctionUser user);
	
}
