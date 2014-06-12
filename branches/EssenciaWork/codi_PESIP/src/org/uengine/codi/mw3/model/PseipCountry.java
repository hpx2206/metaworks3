package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.dao.Database;

public class PseipCountry extends Database<IPseipCountry> implements IPseipCountry{
	
	String countryCode;
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

	String nameKo;
		public String getNameKo() {
			return nameKo;
		}
		public void setNameKo(String nameKo) {
			this.nameKo = nameKo;
		}

	String nameEn;
		public String getNameEn() {
			return nameEn;
		}
		public void setNameEn(String nameEn) {
			this.nameEn = nameEn;
		}

	String alpha2;
		public String getAlpha2() {
			return alpha2;
		}
		public void setAlpha2(String alpha2) {
			this.alpha2 = alpha2;
		}

	String continent;
		public String getContinent() {
			return continent;
		}
		public void setContinent(String continent) {
			this.continent = continent;
		}

	Date modDate;
		public Date getModDate() {
			return modDate;
		}
		public void setModDate(Date modDate) {
			this.modDate = modDate;
		}

	Date regDate;
		public Date getRegDate() {
			return regDate;
		}
		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}

	String alpha3;
		public String getAlpha3() {
			return alpha3;
		}
		public void setAlpha3(String alpha3) {
			this.alpha3 = alpha3;
		}

	String odaCooperation;
		public String getOdaCooperation() {
			return odaCooperation;
		}
		public void setOdaCooperation(String odaCooperation) {
			this.odaCooperation = odaCooperation;
		}

	String edcfCooperation;
		public String getEdcfCooperation() {
			return edcfCooperation;
		}
		public void setEdcfCooperation(String edcfCooperation) {
			this.edcfCooperation = edcfCooperation;
		}

	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

	int isDeleted;
		public int getIsDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(int isDeleted) {
			this.isDeleted = isDeleted;
		}

	double lat;
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}

	double lng;
		public double getLng() {
			return lng;
		}
		public void setLng(double lng) {
			this.lng = lng;
		}
		
	public IPseipCountry loadCountryCodeInfo() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select alpha2, country_code as countryCode");
		sb.append(" from pseip_country");
		
		IPseipCountry countryCodeInfo = (IPseipCountry) sql(IPseipCountry.class, sb.toString());
		countryCodeInfo.select();
		
		return countryCodeInfo;
	}
	
	public IPseipCountry loadCountryDetailInfo(String countryCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select name_ko as nameKo, url");
		sb.append(" from pseip_country");
		sb.append(" where country_code = ?countryCode");
		
		IPseipCountry countryKoName = (IPseipCountry) sql(IPseipCountry.class, sb.toString());
		countryKoName.setCountryCode(countryCode);
		countryKoName.select();
		
		return countryKoName;
	}
	
}
