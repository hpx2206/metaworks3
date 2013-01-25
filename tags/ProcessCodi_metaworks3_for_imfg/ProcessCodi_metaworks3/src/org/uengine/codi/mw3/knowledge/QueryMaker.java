package org.uengine.codi.mw3.knowledge;

import java.util.*;
import com.diquest.ir.common.exception.IRException;
import com.diquest.ir.common.msg.protocol.Protocol;
import com.diquest.ir.common.msg.protocol.query.OrderBySet;
import com.diquest.ir.common.msg.protocol.query.Query;
import com.diquest.ir.common.msg.protocol.query.QuerySet;
import com.diquest.ir.common.msg.protocol.query.SelectSet;
import com.diquest.ir.common.msg.protocol.query.WhereSet;
import com.diquest.ir.common.msg.protocol.result.ResultSet;
import com.diquest.ir.common.msg.protocol.result.Result;
import com.diquest.ir.client.command.CommandSearchRequest;
 
public class QueryMaker{

	public ArrayList getResult(String keyword){
		
		if(keyword != null && keyword.trim().length() > 0) {
			
			String search_ip = "127.0.0.1";		// 검색엔진 IP
			int search_port = 5555;				// 검색엔진 PORT
			int rs = -1;
			Result result = null;
			ResultSet results = null;
			Result [] resultlist = null;
			CommandSearchRequest cmd = null;
			
			String selectFieldName="";
			
			ArrayList selectSetList = new ArrayList();
			SelectSet[] selectSet = null;
			ArrayList whereSetList = new ArrayList();
			WhereSet[] whereSet = null;
			OrderBySet[] order = null;
			ArrayList al = new ArrayList();			
			
			//selectSet			
			selectSetList.add(new SelectSet("URL", (byte) (Protocol.SelectSet.NONE)));				
			selectSetList.add(new SelectSet("THUMBNAIL", (byte) (Protocol.SelectSet.NONE)));		
			selectSetList.add(new SelectSet("TITLE", (byte) (Protocol.SelectSet.NONE)));				
			selectSetList.add(new SelectSet("INTRODUCTION", (byte) (Protocol.SelectSet.HIGHLIGHT), 200));
			selectSetList.add(new SelectSet("GOAL", (byte) (Protocol.SelectSet.HIGHLIGHT), 200));

			selectSet = new SelectSet[selectSetList.size()];
			for(int i=0 ; i<selectSetList.size() ; i++)
				selectSet[i] = (SelectSet)selectSetList.get(i);
			
			//WhereSet
			whereSetList.add(new WhereSet ("IDX_TITLE", Protocol.WhereSet.OP_HASANY, keyword, 100));
			whereSetList.add(new WhereSet (Protocol.WhereSet.OP_OR));
			whereSetList.add(new WhereSet ("IDX_INTRODUCTION", Protocol.WhereSet.OP_HASANY, keyword, 50));
			whereSetList.add(new WhereSet (Protocol.WhereSet.OP_OR));
			whereSetList.add(new WhereSet ("IDX_GOAL", Protocol.WhereSet.OP_HASANY, keyword, 50));
			whereSet = new WhereSet[whereSetList.size()];
			for(int i=0 ; i<whereSetList.size() ; i++)
				whereSet[i] = (WhereSet)whereSetList.get(i);
					
			//OrderBySet
			order = new OrderBySet[1];
			order[0] = new OrderBySet(true, "WEIGHT", Protocol.OrderBySet.OP_NONE);

			//make QuerySet
			QuerySet querySet = new QuerySet(1);
			
			char[] startTag = "<b>".toCharArray();
			char[] endTag = "</b>".toCharArray();
			
			Query query = new Query(startTag, endTag);		

			query.setSelect(selectSet);
			query.setWhere(whereSet);
			query.setOrderby(order);			
			query.setSearchOption((byte)Protocol.SearchOption.CACHE | Protocol.SearchOption.STOPWORD | Protocol.SearchOption.BANNED);
			query.setThesaurusOption((byte)Protocol.ThesaurusOption.EQUIV_SYNONYM|(byte)Protocol.ThesaurusOption.QUASI_SYNONYM);	
			query.setFrom("SEARCH_D");
			
			query.setResult(0, 99);
			query.setDebug(true);
			query.setLoggable(true);
			query.setPrintQuery(true);
			
			querySet.addQuery(query);
			
			cmd = new CommandSearchRequest (search_ip, search_port);		
			
			try {
				rs = cmd.request(querySet);
			} catch (IRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(rs >= 0){
				results = cmd.getResultSet();
				resultlist = results.getResultList();
				
				if(resultlist !=null){
					result = resultlist[0];			
					if(result != null &&result.getTotalSize() > 0) 
					{
						for (int i=0; i < result.getRealSize(); i++) {
							Hashtable ht = new Hashtable();
							for(int k=0 ; k < result.getNumField() ; k++){			
								selectFieldName = new String((query.getSelectFields())[k].getField());							
		
								if(selectFieldName.equals("URL")){
									ht.put("URL", new String(result.getResult(i,k)) );		
								} else if(selectFieldName.equals("THUMBNAIL")){
									ht.put("THUMBNAIL", new String(result.getResult(i,k)) );						
								}else if(selectFieldName.equals("TITLE")){
									ht.put("TITLE", new String(result.getResult(i,k)) );			
								}else if(selectFieldName.equals("INTRODUCTION")){
									ht.put("INTRODUCTION", new String(result.getResult(i,k)) );				
								}else if(selectFieldName.equals("GOAL")){
									ht.put("GOAL", new String(result.getResult(i,k)) );			
								}
							}
							ht.put("size", result.getTotalSize());
							al.add(ht);
						} 
						return al;
					}
				}
			}
		}
		return null;
	}
}
