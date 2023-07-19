package com.barlo.moex_rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SecuritySpecification extends Security {
    private Integer issueSize;
    private Integer faceValue;
    private LocalDate issueDate;
    private String latName;
    private Integer listLevel;
    private boolean isQualifiedInvestors;
    private boolean isMorningSession;
    private boolean isEveningSession;
    private String typeName;
    private String groupName;
    private Map<String, String> titles;
    private List<Board> boards;
}
