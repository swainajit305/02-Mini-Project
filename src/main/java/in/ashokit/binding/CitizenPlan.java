package in.ashokit.binding;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIZEN_PLAN")
public class CitizenPlan {
	@Id
	@GeneratedValue
	private Integer cid;
	private String cname;
	private String cemail;
	private String planStatus;
	private String planName;
	private Integer ssn;
	private Integer phno;
	private String gender;

}
