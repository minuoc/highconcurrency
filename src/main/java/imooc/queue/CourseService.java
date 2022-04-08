package imooc.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlufeng
 * @date 2021/9/11
 */
public class CourseService {
    public List<Course> findAll(){
        List<Course> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Course((long)i,"course"+i));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return list;
    }

    public void getClassStatisticsInfo(Course course) {
        getUnitNum(course.getId());
        getLearnNum(course.getId());
    }

    /**
     * 获取课程的单元数量
     */
    private void getUnitNum(Long id) {
        try {
            Thread.sleep(1000*1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有关联课程学习总人数
     */
    private void getLearnNum(Long id) {
        try {
            Thread.sleep(1000*1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
