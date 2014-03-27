package org.uengine.jms.mw3.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.ide.form.CheckBoxField;
import org.uengine.codi.mw3.ide.form.CommonFormField;
import org.uengine.codi.mw3.ide.form.DateField;
import org.uengine.codi.mw3.ide.form.DropDownField;
import org.uengine.codi.mw3.ide.form.FileUploadField;
import org.uengine.codi.mw3.ide.form.Form;
import org.uengine.codi.mw3.ide.form.MultipleChoiceField;
import org.uengine.codi.mw3.ide.form.MultipleChoiceOption;
import org.uengine.codi.mw3.ide.form.ParagraphField;
import org.uengine.codi.mw3.ide.form.UserField;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.ReceiveActivity;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfBorderDictionary;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;
import com.itextpdf.text.pdf.RadioCheckField;
import com.itextpdf.text.pdf.TextField;

public class MakeJMSPdf {
	
	public static Font mainFont = new Font();
	public static Font catFont = new Font();
	public static Font subFont = new Font();
	public static Font smallBold = new Font(); 
	public static Font smallFont9 = new Font(); 
	public static BaseFont objBaseFont;
	
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
	transient ChapterSectionTOC chapterSectionTOC;
	@Hidden
		public ChapterSectionTOC getChapterSectionTOC() {
			return chapterSectionTOC;
		}
		public void setChapterSectionTOC(ChapterSectionTOC chapterSectionTOC) {
			this.chapterSectionTOC = chapterSectionTOC;
		}
		
	String convertedFilepath;
		public String getConvertedFilepath() {
			return convertedFilepath;
		}
		public void setConvertedFilepath(String convertedFilepath) {
			this.convertedFilepath = convertedFilepath;
		}
		
	public void load(String convertedFilepath) throws Exception{
		setConvertedFilepath(convertedFilepath);
		initFont();
		Document document = new Document(PageSize.A4);
		try{
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(convertedFilepath+ ".pdf"));
			pdfWriter.setLinearPageMode();
	        ChapterSectionTOC event = new ChapterSectionTOC();
	        pdfWriter.setPageEvent(event);
	        setChapterSectionTOC(event);
			setPdfWriter(pdfWriter);
			document.open();
			
			// 목차 생성
			addTableOfContents(document);
			
			
//			addTitle(document);
//			addFormWithImage(pdfWriter, document);
	        
//	        Image image1 = Image.getInstance(convertedFilepath + ".png");
//	        document.add(image1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			document.close();
		}
		 
	}
	
	public static void initFont() throws Exception{
		objBaseFont = BaseFont.createFont("org/uengine/codi/util/font/NanumMyeongjo.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		
		mainFont = new Font(objBaseFont , 24, Font.BOLD);
		catFont = new Font(objBaseFont , 18, Font.BOLD);
		subFont = new Font(objBaseFont, 16, Font.BOLD);
		smallBold = new Font(objBaseFont, 12, Font.BOLD);
		smallFont9 = new Font(objBaseFont, 12, Font.NORMAL);
	}
	
	/**
	 * 상단 네비게이터
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public void addTopNavigator(Document document, int pageNum) throws DocumentException, MalformedURLException, IOException {
		String processTitle = processViewPanel.processViewer.getTitle();
		
		float[] colsWidth = {1f, 1f, 2f, 1.3f, 1f, 1f};
		PdfPTable table = new PdfPTable(colsWidth);
	    table.setWidthPercentage(95);
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);
	    
	    // logo
	    URL url = Thread.currentThread().getContextClassLoader().getResource("org/uengine/codi/mw3/processexplorer/images/logo.png");
	    Image image = Image.getInstance(url.getPath());
	    PdfPCell thCell = new PdfPCell(image, false);
	    thCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		thCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    thCell.setRowspan(3);
	    thCell.setFixedHeight(60);
		table.addCell(thCell);
		
		// 발전소 이름
		thCell = new PdfPCell(new Paragraph("월 성 원 자 력 제 2 발 전 소" , subFont));
		thCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		thCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		thCell.setColspan(2);
		thCell.setRowspan(2);
		thCell.setFixedHeight(30);
		table.addCell(thCell);
		
		// 계통분류번호
		thCell = new PdfPCell(new Paragraph("계통분류번호" , smallFont9));
		thCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		thCell.setFixedHeight(18);
		table.addCell(thCell);
		
		// 개정번호
		thCell = new PdfPCell(new Paragraph("개정번호" , smallFont9));
		thCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(thCell);
		
		// 쪽 번호
		thCell = new PdfPCell(new Paragraph("쪽 번호" , smallFont9));
		thCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(thCell);
		
		// 공백 cell
		thCell = new PdfPCell();
		thCell.setFixedHeight(18);
		table.addCell(thCell);	// 계통분류번호
		table.addCell(thCell);	// 개정번호
		PdfPCell numberCell = new PdfPCell(new Paragraph( pageNum + "" , smallFont9));
		numberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(numberCell);	// 쪽 번호
		
		// 정기-081
		thCell = new PdfPCell(new Paragraph("정기-081" , smallBold));
		thCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		thCell.setFixedHeight(22);
		table.addCell(thCell);
		
		// 프로세스 title
		thCell = new PdfPCell(new Paragraph(processTitle , smallBold));
		thCell.setPaddingLeft(10);
		thCell.setColspan(4);
		table.addCell(thCell);
		
		document.add(table);
	}
	/**
	 * 목차 
	 * @throws Exception 
	 */
	public void addTableOfContents(Document document) throws Exception {
		
		boolean isFormFlag = false;
		
		JmsProcessDesignerContainer processDesignerContainer = (JmsProcessDesignerContainer)processViewPanel.processViewer.getProcessDesignerContainer();
		Documentation documentation = processDesignerContainer.getProcessDetailPanel().getDocumentation();
		
        WebObjectType objType = new WebObjectType(Documentation.class);
        Map<String, String> optionMap = objType.getFaceOptions();
        String fieldOrderValue = optionMap.get("fieldOrder");
        
        HashMap<String, WebFieldDescriptor> webFieldDescriptorMap = new HashMap<String, WebFieldDescriptor>(); 
        for(int i=0; i < objType.getFieldDescriptors().length; i++){
			WebFieldDescriptor fd = objType.getFieldDescriptors()[i];
			webFieldDescriptorMap.put(fd.getName(), fd);
        }
        
        Method chkm = documentation.getClass().getMethod("getPurpose", new Class[]{});
		Object chkContents = chkm.invoke(documentation, new Object[]{});
        if( chkContents != null && !"".equals(chkContents.toString())){
        	// 목적에 값이 있다면 이 부분은 전체가 보여야할 부분임
        	isFormFlag = true;
        }
        
        Paragraph menuParagraph = new Paragraph(35);
        Paragraph contentParagraph = new Paragraph(35);
        
        
		Paragraph preface = new Paragraph();
		preface.setFont(subFont);
		addEmptyLine(preface, 1);
		menuParagraph.add(preface);
		
		// 목차
		Chunk underline = new Chunk(" 목         차 " , mainFont);
		underline.setUnderline(0.2f, -2f);
		Paragraph listTitle = new Paragraph(underline);
		listTitle.setAlignment(Element.ALIGN_CENTER);
		menuParagraph.add(listTitle);
		addEmptyLine(preface, 1);
		menuParagraph.add(preface);
		
		// 항목, 내용, 쪽 번호
		Chunk sub1 = new Chunk("항목" , catFont);
		sub1.setUnderline(0.1f, -2f);
		Chunk sub2 = new Chunk("내 용" , catFont);
		sub2.setUnderline(0.1f, -2f);
		Chunk sub3 = new Chunk("쪽 번호" , catFont);
		sub3.setUnderline(0.1f, -2f);
		Paragraph subTitle = new Paragraph(25);
		subTitle.add(sub1);
		addEmptySpace(subTitle, 20);
		subTitle.add(sub2);
		addEmptySpace(subTitle, 80);
		subTitle.add(sub3);
        subTitle.setAlignment(Element.ALIGN_LEFT);
        menuParagraph.add(subTitle);
        
		if( fieldOrderValue != null ){
			int contentOrder = 1;
        	String[] fieldOrderValues = fieldOrderValue.split(",");
        	for(int i = 0; i < fieldOrderValues.length; i++){
        		
        		// ==== 컨텐츠 파트
        		String columnName = fieldOrderValues[i];
        		WebFieldDescriptor fd = webFieldDescriptorMap.get(columnName);
        		
        		Method m = documentation.getClass().getMethod("get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1), new Class[]{});
    			Object contents = m.invoke(documentation, new Object[]{});
    			if( contents == null || (contents != null && "".equals(contents)) ){
    				if( !"step".equals(columnName) ){	// 절차는 무조건 나옴
    					continue;
    				}
    			}
    			
    			String chapter1 = contentOrder +".0  " + fd.getDisplayName();
    			if( "activityDetail".equals(columnName)){
    				chapter1 = contentOrder +".0  " + "붙임";
    			}
        		Paragraph paragraph = new Paragraph();
        		paragraph.add(new Paragraph(chapter1, subFont));
        		Paragraph contentsParagraph = new Paragraph((String)contents, smallBold);
        		contentsParagraph.setIndentationLeft(30);
        		paragraph.add(contentsParagraph);
        		addEmptyLine(paragraph, 1);
        		
        		contentParagraph.add(paragraph);
        		if( !isFormFlag && "step".equals(columnName)){
        			// 절차 이고, 
        			addForms(contentParagraph , contentOrder);
        		}
        		
        		// ===  메뉴 파트
        		Chunk sub11 = new Chunk( contentOrder + ".0" , catFont);
        		chapter1 = fd.getDisplayName();
        		if( "activityDetail".equals(columnName)){
        			chapter1 = "붙임";
        		}
        		Chunk sub12 = new Chunk( chapter1 , catFont);
//        		Chunk sub13 = new Chunk("2" , catFont);
        		Paragraph subTitle1 = new Paragraph(35);
        		subTitle1.add(sub11);
        		addEmptySpace(subTitle1, contentOrder < 10 ? 20 : 17);
        		subTitle1.add(sub12);
        		addEmptySpace(subTitle1, 82);
//        		subTitle1.add(sub13);
        		subTitle1.setAlignment(Element.ALIGN_LEFT);
        		menuParagraph.add(subTitle1);
        		
        		contentOrder++;
        	}
        	
        	if( isFormFlag ){
        		// ===  메뉴 파트
        		Chunk sub11 = new Chunk( contentOrder + ".0" , catFont);
	    		Chunk sub12 = new Chunk( "부록" , catFont);
	//    		Chunk sub13 = new Chunk("2" , catFont);
	    		Paragraph subTitle1 = new Paragraph(35);
	    		subTitle1.add(sub11);
	    		addEmptySpace(subTitle1, contentOrder < 10 ? 20 : 17);
	    		subTitle1.add(sub12);
	    		addEmptySpace(subTitle1, 82);
	//    		subTitle1.add(sub13);
	    		subTitle1.setAlignment(Element.ALIGN_LEFT);
	    		menuParagraph.add(subTitle1);
	    		
	    		// ==== 컨텐츠 파트
	        	// 부록
	        	String others = contentOrder + ".0  " + "부록";
	        	
	        	Paragraph paragraph = new Paragraph();
	    		paragraph.add(new Paragraph(others, subFont));
	    		ArrayList<Activity> activityList = processDesignerContainer.getActivityList();
	    		int order = 1;
	    		for(Activity activity : activityList){
	    			Class paramClass = activity.getClass();
	    			boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
	    			if( isReceiveActivity ){
	    				String otherTitle = contentOrder +"." + order++ + "  " + activity.getDescription().getText() ;
			    		Paragraph contentsParagraph = new Paragraph(otherTitle, smallBold);
			    		contentsParagraph.setIndentationLeft(30);
			    		paragraph.add(contentsParagraph);
	    			}
	    		}
//	    		addEmptyLine(paragraph, 1);
	    		contentParagraph.add(paragraph);
	    		// 부록이 나온 후에 프로세스를 그린다.
	    		Image processImage = Image.getInstance(convertedFilepath + ".png");
	    		contentParagraph.add(processImage);
        	}
		}
		document.add(menuParagraph);
		document.newPage();
		document.add(contentParagraph);
        
	}
	public void addForms(Paragraph contentParagraph, int columnNum) throws Exception{
		ProcessDesignerContainer processDesignerContainer = processViewPanel.processViewer.getProcessDesignerContainer();
		ArrayList<Activity> activityList = processDesignerContainer.getActivityList();
		// 부록이 나온 후에 프로세스를 그린다.
		Image processImage = Image.getInstance(convertedFilepath + ".png");
		contentParagraph.add(processImage);
		
		int formIndex = 1;
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
							
							Paragraph formTitleName = new Paragraph( columnNum + "." + formIndex++ + " " + activity.getDescription().getText() , smallBold);
							formTitleName.setIndentationLeft(30);
							contentParagraph.add(formTitleName);
					    	
							ArrayList<CommonFormField> ffList = form.getFormFields();
							PdfWriter writer = getPdfWriter();
						    for(int j=0; j < ffList.size(); j++){
						    	CommonFormField formField = ffList.get(j);
						    	Paragraph formDisplayName = new Paragraph("(" + (j+1) + ") " +formField.getDisplayName() , smallBold);
						    	formDisplayName.setIndentationLeft(60);
						    	
						    	if(formField instanceof CheckBoxField || formField instanceof MultipleChoiceField){
						    		ArrayList<MultipleChoiceOption> choiceList = ((MultipleChoiceField) formField).getMultipleChoiceOptionPanel().getChoiceOptions();
									// checkBox
									Rectangle rect;
									PdfFormField radioField = PdfFormField.createRadioButton(writer, true);
									float[] colsWidth = {80, 20};
									PdfPTable checkboxTable = new PdfPTable(colsWidth);
									checkboxTable.setWidthPercentage(95);
									checkboxTable.setHorizontalAlignment(Element.ALIGN_CENTER);
									for(int k = 0 ; k < choiceList.size(); k++){
										PdfPCell checkBoxCell = new PdfPCell();
										MultipleChoiceOption multipleChoiceOption = choiceList.get(k);
										rect = new Rectangle(160, 815 - k * 30, 180, 797 - k * 30);
										RadioCheckField check = addRadioButton(writer, rect, radioField, multipleChoiceOption.getValue());
										check.setAlignment(Element.ALIGN_RIGHT);
										
										checkBoxCell.setCellEvent(new ChildFieldEvent(check.getCheckField(), 15, writer));
										checkboxTable.addCell(new Paragraph(multipleChoiceOption.getOption() , smallBold));
										checkboxTable.addCell(checkBoxCell);
									}
									formDisplayName.add(checkboxTable);
						    	}else{
						    		Chunk emptySpace = new Chunk("  ");
						    		Chunk underline = new Chunk("                             " , smallBold);
						    		underline.setUnderline(0.2f, -2f);
						    		
						    		formDisplayName.add(emptySpace);
						    		formDisplayName.add(underline);
						    	}
						    	contentParagraph.add(formDisplayName);
						    }
						}
					}
				}
			}
		}
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	    	paragraph.add(new Paragraph(" "));
	    }
	}
	private static void addEmptySpace(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Chunk(" "));
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
	
	class ChapterSectionTOC extends PdfPageEventHelper {
        /** List with the titles. */
        List<Paragraph> titles = new ArrayList<Paragraph>();
        public void onStartPage(PdfWriter writer, Document document){
        	int pageNum = document.getPageNumber();
        	try {
				addTopNavigator(document , pageNum);
				document.add(new Paragraph(" "));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        public void onChapter(PdfWriter writer, Document document,
                float position, Paragraph title) {
            titles.add(new Paragraph(title.getContent(), mainFont));
        }
 
        public void onChapterEnd(PdfWriter writer, Document document,
                float position) {
            drawLine(writer.getDirectContent(),
                    document.left(), document.right(), position - 5);
        }
 
        public void onSection(PdfWriter writer, Document document,
                float position, int depth, Paragraph title) {
            title = new Paragraph(title.getContent(), mainFont);
            title.setIndentationLeft(18 * depth);
            titles.add(title);
        }
 
        public void onSectionEnd(PdfWriter writer, Document document,
                float position) {
            drawLine(writer.getDirectContent(),
                    document.left(), document.right(), position - 3);
        }
 
        public void drawLine(PdfContentByte cb, float x1, float x2, float y) {
            cb.moveTo(x1, y);
            cb.lineTo(x2, y);
            cb.stroke();
        }
    }
	/**
	 * 사용안함 - 참고용으로 보시요 
	 */
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
	
	/**
	 * 사용안함 - 참고용으로 보시요 
	 */
	private void createFormTable(Document document, Form form, String title) throws MalformedURLException, IOException, DocumentException {
		float[] colsWidth = {1f, 2f};
	    PdfPTable table = new PdfPTable(colsWidth);
	    table.setWidthPercentage(95);
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);
	    
	    PdfPCell thCell = new PdfPCell(new Paragraph(title , subFont));
	    thCell.setColspan(2);
	    thCell.setFixedHeight(35);
		table.addCell(thCell);
			
	    PdfWriter writer = getPdfWriter();
	    PdfFormField pdfFormField = PdfFormField.createEmpty(getPdfWriter());
	    
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
			}else if(formField instanceof DateField || formField instanceof FileUploadField || formField instanceof UserField){
				String buttonName = "";
				PushbuttonField submitBtn = new PushbuttonField(writer, 	new Rectangle(0, 0, 35, 15), formField.getName());
				if(formField instanceof DateField){
					buttonName = "Date";
				}else if(formField instanceof FileUploadField){
					buttonName = "File";
				}else if(formField instanceof UserField){
					buttonName = "User";
				}
				submitBtn.setBackgroundColor(BaseColor.LIGHT_GRAY);
				submitBtn.setBorderStyle(PdfBorderDictionary.STYLE_BEVELED);
				submitBtn.setText(buttonName);
				submitBtn.setOptions(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
				PdfFormField submitField = submitBtn.getField();
				
				cell.setCellEvent(new FieldCell(submitField, 35, writer));
				table.addCell(cell);
			}else if( formField instanceof ParagraphField){
				TextField comment = new TextField(writer, new Rectangle(0, 0,200,	100), formField.getName());
					comment.setBorderColor(BaseColor.BLACK);
					comment.setBorderWidth(1);
					comment.setBorderStyle(PdfBorderDictionary.STYLE_SOLID);
					comment.setText("");
					comment.setOptions(TextField.MULTILINE | TextField.DO_NOT_SCROLL);
					cell.setMinimumHeight(100);
					cell.setCellEvent(new FieldCell(comment.getTextField(), 200, writer));
					table.addCell(cell);
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
}
