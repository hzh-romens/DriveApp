package com.example.ausu.erpapp.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/28.
 */
public class PaperBean {

    //将要答题的题目id
    private int currentid;
    //将要答题的题目序号，用于底部显示 33/1022
    private int sortnumber;
    //全部题目数量，用于底部显示 33/1022
    private int allcount;
    //该题目是否为图片题目
    private int isPicQuestion;
    //题干内容，如果是图片题目，该字段放的是图片url
    private String question;
    //题目类型，1 单选题   2 多选题
    private int quettype;
    //题目解析
    private String questanalysis;
    //全部题目选项数组
    private List<optionBean> options;

    public PaperBean jsonToEntity(JsonNode jsonNode) {
        PaperBean paperBean = new PaperBean();
        paperBean.setCurrentid(jsonNode.path("currentid").asInt());
        paperBean.setSortnumber(jsonNode.path("sortnumber").asInt());
        paperBean.setAllcount(jsonNode.path("allcount").asInt());
        paperBean.setIsPicQuestion(jsonNode.path("isPicQuestion").asInt());
        paperBean.setQuestion(jsonNode.path("question").asText());
        paperBean.setQuettype(jsonNode.path("quettype").asInt());
        paperBean.setQuestanalysis(jsonNode.path("questanalysis").asText());
        JsonNode optionList = jsonNode.path("options");
        options = new ArrayList<optionBean>();
        if (optionList != null && optionList.size() != 0) {
            for (int i = 0; i < optionList.size(); i++) {
                JsonNode jsonNode1 = optionList.get(i);
                optionBean optionBean = PaperBean.optionBean.jsonToEntity(jsonNode1);
                options.add(optionBean);
            }
        }
        paperBean.setOptions(options);
        return paperBean;
    }

    public int getCurrentid() {
        return currentid;
    }

    public void setCurrentid(int currentid) {
        this.currentid = currentid;
    }

    public int getSortnumber() {
        return sortnumber;
    }

    public void setSortnumber(int sortnumber) {
        this.sortnumber = sortnumber;
    }

    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }

    public int getIsPicQuestion() {
        return isPicQuestion;
    }

    public void setIsPicQuestion(int isPicQuestion) {
        this.isPicQuestion = isPicQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuettype() {
        return quettype;
    }

    public void setQuettype(int quettype) {
        this.quettype = quettype;
    }

    public String getQuestanalysis() {
        return questanalysis;
    }

    public void setQuestanalysis(String questanalysis) {
        this.questanalysis = questanalysis;
    }

    public List<optionBean> getOptions() {
        return options;
    }

    public void setOptions(List<optionBean> options) {
        this.options = options;
    }

    public static class optionBean {
        private int id;                              //题目选项id
        private int ispictureopt;                     //该选项是否为图片
        private String optcontent;              //选项内容，如果是图片，该字段放的是图片url
        private int isanswer;                          //是否正确答案

        public static optionBean jsonToEntity(JsonNode jsonNode) {
            optionBean optionBean = new optionBean();
            optionBean.setId(jsonNode.path("id").asInt());
            optionBean.setIspictureopt(jsonNode.path("ispictureopt").asInt());
            optionBean.setOptcontent(jsonNode.path("optcontent").asText());
            optionBean.setIsanswer(jsonNode.path("isanswer").asInt());
            return optionBean;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIspictureopt() {
            return ispictureopt;
        }

        public void setIspictureopt(int ispictureopt) {
            this.ispictureopt = ispictureopt;
        }

        public String getOptcontent() {
            return optcontent;
        }

        public void setOptcontent(String optcontent) {
            this.optcontent = optcontent;
        }

        public int getIsanswer() {
            return isanswer;
        }

        public void setIsanswer(int isanswer) {
            this.isanswer = isanswer;
        }
    }
}
