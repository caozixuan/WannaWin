//package com.citiexchangeplatform.pointsleague;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.citiexchangeplatform.pointsleague.models.PointsExchangeModel;
//
//import java.util.List;
//
//
//public class PointsExchangeRecordAdapter extends RecyclerView.Adapter<BaseViewHolder>  {
//    private Context context;
//    private List<PointsExchangeModel> sourceItems;
//    private OnScrollListener mOnScrollListener;
//    private LayoutInflater mInflater;
//
//
//    //构造方法
//    public PointsExchangeRecordAdapter(Context context,List<PointsExchangeModel> records) {
//        this.context = context;
//        this.mInflater = LayoutInflater.from(context);
//        this.sourceItems = records;
//    }
//
//    @NonNull
//    @Override
//    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = null;
//        switch (viewType){
//            case PointsExchangeModel.PARENT_ITEM:
//                view = mInflater.inflate(R.layout.points_exchange_item_parent, parent, false);
//                return new ParentViewHolder(context, view);
//            case PointsExchangeModel.CHILD_ITEM:
//                view = mInflater.inflate(R.layout.points_exchange_item_children, parent, false);
//                return new ChildViewHolder(context, view);
//            default:
//                view = mInflater.inflate(R.layout.points_exchange_item_parent, parent, false);
//                return new ParentViewHolder(context, view);
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//
//        switch (getItemViewType(position)){
//            case PointsExchangeModel.PARENT_ITEM:
//                ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
//                parentViewHolder.bindView(sourceItems.get(position), position, itemClickListener);
//                break;
//            case PointsExchangeModel.CHILD_ITEM:
//                ChildViewHolder childViewHolder = (ChildViewHolder) holder;
//                childViewHolder.bindView(sourceItems.get(position), position);
//                break;
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return sourceItems.get(position).getType();
//    }
//
//    private ItemClickListener itemClickListener = new ItemClickListener() {
//        @Override
//        public void onExpandChildren(PointsExchangeModel model) {
//            int position = getCurrentPosition(model.getID());//确定当前点击的item位置
//            PointsExchangeModel children = getChildDataModel(model);//获取要展示的子布局数据对象，注意区分onHideChildren方法中的getChildBean()。
//            if (children == null) {
//                return;
//            }
//            add(children, position + 1);//在当前的item下方插入
//            if (position == sourceItems.size() - 2 && mOnScrollListener != null) { //如果点击的item为最后一个
//                mOnScrollListener.scrollTo(position + 1);//向下滚动，使子布局能够完全展示
//            }
//        }
//
//        @Override
//        public void onHideChildren(PointsExchangeModel bean) {
//            int position = getCurrentPosition(bean.getID());//确定当前点击的item位置
//            PointsExchangeModel children = bean.getChildBean();//获取子布局对象
//            if (children == null) {
//                return;
//            }
//            remove(position + 1);//删除
//            if (mOnScrollListener != null) {
//                mOnScrollListener.scrollTo(position);
//            }
//        }
//    };
//
//    /**
//     * 在父布局下方插入一条数据
//     * @param bean
//     * @param position
//     */
//    public void add(PointsExchangeModel bean, int position) {
//        sourceItems.add(position, bean);
//        notifyItemInserted(position);
//    }
//
//    /**
//     *移除子布局数据
//     * @param position
//     */
//    protected void remove(int position) {
//        sourceItems.remove(position);
//        notifyItemRemoved(position);
//    }
//
//    /**
//     * 确定当前点击的item位置并返回
//     * @param uuid
//     * @return
//     */
//    protected int getCurrentPosition(String uuid) {
//        for (int i = 0; i < sourceItems.size(); i++) {
//            if (uuid.equalsIgnoreCase(sourceItems.get(i).getID())) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     * 封装子布局数据对象并返回
//     * 注意，此处只是重新封装一个DataBean对象，为了标注Type为子布局数据，进而展开，展示数据
//     * 要和onHideChildren方法里的getChildBean()区分开来
//     * @param bean
//     * @return
//     */
//    private PointsExchangeModel getChildDataModel(PointsExchangeModel bean){
//        PointsExchangeModel child = new PointsExchangeModel();
//        child.setType(1);
//        child.setExchangeTotalPoint(bean.getExchangeTotalPoint());
//        child.setDate(bean.getDate());
//        child.setMsCardPoint(bean.getMsCardPoint());
//        child.setExchangePoint(bean.getExchangePoint());
//        return child;
//    }
//
//    @Override
//    public int getItemCount() {
//        return sourceItems.size();
//    }
//
//
//    /**
//     * 滚动监听接口
//     */
//    public interface OnScrollListener{
//        void scrollTo(int pos);
//    }
//
//    public void setOnScrollListener(OnScrollListener onScrollListener){
//        this.mOnScrollListener = onScrollListener;
//    }
//
//
//}
