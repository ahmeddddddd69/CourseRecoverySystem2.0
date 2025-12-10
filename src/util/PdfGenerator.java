package crs.util;

import crs.model.AcademicReport;
import crs.model.Course;
import crs.model.Grade;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class PdfGenerator {

    public static void generateAcademicReport(AcademicReport report, int sem, int year) {

        try {
            String path = PdfPathHelper.generateReportPath(
                    report.getStudent().getStudentId(), sem, year);

            Document doc = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();

            // -------------------------------
            // TITLE
            // -------------------------------
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph("Academic Performance Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);
            doc.add(new Paragraph(" "));

            // -------------------------------
            // STUDENT INFO
            // -------------------------------
            doc.add(new Paragraph("Student Name : " +
                    report.getStudent().getFirstName() + " " +
                    report.getStudent().getLastName()));
            doc.add(new Paragraph("Student ID   : " + report.getStudent().getStudentId()));
            doc.add(new Paragraph("Program      : " + report.getStudent().getMajor()));
            doc.add(new Paragraph("Semester     : " + report.getSemester()));
            doc.add(new Paragraph(" "));

            // -------------------------------
            // TABLE SETUP
            // -------------------------------
            PdfPTable table = new PdfPTable(new float[]{1.5f, 3.5f, 1f, 1f, 1f});
            table.setWidthPercentage(100);

            // Table Header
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

            table.addCell(new PdfPCell(new Phrase("Course Code", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Course Title", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Credit Hours", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Grade", headerFont)));
            table.addCell(new PdfPCell(new Phrase("Grade Point", headerFont)));

            // -------------------------------
            // COURSE ROWS WITH GRADES
            // -------------------------------
            for (Course c : report.getCourses()) {

                table.addCell(c.getCourseId());
                table.addCell(c.getCourseName());
                table.addCell(String.valueOf(c.getCredits()));

                // Find matching grade for this course
                Grade matchedGrade = null;
                for (Grade g : report.getGrades()) {
                    if (g.getCourseId().equalsIgnoreCase(c.getCourseId())) {
                        matchedGrade = g;
                        break;
                    }
                }

                // Extract grade + gradePoint
                String gradeLetter = (matchedGrade != null)
                        ? matchedGrade.getGradeLetter()
                        : "N/A";

                String gradePoint = (matchedGrade != null)
                        ? String.format("%.2f", matchedGrade.getGradePoint())
                        : "N/A";

                table.addCell(gradeLetter);
                table.addCell(gradePoint);
            }

            doc.add(table);
            doc.add(new Paragraph(" "));

            // -------------------------------
            // CGPA (2 decimal places)
            // -------------------------------
            String formattedCgpa = String.format("%.2f", report.getCgpa());
            Paragraph cgpaText = new Paragraph("CGPA : " + formattedCgpa);
            cgpaText.setSpacingBefore(10);
            doc.add(cgpaText);

            doc.close();
            System.out.println("PDF Generated Successfully → " + path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
