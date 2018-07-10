//package com.citiexchangeplatform.pointsleague.models;
//
//import android.support.annotation.NonNull;
//
////import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
//
//
//public class PayingModel implements SortedListAdapter.ViewModel {
//
//    //private final long mId;
//    //private final int mRank;
//    //private final String mWord;
//    private final String name;
//
//    public PayingModel(String name) {
//        //mId = id;
//        //mRank = rank;
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
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
//            return payingModel.name.equals(getName());
//        }
//        return false;
//    }
//
//    @Override
//    public <T> boolean isContentTheSameAs(@NonNull T item) {
//        if (item instanceof PayingModel) {
//            final PayingModel other = (PayingModel) item;
//            if (!other.name.equals(getName())) {
//                return false;
//            }
//            return name != null ? name.equals(other.name) : other.name.isEmpty();
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
