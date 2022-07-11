package com.example.dynamicdatasource;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final UserService userService;

    private final DataSource dataSource;

    private final DefaultDataSourceCreator dataSourceCreator;

    @GetMapping("/create")
    public User create() {
        return userService.createUserByName("test");
    }

    @GetMapping("/get")
    public User get() {
        return userService.getUserById(1L).orElse(new User());
    }

    @GetMapping("/datasource/add1")
    public Set<String> add1() {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl("jdbc:mysql://127.0.0.1:3306/test_1?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false&useSSL=false");
        dataSourceProperty.setUsername("root");
        dataSourceProperty.setPassword("123456");
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource("test_1", dataSource);
        return ds.getDataSources().keySet();
    }

    @GetMapping("/datasource/add2")
    public Set<String> add2() {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUrl("jdbc:mysql://127.0.0.1:3306/test_2?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false&useSSL=false");
        dataSourceProperty.setUsername("root");
        dataSourceProperty.setPassword("123456");
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource("test_2", dataSource);
        return ds.getDataSources().keySet();
    }

    @GetMapping("/create1")
    public User create1() {
        DynamicDataSourceContextHolder.push("test_1");//手动切换
        return userService.createUserByName("test1");
    }

    @GetMapping("/create2")
    public User create2() {
        DynamicDataSourceContextHolder.push("test_2");//手动切换
        return userService.createUserByName("test2");
    }
}
