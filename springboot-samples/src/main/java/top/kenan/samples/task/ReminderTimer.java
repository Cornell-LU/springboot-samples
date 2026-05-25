package top.kenan.samples.task;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class ReminderTimer {
    public static void main(String[] args) {
        //创建定时器
        Timer timer = new Timer();
        //创建定时任务任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "请休息一下，喝点水吧！");
            }
        };

        timer.schedule(task, 0, 3 * 1000);
    }
}
