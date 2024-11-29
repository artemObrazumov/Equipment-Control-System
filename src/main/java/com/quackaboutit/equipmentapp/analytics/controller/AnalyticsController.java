package com.quackaboutit.equipmentapp.analytics.controller;

import com.quackaboutit.equipmentapp.analytics.service.ExcelTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/excel")
public class AnalyticsController {

    private final ExcelTableService excelTableService;

    @GetMapping("/named_equipment/{id}")
    public ResponseEntity<byte[]> namedEquipmentRepoer(@PathVariable Long id) throws IOException{
        try {
            byte[] excelFile = excelTableService.namedEquipmentReportExcel(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "named_equipment_"+id+".xlsx");
            headers.setContentLength(excelFile.length);

            return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/workplace/{id}")
    public ResponseEntity<byte[]> workplaceReportExcel(@PathVariable Long id) throws IOException{
        try {
            byte[] excelFile = excelTableService.workplaceReportExcel(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "workplace_"+id+".xlsx");
            headers.setContentLength(excelFile.length);

            return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/named_equipment")
    public ResponseEntity<byte[]> namedEquipmentExcel() throws IOException {

        try {
            byte[] excelFile = excelTableService.namedEquipmentExcel();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "named_equipment.xlsx");
            headers.setContentLength(excelFile.length);

            return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contractor/{id}")
    public ResponseEntity<byte[]> contractorExcel(@PathVariable Long id) throws IOException {

        try {
            byte[] excelFile = excelTableService.contractorExcel(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "contractor_"+id+".xlsx");
            headers.setContentLength(excelFile.length);

            return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
