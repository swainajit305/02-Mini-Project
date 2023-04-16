package in.ashokit.rest;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.CitizenPlan;
import in.ashokit.binding.SearchRequest;
import in.ashokit.service.CitizenPlanService;

@RestController
public class ReportRestController {
	@Autowired
	private CitizenPlanService service;

	@GetMapping("/planname")
	public ResponseEntity<List<String>> getCitizenPlanNames() {
		List<String> citizenPlanNames = service.getCitizenPlanNames();
		return new ResponseEntity<List<String>>(citizenPlanNames, HttpStatus.OK);

	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getCitizenPlanStatuses() {

		List<String> citizenPlanStatuses = service.getCitizenPlanStatuses();
		return new ResponseEntity<List<String>>(citizenPlanStatuses, HttpStatus.OK);

	}

	@PostMapping("/search")
	public ResponseEntity<List<CitizenPlan>> search(@RequestBody SearchRequest request) {

		List<CitizenPlan> search = service.search(request);
		return new ResponseEntity<List<CitizenPlan>>(search, HttpStatus.OK);

	}

	@GetMapping("/excel")
	public void exportExcell(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String key = "Content-Disposition";
		String value = "attachment; filename= citizenx.xlsx";
		response.setHeader(key, value);
		    service.exportExcel(response);
		response.flushBuffer();

	}
      @GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		String key = "Content-Disposition";
		String value = "attachment; filename=plans.pdf";
		response.setHeader(key, value);
		service.exportPdf(response);
		response.flushBuffer();

	}
}
