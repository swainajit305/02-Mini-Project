package in.ashokit.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.binding.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepository;
@Component
public class Dbinsert implements ApplicationRunner{
	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		CitizenPlan cp1=new CitizenPlan();
		cp1.setCname("john");
		cp1.setCemail("john@gmail.com");
		cp1.setPlanName("SNAP");
		cp1.setPlanStatus("Approved");
		cp1.setGender("Male");
		cp1.setPhno(79797991);
		cp1.setSsn(4356787);
		
		CitizenPlan cp2=new CitizenPlan();
		cp2.setCname("smith");
		cp2.setCemail("smith@gmail.com");
		cp2.setPlanName("SNAP");
		cp2.setPlanStatus("Denied");
		cp2.setGender("Male");
		cp2.setPhno(79797992);
		cp2.setSsn(43567877);
		
		CitizenPlan cp3=new CitizenPlan();
		cp3.setCname("charles");
		cp3.setCemail("charles@gmail.com");
		cp3.setPlanName("CCAP");
		cp3.setPlanStatus("Approved");
		cp3.setGender("Fe-Male");
		cp3.setPhno(79797993);
		cp3.setSsn(43567871);
		
		
		CitizenPlan cp4=new CitizenPlan();
		cp4.setCname("jenny");
		cp4.setCemail("jenny@gmail.com");
		cp4.setPlanName("CCAP");
		cp4.setPlanStatus("Denied");
		cp4.setGender("Male");
		cp4.setPhno(79797994);
		cp4.setSsn(43567874);
		         List<CitizenPlan> asList = Arrays.asList(cp1,cp2,cp3,cp4);
		
		         repo.saveAll(asList);
		
	}

}
