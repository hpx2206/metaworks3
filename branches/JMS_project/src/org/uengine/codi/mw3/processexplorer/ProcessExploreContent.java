package org.uengine.codi.mw3.processexplorer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.directwebremoting.io.FileTransfer;
import org.metaworks.FieldDescriptor;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.website.Download;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.form.CheckBoxField;
import org.uengine.codi.mw3.ide.form.CommonFormField;
import org.uengine.codi.mw3.ide.form.DropDownField;
import org.uengine.codi.mw3.ide.form.Form;
import org.uengine.codi.mw3.ide.form.MultipleChoiceField;
import org.uengine.codi.mw3.ide.form.MultipleChoiceOption;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.ImageMagick;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContentPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDetailPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessSummaryPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.Activity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.ReceiveActivity;
import org.uengine.processmanager.ProcessManagerRemote;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfBorderDictionary;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;
import com.itextpdf.text.pdf.TextField;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class ProcessExploreContent{
	
	public static Font catFont = new Font();
	public static Font subFont = new Font();
	public static Font smallBold = new Font(); 
	public static BaseFont objBaseFont;
			
	@AutowiredFromClient
	transient public Session session;
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	String svgData;
		public String getSvgData() {
			return svgData;
		}
		public void setSvgData(String svgData) {
			this.svgData = svgData;
		}

	ProcessViewPanel processViewPanel;
		public ProcessViewPanel getProcessViewPanel() {
			return processViewPanel;
		}
		public void setProcessViewPanel(ProcessViewPanel processViewPanel) {
			this.processViewPanel = processViewPanel;
		}
	ProcessDetailPanel processDetailPanel;
		public ProcessDetailPanel getProcessDetailPanel() {
			return processDetailPanel;
		}
		public void setProcessDetailPanel(ProcessDetailPanel processDetailPanel) {
			this.processDetailPanel = processDetailPanel;
		}
	ProcessSummaryPanel processSummaryPanel;
		public ProcessSummaryPanel getProcessSummaryPanel() {
			return processSummaryPanel;
		}
		public void setProcessSummaryPanel(ProcessSummaryPanel processSummaryPanel) {
			this.processSummaryPanel = processSummaryPanel;
		}
		
	transient PdfWriter pdfWriter;
	@Hidden
		public PdfWriter getPdfWriter() {
			return pdfWriter;
		}
		public void setPdfWriter(PdfWriter pdfWriter) {
			this.pdfWriter = pdfWriter;
		}
		
	public ProcessExploreContent(){
		processViewPanel = new ProcessViewPanel();
		processDetailPanel = new ProcessDetailPanel();
		processSummaryPanel = new ProcessSummaryPanel();
	}
	
	public void load() throws Exception{
		processViewPanel.setDefId(defId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
		
		ProcessDesignerContainer processDesignerContainer = processViewPanel.processViewer.getProcessDesignerContainer();
		
		processDetailPanel.setDocumentation(processDesignerContainer.getProcessDetailPanel().getDocumentation());
		getProcessDetailPanel().getMetaworksContext().setHow("snsView");
		getProcessDetailPanel().getDocumentation().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		processSummaryPanel.setDetailList(processDesignerContainer.getProcessSummaryPanel().getDetailList());
		getProcessSummaryPanel().getMetaworksContext().setHow("snsView");
		getProcessSummaryPanel().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
	}
	
	@Autowired
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public NewInstancePanel newInstancePanel;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] startProcess() throws Exception{
		instanceViewContent.session = session;
		
		ProcessMap processMap = new ProcessMap();
		processMap.processManager = processManager;
		processMap.session = session;
		processMap.instanceView = instanceViewContent;
		processMap.setDefId(alias);
		
		String instId = processMap.initializeProcess();
				
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, alias, session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		processManager.executeProcess(instId);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
		instance.databaseMe();
		 
		instance.databaseMe().setInitiator(session.getUser());
		instance.databaseMe().setInitComCd(session.getCompany().getComCode());
		
		if( instanceViewContent == null ){
			instanceViewContent = new InstanceViewContent();
		}
		instanceViewContent.session = session;
		instanceViewContent.load(instance);
		
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session); //should return instanceListPanel not the instanceList only since there're one or more instanceList object in the client-side
		instanceListPanel.session = session;
		instanceListPanel.setNewInstancePanel(newInstancePanel);
		instanceListPanel.getInstanceList().load();

		if("sns".equals(session.getEmployee().getPreferUX())){
			return new Object[]{new Remover(new ModalWindow() , true) , new Refresh(instanceListPanel)};
		}else{
			return new Object[]{new Remover(new ModalWindow() , true) , new Remover(new Popup() , true), new Refresh(instanceViewContent)};
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] startPrint() throws Exception{
		
		return new Object[]{};
	}
	
	public static void initFont() throws Exception{
		objBaseFont = BaseFont.createFont("org/uengine/codi/util/font/NanumMyeongjo.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		catFont = new Font(objBaseFont , 18, Font.BOLD);
		subFont = new Font(objBaseFont, 16, Font.BOLD);
		smallBold = new Font(objBaseFont, 12, Font.BOLD);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] downloadPdf() throws Exception{
		initFont();
		
		String processName = this.getDefId();
		String convertedFilename = processName.replace(".", "@").split("@")[0];
		String convertedFilepath = GlobalContext.getPropertyString("filesystem.path") + File.separatorChar + convertedFilename;
		
		BufferedWriter writer = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			File file = new File(convertedFilepath + ".svg");
			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			fos = new FileOutputStream(convertedFilepath + ".svg");
			osw = new OutputStreamWriter(fos, "UTF-8");
			writer = new BufferedWriter(osw);
			writer.write(ProcessDesignerContentPanel.unescape(this.getSvgData()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(osw != null)
				try {
					osw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		ImageMagick im = new ImageMagick();
		im.convertFile(convertedFilepath + ".svg", convertedFilepath + ".png");
		
		
		Document document = new Document(PageSize.A4);
		try{
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(convertedFilepath+ ".pdf"));
			setPdfWriter(pdfWriter);
			document.open();
			
			addTitle(document);
			addFormWithImage(pdfWriter, document);
	        
	        Image image1 = Image.getInstance(convertedFilepath + ".png");
	        document.add(image1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			document.close();
		}
		return new Object[]{new Download(new FileTransfer(new String(convertedFilename.getBytes("UTF-8"),"ISO8859_1"), "application/pdf", new FileInputStream(convertedFilepath+ ".pdf")))};
		
	}
	
	public void addTitle(Document document) throws DocumentException {
		ProcessDesignerContainer processDesignerContainer = processViewPanel.processViewer.getProcessDesignerContainer();
		Documentation documentation = processDesignerContainer.getProcessDetailPanel().getDocumentation();
		
		
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		
		String chapter1 = "1.0  목 적";
		String contents1 = documentation.getPurpose();
		preface.add(new Paragraph(chapter1, subFont));
		preface.add(new Paragraph(contents1, smallBold));
		addEmptyLine(preface, 1);
		
		
		String chapter2 = "2.0  참 조";
		String contents2 = documentation.getReference();
		preface.add(new Paragraph(chapter2, subFont));
		preface.add(new Paragraph(contents2, smallBold));
		addEmptyLine(preface, 1);
		
		String chapter3 = "3.0  책 임";
		String contents3 = documentation.getResponsibility();
		preface.add(new Paragraph(chapter3, subFont));
		preface.add(new Paragraph(contents3, smallBold));
		addEmptyLine(preface, 1);
		
		String chapter4 = "4.0  장 비";
		String contents4 = documentation.getEquipment();
		preface.add(new Paragraph(chapter4, subFont));
		preface.add(new Paragraph(contents4, smallBold));
		addEmptyLine(preface, 1);
		
		String chapter5 = "5.0  자격요건";
		String contents5 = documentation.getRequirement();
		preface.add(new Paragraph(chapter5, subFont));
		preface.add(new Paragraph(contents5, smallBold));
		addEmptyLine(preface, 1);
		
		String chapter6 = "6.0  판정기준";
		String contents6 = documentation.getIndicationStandard();
		preface.add(new Paragraph(chapter6, subFont));
		preface.add(new Paragraph(contents6, smallBold));
		addEmptyLine(preface, 1);
		
		String chapter7 = "7.0  초기조건";
		String contents7 = documentation.getInitialCondition();
		preface.add(new Paragraph(chapter7, subFont));
		preface.add(new Paragraph(contents7, smallBold));
		addEmptyLine(preface, 1);
		
		
		document.add(preface);
	}
	
	public void addFormWithImage(PdfWriter pdfWriter , Document document) throws Exception {
		ProcessDesignerContainer processDesignerContainer = processViewPanel.processViewer.getProcessDesignerContainer();
		ArrayList<Activity> activityList = processDesignerContainer.getActivityList();
		for(Activity activity : activityList){
			Class paramClass = activity.getClass();
			boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
			if( isReceiveActivity ){
				ParameterContext[] contexts = ((ReceiveActivity)activity).getParameters();
				if( contexts != null && contexts.length > 0){
					for(int i=0; i < contexts.length; i++){
						ProcessVariable processVariable = contexts[i].getVariable();
						if( processVariable.getDefaultValue() != null && processVariable.getDefaultValue() instanceof ComplexType ){
							ComplexType complexType = ((ComplexType)processVariable.getDefaultValue());
							complexType.setDesignerMode(false);
							Class ComplexTypeClass = complexType.getTypeClass();
							
							Form form = new Form();
							form.setId(ComplexTypeClass.getName());
							form.setPackageName(ComplexTypeClass.getPackage() != null ? ComplexTypeClass.getPackage().getName() : null );
							form.load();
							
							
//							WebObjectType srcWOT = MetaworksRemoteService.getInstance().getMetaworksType(ComplexTypeClass.getName());
//							ObjectInstance srcInstance = (ObjectInstance) srcWOT.metaworks2Type().createInstance();
//							srcInstance.setObject(this);
							Paragraph preface = new Paragraph();
							addEmptyLine(preface, 2);
							document.add(preface);
							
							createFormTable(document , form , activity.getDescription().getText());
							
							/**
							 * 아래 주석은 이미지를 넣는 방식... 이미지가 제대로 변환이 안되는 문제로 우선 주석 처리함 - 12.09
							String imgFilePath = CodiClassLoader.mySourceCodeBase(); 
							String imgFileName = (typeId.substring(1, typeId.lastIndexOf("]"))).replace('.', File.separatorChar) + ".png";
							File imageFile = new File(imgFilePath + File.separatorChar + imgFileName);
							if( imageFile.exists() ){
								Image image1 = Image.getInstance(imgFilePath + File.separatorChar + imgFileName);
								document.add(image1);
							}else{
								for(int j=0; j < 100; j++){
									imgFileName = (typeId.substring(1, typeId.lastIndexOf("]"))).replace('.', File.separatorChar) +"-"+ j +".png";
									imageFile = new File(imgFilePath + File.separatorChar + imgFileName);
									if( imageFile.exists() ){
										Image image1 = Image.getInstance(imgFilePath + File.separatorChar + imgFileName);
										document.add(image1);
									}else{
										break;
									}
								}
							}
							 */
							
						}
					}
				}
			}
		}
	}
	
	private void createFormTable(Document document, Form form, String title) throws MalformedURLException, IOException, DocumentException {
		float[] colsWidth = {1f, 2f};
	    PdfPTable table = new PdfPTable(colsWidth);
	    table.setWidthPercentage(95);
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);
	    
	    PdfPCell thCell = new PdfPCell(new Paragraph(title , subFont));
	    thCell.setColspan(2);
		table.addCell(thCell);
			
	    PdfWriter writer = getPdfWriter();
	    PdfFormField pdfFormField = PdfFormField.createEmpty(getPdfWriter());
	    PdfContentByte cb = writer.getDirectContent();
	    
	    ArrayList<CommonFormField> ffList = form.getFormFields();
	    for(int i=0; i < ffList.size(); i++){
//			FieldDescriptor fd = wot.metaworks2Type().getFieldDescriptors()[i];
//	    	WebFieldDescriptor fd = wot.getFieldDescriptors()[i];
	    	CommonFormField formField = ffList.get(i);
			String thName = formField.getDisplayName() != null ? formField.getDisplayName() : formField.getName(); 
			table.addCell(new Phrase(thName , smallBold) );
			
			PdfPCell cell = new PdfPCell();
			cell.setFixedHeight(25);
			
			if( formField instanceof DropDownField){
				ArrayList<MultipleChoiceOption> choiceList = ((MultipleChoiceField) formField).getMultipleChoiceOptionPanel().getChoiceOptions();
				String[] dropDownArray = new String[choiceList.size()];
				for(int j = 0 ; j < choiceList.size(); j++){
					MultipleChoiceOption multipleChoiceOption = choiceList.get(j);
					dropDownArray[j] = multipleChoiceOption.getOption();
				}
				PdfFormField dropDown = PdfFormField.createCombo(writer, true, dropDownArray, 0);
				dropDown.setWidget(new Rectangle(50, 785, 120, 800),
						PdfAnnotation.HIGHLIGHT_INVERT);
				dropDown.setFieldName(formField.getName());
				dropDown.setMKBorderColor(BaseColor.BLACK);
				cell.setCellEvent(new FieldCell(dropDown, 200, writer));
				table.addCell(cell);
				// select Box
			}else if(formField instanceof CheckBoxField || formField instanceof MultipleChoiceField){
				ArrayList<MultipleChoiceOption> choiceList = ((MultipleChoiceField) formField).getMultipleChoiceOptionPanel().getChoiceOptions();
				// checkBox
				Rectangle rect;
				PdfFormField radioField = PdfFormField.createRadioButton(writer, true);
				PdfPTable checkboxTable = new PdfPTable(colsWidth);
				for(int j = 0 ; j < choiceList.size(); j++){
					PdfPCell checkBoxCell = new PdfPCell();
					MultipleChoiceOption multipleChoiceOption = choiceList.get(j);
					rect = new Rectangle(160, 815 - j * 30, 180, 797 - j * 30);
					RadioCheckField check = addRadioButton(writer, rect, radioField, multipleChoiceOption.getValue());
					checkBoxCell.setCellEvent(new FieldCell(check.getCheckField(), 15, writer));
					checkboxTable.addCell(checkBoxCell);
					checkboxTable.addCell(new Paragraph(multipleChoiceOption.getOption() , smallBold));
				}
				table.addCell(checkboxTable);
			}else{
				TextField nameField = new TextField(writer, new Rectangle(0,0,200,20), formField.getName());
				nameField.setFieldName(formField.getName());
				nameField.setFont(objBaseFont);
				nameField.setBackgroundColor(BaseColor.WHITE);
				nameField.setBorderColor(BaseColor.BLACK);
				nameField.setBorderWidth(1);
				nameField.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
				nameField.setAlignment(Element.ALIGN_LEFT);
				
				cell.setCellEvent(new FieldCell(nameField.getTextField(), 200, writer));
				table.addCell(cell);
			}
				
	    }
	    // Data
	    getPdfWriter().addAnnotation(pdfFormField);
	    document.add(table);
	}
	
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	    	paragraph.add(new Paragraph(" "));
	    }
	}
	  
	  class DefaultFontProvider extends FontFactoryImp {
		  // I believe this is the correct override, but there are quite a few others.
		  public Font getFont(String fontname,String encoding, boolean embedded, float size,int style, BaseColor color) {
			  try {
				  BaseFont objBaseFont = BaseFont.createFont("org/uengine/codi/util/font/NanumMyeongjo.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				  return new Font(objBaseFont, 10, style, BaseColor.BLACK);
			  } catch (DocumentException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  } catch (IOException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
			  return null;
		  }
	  }
	  class ChildFieldEvent implements PdfPCellEvent {
		  
		  	PdfFormField formField;
			PdfWriter writer;
			int width;
			
			public ChildFieldEvent(PdfFormField formField, int width, 
				PdfWriter writer){
				this.formField = formField;
				this.width = width;
				this.writer = writer;
			}
			
		    /**
		     * Add the child field to the parent, and sets the coordinates of the child field.
		     * @see com.lowagie.text.pdf.PdfPCellEvent#cellLayout(com.lowagie.text.pdf.PdfPCell,
		     *      com.lowagie.text.Rectangle, com.lowagie.text.pdf.PdfContentByte[])
		     */
		    public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
		        try {
		        	// delete cell border
					PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
					cb.reset();
		        	
					formField.setWidget(
							new Rectangle(rect.getLeft(5), 
								rect.getBottom(2), 
								rect.getLeft(5)+width, 
								rect.getTop(2)), 
								PdfAnnotation
								.HIGHLIGHT_NONE);
					writer.addAnnotation(formField);
		        } catch (Exception e) {
		            throw new ExceptionConverter(e);
		        }
		    }
		}
	  class FieldCell implements PdfPCellEvent{
			
			PdfFormField formField;
			PdfWriter writer;
			int width;
			
			public FieldCell(PdfFormField formField, int width, 
				PdfWriter writer){
				this.formField = formField;
				this.width = width;
				this.writer = writer;
			}
			
			public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas){
				
				try{
					formField.setWidget(
						new Rectangle(rect.getLeft(3), 
							rect.getBottom(3), 
							rect.getLeft(3)+width, 
							rect.getTop(3)), 
							PdfAnnotation
							.HIGHLIGHT_NONE);
					
					writer.addAnnotation(formField);
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
	  
	  private static RadioCheckField addRadioButton(PdfWriter writer, Rectangle rect,	PdfFormField radio, String name) 
			throws IOException, DocumentException {
		RadioCheckField check = 
			new RadioCheckField(writer, rect, name , "Yes");
		check.setCheckType(RadioCheckField.TYPE_CHECK);
		check.setBorderColor(BaseColor.BLACK);
		radio.addKid(check.getRadioField());
		
		return check;
	}	
	
}
