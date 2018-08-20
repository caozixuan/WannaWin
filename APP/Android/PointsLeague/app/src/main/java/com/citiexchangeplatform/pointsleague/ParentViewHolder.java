//package com.citiexchangeplatform.pointsleague;
//
//import android.animation.ValueAnimator;
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.os.Build;
//import android.view.View;
//import android.view.animation.DecelerateInterpolator;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.citiexchangeplatform.pointsleague.models.PointsExchangeModel;
//
//public class ParentViewHolder extends BaseViewHolder {
//
//    private Context mContext;
//    private View view;
//    private RelativeLayout containerLayout;
//    private TextView totalPoints;
//    private TextView date;
//    private ImageView expand;
//    private View parentDashedView;
//
//    public ParentViewHolder(Context context, View itemView) {
//        super(itemView);
//        this.mContext = context;
//        this.view = itemView;
//
//
//    }
//
//    public void bindView(final PointsExchangeModel dataModel, final int pos, final ItemClickListener listener){
//
//        containerLayout = (RelativeLayout) view.findViewById(R.id.container);
//        totalPoints = view.findViewById(R.id.textView_total_points);
//        date = view.findViewById(R.id.textView_exchange_date);
//        expand = (ImageView) view.findViewById(R.id.expend);
//        parentDashedView = view.findViewById(R.id.parent_dashed_view);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) expand
//                .getLayoutParams();
//        expand.setLayoutParams(params);
//        totalPoints.setText(dataModel.getExchangeTotalPoint());
//        date.setText(dataModel.getDate());
//
//        if (dataModel.isExpand()) {
//            expand.setRotation(90);
//            parentDashedView.setVisibility(View.INVISIBLE);
//        } else {
//            expand.setRotation(0);
//            parentDashedView.setVisibility(View.VISIBLE);
//        }
//
//        //父布局OnClick监听
//        containerLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (listener != null) {
//                    if (dataModel.isExpand()) {
//                        listener.onHideChildren(dataModel);
//                        parentDashedView.setVisibility(View.VISIBLE);
//                        dataModel.setExpand(false);
//                        rotationExpandIcon(180, 0);
//                    } else {
//                        listener.onExpandChildren(dataModel);
//                        parentDashedView.setVisibility(View.INVISIBLE);
//                        dataModel.setExpand(true);
//                        rotationExpandIcon(0, 180);
//                    }
//                }
//            }
//        });
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    private void rotationExpandIcon(float from, float to) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);//属性动画
//            valueAnimator.setDuration(500);
//            valueAnimator.setInterpolator(new DecelerateInterpolator());
//            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//                @Override
//                public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                    expand.setRotation((Float) valueAnimator.getAnimatedValue());
//                }
//            });
//            valueAnimator.start();
//        }
//    }
//}
