package com.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class smsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // khi có tin nhắn sms nó tự động nhảy vào đây
        //gói tin dược đóng gói lại rồi đưa vào Bundle, sau đó mã hoá, đến nơi nhận thì sẽ giải mã
        Bundle bundle=intent.getExtras();
        Object[] arrMessages= (Object[]) bundle.get("pdus");// khi có nhiều tin cùng lúc gửi tới thì chơi mảng luôn
        String phone, time = "", content;// biết ba thông tin này là đủ
        Date date;
        byte[] bytes;

        SimpleDateFormat sdf= new SimpleDateFormat("dd//mm/yyyy HH:mm:ss");
        for (int i=0; i<arrMessages.length;i++)
        {
            bytes=(byte[]) arrMessages[i];
            SmsMessage message= SmsMessage.createFromPdu(bytes);
            phone=message.getDisplayOriginatingAddress();
            date= new Date(message.getTimestampMillis());
            content=message.getMessageBody();
            time=sdf.format(date);

            String infor= phone + "\n" +time +"\n"+ content;
            Toast.makeText(context, infor, Toast.LENGTH_LONG).show();

       }
    }
}
