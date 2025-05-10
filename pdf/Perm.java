package in.gov.cgg.scheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Perm {
    public static void main(String[] args) {
        // Define the path to the output PDF file
        String dest = "C:/Uploads/Test/sample_pdf_with_borders.pdf";

        try {
            // Create a File object for the directory
            File file = new File(dest).getParentFile();
            if (!file.exists()) {
                file.mkdirs(); // Create the directory if it doesn't exist
            }

            // Create a Document instance
            Document document = new Document();

            // Create a PdfWriter instance
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));

            // Open the document
            document.open();

            
				

				Paragraph paragraph = new Paragraph(" ");
				paragraph.setAlignment(Element.ALIGN_LEFT);
				paragraph.setIndentationLeft(25);
				document.add(paragraph);
				
				String commissionerOrPanchayatSecretary = "";
 

				
				PdfPTable qrtable = new PdfPTable(18);
				qrtable.setTotalWidth(100);
				qrtable.setWidthPercentage(100);
				qrtable.setHorizontalAlignment(Element.ALIGN_CENTER);

				//PdfPCell qrcel = new PdfPCell(); 
				PdfPCell qrcell = new PdfPCell();
				PdfPCell qrcell2 = new PdfPCell(); 

				qrcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				qrcell.setBorder(0);
				qrcell.setColspan(14);
				
				paragraph = new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN,13, Font.BOLD, BaseColor.BLACK));
				paragraph.setAlignment(Element.ALIGN_CENTER);
				qrcell.addElement(paragraph);
				paragraph = new Paragraph(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN,13, Font.BOLD, BaseColor.BLACK));
				paragraph.setAlignment(Element.ALIGN_CENTER);
				qrcell.addElement(paragraph);
				paragraph = new Paragraph("OFFICE OF THE GRAMPANCHAYAT, "+"sample"+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,13, Font.BOLD, BaseColor.BLACK));
				paragraph.setAlignment(Element.ALIGN_CENTER);
				qrcell.addElement(paragraph);
				paragraph = new Paragraph("Mandal: "+"sample"+", District: "+"sample"+"", FontFactory.getFont(FontFactory.TIMES_ROMAN,13, Font.BOLD, BaseColor.BLACK));
				paragraph.setAlignment(Element.ALIGN_CENTER);
				qrcell.addElement(paragraph);
				qrtable.addCell(qrcell);
				
				qrcell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
				qrcell2.setBorder(0);
				qrcell2.setColspan(4);
			
				
			//	GenerateQrCode generateQrCode = new GenerateQrCode();
				
				 HashMap<String, String> qrData = new  HashMap<String, String>();
				
				 qrData.put("name_of_applicant", "sample");
				 qrData.put("department_name", "sample");
				 qrData.put("application_id","sample");
				 qrData.put("approval", "Application Deemed Approved");
				
				 		
//				document.add(qrtable);
				
				String app_type="";
				 
					app_type="Mobile Tower";
			 
				
	      
	      Paragraph paragraph3=new Paragraph("Present: Sri./Smt./Kum."+"sample"+" ("+"sample"+")"
	      		+ "",FontFactory.getFont(FontFactory.TIMES_ROMAN,13, Font.BOLD, BaseColor.BLACK));
	      paragraph3.setAlignment(Element.ALIGN_CENTER);
	      
	      
	    
	      
	      Paragraph paragraph4=new Paragraph("Proceedings No :"+"sample"+" Date:"+"sample"+"\r\n"
	      		+ ""
		       		+ "",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
		       paragraph4.setAlignment(Element.ALIGN_LEFT);
		       
		       
		       Phrase pharse5 = new Phrase();
		      
			 	  Chunk c10 = new Chunk("          SUB:",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
			 	  Chunk c11 = new Chunk(" "+"sample"+" Gram Panchayat,"+"sample"+" Mandal,"+"sample"+" District-\r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
			 	  Chunk c12 = new Chunk("                      Permission for installation of "+app_type+" – Reg.",FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
			 	 pharse5.add(c10);pharse5.add(c11);pharse5.add(c12);
			 	 Paragraph para8 = new Paragraph();
			 	 para8.add(pharse5);
			 	para8.setAlignment(Element.ALIGN_JUSTIFIED);

			 	 Phrase pharse6 = new Phrase();
			 	 Chunk c13 = new Chunk("         REF :",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
			 	 Chunk c14 = new Chunk("  1. G.O.Ms. No. 334, Dated: 09.10.2012 of Panchayat Raj and Rural Development\r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
			 	 Chunk c15 = new Chunk("                       (Pts. IV) Department, Government of Telangana\r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
			 	 Chunk c16 = new Chunk("                       2. G.O.Ms.No.2, Dated: 19.02.2021 of Information Technology, Electronics & \r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
			 	 Chunk c17 = new Chunk("                       Communications Department, Government of Telangana \r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
			 	 
			 	 
			 	pharse6.add(c13);pharse6.add(c14);pharse6.add(c15);pharse6.add(c16);pharse6.add(c17);pharse6.add("sample");
			 	Paragraph para9=new Paragraph();
			 	para9.add(pharse6);
			 	para9.setAlignment(Element.ALIGN_JUSTIFIED);

		       
		       
		       
		       String p1="     The application in the ref. 3rd cited have been examined and considered for Permission for " +
		               "erection of Telecommunication Infrastructure Tower based on the strength of the documents filed in "+
		    		   "terms of 1st & 2nd cited references i.e., G.O.Ms. No. 334, Dated: 09.10.2012 of PR & RD (Pts. IV) "+
		               "Department & G.O.Ms. No.2, Dated: 19.02.2021 of ITE & C Department.";
		       
		       Paragraph paragraph7=new Paragraph(p1,FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
		       paragraph7.setAlignment(Element.ALIGN_JUSTIFIED);
		       Paragraph parag1=new Paragraph("     The details of application filed are as follows",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.NORMAL, BaseColor.BLACK));
		       parag1.setAlignment(Element.ALIGN_JUSTIFIED);
		       PdfPTable table = new PdfPTable(6);
				 PdfPCell cell;
				  String p2="";
				  Paragraph paragraph8 = new Paragraph(" ");
				  Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
		       {
		       String tower="sample";
		       String PlotNo_surveyNo="";
		       String towerName="";
		       String towerDetails="";
		       if(tower.equalsIgnoreCase("GBT")) {
		    	   p2="For Ground Based Tower:" ;
		    	   towerName="Ground Based Tower";
		    	   
		    		   PlotNo_surveyNo="Survey No. "+"sample"+",";
		    	    
		    	   towerDetails=""+PlotNo_surveyNo+"\r\n"
		    	   		 				 	+" Street/Locality: "+"sample"+", \r\n"
		    	   		 				 		+" GP: "+"sample"+", \r\n"
		    	   		 				 		+ " Mandal: "+"sample"+", \r\n"
		    	   		 				 		+ "District: "+"sample"+" \r\n";
		    	   		 				 		
		       }else {
		    	   p2="For Roof Top Tower:";
		    	   towerName=   "Roof Top Tower ";
		    	   towerDetails="HN No."+"sample"+" ,\r\n"
  		 				 	+" Street/Locality: "+"sample"+", \r\n"
  		 				 		+" GP: "+"sample"+", \r\n"
  		 				 		+ " Mandal: "+"sample"+", "
  		 				 		+ "District: "+"sample"+" \r\n"
		    	   				+ "PIN Code: "+"sample"+" ";
		    	   
		       }
		          	paragraph8=new Paragraph(p2,FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.NORMAL, BaseColor.BLACK));
		       paragraph8.setAlignment(Element.ALIGN_LEFT);
		       
		   table = new PdfPTable(6);
		       float widths[] = { 4,3,5,3,3,4 };
		       table.setWidths(widths);
				
				 
			

				 cell = new PdfPCell(new Phrase("Name of the Company /Organization", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
			
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase("Type of Tower to be erected", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
			
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase("Location of the Tower to be erected", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
				
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase("Installation Fee (in Rs.)", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
			
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase("Date of Fee payment", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
			
				table.addCell(cell);
				
				 cell = new PdfPCell(new Phrase("Permission/License No.", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
				
				table.addCell(cell);
				
				 
				 cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase(""+towerName+"", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
				 cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase(""+towerDetails+"", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
				// cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase(""+"sample"
				 		+ "", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
				 cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase(""+"sample"+"", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
				 cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell);
				 cell = new PdfPCell(new Phrase("MT- "+"sample"+"", FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK)));
				 cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell);       
		       
		      
		       }	  
		
//		  
//
//	 

					String p4="     The permission granted on the basis of field visit and post verification of the application " +
				               "submitted by the Applicant/Company as per the references 1st & 2nd cited i.e., G.O.Ms. No. 334, Dated:"+
				    		   "09.10.2012 of PR & RD (Pts. IV) Department & G.O.Ms. No.2, Dated: 19.02.2021 of ITE & C Department. ";
				               
				       
				       Paragraph paragraph10=new Paragraph(p4,FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
				       paragraph10.setAlignment(Element.ALIGN_JUSTIFIED);
			       
				       
				       Paragraph paragraph11=new Paragraph("The applicant/company should strictly adhere to the rules of the GOs. cited in the ref. 1st & 2nd.",FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
				       paragraph11.setAlignment(Element.ALIGN_CENTER);
				       
				       Paragraph paragraph12=new Paragraph("In this regard, the Applicant/Company shall follow the below mentioned rules:",FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.BOLD, BaseColor.BLACK));
				       paragraph12.setAlignment(Element.ALIGN_LEFT);
				       
				       String p5= " i.    The applicant/company shall take special precaution for fire safety and lightening etc., \n"
				                + "ii.    The applicant/company shall not install the mobile tower near high density residential areas, \n"
				                + "        schools, playgrounds and hospitals as far as possible  \n"
				               + "iii.    The applicant/company shall continue regular restrictions as per established practices in cases \n"
				                + "        where mobile towers have to be erected in close proximity of certain critical infrastructures \n"
				                + "        such as – Airport, Defence Establishments, Railway and Electric Lines, Water Bodies, Oil/ Gas \n"
				                + "        pipelines and Heritage Structures etc., \n"
				                + "iv.    The applicant/company shall install Digital Display Boards to show the current EMF (Electric \n"
				                + "       and Magnetic Fields) Radiation and prescribed limitation, at prominent place in Gram \n"
				                + "       Panchayat area in order to spread awareness. \n"
				                + " v.    The applicant/company shall pay Annual License Renewal Fee regularly and shall furnish \n"
				                + "       updated structural stability certificate to the Gram Panchayat, issued by a qualified Structural \n"
				                + "       Engineer.";
				       
				       Paragraph paragraph13=new Paragraph(p5,FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
				       paragraph13.setAlignment(Element.ALIGN_JUSTIFIED);
				       
				       
				       
				       String p6="       As per the Rule 17 (iii) of G.O. Ms. No. 2, Dated : 19.02.2021 any person aggrieved by the "
				       		+ " decision of local authorities or for such other grievances related to Telecom Infrastructure in any "
				       		+ " District, may approach the District Nodal Officer i.e., Additional Collector (Local Bodies), who shall "
				       		+ " be the First Appellate Authority to resolve those issues."
				       		+ "" ;
				       	
				       Paragraph paragraph14=new Paragraph(p6,FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.NORMAL, BaseColor.BLACK));
				       paragraph14.setAlignment(Element.ALIGN_JUSTIFIED);        
				       
				       String p7="NOTE:As per the Indian Telegraph Infrastructure safety Rules, 2022, it is mandatory to report digging schedule"
								+ " & location to all asset owners through Call Before u Dig (CBuD) mobile app before starting of the"
								+ " excavation/digging activity as per this RoW approval. CBuD app can be downloaded from Google and Apple play store."
								+ " For more details visit https://cbud.gov.in";
				       
				       Paragraph paragraph1_1=new Paragraph(p7,FontFactory.getFont(FontFactory.TIMES_ROMAN,11, Font.BOLD, BaseColor.BLACK));
				       paragraph1_1.setAlignment(Element.ALIGN_JUSTIFIED); 
				       
				 	  Phrase pharse1 = new Phrase();
				 	  Chunk c1a = new Chunk("Application Deemed Approved,\r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
				 	  Chunk c1 = new Chunk("Panchayat Secretary,\r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
				 	  Chunk c2 = new Chunk(""+"sample"+",\r\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
				 	  Chunk c3 = new Chunk(" Mandal:"+"sample"+",\r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
				 	  Chunk c4 = new Chunk(" District:"+"sample"+"\r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
				 	 pharse1.add(c1a);pharse1.add(c1);pharse1.add(c2);pharse1.add(c3);pharse1.add(c4);
				 	  Paragraph para1 = new Paragraph();
				 	  para1.add(pharse1);
				 	  para1.setAlignment(Element.ALIGN_JUSTIFIED);
				 	  
				 	 Phrase pharse2 = new Phrase();
				 	Chunk c5 = new Chunk("To \r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
				 	Chunk c6=  new Chunk(""+"sample"+", \r\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
				 	Chunk c7=  new Chunk(""+"sample"+"",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
				 	pharse2.add(c5);pharse2.add(c6);pharse2.add(c7);
				 	  Paragraph para2 = new Paragraph();
				 	  para2.add(pharse2);
				 	  para2.setAlignment(Element.ALIGN_JUSTIFIED);
//				      document.add(paragraph1);
//				      document.add(paragraph2);
				      document.add(qrtable);
				      Paragraph para3 = new Paragraph("----------------------------------------------------------------------------------------------------------------------");
					  para3.setAlignment(Element.ALIGN_JUSTIFIED);
					  document.add(para3);
					  document.add(paragraph3); 
					  document.add(new Paragraph("\n"));
					  document.add(paragraph4); 
					  document.add(new Paragraph("\n"));
					  document.add(para8); 
					  document.add(new Paragraph("\n"));
					  document.add(para9);
					  Paragraph para4 = new Paragraph("*****",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
					  para4.setAlignment(Element.ALIGN_CENTER);
					  document.add(para4);
					  Paragraph para5 = new Paragraph("ORDER",FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK));
					  para5.setAlignment(Element.ALIGN_LEFT);
					  document.add(para5);
					  document.add(new Paragraph("\n"));
					  document.add(paragraph7);
					  document.add(new Paragraph("\n"));
					  document.add(parag1);
					  document.add(new Paragraph("\n"));
					  document.add(paragraph8);
					  document.add(new Paragraph("\n"));
					  document.add(table);
					  document.add(new Paragraph("\n"));
					  document.add(new Paragraph("\n"));
					  document.add(paragraph10);
					  document.add(new Paragraph("\n"));
					  document.add(paragraph11);
					  document.add(new Paragraph("\n"));
					  document.add(paragraph12);
					  document.add(new Paragraph("\n"));
					  document.add(paragraph13);
					  document.add(new Paragraph("\n"));
					  document.add(paragraph14);
					  document.add(new Paragraph("\n"));
					  document.add(paragraph1_1);
					  document.add(new Paragraph("\n\n\n\n\n"));
					  para1.setAlignment(Element.ALIGN_RIGHT);
					  document.add(para1);
					  document.add(new Paragraph("\n\n"));
					  para2.setAlignment(Element.ALIGN_LEFT);
					  document.add(para2);
					  document.close(); 
					  writer.close();
            // Close the document
            document.close();

            System.out.println("PDF created successfully!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    } 
}
