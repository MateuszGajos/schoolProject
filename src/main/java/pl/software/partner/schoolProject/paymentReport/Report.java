package pl.software.partner.schoolProject.paymentReport;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import pl.software.partner.schoolProject.paymentReport.ParentDto;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Report {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal finalSchoolIncome;

    private List<ParentDto> parents;

    public Report(List<ParentDto> monthlyParentDtos) {
        this.parents = monthlyParentDtos;
    }

    public Report(BigDecimal finalSchoolIncome, List<ParentDto> monthlyParentDtos) {
        this.finalSchoolIncome = finalSchoolIncome;
        this.parents = monthlyParentDtos;
    }
}
