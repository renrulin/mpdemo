package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class Mpdemo1010ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void add(){
        User user=new User();
        user.setAge(21);
        user.setEmail("gg@qq.com");
        user.setName("gg");
        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
    }

    @Test
    public void update(){
        User user=new User();
        user.setId(2);
        user.setAge(10);
        int update = userMapper.updateById(user);
        System.out.println(update);
    }

    @Test
    public void testOptimisticLocker(){
        User user = userMapper.selectById(9);

        user.setAge(20);
        userMapper.updateById(user);
    }

    @Test
    public void selectUsers(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        System.out.println(users);
    }

    @Test
    public void testPage(){
        //创建page对象
        //传入两个参数：当前页 和 每页显示记录数
        Page<User> page=new Page<>(1,3);
        //把分页所有数据封装到page对象
        userMapper.selectPage(page,null);
        //通过page对象获取分页数据
        System.out.println("当前页:"+page.getCurrent());
        System.out.println("每页数据list集合:"+page.getRecords());
        System.out.println("每页显示记录数:"+page.getSize());
        System.out.println("总记录数:"+page.getTotal());
        System.out.println("总页数:"+page.getPages());
        System.out.println("下一页:"+page.hasNext());
        System.out.println("上一页:"+page.hasPrevious());

    }

    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(2);
        System.out.println(i);
    }

    @Test
    public void testDeleteByIds(){
        int ids = userMapper.deleteBatchIds(Arrays.asList(8, 9));
        System.out.println(ids);
    }

    //mp实现复杂查询
    @Test
    public void testSelectQuery(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //ge,gt,le,lt   >=,>,<=,<
        //查询age>=12
        //第一个参数字段名称，第二个设置值
//        queryWrapper.ge("age",12);

        //eq,ne
//        queryWrapper.eq("name","aa");//查指定值
//        queryWrapper.ne("name","aa");//查指定值之外

        //between
        //查询年龄在10-12
//        queryWrapper.between("age",10,12);

        //like
        //模糊查询
//        queryWrapper.like("name","a");

        //orderByDesc
        //排序
//        queryWrapper.orderByAsc("age");

        //last
//        queryWrapper.last("limit 2");

        //指定要查询的列
        queryWrapper.select("id","name");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);

    }

}
