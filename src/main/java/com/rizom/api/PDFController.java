package com.rizom.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class PDFController {

    @GetMapping("/download/pdf")
    public void downloadPDF(HttpServletResponse response) throws IOException {
        String pdfPath = "path/to/your/pdf.pdf";

        File pdfFile = new File(pdfPath);
        FileInputStream inputStream = new FileInputStream(pdfFile);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=news.pdf");

        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.flush();
    }
}
