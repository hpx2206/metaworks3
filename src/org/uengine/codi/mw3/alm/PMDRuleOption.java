package org.uengine.codi.mw3.alm;

public class PMDRuleOption{
	
	public PMDRuleOption(){
		setBasic(true);
		setImports(false);
		setUnusedcode(false);
	}

	Boolean basic;
		public Boolean getBasic(){ return basic; }
		public void setBasic(Boolean basic){ this.basic = basic; }

	Boolean imports;
		public Boolean getImports(){ return imports; }
		public void setImports(Boolean imports){ this.imports = imports; }

	Boolean unusedcode;
		public Boolean getUnusedcode(){ return unusedcode; }
		public void setUnusedcode(Boolean unusedcode){ this.unusedcode = unusedcode; }

	public String generatePMDOption(){
		StringBuffer options = new StringBuffer();
		
		if(basic)
			options.append(",basic");
		
		if(imports)
			options.append(",imports");
		
		if(unusedcode)
			options.append(",unusedcode");
	
		String optionStr = options.toString();
		
		if(optionStr.length() > 1)
			return optionStr.substring(1);
		
		return null;
	}
}