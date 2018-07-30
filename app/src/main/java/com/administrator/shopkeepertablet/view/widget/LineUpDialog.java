package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogLineUpNewBinding;
import com.administrator.shopkeepertablet.databinding.DialogReserveInfoBinding;
import com.administrator.shopkeepertablet.model.entity.TableType;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.adapter.TableTypeAdapter;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class LineUpDialog extends DialogFragment {

    private DialogLineUpNewBinding binding;
    private OnConfirmClick onConfirmClick;
    private List<TableType> tableTypeList = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private TableType tableType;

    public void setTableTypeList(List<TableType> tableTypeList) {
        this.tableTypeList = tableTypeList;
        for (TableType tableType : tableTypeList) {
            strings.add(tableType.getName());
        }
    }

    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_line_up_new, null);
        binding = DataBindingUtil.bind(customView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.item_spinner,
                R.id.tv_name,
                strings);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (!tableTypeList.isEmpty() && tableTypeList.size() > position) {
                            tableType = tableTypeList.get(position);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        if (!tableTypeList.isEmpty()) {
                            tableType = tableTypeList.get(0);
                        }
                    }
                }
        );

        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(binding.getRoot()).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onConfirmClick != null && tableType != null) {
                    onConfirmClick.confirm(tableType);
                }
            }
        });
        return dialog;
    }

    public interface OnConfirmClick {
        void confirm(TableType tableType);
    }

}
