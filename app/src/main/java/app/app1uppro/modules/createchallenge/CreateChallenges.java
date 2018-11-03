package app.app1uppro.modules.createchallenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Scroller;
import android.widget.TextView;

import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import com.github.rubensousa.bottomsheetbuilder.adapter.BottomSheetItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import app.app1uppro.R;
import app.app1uppro.apibase.modelclass.CreateChallengeModel;
import app.app1uppro.baseui.BaseFragment;
import app.app1uppro.common.DataManager;
import app.app1uppro.common.GlobalVariable;
import app.app1uppro.common.Validate_Structure;
import app.app1uppro.modules.homefragment.HomeFragment;
import app.app1uppro.modules.uploadvideo.GoogleLoginUploadVideo;
import app.app1uppro.modules.videocategoryhome.VideoCategoryHome;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class CreateChallenges extends BaseFragment implements
        CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener, ICreateChallengeView {

    @Inject
    DataManager sessionManager;

    @BindView(R.id.selectcategory)
    TextView selectcategory;
    @BindView(R.id.challengername)
    EditText challengername;
    @BindView(R.id.challengelength)
    TextView challengelength;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.horizontalradio)
    RadioButton horizontalradio;
    @BindView(R.id.verticalradio)
    RadioButton verticalradio;
    @BindView(R.id.rules_edit_text)
    EditText rules_edit_text;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.createchallengebtn)
    Button createchallengebtn;
    RadioButton orientation;
    String orientationValue="";
    int challengLengthValue;  //0 for horizontal and 1 for vertical
    ArrayList<String> categoryValues = new ArrayList<>();
    ArrayList<String> categoryIds = new ArrayList<>();
    BottomSheetMenuDialog dialog;
    @Inject
    CreateChallengePresenter<ICreateChallengeView> presenter;
    private HashMap<String,Object> challengehashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_challenges, container, false);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        hideKeyboard();
        presenter.onAttach(this);
        initialize();
        return view;
    }//end onCreate

    private void initialize() {
        challengehashMap = new HashMap<>();
        challengelength.setText(getString(R.string.days_30));
        challengLengthValue=30;
        orientationValue = getString(R.string.vertical);
        radiogroup.setOnCheckedChangeListener(this);
        checkBox.setOnCheckedChangeListener(this);
        rules_edit_text.setScroller(new Scroller(getActivity()));
        rules_edit_text.setVerticalScrollBarEnabled(true);
        rules_edit_text.setMovementMethod(new ScrollingMovementMethod());

    }//end initialize

    @Override
    protected void setUp(View view) { }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Optional
    @OnClick({R.id.challengelength, R.id.selectcategory,
            R.id.rules_edit_text, R.id.createchallengebtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.challengelength:
                bottomChallengeLength();
                break;
            case R.id.selectcategory:
                Intent intent = new Intent(getActivity(), VideoCategoryHome.class);
                startActivityForResult(intent, GlobalVariable.Video_Category);
                break;
            case R.id.rules_edit_text:
                rules_edit_text.setFocusableInTouchMode(true);
                rules_edit_text.requestFocus();
                rules_edit_text.setOnTouchListener(new View.OnTouchListener() {
                    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
                    public boolean onTouch(View v, MotionEvent event) {
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(rules_edit_text, InputMethodManager.SHOW_FORCED);
                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_UP:
                                rules_edit_text.setFocusableInTouchMode(true);
                                rules_edit_text.requestFocus();
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                return true;
                        }
                        return false;
                    }
                });
                break;
            case R.id.createchallengebtn:
                if (isNetworkConnected() &&
                        Validate_Structure.validateCreateChallenge(getActivity(),
                        categoryIds)) {
                    hideKeyboard();
                    challengehashMap.put("user_id", sessionManager.getSharedpref(GlobalVariable.User_id));
                    challengehashMap.put("ChallengeCatID",categoryIds.get(categoryIds.size()-1));
                    challengehashMap.put("CatID",android.text.TextUtils.join(",", categoryIds));
                    challengehashMap.put("challenger_user_name",challengername.getText().toString());
                    challengehashMap.put("challengeLength", challengLengthValue);
                    challengehashMap.put("orientation",orientationValue);
                    challengehashMap.put("rules",rules_edit_text.getText().toString());
                    presenter.onLoginClick(challengehashMap);
                }
                break;
        }//end switch
    }//end onViewClicked

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Select Category Part
        if (resultCode == Activity.RESULT_OK && requestCode == GlobalVariable.Video_Category) {
            if (data.getExtras() != null) {
                if (data.getStringArrayListExtra("category_id") != null) {
                    categoryValues = data.getStringArrayListExtra("category_name");
                    categoryIds = data.getStringArrayListExtra("category_id");
                    selectcategory.setText(android.text.TextUtils.join(",", categoryValues));
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED && requestCode == GlobalVariable.Video_Category) {
            selectcategory.setHint(getString(R.string.select_category));
        }
    }//end onActivityResult

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int selectedId = radiogroup.getCheckedRadioButtonId();
        orientation = getActivity().findViewById(selectedId);
        if (orientation.getText().toString().equals(getString(R.string.horizontal)))
            orientationValue = getString(R.string.horizontal);
        else orientationValue = getString(R.string.vertical);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkBox:
                if (isChecked) {
                    challengername.setText("");
                    challengername.setEnabled(false);
                } else challengername.setEnabled(true);
                break;
        }//end switch
    }

    public void bottomChallengeLength() {
        dialog = new BottomSheetBuilder(getActivity(),
                R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setMenu(R.menu.bottomchallenge)
                .expandOnStart(true)           // expand the dialog automatically:
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.days14id:
                                challengelength.setText(getString(R.string.days_14));
                                challengLengthValue=14;
                                dialog.dismiss();
                                break;
                            case R.id.days30id:
                                challengelength.setText(getString(R.string.days_30));
                                challengLengthValue=30;
                                dialog.dismiss();
                                break;
                            case R.id.days60id:
                                challengelength.setText(getString(R.string.days_60));
                                challengLengthValue=60;
                                dialog.dismiss();
                                break;
                        }
                    }
                })
                .createDialog();
        dialog.show();
    }//end bottom dialog

    @Override
    public void updateResponse(String msg,CreateChallengeModel.DataBean dataBean) {
        Intent videointent=new Intent(getActivity(), GoogleLoginUploadVideo.class);
        videointent.putExtra("ChallengeID",dataBean.getChallengeID());
        videointent.putExtra("ChallengeCatID",dataBean.getChallengeCatID());
        startActivity(videointent);
    }//end updateResponse

}//end main class
