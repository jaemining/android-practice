package com.jaemin.android.sqlitebasic_bbs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

package com.kodonho.android.sqlitebasic_bbs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditFragment extends Fragment {

    private static final int BBS_INSERT = -1;
    //private static final int BBS_INSERT = -1;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button cancel;
    Button save;

    int bbsno = -1;
    EditText title;
    EditText name;
    EditText contents;

    Button buttonImage;

    ImageView imageView;

    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    // Database에서 bbsno에 해당하는 레코드를 가져와서 화면에 뿌려준다
    public void setData(int bbsno) {
        // 해당 리스트가 클릭되면 Edit 화면에 값을 뿌려주는 함수
        BbsData data = DataUtil.select(getContext(), bbsno);
        Log.i("setData", "bbsno =  "+bbsno);

        title.setText(data.title);
        name.setText(data.name);
        contents.setText(data.contents);

        this.bbsno = bbsno;
    }

    // 캔슬하거나 저장 후에 호출하여 데이터를 리셋 !
    public void resetData() {
        title.setText("");
        name.setText("");
        contents.setText("");

        this.bbsno = -1;
    }

    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        cancel = (Button) view.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.action(MainActivity.ACTION_CANCEL);
                resetData();
            }
        });

        save = (Button) view.findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BbsData data = new BbsData();
                data.no = bbsno;
                data.title = title.getText().toString();
                data.name = name.getText().toString();
                data.contents = contents.getText().toString();

                if (bbsno == BBS_INSERT) {
                    DataUtil.insert(getContext(), data);
                    // insert 또는 update에 따라 flag값 변경해야 됨
                    //mListener.actionSave(data, MainActivity.SAVE_INSERT);
                } else {
                    DataUtil.update(getContext(), data);
                    //mListener.actionSave(data, MainActivity.SAVE_UPDATE);
                }
                resetData();
                mListener.action(MainActivity.ACTION_GOEDIT_WITH_REFRESH);


                resetData();
            }
        });

        title = (EditText) view.findViewById(R.id.etTitle);
        name = (EditText) view.findViewById(R.id.etName);
        contents = (EditText) view.findViewById(R.id.etContents);

        buttonImage = (Button) view.findViewById(R.id.button_image);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이미지를 호출하는 action intent
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // 결과값을 넘겨받기위해 호출
                startActivityForResult(intent, REQ_CODE_IMAGE);
            }
        });

        imageView = (ImageView) view.findViewById(R.id.imageView);

        return view;
    }

    private static final int REQ_CODE_IMAGE = 99;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 갤러리가 닫히면 호출된다 ?
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_IMAGE && data != null) {
            Uri mediaImage = data.getData(); // 이미지 Uri
            String selections[] = {MediaStore.Images.Media.DATA}; // 실제 이미지 패스 데이터
            Cursor cursor = getContext().getContentResolver().query(mediaImage, selections, null, null, null);

            if (cursor.moveToNext()) {
                String imagePath = cursor.getString(0);
                name.setText(imagePath);
                // Log.i("aaaa", "imagePath ===== " + imagePath);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;

                options.inSampleSize = 10; // 이미지 사이즈를 1/2로 줄인다
                Bitmap image = BitmapFactory.decodeFile(imagePath, options);

                imageView.setImageBitmap(image);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
