package com.nhnacademy.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("jeongwoo", 25);
        try {
            Class userClass = Class.forName(User.class.getName());
            // Class.forName(className) 물리적인 클래스 파일명을 인자로 넘겨주면 이에 해당하는 클래스를 반환합니다.

            Constructor<?> constructor = userClass.getConstructor();
            // public 접근자를 가진 생성자를 반환합니다.

            User user = (User) constructor.newInstance();
            System.out.println(user);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        try {
            Constructor cUser =
                    Class.forName(User.class.getName()).getConstructor(String.class, Integer.TYPE);
            User user = (User) cUser.newInstance("jeongwoo", 25);
            System.out.println(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Class clazz = Class.forName(User.class.getName());
            Object user = clazz.getDeclaredConstructor().newInstance();
            Method setUserNameMethod = clazz.getDeclaredMethod("setUserName", String.class);
            setUserNameMethod.invoke(user, "NHN 아카데미");
            Method getUserNameMethod = clazz.getDeclaredMethod("getUserName");
            String userName = (String)
                    getUserNameMethod.invoke(user);
            Method setUserAgeMethod = clazz.getDeclaredMethod("setUserAge", Integer.TYPE);
            setUserAgeMethod.invoke(user, 30);
            Method getUserAgeMethod = clazz.getDeclaredMethod("getUserAge");
            int userAge = (int) getUserAgeMethod.invoke(user);
            System.out.println("userName:" + userName);
            System.out.println("userAge:" + userAge);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            Class clazz = Class.forName(User.class.getName());
            Object user = clazz.getDeclaredConstructor().newInstance();
            Field userNameField = clazz.getDeclaredField("userName");
            userNameField.setAccessible(true);
            userNameField.set(user, "jeongwoo");
            String userName = (String) userNameField.get(user);
            Field userAgeField = clazz.getDeclaredField("userAge");
            userAgeField.setAccessible(true);
            userAgeField.set(user, 30);
            int userAge = userAgeField.getInt(user);
            System.out.println("userName:" + userName);
            System.out.println("userAge:" + userAge);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
