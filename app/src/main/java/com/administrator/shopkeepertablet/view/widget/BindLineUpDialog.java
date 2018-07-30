package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogBindLineUpBinding;
import com.administrator.shopkeepertablet.databinding.DialogLineUpNewBinding;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.TableType;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class BindLineUpDialog extends DialogFragment {

    private DialogBindLineUpBinding binding;
    private OnConfirmClick onConfirmClick;
    private List<RoomEntity> roomEntityList = new ArrayList<>();
    private List<String> stringRoom = new ArrayList<>();
    private List<TableEntity> tableEntityList = new ArrayList<>();
    private List<String> stringTable = new ArrayList<>();
    private RoomEntity roomEntity;
    private TableEntity tableEntity;
    private ArrayAdapter<String> adapterTable;

    public void setRoomList(List<RoomEntity> roomList) {
        this.roomEntityList = roomList;
        for (RoomEntity entity : roomList) {
            stringRoom.add(entity.getName());
        }
    }

    public void setTableList(List<TableEntity> tableList) {
        this.tableEntityList = tableList;
        for (TableEntity entity : tableList) {
            stringTable.add(entity.getTableName());
        }
    }

    public void refreshTable(List<TableEntity> tableList) {
        stringTable.clear();
        this.tableEntityList = tableList;
        for (TableEntity entity : tableList) {
            stringTable.add(entity.getTableName());
        }
        adapterTable.notifyDataSetChanged();
    }

    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_bind_line_up, null);
        binding = DataBindingUtil.bind(customView);
        ArrayAdapter<String> adapterRoom = new ArrayAdapter<String>(
                getActivity(),
                R.layout.item_spinner,
                R.id.tv_name,
                stringRoom);
        binding.spinnerRoom.setAdapter(adapterRoom);
        binding.spinnerRoom.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (!roomEntityList.isEmpty() && roomEntityList.size() > position && onConfirmClick != null) {
                            roomEntity = roomEntityList.get(position);
                            onConfirmClick.selectRoom(roomEntity);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        if (!roomEntityList.isEmpty()) {
                            roomEntity = roomEntityList.get(0);
                        }
                    }
                }
        );

        adapterTable = new ArrayAdapter<String>(
                getActivity(),
                R.layout.item_spinner,
                R.id.tv_name,
                stringTable);
        binding.spinnerTable.setAdapter(adapterTable);
        binding.spinnerTable.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (!tableEntityList.isEmpty() && tableEntityList.size() > position) {
                            tableEntity = tableEntityList.get(position);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        if (!tableEntityList.isEmpty()) {
                            tableEntity = tableEntityList.get(0);
                        }
                    }
                }
        );

        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(binding.getRoot()).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

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
                if (onConfirmClick != null && roomEntity != null && tableEntity != null) {
                    onConfirmClick.confirm(roomEntity, tableEntity);
                }
            }
        });
        return dialog;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (onConfirmClick != null ) {
            onConfirmClick.cancel();
        }
    }

    public interface OnConfirmClick {
        void confirm(RoomEntity roomEntity, TableEntity tableEntity);

        void selectRoom(RoomEntity roomEntity);

        void cancel();
    }

}
