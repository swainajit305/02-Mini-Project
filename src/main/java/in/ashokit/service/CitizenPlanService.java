package in.ashokit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.ashokit.binding.CitizenPlan;
import in.ashokit.binding.SearchRequest;

public interface CitizenPlanService {

	public List<String> getCitizenPlanNames();

	public List<String> getCitizenPlanStatuses();

	public List<CitizenPlan> search(SearchRequest request);

	public void exportExcel(HttpServletResponse response) throws Exception;

	public void exportPdf(HttpServletResponse response) throws Exception;

}
