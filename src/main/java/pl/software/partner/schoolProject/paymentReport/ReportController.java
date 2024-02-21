package pl.software.partner.schoolProject.paymentReport;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/schools/{schoolId}/months/{month}")
    public ResponseEntity<Report> getMonthlyBillingBySchool(@PathVariable Long schoolId, @PathVariable int month) {
        return ResponseEntity.ok(reportService.getMonthlyReportForSchool(schoolId, month));
    }

    @GetMapping("/parents/{parentId}/months/{month}")
    public ResponseEntity<Report> getMonthlyBillingByParent(@PathVariable Long parentId, @PathVariable int month) {
        return ResponseEntity.ok(reportService.getMonthlyReportForParent(parentId, month));
    }
}
