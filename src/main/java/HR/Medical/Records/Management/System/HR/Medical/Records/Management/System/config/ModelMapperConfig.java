package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.config;

import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.requestdto.MedicalRecordRequestDTO;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.response.dto.MedicalRecordResponseDTO;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.MedicalRecordEntity;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.MedicalRecordTypeEntity;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.StatusEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Mapping for MedicalRecordRequestDTO to MedicalRecordEntity
        modelMapper.typeMap(MedicalRecordRequestDTO.class, MedicalRecordEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(MedicalRecordEntity::setStatus); // Skip status mapping for manual handling
                    mapper.skip(MedicalRecordEntity::setMedicalRecordType); // Skip medicalRecordType mapping for manual handling
                })
                .setPostConverter(context -> {
                    MedicalRecordRequestDTO source = context.getSource();
                    MedicalRecordEntity destination = context.getDestination();

                    if (source.getStatusId() != null) {
                        destination.setStatus(new StatusEntity(source.getStatusId(), null));
                    }

                    if (source.getMedicalRecordTypeId() != null) {
                        destination.setMedicalRecordType(new MedicalRecordTypeEntity(source.getMedicalRecordTypeId()));
                    }

                    return destination;
                });

        // Mapping for MedicalRecordEntity to MedicalRecordResponseDTO
        modelMapper.typeMap(MedicalRecordEntity.class, MedicalRecordResponseDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getStatus(), MedicalRecordResponseDTO::setStatus);
                    mapper.map(src -> src.getMedicalRecordType(), MedicalRecordResponseDTO::setMedicalRecordType);
                });

        return modelMapper;


}
}