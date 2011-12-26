package org.metaworks.example.ide;

import org.metaworks.annotation.Face;

public class DatabaseActivity extends Activity{
	
	String sqlStmt;
		@Face(ejsPath="genericfaces/SourceEditor.ejs")
		public String getSqlStmt() {
			return sqlStmt;
		}
	
		public void setSqlStmt(String sqlStmt) {
			this.sqlStmt = sqlStmt;
		}
}