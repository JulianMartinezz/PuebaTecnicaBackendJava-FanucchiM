package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.response.dto;

import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.MedicalRecordTypeEntity;

public class MedicalRecordTypeDTO {
    private Long medicalRecordTypeId;
    private String name;
    private String description;

    public MedicalRecordTypeDTO(){
    }

    public MedicalRecordTypeDTO(MedicalRecordTypeEntity medicalRecordTypeEntity) {
        this.medicalRecordTypeId = medicalRecordTypeEntity.getMedicalRecordTypeId();
        this.name = medicalRecordTypeEntity.getName();
        this.description = medicalRecordTypeEntity.getDescription();
    }

    // Getters and Setters
    public Long getMedicalRecordTypeId() {
        return medicalRecordTypeId;
    }

    public void setMedicalRecordTypeId(Long medicalRecordTypeId) {
        this.medicalRecordTypeId = medicalRecordTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
