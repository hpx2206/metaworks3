package org.uengine.dbrepo;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class QueryResult {
	public QueryResult() {
		errorCode = 0;
	}

	public QueryResult(ResultSet rs) {
		this();
		SetFieldByResultSet(rs);
	}

	@SuppressWarnings("unchecked")
	private void SetFieldByResultSet(ResultSet rs) {
		try {
//			ConvertResult convertResult = ConvertHelper.ConvertToList(rs);
//			if(convertResult.errorCode == 2)
//			{
//				this.errorCode = 2;
//				this.errorMsg = String.format("최대 쿼리 조회건수 오류 %d", ConvertHelper.MAXPERQUERYCOUNT);
//			}
//			this.listResult = (List<Map<String, String>>)convertResult.result;
		} catch (Exception e) {
			this.errorCode = 1;
			this.errorMsg = e.getMessage();
		}
	}


	public List<Map<String, String>> listResult;

	public int errorCode;

	public String errorMsg;
}
