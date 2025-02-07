package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {
		"HR.Medical.Records.Management.System",
		"HR.Medical.Records.Management.System.mapper"
})
public class HrMedicalRecordsManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrMedicalRecordsManagementSystemApplication.class, args);
	}

}
