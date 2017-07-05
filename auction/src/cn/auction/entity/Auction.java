package cn.auction.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Auction entity. @author MyEclipse Persistence Tools
 */

public class Auction implements java.io.Serializable {

	// Fields

	private Integer auctionid;
	private String auctionname;
	private Double auctionstartprice;
	private Double auctionupset;
	private Date auctionstarttime;
	private Date auctionendtime;
	private String auctionpictype;
	private String auctiondesc;
	private Set auctionRecords = new HashSet(0);
	
	
	//非持久化属性提供getter, setter
	private double auctionPrice;
	private String username;

	// Constructors

	/** default constructor */
	public Auction() {
	}

	/** minimal constructor */
	public Auction(String auctionname, Double auctionstartprice,
			Double auctionupset, Date auctionstarttime, Date auctionendtime,
			String auctionpictype, String auctiondesc) {
		this.auctionname = auctionname;
		this.auctionstartprice = auctionstartprice;
		this.auctionupset = auctionupset;
		this.auctionstarttime = auctionstarttime;
		this.auctionendtime = auctionendtime;
		this.auctionpictype = auctionpictype;
		this.auctiondesc = auctiondesc;
	}

	/** full constructor */
	public Auction(String auctionname, Double auctionstartprice,
			Double auctionupset, Date auctionstarttime, Date auctionendtime,
			String auctionpictype, String auctiondesc, Set auctionRecords) {
		this.auctionname = auctionname;
		this.auctionstartprice = auctionstartprice;
		this.auctionupset = auctionupset;
		this.auctionstarttime = auctionstarttime;
		this.auctionendtime = auctionendtime;
		this.auctionpictype = auctionpictype;
		this.auctiondesc = auctiondesc;
		this.auctionRecords = auctionRecords;
	}

	// Property accessors

	public Integer getAuctionid() {
		return this.auctionid;
	}

	public void setAuctionid(Integer auctionid) {
		this.auctionid = auctionid;
	}

	public String getAuctionname() {
		return this.auctionname;
	}

	public void setAuctionname(String auctionname) {
		this.auctionname = auctionname;
	}

	public Double getAuctionstartprice() {
		return this.auctionstartprice;
	}

	public void setAuctionstartprice(Double auctionstartprice) {
		this.auctionstartprice = auctionstartprice;
	}

	public Double getAuctionupset() {
		return this.auctionupset;
	}

	public void setAuctionupset(Double auctionupset) {
		this.auctionupset = auctionupset;
	}

	public Date getAuctionstarttime() {
		return this.auctionstarttime;
	}

	public void setAuctionstarttime(Date auctionstarttime) {
		this.auctionstarttime = auctionstarttime;
	}

	public Date getAuctionendtime() {
		return this.auctionendtime;
	}

	public void setAuctionendtime(Date auctionendtime) {
		this.auctionendtime = auctionendtime;
	}

	public String getAuctionpictype() {
		return this.auctionpictype;
	}

	public void setAuctionpictype(String auctionpictype) {
		this.auctionpictype = auctionpictype;
	}

	public String getAuctiondesc() {
		return this.auctiondesc;
	}

	public void setAuctiondesc(String auctiondesc) {
		this.auctiondesc = auctiondesc;
	}

	public Set getAuctionRecords() {
		return this.auctionRecords;
	}

	public void setAuctionRecords(Set auctionRecords) {
		this.auctionRecords = auctionRecords;
	}

	public double getAuctionPrice() {
		return auctionPrice;
	}

	public void setAuctionPrice(double auctionPrice) {
		this.auctionPrice = auctionPrice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}