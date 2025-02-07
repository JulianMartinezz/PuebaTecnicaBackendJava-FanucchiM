package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.response.dto;

import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.MedicalRecordEntity;

import java.time.LocalDate;

public class MedicalRecordResponseDTO {

    private Long medicalRecordId;
    private Integer fileId;
    private String audiometry;
    private String positionChange;
    private String motherData;
    private String diagnosis;
    private String otherFamilyData;
    private String fatherData;
    private String executeMicros;
    private String executeExtra;
    private String voiceEvaluation;
    private LocalDate deletionDate;
    private LocalDate creationDate;
    private LocalDate modificationDate;
    private LocalDate endDate;
    private LocalDate startDate;
    private StatusDTO status;
    private MedicalRecordTypeDTO medicalRecordType;
    private String disability;
    private String medicalBoard;
    private String deletionReason;
    private String observations;
    private Integer disabilityPercentage;
    private String deletedBy;
    private String createdBy;
    private String modifiedBy;
    private String areaChange;

    public MedicalRecordResponseDTO(MedicalRecordEntity entity) {
        this.medicalRecordId = entity.getMedicalRecordId();
        this.fileId = entity.getFileId();
        this.audiometry = entity.getAudiometry();
        this.positionChange = entity.getPositionChange();
        this.motherData = entity.getMotherData();
        this.diagnosis = entity.getDiagnosis();
        this.otherFamilyData = entity.getOtherFamilyData();
        this.fatherData = entity.getFatherData();
        this.executeMicros = entity.getExecuteMicros();
        this.executeExtra = entity.getExecuteExtra();
        this.voiceEvaluation = entity.getVoiceEvaluation();
        this.deletionDate = entity.getDeletionDate();
        this.creationDate = entity.getCreationDate();
        this.modificationDate = entity.getModificationDate();
        this.endDate = entity.getEndDate();
        this.startDate = entity.getStartDate();

        if (entity.getStatus() != null) {
            this.status = new StatusDTO(entity.getStatus());
        }

        if (entity.getMedicalRecordType() != null) {
            this.medicalRecordType = new MedicalRecordTypeDTO(entity.getMedicalRecordType());
        }

        this.disability = entity.getDisability();
        this.medicalBoard = entity.getMedicalBoard();
        this.deletionReason = entity.getDeletionReason();
        this.observations = entity.getObservations();
        this.disabilityPercentage = entity.getDisabilityPercentage();
        this.deletedBy = entity.getDeletedBy();
        this.createdBy = entity.getCreatedBy();
        this.modifiedBy = entity.getModifiedBy();
        this.areaChange = entity.getAreaChange();
    }

    // Getters and Setters
    public Long getMedicalRecordId() {
        return medicalRecordId;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
    public void setMedicalRecordId(Long medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getAudiometry() {
        return audiometry;
    }

    public void setAudiometry(String audiometry) {
        this.audiometry = audiometry;
    }

    public String getPositionChange() {
        return positionChange;
    }

    public void setPositionChange(String positionChange) {
        this.positionChange = positionChange;
    }

    public String getMotherData() {
        return motherData;
    }

    public void setMotherData(String motherData) {
        this.motherData = motherData;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getOtherFamilyData() {
        return otherFamilyData;
    }

    public void setOtherFamilyData(String otherFamilyData) {
        this.otherFamilyData = otherFamilyData;
    }

    public String getFatherData() {
        return fatherData;
    }

    public void setFatherData(String fatherData) {
        this.fatherData = fatherData;
    }

    public String getExecuteMicros() {
        return executeMicros;
    }

    public void setExecuteMicros(String executeMicros) {
        this.executeMicros = executeMicros;
    }

    public String getExecuteExtra() {
        return executeExtra;
    }

    public void setExecuteExtra(String executeExtra) {
        this.executeExtra = executeExtra;
    }

    public String getVoiceEvaluation() {
        return voiceEvaluation;
    }

    public void setVoiceEvaluation(String voiceEvaluation) {
        this.voiceEvaluation = voiceEvaluation;
    }

    public LocalDate getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(LocalDate deletionDate) {
        this.deletionDate = deletionDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public MedicalRecordTypeDTO getMedicalRecordType() {
        return medicalRecordType;
    }

    public void setMedicalRecordType(MedicalRecordTypeDTO medicalRecordType) {
        this.medicalRecordType = medicalRecordType;
    }



    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getMedicalBoard() {
        return medicalBoard;
    }

    public void setMedicalBoard(String medicalBoard) {
        this.medicalBoard = medicalBoard;
    }

    public String getDeletionReason() {
        return deletionReason;
    }

    public void setDeletionReason(String deletionReason) {
        this.deletionReason = deletionReason;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Integer getDisabilityPercentage() {
        return disabilityPercentage;
    }

    public void setDisabilityPercentage(Integer disabilityPercentage) {
        this.disabilityPercentage = disabilityPercentage;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getAreaChange() {
        return areaChange;
    }

    public void setAreaChange(String areaChange) {
        this.areaChange = areaChange;
    }
}
