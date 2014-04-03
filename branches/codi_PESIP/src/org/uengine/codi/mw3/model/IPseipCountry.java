package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="pseip_country")
public interface IPseipCountry extends IDAO {

	@Id
	public String getCountryCode();
	public void setCountryCode(String countryCode);
	
	public String getNameKo();
	public void setNameKo(String nameKo);
	
	public String getNameEn();
	public void setNameEn(String nameEn);
	
	public String getAlpha2();
	public void setAlpha2(String alpha2);
	
	public String getContinent();
	public void setContinent(String continent);
	
	public Date getModDate();
	public void setModDate(Date modDate);
	
	public Date getRegDate();
	public void setRegDate(Date regDate);
	
	public String getAlpha3();
	public void setAlpha3(String alpha3);
	
	public String getOdaCooperation();
	public void setOdaCooperation(String odaCooperation);
	
	public String getEdcfCooperation();
	public void setEdcfCooperation(String edcfCooperation);
	
	public String getUrl();
	public void setUrl(String url);
	
	public int getIsDeleted();
	public void setIsDeleted(int isDeleted);

	@Hidden
	public double getLat();
	public void setLat(double lat);
	
	@Hidden
	public double getLng();
	public void setLng(double lng);
	
	
}
