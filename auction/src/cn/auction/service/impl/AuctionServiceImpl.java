package cn.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.auction.dao.AuctionDao;
import cn.auction.dao.AuctionRecordDao;
import cn.auction.entity.Auction;
import cn.auction.entity.AuctionRecord;
import cn.auction.entity.AuctionUser;
import cn.auction.service.AuctionService;

public class AuctionServiceImpl implements AuctionService {
	
	private AuctionDao auctionDao;
	private AuctionRecordDao auctionRecordDao;

	@Override
	public List<Auction> findAllAuction() {
		
		return auctionDao.findAllAuction();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Auction auction) {
		auctionDao.delete(auction);
	}
	
	@Override
	public Auction getAuction(Integer auctionid) {
		
		return auctionDao.getAuction(auctionid);
	}
	
	@Override
	public List<Auction> findAuctionByPages(int pageNo, int pageSize) {
		return auctionDao.findAuctionByPages(pageNo, pageSize);
	}
	
	@Override
	public List<Auction> findAuctionByPages(int pageNo, int pageSize,
			Auction auctionExample) {
		return auctionDao.findAuctionByPages(pageNo, pageSize, auctionExample);
	}
	
	@Override
	public long getAllCounts() {
		return auctionDao.getAllCounts();
	}
	
	@Override
	public int getAllCounts(Auction auctionExample) {
		return auctionDao.getAllCounts(auctionExample);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addAuction(Auction auction) {
		auctionDao.addAuction(auction);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateAuction(Auction auction) {
		auctionDao.updateAuction(auction);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void createAuctionBid(AuctionRecord record) throws Exception {
		//先查询到当前竞拍对应的商品对象
		Auction auction = auctionDao.getAuction(record.getAuction().getAuctionid());
		
		//1.判断时间是否有效
		if(auction.getAuctionendtime().after(new Date()) == false){
			throw new Exception("该商品已经过期！");
		}
		
		//2.判断有没有竞拍记录
		if(auction.getAuctionRecords().size() != 0){
			//获取最高出价记录
			AuctionRecord maxRecord = (AuctionRecord) auction.getAuctionRecords().toArray()[0];
			if(record.getAuctionprice() <= maxRecord.getAuctionprice()){
				throw new Exception("出价必须高于当前最高竞拍价！");
			}
			
		}else{//没有竞拍记录，出价必须高于起拍价
			if(record.getAuctionprice() <= auction.getAuctionstartprice()){
				throw new Exception("出价必须高于起拍价！");
			}
		}
		auctionRecordDao.addAuctionRecord(record);
	}
	
	public void setAuctionDao(AuctionDao auctionDao) {
		this.auctionDao = auctionDao;
	}

	public void setAuctionRecordDao(AuctionRecordDao auctionRecordDao) {
		this.auctionRecordDao = auctionRecordDao;
	}

	@Override
	public List<Auction> selectBiddedAuction(AuctionUser user) {
		String hql = "select distinct a from Auction a inner join fetch a.auctionRecords r where a.auctionendtime<:endtime and r.auctionUser.userid=:userid";
		String[] paramNames = {"endtime", "userid"};
		Object[] values = {new Date(), user.getUserid()};
		List<Auction> list = auctionDao.find(hql, paramNames, values);
		
		for (Auction auction : list) {
			AuctionRecord maxRecord = (AuctionRecord) auction.getAuctionRecords().toArray()[0];
			auction.setUsername(maxRecord.getAuctionUser().getUsername());
			auction.setAuctionPrice(maxRecord.getAuctionprice());
		}
		return list;
	}

	@Override
	public List<Auction> selectBiddingAuction(AuctionUser user) {
		String hql = "select distinct a from Auction a inner join fetch a.auctionRecords r where a.auctionendtime>=:endtime and r.auctionUser.userid=:userid";
		String[] paramNames = {"endtime", "userid"};
		Object[] values = {new Date(), user.getUserid()};
		List<Auction> list = auctionDao.find(hql, paramNames, values);
		
		for (Auction auction : list) {
			AuctionRecord maxRecord = (AuctionRecord) auction.getAuctionRecords().toArray()[0];
			auction.setUsername(maxRecord.getAuctionUser().getUsername());
			auction.setAuctionPrice(maxRecord.getAuctionprice());
		}
		return list;
	}
	
}
