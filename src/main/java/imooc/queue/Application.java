package imooc.queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenlufeng
 * @date 2021/9/11
 */
public class Application {
    public static void main(String[] args) {
        Application application = new Application();
//        application.ordinaryTask();
        application.executors();
    }

    /**
     * 普通单线成任务
     */
    private void ordinaryTask() {
        long startTime = System.currentTimeMillis();
        //获取数据库中数据
        CourseService courseStatisticsService = new CourseService();
        List<Course> list = courseStatisticsService.findAll();
        //处理任务
        for (Course course : list) {
            courseStatisticsService.getClassStatisticsInfo(course);
            System.out.println("课程：" + course.getName() + "处理完成！完成时间："+
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("任务耗时："+(endTime - startTime));
    }


    public void executors(){
        long startTime = System.currentTimeMillis();
        //获取数据库中数据
        CourseService courseStatisticsService = new CourseService();
        List<Course> list = courseStatisticsService.findAll();
        //常见固定数量的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //taskTimes 用于记录执行的次数 当任务执行完毕是统计执行时间
        List<String> taskTimes = new ArrayList();
        //处理任务
        for (Course course : list) {
            fixedThreadPool.execute(() -> {

                courseStatisticsService.getClassStatisticsInfo(course);
                System.out.println("课程：" + course.getName() + "处理完成！完成时间："+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                if(taskTimes.size() == list.size()-1){
                    Long endTime = System.currentTimeMillis();
                    System.out.println("任务耗时："+(endTime - startTime));
                }
                taskTimes.add("1");
            });
        }
        fixedThreadPool.shutdown();
    }
}

