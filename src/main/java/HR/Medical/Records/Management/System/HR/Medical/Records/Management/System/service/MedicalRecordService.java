package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.service;

import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.requestdto.MedicalRecordRequestDTO;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.response.dto.MedicalRecordResponseDTO;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.MedicalRecordEntity;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.MedicalRecordTypeEntity;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.StatusEntity;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.repository.MedicalRecordRepository;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.repository.MedicalRecordTypeRepository;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.repository.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MedicalRecordService {


    @Autowired
    private final StatusRepository statusRepository;
    private  final MedicalRecordTypeRepository medicalRecordTypeRepository;


    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(StatusRepository statusRepository, MedicalRecordTypeRepository medicalRecordTypeRepository) {
        this.statusRepository = statusRepository;
        this.medicalRecordTypeRepository = medicalRecordTypeRepository;
    }

    //"get with pagination, size, and filtering"
    public Page<MedicalRecordResponseDTO> findFiltered(
            Long statusId,
            LocalDate startDate,
            LocalDate endDate,
            Long medicalRecordTypeId,
            Pageable pageable) {

        Specification<MedicalRecordEntity> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (statusId != null) {
                predicates.add(criteriaBuilder.equal(root.get("status").get("statusId"), statusId));
            }

            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate));
            }

            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate));
            }

            if (medicalRecordTypeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("medicalRecordType").get("medicalRecordTypeId"), medicalRecordTypeId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };


        Page<MedicalRecordEntity> entityPage = medicalRecordRepository.findAll(spec, pageable);

        // Convertir las entidades a DTOs
        return entityPage.map(MedicalRecordResponseDTO::new);
    }


        // Find a medical record by ID
        public Optional<MedicalRecordResponseDTO> findById(Long id) {
            Optional<MedicalRecordEntity> entityOptional = medicalRecordRepository.findById(id);

            return entityOptional.map(MedicalRecordResponseDTO::new);
        }


        //Update method to edit a medical record
    @Transactional
    public MedicalRecordResponseDTO update(Long id, MedicalRecordRequestDTO dto) {
        MedicalRecordEntity entity = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical record not found with id: " + id));


        entity.setFileId(dto.getFileId());
        entity.setAudiometry(dto.getAudiometry());
        entity.setPositionChange(dto.getPositionChange());
        entity.setMotherData(dto.getMotherData());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setOtherFamilyData(dto.getOtherFamilyData());
        entity.setFatherData(dto.getFatherData());
        entity.setExecuteMicros(dto.getExecuteMicros());
        entity.setExecuteExtra(dto.getExecuteExtra());
        entity.setVoiceEvaluation(dto.getVoiceEvaluation());
        entity.setStartDate(dto.getStartDate());

        StatusEntity statusEntity = statusRepository.findById(dto.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found with id: " + dto.getStatusId()));
        entity.setStatus(statusEntity);

        MedicalRecordTypeEntity medicalRecordTypeEntity = medicalRecordTypeRepository.findById(dto.getMedicalRecordTypeId())
                .orElseThrow(() -> new EntityNotFoundException("MedicalRecordType not found with id: " + dto.getMedicalRecordTypeId()));
        entity.setMedicalRecordType(medicalRecordTypeEntity);

        if (entity.getStatus().getStatusId() == 2) {
            throw new IllegalArgumentException("Cannot update a medical record with 'Inactive' status.");
        }




        entity.setDisability(dto.getDisability());
        entity.setMedicalBoard(dto.getMedicalBoard());
        entity.setObservations(dto.getObservations());
        entity.setDisabilityPercentage(dto.getDisabilityPercentage());
        entity.setModifiedBy(dto.getModifiedBy());

        if (dto.getModifiedBy() == null || dto.getModifiedBy().trim().isEmpty()) {
            throw new IllegalArgumentException("MODIFIED_BY is required for updating the medical record.");
        }

        validateDisabilityPercentage(dto.getDisability(), dto.getDisabilityPercentage());
        validateObservationsForPositionChange(dto.getPositionChange(), dto.getObservations());

        entity.setAreaChange(dto.getAreaChange());


        entity.setModificationDate(LocalDate.now());
        MedicalRecordEntity updatedEntity = medicalRecordRepository.save(entity);

        return new MedicalRecordResponseDTO(updatedEntity);
    }




        //Method to delete a medical record
        public void deleteMedicalRecordById(Long id, String deletedBy, String deletionReason, LocalDate endDate) {

            MedicalRecordEntity medicalRecordEntity = medicalRecordRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Record not found"));

            if (deletionReason == null || deletionReason.trim().isEmpty()) {
                throw new IllegalArgumentException("DELETION_REASON es obligatorio para eliminar el registro");
            }

            if (deletionReason.length()>2000){
                throw new IllegalArgumentException("DELETION_REASON no puede tener más de 2000 caracteres");
            }

            if (deletedBy == null || deletedBy.trim().isEmpty()){
                throw new IllegalArgumentException("deleted_By es obligatorio para eliminar el registro");
            }

            if (endDate == null || deletedBy.trim().isEmpty()){
                throw new IllegalArgumentException("endDate es obligatorio para eliminar el registro");
            }

            if (endDate.isBefore(medicalRecordEntity.getStartDate())){
                throw new IllegalArgumentException("endDate no puede estar antes que startDate");

            }




            medicalRecordEntity.setStatus(new StatusEntity(2L, "inactivo"));  // Suponiendo que 2 es el ID para "Inactive"
            medicalRecordEntity.setDeletionReason(deletionReason);
            medicalRecordEntity.setDeletionDate(LocalDate.now());  // Fecha de eliminación actual
            medicalRecordEntity.setDeletedBy(deletedBy);  // Usuario que realiza la eliminación
            medicalRecordEntity.setEndDate(endDate);

            medicalRecordRepository.save(medicalRecordEntity);
        }



        // Method to create a medical record
    public MedicalRecordResponseDTO createMedicalRecord(MedicalRecordRequestDTO dto) {

        // Validations

        if (dto.getStatusId() == 2) {
            throw new IllegalArgumentException("Cannot assign 'Inactive' status when creating a new record.");
        }


        validateDisabilityPercentage(dto.getDisability(), dto.getDisabilityPercentage());
        validateObservationsForPositionChange(dto.getPositionChange(), dto.getObservations());



        // Map the DTO to the Entity

        MedicalRecordEntity entity = new MedicalRecordEntity();
        entity.setFileId(dto.getFileId());
        entity.setAudiometry(dto.getAudiometry());
        entity.setPositionChange(dto.getPositionChange());
        entity.setMotherData(dto.getMotherData());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setOtherFamilyData(dto.getOtherFamilyData());
        entity.setFatherData(dto.getFatherData());
        entity.setExecuteMicros(dto.getExecuteMicros());
        entity.setExecuteExtra(dto.getExecuteExtra());
        entity.setVoiceEvaluation(dto.getVoiceEvaluation());
        entity.setCreationDate(LocalDate.now());
        entity.setStartDate(dto.getStartDate());


        StatusEntity statusEntity = statusRepository.findById(dto.getStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found with id: " + dto.getStatusId()));
        entity.setStatus(statusEntity);

        MedicalRecordTypeEntity medicalRecordTypeEntity = medicalRecordTypeRepository.findById(dto.getMedicalRecordTypeId())
                .orElseThrow(() -> new EntityNotFoundException("MedicalRecordType not found with id: " + dto.getMedicalRecordTypeId()));
        entity.setMedicalRecordType(medicalRecordTypeEntity);

        entity.setDisability(dto.getDisability());
        entity.setMedicalBoard(dto.getMedicalBoard());
        entity.setObservations(dto.getObservations());
        entity.setDisabilityPercentage(dto.getDisabilityPercentage());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setAreaChange(dto.getAreaChange());

        MedicalRecordEntity savedEntity = medicalRecordRepository.save(entity);

        return new MedicalRecordResponseDTO(savedEntity);

    }

    // Validation: DISABILITY_PERCENTAGE must be between 0 and 100 if DISABILITY = 'YES'
    private void validateDisabilityPercentage(String disability, Integer disabilityPercentage) {
        if ("YES".equalsIgnoreCase(disability)) {
            if (disabilityPercentage == null || disabilityPercentage < 0 || disabilityPercentage > 100) {
                throw new IllegalArgumentException("DISABILITY_PERCENTAGE must be between 0 and 100 when DISABILITY = 'YES'.");
            }
        }
    }

    // Validation: observations must be mandatory if positionChange = 'YES'
    private void validateObservationsForPositionChange(String positionChange, String observations) {
        if ("YES".equalsIgnoreCase(positionChange)) {
            if (observations == null || observations.trim().isEmpty()) {
                throw new IllegalArgumentException("OBSERVATIONS field is mandatory when POSITION_CHANGE = 'YES'.");
            }
        }
    }



    }

