package com.example.internshipone.controller;

import com.example.internshipone.config.HttpConfiguration;
import com.example.internshipone.model.Users;
import com.example.internshipone.service.Services;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.handler.ExceptionHandlingWebHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class Queries {
    @Autowired
    private Services services;

    @GetMapping("/get/All")
    public  String getAll(){
        Services query=new Services();
      return services.getAll(query.getHttpConfiguration());

    }
    @GetMapping("/searchby/{name}")
    public String getExactPhrase(@PathVariable  String name){
       return services.getExactPhrase(name);
    }


    @GetMapping("/searchingByPhrash")
    public ModelAndView searchPhrase(){
        ModelAndView mod=new ModelAndView("tryy");
        Services query=new Services();
        String categoriesList=services.getAll(query.getHttpConfiguration());
        String []A=print(categoriesList);
        mod.addObject("catlist" ,A);
        return  mod;
    }



    public static String highlightSearchParams(String sentence, String keywordsString) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] tokens = sentence.split(" ");
        String[] keywords = keywordsString.split(" ");
        for (String token : tokens) {
            for (String keyword : keywords) {
                if (!"".equals(keyword) && !" ".equals(keyword) && keyword.equals(token) || token.contains(keyword)) {
                    token = "<mark>" + token + "</mark>";
                }
            }
            stringBuilder.append(token);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }


    @PostMapping("/search")
    public ModelAndView search(@ModelAttribute Users users) throws Exception {
        ModelAndView mod=new ModelAndView("serach_phrase");
        Services query=new Services();

        if(services.isNumeric(users.getEntry().trim())){
            System.out.println(users.getEntry()+"---------------------\n");
              Integer sayac=Integer.parseInt(users.getEntry());
            System.out.println(sayac+"after-------------------\n");
            String categoriesList=services.getByYearGrt(sayac);

//            System.out.println(categoriesList);
            Object [] years=year(categoriesList);

            Object []A= returnToJson(categoriesList);
            Object []link=returnlink(categoriesList);
            Object []authors=returnWriters(categoriesList);
            Object []PDF=pdf(categoriesList);
            Object []title=title(categoriesList);
            mod.addObject("year",years);
            mod.addObject("title",title);
            mod.addObject("author",authors) ;
            mod.addObject("catlist" ,A);
            mod.addObject("links",link);
            mod.addObject("pdf",PDF);
            return mod;
        }


        String categoriesList=services.getExactPhrase(users.getEntry().trim());
//        System.out.println(categoriesList);
//        System.out.println(categoriesList);
        Object [] years=year(categoriesList);

        Object []A= returnToJson(categoriesList);
        Object []link=returnlink(categoriesList);
        Object []authors=returnWriters(categoriesList);
        Object []PDF=pdf(categoriesList);
        Object []title=title(categoriesList);
        mod.addObject("year",years);
        mod.addObject("title",title);
        mod.addObject("author",authors) ;
        mod.addObject("catlist" ,A);
        mod.addObject("links",link);
        mod.addObject("pdf",PDF);
        return  mod;
    }




//    @PostMapping("/search")
//    public ModelAndView Tsearch(@ModelAttribute Users users) throws Exception {
//        ModelAndView mod=new ModelAndView("makaleleri");
//        Services query=new Services();
//
//        if(services.isNumeric(users.getEntry())){
//            String categoriesList=services.getByYearGrt(Integer.getInteger(users.getEntry()));
//
////            System.out.println(categoriesList);
//            Object [] years=year(categoriesList);
//
//            Object []A= returnToJson(categoriesList);
//            Object []link=returnlink(categoriesList);
//            Object []authors=returnWriters(categoriesList);
//            Object []PDF=pdf(categoriesList);
//            Object []title=title(categoriesList);
//            mod.addObject("year",years);
//            mod.addObject("title",title);
//            mod.addObject("author",authors) ;
//            mod.addObject("catlist" ,A);
//            mod.addObject("links",link);
//            mod.addObject("pdf",PDF);
//        }
//
//
//        String categoriesList=services.getByLanguage(users.getEntry());
//
////        System.out.println(categoriesList);
//        Object [] years=year(categoriesList);
//        Object [] AA=returnToJsonHight(categoriesList);
//
//        Object []A= returnToJson(categoriesList);
//
//        Object []link=returnlink(categoriesList);
//        Object []authors=returnWriters(categoriesList);
//        Object []PDF=pdf(categoriesList);
//        Object []title=title(categoriesList);
//        mod.addObject("year",years);
//        mod.addObject("title",title);
//        mod.addObject("author",authors) ;
//        mod.addObject("catlist" ,AA);
//        mod.addObject("links",link);
//        mod.addObject("pdf",PDF);
//        return  mod;
//    }





    @GetMapping("/categories")
    public ModelAndView listCategories(){
        ModelAndView mod=new ModelAndView("makaleleri");
        Services query=new Services();
        String categoriesList=services.getAll(query.getHttpConfiguration());
        Object []A= returnToJson(categoriesList);
        Object []link=returnlink(categoriesList);
        Object []authors=returnWriters(categoriesList);
        Object []PDF=pdf(categoriesList);
        Object []title=title(categoriesList);
        Object [] years=year(categoriesList);
        mod.addObject("year",years);
        mod.addObject("title",title);
         mod.addObject("author",authors) ;
        mod.addObject("catlist" ,A);
        mod.addObject("links",link);
        mod.addObject("pdf",PDF);
        return  mod;
    }

    @GetMapping("/sorting")
    public ModelAndView sorting(){
        ModelAndView mod=new ModelAndView("serach_phrase");
        Services query=new Services();
        String categoriesList=services.sortByYear();
//        System.out.println(categoriesList);

        Object []A= returnToJson(categoriesList);
        Object []link=returnlink(categoriesList);
        Object []authors=returnWriters(categoriesList);
        Object []PDF=pdf(categoriesList);
        Object []title=title(categoriesList);
        Object [] years=year(categoriesList);
        mod.addObject("year",years);
        mod.addObject("title",title);
        mod.addObject("author",authors) ;
        mod.addObject("catlist" ,A);
        mod.addObject("links",link);
        mod.addObject("pdf",PDF);
        return  mod;
    }


    @GetMapping("/english")
    public ModelAndView getEN(){
        ModelAndView mod=new ModelAndView("serach_phrase");
        Services query=new Services();
        String categoriesList=services.getByEn();
        System.out.println(categoriesList);

        Object []A= returnToJson(categoriesList);
        Object []link=returnlink(categoriesList);
        Object []authors=returnWriters(categoriesList);
        Object []PDF=pdf(categoriesList);
        Object []title=title(categoriesList);
        Object [] years=year(categoriesList);
        mod.addObject("year",years);
        mod.addObject("title",title);
        mod.addObject("author",authors) ;
        mod.addObject("catlist" ,A);
        mod.addObject("links",link);
        mod.addObject("pdf",PDF);
        return  mod;
    }
    @GetMapping("/turkish")
    public ModelAndView getTr(){
        ModelAndView mod=new ModelAndView("serach_phrase");
        Services query=new Services();
        String categoriesList=services.getByTr();
//        System.out.println(categoriesList);

        Object []A= returnToJson(categoriesList);
        Object []link=returnlink(categoriesList);
        Object []authors=returnWriters(categoriesList);
        Object []PDF=pdf(categoriesList);
        Object []title=title(categoriesList);
        Object [] years=year(categoriesList);
        mod.addObject("year",years);
        mod.addObject("title",title);
        mod.addObject("author",authors) ;
        mod.addObject("catlist" ,A);
        mod.addObject("links",link);
        mod.addObject("pdf",PDF);
        return  mod;
    }




    private   String[] print(String a) {
        String[] result = a.split("_index");
        List<String> wordList = new ArrayList<String>(Arrays.asList(result));
//        System.out.println(wordList.size());
        wordList.remove(0);
        String end[]=new String[wordList.size()];

         int j=0;
        for( String i: wordList){
            StringBuffer sb = new StringBuffer(i);
            sb=sb.delete(0,87);
            String newString = sb.toString().replaceAll("[<>\\[\\]:{},-]", "");
            String newString1 = newString.replaceAll("\"", " : ");
           String kk=newString1.substring(13,200);
            end[j]=kk;
            j++;
//            System.out.println(newString1+"\n\n\n");
        }
        return  end;
    }



        @GetMapping("/")
    public ModelAndView index(){
            ModelAndView mod =new ModelAndView("list-categores");
            return  mod;
    }
    @GetMapping("/homepage")
    public ModelAndView homepage(){
        ModelAndView mod =new ModelAndView("list-categores");
        return  mod;
    }

    private Object [] returnToJson(String a){
        ArrayList<String> end= new ArrayList<>();
        JSONObject obj = new JSONObject(a);
        JSONObject pageName = obj.getJSONObject("hits");
        JSONArray array=new JSONArray(pageName.getJSONArray("hits"));

        for(int i=0; i<array.length(); i++){
            JSONObject al=array.getJSONObject(i);
            JSONObject addd=al.getJSONObject("_source");
            JSONArray author=addd.getJSONArray("authors");

            String kk=addd.get("summary").toString();
            end.add(kk);
//            System.out.println(kk+"\n\n\t\t");
        }
        Object mm[]= end.toArray();
        return  mm;
    }


    private Object[] returnWriters(String a) {
//        ArrayList<String> write= new ArrayList<>();
        ArrayList<String> end= new ArrayList<>();
        JSONObject obj = new JSONObject(a);
        JSONObject pageName = obj.getJSONObject("hits");
        JSONArray array=new JSONArray(pageName.getJSONArray("hits"));

        for(int i=0; i<array.length(); i++){
            JSONObject al=array.getJSONObject(i);
            JSONObject addd=al.getJSONObject("_source");
            JSONArray author=addd.getJSONArray("authors");
//            System.out.println(author.toString()+"\n\n\t\t");
            end.add("YAZAR: "+author.toString());
        }
        Object mm[]= end.toArray();
//        System.out.println(mm.toString());
        return  mm;
    }

    private Object[] returnlink(String a) {
//        ArrayList<String> write= new ArrayList<>();
        ArrayList<String> end= new ArrayList<>();
        JSONObject obj = new JSONObject(a);
        JSONObject pageName = obj.getJSONObject("hits");
        JSONArray array=new JSONArray(pageName.getJSONArray("hits"));

        for(int i=0; i<array.length(); i++){
            JSONObject al=array.getJSONObject(i);
            JSONObject addd=al.getJSONObject("_source");
            String author=addd.get("sourceurl").toString();
//            System.out.println(author+"\n\n\t\t");
            end.add(author);
        }
        Object mm[]= end.toArray();
//        System.out.println(mm.toString());
        return  mm;
    }
    private Object[] pdf(String a) {
//        ArrayList<String> write= new ArrayList<>();
        ArrayList<String> end= new ArrayList<>();
//        ArrayList<String> title1= new ArrayList<>();
        JSONObject obj = new JSONObject(a);
        JSONObject pageName = obj.getJSONObject("hits");
        JSONArray array=new JSONArray(pageName.getJSONArray("hits"));

        for(int i=0; i<array.length(); i++){
            JSONObject al=array.getJSONObject(i);
            JSONObject addd=al.getJSONObject("_source");
            String author=addd.get("pdfurl").toString();
//            String title=addd.get("title").toString();
            System.out.println(author+"\n\n\t\t");
            end.add(author);
//            title1.add(title);
        }
        Object mm[]= end.toArray();
//        System.out.println(mm.toString());
        return  mm;
    }

    private Object[] title(String a) {
//        ArrayList<String> write= new ArrayList<>();
        ArrayList<String> end= new ArrayList<>();

        JSONObject obj = new JSONObject(a);
        JSONObject pageName = obj.getJSONObject("hits");
        JSONArray array=new JSONArray(pageName.getJSONArray("hits"));

        for(int i=0; i<array.length(); i++){
            JSONObject al=array.getJSONObject(i);
            JSONObject addd=al.getJSONObject("_source");

            String title=addd.get("title").toString();
//            System.out.println(title+"\n\n\t\t");
            end.add(title);

        }
        Object mm[]= end.toArray();
//        System.out.println(mm.toString());
        return  mm;
    }

    private Object[] year(String a) {
//        ArrayList<String> write= new ArrayList<>();
        ArrayList<String> end= new ArrayList<>();

        JSONObject obj = new JSONObject(a);
        JSONObject pageName = obj.getJSONObject("hits");
        JSONArray array=new JSONArray(pageName.getJSONArray("hits"));

        for(int i=0; i<array.length(); i++){
            JSONObject al=array.getJSONObject(i);
            JSONObject addd=al.getJSONObject("_source");

            String title=addd.get("year").toString();
//            System.out.println("YEAR: "+title+"\n\n\t\t");
            end.add("YEAR: "+title);

        }
        Object mm[]= end.toArray();

        return  mm;
    }


    private Object [] returnToJsonHight(String a){
        ArrayList<String> end= new ArrayList<>();
        JSONObject obj = new JSONObject(a);
        JSONObject pageName = obj.getJSONObject("hits");
        JSONArray array=new JSONArray(pageName.getJSONArray("hits"));

        for(int i=0; i<array.length(); i++){
            JSONObject al=array.getJSONObject(i);
            JSONObject addd=al.getJSONObject("highlight");
//            JSONArray author=addd.getJSONArray("authors");
            JSONArray summary=addd.getJSONArray("summary");

            String kk=summary.toString().replaceAll("/","");
//            System.out.println(kk+"\n\n\n\n");
            end.add(kk);
//            System.out.println(kk+"\n\n\t\t");
        }
        Object mm[]= end.toArray();
        return  mm;
    }






}


