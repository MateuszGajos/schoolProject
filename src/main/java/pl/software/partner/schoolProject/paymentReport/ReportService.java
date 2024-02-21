package pl.software.partner.schoolProject.paymentReport;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.software.partner.schoolProject.attendance.Attendance;
import pl.software.partner.schoolProject.child.Child;
import pl.software.partner.schoolProject.parent.Parent;
import pl.software.partner.schoolProject.parent.ParentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ParentService parentService;
    private final CalculationService calculationService;

    public Report getMonthlyReportForParent(Long parentId, int month) {
        var parent = parentService.getParentById(parentId);
        var childAttendanceFees = getChildAttendanceFeesForParent(parent, month);
        return new Report(Collections.singletonList(new ParentDto(parent, childAttendanceFees)));
    }

    public Report getMonthlyReportForSchool(Long schoolId, int month) {
        var parents = parentService.getParentsBySchool(schoolId);
        var dtoList = new ArrayList<ParentDto>();
        for (Parent parent : parents) {
            var childAttendanceFees = getChildAttendanceFeesForParent(parent, month);
            dtoList.add(new ParentDto(parent, childAttendanceFees));
        }
        var schoolMonthlyIncome = calculateFinalSchoolIncome(dtoList);
        return new Report(schoolMonthlyIncome, dtoList);
    }

    private BigDecimal calculateFinalSchoolIncome(ArrayList<ParentDto> dtoList) {
        return dtoList.stream().map(ParentDto::getTotalFee).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ChildAttendanceFee> getChildAttendanceFeesForParent(Parent parent, int month) {
        List<ChildAttendanceFee> childrenFees = new ArrayList<>();
        for (Child child : parent.getChildren()) {
            var childAttendanceFee = calculationService.calculateMonthlyPaymentForChild(child, month);
            childrenFees.add(childAttendanceFee);
        }
        return childrenFees;
    }
}

record ChildAttendanceFee(Long childId, List<AttendanceFee> fees) {

}

record AttendanceFee(Attendance attendance, BigDecimal fee) {

}

