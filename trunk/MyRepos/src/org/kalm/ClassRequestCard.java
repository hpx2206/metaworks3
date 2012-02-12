package org.kalm;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.codi.mw3.model.ClassDesignerContentPanel;
import java.io.Serializable;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.alm.QualityOption;
import org.uengine.codi.mw3.alm.PMDRuleOption;

@Face(ejsPath="genericfaces/Tab.ejs")
public class ClassRequestCard implements Serializable, NeedArrangementToSerialize{

	java.lang.String requirement;
    @Face(ejsPath="genericfaces/richText.ejs")
		public java.lang.String getRequirement(){ return requirement; }
		public void setRequirement(java.lang.String requirement){ this.requirement = requirement; }

    ClassDefinition classDefinition;
        public ClassDefinition getClassDefinition(){
            return this.classDefinition;
        }
        public void setClassDefinition(ClassDefinition classDefinition){
            this.classDefinition = classDefinition;
        }
        
    PMDRuleOption pmdRuleOption;
        public PMDRuleOption getPMDRuleOption(){return pmdRuleOption;}
        public void setPMDRuleOption(PMDRuleOption pmdRuleOption){this.pmdRuleOption = pmdRuleOption;}
        
    public ClassRequestCard(){
        setClassDefinition(new ClassDefinition());
        setPMDRuleOption(new PMDRuleOption());
    }
    
    @Override
	public void beforeSerialization() {
		if(classDefinition.getAlias()==null)
			throw new RuntimeException("Class should be compiled once.");
	}

    @Override
    public void afterDeserialization() {
		
		if(org.uengine.util.UEngineUtil.isNotEmpty(classDefinition.getAlias())){
	        setPMDRuleOption(new PMDRuleOption());

			try {
				classDesignerContentPanel.load("["+classDefinition.getAlias()+"]");
                classDefinition = classDesignerContentPanel.getClassDefinition();
                classDefinition.setQualityOption(new QualityOption());
                classDefinition.getQualityOption().setPmdRuleOption(getPMDRuleOption());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}
    
    @Autowired
    public ClassDesignerContentPanel classDesignerContentPanel;
 
}