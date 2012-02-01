package org.uengine.codi.mw3.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.example.ide.CompileError;
import org.uengine.codi.mw3.model.EntitySourceCode;

public class EntityQuery {
	
	public EntityQuery(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		setQueryCode(new EntitySourceCode());
	}
	
	EntitySourceCode queryCode;
		public EntitySourceCode getQueryCode() {
			return queryCode;		}
	
		public void setQueryCode(EntitySourceCode queryCode) {
			this.queryCode = queryCode;
		}

	@ServiceMethod(callByContent=true)
	public void run() throws Exception{
			if(getQueryCode().getCode()==null) return;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = TransactionContext.getThreadLocalInstance().getConnection();				
				pstmt = conn.prepareStatement(getQueryCode().getCode());
				pstmt.execute();
			} catch (Exception e) {
				// TODO we need to report the error properly
				String message = e.getMessage();
				String[] parts = null;
				int lineNumber = 0;
				
				try {
					lineNumber = Integer.parseInt((parts = message.split("at line "))[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					parts = null;
					//e1.printStackTrace();
				}
				
				CompileError compileError = new CompileError();
				compileError.setLine(lineNumber);
				compileError.setColumn(1);
				compileError.setMessage(parts != null ? parts[0] : message);
				
				//getCreateSQL().setCompileErrors(new CompileError[]{compileError});
			
				e.printStackTrace();
			}finally{
				if(conn != null){
					conn.close();
					conn = null;
				}
				
				try {
					if(pstmt != null){
						pstmt.close();
						pstmt = null;
					}
				} catch (SQLException sqle){						
				}				
			}			
	}
	
	transient MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	} 
	
}
