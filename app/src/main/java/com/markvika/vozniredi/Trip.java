package com.markvika.vozniredi;

import java.util.ArrayList;

public class Trip {
	private ArrayList<String> as;
	private ArrayList<String> ad;
	private ArrayList<String> ds;
	private ArrayList<String> dd;
	private ArrayList<String> tn;
	private String priceLink;

	public Trip() {
		as = new ArrayList<String>();
		ad = new ArrayList<String>();
		ds = new ArrayList<String>();
		dd = new ArrayList<String>();
		tn = new ArrayList<String>();
		priceLink = "";
	}

	public ArrayList<String> getAs() {
		return as;
	}

	public void setAs(ArrayList<String> as) {
		this.as = as;
	}

	public ArrayList<String> getAd() {
		return ad;
	}

	public void setAd(ArrayList<String> ad) {
		this.ad = ad;
	}

	public ArrayList<String> getDs() {
		return ds;
	}

	public void setDs(ArrayList<String> ds) {
		this.ds = ds;
	}

	public ArrayList<String> getDd() {
		return dd;
	}

	public void setDd(ArrayList<String> dd) {
		this.dd = dd;
	}

	public ArrayList<String> getTn() {
		return tn;
	}

	public void setTn(ArrayList<String> tn) {
		this.tn = tn;
	}

	public String getPriceLink() {
		return priceLink;
	}

	public void setPriceLink(String priceLink) {
		this.priceLink = priceLink;
	}

	public String toString() {
		String temp = "";
		for (int i = 0; i < as.size(); i++) {
//			temp += ds.get(i)+" "+dd.get(i)+"\n"+as.get(i)+" "+ad.get(i)+"\n"+tn.get(i)+"\n";
			temp += "Vlak: "+tn.get(i)+"\nOdhod: "+ds.get(i)+" ob: "+dd.get(i)+"\nPrihod: "+as.get(i)+" ob: "+ad.get(i)+"\n";
		}
		return temp+"\n";
	}
}
