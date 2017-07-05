package cn.auction.entity;

import java.util.Date;

/**
 * AuctionRecord entity. @author MyEclipse Persistence Tools
 */

public class AuctionRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Auction auction;
	private AuctionUser auctionUser;
	private Date auctiontime;
	private Double auctionprice;

	// Constructors

	/** default constructor */
	public AuctionRecord() {
	}

	/** minimal constructor */
	public AuctionRecord(Auction auction, Date auctiontime, Double auctionprice) {
		this.auction = auction;
		this.auctiontime = auctiontime;
		this.auctionprice = auctionprice;
	}

	/** full constructor */
	public AuctionRecord(Auction auction, AuctionUser auctionUser,
			Date auctiontime, Double auctionprice) {
		this.auction = auction;
		this.auctionUser = auctionUser;
		this.auctiontime = auctiontime;
		this.auctionprice = auctionprice;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public AuctionUser getAuctionUser() {
		return this.auctionUser;
	}

	public void setAuctionUser(AuctionUser auctionUser) {
		this.auctionUser = auctionUser;
	}

	public Date getAuctiontime() {
		return this.auctiontime;
	}

	public void setAuctiontime(Date auctiontime) {
		this.auctiontime = auctiontime;
	}

	public Double getAuctionprice() {
		return this.auctionprice;
	}

	public void setAuctionprice(Double auctionprice) {
		this.auctionprice = auctionprice;
	}

}