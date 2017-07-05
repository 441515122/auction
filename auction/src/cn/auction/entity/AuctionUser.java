package cn.auction.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AuctionUser entity. @author MyEclipse Persistence Tools
 */

public class AuctionUser implements java.io.Serializable {

	// Fields

	private Integer userid;
	private String username;
	private String userpassword;
	private String usercardno;
	private String usertel;
	private String useraddress;
	private String userpostnumber;
	private Boolean userisadmin;
	private Set auctionRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public AuctionUser() {
	}

	/** minimal constructor */
	public AuctionUser(String username, String userpassword) {
		this.username = username;
		this.userpassword = userpassword;
	}

	/** full constructor */
	public AuctionUser(String username, String userpassword, String usercardno,
			String usertel, String useraddress, String userpostnumber,
			Boolean userisadmin, Set auctionRecords) {
		this.username = username;
		this.userpassword = userpassword;
		this.usercardno = usercardno;
		this.usertel = usertel;
		this.useraddress = useraddress;
		this.userpostnumber = userpostnumber;
		this.userisadmin = userisadmin;
		this.auctionRecords = auctionRecords;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return this.userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsercardno() {
		return this.usercardno;
	}

	public void setUsercardno(String usercardno) {
		this.usercardno = usercardno;
	}

	public String getUsertel() {
		return this.usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUseraddress() {
		return this.useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getUserpostnumber() {
		return this.userpostnumber;
	}

	public void setUserpostnumber(String userpostnumber) {
		this.userpostnumber = userpostnumber;
	}

	public Boolean getUserisadmin() {
		return this.userisadmin;
	}

	public void setUserisadmin(Boolean userisadmin) {
		this.userisadmin = userisadmin;
	}

	public Set getAuctionRecords() {
		return this.auctionRecords;
	}

	public void setAuctionRecords(Set auctionRecords) {
		this.auctionRecords = auctionRecords;
	}

}