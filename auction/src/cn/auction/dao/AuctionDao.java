package cn.auction.dao;

import java.util.List;

import cn.auction.entity.Auction;

public interface AuctionDao {
	
	public List<Auction> findAllAuction();

	public void delete(Auction auction);

	public Auction getAuction(Integer auctionid);
	
	/**
	 * 分页查询
	 * @param pageNO 页码
	 * @param pageSize 每页记录数
	 * @author YS
	 * @date : 2017年6月16日 下午2:42:05
	 */ 
	public List<Auction> findAuctionByPages(int pageNo, int pageSize);
	
	public long getAllCounts();

	public void addAuction(Auction auction);

	public void updateAuction(Auction auction);

	public List<Auction> findAuctionByPages(int pageNo, int pageSize,
			Auction auctionExample);

	public Integer getAllCounts(Auction auctionExample);
	
	public List<Auction> find(String hql, String[] paramNames, Object[] values);
}
