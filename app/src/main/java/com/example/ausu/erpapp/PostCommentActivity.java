package com.example.ausu.erpapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ausu.erpapp.view.FlowLayout;
import com.example.ausu.erpapp.view.ListPopWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lanxumit on 2016/8/7.
 */
public class PostCommentActivity extends BaseActivity implements View.OnClickListener {
    private TextView cancel, post;
    private ImageView add;
    private FlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postcomment);
        setUpView();
        setListener();
    }


    private void setUpView() {
        cancel = (TextView) findViewById(R.id.cancel);
        post = (TextView) findViewById(R.id.post);
        add = (ImageView) findViewById(R.id.add);
        flowLayout = (FlowLayout) findViewById(R.id.flowLayout);
    }

    private void setListener() {
        cancel.setOnClickListener(this);
        post.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    private static final int RESULT_CAPTURE = 100;
    private static final int RESULT_PICK = 101;
    private static final int CROP_PHOTO = 102;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                break;
            case R.id.post:
                break;
            case R.id.add:
                ListPopWindow listPopWindow = new ListPopWindow();
                final List<String> items = new ArrayList<String>();
                items.add("照相机");
                items.add("相册");
                items.add("取消");
                listPopWindow.showPopWindow(this, items, v);
                listPopWindow.setPopWindowItemListener(new ListPopWindow.popWindowItemListener() {
                    @Override
                    public void popWindowItemClick(int position) {
                        if (position == 0) {
                            //照相
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            String externalStorageState = Environment.getExternalStorageState();
                            if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
                                File dir = new File(Environment.getExternalStorageDirectory() + "/erp");
                                if (!dir.exists()) dir.mkdir();
                                File f = new File(dir, "head.jpg");
                                Uri u = Uri.fromFile(f);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
                                startActivityForResult(intent, RESULT_CAPTURE);
                            }
                        } else if (position == 1) {
                            //去图库
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                            startActivityForResult(Intent.createChooser(intent, "请选择图片"), RESULT_PICK);
                        }
                    }
                });
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {

        }
    }
}
