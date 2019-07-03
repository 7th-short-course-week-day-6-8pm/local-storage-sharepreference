package com.rathana.local_storage.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.rathana.local_storage.model.User;

public class UserPref {

    static final String USER_PREF="user_pref";
    static final String USER_NAME="name";
    static final String USER_PASSWORD="password";
    static final String IS_LOGIN="is_login";

    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE);
    }

    public static void save(Context context, User user){
        SharedPreferences.Editor editor= getPreferences(context).edit();
        if(user!=null){
            editor.putString(USER_NAME,user.getName());
            editor.putString(USER_PASSWORD,user.getPassword());
            editor.putBoolean(IS_LOGIN,false);
            editor.apply();
        }
    }

    public static boolean login(Context context){
        SharedPreferences.Editor editor=getPreferences(context).edit();
        editor.putBoolean(IS_LOGIN,true);
        editor.apply();
        return true;
    }

    public static boolean logout(Context context){
        SharedPreferences.Editor editor=getPreferences(context).edit();
        editor.putBoolean(IS_LOGIN,false);
        editor.apply();
        return false;
    }

    public static boolean isLogin(Context context){
        SharedPreferences preferences=getPreferences(context);
        return preferences.getBoolean(IS_LOGIN,false);
    }

    public static User read(Context context){
        SharedPreferences userPref= getPreferences(context);
        return  new User(userPref.getString(USER_NAME,""),
                userPref.getString(USER_PASSWORD,""),
                userPref.getBoolean(IS_LOGIN,false));
    }

    public static void remove(Context context){
        SharedPreferences.Editor editor=getPreferences(context).edit();
        editor.putString(USER_NAME,null);
        editor.putString(USER_PASSWORD,null);
        editor.putBoolean(IS_LOGIN,false);
        editor.apply();

    }
}
