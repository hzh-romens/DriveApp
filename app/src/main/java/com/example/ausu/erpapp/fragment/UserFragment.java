package com.example.ausu.erpapp.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ausu.erpapp.MainActivity;
import com.example.ausu.erpapp.R;
import com.example.ausu.erpapp.SettingActivity;
import com.example.ausu.erpapp.adapter.UserAdapter;
import com.example.ausu.erpapp.cell.UserSettingCell;
import com.example.ausu.erpapp.utils.BitmapUtils;
import com.example.ausu.erpapp.utils.PopWindowUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by pc on 2016/4/25.////
 */
public class UserFragment extends Fragment implements View.OnClickListener {
    private ListView listView;
    private UserAdapter adapter;
    private SparseArray types;
    private SparseArray results;
    private ImageView setting;
    private View view;
    public static int TAKE_PHOTO = 1;
    public static int ALBUM = 2;
    public static int CROP_PHOTO = 3;
    private Uri headUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_user, null);
        listView = (ListView) view.findViewById(R.id.listview);
        setting = (ImageView) view.findViewById(R.id.setting);
        setting.setOnClickListener(this);
        initData();
        return view;
    }

    private void initData() {
        results = new SparseArray();
        types = new SparseArray();
        for (int i = 0; i < 7; i++) {
            types.put(i, i + 1);
        }
        adapter = new UserAdapter(getActivity());
        adapter.setData(results, types);
        listView.setAdapter(adapter);
        adapter.setHeadClickListener(new UserAdapter.HeadClickListener() {
            @Override
            public void headClick() {
                PopWindowUtils popWindowUtils = new PopWindowUtils();
                List<String> data = new ArrayList<String>();
                data.add("相机");
                data.add("相册");
                popWindowUtils.showPopWindow(getActivity(), data, view);
                popWindowUtils.setPopWindowItemListener(new PopWindowUtils.popWindowItemListener() {
                    @Override
                    public void popWindowItemClick(int position) {
                        if (position == 0) {
                            try {
                                File outputImage = new File(Environment.getExternalStorageDirectory(), "head.jpg");
                                if (outputImage.exists()) {
                                    outputImage.delete();
                                }
                                outputImage.createNewFile();
                                headUri = Uri.fromFile(outputImage);
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, headUri); //指定图片输出地址
                                startActivityForResult(cameraIntent, TAKE_PHOTO); //启动照相
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Intent ablumIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(ablumIntent,ALBUM);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == TAKE_PHOTO) {
            Intent intent = new Intent("com.android.camera.action.CROP"); //剪裁
            intent.setDataAndType(headUri, "image/*");
            intent.putExtra("scale", true);
            //设置宽高比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置裁剪图片宽高
            intent.putExtra("outputX", 480);
            intent.putExtra("outputY", 480);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, headUri);
            startActivityForResult(intent, CROP_PHOTO); //设置裁剪参数显示图片至ImageView
        } else if (resultCode == getActivity().RESULT_OK && requestCode == ALBUM) {
            Uri data1 = data.getData();
            //图片解析成Bitmap对象
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(
                        getActivity().getContentResolver().openInputStream(data1));
                Bitmap zoomBitmap = BitmapUtils.zoomImage(bitmap, 160, 160);
                //将图片上传，然后更新头像
                results.append(1, zoomBitmap);
                adapter.notifyData(results);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (resultCode == getActivity().RESULT_OK && requestCode == CROP_PHOTO) {
            //图片解析成Bitmap对象
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(
                        getActivity().getContentResolver().openInputStream(headUri));
                Bitmap zoomBitmap = BitmapUtils.zoomImage(bitmap, 160, 160);
                //将图片上传，然后更新头像
                results.append(1, zoomBitmap);
                adapter.notifyData(results);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                getActivity().startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.message:
                break;
        }
    }
}
