package pl.software.partner.schoolProject.paymentReport.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.software.partner.schoolProject.attendance.Attendance;
import pl.software.partner.schoolProject.child.Child;
import pl.software.partner.schoolProject.parent.Parent;
import pl.software.partner.schoolProject.paymentReport.CalculationService;
import pl.software.partner.schoolProject.school.School;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    private static Child child;
    private static School school;
    private static Parent parent;
    private static Attendance attendance1;
    private static Attendance attendance2;

    private static CalculationService calculationService;

    @BeforeAll
    public static void setUp() {
        calculationService = new CalculationService();
        calculationService.setParsedMinTimeOfArrival((LocalTime.parse("07:00")));
        calculationService.setParsedMaxTimeOfExit((LocalTime.parse("12:00")));
        school = new School(1L, "School", 11.01F, null);
        attendance1 = new Attendance(child, LocalDateTime.parse("2024-01-05T06:59:00"), LocalDateTime.parse("2024-01-05T12:01:00"));
        attendance2 = new Attendance(child, LocalDateTime.parse("2024-01-06T07:00:00"), LocalDateTime.parse("2024-01-06T12:00:00"));
        child = new Child(1L, "Syn", "Kowalski", parent, school, Arrays.asList(attendance1, attendance2));
        parent = new Parent(1L, "Jan", "Kowalski", Arrays.asList(child));
    }

    @Test
    void calculateChildDailyFee() {
        var expected = new BigDecimal(22.02F).setScale(2, RoundingMode.CEILING);

        var result = calculationService.countDailyChildFee(
                child, child.getAttendances().get(0).getEntryDate().toLocalTime(),
                child.getAttendances().get(0).getExitDate().toLocalTime()).setScale(2, RoundingMode.CEILING);

        assertEquals(expected, result);
    }

    @Test
    void countTimeSpentInSchool() {
        var duration = Duration.ofHours(5);
        var expected = duration.plus(Duration.ofMinutes(2));
        var result = attendance1.getTimeSpentInSchool();
        assertEquals(expected, result);
    }
}