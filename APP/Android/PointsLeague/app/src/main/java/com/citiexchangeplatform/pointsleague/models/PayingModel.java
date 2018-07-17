//package com.citiexchangeplatform.pointsleague.models;
//
//import android.support.annotation.NonNull;
//
//import com.citiexchangeplatform.pointsleague.SearchAdapter;
//
////import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
//
//
//public class PayingModel implements SearchAdapter.ViewModel {
//
//    //private final long mId;
//    //private final int mRank;
//    //private final String mWord;
//    private final String totalPoints;
//
//    public PayingModel(String totalPoints) {
//        //mId = id;
//        //mRank = rank;
//        this.totalPoints = totalPoints;
//    }
//
//    public String getName() {
//        return totalPoints;
//    }
//
//    /*public long getId() {
//        return mId;
//    }
//
//    public int getRank() {
//        return mRank;
//    }
//
//    public String getWord() {
//        return mWord;
//    }*/
//
//
//    @Override
//    public <T> boolean isSameModelAs(@NonNull T item) {
//        if (item instanceof PayingModel) {
//            final PayingModel payingModel = (PayingModel) item;
//            return payingModel.totalPoints.equals(getName());
//        }
//        return false;
//    }
//
//    @Override
//    public <T> boolean isContentTheSameAs(@NonNull T item) {
//        if (item instanceof PayingModel) {
//            final PayingModel other = (PayingModel) item;
//            if (!other.totalPoints.equals(getName())) {
//                return false;
//            }
//            return totalPoints != null ? totalPoints.equals(other.totalPoints) : other.totalPoints.isEmpty();
//        }
//        return false;
//    }
//
//    /*@Override
//    public <T> boolean isContentTheSameAs(@NonNull T item) {
//        if (item instanceof PayingModel) {
//            final PayingModel other = (PayingModel) item;
//            if (mRank != other.mRank) {
//                return false;
//            }
//            return mWord != null ? mWord.equals(other.mWord) : other.mWord == null;
//        }
//        return false;
//    }*/
//}
