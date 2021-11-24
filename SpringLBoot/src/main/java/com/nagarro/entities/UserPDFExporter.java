package com.nagarro.entities;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Class for creating pdf of the details of all user and export to the user's
 * device
 * 
 * @author priyasharma
 *
 */
public class UserPDFExporter {

	private List<Library> listUsers;

	public UserPDFExporter(List<Library> listUsers) {
		this.listUsers = listUsers;
	}

	/**
	 * Design Pattern for Table Headings
	 * 
	 * @param table
	 */
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.YELLOW);

		cell.setPhrase(new Phrase("Book Code", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Book Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Author", font));
		table.addCell(cell);

		

		cell.setPhrase(new Phrase("Data Added", font));
		table.addCell(cell);

	}

	/**
	 * Retrieving the data of all librarian and adding to pdf
	 * 
	 * @param table
	 */
	private void writeTableData(PdfPTable table) {
		for (Library lib : listUsers) {
			table.addCell(String.valueOf(lib.getBookCode()));
			table.addCell(lib.getBookName());
			table.addCell(lib.getAuthor());
			
			table.addCell(lib.getDataadded());
		}
	}

	/**
	 * Method to export the pdf containing the data of all librarian to user's
	 * machine
	 * 
	 * @param response
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLUE);
		font.setSize(18);

		Paragraph title = new Paragraph("List of all Employees", font);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.5f, 3.0f });

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();
	}

}

