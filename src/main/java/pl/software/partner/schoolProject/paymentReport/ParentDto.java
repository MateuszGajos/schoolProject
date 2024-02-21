package pl.software.partner.schoolProject.paymentReport;

import lombok.Getter;
import lombok.Setter;
import pl.software.partner.schoolProject.parent.Parent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ParentDto implements Serializable {

    private Long id;
    private String fullName;
    private BigDecimal totalFee;
    private final List<ChildAttendanceFee> childAttendanceFees;

    public ParentDto(Parent parent, List<ChildAttendanceFee> childAttendanceFees) {
        this.id = parent.getId();
        this.fullName = parent.getFullName();
        this.childAttendanceFees = childAttendanceFees;
        this.totalFee = calculateTotalFee(childAttendanceFees);
    }

    private BigDecimal calculateTotalFee(List<ChildAttendanceFee> childAttendanceFees) {
        var totalFee = BigDecimal.ZERO;
        for (ChildAttendanceFee childAttendanceFee : childAttendanceFees) {
            for (AttendanceFee attendanceFee : childAttendanceFee.fees()) {
                totalFee = totalFee.add(attendanceFee.fee());
            }
        }
        return totalFee;
    }
}
