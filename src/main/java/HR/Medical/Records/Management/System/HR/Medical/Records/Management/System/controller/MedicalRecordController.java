package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.controller;


import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.requestdto.DeletionRequestDTO;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.requestdto.MedicalRecordRequestDTO;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.response.dto.BaseResponse;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.response.dto.MedicalRecordResponseDTO;
import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.service.MedicalRecordService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {


    @Autowired
    private MedicalRecordService medicalRecordService;
    private DeletionRequestDTO deletionRequestDTO;



    /// Retrieves a list of medical records based on the provided filters.
    @GetMapping("/filter")
    public ResponseEntity<BaseResponse<Page<MedicalRecordResponseDTO>>> obtenerTodosConFiltros(
            @RequestParam int page, // Obligatorio
            @RequestParam int pageSize, // Obligatorio
            @RequestParam(required = false) Long statusId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Long medicalRecordTypeId) {

        BaseResponse<Page<MedicalRecordResponseDTO>> response = new BaseResponse<>();

        try {
            if (pageSize <= 0) {
                response.setSuccess(false);
                response.setMessage("PageSize must be greater than 0.");
                response.setCode(HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.badRequest().body(response);
            }

            // Crear un objeto Pageable para la paginación
            Pageable pageable = PageRequest.of(page, pageSize);

            // Llamar al servicio para obtener los expedientes filtrados
            Page<MedicalRecordResponseDTO> recordsPage = medicalRecordService.findFiltered(
                    statusId, startDate, endDate, medicalRecordTypeId, pageable);

            // Configurar la respuesta exitosa
            response.setSuccess(true);
            response.setMessage("Medical records retrieved successfully.");
            response.setData(recordsPage);
            response.setCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Manejar errores de validación o filtros incorrectos
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            // Registrar la excepción completa
            e.printStackTrace();

            // Configurar la respuesta de error inesperado
            response.setSuccess(false);
            response.setMessage("An unexpected error occurred while retrieving the medical records.");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setException(e.getMessage()); // Opcional: incluir detalles de la excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /// Retrieves the details of a specific medical record by its ID.

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<MedicalRecordResponseDTO>> getMedicalRecordById(@PathVariable Long id) {
        BaseResponse<MedicalRecordResponseDTO> response = new BaseResponse<>();

        try {

            Optional<MedicalRecordResponseDTO> dtoOptional = medicalRecordService.findById(id);

            if (dtoOptional.isPresent()) {

                response.setSuccess(true);
                response.setMessage("Medical record found successfully.");
                response.setData(dtoOptional.get());
                response.setCode(HttpStatus.OK.value());

                return ResponseEntity.ok(response);
            } else {

                response.setSuccess(false);
                response.setMessage("Medical record not found with id: " + id);
                response.setCode(HttpStatus.NOT_FOUND.value());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {

            response.setSuccess(false);
            response.setMessage("An unexpected error occurred while retrieving the medical record.");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setException(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /// Creates a new medical record
    @PostMapping
    public ResponseEntity<BaseResponse<MedicalRecordResponseDTO>> createMedicalRecord(
            @RequestBody @Valid MedicalRecordRequestDTO dto,
            BindingResult result) {
        BaseResponse<MedicalRecordResponseDTO> response = new BaseResponse<>();

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            response.setSuccess(false);
            response.setMessage("Validation failed.");
            response.setData(null);
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setException(errors.toString());
            return ResponseEntity.badRequest().body(response);
        }

        try {
            MedicalRecordResponseDTO createdRecord = medicalRecordService.createMedicalRecord(dto);

            // Configurar la respuesta exitosa
            response.setSuccess(true);
            response.setMessage("Medical record created successfully.");
            response.setData(createdRecord);
            response.setCode(HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        } catch (EntityNotFoundException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setCode(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();

            response.setSuccess(false);
            response.setMessage("An unexpected error occurred while creating the medical record.");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setException(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /// Updates an existing medical record
    @PostMapping("update/{id}")
    public ResponseEntity<BaseResponse<MedicalRecordResponseDTO>> update(
            @Valid @RequestBody MedicalRecordRequestDTO dto,
            @PathVariable Long id) {
        BaseResponse<MedicalRecordResponseDTO> response = new BaseResponse<>();

        try {
            MedicalRecordResponseDTO updatedRecord = medicalRecordService.update(id, dto);

            response.setSuccess(true);
            response.setMessage("Medical record updated successfully.");
            response.setData(updatedRecord);
            response.setCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(response);
        } catch (EntityNotFoundException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setCode(HttpStatus.NOT_FOUND.value());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();

            // Configurar la respuesta de error inesperado
            response.setSuccess(false);
            response.setMessage("An unexpected error occurred while updating the medical record.");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setException(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /// Performs a logical deletion of a medical record
    @DeleteMapping("delete/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteMedicalRecord(
            @PathVariable Long id,
            @RequestBody @Valid DeletionRequestDTO deletionRequest) {
        BaseResponse<Void> response = new BaseResponse<>();

        try {

            if (deletionRequest == null) {
                response.setSuccess(false);
                response.setMessage("El cuerpo de la solicitud no puede estar vacío.");
                response.setCode(HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.badRequest().body(response);
            }

            if (deletionRequest.getDeletedBy() == null || deletionRequest.getDeletedBy().trim().isEmpty()) {
                response.setSuccess(false);
                response.setMessage("Deleted by cannot be empty.");
                response.setCode(HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.badRequest().body(response);
            }




            medicalRecordService.deleteMedicalRecordById(
                    id,
                    deletionRequest.getDeletedBy(),
                    deletionRequest.getDeletionReason(),
                    deletionRequest.getEndDate()
            );

            response.setSuccess(true);
            response.setMessage("Medical record deleted successfully.");
            response.setData(null);
            response.setCode(HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {

            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setCode(HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(response);
        } catch (EntityNotFoundException e) {

            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setCode(HttpStatus.NOT_FOUND.value());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {

            e.printStackTrace();

            response.setSuccess(false);
            response.setMessage("An unexpected error occurred while deleting the medical record.");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setException(e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
