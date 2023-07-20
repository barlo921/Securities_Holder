package com.barlo.moex_rest.service;

import com.barlo.moex_rest.exception.NoResultsException;
import com.barlo.moex_rest.exception.SecurityNotFoundException;
import com.barlo.moex_rest.model.Board;
import com.barlo.moex_rest.model.MarketData;
import com.barlo.moex_rest.model.Security;
import com.barlo.moex_rest.model.SecuritySpecification;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@NoArgsConstructor
public class DataService {

    public List<Security> mapSecuritiesToList(String securities, String search) throws JSONException {
        JSONObject obj = new JSONObject(securities).getJSONObject("securities");
        JSONArray columns = obj.getJSONArray("columns");
        JSONArray data = obj.getJSONArray("data");

        if (data.length() == 0) {
            throw new NoResultsException("No results for search: " + search);
        }

        Map<String, String> flatData = new HashMap<String, String>();
        List<Security> securityList = new ArrayList<Security>();

        for (int i=0; i<data.length(); i++) {

            JSONArray list = data.getJSONArray(i);

            for (int j = 0; j < list.length(); j++) {
                flatData.put(columns.getString(j), list.getString(j));
            }
            securityList.add(Security
                    .builder()
                    .id(tryParseInt(flatData.get("id")))
                    .secId(flatData.get("secid"))
                    .shortname(flatData.get("shortname"))
                    .regNumber(flatData.get("regnumber"))
                    .name(flatData.get("name"))
                    .isin(flatData.get("isin"))
                    .isTraded(flatData.get("is_traded"))
                    .emitentId(tryParseInt(flatData.get("emitent_id")))
                    .emitentTitle(flatData.get("emitent_title"))
                    .emitentInn(flatData.get("emitent_inn"))
                    .emitentOkpo(flatData.get("emitent_okpo"))
                    .gosReg(flatData.get("gosreg"))
                    .type(flatData.get("type"))
                    .group(flatData.get("group"))
                    .primaryBoardId(flatData.get("primary_boardid"))
                    .marketPriceBoardId(flatData.get("marketprice_boardid"))
                    .build());

            flatData.clear();
        }

        return securityList;
    }

    public SecuritySpecification mapSecuritySpecification(String security, String secid) throws JSONException {

        JSONArray descriptionData =
                new JSONObject(security)
                        .getJSONObject("description")
                        .getJSONArray("data");

        if (descriptionData.length() == 0) {
            throw new SecurityNotFoundException("Can't find security with Sec ID: " + secid);
        }

        Map<String, String> descriptionTitles = new HashMap<String, String>();
        Map<String, String> descriptionValues = new HashMap<String, String>();

        for (int i=0; i<descriptionData.length(); i++) {
            JSONArray arr = descriptionData.getJSONArray(i);
            descriptionTitles.put(arr.getString(0), arr.getString(1));
            descriptionValues.put(arr.getString(0), arr.getString(2));
        }

        JSONArray boardsData =
                new JSONObject(security)
                        .getJSONObject("boards")
                        .getJSONArray("data");

        List<Board> boards = new ArrayList<Board>();

        for (int i=0; i<boardsData.length(); i++) {
            JSONArray arr = boardsData.getJSONArray(i);
            boards.add(
                    Board
                            .builder()
                            .boardId(arr.getString(0))
                            .title(arr.getString(1))
                            .market(arr.getString(2))
                            .engine(arr.getString(3))
                            .build());
        }

        return SecuritySpecification
                .builder()
                .secId(descriptionValues.get("SECID"))
                .name(descriptionValues.get("NAME"))
                .isin(descriptionValues.get("ISIN"))
                .regNumber(descriptionValues.get("REGNUMBER"))
                .group(descriptionValues.get("GROUP"))
                .type(descriptionValues.get("TYPE"))
                .emitentId(tryParseInt(descriptionValues.get("EMITTER_ID")))
                .issueSize(tryParseInt(descriptionValues.get("ISSUESIZE")))
                .faceValue(tryParseInt(descriptionValues.get("FACEVALUE")))
                .issueDate(LocalDate.parse(descriptionValues.get("ISSUEDATE")))
                .latName(descriptionValues.get("LATNAME"))
                .listLevel(tryParseInt(descriptionValues.get("LISTLEVEL")))
                .isQualifiedInvestors(Boolean.parseBoolean(descriptionValues.get("ISQUALIFIEDIVESTORS")))
                .isMorningSession(Boolean.parseBoolean(descriptionValues.get("MORNINGSESSION")))
                .isEveningSession(Boolean.parseBoolean(descriptionValues.get("EVENINGSESSION")))
                .typeName(descriptionValues.get("TYPENAME"))
                .groupName(descriptionValues.get("GROUPNAME"))
                .titles(descriptionTitles)
                .boards(boards)
                .build();
    }

    public MarketData flatMarketData(String marketdata) throws JSONException {

        JSONObject obj = new JSONObject(marketdata).getJSONObject("marketdata");
        JSONArray columns = obj.getJSONArray("columns");
        JSONArray data = obj.getJSONArray("data").getJSONArray(0);

        Map<String, Double> mappedData = new HashMap<String, Double>();

        for (int i=0; i<columns.length(); i++) {
            mappedData.put(columns.getString(i), data.getDouble(i));
        }

        return MarketData
                .builder()
                .open(mappedData.get("OPEN"))
                .low(mappedData.get("LOW"))
                .high(mappedData.get("HIGH"))
                .last(mappedData.get("LAST"))
                .build();
    }

    private Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException nfe) {
            return null;
        }
    }
}
