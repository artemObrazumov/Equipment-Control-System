package com.quackaboutit.equipmentapp.analytics.service;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.contractor.entity.Contractor;
import com.quackaboutit.equipmentapp.contractor.repository.ContractorRepository;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;
import com.quackaboutit.equipmentapp.equipment.exceptions.EquipmentNotFound;
import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
import com.quackaboutit.equipmentapp.tracks.entity.ArrivalPoint;
import com.quackaboutit.equipmentapp.tracks.entity.Track;
import com.quackaboutit.equipmentapp.tracks.repository.ArrivalPointRepository;
import com.quackaboutit.equipmentapp.tracks.repository.TrackRepository;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.service.JwtService;
import com.quackaboutit.equipmentapp.utils.ObjectForReport;
import com.quackaboutit.equipmentapp.utils.ObjectForWorkplaceReport;
import com.quackaboutit.equipmentapp.workplace.entity.Workplace;
import com.quackaboutit.equipmentapp.workplace.exceptions.WorkplaceNotFound;
import com.quackaboutit.equipmentapp.workplace.repository.WorkplaceRepository;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelTableService {

    private final NamedEquipmentRepository namedEquipmentRepository;
    private final BaseRepository baseRepository;
    private final ContractorRepository contractorRepository;
    private final TrackRepository trackRepository;
    private final JwtService jwtService;
    private final WorkplaceRepository workplaceRepository;
    private final ArrivalPointRepository arrivalPointRepository;

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
            NamedEquipment item = equipment.get(i - 1);
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

    public byte[] workplaceReportExcel(Long id) throws IOException{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Информация");

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


        Workplace workplace = workplaceRepository.findById(id).orElseThrow(
            () -> new WorkplaceNotFound()
        );

        Row preHeaderRow = sheet.createRow(0);
        Cell preHeaderCell1 = preHeaderRow.createCell(0);
        preHeaderCell1.setCellValue(workplace.getAddress());
        Cell preHeaderCell2 = preHeaderRow.createCell(1);
        preHeaderCell2.setCellValue("по состоянию на:");
        Cell preHeaderCell3 = preHeaderRow.createCell(2);
        preHeaderCell3.setCellValue(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        );

        Row headerRow = sheet.createRow(1);

        // машины + водитель + дата работы +факт въезд/выезд, расстояние, бензин, стоимость

        List<String> lines = List.of("Номер автомобиля", "Водитель",
                                    "Дата проведения работы", "Время приезда",
                                    "Время завершения", "Преодолённое расстояние",
                                    "Затраченный бензин", "Стоимость работ");

        for(int i = 0; i != lines.size(); ++i){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(lines.get(i));
            cell.setCellStyle(headerCellStyle);
        }

        List<ObjectForWorkplaceReport> objs = new ArrayList<>();

        arrivalPointRepository.findByAddress(workplace.getAddress()).forEach(arrivalPoint -> {
            objs.add(new ObjectForWorkplaceReport(trackRepository.findByArrivalPoint(arrivalPoint), arrivalPoint));
        });

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        for(int i = 0; i != objs.size(); ++i){
            Row newRow = sheet.createRow(i + 2);

            var track = objs.get(i).getTrack();
            var arrialPoint = objs.get(i).getArrivalPoint();

            List<String> params = new ArrayList<>();
            if(track != null && !track.getIsActive()){
                params = List.of(track.getLicensePlateNumber(), track.getDriver(),
                        track.getDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")), arrialPoint.getRealArrivalTime().format(formatter),
                        arrialPoint.getRealOutTime().format(formatter), (arrialPoint.getKmOnEnd() - arrialPoint.getKmOnStart())+"",
                        (arrialPoint.getFuelOnStart() - arrialPoint.getFuelOnEnd())+"", ""+(track.getNamedEquipment().getPaymentHourly() * arrialPoint.getPlanWorkDuration().toMillis()/3600000));
            }else{
                params = List.of(track.getLicensePlateNumber(), "",
                        track.getDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")), "",
                        "", "",
                        "", ""+(track.getNamedEquipment().getPaymentHourly() * arrialPoint.getPlanWorkDuration().toMillis()/3600000));
            }

            for(int j = 0; j != lines.size(); ++j){
                newRow.createCell(j).setCellValue(params.get(j));
            }
        }

        for(int i = 0; i != lines.size(); ++i){
            sheet.autoSizeColumn(i);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        }
    }

    public byte[] namedEquipmentReportExcel(Long id) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Информация");

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

        Row headerRow = sheet.createRow(0);
        Cell cell1 = headerRow.createCell(0);
        cell1.setCellValue("Номер машины");
        cell1.setCellStyle(headerCellStyle);

        Cell cell2 = headerRow.createCell(1);
        cell2.setCellValue("Вид техники");
        cell2.setCellStyle(headerCellStyle);

        Cell cell3 = headerRow.createCell(2);
        cell3.setCellValue("Тип техники");
        cell3.setCellStyle(headerCellStyle);

        Cell cell4 = headerRow.createCell(3);
        cell4.setCellValue("Марка");
        cell4.setCellStyle(headerCellStyle);

        Cell cell5 = headerRow.createCell(4);
        cell5.setCellValue("Вид бензина");
        cell5.setCellStyle(headerCellStyle);

        Cell cell6 = headerRow.createCell(5);
        cell6.setCellValue("База");
        cell6.setCellStyle(headerCellStyle);

        var namedEquipment = namedEquipmentRepository.findById(id).
                orElseThrow(() -> new EquipmentNotFound());

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(namedEquipment.getLicensePlate());
        row.createCell(1).setCellValue(namedEquipment.getEquipmentType().getEquipment().getName());
        row.createCell(2).setCellValue(namedEquipment.getEquipmentType().getType());
        row.createCell(3).setCellValue(namedEquipment.getCarBrand());
        row.createCell(4).setCellValue(namedEquipment.getFuelType());
        row.createCell(5).setCellValue(namedEquipment.getBase().getAddress());

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        Sheet logisticSheet = workbook.createSheet("Поездки");

        Row orderHeaderRow = logisticSheet.createRow(0);


        List<String> lines = List.of("Адрес объекта", "Дата работы",
                "Плановое время выезда", "Фактическое время выезда",
                "Плановое время прибытия", "Фактическое время прибытия",
                "Время ожидания", "Время работы",
                "Топливо (нач.)", "Топливо (кон.)",
                "Пробег (нач.)", "Пробег (кон.)",
                "Дистанция", "Стоимость");

        for (int i = 0; i != lines.size(); ++i) {
            Cell cell = orderHeaderRow.createCell(i);
            cell.setCellValue(lines.get(i));
            cell.setCellStyle(headerCellStyle);
        }

        List<ObjectForReport> objs = new ArrayList<>();
        trackRepository.findByNamedEquipment(namedEquipment).forEach(track -> {
            track.getArrivalPoint().forEach(arrialPoint -> {
                objs.add(new ObjectForReport(track, arrialPoint));
            });
        });

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

        for (int i = 0; i != objs.size(); ++i) {
            var arrialPoint = objs.get(i).getArrivalPoint();
            var track = objs.get(i).getTrack();
            Row newRow = logisticSheet.createRow(i + 1);
            List<String> params = new ArrayList<>();
            if (!track.getIsActive()) {
                params = List.of(arrialPoint.getAddress(), track.getDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                        arrialPoint.getPlanOutTime().format(formatter), arrialPoint.getRealOutTime().format(formatter),
                        arrialPoint.getPlanArrivalTime().format(formatter), arrialPoint.getRealArrivalTime().format(formatter),
                        arrialPoint.getWaitTime().format(DateTimeFormatter.ofPattern("HH:mm")), String.format("%d:%02d", arrialPoint.getPlanWorkDuration().toHours(), arrialPoint.getPlanWorkDuration().toMinutesPart()),
                        arrialPoint.getFuelOnStart().toString(), arrialPoint.getFuelOnEnd().toString(),
                        arrialPoint.getKmOnStart().toString(), arrialPoint.getKmOnEnd().toString(),
                        (arrialPoint.getKmOnEnd() - arrialPoint.getKmOnStart()) + "", "" + (namedEquipment.getPaymentHourly() * arrialPoint.getPlanWorkDuration().toMillis() / 3600000));
            } else {
                params = List.of(arrialPoint.getAddress(), track.getDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                        arrialPoint.getPlanOutTime().format(formatter), "",
                        arrialPoint.getPlanArrivalTime().format(formatter), "",
                        "", String.format("%d:%02d", arrialPoint.getPlanWorkDuration().toHours(), arrialPoint.getPlanWorkDuration().toMinutesPart()),
                        "", "",
                        "", "",
                        arrialPoint.getDistance().toString(), "" + (namedEquipment.getPaymentHourly() * arrialPoint.getPlanWorkDuration().toMillis() / 3600000));
            }
            for (int j = 0; j != lines.size(); ++j) {
                newRow.createCell(j).setCellValue(params.get(j));
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            logisticSheet.autoSizeColumn(i);
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

    public byte[] trackSheetExcel(Long id) throws IOException {
        Track track = trackRepository.findById(id).orElseThrow();
        String excelFilePath = "sheet.xls";

        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new HSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");

        sheet.getRow(10).getCell(12).setCellValue(track.getDriver());
        sheet.getRow(32).getCell(75).setCellValue(track.getDriver());
        sheet.getRow(39).getCell(74).setCellValue(track.getDriver());
        sheet.getRow(62).getCell(28).setCellValue(track.getDriver());
        sheet.getRow(64).getCell(75).setCellValue(track.getDriver());
        sheet.getRow(43).getCell(70).setCellValue(track.getNamedEquipment().getFuelType());
        sheet.getRow(39).getCell(26).setCellValue( formatter.format(track.getArrivalPoint().getFirst().getPlanOutTime()) );
        sheet.getRow(46).getCell(26).setCellValue( formatter.format(track.getArrivalPoint().getLast().getPlanArrivalTime()) );
        sheet.getRow(8).getCell(34).setCellValue(track.getNamedEquipment().getLicensePlate());
        sheet.getRow(7).getCell(21).setCellValue(track.getNamedEquipment().getCarBrand());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            workbook.close();
            return outputStream.toByteArray();
        }
    }
}
