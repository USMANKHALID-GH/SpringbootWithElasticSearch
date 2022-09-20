package com.example.internshipone.service;

import com.example.internshipone.config.HttpConfiguration;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Data
@Service
@Slf4j
public class Services implements  interfaces{
    private Scanner input = new Scanner("System.in");

    private  HttpConfiguration httpConfiguration= new HttpConfiguration();
    @Override
    public String getAll(HttpConfiguration httpConfiguration) {
        String jsonQuery4All="{\n" +
                "  \"_source\": {\n" +
                "    \"includes\": [\n" +
               "      \"sourceurl\",\n" +
                "      \"pdfurl\",\n" +
                "      \"year\",\n" +
               "      \"authors\",\n" +
                "      \"summary\",\n" +
                "      \"title\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}";
        log.info(jsonQuery4All);
      return httpConfiguration.responseHttp(jsonQuery4All,"yokulusaltez.gov");
    }

    @Override
    public String getExactPhrase(String string) {
        String jsonQuery4All= "{\n" +
                "  \"_source\": {\n" +
                "    \"includes\": [\n" +
                "      \"pdfurl\",\n" +
                "      \"year\",\n" +
                "      \"sourceurl\",\n" +
                "      \"authors\",\n" +
                "      \"summary\",\n" +
                "      \"title\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"query\": {\n" +
                "    \"multi_match\": {\n" +
                "        \"query\":\n" +
                ""+        "\""+string+"\"" +""+
                "        , \"fields\": [\"summary\",\"authors\" ,\"year\" ,\"title\" ,\"keywords\"],\n" +
                "       \"slop\": 3,\n" +
                "          \"operator\" : \"and\", \n" +
                "            \"fuzziness\": 2\n" +
                "    }\n" +
                "  },\n" +
                "  \"highlight\": {\n" +
                "    \"fields\": {\n" +
                "      \"title\": {},\n" +
                "      \"summary\": {},\n" +
                "      \"authors\": {}\n" +
                "    }\n" +
                "  }\n" +
                "}";
        log.info(jsonQuery4All);

//        String l="{\n" +
//                "    \"query\": {\n" +
//                "        \"multi_match\" : {\n" +
//                "            \"query\" : \"heursitic reserch\",\n" +
//                "            \"fields\": [\"phrase\",\"position\"],\n" +
//                "            \"fuzziness\": 2\n" +
//                "        }\n" +
//                "    },\n" +
//                "    \"size\": 10\n" +
//                "}" ;



       return  httpConfiguration.responseHttp(jsonQuery4All,"yokulusaltez.gov");
    }

    @Override
    public String getByLanguage(String string) {

        String jsonQuery4All= "{\n" +
                "  \"_source\": {\n" +
                "    \"includes\": [\n" +
                "      \"pdfurl\",\n" +
                "      \"year\",\n" +
                "      \"sourceurl\",\n" +
                "      \"authors\",\n" +
                "      \"summary\",\n" +
                "      \"title\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"query\": {\n" +
                "    \"multi_match\": {\n" +
                "        \"query\":\n" +
                ""+        "\""+string+"\"" +""+
                "        , \"fields\": [\"summary\",\"authors\" ,\"year\" ,\"title\"],\n" +
                "            \"fuzziness\": 2\n" +
                "    }\n" +
                "  },\n" +
                "  \"highlight\": {\n" +
                "    \"fields\": {\n" +
                "      \"title\": {},\n" +
                "      \"summary\": {},\n" +
                "      \"authors\": {},\n" +
                "      \"pdfurl\": {},\n" +
                "      \"sourceurl\": {},\n" +
                "      \"year\": {}\n" +
                "    }\n" +
                "  }\n" +
                "}";
        log.info(jsonQuery4All);
        return httpConfiguration.responseHttp(jsonQuery4All,"yokulusaltez.gov");


    }

    public  boolean isNumeric(String string) {
            int intValue;



            if(string == null || string.equals("")) {
                System.out.println("String cannot be parsed, it is null or empty.");
                return false;
            }

            try {
                intValue = Integer.parseInt(string);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Input String cannot be parsed to Integer.");
            }
            return false;
        }


    @Override
    public String getByYearGrt(Integer integer) {
        log.info(integer+" this is integer was entered\n\n");
        String a="{\n" +
                "  \"_source\": {\n" +
                "    \"includes\": [\n" +
                "      \"keywords\",\n" +
                "      \"pdfurl\",\n" +
                "      \"sourceurl\",\n" +
                "      \"year\",\n" +
                "      \"summary\",\n" +
                "      \"authors\",\n" +
                "      \"title\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"query\": {\n" +
                "   \"match_phrase\": {\n" +
                "     \"year\": {\n" +
                "       \"query\":" +
                ""+        "\""+integer+"\"" +","+
                "       \"slop\": 7\n" +
                "     }\n" +
                "   }\n" +
                "  }\n" +
                "}\n";
        log.info(a);

        return  httpConfiguration.responseHttp(a,"yokulusaltez.gov");
    }

    @Override
    public void getByYearLd(Integer integer) {

    }

    @Override
    public void getByRange(Integer starting, Integer ending) {

    }

    @Override
    public void getByKeyword(String string) {

    }

    @Override
    public void getByTitle(String string) {

    }

    @Override
    public void getBylangaugeAndYear(String lag, Integer year) {

    }

    @Override
    public void deleteByYear(Integer integer) {

    }

    @Override
    public String sortByYear() {
        String a="{\n" +
                "  \"sort\": [\n" +
                "    {\n" +
                "      \"year\": {\n" +
                "        \"order\": \"asc\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        log.info(a);
        return  httpConfiguration.responseHttp(a,"yokulusaltez.gov");
    }

    @Override
    public String getByEn() {
        String string="en";
        String a="{\n" +
                "  \"_source\": {\n" +
                "    \"includes\": [\n" +
                "      \"pdfurl\",\n" +
                "      \"year\",\n" +
                "      \"sourceurl\",\n" +
                "      \"authors\",\n" +
                "      \"summary\",\n" +
                "      \"title\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"query\": {\n" +
                "    \"terms\": {\n" +
                "      \"languages\": [\n" +
                ""+        "\""+string+"\"" +""+
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        log.info(a);
        return  httpConfiguration.responseHttp(a,"yokulusaltez.gov");
    }

    @Override
    public String getByTr() {
        String string="tr";
        String a="{\n" +
                "  \"query\": {\n" +
                "    \"terms\": {\n" +
                "      \"languages\": [\n" +
                ""+        "\""+string+"\"" +""+
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        log.info(a);
        return  httpConfiguration.responseHttp(a,"yokulusaltez.gov");

    }

    @Override
    public void getByName(Integer integer) {

    }

    @Override
    public void getByIndex(Integer starting, Integer ending) {

    }

    @Override
    public void getByPhrase(String string) {

    }

    @Override
    public void getByPhraseWithErrors(String string) {

    }

    @Override
    public void aggregationQueries(String lag, Integer year) {

    }
}
