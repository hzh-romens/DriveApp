package com.example.ausu.erpapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ausu.erpapp.adapter.PracticeAdapter;
import com.example.ausu.erpapp.config.Config;
import com.example.ausu.erpapp.model.PracticeItemBean;
import com.example.ausu.erpapp.utils.JacksonMapper;
import com.example.ausu.erpapp.utils.NetUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lanxumit on 2016/7/27.
 */
public class PracticeActivity extends BaseActivity {
    private ListView listView;
    private PracticeAdapter practiceAdapter;
    private SparseBooleanArray answers;
    private ImageView backView;
    //用StartActivtyForResult将选择的答案返回回去

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practive);
        listView = (ListView) findViewById(R.id.listview);
        backView = (ImageView) findViewById(R.id.btn_back);
        initData();

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < answers.size(); i++) {
                    int position = answers.keyAt(i);
                    sb.append(results.get(position).getCoursename());
                    sb.append(",");
                }
                Intent intent = getIntent();
                if (sb.length() != 0) {
                    String substring = sb.toString().substring(0, sb.length() - 1);
                    intent.putExtra("item", substring);
                } else {
                    intent.putExtra("item", "");
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initData() {
        answers = new SparseBooleanArray();
        results = new ArrayList<PracticeItemBean>();
        setData();
        practiceAdapter = new PracticeAdapter(this);
        listView.setAdapter(practiceAdapter);
        practiceAdapter.setOnCheckListener(new PracticeAdapter.onCheckListener() {
            @Override
            public void onCheckChange(int position, boolean isChecked) {
                if (isChecked) {
                    if (answers.size() < 2) {
                        answers.put(position, isChecked);
                    } else {
                        Toast.makeText(PracticeActivity.this, "亲，每次最多只能选择两个哦", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    answers.delete(position);
                }
                practiceAdapter.setAnswers(answers);
            }
        });

    }

    private List<PracticeItemBean> results;

    private void setData() {
        NetUtils netUtils = new NetUtils();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        netUtils.connectServer(hashMap, Config.BASE_URL + "/study/canstudyitems");
        netUtils.setCallBackListener(new NetUtils.CallBackListener() {
            @Override
            public void CallBackResult(String result) {
                try {
                    JsonNode jsonNode = JacksonMapper.getInstance().readTree(result);
                    results = PracticeItemBean.JsonToList(jsonNode);
                    practiceAdapter.setData(results);
                    practiceAdapter.setAnswers(answers);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < answers.size(); i++) {
            int position = answers.keyAt(i);
            sb.append(results.get(position).getCoursename());
            sb.append(",");
        }
        Intent intent = getIntent();
        if (sb.length() != 0) {
            String substring = sb.toString().substring(0, sb.length() - 1);
            intent.putExtra("item", substring);
        } else {
            intent.putExtra("item", "");
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
