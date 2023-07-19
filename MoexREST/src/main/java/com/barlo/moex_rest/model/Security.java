package com.barlo.moex_rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Security {
    private Integer id;
    protected String secId;
    protected String shortname;
    protected String regNumber;
    protected String name;
    protected String isin;
    private String isTraded;
    protected Integer emitentId;
    private String emitentTitle;
    private String emitentInn;
    private String emitentOkpo;
    private String gosReg;
    protected String type;
    protected String group;
    private String primaryBoardId;
    private String marketPriceBoardId;
}
