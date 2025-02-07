package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.repository;

import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

}
