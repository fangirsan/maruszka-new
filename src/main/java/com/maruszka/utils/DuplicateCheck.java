package com.maruszka.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class DuplicateCheck {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean isDuplicate(String fieldName, String tableName, String lookUpObject, String type) {

        String SQL_TO_CHECK_DUPLICATES = String.format("select %s from %s where TYPE = '%s' and NAME = '%s'", fieldName, tableName, type, lookUpObject);
        List<String> result = jdbcTemplate.queryForList(SQL_TO_CHECK_DUPLICATES, String.class);

        List<String> lowerCaseResult = result.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        return lowerCaseResult.contains(lookUpObject.toLowerCase());
    }

    public boolean isDuplicate(Integer batchId) {

        return false;
    }

}
