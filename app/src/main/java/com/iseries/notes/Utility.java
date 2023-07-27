package com.iseries.notes;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utility {

    static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    static CollectionReference getCollectionReferenceForNotes(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("notes")
                .document(currentUser.getUid()).collection("my_notes");
    }

   public static String covertTimeToText(String dataDate) {

       String convTime = null;

       String prefix = "";
       String suffix = "";

       try {
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
           Date pasTime = dateFormat.parse(dataDate);

           Date nowTime = new Date();

           long dateDiff = nowTime.getTime() - pasTime.getTime();

           long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
           long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
           long hour   = TimeUnit.MILLISECONDS.toHours(dateDiff);
           long day  = TimeUnit.MILLISECONDS.toDays(dateDiff);

           if (second < 60) {
               convTime = second + " Sec " + suffix;
           } else if (minute < 60) {
               convTime = minute + " Min "+suffix;
           } else if (hour < 24) {
               convTime = hour + " Hr " + suffix;
           }else if (hour>24)
           {
               convTime=dataDate;
           }
         //  } else if (day >= 7) {
               //if (day > 360) {
                //   convTime = (day / 360) + " Yrs " + suffix;
               //} else if (day > 30) {
                //   convTime = (day / 30) + " Mon " + suffix;
               //} else {
               //    convTime = (day / 7) + " Wk " + suffix;
             //  }
          // } else if (day < 7) {
             //  convTime = day+" D "+suffix;
           //}

       } catch (ParseException e) {
           e.printStackTrace();
           Log.e("ConvTimeE", e.getMessage());
       }
 return convTime;
    }

}


