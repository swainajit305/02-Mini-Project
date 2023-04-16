package in.ashokit.service;

import java.awt.Color;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.binding.CitizenPlan;
import in.ashokit.binding.SearchRequest;
import in.ashokit.repo.CitizenPlanRepository;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {
	@Autowired
	private CitizenPlanRepository cpRepo;

	@Override
	public List<String> getCitizenPlanNames() {

		return cpRepo.getCitizenPlanNames();
	}

	@Override
	public List<String> getCitizenPlanStatuses() {

		return cpRepo.getCitizenPlanStatuses();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();

		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (request.getGender() != null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}
		Example<CitizenPlan> example = Example.of(entity);
		List<CitizenPlan> records = cpRepo.findAll(example);

		return records;
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Citizen Info");
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("planName");
		headerRow.createCell(3).setCellValue("planStatus");
		headerRow.createCell(4).setCellValue("Email");
		headerRow.createCell(5).setCellValue("gender");
		headerRow.createCell(6).setCellValue("ssn");
		headerRow.createCell(7).setCellValue("phno");

		int rowIndex = 1;
		List<CitizenPlan> records = cpRepo.findAll();
		for (CitizenPlan record : records) {
			XSSFRow dataRow = sheet.createRow(rowIndex);
			dataRow.createCell(0).setCellValue(record.getCid());
			dataRow.createCell(1).setCellValue(record.getCname());
			dataRow.createCell(2).setCellValue(record.getPlanName());
			dataRow.createCell(3).setCellValue(record.getPlanStatus());
			dataRow.createCell(4).setCellValue(record.getCemail());
			dataRow.createCell(5).setCellValue(record.getGender());
			dataRow.createCell(6).setCellValue(record.getSsn());
			dataRow.createCell(0).setCellValue(record.getPhno());
			rowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		
		workbook.close();
		ops.close();
	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("List of Users", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.5f, 3.5f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(10);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font f = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Id", f));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("planName", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("planStatus", f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Email", f));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Gender", f));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ssn", f));
		table.addCell(cell);
		cell.setPhrase(new Phrase("phno", f));
		table.addCell(cell);
		List<CitizenPlan> records = cpRepo.findAll();
		for (CitizenPlan record : records) {
			table.addCell(String.valueOf(record.getCid()));
			table.addCell((record.getCname()));
			table.addCell((record.getPlanName()));
			table.addCell((record.getPlanStatus()));
			table.addCell((record.getCemail()));
			table.addCell((record.getGender()));
			table.addCell(String.valueOf(record.getSsn()));
			table.addCell(String.valueOf(record.getPhno()));

		}

		document.add(table);

		document.close();
	}

}
