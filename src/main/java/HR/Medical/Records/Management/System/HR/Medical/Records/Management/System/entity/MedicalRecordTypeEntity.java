package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
@Table(name = "medical_record_type")
public class MedicalRecordTypeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_type_id")
    private Long medicalRecordTypeId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "medicalRecordType")
    private List<MedicalRecordEntity> records;


    public MedicalRecordTypeEntity(Long medicalRecordTypeId) {
        this.medicalRecordTypeId = medicalRecordTypeId;
    }

    //Getters and Setters
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
