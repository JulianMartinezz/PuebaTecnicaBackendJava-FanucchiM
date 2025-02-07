package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity;

import  jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


@Entity
@Table(name = "t_medical_record")
public class MedicalRecordEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDICAL_RECORD_ID")
    private Long medicalRecordId;


    @NotNull
    @Column(name = "FILE_ID")
    private Integer fileId;

    @Column(name = "audiometry")
    private String audiometry;



    @Pattern(regexp = "YES|NO", message = "Position change must be 'YES' or 'NO'")
    @Column(name = "POSITION_CHANGE")
    private String positionChange;

    @Size(max = 2000, message = "Mother data must be at most 2000 characters")
    @Column(name = "MOTHER_DATA")
    private String motherData;


    @NotNull
    @Size(max = 100, message = "Diagnosis must be at most 100 characters")
    @Column(name = "DIAGNOSIS")
    private String diagnosis;

    @Size(max = 2000, message = "Other family data must be at most 2000 characters")
    @Column(name = "OTHER_FAMILY_DATA", length = 2000)
    private String otherFamilyData;

    @Size(max = 2000, message = "Father data must be at most 2000 characters")
    @Column(name = "FATHER_DATA", length = 2000)
    private String fatherData;

    @Pattern(regexp = "YES|NO", message = "Execute micros must be 'YES' or 'NO'")
    @Column(name = "executeMicros")
    private String executeMicros;

    @Pattern(regexp = "YES|NO", message = "Execute extra must be 'YES' or 'NO'")
    @Column(name = "EXECUTE_EXTRA")
    private String executeExtra;

    @Pattern(regexp = "YES|NO", message = "Voice evaluation must be 'YES' or 'NO'")
    @Column(name = "VOICE_EVALUATION")
    private String voiceEvaluation;

    @Column(name = "DELETION_DATE")
    private LocalDate deletionDate;



    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDate creationDate;


    @Column(name = "MODIFICATION_DATE")
    private LocalDate modificationDate;


    @Column(name = "END_DATE")
    private LocalDate endDate;

    @NotNull
    @Column(name = "START_DATE")
    private LocalDate startDate;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "medical_record_type_id")
    private MedicalRecordTypeEntity medicalRecordType;




    @Pattern(regexp = "YES|NO", message = "Disability must be 'YES' or 'NO'")
    @Column(name = "DISABILITY")
    private String disability;

    @Size(max = 200, message = "Medical board must be at most 200 characters")
    @Column(name = "MEDICAL_BOARD")
    private String medicalBoard;

    @Size(max = 2000, message = "Deletion reason must be at most 2000 characters")
    @Column(name = "DELETION_REASON")
    private String deletionReason;


    @Size(max = 2000, message = "Observations must be at most 2000 characters")
    @Column(name = "OBSERVATIONS")
    private String observations;

    @Column(name = "DISABILITY_PERCENTAGE", precision = 10)
    private Integer  disabilityPercentage;

    @Column(name = "DELETED_BY")
    private String deletedBy;


    @NotNull
    @Column(name = "CREATED_BY", length = 2000)
    private String createdBy;

    @Column(name = "MODIFIED_BY", length = 2000)
    private String modifiedBy;


    @Pattern(regexp = "YES|NO", message = "Area change must be 'YES' or 'NO'")
    @Column(name = "AREA_CHANGE")
    private String areaChange;




    // Getters and Setters
    public Long getMedicalRecordId() {
        return medicalRecordId;
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


    public StatusEntity getStatus() {
        return status;
    }
    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public MedicalRecordTypeEntity getMedicalRecordType() {
        return medicalRecordType;
    }

    public void setMedicalRecordType(MedicalRecordTypeEntity medicalRecordType) {
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
