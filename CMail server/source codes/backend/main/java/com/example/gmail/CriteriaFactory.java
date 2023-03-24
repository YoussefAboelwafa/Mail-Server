package com.example.gmail;

public class CriteriaFactory {
    public Criteria createCriteria(String type){
        if(type.equals("subject")){
            return new CriteriaSubject();
        }
        else if(type.equals("sender")){
            return new CriteriaSender();
        }

        else return null;

    }
}
