package com.example.internshipone.service;
import com.example.internshipone.config.HttpConfiguration;

public interface interfaces {
    String getAll(HttpConfiguration httpConfiguration);
    String getExactPhrase(String string);
   String getByLanguage(String string);
    String getByYearGrt(Integer integer);
    void getByYearLd(Integer integer);
    void getByRange(Integer starting, Integer ending);
    void getByKeyword(String string);
    void getByTitle(String string);
    void getBylangaugeAndYear(String lag, Integer year);


    String getByTr();
    String sortByYear();
    String getByEn();

    void deleteByYear(Integer integer);
    void getByName(Integer integer);
    void getByIndex(Integer starting, Integer ending);
    void getByPhrase(String string);
    void getByPhraseWithErrors(String string);
    void aggregationQueries(String lag, Integer year);
}
