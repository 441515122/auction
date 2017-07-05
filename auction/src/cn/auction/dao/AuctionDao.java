package cn.auction.dao;

import java.util.List;

import cn.auction.entity.Auction;

public interface AuctionDao {
	
	public List<Auction> findAllAuction();

	public void delete(Auction auction);

	public Auction getAuction(Integer auctionid);
	
	/**
	 * ��ҳ��ѯ
	 * @param pageNO ҳ��
	 * @param pageSize ÿҳ��¼��
	 * @author YS
	 * @date : 2017��6��16�� ����2:42:05
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
