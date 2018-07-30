package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogLineUpNewBinding;
import com.administrator.shopkeepertablet.databinding.FragmentLineUpBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.line.DaggerLineComponent;
import com.administrator.shopkeepertablet.di.line.LineModule;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.LineUpEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.TableType;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.adapter.LineUpAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.TableTypeAdapter;
import com.administrator.shopkeepertablet.view.widget.BindLineUpDialog;
import com.administrator.shopkeepertablet.view.widget.ConfirmDialog;
import com.administrator.shopkeepertablet.view.widget.LineUpDialog;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.LineUpViewModel;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/24
 */

public class LineUpFragment extends BaseFragment implements View.OnClickListener {

    FragmentLineUpBinding binding;
    @Inject
    LineUpViewModel viewModel;

    private List<TableType> tableTypeList = new ArrayList<>();
    private TableTypeAdapter tableTypeAdapter;

    private List<LineUpEntity> lineUpEntityList = new ArrayList<>();
    private LineUpAdapter lineUpAdapter;
    private BindLineUpDialog bindLineUpDialog;
    private LineUpEntity lineUpEntity;


    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerLineComponent.builder()
                .appComponent(appComponent)
                .lineModule(new LineModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_line_up, container, false);
//        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.search);
        drawable1.setBounds(25, 0, 45, 20);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        binding.etSearch.setCompoundDrawables(drawable1, null, null, null);//只放左边

        tableTypeAdapter = new TableTypeAdapter(getActivity(), tableTypeList);
        binding.rlvTableType.setAdapter(tableTypeAdapter);
        binding.rlvTableType.setLayoutManager(new LinearLayoutManager(getActivity()));
        tableTypeAdapter.setOnItemClick(new TableTypeAdapter.OnItemClick() {
            @Override
            public void onItemClick(TableType entity, int position) {
                selectByType(entity.getName());
            }
        });

        lineUpAdapter = new LineUpAdapter(getActivity(), lineUpEntityList);
        binding.rlvLineUp.setAdapter(lineUpAdapter);
        binding.rlvLineUp.setLayoutManager(new LinearLayoutManager(getActivity()));
        lineUpAdapter.setOnItemClick(new LineUpAdapter.OnClick() {
            @Override
            public void speak(LineUpEntity entity, int position) {

            }

            @Override
            public void bind(LineUpEntity entity, int position) {
                lineUpEntity = entity;
                viewModel.getRooms();
            }

            @Override
            public void delete(LineUpEntity entity, int position) {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setMessage("确认删除排号" + entity.no());
                dialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                    @Override
                    public void confirm() {
                        viewModel.deleteLineUp(entity.getId());
                    }

                    @Override
                    public void cancel() {

                    }
                });
                dialog.show(getActivity().getFragmentManager(), "");
            }
        });
        binding.rlvLineUp.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                viewModel.getLineUp();
            }

            @Override
            public void onLoadMore() {

            }
        });
        binding.rlvLineUp.setLoadingMoreEnabled(false);

        binding.tvSearch.setOnClickListener(this);
        binding.tvAdd.setOnClickListener(this);

        viewModel.getTableType();
        viewModel.getLineUp();
    }

    public void refreshType(List<TableType> tableTypes) {
        tableTypeList.clear();
        tableTypeList.add(new TableType("", "", "全部", "", "", "", true));
        tableTypeList.addAll(tableTypes);
        tableTypeAdapter.notifyDataSetChanged();
    }

    public void refreshLineUp(List<LineUpEntity> lineUpEntities) {
        lineUpEntityList.clear();
        lineUpEntityList.addAll(lineUpEntities);
        lineUpAdapter.notifyDataSetChanged();
        binding.rlvLineUp.refreshComplete();
    }

    private void selectByType(String type) {
        lineUpEntityList.clear();
        if (type.equals("全部")) {
            lineUpEntityList.addAll(viewModel.entityList);
        } else {
            for (LineUpEntity entity : viewModel.entityList) {
                if (entity.getTableName().equals(type)) {
                    lineUpEntityList.add(entity);
                }
            }
        }
        lineUpAdapter.notifyDataSetChanged();
    }

    private void selectByPhone(String phone) {
        lineUpEntityList.clear();
        if (TextUtils.isEmpty(phone)) {
            lineUpEntityList.addAll(viewModel.entityList);
        } else {
            for (LineUpEntity entity : viewModel.entityList) {
                if (entity.getPhone().contains(phone)) {
                    lineUpEntityList.add(entity);
                }
            }
        }
        lineUpAdapter.notifyDataSetChanged();
    }

    public void showBindDialog() {
        if (bindLineUpDialog != null) {
            bindLineUpDialog.refreshTable(viewModel.tableEntityList);
        } else {
            bindLineUpDialog = new BindLineUpDialog();
            bindLineUpDialog.setTableList(viewModel.tableEntityList);
            bindLineUpDialog.setRoomList(viewModel.roomEntityList);
            bindLineUpDialog.setOnConfirmClick(new BindLineUpDialog.OnConfirmClick() {
                @Override
                public void confirm(RoomEntity roomEntity, TableEntity tableEntity) {
                    for (TableType type : viewModel.tableTypeList) {
                        if (lineUpEntity.getTypes().equals(type.getTypes())) {
                            viewModel.bindLineUp(type.getId(), tableEntity.getRoomTableId(), tableEntity.getTableName(), lineUpEntity.getId());
                        }
                    }
                }

                @Override
                public void selectRoom(RoomEntity roomEntity) {
                    viewModel.getTables(roomEntity);
                }

                @Override
                public void cancel() {
                    bindLineUpDialog = null;
                }
            });
            bindLineUpDialog.show(getActivity().getFragmentManager(), "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                LineUpDialog dialog = new LineUpDialog();
                dialog.setTableTypeList(viewModel.tableTypeList);
                dialog.setOnConfirmClick(new LineUpDialog.OnConfirmClick() {
                    @Override
                    public void confirm(TableType tableType) {
                        Log.e("api", tableType.toString());
                        viewModel.addLineUp(tableType.getId());
                    }
                });
                dialog.show(getActivity().getFragmentManager(), "");
                break;
            case R.id.tv_search:
                selectByPhone(binding.etSearch.getText().toString().trim());
                break;
        }
    }
}
