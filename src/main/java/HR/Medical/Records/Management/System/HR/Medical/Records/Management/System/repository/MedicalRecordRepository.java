package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.repository;

import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long>, JpaSpecificationExecutor<MedicalRecordEntity> {
}