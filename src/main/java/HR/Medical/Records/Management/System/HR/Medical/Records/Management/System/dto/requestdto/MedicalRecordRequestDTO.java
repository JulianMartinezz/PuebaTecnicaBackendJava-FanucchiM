package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.requestdto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import jakarta.validation.constraints.Pattern;

public class MedicalRecordRequestDTO {


    @NotNull
    private Integer fileId;

    @Pattern(regexp = "^(YES|NO)$", message = "AUDIOMETRY must be 'YES' or 'NO'.")
    private String audiometry;

    @Pattern(regexp = "^(YES|NO)$", message = "POSITION_CHANGE must be 'YES' or 'NO'.")
    private String positionChange;

    @Size(max = 2000, message = "Mother data must be at most 2000 characters")
    private String motherData;


    @Size(max = 100, message = "diagnosis must be at most 100 characters")
    private String diagnosis;

    @Size(max = 2000, message = "Other family data must be at most 2000 characters")
    private String otherFamilyData;

    @Size(max = 2000, message = "Father data must be at most 2000 characters")
    private String fatherData;


    @Pattern(regexp = "^(YES|NO)$", message = "EXECUTE_MICROS must be 'YES' or 'NO'.")
    private String executeMicros;

    @Pattern(regexp = "^(YES|NO)$", message = "EXECUTE_EXTRA must be 'YES' or 'NO'.")
    private String executeExtra;

    @Pattern(regexp = "^(YES|NO)$", message = "VOICE_EVALUATION must be 'YES' or 'NO'.")
    private String voiceEvaluation;

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

    @PastOrPresent
    @NotNull
    private LocalDate startDate;



    @NotNull
    private Long statusId;

    @NotNull
    private Long  medicalRecordTypeId;

    @Pattern(regexp = "^(YES|NO)$", message = "DISABILITY must be 'YES' or 'NO'.")
    private String disability;


    @Size(max = 200, message = "Medical board must be at most 200 characters")
    private String medicalBoard;



    @Size(max = 2000, message = "Observations must be at most 2000 characters")
    private String observations;

    private Integer disabilityPercentage;



    @NotBlank
    private String createdBy;

    private String modifiedBy;


    @Pattern(regexp = "^(YES|NO)$", message = "AREA_CHANGE must be 'YES' or 'NO'.")
    private String areaChange;


    //Getters and Setters

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

    public  String getPositionChange() {
        return positionChange;
    }

    public void setPositionChange( String positionChange) {
        this.positionChange = positionChange;
    }

    public String getMotherData() {
        return motherData;
    }

    public void setMotherData( String motherData) {
        this.motherData = motherData;
    }

    public  String getDiagnosis() {
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

    public  String getFatherData() {
        return fatherData;
    }

    public void setFatherData( String fatherData) {
        this.fatherData = fatherData;
    }

    public  String getExecuteMicros() {
        return executeMicros;
    }

    public void setExecuteMicros( String executeMicros) {
        this.executeMicros = executeMicros;
    }

    public  String getExecuteExtra() {
        return executeExtra;
    }

    public void setExecuteExtra( String executeExtra) {
        this.executeExtra = executeExtra;
    }

    public  String getVoiceEvaluation() {
        return voiceEvaluation;
    }

    public void setVoiceEvaluation (String voiceEvaluation) {
        this.voiceEvaluation = voiceEvaluation;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public @NotNull LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getMedicalRecordTypeId() {
        return medicalRecordTypeId;
    }

    public void setMedicalRecordTypeId( Long medicalRecordTypeId) {
        this.medicalRecordTypeId = medicalRecordTypeId;
    }

    public  String getDisability() {
        return disability;
    }

    public void setDisability( String disability) {
        this.disability = disability;
    }

    public  String getMedicalBoard() {
        return medicalBoard;
    }

    public void setMedicalBoard( String medicalBoard) {
        this.medicalBoard = medicalBoard;
    }



    public  String getObservations() {
        return observations;
    }

    public void setObservations( String observations) {
        this.observations = observations;
    }

    public Integer getDisabilityPercentage() {
        return disabilityPercentage;
    }

    public void setDisabilityPercentage(Integer disabilityPercentage) {
        this.disabilityPercentage = disabilityPercentage;
    }


    public @NotNull String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@NotNull String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public  String getAreaChange() {
        return areaChange;
    }

    public void setAreaChange( String areaChange) {
        this.areaChange = areaChange;
    }
}
