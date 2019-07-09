package com.maruszka.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class DuplicateCheck {

    // select MALT_NAME from MALT"
//    private static String SQL_TO_CHECK_DUPLICATES = "select ? from ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean isDuplicate(String fieldName, String tableName, String lookUpObject) {

        String SQL_TO_CHECK_DUPLICATES = String.format("select %s from %s", fieldName, tableName);
        List<String> result = jdbcTemplate.queryForList(SQL_TO_CHECK_DUPLICATES, String.class);
//        List<String> result = jdbcTemplate.queryForList("SELECT MALT_NAME from MALT", String.class);

        List<String> lowerCaseResult = result.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        if (lowerCaseResult.contains(lookUpObject.toLowerCase())) {
            return true;
        } else {
            return false;
        }

    }


}
