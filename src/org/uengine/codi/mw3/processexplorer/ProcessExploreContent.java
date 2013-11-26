package org.uengine.codi.mw3.processexplorer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.Download;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.ParameterValue;
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
import org.uengine.kernel.designer.web.ActivityView;
import org.uengine.processmanager.ProcessManagerRemote;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class ProcessExploreContent{
	
	public static Font catFont = new Font();
	public static Font subFont = new Font();
	public static Font smallBold = new Font(); 
			
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
			return new Object[]{new Remover(new ModalWindow() , true) , new Remover(new Popup() , true), new Refresh(instanceListPanel), new Refresh(instanceViewContent)};
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] startPrint() throws Exception{
		
		return new Object[]{};
	}
	
	public static void initFont() throws Exception{
		BaseFont objBaseFont = BaseFont.createFont("org/uengine/codi/util/font/NanumMyeongjo.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
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
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(convertedFilepath+ ".pdf"));
		document.open();
		
		addTitle(document);
		addForm(pdfWriter, document);
        
        Image image1 = Image.getInstance(convertedFilepath + ".png");
        document.add(image1);
        
        document.close();
		return new Object[]{new Download(new FileTransfer(new String(convertedFilename.getBytes("UTF-8"),"ISO8859_1"), "application/pdf", new FileInputStream(convertedFilepath+ ".pdf")))};
		
	}
	
	public void addTitle(Document document) throws DocumentException {
		ProcessDesignerContainer processDesignerContainer = processViewPanel.processViewer.getProcessDesignerContainer();
		Documentation documentation = processDesignerContainer.getProcessDetailPanel().getDocumentation();
		
		
		String chapter1 = "1.0  목 적";
		String contents1 = documentation.getPurpose().getContents();
		Anchor anchor = new Anchor(chapter1, subFont);
	    anchor.setName(chapter1);
	    // Second parameter is the number of the chapter
		
		String chapter2 = "2.0  참 조";
		String contents2 = documentation.getReference().getContents();
		
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		
		preface.add(new Paragraph(chapter1, subFont));
		preface.add(new Paragraph(contents1, smallBold));
		addEmptyLine(preface, 1);
		
		preface.add(new Paragraph(chapter2, subFont));
		preface.add(new Paragraph(contents2, smallBold));
		addEmptyLine(preface, 1);
		
		
		document.add(preface);
	}
	
	public void addForm(PdfWriter pdfWriter , Document document) throws DocumentException, IOException {
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
							String typeId = ((ComplexType)processVariable.getDefaultValue()).getTypeId();
							String imgFilePath = CodiClassLoader.mySourceCodeBase(); 
							String imgFileName = (typeId.substring(1, typeId.lastIndexOf("]"))).replace('.', File.separatorChar) + ".png";
							Paragraph pragraph = new Paragraph();
							createTable(pragraph);
//							System.out.println(imgFilePath + File.separatorChar + imgFileName);
							try{
								Image image1 = Image.getInstance(imgFilePath + File.separatorChar + imgFileName);
								pragraph.add(image1);
							}catch(Exception e){
								
							}
							document.add(pragraph);
						}
					}
				}
			}
		}
	}
	
	private void createTable(Paragraph pragraph) throws BadElementException {
	    PdfPTable table = new PdfPTable(4);
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);
	    // Data
	    PdfPCell cell = new PdfPCell();
	    table.addCell("부록 13.1");
	    table.addCell(new Phrase("냉각재계통 액체발출밸브 점검" , smallBold) );
	    table.addCell(new Phrase("쪽 번호" , smallBold) );
	    table.addCell("1 / 5");
	    pragraph.add(table);
	}
	
//	private static void addContent(Document document) throws DocumentException {
//		
//	    Anchor anchor = new Anchor("First Chapter", catFont);
//	    anchor.setName("First Chapter");
//
//	    // Second parameter is the number of the chapter
//	    Chapter catPart = new Chapter(new Paragraph(anchor), 1);
//
//	    Paragraph subPara = new Paragraph("Subcategory 1", subFont);
//	    Section subCatPart = catPart.addSection(subPara);
//	    subCatPart.add(new Paragraph("Hello"));
//
//	    subPara = new Paragraph("Subcategory 2", subFont);
//	    subCatPart = catPart.addSection(subPara);
//	    subCatPart.add(new Paragraph("Paragraph 1"));
//	    subCatPart.add(new Paragraph("Paragraph 2"));
//	    subCatPart.add(new Paragraph("Paragraph 3"));
//
//	    // add a list
//	    createList(subCatPart);
//	    Paragraph paragraph = new Paragraph();
//	    addEmptyLine(paragraph, 5);
//	    subCatPart.add(paragraph);
//
//	    // add a table
////	    createTable(subCatPart);
//
//	    // now add all this to the document
//	    document.add(catPart);
//
//	    // Next section
//	    anchor = new Anchor("Second Chapter", catFont);
//	    anchor.setName("Second Chapter");
//
//	    // Second parameter is the number of the chapter
//	    catPart = new Chapter(new Paragraph(anchor), 1);
//
//	    subPara = new Paragraph("Subcategory", subFont);
//	    subCatPart = catPart.addSection(subPara);
//	    subCatPart.add(new Paragraph("This is a very important message"));
//
//	    // now add all this to the document
//	    document.add(catPart);
//
//	  }
//	
	
//	private static void createList(Section subCatPart) {
//	    List list = new List(true, false, 10);
//	    list.add(new ListItem("First point"));
//	    list.add(new ListItem("Second point"));
//	    list.add(new ListItem("Third point"));
//	    subCatPart.add(list);
//	  }

	  private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	    	paragraph.add(new Paragraph(" "));
	    }
	  }
}
