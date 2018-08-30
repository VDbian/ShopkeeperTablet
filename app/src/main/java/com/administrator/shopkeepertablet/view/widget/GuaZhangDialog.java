package com.administrator.shopkeepertablet.view.widget;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogGuaZhangBinding;
import com.administrator.shopkeepertablet.databinding.DialogPermissionDiscountBinding;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.model.entity.GuaZhangEntity;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.adapter.DiscountAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.GuaZhangAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class GuaZhangDialog extends DialogFragment {

    private DialogGuaZhangBinding binding;
    private OnConfirmClick onConfirmClick;
    private List<GuaZhangEntity> mList = new ArrayList<>();
    private GuaZhangEntity guaZhangEntity;
    private GuaZhangAdapter adapter;

    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    public List<GuaZhangEntity> getmList() {
        return mList;
    }

    public void setmList(List<GuaZhangEntity> mList) {
        this.mList.clear();
        this.mList.addAll(mList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(STYLE_NO_FRAME, R.style.Theme_AppCompat_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_gua_zhang, null);
        binding = DataBindingUtil.bind(customView);
        binding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onConfirmClick!=null){
                    onConfirmClick.cancel();
                }
            }
        });

        adapter = new GuaZhangAdapter(getActivity(), mList);
        binding.rlvDiscount.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.rlvDiscount.setAdapter(adapter);
        binding.rlvDiscount.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new GuaZhangAdapter.OnItemClick() {
            @Override
            public void onItemClick(GuaZhangEntity entity, int position) {
                guaZhangEntity = entity;
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guaZhangEntity == null) {
                    MToast.showToast(getActivity(), "请选择挂账人");
                    return;
                }
                if (TextUtils.isEmpty(binding.etNum.getText().toString().trim())) {
                    MToast.showToast(getActivity(), "请输入挂账金额");
                    return;
                }
                Double d = Double.valueOf(binding.etNum.getText().toString().trim());
                if (onConfirmClick != null) {
                    onConfirmClick.confirm(guaZhangEntity, d);
                }
                dismiss();
            }
        });
        return binding.getRoot();
    }

    public interface OnConfirmClick {
        void confirm(GuaZhangEntity entity, double money);

        void cancel();
    }

}
