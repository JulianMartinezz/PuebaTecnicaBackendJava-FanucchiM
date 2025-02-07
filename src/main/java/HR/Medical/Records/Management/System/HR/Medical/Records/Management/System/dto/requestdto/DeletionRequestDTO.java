package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.requestdto;

import java.time.LocalDate;

public class DeletionRequestDTO {

    private String deletedBy;
    private String deletionReason;
    private LocalDate endDate;

    // Getters and Setters
    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getDeletionReason() {
        return deletionReason;
    }

    public void setDeletionReason(String deletionReason) {
        this.deletionReason = deletionReason;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
