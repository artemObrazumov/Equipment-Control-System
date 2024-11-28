package com.quackaboutit.equipmentapp.analytics.service;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.contractor.entity.Contractor;
import com.quackaboutit.equipmentapp.contractor.repository.ContractorRepository;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;
import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelTableService {

    private final NamedEquipmentRepository namedEquipmentRepository;
    private final BaseRepository baseRepository;
    private final ContractorRepository contractorRepository;
    private final JwtService jwtService;

    public byte[] namedEquipmentExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Закрепленная техника");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);

        Row preHeaderRow = sheet.createRow(0);
        Cell preHeaderCell1 = preHeaderRow.createCell(0);
        preHeaderCell1.setCellValue("по состоянию на:");
        Cell preHeaderCell2 = preHeaderRow.createCell(1);
        preHeaderCell2.setCellValue(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        );

        Row headerRow = sheet.createRow(1);
        Cell cell1 = headerRow.createCell(0);
        cell1.setCellValue("Номер машины");
        cell1.setCellStyle(headerCellStyle);

        Cell cell5 = headerRow.createCell(1);
        cell5.setCellValue("Вид техники");
        cell5.setCellStyle(headerCellStyle);

        Cell cell6 = headerRow.createCell(2);
        cell6.setCellValue("Тип техники");
        cell6.setCellStyle(headerCellStyle);

        Cell cell2 = headerRow.createCell(3);
        cell2.setCellValue("Марка");
        cell2.setCellStyle(headerCellStyle);

        Cell cell3 = headerRow.createCell(4);
        cell3.setCellValue("Вид бензина");
        cell3.setCellStyle(headerCellStyle);

        Cell cell4 = headerRow.createCell(5);
        cell4.setCellValue("База");
        cell4.setCellStyle(headerCellStyle);

        User user = jwtService.getUserFromSecurityContextHolder();
        Base base = baseRepository.findBaseByUnit(user.getUnit());
        List<NamedEquipment> equipment = namedEquipmentRepository.findAllByEquipmentBase(base);
        for (int i = 1; i <= equipment.size(); i++) {
            Row row = sheet.createRow(i + 1);
            NamedEquipment item = equipment.get(i-1);
            row.createCell(0).setCellValue(item.getLicensePlate());
            row.createCell(1).setCellValue(item.getEquipmentType().getEquipment().getName());
            row.createCell(2).setCellValue(item.getEquipmentType().getType());
            row.createCell(3).setCellValue(item.getCarBrand());
            row.createCell(4).setCellValue(item.getFuelType());
            row.createCell(5).setCellValue(item.getBase().getAddress());
        }

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        }
    }

    public byte[] contractorExcel(Long id) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Информация о подрядчике");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);

        Row preHeaderRow = sheet.createRow(0);
        Cell preHeaderCell1 = preHeaderRow.createCell(0);
        preHeaderCell1.setCellValue("по состоянию на:");
        Cell preHeaderCell2 = preHeaderRow.createCell(1);
        preHeaderCell2.setCellValue(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        );

        Contractor contractor = contractorRepository.findById(id).orElseThrow();

        Row nameRow = sheet.createRow(1);
        Cell nameRowCell = nameRow.createCell(0);
        nameRowCell.setCellValue(contractor.getName());

        Row INNRow = sheet.createRow(2);
        INNRow.createCell(0).setCellValue("ИНН");
        INNRow.createCell(1).setCellValue(contractor.getInn());

        Row KPPRow = sheet.createRow(3);
        KPPRow.createCell(0).setCellValue("КПП");
        KPPRow.createCell(1).setCellValue(contractor.getKpp());

        Row addressRow = sheet.createRow(4);
        addressRow.createCell(0).setCellValue("Юр. адрес");
        addressRow.createCell(1).setCellValue(contractor.getLegalAddress());

        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }


        Sheet equipmentSheet = workbook.createSheet("Оборудование");

        Row headerRow = equipmentSheet.createRow(0);
        Cell cell1 = headerRow.createCell(0);
        cell1.setCellValue("Номер машины");
        cell1.setCellStyle(headerCellStyle);

        Cell cell5 = headerRow.createCell(1);
        cell5.setCellValue("Вид техники");
        cell5.setCellStyle(headerCellStyle);

        Cell cell6 = headerRow.createCell(2);
        cell6.setCellValue("Тип техники");
        cell6.setCellStyle(headerCellStyle);

        Cell cell2 = headerRow.createCell(3);
        cell2.setCellValue("Марка");
        cell2.setCellStyle(headerCellStyle);

        Cell cell3 = headerRow.createCell(4);
        cell3.setCellValue("Вид бензина");
        cell3.setCellStyle(headerCellStyle);

        List<NamedEquipment> equipment = namedEquipmentRepository.findAllByEquipmentContractorId(id);

        for (int i = 0; i < equipment.size(); i++) {
            Row row = equipmentSheet.createRow(i + 1);
            NamedEquipment item = equipment.get(i);
            row.createCell(0).setCellValue(item.getLicensePlate());
            row.createCell(1).setCellValue(item.getEquipmentType().getEquipment().getName());
            row.createCell(2).setCellValue(item.getEquipmentType().getType());
            row.createCell(3).setCellValue(item.getCarBrand());
            row.createCell(4).setCellValue(item.getFuelType());
        }


        Sheet ordersSheet = workbook.createSheet("Заказы");

        Row orderHeaderRow = ordersSheet.createRow(0);
        Cell cell11 = orderHeaderRow.createCell(0);
        cell11.setCellValue("Номер машины");
        cell11.setCellStyle(headerCellStyle);

        Cell cell51 = orderHeaderRow.createCell(1);
        cell51.setCellValue("Вид техники");
        cell51.setCellStyle(headerCellStyle);

        Cell cell61 = orderHeaderRow.createCell(2);
        cell61.setCellValue("Тип техники");
        cell61.setCellStyle(headerCellStyle);

        Cell cell21 = orderHeaderRow.createCell(3);
        cell21.setCellValue("Марка");
        cell21.setCellStyle(headerCellStyle);

        Cell cell31 = orderHeaderRow.createCell(4);
        cell31.setCellValue("Вид бензина");
        cell31.setCellStyle(headerCellStyle);

//        List<NamedEquipment> equipment = namedEquipmentRepository.findAllByEquipmentContractorId(id);
//
//        for (int i = 0; i < equipment.size(); i++) {
//            Row row = equipmentSheet.createRow(i + 1);
//            NamedEquipment item = equipment.get(i);
//            row.createCell(0).setCellValue(item.getLicensePlate());
//            row.createCell(1).setCellValue(item.getEquipmentType().getEquipment().getName());
//            row.createCell(2).setCellValue(item.getEquipmentType().getType());
//            row.createCell(3).setCellValue(item.getCarBrand());
//            row.createCell(4).setCellValue(item.getFuelType());
//        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        }
    }
}
