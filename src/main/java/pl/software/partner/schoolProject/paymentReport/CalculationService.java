package pl.software.partner.schoolProject.paymentReport;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.software.partner.schoolProject.child.Child;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter
@RequiredArgsConstructor
public class CalculationService {

    @Value("${min.time.of.arrival}")
    private String minTimeOfArrival;
    @Value("${max.time.of.exit}")
    private String maxTimeOfExit;

    private LocalTime parsedMinTimeOfArrival;
    private LocalTime parsedMaxTimeOfExit;

    @PostConstruct
    private void init() {
        parsedMinTimeOfArrival = LocalTime.parse(minTimeOfArrival);
        parsedMaxTimeOfExit = LocalTime.parse(maxTimeOfExit);
    }

    public ChildAttendanceFee calculateMonthlyPaymentForChild(Child child, int month) {
        List<AttendanceFee> fees = new ArrayList<>();
        var attendances = child.getAttendances().stream().filter(attendance -> attendance.getExitDate().getMonth().getValue() == month).collect(Collectors.toList());
        for (var attendance : attendances) {
            var payment = countDailyChildFee(child, attendance.getEntryDate().toLocalTime(), attendance.getExitDate().toLocalTime());
            attendance.setTimeSpentInSchool(Duration.between(attendance.getEntryDate(), attendance.getExitDate()));
            fees.add(new AttendanceFee(attendance, payment));
        }
        return new ChildAttendanceFee(child.getId(), fees);
    }

    public BigDecimal countDailyChildFee(Child child, LocalTime entryDate, LocalTime exitDate) {
        var schoolRate = getSchoolRate(child);
        var additionalHours = 0;
        if (entryDate.isBefore(parsedMinTimeOfArrival)) {
            additionalHours += getAdditionalHours(parsedMinTimeOfArrival, entryDate);
        }
        if (exitDate.isAfter(parsedMaxTimeOfExit)) {
            additionalHours += getAdditionalHours(parsedMaxTimeOfExit, exitDate);
        }
        return schoolRate.multiply(BigDecimal.valueOf(additionalHours));
    }

    private BigDecimal getSchoolRate(Child child) {
        return BigDecimal.valueOf(child.getSchool().getHourPrice());
    }

    private int getAdditionalHours(LocalTime minTimeOfArrival, LocalTime entryDate) {
        var hours = 0;
        Duration duration = (Duration.between(minTimeOfArrival, entryDate)).abs();
        hours += duration.toHours();
        hours += duration.toMinutes() % 60 > 0 ? 1 : 0;
        return hours;
    }

}
