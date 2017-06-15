package com.example.ausu.erpapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.PaperBean;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.NetUtils;
import com.example.ausu.erpapp.view.CloudImageView;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lanxumit on 2016/7/31.
 * 练习中的单选题
 */
public class SingleFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private CheckBox selectedA, selectedC, selectedB, selectedD;
    private TextView body, sureBtn, answer, analysis, previousPage, pageNumber, nextPage;
    private LinearLayout answerLayout;
    private CloudImageView imageBody;
    private RelativeLayout loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_single, null);
        selectedA = (CheckBox) view.findViewById(R.id.selectedA);
        selectedB = (CheckBox) view.findViewById(R.id.selectedB);
        selectedC = (CheckBox) view.findViewById(R.id.selectedC);
        selectedD = (CheckBox) view.findViewById(R.id.selectedD);
        sureBtn = (TextView) view.findViewById(R.id.sure);
        body = (TextView) view.findViewById(R.id.body);
        analysis = (TextView) view.findViewById(R.id.analysis);
        answer = (TextView) view.findViewById(R.id.answer);
        answerLayout = (LinearLayout) view.findViewById(R.id.answerLayout);
        imageBody = (CloudImageView) view.findViewById(R.id.image_body);
        loading = (RelativeLayout) view.findViewById(R.id.loading);
        previousPage = (TextView) view.findViewById(R.id.previousPage);
        pageNumber = (TextView) view.findViewById(R.id.pageNumber);
        nextPage = (TextView) view.findViewById(R.id.nextPage);
        selectedA.setOnCheckedChangeListener(this);
        selectedB.setOnCheckedChangeListener(this);
        selectedC.setOnCheckedChangeListener(this);
        selectedD.setOnCheckedChangeListener(this);
        sureBtn.setOnClickListener(this);
        nextPage.setOnClickListener(this);
        previousPage.setOnClickListener(this);
        parameter = "/bookquestion/next";
        initData("", "");
        return view;
    }

    private void initView() {
        setButtonClickable(true);
        selectedA.setButtonDrawable(R.mipmap.ic_unselected_a);
        selectedB.setButtonDrawable(R.mipmap.ic_unselected_b);
        selectedC.setButtonDrawable(R.mipmap.ic_unselected_c);
        selectedD.setButtonDrawable(R.mipmap.ic_unselected_d);
        selectedA.setTextColor(getActivity().getResources().getColor(R.color.theme_black));
        selectedB.setTextColor(getActivity().getResources().getColor(R.color.theme_black));
        selectedC.setTextColor(getActivity().getResources().getColor(R.color.theme_black));
        selectedD.setTextColor(getActivity().getResources().getColor(R.color.theme_black));
        if (paperBean.getIsPicQuestion() == 0) {
            //题目标题不是图片
            body.setText(paperBean.getQuestion());
            imageBody.setVisibility(View.GONE);
        } else {
            //题目标题是图片
            body.setVisibility(View.GONE);
            imageBody.setImagePath(paperBean.getQuestion());
        }

        if (paperBean.getOptions().get(0).getIspictureopt() == 0) {
            //文本内容
            selectedA.setText(paperBean.getOptions().get(0).getOptcontent());
        } else {
            //图片
            selectedA.setText("");
        }

        if (paperBean.getOptions().get(1).getIspictureopt() == 0) {
            //文本内容
            selectedB.setText(paperBean.getOptions().get(1).getOptcontent());
        } else {
            //图片
            selectedB.setText("");
        }

        if (paperBean.getOptions().get(2).getIspictureopt() == 0) {
            //文本内容
            selectedC.setText(paperBean.getOptions().get(2).getOptcontent());
        } else {
            //图片
            selectedC.setText("");
        }

        if (paperBean.getOptions().get(3).getIspictureopt() == 0) {
            //文本内容
            selectedD.setText(paperBean.getOptions().get(3).getOptcontent());
        } else {
            //图片
            selectedD.setText("");
        }
        pageNumber.setText(paperBean.getSortnumber() + "/" + paperBean.getAllcount());

        loading.setVisibility(View.GONE);

    }

    private PaperBean paperBean;
    private String parameter;

    private void initData(String currentId, String answers) {
        singleAnswer = new HashMap<String, String>();
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("currentid", currentId);
        hashMap.put("answers", answers);
        netUtils.connectServer(hashMap, Config.BASE_URL + parameter);
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                try {
                     JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                    paperBean = new PaperBean();
                    paperBean = paperBean.jsonToEntity(jsonNode);
                    initView();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        //  paperBean.

    }

    private Map<String, String> singleAnswer;

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.selectedA:
                if (isChecked) {
                    selectedA.setButtonDrawable(R.mipmap.ic_selected_a);
                    //如果是单选其余都要设置为false并添加choice值;多选不用设置false，多选直接存储选择的选项和对应的值
                    selectedD.setChecked(false);
                    selectedC.setChecked(false);
                    selectedB.setChecked(false);
                    //     if (singleAnswer != null && singleAnswer.size() != 0) {
                    //   singleAnswer.clear();
                    singleAnswer.put("choice", "A");
                    //     }
                } else {
                    selectedA.setButtonDrawable(R.mipmap.ic_unselected_a);
                    //如果输单选直接清空，如果是多选移除所选项
                    if (singleAnswer != null && singleAnswer.size() != 0) {
                        singleAnswer.clear();
                    }
                }
                break;
            case R.id.selectedB:
                if (isChecked) {
                    selectedB.setButtonDrawable(R.mipmap.ic_selected_b);
                    //如果是单选其余都要设置为false并添加choice值;多选不用设置false，多选直接存储选择的选项和对应的值
                    selectedD.setChecked(false);
                    selectedC.setChecked(false);
                    selectedA.setChecked(false);
                    //  if (singleAnswer != null && singleAnswer.size() != 0) {
                    // singleAnswer.clear();
                    singleAnswer.put("choice", "B");
                    // }
                } else {
                    selectedB.setButtonDrawable(R.mipmap.ic_unselected_b);
                    //如果输单选直接清空，如果是多选移除所选项
                    if (singleAnswer != null && singleAnswer.size() != 0) {
                        singleAnswer.clear();
                    }

                }
                break;
            case R.id.selectedC:
                if (isChecked) {
                    selectedC.setButtonDrawable(R.mipmap.ic_selected_c);
                    //如果是单选其余都要设置为false并添加choice值;多选不用设置false，多选直接存储选择的选项和对应的值
                    selectedD.setChecked(false);
                    selectedB.setChecked(false);
                    selectedA.setChecked(false);
                    //  if (singleAnswer != null && singleAnswer.size() != 0) {
                    //   singleAnswer.clear();
                    singleAnswer.put("choice", "C");
                    //  }
                } else {
                    selectedC.setButtonDrawable(R.mipmap.ic_unselected_c);
                    //如果输单选直接清空，如果是多选移除所选项
                    if (singleAnswer != null && singleAnswer.size() != 0) {
                        singleAnswer.clear();
                    }
                }
                break;
            case R.id.selectedD:
                if (isChecked) {
                    selectedD.setButtonDrawable(R.mipmap.ic_selected_d);
                    //如果是单选其余都要设置为false并添加choice值;多选不用设置false，多选直接存储选择的选项和对应的值
                    selectedC.setChecked(false);
                    selectedB.setChecked(false);
                    selectedA.setChecked(false);
                    //if (singleAnswer != null && singleAnswer.size() != 0) {
                    //  singleAnswer.clear();
                    singleAnswer.put("choice", "D");
                    //   }
                } else {
                    //如果输单选直接清空，如果是多选移除所选的选项
                    if (singleAnswer != null && singleAnswer.size() != 0) {
                        singleAnswer.clear();
                    }
                    selectedD.setButtonDrawable(R.mipmap.ic_unselected_d);
                }
                break;
        }
    }

    public void changeSelected() {

    }

    private StringBuffer answers;
    private StringBuffer answerIdList;

    @Override
    public void onClick(View v) {
        String answerStr = "";
        switch (v.getId()) {
            case R.id.sure:
                //按完确认就将答案暂时存储起来
                if (singleAnswer != null && singleAnswer.size() != 0) {
                    String choice = singleAnswer.get("choice").toString();
                    List<PaperBean.optionBean> options = paperBean.getOptions();
                    answerLayout.setVisibility(View.VISIBLE);
                    if ("A".equals(choice)) {
                        if (options.get(0).getIsanswer() == 1) {
                            //正确答案
                            selectedA.setButtonDrawable(R.mipmap.ic_correct);
                            selectedA.setTextColor(getActivity().getResources().getColor(R.color.correct));
                        } else {
                            selectedA.setButtonDrawable(R.mipmap.ic_wrong);
                            selectedA.setTextColor(getActivity().getResources().getColor(R.color.wrong));
                        }
                    } else if ("B".equals(choice)) {
                        if (options.get(1).getIsanswer() == 1) {
                            //正确答案
                            selectedB.setButtonDrawable(R.mipmap.ic_correct);
                            selectedB.setTextColor(getActivity().getResources().getColor(R.color.correct));
                        } else {
                            selectedB.setButtonDrawable(R.mipmap.ic_wrong);
                            selectedB.setTextColor(getActivity().getResources().getColor(R.color.wrong));
                        }

                    } else if ("C".equals(choice)) {
                        if (options.get(2).getIsanswer() == 1) {
                            //正确答案
                            selectedC.setButtonDrawable(R.mipmap.ic_correct);
                            selectedC.setTextColor(getActivity().getResources().getColor(R.color.correct));
                        } else {
                            selectedC.setButtonDrawable(R.mipmap.ic_wrong);
                            selectedC.setTextColor(getActivity().getResources().getColor(R.color.wrong));
                        }

                    } else {
                        if (options.get(3).getIsanswer() == 1) {
                            //正确答案
                            selectedD.setButtonDrawable(R.mipmap.ic_correct);
                            selectedD.setTextColor(getActivity().getResources().getColor(R.color.correct));
                        } else {
                            selectedD.setButtonDrawable(R.mipmap.ic_wrong);
                            selectedD.setTextColor(getActivity().getResources().getColor(R.color.wrong));
                        }
                    }
                    answers = new StringBuffer();
                    answerIdList = new StringBuffer();
                    for (int i = 0; i < options.size(); i++) {
                        if (options.get(i).getIsanswer() == 1) {
                            if (i == 0) {
                                answers.append("A,");
                                answerIdList.append(options.get(i).getId() + ",");
                                selectedA.setButtonDrawable(R.mipmap.ic_correct);
                                selectedA.setTextColor(getActivity().getResources().getColor(R.color.correct));
                            } else if (i == 1) {
                                answers.append("B,");
                                answerIdList.append(options.get(i).getId() + ",");
                                selectedB.setButtonDrawable(R.mipmap.ic_correct);
                                selectedB.setTextColor(getActivity().getResources().getColor(R.color.correct));
                            } else if (i == 2) {
                                answers.append("C,");
                                answerIdList.append(options.get(i).getId() + ",");
                                selectedC.setButtonDrawable(R.mipmap.ic_correct);
                                selectedC.setTextColor(getActivity().getResources().getColor(R.color.correct));
                            } else if (i == 3) {
                                answers.append("D,");
                                answerIdList.append(options.get(i).getId() + ",");
                                selectedD.setButtonDrawable(R.mipmap.ic_correct);
                                selectedD.setTextColor(getActivity().getResources().getColor(R.color.correct));
                            }
                        }
                    }

                    answer.setText("正确答案是" + answers.toString().substring(0, answers.length() - 1));
                    analysis.setText(paperBean.getQuestanalysis());
                    setButtonClickable(false);
                } else {
                    Toast.makeText(getActivity(), "请选择一个选项", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.previousPage:
                answerLayout.setVisibility(View.GONE);
                parameter = "/bookquestion/previous";
                if (answerIdList != null && answerIdList.length() != 0) {
                    answerStr = answerIdList.toString().substring(0, answerIdList.length() - 1);
                }
                if (paperBean.getCurrentid() != 1) {
                    initData(paperBean.getCurrentid() + "", answerStr);
                }
                break;
            case R.id.nextPage:
                answerLayout.setVisibility(View.GONE);
                parameter = "/bookquestion/next";
                if (answerIdList != null && answerIdList.length() != 0) {
                    answerStr = answerIdList.toString().substring(0, answerIdList.length() - 1);
                }
                if (paperBean.getCurrentid() == paperBean.getAllcount()) {

                } else {
                    initData(paperBean.getCurrentid() + "", answerStr);
                }
                break;
        }
    }

    private void setButtonClickable(boolean b) {
        selectedA.setClickable(b);
        selectedC.setClickable(b);
        selectedB.setClickable(b);
        selectedD.setClickable(b);
        sureBtn.setClickable(b);
    }


}
