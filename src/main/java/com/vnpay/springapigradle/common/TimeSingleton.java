package com.vnpay.springapigradle.common;

public class TimeSingleton {
    // Dùng khi Shared resource, Logger, Configuration, Caching, Thread pool, connect database
    // Đặt private static final variable đảm bảo biến chỉ được khởi tạo trong class.
    private static final TimeSingleton instanceTime = new TimeSingleton();
    private String time;
    private String message;
    // private constructor để hạn chế truy cập từ class bên ngoài
    private TimeSingleton() {
    }
    public static TimeSingleton getInstanceTime() {
        return instanceTime;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    //sử dụng BillPughSingleton vì có hiệu suất cao
    // **************************************************
    //    private TimeSingleton() {
    //    }
    //
    //    public static TimeSingleton getInstance() {
    //        return SingletonHelper.instance;
    //    }
    //
    //    private static class SingletonHelper {
    //        private static final TimeSingleton instance = new TimeSingleton();
    //    }
    // **************************************************
    // **************************************************
    //sử dụng LazyInitializedSingleton cho những ứng dụng chỉ làm việc với ứng dụng single-thread
    //    private static TimeSingleton instance;
    //
    //    private TimeSingleton() {
    //    }
    //
    //    public static TimeSingleton getInstance() {
    //        if (instance == null) {
    //            instance = new TimeSingleton();
    //        }
    //        return instance;
    //    }
    // **************************************************
    //sử dụng DoubleCheckLockingSingleton khi làm việc với ứng dụng multi-thread
    // **************************************************
    //  public static DoubleCheckLockingSingleton getInstance() {
    //        // Do something before get instance ...
    //        if (instance == null) {
    //            // Do the task too long before create instance ...
    //            // Block so other threads cannot come into while initialize
    //            synchronized (DoubleCheckLockingSingleton.class) {
    //                // Re-check again. Maybe another thread has initialized before
    //                if (instance == null) {
    //                    instance = new DoubleCheckLockingSingleton();
    //                }
    //            }
    //        }
    //        // Do something after get instance ...
    //        return instance;
    //    }
    // **************************************************
}
